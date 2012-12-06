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
	
	public GetSellerList() throws Exception {
	}
	
	public GetSellerList(String[] args) throws Exception {
		this.email     = args[0];
		this.userid    = args[1];
		this.daterange = args[2];
		this.datestart = args[3];
		this.dateend   = args[4];
	}
	
	public String call() throws Exception {
		
		String token = gettoken(email, userid);
		
		/* GetSellerList */
		BasicDBObject dbobject = new BasicDBObject();
		//dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put(daterange+"TimeFrom", datestart+" 00:00:00");
		dbobject.put(daterange+"TimeTo",   dateend  +" 00:00:00");
		dbobject.put("Pagination", new BasicDBObject("EntriesPerPage",50).append("PageNumber",1));
		dbobject.put("Sort", "2");
		dbobject.put("MessageID", email+" "+userid);
    
		String requestxml = convertDBObject2XML(dbobject, "GetSellerList");
		//writelog("GSL.req."+email+"."+userid+".xml", requestxml);
		Future<String> future =
            pool18.submit(new ApiCallTask(userid, 0, requestxml, "GetSellerList"));
		String responsexml = future.get();
		
		BasicDBObject result = convertXML2DBObject(responsexml);
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		log(userid+" : total "+pages+" page(s).");
		
		for (int i=2; i<=pages; i++) {
			((BasicDBObject) dbobject.get("Pagination")).put("PageNumber", i);
			requestxml = convertDBObject2XML(dbobject, "GetSellerList");
			
			future = pool18.submit(new ApiCallTask(userid, 0, requestxml, "GetSellerList"));
			future.get();
		}
		
		updatemessage(email, "");
		
		return "OK";
	}
	
	public String callback(String responsexml) throws Exception {
    
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		String userid = ((JSONObject) json.get("Seller")).get("UserID").toString();
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];
		String token = gettoken(email, userid);
		
		//String userid = ((BasicDBObject) resdbo.get("Seller")).get("UserID").toString();
		
		int pagenumber = Integer.parseInt(resdbo.getString("PageNumber"));
		int itemcount  = Integer.parseInt(resdbo.getString("ReturnedItemCountActual"));
		int itemsperpage = Integer.parseInt(resdbo.getString("ItemsPerPage"));
		
		writelog("GetSellerList/"+email+"."+userid+"."+pagenumber+".xml", responsexml);
		
		if (itemcount == 0) {
			log(userid+" no items.");
			return responsexml;
		}
		
		log(userid+": "
        +pagenumber+"/"
        +((BasicDBObject) resdbo.get("PaginationResult")).get("TotalNumberOfPages").toString()
        +" pages, "
        +((pagenumber-1)*itemsperpage+1)+"-"+((pagenumber-1)*itemsperpage+itemcount)+"/"
        +((BasicDBObject) resdbo.get("PaginationResult")).get("TotalNumberOfEntries").toString()
        +" items");
		
		String message = "Importing "+userid+"'s items from eBay."
			+ " "+((pagenumber-1)*itemsperpage+1)+"-"+((pagenumber-1)*itemsperpage+itemcount)
			+ " of "
			+ ((BasicDBObject) resdbo.get("PaginationResult")).getString("TotalNumberOfEntries")
			+ " items.";
		updatemessage(email, message);
    
		JSONArray jsonarr = new JSONArray();
		if (itemcount == 1) {
			jsonarr.add(json.getJSONObject("ItemArray").getJSONObject("Item"));
		} else {
			jsonarr = json.getJSONObject("ItemArray").getJSONArray("Item");
		}
		for (Object item : jsonarr) {
			
			/* convert JSON to DBObject */
			DBObject dbobject = (DBObject) com.mongodb.util.JSON.parse(item.toString());
			String itemid = dbobject.get("ItemID").toString();
			
			/* GetItem */
			String[] args = {email, userid, itemid, "waitcallback"};
			GetItem getitem = new GetItem(args);
			getitem.call();
			
			// Don't use GetMultipleItems which doesn't return needed info.
		}
		
		return responsexml;
	}
	
}
