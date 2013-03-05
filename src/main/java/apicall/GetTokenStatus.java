package ebaytool.apicall;

import com.mongodb.*;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.bson.types.ObjectId;

public class GetTokenStatus extends ApiCall implements Callable {
  
	public GetTokenStatus() throws Exception {
	}
  
	public String call() throws Exception {
    
		DBCollection coll = db.getCollection("users");
    
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			BasicDBObject user = (BasicDBObject) cursor.next();
      
      String email = user.getString("email");
      
      if (!user.containsField("userids2")) {
        log(email + " no userids2. skip.");
        continue;
      }
      if (email.equals("demo@listers.in")) {
        continue;
      }
      
      for (Object useridobj : (BasicDBList) user.get("userids2")) {
        
        String userid = ((BasicDBObject) useridobj).getString("username");
        String token  = ((BasicDBObject) useridobj).getString("eBayAuthToken");
        
        /* TokenMap */
        ObjectId newid = new ObjectId();
        
        BasicDBObject newitem = new BasicDBObject();
        newitem.put("_id", newid);
        newitem.put("email", email);
        
        db.getCollection("tokenmap").insert(newitem, WriteConcern.SAFE);
        
        log(email + " " + userid);
        
        /* GetTokenStatus */
        BasicDBObject reqdbo = new BasicDBObject();
        reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
        reqdbo.put("WarningLevel", "High");
        reqdbo.put("MessageID", newid.toString() + " " + userid);
        
        String requestxml = convertDBObject2XML(reqdbo, "GetTokenStatus");
        
        pool18.submit(new ApiCallTask(0, requestxml, "GetTokenStatus"));
      }
    }
    
    return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
    
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		String tokenid = messages[0];
		String userid  = messages[1];
    
    /* TokenMap */
		BasicDBObject tokenquery = new BasicDBObject();
		tokenquery.put("_id", new ObjectId(tokenid));
    
		BasicDBObject tokenmap = (BasicDBObject) db.getCollection("tokenmap").findOne(tokenquery);
		String email = tokenmap.getString("email");
    
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
