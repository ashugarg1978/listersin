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
    
    String token = gettoken(email, userid);
    
		BasicDBObject mm = new BasicDBObject();
		mm.put("QuestionType", "General");
		mm.put("RecipientID",  seller);
		mm.put("Subject",      "aaqtest");
		mm.put("Body",         body);
    
    BasicDBObject reqdbo = new BasicDBObject();
    reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
    reqdbo.append("WarningLevel",  "High");
    reqdbo.append("DetailLevel",   "ReturnAll");
    reqdbo.append("ItemID",        itemid);
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
