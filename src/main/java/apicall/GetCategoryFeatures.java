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

public class GetCategoryFeatures extends ApiCall implements Callable {
	
	public GetCategoryFeatures() throws Exception {
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
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel",  "ReturnAll");
			reqdbo.append("ViewAllNodes", "true");
			reqdbo.append("MessageID",    site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetCategoryFeatures");
			ecs18.submit(new ApiCallTask(siteid, requestxml, "GetCategoryFeatures"));
			
			//writelog("GCF.req."+site+".xml", requestxml);
		}
		
		/* parse response */
		for (int i = 1; i <= cnt; i++) {
			String responsexml = ecs18.take().get();
			BasicDBObject resdbo = convertXML2DBObject(responsexml);
			String site = resdbo.getString("CorrelationID");
			
			writelog("GCF.res."+site+".xml", responsexml);
			
			DBCollection coll2 = db.getCollection(site+".CategoryFeatures.Category");
			coll2.drop();
			coll2.insert((List<DBObject>) resdbo.get("Category"));
			
			DBCollection coll1 = db.getCollection(site+".CategoryFeatures");
			coll1.drop();
			resdbo.removeField("Category");
			coll1.insert(resdbo);
		}
		
		return "";
	}
	
}
