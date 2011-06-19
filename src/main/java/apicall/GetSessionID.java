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

public class GetSessionID extends ApiCall {
	
	private String email;
	
	public GetSessionID() throws Exception {
	}
	
	public GetSessionID(String email) throws Exception {
		this.email  = email;
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("RuName", "Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc");
		reqdbo.append("MessageID", email);
		
		String requestxml = convertDBObject2XML(reqdbo, "GetSessionID");
		
		Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GetSessionID"));
		future.get();
		
		return "";
	}
	
	public BasicDBObject parseresponse(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String sessionid = resdbo.getString("SessionID");
		email = resdbo.getString("CorrelationID");
		log("GetSessionID parse response. "+email);
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("sessionid", sessionid));
		
		db.getCollection("users").update(query, update);
		
		return resdbo;
	}
}
