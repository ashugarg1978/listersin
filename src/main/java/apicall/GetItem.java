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
		reqdbo.append("ItemID",                         itemid);
		reqdbo.append("MessageID", getnewtokenmap(email) + " " + userid + " " + itemid);
    
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
    
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email = getemailfromtokenmap(messages[0]);
		//userid = messages[1];
		//itemid = messages[2];
		
		userid = ((BasicDBObject) org.get("Seller")).getString("UserID");
		itemid = org.getString("ItemID");
		String timestamp = resdbo.getString("Timestamp").replaceAll("\\.", "_");
		
		/* make log directory for each call */
		String savedir = basedir + "/logs/apicall/GetItem/" + timestamp.substring(0,10);
		if (!(new File(savedir)).exists()) {
			new File(savedir).mkdir();
		}
		writelog("GetItem/"+timestamp.substring(0,10)+"/"+userid+"."+itemid+".xml", responsexml);
    
		/* get collection name for each users */
		BasicDBObject userquery = new BasicDBObject();
		userquery.put("email", email);
		userquery.put("userids2.username", userid);
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
    
		String token = gettoken(email, userid);
    
    String itemcollname = "items." + userdbo.getString("_id");
		DBCollection itemcoll = db.getCollection(itemcollname);
    
    upsertitem(email, userid, itemid, org, itemcollname, timestamp);
    
		return "";
	}
  
  public void upsertitem(String email, String userid, String itemid,
                         BasicDBObject org,
                         String itemcollname,
                         String timestamp) throws Exception {
    
		BasicDBObject mod = (BasicDBObject) org.copy();
    
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
				log("Class Error:" + classname);
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
    
		BasicDBList doublefields = (BasicDBList) configdbo.get("doublefield");
		for (Object fieldname : doublefields) {
			convertfield(mod, fieldname.toString());
			convertfield(org, fieldname.toString());
		}
    
		BasicDBList intfields = (BasicDBList) configdbo.get("intfield");
		for (Object fieldname : intfields) {
			convertint(mod, fieldname.toString());
			convertint(org, fieldname.toString());
		}
    
		/* Remove banner from description */
		String description = mod.getString("Description");
		description = description.replaceAll("<div id=\"listersin-banner\".+?</div>", "");
		mod.put("Description", description);
		
		BasicDBObject query = new BasicDBObject();
		query.put("org.Seller.UserID", userid);
    query.put("org.ItemID", itemid);
    /*
    if (org.containsField("RelistParentID")) {
      query.put("org.ItemID", org.getString("RelistParentID"));
    }
    */
    
		BasicDBObject update = new BasicDBObject();
		
		BasicDBObject set = new BasicDBObject();
		set.put("org", org);
		set.put("mod", mod);
		set.put("UserID", userid);
		
		update.put("$set", set);
		update.put("$push", new BasicDBObject("log", new BasicDBObject(timestamp, "Import from eBay")));
    
		DBCollection itemcoll = db.getCollection(itemcollname);
		itemcoll.update(query, update, true, false);
    
    return;
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
      
      /* test for int cast (price) */
      /*
      log(path[0] + " cast ?");
      if (path[0].equals("#text")) {
        String value = dbo.get(path[0]).toString();
        Float floatval = Float.parseFloat(value);
        dbo.put(path[0], floatval);
        log(path[0] + ":" + value);
      }
      */
      
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
  
	private void convertfield(DBObject dbo, String field) throws Exception {
		
		String[] path = field.split("\\.", 2);
		
		if (!dbo.containsField(path[0])) return;
		
		String classname = dbo.get(path[0]).getClass().toString();
		
		/* leaf */
		if (path.length == 1) {
      Double doubleval = new Double(dbo.get(path[0]).toString());
      dbo.put(path[0], doubleval);
			return;
		}
    
		/* not leaf */
		if (classname.equals("class com.mongodb.BasicDBList")) {
			BasicDBList orgdbl = (BasicDBList) dbo.get(path[0]);
			for (int i = 0; i < orgdbl.size(); i++) {
				convertfield((DBObject) orgdbl.get(i), path[1]);
			}
		} else if (classname.equals("class com.mongodb.BasicDBObject")) {
			convertfield((DBObject) dbo.get(path[0]), path[1]);
		}
    
		return;
	}
  
	private void convertint(DBObject dbo, String field) throws Exception {
		
		String[] path = field.split("\\.", 2);
		
		if (!dbo.containsField(path[0])) return;
		
		String classname = dbo.get(path[0]).getClass().toString();
    
		/* leaf */
		if (path.length == 1) {
      Integer intval = new Integer(dbo.get(path[0]).toString());
      dbo.put(path[0], intval);
			return;
		}
    
		/* not leaf */
		if (classname.equals("class com.mongodb.BasicDBList")) {
			BasicDBList orgdbl = (BasicDBList) dbo.get(path[0]);
			for (int i = 0; i < orgdbl.size(); i++) {
				convertint((DBObject) orgdbl.get(i), path[1]);
			}
		} else if (classname.equals("class com.mongodb.BasicDBObject")) {
			convertint((DBObject) dbo.get(path[0]), path[1]);
		}
    
		return;
	}
  
}
