package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class FetchToken extends ApiCall {

	private String email;
	private String sessionid;
	private String username;
	
	public FetchToken() throws Exception {
	}
    
	public FetchToken(String[] args) throws Exception {
		this.email     = args[0];
		this.sessionid = args[1];
		this.username  = args[2];
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("SessionID", sessionid);
		reqdbo.append("MessageID", email+" "+username);
		
		String requestxml = convertDBObject2XML(reqdbo, "FetchToken");
		
		Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "FetchToken"));
		future.get();
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
        
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email    = messages[0];
		username = messages[1];
		
		writelog("FetchToken/"+email+"."+username+".xml", responsexml);
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("userids."+username, resdbo));
		
		db.getCollection("users").update(query, update);
		
		return "";
	}
}
