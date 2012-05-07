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
	public static String basedir;
	public static BasicDBObject configdbo;
	public static String admintoken;
	
	public ApiCall() throws Exception {
		
		if (pool18 == null) {
			pool18 = (ThreadPoolExecutor) Executors.newFixedThreadPool(18);
			ecs18 = new ExecutorCompletionService<String>(pool18);
			log("ApiCall() constructor. pool18");
		}
		
		basedir = System.getProperty("user.dir");
		configdbo = convertXML2DBObject(readfile(basedir+"/config/config.xml"));
		admintoken = configdbo.getString("admintoken");
		
		if (db == null) {
			Mongo m = new Mongo();
			db = m.getDB(configdbo.getString("database"));
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
		
		// todo: auto create directory
		FileWriter fstream = new FileWriter(basedir+"/logs/apicall/"+filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
		
		return;
	}
	
	public void updatemessage(String email, String message) {
		
		db.getCollection("users").update
			(new BasicDBObject("email", email),
			 new BasicDBObject("$set", new BasicDBObject("message", message)));
		
		log(email+" "+message);
		
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
