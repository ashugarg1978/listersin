package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class GetOrders extends ApiCall implements Callable {
	
	private String email;
	private String userid;
    
	public GetOrders() throws Exception {
	}
	
	public GetOrders(String email, String userid) throws Exception {
		this.email  = email;
		this.userid = userid;
	}
	
	public String call() throws Exception {
        
		/* get token from db */
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("userids."+userid, new BasicDBObject("$exists", 1));
		
		BasicDBObject fields = new BasicDBObject();
		fields.put("userids."+userid, 1);
		
		BasicDBObject user = (BasicDBObject) db.getCollection("users").findOne(query, fields);
		
		BasicDBObject useriddbo = (BasicDBObject) user.get("userids");
		BasicDBObject tokendbo  = (BasicDBObject) useriddbo.get(userid);
		String token = tokendbo.getString("eBayAuthToken");
		
        BasicDBObject reqdbo = new BasicDBObject();
        reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
        reqdbo.put("WarningLevel", "High");
        reqdbo.put("DetailLevel", "ReturnAll");
        reqdbo.put("NumberOfDays", "30");
		reqdbo.put("MessageID", email+" "+userid);
        
        String requestxml = convertDBObject2XML(reqdbo, "GetOrders");
		
        pool18.submit(new ApiCallTask(0, requestxml, "GetOrders"));
        
        // todo: next page (pagination)
        
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
        
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
        
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		email  = messages[0];
		userid = messages[1];
        
		int pagenumber = Integer.parseInt(resdbo.getString("PageNumber"));
		int ordercount  = Integer.parseInt(resdbo.getString("ReturnedOrderCountActual"));
		int ordersperpage = Integer.parseInt(resdbo.getString("OrdersPerPage"));
        
		writelog("GetOrders/"+email+"."+userid+"."+pagenumber+".xml", responsexml);
        
		if (ordercount == 0) {
			log(userid+" no orders.");
			return "";
		}
        
		BasicDBObject userquery = new BasicDBObject();
		userquery.put("userids."+userid, new BasicDBObject("$exists", true));
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
        
		DBCollection ordercoll = db.getCollection("orders."+userdbo.getString("_id"));
        log("orders."+userdbo.getString("_id"));
        
		JSONArray jsonarr = new JSONArray();
		if (ordercount == 1) {
			jsonarr.add(json.getJSONObject("OrderArray").getJSONObject("Order"));
		} else {
			jsonarr = json.getJSONObject("OrderArray").getJSONArray("Order");
		}
		for (Object order : jsonarr) {
            
			/* convert JSON to DBObject */
			DBObject dbobject = (DBObject) com.mongodb.util.JSON.parse(order.toString());
			String orderid = dbobject.get("OrderID").toString();
            
            log(orderid);
            
            //dbobject.put("_id", orderid);
            
            BasicDBObject query = new BasicDBObject();
            query.put("_id", orderid);
            
            BasicDBObject update = new BasicDBObject();
            update.put("$set", dbobject);
            
            WriteResult result = ordercoll.update(query, update, true, false);
            log(result.getError());
        }
        
		return "";
	}
    
}
