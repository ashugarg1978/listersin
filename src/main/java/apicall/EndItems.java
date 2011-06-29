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

public class EndItems extends ApiCall {
	
	private String email;
	private String userid;
	private String site;
	private String chunkidx;
	private String requestxml;
	
	public EndItems() throws Exception {
	}
	
	public EndItems(String email) throws Exception {
		this.email = email;
	}
	
	public String call() throws Exception {
		
		String userid;
		String site;
		HashMap<String,String> tokenmap = getUserIdToken(email);
		
		BasicDBObject query = new BasicDBObject();
		query.put("ext.deleted", new BasicDBObject("$exists", 0));
		query.put("ext.status", "end");
		query.put("ItemID", new BasicDBObject("$exists", 1));
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("ext.status", "ending"));
		
		DBCollection coll = db.getCollection("items");
		WriteResult result = coll.update(query, update, false, true);
		
		query.put("ext.status", "ending");
		
		LinkedHashMap<String,LinkedHashMap> lhm = new LinkedHashMap<String,LinkedHashMap>();
		DBCursor cur = coll.find(query);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			
			userid = ((BasicDBObject) item.get("ext")).get("UserID").toString();
			site   = item.get("Site").toString();
			
			if (!lhm.containsKey(userid)) {
				lhm.put(userid, new LinkedHashMap<String,LinkedHashMap>());
			}
			
			if (!lhm.get(userid).containsKey(site)) {
				((LinkedHashMap) lhm.get(userid)).put(site, new LinkedHashMap<Integer,ArrayList>());
				((LinkedHashMap) lhm.get(userid).get(site)).put(0, new ArrayList<DBObject>());
			}
			
			int curidx = ((LinkedHashMap) lhm.get(userid).get(site)).size();
			int size = ((List) ((LinkedHashMap) lhm.get(userid).get(site)).get(curidx-1)).size();
			if (size >= 10) {
				((LinkedHashMap) lhm.get(userid).get(site)).put(curidx,
																new ArrayList<DBObject>());
				curidx = ((LinkedHashMap) lhm.get(userid).get(site)).size();
			}
			
			// add item data to each userid.site.chunk array.
			((List) ((LinkedHashMap) lhm.get(userid).get(site)).get(curidx-1)).add(item);
		}		
		
		// each userid
		for (String tmpuserid : lhm.keySet()) {
			LinkedHashMap lhmuserid = lhm.get(tmpuserid);
			
			// each site
			for (Object tmpsite : lhmuserid.keySet()) {
				LinkedHashMap lhmsite = (LinkedHashMap) lhmuserid.get(tmpsite);
				
				// each chunk
				for (Object tmpchunk : lhmsite.keySet()) {
					List litems = (List) lhmsite.get(tmpchunk);
					
					BasicDBObject requestdbo = new BasicDBObject();
					requestdbo.append("WarningLevel", "High");
					requestdbo.append("RequesterCredentials",
									  new BasicDBObject("eBayAuthToken", tokenmap.get(tmpuserid)));
					
					int messageid = 0;
					List<DBObject> ldbo = new ArrayList<DBObject>();
					for (Object tmpidx : litems) {
						
						String id     = ((BasicDBObject) tmpidx).get("_id").toString();
						String itemid = ((BasicDBObject) tmpidx).get("ItemID").toString();
						
						log(tmpuserid
							+" "+tmpsite
							+" "+tmpchunk+"."+messageid
							+":"+itemid);
						
						ldbo.add(new BasicDBObject("MessageID", id)
								 .append("ItemID", itemid)
								 .append("EndingReason", "NotAvailable"));
					}
					
					requestdbo.append("EndItemRequestContainer", ldbo);
					
					JSONObject jso = JSONObject.fromObject(requestdbo.toString());
					jso.getJSONArray("EndItemRequestContainer").setExpandElements(true);
					
					XMLSerializer xmls = new XMLSerializer();
					xmls.setObjectName("EndItemsRequest");
					xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
					xmls.setTypeHintsEnabled(false);
					String requestxml = xmls.write(jso);
					
					writelog("EIs"
							 +"."+((String) tmpuserid)
							 +"."+((String) tmpsite)
							 +"."+new Integer(Integer.parseInt(tmpchunk.toString())).toString()
							 +".xml", requestxml);
					
					pool18.submit(new ApiCallTask(0, requestxml, "EndItems"));
				}
			}
		}
		
		return "OK";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		log(responsedbo.get("Ack").toString());
		
		DBCollection coll = db.getCollection("items");
		
		BasicDBList dbl = (BasicDBList) responsedbo.get("EndItemResponseContainer");
		for (Object item : dbl) {
			
			String id      = ((BasicDBObject) item).getString("CorrelationID");
			String endtime = ((BasicDBObject) item).getString("EndTime");
			
			BasicDBObject upditem = new BasicDBObject();
			upditem.put("ListingDetails.EndTime", endtime);
			upditem.put("ext.status", "");
			
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(id));
			
			BasicDBObject update = new BasicDBObject();
			update.put("$set", upditem);
			
			WriteResult result = coll.update(query, update);
		}
		
		return "";
	}
}
