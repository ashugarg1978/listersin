package ebaytool.apicall;

import com.mongodb.*;
import com.mongodb.util.*;
import ebaytool.apicall.ApiCall;
import ebaytool.apicall.GetItem;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class GetSellerList extends ApiCall {

	private String email;
	private String userid;
	private String daterange;
	private String datestart;
	private String dateend;
	
	public GetSellerList() throws Exception {
	}
	
	public GetSellerList(String email, String userid,
						 String daterange, String datestart, String dateend) throws Exception {
		
		this.email     = email;
		this.userid    = userid;
		this.daterange = daterange;
		this.datestart = datestart;
		this.dateend   = dateend;
	}
	
	public String call() throws Exception {
		
		/* get token from db */
		/*
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("userids."+userid, new BasicDBObject("$exists", 1));

		BasicDBObject fields = new BasicDBObject();
		fields.put("userids."+userid, 1);
		
		BasicDBObject user = (BasicDBObject) db.getCollection("users").findOne(query, fields);
		
		BasicDBObject useriddbo = (BasicDBObject) user.get("userids");
		BasicDBObject tokendbo  = (BasicDBObject) useriddbo.get(userid);
		String token = tokendbo.getString("eBayAuthToken");
		*/
		String token = gettoken(email, userid);
		
		/* GetSellerList */
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put(daterange+"TimeFrom", datestart+" 00:00:00");
		dbobject.put(daterange+"TimeTo",   dateend  +" 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage",7).append("PageNumber",1));
		dbobject.put("Sort", "1");
		dbobject.put("MessageID", email+" "+userid);
		//dbobject.put("UserID", "testuser_sbmsku");
		
		String requestxml = convertDBObject2XML(dbobject, "GetSellerList");
		//writelog("GSL.req."+email+"."+userid+".xml", requestxml);
		Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GetSellerList"));
		String responsexml = future.get();
		
		BasicDBObject result = convertXML2DBObject(responsexml);
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		log(userid+" : total "+pages+" page(s).");
		
		for (int i=2; i<=pages; i++) {
			((BasicDBObject) dbobject.get("Pagination")).put("PageNumber", i);
			requestxml = convertDBObject2XML(dbobject, "GetSellerList");
			pool18.submit(new ApiCallTask(0, requestxml, "GetSellerList"));
		}
		
		// todo: call GetItem here.
		// todo: Should I replace with GetMultipleItems? -> doesn't return needed info.
		//ecs18.submit(new GetItem());
		if (false) {
			log("Calling GetItem from GetSellerList.");
			ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
			Callable task = new GetItem(email, userid);
			pool.submit(task);
		}
		
		return "OK";
	}
	
	public String callback(String responsexml) throws Exception {
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		String userid = ((JSONObject) json.get("Seller")).get("UserID").toString();
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		String[] messages = responsedbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];
		String token = gettoken(email, userid);
		
		//String userid = ((BasicDBObject) responsedbo.get("Seller")).get("UserID").toString();
		
		String pagenumber = responsedbo.get("PageNumber").toString();
		
		log(userid
			+" "+responsedbo.get("PageNumber").toString()
			+"/"+((BasicDBObject) responsedbo.get("PaginationResult"))
			.get("TotalNumberOfPages").toString()
			+" "+responsedbo.get("ReturnedItemCountActual").toString()
			+"/"+((BasicDBObject) responsedbo.get("PaginationResult"))
			.get("TotalNumberOfEntries").toString());
		
		writelog("GSL.res."+userid+"."+pagenumber+".xml", responsexml);
		
		int itemcount = Integer.parseInt(json.get("ReturnedItemCountActual").toString());
		if (itemcount == 0) return "";
		
		DBCollection coll = db.getCollection("items");
		
		JSONArray jsonarr = new JSONArray();
		if (itemcount == 1) {
			jsonarr.add(json.getJSONObject("ItemArray").getJSONObject("Item"));
		} else {
			jsonarr = json.getJSONObject("ItemArray").getJSONArray("Item");
		}
		for (Object item : jsonarr) {
			
			/* convert JSON to DBObject */
			DBObject dbobject = (DBObject) com.mongodb.util.JSON.parse(item.toString());
			String itemid = dbobject.get("ItemID").toString();
			
			/* add extended information */
			BasicDBObject ext = new BasicDBObject();
			ext.put("UserID", userid);
			ext.put("labels", new BasicDBList());
			ext.put("importstatus", "waiting GetItem");
			dbobject.put("ext", ext);
			
			/* insert into mongodb */
			BasicDBObject query = new BasicDBObject();
			query.put("ItemID", itemid);
			
			BasicDBObject update = new BasicDBObject();
			update.put("$set", dbobject);
			
			coll.findAndRemove(query);
			coll.update(query, update, true, true);
			
			/* GetItem */
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel", "ReturnAll");
			reqdbo.append("ItemID", itemid);
			String requestxml = convertDBObject2XML(reqdbo, "GetItem");
			
			pool18.submit(new ApiCallTask(0, requestxml, "GetItem"));
			//Callable task = new GetItem(email, userid, itemid);
			//pool18.submit(task);
		}
		
		return responsexml;
	}
	
	
	/* will be called from IndexAction.receivenotify() */
	public void parsenotifyxml(String notifyxml) throws Exception {
		
		JSONObject json = (JSONObject) new XMLSerializer().read(notifyxml);
		
		JSONObject item = json
			.getJSONObject("soapenv:Body")
			.getJSONObject("GetItemResponse")
			.getJSONObject("Item");
		
		String notificationeventname = json
			.getJSONObject("soapenv:Body")
			.getJSONObject("GetItemResponse")
			.get("NotificationEventName")
			.toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS");
		Date now = new Date();
		String timestamp = sdf.format(now).toString();
		
		writelog("NTF."+notificationeventname+"."+timestamp+".xml", notifyxml);
		
		
		// todo: event name operation

		String userid = ((JSONObject) item.get("Seller")).get("UserID").toString();
		
		DBCollection coll = db.getCollection("items");
		
		if (false) {
			
			BasicDBObject ext = new BasicDBObject();
			ext.put("UserID", userid);
			ext.put("labels", new BasicDBList());
			
			/* convert JSON to DBObject */
			DBObject dbobject = (DBObject) com.mongodb.util.JSON.parse(item.toString());
			dbobject.put("ext", ext);
			
			BasicDBObject query = new BasicDBObject();
			query.put("ItemID", dbobject.get("ItemID").toString());
			
			BasicDBObject update = new BasicDBObject();
			update.put("$set", dbobject);
			
			/* insert into mongodb */
			coll.findAndRemove(query);
			coll.update(query, update, true, true);
			
		}
		
		return;
	}
}
