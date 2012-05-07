package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;

public class GetDescriptionTemplates extends ApiCall {
	
	public GetDescriptionTemplates() throws Exception {
	}
	
	public String call() throws Exception {
		
		DBObject row = db.getCollection("US.eBayDetails")
			.findOne(null, new BasicDBObject("SiteDetails", 1));
		BasicDBList sitedetails = (BasicDBList) row.get("SiteDetails");
		for (Object sitedbo : sitedetails) {
			
			String  site   = ((BasicDBObject) sitedbo).getString("Site");
			Integer siteid = Integer.parseInt(((BasicDBObject) sitedbo).getString("SiteID"));
			log(site);
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel",  "ReturnAll");
			reqdbo.append("MessageID",    site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetDescriptionTemplates");
			pool18.submit(new ApiCallTask(siteid, requestxml, "GetDescriptionTemplates"));
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetDescriptionTemplates/"+site+".xml", responsexml);
		
		for (Object idx : resdbo.keySet()) {

			log(idx.toString());
			
			// todo: don't skip
			if (idx.equals("ObsoleteLayoutID")) continue;
                        
			DBCollection coll = db.getCollection(site+".DescriptionTemplates."+idx.toString());
			if (db.collectionExists(site+".DescriptionTemplates."+idx.toString())) {
				coll.drop();
			}
			
			String classname = resdbo.get(idx.toString()).getClass().toString();
			if (classname.equals("class com.mongodb.BasicDBList")) {
				coll.insert((List<DBObject>) resdbo.get(idx.toString()));
			} else if (classname.equals("class com.mongodb.BasicDBObject")) {
				coll.insert((DBObject) resdbo.get(idx.toString()));
			} else {
				//log("SKIP "+classname+" "+idx.toString());
			}
		}
		
		return "";
	}
}
