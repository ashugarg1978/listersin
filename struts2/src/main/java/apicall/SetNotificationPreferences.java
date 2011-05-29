package ebaytool.apicall;

import com.mongodb.*;
import ebaytool.apicall.ApiCall;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class SetNotificationPreferences extends ApiCall implements Callable {
	
	public SetNotificationPreferences() throws Exception {
	}
	
	public String call() throws Exception {
		
		DBObject user = db.getCollection("users").findOne();
		
		Map userids = ((BasicDBObject) user.get("userids")).toMap();
		for (Object userid : userids.keySet()) {
			JSONObject json = JSONObject.fromObject(userids.get(userid).toString());
			String token = json.get("eBayAuthToken").toString();
			call2(userid.toString(), token);
		}
		
		return "";
	}
	
	private void call2(String userid, String token) throws Exception {
		
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
		adp.put("ApplicationURL", "http://ebaytool.jp/receivenotify");
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("ApplicationDeliveryPreferences", adp);
		dbobject.put("UserDeliveryPreferenceArray", new BasicDBObject("NotificationEnable", ane));
		
		JSONObject jso = JSONObject.fromObject(dbobject.toString());
		jso.getJSONObject("UserDeliveryPreferenceArray")
			.getJSONArray("NotificationEnable").setExpandElements(true);
		
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("SetNotificationPreferencesRequest");
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String requestxml = xmls.write(jso);
		writelog("SNP.req."+userid+".xml", requestxml);
		
		Future<String> future =
			ecs18.submit(new ApiCallTask(0, requestxml, "SetNotificationPreferences"));
		
		String responsexml = future.get();
		writelog("SNP.res."+userid+".xml", responsexml);
		
		return;
	}
}
