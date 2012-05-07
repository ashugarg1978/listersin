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

public class GetItem extends ApiCall implements Callable {
	
	private String email;
	private String userid;
	private String itemid;
	
	public GetItem() throws Exception {
	}
	
	public GetItem(String email, String userid) throws Exception {
		this.email  = email;
		this.userid = userid;
	}
	
	public GetItem(String email, String userid, String itemid) throws Exception {
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
		
		/* GetItem */
		query = new BasicDBObject();
		query.put("ItemID",           new BasicDBObject("$exists", 1));
		query.put("deleted",      new BasicDBObject("$exists", 0));
		query.put("importstatus", "waiting GetItem");
		query.put("UserID",       userid);
		if (itemid != null) {
			query.put("ItemID",       itemid);
		}
		
		BasicDBObject field = new BasicDBObject();
		field.put("ItemID", 1);
		
		DBCursor cur = db.getCollection("items").find(query, field);
		Integer cnt = cur.count();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String itemid  = row.get("ItemID").toString();
			log("GetItem "+userid+" "+itemid);
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
			reqdbo.append("WarningLevel",                 "High");
			reqdbo.append("DetailLevel",             "ReturnAll");
			reqdbo.append("IncludeCrossPromotion",        "true");
			reqdbo.append("IncludeItemCompatibilityList", "true");
			reqdbo.append("IncludeItemSpecifics",         "true");
			reqdbo.append("IncludeTaxTable",              "true");
			reqdbo.append("IncludeWatchCount",            "true");
			reqdbo.append("ItemID", itemid);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetItem");
			
			pool18.submit(new ApiCallTask(0, requestxml, "GetItem"));
		}
		
		// todo: check mixing other user's items
		log("item count "+cnt);
		/*
		for (int i = 1; i <= cnt; i++) {
			log("parse response "+i);
			String responsexml = ecs18.take().get();
			callback(responsexml);
		}
		*/
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		BasicDBObject org = (BasicDBObject) responsedbo.get("Item");
		BasicDBObject mod = (BasicDBObject) org.copy();
		
		String callbackuserid = ((BasicDBObject) org.get("Seller")).getString("UserID");
		String callbackitemid = org.getString("ItemID");
		String timestamp = responsedbo.getString("Timestamp").replaceAll("\\.", "_");
		
		writelog("GetItem/"+callbackuserid+"."+callbackitemid+".xml", responsexml);
		
		/* get collection name for each users */
		BasicDBObject userquery = new BasicDBObject();
		userquery.put("userids."+callbackuserid, new BasicDBObject("$exists", true));
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
		String user_id = userdbo.getString("_id");
		
		DBCollection coll = db.getCollection("items."+user_id);
		
		/* delete fields which is not necessary in AddItem families */
		BasicDBList movefields = (BasicDBList) configdbo.get("removefield");
		for (Object fieldname : movefields) {
			movefield(mod, fieldname.toString());
		}
		
		BasicDBObject query = new BasicDBObject();
		query.put("org.Seller.UserID", callbackuserid);
		query.put("org.ItemID",        callbackitemid);
		
		BasicDBObject update = new BasicDBObject();
		
		BasicDBObject set = new BasicDBObject();
		set.append("org", org);
		
		BasicDBObject exists = (BasicDBObject) coll.findOne(query);
		if (exists == null) {
			set.append("mod", mod);
			
			update.append("$push", new BasicDBObject
						  ("log", new BasicDBObject(timestamp, "GetItem initial import")));
		} else {
			
			update.append("$push", new BasicDBObject
						  ("log", new BasicDBObject(timestamp, "GetItem update")));
		}
		
		update.append("$set",  set);
		
		coll.update(query, update, true, false);
		
		return "";
	}
	
	/**
	 *
	 * ref: https://jira.mongodb.org/browse/JAVA-260
	 */
	private void movefield(DBObject dbo, String field) throws Exception {
		
		String[] path = field.split("\\.", 2);
		
		if (!dbo.containsField(path[0])) return;
		
		String classname = dbo.get(path[0]).getClass().toString();
		
		/* leaf */
		if (path.length == 1) {
			dbo.removeField(path[0]);
			return;
		}
		
		/* not leaf */
		if (classname.equals("class com.mongodb.BasicDBList")) {
			BasicDBList orgdbl = (BasicDBList) dbo.get(path[0]);
			for (int i = 0; i < orgdbl.size(); i++) {
				movefield((DBObject) orgdbl.get(i), path[1]);
			}
		} else if (classname.equals("class com.mongodb.BasicDBObject")) {
			movefield((DBObject) dbo.get(path[0]), path[1]);
		}
		
		return;
	}
}
