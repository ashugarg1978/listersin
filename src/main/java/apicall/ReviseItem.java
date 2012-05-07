package ebaytool.apicall;

import com.mongodb.*;
import com.mongodb.util.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import javax.xml.XMLConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.bson.types.ObjectId;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class ReviseItem extends ApiCall {
	
	private String email;
	private String taskid;
	
	public ReviseItem() throws Exception {
	}
	
	public ReviseItem(String email, String taskid) throws Exception {
		this.email  = email;
		this.taskid = taskid;
	}
	
	public String call() throws Exception {
		
		String userid;
		String site;
		HashMap<String,String> tokenmap = getUserIdToken(email);
		
		BasicDBObject userdbo =
			(BasicDBObject) db.getCollection("users").findOne(new BasicDBObject("email", email));
		String user_id = userdbo.getString("_id");
		String uuidprefix = user_id.substring(user_id.length()-8);
		
		/* set intermediate status */
		BasicDBObject query = new BasicDBObject();
		query.put("deleted",    new BasicDBObject("$exists", 0));
		query.put("org.ItemID", new BasicDBObject("$exists", 1));
		query.put("status",     taskid);
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("status", taskid+"_processing"));
		
		DBCollection coll = db.getCollection("items."+userdbo.getString("_id"));
		WriteResult result = coll.update(query, update, false, true);
		
		/* re-query */
		query.put("status", taskid+"_processing");
		DBCursor cur = coll.find(query);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			DBObject mod = (DBObject) item.get("mod");
			DBObject org = (DBObject) item.get("org");
			
			String uuid = uuidprefix + item.get("_id").toString();
			uuid = uuid.toUpperCase();
			mod.put("UUID", uuid);
			
			userid = ((BasicDBObject) org.get("Seller")).get("UserID").toString();
			site   = mod.get("Site").toString();
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("ErrorLanguage", "en_US");
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("RequesterCredentials",
						  new BasicDBObject("eBayAuthToken", tokenmap.get(userid)));
			reqdbo.append("MessageID", item.get("_id").toString());
			reqdbo.append("Item", new BasicDBObject("ItemID", org.get("ItemID").toString()));
			
			String requestxml = convertDBObject2XML(reqdbo, "ReviseItem");
			pool18.submit(new ApiCallTask(getSiteID(site), requestxml, "ReviseItem"));
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		// todo: almost same as AddItems callback function.
		String id        = responsedbo.getString("CorrelationID");
		String itemid    = responsedbo.getString("ItemID");
		String starttime = responsedbo.getString("StartTime");
		String endtime   = responsedbo.getString("EndTime");
		
		writelog("ReviseItem/res."+itemid+".xml", responsexml);
		
		log("Ack:"+responsedbo.get("Ack").toString());
		
		BasicDBObject upditem = new BasicDBObject();
		upditem.put("status", "");
		if (itemid != null) {
			upditem.put("org.ItemID", itemid);
			upditem.put("org.ListingDetails.StartTime", starttime);
			upditem.put("org.ListingDetails.EndTime", endtime);
			upditem.put("org.SellingStatus.ListingStatus", "Active");
		}
		
		// todo: aware <SeverityCode>Warning</SeverityCode>
		if (responsedbo.get("Errors") != null) {
			String errorclass = responsedbo.get("Errors").getClass().toString();
			BasicDBList errors = new BasicDBList();
			if (errorclass.equals("class com.mongodb.BasicDBObject")) {
				errors.add((BasicDBObject) responsedbo.get("Errors"));
			} else if (errorclass.equals("class com.mongodb.BasicDBList")) {
				errors = (BasicDBList) responsedbo.get("Errors");
			} else {
				log("Class Error:"+errorclass);
			}
			upditem.put("ext.errors", errors);
		}
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", upditem);
		
		DBCollection coll = db.getCollection("items");
		WriteResult result = coll.update(query, update);
		
		return "";
	}
	
	// todo: move to super class?
	private int getSiteID(String site) throws Exception {

		Integer siteid = null;
		
		DBObject row = db.getCollection("US.eBayDetails")
			.findOne(null, new BasicDBObject("SiteDetails", 1));
		BasicDBList sitedetails = (BasicDBList) row.get("SiteDetails");
		for (Object sitedbo : sitedetails) {
			if (site.equals(((BasicDBObject) sitedbo).getString("Site"))) {
				siteid = Integer.parseInt(((BasicDBObject) sitedbo).getString("SiteID"));
				break;
			}
		}
		
		return siteid;
	}
}
