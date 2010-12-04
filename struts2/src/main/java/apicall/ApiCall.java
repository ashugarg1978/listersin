package ebaytool.apicall;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.*;
import java.io.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class ApiCall implements Callable {
	
	public static ThreadPoolExecutor pool18;
	public static CompletionService<String> ecs18;
	public static Mongo m;
	public static DB db;
	
	public ApiCall() throws Exception {
		
		if (pool18 == null) {
			pool18 = (ThreadPoolExecutor) Executors.newFixedThreadPool(18);
			ecs18 = new ExecutorCompletionService<String>(pool18);
		}
		
		if (db == null) {
			Mongo m = new Mongo();
			db = m.getDB("ebay");
		}
	}
	
	public String call() throws Exception {
		return "";
	}
	
	public void shutdown() {
		
		pool18.shutdown();
		
		return;
	}
	
	public String convertDBObject2XML(DBObject dbobject) {
		
		JSONObject jso = JSONObject.fromObject(dbobject.toString());
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("GetSellerListRequest");
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String xml = xmls.write(jso);
		
		return xml;
	}
	
	public BasicDBObject convertXML2DBObject(String xml) {
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		
		net.sf.json.JSON json = xmlSerializer.read(xml);
		
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		
		return dbobject;
	}
	
	public void writelog(String filename, String content) {
		try {
			FileWriter fstream = new FileWriter("/var/www/ebaytool/logs/apixml/"+filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return;
	}
	
}
