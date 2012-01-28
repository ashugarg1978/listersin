package ebaytool.apicall;

import com.mongodb.*;
import com.mongodb.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class ApiCall implements Callable {
	
	public static ThreadPoolExecutor pool18;
	public static CompletionService<String> ecs18;
	public static Mongo m;
	public static DB db;
	
	/* Sandbox */
	public String admintoken = "AgAAAA**AQAAAA**aAAAAA**p/6OTg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mFow6dj6x9nY+seQ**Q0UBAA**AAMAAA**JJDsfE+wQFK+EWdZ26syt/D3f1YfYL236IUFs4qTucxrgq0cSr5Pk0YD35LuwFQHLwwJ6Mbz3l+qsQZkF05V/WbkYgbvLu8HG6AtdtL6VdIo941fiW9+XFOvdMgdPI+zFnSp7HNec9+f0plQ7tutQUuu2/t5GmWEEFB3hP9jDVDZ5nD/uNY+332WybjTSwkUUwTQW74fcnEZJB8Afr+gYk9kt71yD5iZMMc6Pbj38FcrYPDLIrhIh5yC7S/Z3EGkUxqPqJuC0SO6iisuG4wG28zJ5vJqQ4lfa+M9VIlOhBADdo2KFaF37wVR0sakHBmmXRDRNfJ5M4DxHX0mW1Y9gHXrjO9IRuG77kjeCA+yvauYJU/QMuidcftlP/QwXZEd4oI1nj/fUyPZbtNMbichZn/5AmEfpJfD3tRkfEyW9QL0Xobw4i5QOi8MVSE2jh+3OjK4J0iKK4naQ9Xiv43uOjPTVbR6Ii3hgGBC+nOXVqgh2DmQpX4vznTl92f5WEyFuNZpLuiyNs3T/QbQ/7ps5POBAnfCwDQMSVBdvRPPOxcXwb8D8uPk2H71VarDxPFYzpew/KVBjpB1nhwXvXWT5uKyq86cYQSanEK7QxUDGu5PcTT2eYB/cZ4lDoviF135Z91ULc4WiVzUpeoHQ7et+eED7bw4VD0HeFfTKwqoQ0VZvPfs3IVhLGswtrlrWmklmMUBuy2d4GF3UInZOUvkkcKw7Sk/nmfLhueixz4iQkWDSi+wxeOumVk86mNoc93H";

	/* Production */
	//public String admintoken = "AgAAAA**AQAAAA**aAAAAA**tzUjTw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wNmYujAZKEpwqdj6x9nY+seQ**bwYBAA**AAMAAA**ePoopeUk1B6r20McoOgQ3e3JkxioqhJPeYW7xflFSKuxbImCPWBqPM5fzpk7WfBCBCACRN7uJyt2hE4Jz33tmOddLnuwzaUIEyhYoIU5kPT2R8J6OvhJzgHT5sLmwkiX1LEa84JfPPgshxK6PjaQhfAb0l+YSTxz9e86OXbUawtXg3nMu33h1aYSD0W0pdSp65KcTXm0VRZimdGHcEZ0laQeSoSG5vxZW8qVLbHcYFuYjySx1Tyu0StMAFMWBrwIES+oX5e1wkoZgPojLDZBlgus2ZW8mLckz6Kdozw9k+uVCp+v5GJXXGEWRabhsqqd7D/aYNNeramp3ZgQx/jVioRZBWUa529JShBkKz8kbCbx3I4cjjRKFcNhDHStxZHCs0qm06J2Be5/9V/KzT36Rx+rM4Nu4BMSFJ2XiG0Fn32nMjkglapQaQSYkL+osspwqUfRU2pDeKhHXZbNjBpJBYRiA7WytYAPEhNyuvOQmD9iVydWY6YKVNaRGUIjVhppmUZeiCb7Tyl4o/xCZzoPpr0haEorAKmnBlge9bC+1HmlyK9OhzKlB7vvAi5ew9H7DTq5KClwdlR2N8lQhKt+5578qtuBDgIZv8P3ri51ycy8NSFQvqMa/0E3UcI3eCuJl+VPBXbw5Y4JtQl8H5NDfoeA04mB5Ln5YK2D8DMJW4Vy6uVfDbeFFrziZ2AYLsqUUC6XdpTkEW2hh6XQsKCtGI3A5A65PFGEzoRQSM/La4th7bnKcsEQp/NiNS7mhpHH";
	
	public ApiCall() throws Exception {
		
		if (pool18 == null) {
			pool18 = (ThreadPoolExecutor) Executors.newFixedThreadPool(18);
			ecs18 = new ExecutorCompletionService<String>(pool18);
			log("ApiCall() constructor. pool18");
		}
		
		if (db == null) {
			Mongo m = new Mongo();
			db = m.getDB("ebay");
			log("ApiCall() constructor. db");
		}
	}
	
	public String call() throws Exception {
		return "";
	}
	
	// todo: Should I create an interface class?
	public String callback(String responsexml) throws Exception {
		return "";
	}
	
	public void shutdown() {
		
		pool18.shutdown();
		
		return;
	}
	
	public String convertDBObject2XML(DBObject dbobject, String rootname) {
		
		JSONObject jso = JSONObject.fromObject(dbobject.toString());
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName(rootname);
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String xml = xmls.write(jso);
		
		return xml;
	}
	
	public BasicDBObject convertXML2DBObject(String xml) {
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		//xmlSerializer.setTypeHintsCompatibility(true);
		xmlSerializer.setTypeHintsEnabled(false);
		
		net.sf.json.JSON json = xmlSerializer.read(xml);
		
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		
		return dbobject;
	}
	
	public void writelog(String filename, String content) throws Exception {
		
		FileWriter fstream = new FileWriter("/var/www/ebaytool.jp/logs/apicall/"+filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
		
		return;
	}
	
	public void log(String message) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println(sdf.format(now).toString()+" "+message);
		
		return;
	}
	
	public String readfile(String filename) throws Exception {
		
		String data = "";
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			data = data + line;
		}
		br.close();
		
		return data;
	}
	
	public String gettoken(String email, String userid) throws Exception {
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("userids."+userid, new BasicDBObject("$exists", 1));
		
		BasicDBObject fields = new BasicDBObject();
		fields.put("userids."+userid, 1);
		
		BasicDBObject user = (BasicDBObject) db.getCollection("users").findOne(query, fields);
		
		BasicDBObject useriddbo = (BasicDBObject) user.get("userids");
		BasicDBObject tokendbo  = (BasicDBObject) useriddbo.get(userid);
		String token = tokendbo.getString("eBayAuthToken");
		
		return token;
	}
	
	public HashMap<String,String> getUserIdToken(String email) throws Exception {
		
		HashMap<String,String> hashmap = new HashMap<String,String>();
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		
		BasicDBObject user = (BasicDBObject) db.getCollection("users").findOne(query);
			
		if (user.containsField("userids")) {
			BasicDBObject userids = (BasicDBObject) user.get("userids");
			for (Object userid : userids.keySet()) {
				
				String token =
					((BasicDBObject) userids.get(userid.toString())).getString("eBayAuthToken");
				
				hashmap.put(userid.toString(), token);
			}
		}
		
		return hashmap;
	}
	
}
