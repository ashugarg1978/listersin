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

public class AddMemberMessageRTQ extends ApiCall implements Callable {
	
	private String email;
	private String userid;
  private String itemid;
  private String parent;
  private String body;
    
	public AddMemberMessageRTQ() throws Exception {
    }
	
	public AddMemberMessageRTQ(String[] args) throws Exception {
		this.email  = args[0];
		this.userid = args[1];
    this.itemid = args[2];
    this.parent = args[3];
    this.body   = args[4];
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
    reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
    reqdbo.append("WarningLevel", "High");
    reqdbo.append("DetailLevel", "ReturnAll");
    reqdbo.append("ItemID", itemid);
    reqdbo.append("MessageID", email+" "+userid+" "+itemid);
		
		BasicDBObject mm = new BasicDBObject();
		mm.put("ParentMessageID", parent);
		mm.put("Body", body);
    
		reqdbo.append("MemberMessage", mm);
		
    String requestxml = convertDBObject2XML(reqdbo, "AddMemberMessageRTQ");
		
		Future<String> future =
      pool18.submit(new ApiCallTask(userid, 0, requestxml, "AddMemberMessageRTQ"));
		String result = future.get();
    
		return result;
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];
    itemid = messages[2];
		
		writelog("AddMemberMessageRTQ/"+email+"."+userid+"."+itemid+".xml", responsexml);
		
		String ack = resdbo.get("Ack").toString();
		
		return ack;
	}
    
}
