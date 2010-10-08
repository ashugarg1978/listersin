package ebaytool;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import com.thoughtworks.xstream.XStream;

import java.util.LinkedHashMap;

import java.io.*;
import java.net.URL;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
//import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

public class Daemon extends Thread {
	
    public static void main(String[] args) {
		
		System.out.println("main() method");
		
		Daemon t = new Daemon();
		//t.setDaemon(true);
		t.start();
		
		try {
			
			for (int i=0; i<10; i++) {
				//System.out.println("main() method.["+i+"]");
			}
			
			//Thread.sleep(10000);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
    }
	
	public void run() {
		
		System.out.println("run() method");
		
		try {
			
			System.out.println("run() calling getSellerList()");
			getSellerList();
			System.out.println("run() end of getSellerList()");
			
			for (int i=0; i<10; i++) {
				//Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public String getSellerList() throws Exception {
		
		String token = "AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB";
		
		BasicDBObject test = new BasicDBObject();
		test.put("DetailLevel", "ReturnAll");
		test.put("WarningLevel", "High");
		test.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		test.put("StartTimeFrom", "2010-06-01 00:00:00");
		test.put("StartTimeTo",   "2010-08-01 00:00:00");
		test.put("Pagination", new BasicDBObject("EntriesPerPage", "50"));
		test.put("Sort", "1");
		String js = (String) com.mongodb.util.JSON.serialize(test);
		
		
		JSONObject jso = JSONObject.fromObject(js);
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("GetSellerListRequest");
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String xml = xmls.write(jso);
		
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		/* call api */
		JSONObject json = (JSONObject) callapi("GetSellerList", xml);
		
		String userid = ((JSONObject) json.get("Seller")).get("UserID").toString();
		
		JSONArray jsonarr = json.getJSONObject("ItemArray").getJSONArray("Item");
		for (Object item : jsonarr) {
			
			/* convert JSON to DBObject */
			DBObject dbobject = (DBObject) com.mongodb.util.JSON.parse(item.toString());
			dbobject.put("UserID", userid);
			dbobject.put("deleted", 0);
			
			/* insert into mongodb */
			coll.insert(dbobject);
			
			System.out.println(dbobject.get("Title"));
		}
		
		return "ok";
	}
	
	
	private net.sf.json.JSON callapi(String callname, String xmldata) throws Exception {
		
        URL url = new URL("https://api.sandbox.ebay.com/ws/api.dll");
		
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
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
		osw.write(xmldata);
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
		
		/* XML -> JSON */
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		net.sf.json.JSON json = xmlSerializer.read(response);
		
		return json;
	}
	
}
