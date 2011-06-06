package ebaytool.apicall;

import com.mongodb.*;
import ebaytool.apicall.ApiCall;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class GetItem extends ApiCall implements Callable {
	
	public GetItem() throws Exception {
	}
	
	public String call() throws Exception {
		
		BasicDBObject query = new BasicDBObject();
		query.put("ItemID",           new BasicDBObject("$exists", 1));
		query.put("ext.deleted",      new BasicDBObject("$exists", 0));
		query.put("ext.importstatus", "waiting GetItem");
		//query.put("SellingStatus.ListingStatus", "Active");
		
		BasicDBObject field = new BasicDBObject();
		field.put("ItemID", 1);
		
		DBCursor cur = db.getCollection("items").find(query, field);
		Integer cnt = cur.count();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String itemid  = row.get("ItemID").toString();
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel", "ReturnAll");
			reqdbo.append("ItemID", itemid);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetItem");
			writelog("GI.req."+itemid+".xml", requestxml);
			
			ecs18.submit(new ApiCallTask(0, requestxml, "GetItem"));
		}
		
		for (int i = 1; i <= cnt; i++) {
			String responsexml = ecs18.take().get();
			parseresponse(responsexml);
		}
		
		return "";
	}
	
	public BasicDBObject parseresponse(String responsexml) throws Exception {
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		BasicDBObject item = (BasicDBObject) responsedbo.get("Item");
		
		writelog("GI.res."+item.getString("ItemID")+".xml", responsexml);
		
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = new BasicDBObject();
		query.put("ItemID", item.getString("ItemID"));
		
		BasicDBObject upditem = new BasicDBObject();
		upditem.put("ConditionID", item.getString("ConditionID"));
		if (item.containsKey("ProductListingDetails")) {
			upditem.put("ProductListingDetails", item.get("ProductListingDetails"));
		}
		upditem.put("ext.importstatus", "completed");
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", upditem);
		
		WriteResult result = coll.update(query, update);
		
		return responsedbo;
	}
}
