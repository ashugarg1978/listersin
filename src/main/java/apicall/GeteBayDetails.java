package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class GeteBayDetails extends ApiCall {
	
	public GeteBayDetails() throws Exception {
	}
	
	public String call() throws Exception {
		
		String  site   = "US";
		Integer siteid = 0;
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("MessageID", site);
		
		/* at first, get only US */
		String requestxml = convertDBObject2XML(reqdbo, "GeteBayDetails");
		Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GeteBayDetails"));
		String responsexml = future.get();
		
		DBObject row = db.getCollection("US.eBayDetails")
			.findOne(null, new BasicDBObject("SiteDetails", 1));
		BasicDBList sitedetails = (BasicDBList) row.get("SiteDetails");
		for (Object sitedbo : sitedetails) {
			// todo: skip US
			site   = ((BasicDBObject) sitedbo).getString("Site");
			siteid = Integer.parseInt(((BasicDBObject) sitedbo).getString("SiteID"));
			log(site+":"+siteid);
			
			reqdbo.put("MessageID", site);
			
			requestxml  = convertDBObject2XML(reqdbo, "GeteBayDetails");
			pool18.submit(new ApiCallTask(siteid, requestxml, "GeteBayDetails"));
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GeteBayDetails/"+site+".xml", responsexml);
		
		for (Object idx : resdbo.keySet()) {
			
			DBCollection coll = db.getCollection(site+".eBayDetails."+idx.toString());
			if (db.collectionExists(site+".eBayDetails."+idx.toString())) {
				coll.drop();
			}
			
			String classname = resdbo.get(idx).getClass().toString();
			if (classname.equals("class com.mongodb.BasicDBList")) {
				//coll.insert((List<DBObject>) resdbo.get(idx));
			} else if (classname.equals("class com.mongodb.BasicDBObject")) {
				//coll.insert((DBObject) resdbo.get(idx));
			} else {
				//log("SKIP "+classname+" "+idx.toString());
			}
		}
		
		DBCollection coll = db.getCollection(site+".eBayDetails");
		if (db.collectionExists(site+".eBayDetails")) {
			coll.drop();
		}
		coll.insert(resdbo);
		
		
		return responsexml;
	}
}
