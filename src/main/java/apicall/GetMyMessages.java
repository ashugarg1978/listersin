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
        
		/* get token from db */
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("userids."+userid, new BasicDBObject("$exists", 1));
		
		BasicDBObject fields = new BasicDBObject();
		fields.put("userids."+userid, 1);
		
		BasicDBObject user = (BasicDBObject) db.getCollection("users").findOne(query, fields);
		
		BasicDBObject useriddbo = (BasicDBObject) user.get("userids");
		BasicDBObject tokendbo  = (BasicDBObject) useriddbo.get(userid);
		String token = tokendbo.getString("eBayAuthToken");
		
        BasicDBObject reqdbo = new BasicDBObject();
        reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
        reqdbo.put("WarningLevel", "High");
        reqdbo.put("DetailLevel", "ReturnHeaders");
		reqdbo.put("MessageID", email+" "+userid);
        
        String requestxml = convertDBObject2XML(reqdbo, "GetMyMessages");
		
        Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GetMyMessages"));
		future.get();
        
        // todo: next page (pagination)
        
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_S");
		Date now = new Date();
		writelog("GetMyMessages/res"+sdf.format(now).toString()+".xml", responsexml);
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		return "";
	}
    
}
