package ebaytool;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import ebaytool.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import java.util.HashMap;

public class GetSellerList extends ApiCall implements Callable {
	
	private int idx;
	private HashMap<Integer,BasicDBObject> mapdbo = new HashMap<Integer,BasicDBObject>();
	
	public GetSellerList (int idx, BasicDBObject requestdbobject) {
		this.idx = idx;
		mapdbo.put(idx, requestdbobject);
	}
	
	public BasicDBObject call() throws Exception {
		
		int idx = this.idx;
		
		BasicDBObject requestdbo = mapdbo.get(idx);
		String requestxml = convertDBObject2XML(requestdbo);
		
		//System.out.println(idx+" req "+requestdbo.get("Pagination").toString());
		
		String responsexml = callapi("GetSellerList", requestxml);
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		String userid = ((JSONObject) json.get("Seller")).get("UserID").toString();
		
		writelog("GetSellerList."+userid+".xml", responsexml);
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		System.out.println(idx+" res "+responsedbo.get("PageNumber").toString());
		
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
			try {
				coll.update(query, update, true, true);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			//System.out.println(dbobject.get("Title"));
		}
		
		return responsedbo;
	}
	
}
