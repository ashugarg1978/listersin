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

public class FetchToken extends ApiCall implements Callable {

	private String email;
	private String sessionid;
	private String username;
	
	public FetchToken(String email, String sessionid, String username) throws Exception {
		this.email = email;
		this.sessionid = sessionid;
		this.username = username;
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("SessionID", sessionid);
		
		String requestxml = convertDBObject2XML(reqdbo, "FetchToken");
		
		Future<String> future = ecs18.submit(new ApiCallTask(0, requestxml, "FetchToken"));
		
		String responsexml = future.get();
		
		writelog("FT.req.xml", requestxml);
		writelog("FT.res.xml", responsexml);
		
		
		return "";
	}
	
	public BasicDBObject parseresponse(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("sessionid", sessionid);
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("userids."+username, resdbo));
		
		db.getCollection("users").update(query, update);
		
		return resdbo;
	}
}
