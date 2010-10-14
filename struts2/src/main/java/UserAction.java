package ebaytool.actions;

import java.io.*;
import java.net.URL;
//import java.net.HttpURLConnection;
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

@ParentPackage("json-default")
public class UserAction extends ActionSupport {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	private static Mongo m;
	private static DB db;
	
	public UserAction() throws Exception {
		m = new Mongo();
		db = m.getDB("ebay");
	}
	
	private LinkedHashMap<String,Object> json;
	
	public LinkedHashMap<String,Object> getJson() {
		return json;
	}
	
	@Action(value="/items", results={@Result(name="success",type="json")})
	public String items() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<String,BasicDBObject> sellingquery = getsellingquery();
		
		/* connect to database */
		//Mongo m = new Mongo();
		//DB db = m.getDB("ebay");
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("");
		
		DBCursor cur = coll.find(query, field).limit(limit).skip(offset).sort(sort);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			String id = item.get("_id").toString();
			
			DBObject ss = (DBObject) item.get("SellingStatus");
			DBObject cp = (DBObject) ss.get("CurrentPrice");
			item.put("price", cp.get("@currencyID")+" "+cp.get("#text"));
			item.put("endtime", );
			
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
	
	@Action(value="/test")
	public String test() throws Exception {

        URL url = new URL("https://api.sandbox.ebay.com/ws/api.dll");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        //URL url = new URL("http://175.41.130.89/dump.php");
        //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		
        conn.setRequestProperty("Content-Type", "text/xml");
        conn.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "677");
        conn.setRequestProperty("X-EBAY-API-CALL-NAME", "GetSellerList");
        conn.setRequestProperty("X-EBAY-API-SITEID", "0");
        conn.setRequestProperty("X-EBAY-API-DEV-NAME", "e60361cd-e306-496f-ad7d-ba7b688e2207");
        conn.setRequestProperty("X-EBAY-API-APP-NAME", "Yoshihir-1b29-4aad-b39f-1be3a37e06a7");
        conn.setRequestProperty("X-EBAY-API-CERT-NAME", "8118c1eb-e879-47f3-a172-2b08ca680770");
		
		String xmldata;
		xmldata = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><GetSellerListRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"><WarningLevel>High</WarningLevel><RequesterCredentials><eBayAuthToken>AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB</eBayAuthToken></RequesterCredentials><DetailLevel>ReturnAll</DetailLevel><StartTimeFrom>2010-06-01 00:00:00</StartTimeFrom><StartTimeTo>2010-08-30 00:00:00</StartTimeTo><Pagination><EntriesPerPage>50</EntriesPerPage><PageNumber>1</PageNumber></Pagination><Sort>1</Sort></GetSellerListRequest>";
		
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(xmldata);
		osw.flush();
		osw.close();
		
        //PrintWriter output = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
        //String fileContent = convertFileContent2String(XmlFileName);
		
        //output.println(fileContent);
        //output.close();
        conn.connect();
		
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			// Process line...
		}
		br.close();
		
		return SUCCESS;
	}
}
