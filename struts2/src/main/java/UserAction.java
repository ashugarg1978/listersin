package ebaytool.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.ParentPackage;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

@ParentPackage("json-default")
public class UserAction extends ActionSupport {
	
	private LinkedHashMap<String,Object> json;
	
	public LinkedHashMap<String,Object> getJson() {
		return json;
	}
	
	@Action(value="/items", results={@Result(name="success",type="json")})
	public String items() throws Exception {
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = new BasicDBObject();
		query.put("deleted", 0);
		query.put("UserID", "testuser_hal");
		
		json = new LinkedHashMap<String,Object>();
		
		DBCursor cur = coll.find(query).limit(40);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			String id = item.get("_id")+"";
			json.put(id, item);
		}
		
		return SUCCESS;
	}
	
	@Action(value="/summary", results={@Result(name="success",type="json")})
	public String summary() throws Exception {
		
		LinkedHashMap<String,BasicDBObject> selling = getsummaryquery();
		
		String[] userids = {"testuser_hal",
							"testuser_chiba",
							"testuser_aichi",
							"testuser_tokyo",
							"testuser_kanagawa"};
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		json = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<String,Long> allsummary = new LinkedHashMap<String,Long>();
		for (String k : selling.keySet()) {
			BasicDBObject query = new BasicDBObject();
			query = selling.get(k);
			query.put("UserID", new BasicDBObject("$in", userids));
			
			Long cnt = coll.count(query);
			allsummary.put(k, cnt);
		}
		json.put("alluserids", allsummary);
		
		for (String u : userids) {
			LinkedHashMap<String,Long> summary = new LinkedHashMap<String,Long>();
			for (String k : selling.keySet()) {
				BasicDBObject query = new BasicDBObject();
				query = selling.get(k);
				query.put("UserID", u);
				
				Long cnt = coll.count(query);
				summary.put(k, cnt);
			}
			json.put(u, summary);
		}
		
		return SUCCESS;
	}
	
	private LinkedHashMap<String,BasicDBObject> getsummaryquery() {
		
		BasicDBObject allitems  = new BasicDBObject();
		BasicDBObject scheduled = new BasicDBObject();
		BasicDBObject active    = new BasicDBObject();
		BasicDBObject sold      = new BasicDBObject();
		BasicDBObject unsold    = new BasicDBObject();
		BasicDBObject saved     = new BasicDBObject();
		BasicDBObject trash     = new BasicDBObject();
		
		allitems.put("deleted", 0);
		
		scheduled.put("deleted", 0);
		scheduled.put("ItemID", new BasicDBObject("$exists", 0));
		
		active.put("deleted", 0);
		active.put("ItemID", new BasicDBObject("$exists", 1));
		active.put("SellingStatus.ListingStatus", "Active");
		
		sold.put("deleted", 0);
		sold.put("ItemID", new BasicDBObject("$exists", 1));
		sold.put("SellingStatus.QuantitySold", new BasicDBObject("$gte", "1"));
		
		unsold.put("deleted", 0);
		unsold.put("ItemID", new BasicDBObject("$exists", 1));
		unsold.put("SellingStatus.ListingStatus", "Completed");
		unsold.put("SellingStatus.QuantitySold", "0");
		
		saved.put("deleted", 0);
		saved.put("ItemID", new BasicDBObject("$exists", 0));
		
		trash.put("deleted", 1);
		
		
		LinkedHashMap<String,BasicDBObject> selling = new LinkedHashMap<String,BasicDBObject>();
		selling.put("allitems",  allitems);
		selling.put("scheduled", scheduled);
		selling.put("active",    active);
		selling.put("sold",      sold);
		selling.put("unsold",    unsold);
		selling.put("saved",     saved);
		selling.put("trash",     trash);
		
		return selling;
	}
	
}
