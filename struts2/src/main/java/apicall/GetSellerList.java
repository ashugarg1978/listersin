package ebaytool.apicall;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import ebaytool.apicall.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import java.util.HashMap;

public class GetSellerList extends ApiCall implements Callable {
	
	private String requestxml;
	
	public GetSellerList (BasicDBObject requestdbobject) {
		this.requestxml = convertDBObject2XML(requestdbobject);
	}
	
	public BasicDBObject call() throws Exception {
		
		String responsexml = callapi(0, requestxml);
		
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
		
		JSONArray jsonarr = json.getJSONObject("ItemArray").getJSONArray("Item");
		for (Object item : jsonarr) {
			
			/* convert JSON to DBObject */
			DBObject dbobject = (DBObject) com.mongodb.util.JSON.parse(item.toString());
			dbobject.put("UserID", userid);
			dbobject.put("deleted", 0);
			
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
