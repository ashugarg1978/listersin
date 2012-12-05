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

public class GetItemTransactions extends ApiCall implements Callable {
	
	private String email;
	private String userid;
	private String itemid;
    
	public GetItemTransactions() throws Exception {
	}
	
	public GetItemTransactions(String email, String userid, String itemid) throws Exception {
		this.email  = email;
		this.userid = userid;
		this.itemid = itemid;
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
    reqdbo.put("DetailLevel", "ReturnAll");
    reqdbo.put("ItemID", itemid);
		reqdbo.put("MessageID", email+" "+userid+" "+itemid);
    
    String requestxml = convertDBObject2XML(reqdbo, "GetItemTransactions");
		
    pool18.submit(new ApiCallTask(0, requestxml, "GetItemTransactions"));
    
    // todo: next page (pagination)
    
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
    
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
    
    BasicDBObject item   = (BasicDBObject) resdbo.get("Item");
    BasicDBObject seller = (BasicDBObject) item.get("Seller");
    
    userid = seller.getString("UserID");
    itemid = item.getString("ItemID");
    
		/* get collection name for the users */
		BasicDBObject userquery = new BasicDBObject();
		userquery.put("userids."+userid, new BasicDBObject("$exists", true));
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
    
		DBCollection itemcoll = db.getCollection("items."+userdbo.getString("_id"));
    
		writelog("GetItemTransactions/"+email+"."+userid+"."+itemid+".xml", responsexml);
    BasicDBObject ta = (BasicDBObject) resdbo.get("TransactionArray");
    
    String transclass = ta.get("Transaction").getClass().toString();
    BasicDBList trans = new BasicDBList();
    if (transclass.equals("class com.mongodb.BasicDBObject")) {
      trans.add((BasicDBObject) ta.get("Transaction"));
    } else if (transclass.equals("class com.mongodb.BasicDBList")) {
      trans = (BasicDBList) ta.get("Transaction");
    }
    
		itemcoll.update(new BasicDBObject("org.ItemID", itemid),
                    new BasicDBObject("$set", new BasicDBObject("transactions", trans)),
                    false, false);
    
		return "";
	}
  
}
