package ebaytool;

//import java.util.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

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

public class ApiCall {
	
	public String convertDBObject2XML(DBObject dbobject) {
		
		System.out.println(this.getClass());
		
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
	
	public String callapi(String callname, String requestxml) throws Exception {
		
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
		osw.write(requestxml);
		osw.flush();
		osw.close();
		
        conn.connect();
		
		/* handle http response */
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String line;
		String responsexml = "";
		while ((line = br.readLine()) != null) {
			responsexml = responsexml + line;
		}
		br.close();
		
		//String filename = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		//writelog();
		
		return responsexml;
	}
	
	public void writelog(String filename, String content) {
		try {
			FileWriter fstream = new FileWriter("/var/www/dev.xboo.st/logs/apixml/"+filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return;
	}
	
}
