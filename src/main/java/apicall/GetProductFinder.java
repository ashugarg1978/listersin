package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import org.apache.commons.lang.StringEscapeUtils;

public class GetProductFinder extends ApiCall {
	
	public GetProductFinder() throws Exception {
	}
	
	public String call() throws Exception {
		
		DBCursor cur = db.getCollection("US.eBayDetails.SiteDetails").find();
		Integer cnt = cur.count();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());

			if (!site.equals("HongKong")) continue;
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel",  "ReturnAll");
			reqdbo.append("MessageID",    site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetProductFinder");
			
			Future<String> future =
				pool18.submit(new ApiCallTask(siteid, requestxml, "GetProductFinder"));
			future.get();
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		if (site == null) {
			site = "US";
		}
		writelog("GetProductFinder/"+site+".xml", responsexml);
		
		String data = resdbo.getString("ProductFinderData");
		String decoded = StringEscapeUtils.unescapeHtml(data);
		
		writelog("GetProductFinder/"+site+".decoded.xml", decoded);
		
		BasicDBObject dbo = convertXML2DBObject(decoded);
		
		for (Object idx : dbo.keySet()) {
			String classname = dbo.get(idx).getClass().toString();
			log(classname+" "+idx.toString());
		}
		
		DBCollection coll = db.getCollection(site+".ProductFinder");
		if (db.collectionExists(site+".ProductFinderCategory2CS.Category")) {
			coll.drop();
		}
		coll.insert((List<DBObject>) ((BasicDBList) dbo.get("ProductFinders")).get("ProductFinder"));
		//coll.insert((List<DBObject>) ((BasicDBObject) dbo.get("ProductFinders")).get("ProductFinder"));
		
		return "";
	}
}
