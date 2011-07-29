package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;

public class GetCategoryFeatures extends ApiCall {
	
	public GetCategoryFeatures() throws Exception {
	}
	
	public String call() throws Exception {
		
		DBCursor cur = db.getCollection("US.eBayDetails.SiteDetails").find();
		Integer cnt = cur.count();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel",  "ReturnAll");
			reqdbo.append("ViewAllNodes", "true");
			reqdbo.append("MessageID",    site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetCategoryFeatures");
			pool18.submit(new ApiCallTask(siteid, requestxml, "GetCategoryFeatures"));
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetCategoryFeatures/"+site+".xml", responsexml);
		
		DBCollection coll2 = db.getCollection(site+".CategoryFeatures.Category");
		coll2.drop();
		coll2.insert((List<DBObject>) resdbo.get("Category"));
		
		DBCollection coll1 = db.getCollection(site+".CategoryFeatures");
		if (db.collectionExists(site+".CategoryFeatures")) {
			coll1.drop();
		}
		
		resdbo.removeField("Category");
		coll1.insert(resdbo);
		
		return "";
	}
}
