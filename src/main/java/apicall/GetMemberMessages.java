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

public class GetMemberMessages extends ApiCall implements Callable {
	
	private String email;
	private String userid;
	private String itemid;
	private String start;
	private String end;
    
	public GetMemberMessages() throws Exception {
	}
	
	public GetMemberMessages(String[] args) throws Exception {
		email  = args[0];
		userid = args[1];
		if (args.length == 3) {
			itemid = args[2];
		} else if (args.length == 4) {
			itemid = null;
			start = args[2];
			end   = args[3];
		}
	}
	
	public String call() throws Exception {
    
		String token = gettoken(email, userid);
    
    BasicDBObject reqdbo = new BasicDBObject();
    reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
    reqdbo.put("WarningLevel", "High");
    reqdbo.put("DetailLevel", "ReturnAll");
		reqdbo.put("MailMessageType", "All");
		reqdbo.put("MessageID", email+" "+userid+" "+itemid);
		if (itemid != null) {
			reqdbo.put("ItemID", itemid);
		} else {
			reqdbo.put("StartCreationTime", start);
			reqdbo.put("EndCreationTime", end);
		}
		
    String requestxml = convertDBObject2XML(reqdbo, "GetMemberMessages");
		
    Future<String> future =
      pool18.submit(new ApiCallTask(userid, 0, requestxml, "GetMemberMessages"));
		future.get();
    
    // todo: next page (pagination)
    
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_S");
		Date now = new Date();
		writelog("GetMemberMessages/res"+sdf.format(now).toString()+".xml", responsexml);
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];
		if (messages.length == 3) {
			itemid = messages[2];
		}
		
		writelog("GetMemberMessages/"+email+"."+userid+"."+itemid+".xml", responsexml);
		
		BasicDBObject pr = (BasicDBObject) resdbo.get("PaginationResult");
		int total = Integer.parseInt(pr.getString("TotalNumberOfEntries"));
		if (total == 0) {
			return "0 MemberMessages";
		}
		
    BasicDBObject mm = (BasicDBObject) resdbo.get("MemberMessage");
    String messageclass = mm.get("MemberMessageExchange").getClass().toString();
    BasicDBList membermessages = new BasicDBList();
    if (messageclass.equals("class com.mongodb.BasicDBObject")) {
      membermessages.add((BasicDBObject) mm.get("MemberMessageExchange"));
    } else if (messageclass.equals("class com.mongodb.BasicDBList")) {
      membermessages = (BasicDBList) mm.get("MemberMessageExchange");
    }
		for (Object tmpmsg : membermessages) {
			BasicDBObject mme = (BasicDBObject) tmpmsg;
			
			BasicDBObject item = (BasicDBObject) mme.get("Item");
			BasicDBObject seller = (BasicDBObject) item.get("Seller");
			BasicDBObject question = (BasicDBObject) mme.get("Question");

			if (!question.getString("MessageType").equals("AskSellerQuestion")) {
				continue;
			}
			
			String itemid = item.getString("ItemID");
			String userid = seller.getString("UserID");
			String messageid = question.getString("MessageID");
			
			/* get collection name for the users */
			BasicDBObject userquery = new BasicDBObject();
			userquery.put("userids2.username", userid);
			BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
			
			/* GetItem */
			String[] args = {userdbo.getString("email"), userid, itemid, "waitcallback"};
			GetItem getitem = new GetItem(args);
			getitem.call();
			
			DBCollection itemcoll = db.getCollection("items."+userdbo.getString("_id"));
			
			itemcoll.update(new BasicDBObject("org.ItemID", itemid),
                      new BasicDBObject("$set", new BasicDBObject
                                        ("membermessages."+messageid, mme)),
                      false, false);
		}
		
		return "";
	}
    
}
