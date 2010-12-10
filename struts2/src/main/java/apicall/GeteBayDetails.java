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
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		
		String requestxml = convertDBObject2XML(reqdbo, "GeteBayDetails");
		writelog("GeD.req.xml", requestxml);
		
		Future<String> future = ecs18.submit(new ApiCallTask(0, requestxml, "GeteBayDetails"));
		
		String responsexml = future.get();
		writelog("GeD.res.xml", responsexml);
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		for (Object idx : responsedbo.keySet()) {
			String classname = responsedbo.get(idx).getClass().toString();
			
			DBCollection coll = db.getCollection(idx.toString());
			coll.drop();
			
			if (classname.equals("class com.mongodb.BasicDBList")) {
				System.out.println("List ["+idx.toString()+"]");
				coll.insert((List<DBObject>) responsedbo.get(idx));
			} else if (classname.equals("class com.mongodb.BasicDBObject")) {
				System.out.println("Object ["+idx.toString()+"]");
				coll.insert((DBObject) responsedbo.get(idx));
			} else {
				System.out.println("SKIP "+classname+" "+idx.toString());
			}
		}
		
		return "";
	}
	
}
