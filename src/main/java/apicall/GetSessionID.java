package ebaytool.apicall;

import com.mongodb.*;
import ebaytool.apicall.ApiCall;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

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
		writelog("GSI.req."+email+".xml", requestxml);
		
		Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GetSessionID"));
		String result = future.get();
		
		return result;
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String sessionid = resdbo.getString("SessionID");
		email = resdbo.getString("CorrelationID");
		log("GetSessionID callback : "+email);
		
		writelog("GSI."+email+".xml", responsexml);
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("sessionid", sessionid));
		
		db.getCollection("users").update(query, update);
		
		return sessionid;
	}
}
