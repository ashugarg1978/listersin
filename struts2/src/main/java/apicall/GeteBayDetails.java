package ebaytool.apicall;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import ebaytool.apicall.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import java.util.HashMap;
import java.util.*;

public class GeteBayDetails extends ApiCall implements Callable {
	
	public String call() throws Exception {
		
		if (true) {
			Thread.sleep(5000);
			
			return "OK";
		}
		
		String token = "AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB";
		
		BasicDBObject requestdbobject = new BasicDBObject();
		requestdbobject.append("WarningLevel", "High");
		requestdbobject.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		
		JSONObject requestjsonobject = JSONObject.fromObject(requestdbobject.toString());
		
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("GeteBayDetails");
		xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
		xmls.setTypeHintsEnabled(false);
		String requestxml = xmls.write(requestjsonobject);
		
		String responsexml = callapi(0, requestxml);
		
		writelog("GeteBayDetails.res.xml", responsexml);
		
		JSONObject json = (JSONObject) new XMLSerializer().read(responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		for (Object idx : responsedbo.keySet()) {
			String classname = responsedbo.get(idx).getClass().toString();
			
			DBCollection coll = db.getCollection(idx.toString());
			coll.drop();
			
			if (classname.equals("class com.mongodb.BasicDBList")) {
				System.out.println("List "+idx.toString());
				coll.insert((List<DBObject>) responsedbo.get(idx));
			} else if (classname.equals("class com.mongodb.BasicDBObject")) {
				System.out.println("Object "+idx.toString());
				coll.insert((DBObject) responsedbo.get(idx));
			} else {
				System.out.println("SKIP "+classname+" "+idx.toString());
			}
		}
		
		return "";
	}
	
}
