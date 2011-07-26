package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;

public class GetDescriptionTemplates extends ApiCall {
	
	public GetDescriptionTemplates() throws Exception {
	}
	
	public String call() throws Exception {
		
		DBCursor cur = db.getCollection("US.eBayDetails.SiteDetails").find();
		Integer cnt = cur.count();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			log(site+"("+siteid+")");
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel",   "High");
			reqdbo.append("DetailLevel",    "ReturnAll");
			reqdbo.append("MessageID",      site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetDescriptionTemplates");
			pool18.submit(new ApiCallTask(siteid, requestxml, "GetDescriptionTemplates"));
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GDT."+site+".xml", responsexml);
		
		return "";
	}
}
