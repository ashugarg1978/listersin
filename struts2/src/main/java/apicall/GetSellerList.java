package ebaytool.apicall;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import ebaytool.apicall.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import java.util.HashMap;

public class GetSellerList extends ApiCall {
	
	public GetSellerList() {
	}
	
	public String call() throws Exception {
		
		DBObject user = db.getCollection("users").findOne();
		
		Map userids = ((BasicDBObject) user.get("userids")).toMap();
		for (Object userid : userids.keySet()) {
			JSONObject json = JSONObject.fromObject(userids.get(userid).toString());
			String token = json.get("ebaytkn").toString();
			call2(userid.toString(), token);
		}
		
		return "OK";
	}
	
	private void call2(String userid, String token) throws Exception {
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("StartTimeFrom", "2010-09-01 00:00:00");
		dbobject.put("StartTimeTo",   "2010-12-01 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage", 50).append("PageNumber", 1));
		dbobject.put("Sort", "1");
		
		String requestxml = convertDBObject2XML(dbobject);
		
		Future<String> future = pool18.submit(new ApiCallTask(0, requestxml));
		String responsexml = future.get();
		
		BasicDBObject result = convertXML2DBObject(responsexml);
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		System.out.println(userid+" : total "+pages+" page(s).");
		
		/*
		for (int i=2; i<=pages; i++) {
			((BasicDBObject) dbobject.get("Pagination")).put("PageNumber", i);
			pool18.submit(new ApiCallTask(0, requestxml));
		}
		*/
		
		return;
	}
	
	private BasicDBObject test() throws Exception {
		
		String requestxml = "";
		String responsexml = "";
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		String userid = ((JSONObject) json.get("Seller")).get("UserID").toString();
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);

		String pagenumber = responsedbo.get("PageNumber").toString();
		writelog("GSL.req."+userid+"."+pagenumber+".xml", requestxml);
		writelog("GSL.res."+userid+"."+pagenumber+".xml", responsexml);
		
		int rica = Integer.parseInt(json.get("ReturnedItemCountActual").toString());
		if (rica == 0) {
			return responsedbo;
		}
		
		// todo : aware whether count is 1.
		JSONArray jsonarr = new JSONArray();
		if (rica == 1) {
			jsonarr.add(json.getJSONObject("ItemArray").getJSONObject("Item"));
		} else {
			jsonarr = json.getJSONObject("ItemArray").getJSONArray("Item");
		}
		for (Object item : jsonarr) {
			
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
		
		return responsedbo;
	}
}
