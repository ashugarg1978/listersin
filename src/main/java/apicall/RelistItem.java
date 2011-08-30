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

public class RelistItem extends ApiCall {
	
	private String email;
	
	public RelistItem() throws Exception {
	}
	
	public RelistItem(String email) throws Exception {
		this.email = email;
	}
	
	public String call() throws Exception {
		
		String userid;
		String site;
		HashMap<String,String> tokenmap = getUserIdToken(email);
		
		/* set intermediate status */
		BasicDBObject query = new BasicDBObject();
		query.put("ext.deleted", new BasicDBObject("$exists", 0));
		query.put("ext.status", "relist");
		query.put("ItemID", new BasicDBObject("$exists", 1));
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("ext.status", "relisting"));
		
		DBCollection coll = db.getCollection("items");
		WriteResult result = coll.update(query, update, false, true);
		
		/* re-query */
		query.put("ext.status", "relisting");
		DBCursor cur = coll.find(query);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			
			userid = ((BasicDBObject) item.get("ext")).get("UserID").toString();
			site   = item.get("Site").toString();
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("ErrorLanguage", "en_US");
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("RequesterCredentials",
						  new BasicDBObject("eBayAuthToken", tokenmap.get(userid)));
			reqdbo.append("MessageID", item.get("_id").toString());
			reqdbo.append("Item", new BasicDBObject("ItemID", item.get("ItemID").toString()));
			
			String requestxml = convertDBObject2XML(reqdbo, "RelistItem");
			writelog("RelistItem/"+userid+"."+item.get("_id").toString()+".xml", requestxml);
			
			pool18.submit(new ApiCallTask(getSiteID(site), requestxml, "RelistItem"));
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String itemid = resdbo.getString("ItemID");
		
		writelog("RelistItem/res."+itemid+".xml", responsexml);
		
		return "";
	}
	
	// todo: move to super class?
	private int getSiteID(String site) throws Exception {

		DBCollection coll = db.getCollection(site+".eBayDetails.SiteDetails");
		
		BasicDBObject query = new BasicDBObject();
		query.put("Site", site);
		
		BasicDBObject row = (BasicDBObject) coll.findOne(query);
		
		int result = Integer.parseInt(row.get("SiteID").toString());
		
		return result;
	}
}
