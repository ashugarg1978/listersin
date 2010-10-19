package ebaytool.actions;

import java.io.*;
import java.net.URL;
//import java.net.HttpURLConnection;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.net.ssl.HttpsURLConnection;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.ParentPackage;

import org.apache.log4j.Logger;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import org.bson.types.ObjectId;

@ParentPackage("json-default")
public class UserAction extends ActionSupport {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	private static Mongo m;
	private static DB db;
	
	public UserAction() throws Exception {
		//m = new Mongo();
		//db = m.getDB("ebay");
		db = new Mongo().getDB("ebay");
	}
	
	private LinkedHashMap<String,Object> json;
	
	public LinkedHashMap<String,Object> getJson() {
		return json;
	}
	
	/*
	public String execute() throws Exception {
		json.put("foo", "bar");
		return SUCCESS;
	}
	*/
	
	@Action(value="/hash", results={@Result(name="success",type="json")})
	public String hash() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		DBCollection coll = db.getCollection("SiteDetails");
		DBCursor cur = coll.find();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			
			LinkedHashMap<String,Object> hash = new LinkedHashMap<String,Object>();
			
			hash.put("SiteID", siteid.toString());

			hash.put("category", children(site, 0));
			((LinkedHashMap) hash.get("category")).put("grandchildren", new ArrayList());
			((LinkedHashMap) hash.get("category")).put("features",      new ArrayList());
			
			json.put(site, hash);
		}
		
		return SUCCESS;
	}
	
	@Action(value="/items", results={@Result(name="success",type="json")})
	public String items() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<String,BasicDBObject> sellingquery = getsellingquery();
		
		/* connect to database */
		DBCollection coll = db.getCollection("items");
		
		/* handling post parameters */
		ActionContext context = ActionContext.getContext();
		Map request = (Map) context.getParameters();
		
		int limit  = Integer.parseInt(((String[]) request.get("limit"))[0]);
		int offset = Integer.parseInt(((String[]) request.get("offset"))[0]);
		
		/* query */
		BasicDBObject query = new BasicDBObject();
		query = sellingquery.get(((String[]) request.get("selling"))[0]);
		
		BasicDBObject field = new BasicDBObject();
		field.put("UserID", 1);
		field.put("ItemID", 1);
		field.put("Title", 1);
		field.put("Site", 1);
		field.put("StartPrice", 1);
		field.put("ListingDetails.ViewItemURL", 1);
		field.put("ListingDetails.EndTime", 1);
		field.put("PictureDetails.PictureURL", 1);
		field.put("SellingStatus.ListingStatus", 1);
		field.put("SellingStatus.CurrentPrice", 1);
		//field.put("SellingStatus.CurrentPrice@currencyID", 1);
		field.put("status", 1);
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("ListingDetails.EndTime", -1);
		
        Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String today = sdf.format(calendar.getTime());
		
		DBCursor cur = coll.find(query, field).limit(limit).skip(offset).sort(sort);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			String id = item.get("_id").toString();
			
			/* price */
			DBObject ss = (DBObject) item.get("SellingStatus");
			DBObject cp = (DBObject) ss.get("CurrentPrice");
			item.put("price", cp.get("@currencyID")+" "+cp.get("#text"));
			
			/* endtime */
			sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
			String endtime = ((DBObject) item.get("ListingDetails")).get("EndTime").toString();
			Date dfendtime = sdf.parse(endtime.replace("T", " ").replace(".000Z", ""));
			if (dfendtime.equals(now)) {
				sdf.applyPattern("h:mm a");
			} else {
				sdf.applyPattern("MMM d h:mm a");
			}
			item.put("endtime", sdf.format(dfendtime));
			
			/* add */
			json.put(id, item);
		}
		
		json.put("cnt", cur.count());
		//json.put("cnt", cur.size());
		
		for (Object k : request.keySet()) {
			json.put(k.toString(), request.get(k));
		}
		json.put("selling", request.get("selling"));
		
		return SUCCESS;
	}
	
	@Action(value="/item", results={@Result(name="success",type="json")})
	public String item() throws Exception {
		
		json = new LinkedHashMap<String,Object>();

		DBCollection coll = db.getCollection("items");
		
		/* handling post parameters */
		ActionContext context = ActionContext.getContext();
		Map request = (Map) context.getParameters();
		
		String id = ((String[]) request.get("id"))[0];
		
		/* query */
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		BasicDBObject item = (BasicDBObject) coll.findOne(query);
		item.put("id", item.get("_id").toString());
		
		/* categorypath */
		Integer categoryid =
			Integer.parseInt(((BasicDBObject) item.get("PrimaryCategory")).getString("CategoryID"));
		List path = categorypath(item.getString("Site"), categoryid);
		item.put("categorypath", path);
		
		
		json.put("item", item);
		
		return SUCCESS;
	}
	
	@Action(value="/summary", results={@Result(name="success",type="json")})
	public String summary() throws Exception {
		
		LinkedHashMap<String,BasicDBObject> selling = getsellingquery();
		
		String[] userids = {"testuser_aichi",
							"testuser_hal",
							"testuser_chiba",
							"testuser_tokyo",
							"testuser_kanagawa"};
		
		//Mongo m = new Mongo();
		//DB db = m.getDB("ebay");
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

	private LinkedHashMap<String,BasicDBObject> getsellingquery() {
		
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
	
	private ArrayList categorypath(String site, Integer categoryid) {
		
		ArrayList path = new ArrayList();
		BasicDBObject query = new BasicDBObject();
		DBCollection coll = db.getCollection("Categories_"+site);
		
		while (true) {
			query.put("CategoryID", categoryid.toString());
			BasicDBObject row = (BasicDBObject) coll.findOne(query);
			path.add(0, Integer.parseInt(row.getString("CategoryID")));
			
			if (row.getString("CategoryLevel").equals("1")) break;
			categoryid = Integer.parseInt(row.getString("CategoryParentID"));
		}
		
		return path;
	}
	
	private LinkedHashMap<String,Object> children(String site, Integer categoryid) {
		
		LinkedHashMap<String,Object> name = new LinkedHashMap<String,Object>();
		ArrayList<String> arrchildren = new ArrayList<String>();
		
		BasicDBObject query = new BasicDBObject();
		if (categoryid == 0) {
			query.put("CategoryLevel", "1");
		} else {
			query.put("CategoryParentID", categoryid.toString());
			query.put("CategoryID", new BasicDBObject("$ne", categoryid.toString()));
		}
		
		DBCollection coll = db.getCollection("Categories_"+site);
		DBCursor cur = coll.find(query);
		while (cur.hasNext()) {
			DBObject row = cur.next();
			name.put(row.get("CategoryID").toString(), row.get("CategoryName").toString());
			arrchildren.add(row.get("CategoryID").toString());
		}
		LinkedHashMap<Integer,ArrayList<String>> children =
			new LinkedHashMap<Integer,ArrayList<String>>();
		children.put(categoryid, arrchildren);
		
		LinkedHashMap<String,Object> data = new LinkedHashMap<String,Object>();
		data.put("name", name);
		data.put("children", children);
		
		return data;
	}
	
	
	@Action(value="/test")
	public String test() throws Exception {
		
		String site = "US";
		Integer categoryid = 159681;
		
		ArrayList path = new ArrayList();
		
		BasicDBObject query = new BasicDBObject();
		
		DBCollection coll = db.getCollection("Categories_"+site);
		while (true) {
			
			query.put("CategoryID", categoryid.toString());
			BasicDBObject row = (BasicDBObject) coll.findOne(query);
			path.add(0, Integer.parseInt(row.getString("CategoryID")));
			
			if (row.getString("CategoryLevel").equals("1")) break;
			categoryid = Integer.parseInt(row.getString("CategoryParentID"));
			
			System.out.println(row.get("CategoryID"));
		}
		System.out.println(path.toString());
		
		return SUCCESS;
	}
}
