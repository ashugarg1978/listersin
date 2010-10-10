package ebaytool;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;

public class ApiCall implements Callable {
	
	public String callname;
	public BasicDBObject requestdbobject;
	
	private String result;
	
	public BasicDBObject call() throws Exception {
		return null;
	}
	
	private String convertDBObject2XML(DBObject dbobject) {
		
		String jsonstring = (String) com.mongodb.util.JSON.serialize(dbobject);
		
		JSONObject jso = JSONObject.fromObject(jsonstring);
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("GetSellerListRequest");
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String xml = xmls.write(jso);
		
		return xml;
	}
	
	private BasicDBObject convertXML2DBObject(String xml) {
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		
		net.sf.json.JSON json = xmlSerializer.read(xml);
		
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		
		return dbobject;
	}
	
	public BasicDBObject callapi() throws Exception {
		
		String xml = convertDBObject2XML(requestdbobject);
		
        URL url = new URL("https://api.sandbox.ebay.com/ws/api.dll");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        //URL url = new URL("http://175.41.130.89/dump.php");
        //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		
		/* http request header */
        conn.setRequestProperty("Content-Type", "text/xml");
        conn.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "677");
        conn.setRequestProperty("X-EBAY-API-CALL-NAME", callname);
        conn.setRequestProperty("X-EBAY-API-SITEID", "0");
        conn.setRequestProperty("X-EBAY-API-DEV-NAME",  "e60361cd-e306-496f-ad7d-ba7b688e2207");
        conn.setRequestProperty("X-EBAY-API-APP-NAME",  "Yoshihir-1b29-4aad-b39f-1be3a37e06a7");
        conn.setRequestProperty("X-EBAY-API-CERT-NAME", "8118c1eb-e879-47f3-a172-2b08ca680770");
		
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(xml);
		osw.flush();
		osw.close();
		
        conn.connect();
		
		/* handle http response */
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String line;
		String response = "";
		while ((line = br.readLine()) != null) {
			response = response + line;
		}
		br.close();
		
		BasicDBObject responsedbobject = convertXML2DBObject(response);
		
		return responsedbobject;
	}
	
}
