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
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("userids."+userid, new BasicDBObject("$exists", 1));
		
		BasicDBObject user = (BasicDBObject) db.getCollection("users").findOne(query);
		
		if (true) {
			System.out.println(user.get("userids").toString());
			return "OK";
		}
		
		/*
		DBObject user = db.getCollection("users").findOne();
		
		Map userids = ((BasicDBObject) user.get("userids")).toMap();
		for (Object userid : userids.keySet()) {
			JSONObject json = JSONObject.fromObject(userids.get(userid).toString());
			String token = json.get("eBayAuthToken").toString();
			call2(userid.toString(), token);
		}
		*/
		
		// todo: call GetItem here.
		// todo: Should I replace with GetMultipleItems?
		//ecs18.submit(new GetItem());
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
		Callable task = new GetItem();
		pool.submit(task);
		
		return "OK";
	}
	
	private void call2(String userid, String token) throws Exception {
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("StartTimeFrom", "2011-04-01 00:00:00");
		dbobject.put("StartTimeTo",   "2011-06-01 00:00:00");
		//dbobject.put("EndTimeFrom", "2011-01-01 00:00:00");
		//dbobject.put("EndTimeTo",   "2011-03-01 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage",200).append("PageNumber",1));
		dbobject.put("Sort", "1");
		//dbobject.put("UserID", "testuser_sbmsku");
		
		String requestxml = convertDBObject2XML(dbobject, "GetSellerList");
		Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GetSellerList"));
		String responsexml = future.get();
		parseresponse(responsexml);
		
		writelog("GSL.req."+userid+".1.xml", requestxml);
		
		BasicDBObject result = convertXML2DBObject(responsexml);
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		System.out.println(userid+" : total "+pages+" page(s).");
		
		for (int i=2; i<=pages; i++) {
			((BasicDBObject) dbobject.get("Pagination")).put("PageNumber", i);
			requestxml = convertDBObject2XML(dbobject, "GetSellerList");
			ecs18.submit(new ApiCallTask(0, requestxml, "GetSellerList"));
		}
		
		for (int i=2; i<=pages; i++) {
			responsexml = ecs18.take().get();
			parseresponse(responsexml);
		}

		return;
	}
	
	private BasicDBObject parseresponse(String responsexml) throws Exception {
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		String userid = ((JSONObject) json.get("Seller")).get("UserID").toString();
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		String pagenumber = responsedbo.get("PageNumber").toString();
		
		System.out.println
			(userid
			 +" "+responsedbo.get("PageNumber").toString()
			 +"/"+((BasicDBObject) responsedbo.get("PaginationResult"))
			 .get("TotalNumberOfPages").toString()
			 +" "+responsedbo.get("ReturnedItemCountActual").toString()
			 +"/"+((BasicDBObject) responsedbo.get("PaginationResult"))
			 .get("TotalNumberOfEntries").toString());
		
		writelog("GSL.res."+userid+"."+pagenumber+".xml", responsexml);
		
		int itemcount = Integer.parseInt(json.get("ReturnedItemCountActual").toString());
		if (itemcount == 0) return responsedbo;
		
		DBCollection coll = db.getCollection("items");
		
		JSONArray jsonarr = new JSONArray();
		if (itemcount == 1) {
			jsonarr.add(json.getJSONObject("ItemArray").getJSONObject("Item"));
		} else {
			jsonarr = json.getJSONObject("ItemArray").getJSONArray("Item");
		}
		for (Object item : jsonarr) {
			
			BasicDBObject ext = new BasicDBObject();
			ext.put("UserID", userid);
			ext.put("labels", new BasicDBList());
			ext.put("importstatus", "waiting GetItem");
			
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
		
		return responsedbo;
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
