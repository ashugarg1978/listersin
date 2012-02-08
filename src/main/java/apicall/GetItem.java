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
		query.put("ext.deleted",      new BasicDBObject("$exists", 0));
		query.put("ext.importstatus", "waiting GetItem");
		query.put("ext.UserID",       userid);
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
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel", "ReturnAll");
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
		BasicDBObject item = new BasicDBObject();
		BasicDBObject org = (BasicDBObject) responsedbo.get("Item");
		BasicDBObject mod = (BasicDBObject) org.copy();
		
		String callbackuserid = ((BasicDBObject) org.get("Seller")).getString("UserID");
		String callbackitemid = org.getString("ItemID");
		
		writelog("GetItem/"+callbackuserid+"."+callbackitemid+".xml", responsexml);
		
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = new BasicDBObject();
		query.put("org.Seller.UserID", callbackuserid);
		query.put("org.ItemID", callbackitemid);
		
		/* add extended information */
		BasicDBObject ext = new BasicDBObject();
		//item.put("UserID", callbackuserid);
		item.put("labels", new BasicDBList());
		item.put("org", org);
		item.put("mod", mod);
		
		// todo: copy from GetSellerList code.
		/* move some fields which is not necessary in AddItem families */
		String[] movefields =
			{"ItemSpecifics.NameValueList.Source",
			 "SellingStatus",
			 "TimeLeft",
			 "BuyerProtection",
			 "BuyerGuaranteePrice",
			 "PaymentAllowedSite",
			 "PrimaryCategory.CategoryName",
			 "Seller.AboutMePage",
			 "Seller.Email",
			 "Seller.FeedbackPrivate",
			 "Seller.FeedbackRatingStar",
			 "Seller.FeedbackScore",
			 "Seller.IDVerified",
			 "Seller.NewUser",
			 "Seller.PositiveFeedbackPercent",
			 "Seller.RegistrationDate",
			 "Seller.SellerInfo",
			 "Seller.Site",
			 "Seller.Status",
			 "Seller.UserID",
			 "Seller.UserIDChanged",
			 "Seller.UserIDLastChanged",
			 "Seller.VATStatus",
			 "Seller.eBayGoodStanding",
			 "ShippingDetails.ShippingServiceOptions.ShippingTimeMax",
			 "ShippingDetails.ShippingServiceOptions.ShippingTimeMin",
			 "WatchCount"};
		for (String fieldname : movefields) {
			movefield(mod, ext, fieldname);
		}
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", item);
		
		coll.update(query, update, true, true);
		
		return "";
	}
	
	/**
	 *
	 * ref: https://jira.mongodb.org/browse/JAVA-260
	 */
	private void movefield(DBObject dbo, DBObject ext, String field) throws Exception {
		
		String[] path = field.split("\\.", 2);
		
		if (!dbo.containsField(path[0])) {
			log(path[0]+" : NOT EXISTS.");
			return;
		}
		
		String classname = dbo.get(path[0]).getClass().toString();
		
		/* leaf */
		if (path.length == 1) {
			ext.put(path[0], dbo.get(path[0]));
			dbo.removeField(path[0]);
			//log(path[0]+" : (leaf)");
			return;
		}
		
		/* not leaf */
		//log(path[0]+" : "+classname+" ("+path[1]+")");
		if (classname.equals("class com.mongodb.BasicDBList")) {
			
			if (!ext.containsField(path[0])) {
				ext.put(path[0], new BasicDBList());
			}
			
			BasicDBList orgdbl = (BasicDBList) dbo.get(path[0]);
			BasicDBList extdbl = (BasicDBList) ext.get(path[0]);
			
			for (int i = 0; i < orgdbl.size(); i++) {
				if (extdbl.size() < (i+1)) {
					extdbl.add(new BasicDBObject());
				}
				
				movefield((DBObject) orgdbl.get(i),
						  (DBObject) extdbl.get(i),
						  path[1]);
			}
			
		} else if (classname.equals("class com.mongodb.BasicDBObject")) {
			
			//log("ext:"+ext.toString());
			//log(((DBObject) dbo.get(path[0])).toString());
			
			if (!ext.containsField(path[0])) {
				ext.put(path[0], new BasicDBObject());
			}
			
			movefield((DBObject) dbo.get(path[0]),
					  (DBObject) ext.get(path[0]),
					  path[1]);
			
		} else {
			
			log("movefield ERROR "+classname);
			
		}
		
		return;
	}
}
