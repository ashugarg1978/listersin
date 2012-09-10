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
	private String waitcallback;
	
	public GetItem() throws Exception {
	}
	
	public GetItem(String[] args) throws Exception {
		this.email  = args[0];
		this.userid = args[1];
		this.itemid = args[2];
		if (args.length == 4) {
			this.waitcallback = args[3];
		}
	}
    
	public String call() throws Exception {
		
		String token = gettoken(email, userid);
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		reqdbo.append("WarningLevel",                 "High");
		reqdbo.append("DetailLevel",             "ReturnAll");
		reqdbo.append("IncludeCrossPromotion",        "true");
		reqdbo.append("IncludeItemCompatibilityList", "true");
		reqdbo.append("IncludeItemSpecifics",         "true");
		reqdbo.append("IncludeTaxTable",              "true");
		reqdbo.append("IncludeWatchCount",            "true");
		reqdbo.append("ItemID",                       itemid);
		
		String requestxml = convertDBObject2XML(reqdbo, "GetItem");
        
		Future<String> future = pool18.submit(new ApiCallTask(userid, 0, requestxml, "GetItem"));
		
		if (this.waitcallback == "waitcallback") {
			future.get();
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		BasicDBObject org = (BasicDBObject) resdbo.get("Item");
		BasicDBObject mod = (BasicDBObject) org.copy();
		
		String userid = ((BasicDBObject) org.get("Seller")).getString("UserID");
		String itemid = org.getString("ItemID");
		String timestamp = resdbo.getString("Timestamp").replaceAll("\\.", "_");
		
		writelog("GetItem/"+userid+"."+itemid+".xml", responsexml);
		
		/* get collection name for each users */
		BasicDBObject userquery = new BasicDBObject();
		userquery.put("userids."+userid, new BasicDBObject("$exists", true));
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
        
        String email = userdbo.getString("email");
		String token = gettoken(email, userid);
        
		DBCollection itemcoll = db.getCollection("items."+userdbo.getString("_id"));
		
		/* delete ItemSpecifics added from Product */
		if (mod.containsField("ItemSpecifics")) {
			BasicDBObject itemspecifics = (BasicDBObject) mod.get("ItemSpecifics");
			BasicDBObject iscopy = (BasicDBObject) itemspecifics.copy();
			
			String classname = iscopy.get("NameValueList").getClass().toString();
			BasicDBList namevaluelist = new BasicDBList();
			if (classname.equals("class com.mongodb.BasicDBObject")) {
				namevaluelist.add((BasicDBObject) iscopy.get("NameValueList"));
			} else if (classname.equals("class com.mongodb.BasicDBList")) {
				namevaluelist = (BasicDBList) iscopy.get("NameValueList");
			} else {
				log("Class Error:"+classname);
			}
			
			for (int i=namevaluelist.size()-1; i>=0; i--) {
				BasicDBObject namevalue = (BasicDBObject) namevaluelist.get(i);
				if (namevalue.containsField("Source")) {
					if (namevalue.getString("Source").equals("Product")) {
						((BasicDBList) itemspecifics.get("NameValueList")).remove(i);
					}
				}
			}
		}
		
		/* delete fields which is not necessary in AddItem families */
		BasicDBList movefields = (BasicDBList) configdbo.get("removefield");
		for (Object fieldname : movefields) {
			movefield(mod, fieldname.toString());
		}
		
		BasicDBObject query = new BasicDBObject();
		query.put("org.Seller.UserID", userid);
		query.put("org.ItemID",        itemid);
		
		BasicDBObject update = new BasicDBObject();
		
		BasicDBObject set = new BasicDBObject();
		set.put("org", org);
		set.put("mod", mod);
		set.put("UserID", userid);
		
		update.put("$set",  set);
		update.put("$push", new BasicDBObject
				   ("log", new BasicDBObject(timestamp, "Import from eBay")));
		
		itemcoll.update(query, update, true, false);
		
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
