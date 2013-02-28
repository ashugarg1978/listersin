package ebaytool.apicall;

import com.mongodb.*;
import com.mongodb.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class GetSellerList extends ApiCall {

	private String email;
	private String userid;
	private String daterange;
	private String datestart;
	private String dateend;
  private String detaillevel;
	
	public GetSellerList() throws Exception {
	}
	
	public GetSellerList(String[] args) throws Exception {
		this.email     = args[0];
		this.userid    = args[1];
		this.daterange = args[2];
		this.datestart = args[3];
		this.dateend   = args[4];
		if (args.length == 6) {
			this.detaillevel = args[5];
		} else {
			this.detaillevel = "";
    }
	}
	
	public String call() throws Exception {
		
		String token = gettoken(email, userid);
		
		/* GetSellerList */
		BasicDBObject dbobject = new BasicDBObject();
    if (this.detaillevel.equals("ReturnAll")) {
      dbobject.put("DetailLevel", "ReturnAll");
      dbobject.put("IncludeWatchCount", "true");
      dbobject.put("IncludeVariations", "true");
    } else if (this.detaillevel.equals("Fine")) {
      dbobject.put("GranularityLevel", "Fine");
    }
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put(daterange+"TimeFrom", datestart+" 00:00:00");
		dbobject.put(daterange+"TimeTo",   dateend  +" 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage",50).append("PageNumber",1));
		dbobject.put("Sort", "2");
		dbobject.put("MessageID", email+" "+userid);
		
		String requestxml = convertDBObject2XML(dbobject, "GetSellerList");
		Future<String> future = pool18.submit
			(new ApiCallTask(userid, 0, requestxml, "GetSellerList", "filename"));
		String responsexml = future.get();
		
		BasicDBObject result = convertXML2DBObject(responsexml);
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
																 .get("TotalNumberOfPages").toString());
		log(userid+" : total "+pages+" page(s).");
		
		for (int i=2; i<=pages; i++) {
			((BasicDBObject) dbobject.get("Pagination")).put("PageNumber", i);
			requestxml = convertDBObject2XML(dbobject, "GetSellerList");
      
			future = pool18.submit(new ApiCallTask(userid, 0, requestxml, "GetSellerList", "filename"));
			future.get();
		}
		
		updatemessage(email, "");
		
		return "OK";
	}
	
	public String callback(String filename) throws Exception {
    
		String responsexml = readfile(filename);
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String timestamp = resdbo.getString("Timestamp").replaceAll("\\.", "_");
    
		BasicDBObject seller = (BasicDBObject) resdbo.get("Seller");
		String userid = seller.getString("UserID");
		
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];
		String token = gettoken(email, userid);
    
		int pagenumber = Integer.parseInt(resdbo.getString("PageNumber"));
		int itemcount  = Integer.parseInt(resdbo.getString("ReturnedItemCountActual"));
		int itemsperpage = Integer.parseInt(resdbo.getString("ItemsPerPage"));
		
		writelog("GetSellerList/"+email+"."+userid+"."+pagenumber+".xml", responsexml);
		
		if (itemcount == 0) {
			log(userid + " no items.");
			return responsexml;
		}
		
		log(userid + ": "
        + pagenumber + "/"
        + ((BasicDBObject) resdbo.get("PaginationResult")).get("TotalNumberOfPages").toString()
        + " pages, "
        + ((pagenumber-1)*itemsperpage+1)+"-"+((pagenumber-1)*itemsperpage+itemcount)+"/"
        + ((BasicDBObject) resdbo.get("PaginationResult")).get("TotalNumberOfEntries").toString()
        + " items");
    
		String message = "Importing "+userid+"'s items from eBay."
			+ " "+((pagenumber-1)*itemsperpage+1)+"-"+((pagenumber-1)*itemsperpage+itemcount)
			+ " of "
			+ ((BasicDBObject) resdbo.get("PaginationResult")).getString("TotalNumberOfEntries")
			+ " items.";
		updatemessage(email, message);
		
		/* get collection name for each users */
		BasicDBObject userquery = new BasicDBObject();
		userquery.put("email", email);
		userquery.put("userids2.username", userid);
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
    
    String itemcollname = "items." + userdbo.getString("_id");
    
		BasicDBObject itemarray = (BasicDBObject) resdbo.get("ItemArray");
		BasicDBList items = new BasicDBList();
		String classname = itemarray.get("Item").getClass().toString();
		if (classname.equals("class com.mongodb.BasicDBObject")) {
			items.add((BasicDBObject) itemarray.get("Item"));
		} else if (classname.equals("class com.mongodb.BasicDBList")) {
			items = (BasicDBList) itemarray.get("Item");
		} else {
			log("Class Error:" + classname);
		}
		for (Object item : items) {
			
			BasicDBObject dbo = (BasicDBObject) item;
			String itemid = dbo.get("ItemID").toString();
			
      if (dbo.containsField("Title")) {
				
				dbo.put("Seller", seller.copy());
				
        // todo: update items collection
        String[] args = {email, userid, itemid};
        GetItem getitem = new GetItem(args);
        getitem.upsertitem(email, userid, itemid, dbo, itemcollname, timestamp);
        
      } else {
        
        /* GetItem */
        // todo: Should I wait callback?
        //String[] args = {email, userid, itemid, "waitcallback"};
        String[] args = {email, userid, itemid};
        
        GetItem getitem = new GetItem(args);
        getitem.call();
        
        // Don't use GetMultipleItems which doesn't return needed info.
        
      }
		}
		
		return responsexml;
	}
	
}
