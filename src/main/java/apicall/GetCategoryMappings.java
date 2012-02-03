package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class GetCategoryMappings extends ApiCall {
	
	public GetCategoryMappings() throws Exception {
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
			
			String requestxml = convertDBObject2XML(reqdbo, "GetCategoryMappings");
			Future<String> future =
				pool18.submit(new ApiCallTask(siteid, requestxml, "GetCategoryMappings", "filename"));
			future.get();
		}
		
		return "";
	}
	
	public String callback(String filename) throws Exception {
		
		String responsexml = readfile(filename);
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String site = resdbo.getString("CorrelationID");
		writelog("GetCategoryMappings/"+site+".xml", responsexml);
		
		DBCollection coll = db.getCollection(site+".CategoryMappings");
		if (db.collectionExists(site+".CategoryMappings")) {
			coll.drop();
		}
		coll.insert((List<DBObject>) resdbo.get("CategoryMapping"));
		
		return "";
	}
}
