package ebaytool.apicall;

import com.mongodb.*;
import com.mongodb.util.*;
import ebaytool.apicall.ApiCall;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class GeteBayDetails extends ApiCall implements Callable {
	
	public GeteBayDetails() throws Exception {
	}
	
	public String call() throws Exception {
		
		String  site   = "US";
		Integer siteid = 0;
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("MessageID", site);
		
		String requestxml = convertDBObject2XML(reqdbo, "GeteBayDetails");
		Future<String> future = ecs18.submit(new ApiCallTask(0, requestxml, "GeteBayDetails"));
		String responsexml = future.get();
		parseresponse(responsexml);
		
		//writelog("GeD.req."+site+".xml", requestxml);
		
		DBCursor cur = db.getCollection("US.eBayDetails.SiteDetails").find();
		Integer cnt = cur.count() - 1;
		cur.next(); /* Skip US here */
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			site   = row.get("Site").toString();
			siteid = Integer.parseInt(row.get("SiteID").toString());
			
			reqdbo.put("MessageID", site);
			
			requestxml  = convertDBObject2XML(reqdbo, "GeteBayDetails");
			ecs18.submit(new ApiCallTask(siteid, requestxml, "GeteBayDetails"));
			
			//writelog("GeD.req."+site+".xml", requestxml);
		}
		
		for (int i = 1; i <= cnt; i++) {
			responsexml = ecs18.take().get();
			parseresponse(responsexml);
		}
		
		return "";
	}
	
	private BasicDBObject parseresponse(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GeD.res."+site+".xml", responsexml);
		
		for (Object idx : resdbo.keySet()) {
			String classname = resdbo.get(idx).getClass().toString();
			
			DBCollection coll = db.getCollection(site+".eBayDetails."+idx.toString());
			
			if (db.collectionExists(site+".eBayDetails."+idx.toString())) {
				coll.drop();
			}
			
			if (classname.equals("class com.mongodb.BasicDBList")) {
				
				//log("List ["+idx.toString()+"]");
				coll.insert((List<DBObject>) resdbo.get(idx));
				
			} else if (classname.equals("class com.mongodb.BasicDBObject")) {
				
				//log("Object ["+idx.toString()+"]");
				coll.insert((DBObject) resdbo.get(idx));
				
			} else {
				
				//log("SKIP "+classname+" "+idx.toString());
				
			}
		}
		
		return resdbo;
	}
}
