package ebaytool.apicall;

import com.mongodb.*;
import com.mongodb.util.*;
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
		
		String token = gettoken(email, userid);
		
		/* GetSellerList */
		BasicDBObject dbobject = new BasicDBObject();
		//dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put(daterange+"TimeFrom", datestart+" 00:00:00");
		dbobject.put(daterange+"TimeTo",   dateend  +" 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage",20).append("PageNumber",1));
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
			
			future = pool18.submit(new ApiCallTask(0, requestxml, "GetSellerList"));
			future.get();
		}
		
		updatemessage(email, "");
		
		return "OK";
	}
	
	public String callback(String responsexml) throws Exception {
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		String userid = ((JSONObject) json.get("Seller")).get("UserID").toString();
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];
		String token = gettoken(email, userid);
		
		//String userid = ((BasicDBObject) resdbo.get("Seller")).get("UserID").toString();
		
		int pagenumber = Integer.parseInt(resdbo.getString("PageNumber"));
		int itemcount  = Integer.parseInt(resdbo.getString("ReturnedItemCountActual"));
		int itemsperpage = Integer.parseInt(resdbo.getString("ItemsPerPage"));
		
		writelog("GetSellerList/"+email+"."+userid+"."+pagenumber+".xml", responsexml);
		
		if (itemcount == 0) {
			log(userid+" no items.");
			return responsexml;
		}
		
		log(userid+": "
			+pagenumber+"/"
			+((BasicDBObject) resdbo.get("PaginationResult")).get("TotalNumberOfPages").toString()
			+" pages, "
			+((pagenumber-1)*itemsperpage+1)+"-"+((pagenumber-1)*itemsperpage+itemcount)+"/"
			+((BasicDBObject) resdbo.get("PaginationResult")).get("TotalNumberOfEntries").toString()
			+" items");
		
		String message = "Importing "+userid+"'s items from eBay."
			+ " "+((pagenumber-1)*itemsperpage+1)+"-"+((pagenumber-1)*itemsperpage+itemcount)
			+ " of "
			+ ((BasicDBObject) resdbo.get("PaginationResult")).getString("TotalNumberOfEntries")
			+ " items.";
		updatemessage(email, message);
		
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
			
			/* GetItem */
			// todo: Should I replace with GetMultipleItems? -> doesn't return needed info.
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
			reqdbo.append("WarningLevel",                 "High");
			reqdbo.append("DetailLevel",             "ReturnAll");
			reqdbo.append("IncludeCrossPromotion",        "true");
			reqdbo.append("IncludeItemCompatibilityList", "true");
			reqdbo.append("IncludeItemSpecifics",         "true");
			reqdbo.append("IncludeTaxTable",              "true");
			reqdbo.append("IncludeWatchCount",            "true");
			reqdbo.append("ItemID", itemid);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetItem");
			pool18.submit(new ApiCallTask(0, requestxml, "GetItem"));
		}
		
		return responsexml;
	}
	
	/**
	 *
	 * ref: https://jira.mongodb.org/browse/JAVA-260
	 */
	private void movefield(DBObject dbo, DBObject ext, String field) {
		
		String[] path = field.split("\\.", 2);
		
		if (!dbo.containsField(path[0])) {
			log(path[0]+" : NOT EXISTS.");
			return;
		}
		
		String classname = dbo.get(path[0]).getClass().toString();
		
		/* leaf */
		if (path.length == 1) {
			ext.put(path[0], dbo.get(path[0]));
			dbo.removeField(path[0]);
			return;
		}
		
		/* not leaf */
		log(path[0]+" : "+classname+" ("+path[1]+")");
		
		if (classname.equals("class com.mongodb.BasicDBList")) {
			
			if (!ext.containsField(path[0])) {
				ext.put(path[0], new BasicDBList());
			}
			
			BasicDBList orgdbl = (BasicDBList) dbo.get(path[0]);
			BasicDBList extdbl = (BasicDBList) ext.get(path[0]);
			
			for (int i = 0; i < orgdbl.size(); i++) {
				if (extdbl.size() < (i+1)) {
					extdbl.add(new BasicDBObject());
				}
				
				movefield((DBObject) orgdbl.get(i),
						  (DBObject) extdbl.get(i),
						  path[1]);
			}
			
		} else if (classname.equals("class com.mongodb.BasicDBObject")) {
			
			if (!ext.containsField(path[0])) {
				ext.put(path[0], new BasicDBObject());
			}
			
			movefield((DBObject) dbo.get(path[0]),
					  (DBObject) ext.get(path[0]),
					  path[1]);
			
		} else {
			
			log("movefield ERROR "+classname);
			
		}
		
		return;
	}
}
