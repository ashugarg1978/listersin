package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;

public class GetCategory2CS extends ApiCall {
	
	public GetCategory2CS() throws Exception {
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
			reqdbo.append("MessageID",    site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetCategory2CS");
			pool18.submit(new ApiCallTask(siteid, requestxml, "GetCategory2CS"));
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetCategory2CS/"+site+".xml", responsexml);
		
		/*
		DBCollection coll = db.getCollection(site+".Category2CS");
		if (db.collectionExists(site+".Category2CS")) {
			coll.drop();
		}
		coll.insert((List<DBObject>) resdbo.get("Category2CS"));
		*/
		
		return "";
	}
}
