package ebaytool;

import java.util.Map;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.concurrent.*;
import java.util.*;
import ebaytool.GetSellerList;
import ebaytool.AddItems;

public class ThreadPool {
	
	private ExecutorService pool;
	private Mongo m;
	private DB db;
	
	public ThreadPool () {
		
		try {
			 m = new Mongo();
			 db = m.getDB("ebay");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		pool = Executors.newFixedThreadPool(18);
		
	}
	
    public static void main(String[] args) throws Exception {
		
		ThreadPool threadpool = new ThreadPool();
		threadpool.getSellerLists();
		
		threadpool.shutdown();
		return;
    }
	
	private void shutdown() {
		pool.shutdown();
	}
	
	private void addItems() throws Exception {
		
		
		return;
	}
	
	private void getSellerLists() throws Exception {

		BasicDBObject query = new BasicDBObject();
		
		DBCollection coll = db.getCollection("users");
		DBObject user = coll.findOne();

		Map userids = ((BasicDBObject) user.get("userids")).toMap();
		
		//JSONObject jsonarr = json.getJSONObject("userids");
		
		for (Object userid : userids.keySet()) {
			JSONObject json = JSONObject.fromObject(userids.get(userid).toString());
			String token = json.get("ebaytkn").toString();
			getSellerList(token);
		}
		
		return;
	}
	
	private void addItems() throws Exception {
		
		
		
		return;
	}
	
	private void getSellerList(String token) throws Exception {
		
		BasicDBObject pagination = new BasicDBObject();
		pagination.put("EntriesPerPage", "50");
		pagination.put("PageNumber", "1");
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("StartTimeFrom", "2010-06-01 00:00:00");
		dbobject.put("StartTimeTo",   "2010-08-01 00:00:00");
		dbobject.put("Pagination", pagination);
		dbobject.put("Sort", "1");
		
		Future<BasicDBObject> future = pool.submit(new GetSellerList(1, dbobject));
		BasicDBObject result = future.get();
		
		//int pages = 10;
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		System.out.println("TotalNumberOfPages : "+pages);
		
		for (int i=2; i<=pages; i++) {
			BasicDBObject dbocopy = (BasicDBObject) dbobject.clone();
			((BasicDBObject) dbocopy.get("Pagination")).put("PageNumber", i);
			
			pool.submit(new GetSellerList(i, dbocopy));
			
			// todo: next thread overwrite variables of previous thread! How do I fix this?
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				
			}
		}
		
		return;
	}
	
}
