package ebaytool.apicall;

import com.mongodb.*;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class SetNotificationPreferences extends ApiCall implements Callable {
	
	private String email;
	private String userid;
	
	public SetNotificationPreferences() throws Exception {
	}
	
	public SetNotificationPreferences(String email, String userid) throws Exception {
		this.email  = email;
		this.userid = userid;
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
		
		/* SetNotificationPreferences */
		ArrayList<BasicDBObject> ane = new ArrayList<BasicDBObject>();
		
		String events[] = {"ItemListed",
						   "EndOfAuction",
						   "ItemClosed",
						   "ItemExtended",
						   "ItemRevised",
						   "ItemSold",
						   "ItemUnsold"};
		for (String event : events) {
			BasicDBObject ne = new BasicDBObject();
			ne.put("EventType", event);
			ne.put("EventEnable", "Enable");
			ane.add(ne);
		}
		
		BasicDBObject adp = new BasicDBObject();
		adp.put("ApplicationEnable", "Enable");
		adp.put("ApplicationURL", "http://ebaytool.jp/page/receivenotify");
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("ApplicationDeliveryPreferences", adp);
		dbobject.put("UserDeliveryPreferenceArray", new BasicDBObject("NotificationEnable", ane));
		dbobject.put("MessageID", email+" "+userid);
		
		JSONObject jso = JSONObject.fromObject(dbobject.toString());
		jso.getJSONObject("UserDeliveryPreferenceArray")
			.getJSONArray("NotificationEnable").setExpandElements(true);
		
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("SetNotificationPreferencesRequest");
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String requestxml = xmls.write(jso);
		
		Future<String> future =
			pool18.submit(new ApiCallTask(0, requestxml, "SetNotificationPreferences"));
		future.get();
		
		writelog("SNP.req."+email+"."+userid+".xml", requestxml);
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		writelog("SNP.res.xml", responsexml);
		
		return "";
	}
}
