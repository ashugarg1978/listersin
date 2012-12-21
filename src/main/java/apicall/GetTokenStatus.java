package ebaytool.apicall;

import com.mongodb.*;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class GetTokenStatus extends ApiCall implements Callable {
	
	private String email;
	private String userid;
	
	public GetTokenStatus() throws Exception {
	}
    
	public GetTokenStatus(String[] args) throws Exception {
		this.email  = args[0];
		this.userid = args[1];
	}
	
	public String call() throws Exception {
    
		/* get token from db */
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("userids2.username", userid);
    
		String token = gettoken(email, userid);
    
		/* GetTokenStatus */
		BasicDBObject reqdbo = new BasicDBObject();
    reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
    reqdbo.put("WarningLevel", "High");
		reqdbo.put("MessageID", email+" "+userid);
		
		String requestxml = convertDBObject2XML(reqdbo, "GetTokenStatus");
    
    pool18.submit(new ApiCallTask(0, requestxml, "GetTokenStatus"));
    
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
        
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];

		writelog("GetTokenStatus/"+email+"."+userid+".xml", responsexml);
    
    BasicDBObject tokenstatus = (BasicDBObject) resdbo.get("TokenStatus");
    
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("userids2.username", userid);
    
    BasicDBObject update = new BasicDBObject();
    update.put("$set", new BasicDBObject("userids2.$.TokenStatus", tokenstatus));
    
    db.getCollection("users").update(query, update);
    
		return "";
	}
}
