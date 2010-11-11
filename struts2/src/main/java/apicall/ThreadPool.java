package ebaytool.apicall;

import java.util.Map;
import java.util.LinkedHashMap;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.concurrent.*;
import java.util.*;

import ebaytool.apicall.AddItems;
import ebaytool.apicall.GeteBayDetails;
import ebaytool.apicall.GetSellerList;
import ebaytool.apicall.GetCategories;
import ebaytool.apicall.GetCategoryFeatures;

public class ThreadPool {
	
	private ExecutorService pool;
	private Mongo m;
	private DB db;
	
	private String token = "AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB";
	
	public ThreadPool() throws Exception {
		m = new Mongo();
		db = m.getDB("ebay");
		pool = Executors.newFixedThreadPool(18);
	}
	
    public static void main(String[] args) throws Exception {
		
		String action = args[0];
		
		ThreadPool threadpool = new ThreadPool();
		
		if (action.equals("getCategories")) {
			
			threadpool.getCategories();
			
		} if (action.equals("getCategoryFeatures")) {
			
			threadpool.getCategoryFeatures();
			
		} else if (action.equals("geteBayDetails")) {
			
			threadpool.geteBayDetails();
			
		} else if (action.equals("getSellerLists")) {
			
			threadpool.getSellerLists();
			
		} else if (action.equals("addItems")) {
			
			threadpool.addItems();
			
		} else {
			
			System.out.println("no action defined. stop.");
			
		}
		
		threadpool.shutdown();
		
		return;
    }
	
	private void shutdown() {
		
		pool.shutdown();
		
		return;
	}
	
	private void getCategories() throws Exception {
		
		DBCollection coll = db.getCollection("SiteDetails");
		
		//DBCursor cur = coll.find(new BasicDBObject("Site", "US"));
		DBCursor cur = coll.find();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			System.out.println(site+"("+siteid+")");
			
			pool.submit(new GetCategories(siteid, site));
			
			Thread.sleep(500);
		}
		
		return;
	}
	
	private void getCategoryFeatures() throws Exception {
		
		DBCursor cur = db.getCollection("SiteDetails").find();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			
			pool.submit(new GetCategoryFeatures(siteid, site));
			
			Thread.sleep(500);
		}
		
		return;
	}
	
	private void geteBayDetails() throws Exception {
		
		pool.submit(new GeteBayDetails());
		
		return;
	}
	
	private void addItems() throws Exception {
		
		String userid;
		String site;
		HashMap<String,String> tokenmap = getUserIdToken();
		
		BasicDBObject query = new BasicDBObject();
		//query.put("SellingStatus.ListingStatus", "Active");
		//query.put("UserID", "testuser_hal");
		//query.put("Title", "image test");
		
		DBCollection coll = db.getCollection("items");
		
		LinkedHashMap<String,LinkedHashMap> lhm = new LinkedHashMap<String,LinkedHashMap>();
		DBCursor cur = coll.find(query).limit(10);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			
			/* todo: remove more fields */
			item.put("ConditionID", 1000);
			item.removeField("_id");
			((BasicDBObject) item.get("ShippingDetails")).removeField("SalesTax");
			
			userid = item.get("UserID").toString();
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
			if (size >= 5) {
				((LinkedHashMap) lhm.get(userid).get(site)).put(curidx,
																new ArrayList<DBObject>());
				curidx = ((LinkedHashMap) lhm.get(userid).get(site)).size();
			}
			((List) ((LinkedHashMap) lhm.get(userid).get(site)).get(curidx-1)).add(item);
		}		
		
		for (String tmpuserid : lhm.keySet()) {
			LinkedHashMap lhmuserid = lhm.get(tmpuserid);
			for (Object tmpsite : lhmuserid.keySet()) {
				LinkedHashMap lhmsite = (LinkedHashMap) lhmuserid.get(tmpsite);
				for (Object tmpchunk : lhmsite.keySet()) {
					List litems = (List) lhmsite.get(tmpchunk);
					
					BasicDBObject requestdbo = new BasicDBObject();
					requestdbo.append("WarningLevel", "High");
					requestdbo.append("RequesterCredentials",
									  new BasicDBObject("eBayAuthToken", tokenmap.get(tmpuserid)));
					
					int messageid = 0;
					List<DBObject> ldbo = new ArrayList<DBObject>();
					for (Object tmpidx : litems) {
						messageid++;
						ldbo.add(new BasicDBObject("MessageID", messageid).append("Item", tmpidx));
						
						String title = ((BasicDBObject) tmpidx).get("Title").toString();
						//System.out.println(tmpuserid+" "+tmpsite+" "+tmpchunk+" "+title);
					}
					
					requestdbo.append("AddItemRequestContainer", ldbo);
					
					JSONObject jso = JSONObject.fromObject(requestdbo.toString());
					JSONArray tmpitems = jso.getJSONArray("AddItemRequestContainer");
					for (Object tmpitem : tmpitems) {
						JSONObject tmpi = ((JSONObject) tmpitem).getJSONObject("Item");
						
						/* expand array elements */
						if (tmpi.has("PaymentAllowedSite") && tmpi.get("PaymentAllowedSite")
							.getClass().toString().equals("class net.sf.json.JSONArray")) {
							tmpi.getJSONArray("PaymentAllowedSite").setExpandElements(true);
						}
						
						if (tmpi.has("PaymentMethods") && tmpi.get("PaymentMethods")
							.getClass().toString().equals("class net.sf.json.JSONArray")) {
							tmpi.getJSONArray("PaymentMethods").setExpandElements(true);
						}
						
						if (((JSONObject) tmpi.get("PictureDetails")).has("PictureURL")
							&& ((JSONObject) tmpi.get("PictureDetails")).get("PictureURL")
							.getClass().toString().equals("class net.sf.json.JSONArray")) {
							((JSONObject) tmpi.get("PictureDetails"))
								.getJSONArray("PictureURL").setExpandElements(true);
						}
					}			
					jso.getJSONArray("AddItemRequestContainer").setExpandElements(true);
					
					XMLSerializer xmls = new XMLSerializer();
					xmls.setObjectName("AddItemsRequest");
					xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
					xmls.setTypeHintsEnabled(false);
					String requestxml = xmls.write(jso);
					
					Future<BasicDBObject> future = pool.submit
						(new AddItems((String) tmpuserid,
									  (String) tmpsite,
									  new Integer(Integer.parseInt(tmpchunk.toString())).toString(),
									  requestxml));
				}
			}
		}
		
		return;
	}
	
	private void getSellerLists() throws Exception {
		
		DBCollection coll = db.getCollection("users");
		DBObject user = coll.findOne();
		
		Map userids = ((BasicDBObject) user.get("userids")).toMap();
		for (Object userid : userids.keySet()) {
			JSONObject json = JSONObject.fromObject(userids.get(userid).toString());
			String token = json.get("ebaytkn").toString();
			getSellerList(userid.toString(), token);
		}
		
		return;
	}
	
	private void getSellerList(String userid, String token) throws Exception {
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("StartTimeFrom", "2010-06-01 00:00:00");
		dbobject.put("StartTimeTo",   "2010-09-01 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage", 50).append("PageNumber", 1));
		dbobject.put("Sort", "1");
		
		Future<BasicDBObject> future = pool.submit(new GetSellerList(dbobject));
		BasicDBObject result = future.get();
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		System.out.println(userid+" : total "+pages+" page(s).");
		
		for (int i=2; i<=pages; i++) {
			((BasicDBObject) dbobject.get("Pagination")).put("PageNumber", i);
			pool.submit(new GetSellerList(dbobject));
		}
		
		return;
	}
	
	private HashMap<String,String> getUserIdToken() throws Exception {
		
		HashMap<String,String> hm = new HashMap<String,String>();
		
		DBCursor cur = db.getCollection("users").find();
		while (cur.hasNext()) {
			DBObject user = cur.next();
			
			for (Object userid : ((BasicDBObject) user.get("userids")).keySet()) {
				String ebaytkn = 
					((BasicDBObject) ((BasicDBObject) user.get("userids")).get(userid.toString())).get("ebaytkn").toString();
				
				hm.put(userid.toString(), ebaytkn);
			}
			
		}
		
		return hm;
	}
}
