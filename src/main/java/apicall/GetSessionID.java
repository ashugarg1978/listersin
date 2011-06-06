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

public class GetSessionID extends ApiCall implements Callable {
	
	public GetSessionID() throws Exception {
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("RuName", "Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc");
		
		String requestxml = convertDBObject2XML(reqdbo, "GetSessionID");
		
		Future<String> future = ecs18.submit(new ApiCallTask(0, requestxml, "GetSessionID"));
		
		String responsexml = future.get();
		
		writelog("GSI.req.xml", requestxml);
		writelog("GSI.res.xml", responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		String sessionid = responsedbo.getString("SessionID");
		System.out.println(sessionid);
		
		return sessionid;
	}
	
}
