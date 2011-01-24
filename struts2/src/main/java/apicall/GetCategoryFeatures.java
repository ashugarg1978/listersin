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
		
		DBCursor cur = db.getCollection("SiteDetails").find();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			System.out.println(site+"("+siteid+")");
			
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel",  "ReturnAll");
			reqdbo.append("ViewAllNodes", "true");
			
			String requestxml = convertDBObject2XML(reqdbo, "GetCategoryFeatures");
			
			Future<String> future =
				ecs18.submit(new ApiCallTask(siteid, requestxml, "GetCategoryFeatures"));
			
			String responsexml = future.get();
			
			writelog("GCF.req."+site+".xml", requestxml);
			writelog("GCF.res."+site+".xml", responsexml);
			
			
			BasicDBObject responsedbo = convertXML2DBObject(responsexml);
			
			DBCollection coll1 = db.getCollection("CategoryFeatures_"+site);
			DBCollection coll2 = db.getCollection("CategoryFeatures_"+site+"_Category");
			
			coll1.drop();
			coll2.drop();
			
			coll1.insert(responsedbo);
			coll2.insert((List<DBObject>) responsedbo.get("Category"));
		}
		
		return "";
	}
	
}
