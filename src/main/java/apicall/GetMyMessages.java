package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class GetMyMessages extends ApiCall implements Callable {
	
	private String email;
	private String userid;
    
	public GetMyMessages() throws Exception {
	}
	
	public GetMyMessages(String[] args) throws Exception {
		this.email  = args[0];
		this.userid = args[1];
	}
	
	public String call() throws Exception {
    
		String token = gettoken(email, userid);
    
    BasicDBObject reqdbo = new BasicDBObject();
    reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
    reqdbo.put("WarningLevel", "High");
    reqdbo.put("DetailLevel", "ReturnMessages");
		reqdbo.put("MessageID", getnewtokenmap(email) + " " + userid);
    
    String requestxml = convertDBObject2XML(reqdbo, "GetMyMessages");
		
    Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GetMyMessages"));
		future.get();
    
    // todo: next page (pagination)
    
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
    
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
    
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		String email  = getemailfromtokenmap(messages[0]);
		String userid = messages[1];
    
		writelog("GetMyMessages/" + userid + ".xml", responsexml);
    
		return "";
	}
    
}
