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

public class AddMemberMessageAAQToPartner extends ApiCall implements Callable {
	
	private String email;
	private String userid;
    private String itemid;
    private String seller;
    private String body;
    
	public AddMemberMessageAAQToPartner() throws Exception {
	}
	
	public AddMemberMessageAAQToPartner(String email,
                                        String userid,
                                        String itemid,
                                        String seller,
                                        String body) throws Exception {
		this.email  = email;
		this.userid = userid;
        this.itemid = itemid;
        this.seller = seller;
        this.body   = body;
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

		BasicDBObject mm = new BasicDBObject();
		mm.put("QuestionType", "General");
		mm.put("RecipientID", seller);
		mm.put("Subject", "aaqtest");
		mm.put("Body", body);
        
		reqdbo.append("MemberMessage", mm);
		
        String requestxml = convertDBObject2XML(reqdbo, "AddMemberMessageAAQToPartner");
		
        pool18.submit(new ApiCallTask(0, requestxml, "AddMemberMessageAAQToPartner"));
        
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
        
		writelog("AddMemberMessageAAQToPartner/res.xml", responsexml);
        
		return "";
	}
    
}
