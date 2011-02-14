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

public class GetCategories extends ApiCall implements Callable {
	
	public GetCategories() throws Exception {
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
			reqdbo.append("DetailLevel", "ReturnAll");
			reqdbo.append("CategorySiteID", siteid.toString());
			
			String requestxml = convertDBObject2XML(reqdbo, "GetCategories");
			
			Future<String> future =
				ecs18.submit(new ApiCallTask(siteid, requestxml, "GetCategories"));
			
			String responsexml = future.get();
			
			writelog("GCs.req."+site+".xml", requestxml);
			writelog("GCs.res."+site+".xml", responsexml);
			
			BasicDBObject responsedbo = convertXML2DBObject(responsexml);
			
			DBCollection coll = db.getCollection(site+".Categories");
			coll.drop();
			
			coll.insert
			((List<DBObject>) ((BasicDBObject) responsedbo.get("CategoryArray")).get("Category"));
		}
		
		return "";
	}
	
}
