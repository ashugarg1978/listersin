package ebaytool;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import ebaytool.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import java.util.HashMap;

public class AddItems extends ApiCall implements Callable {
	
	private int idx;
	private HashMap<Integer,BasicDBObject> mapdbo = new HashMap<Integer,BasicDBObject>();
	
	public AddItems (int idx, BasicDBObject requestdbobject) {
		this.idx = idx;
		mapdbo.put(idx, requestdbobject);
	}
	
	public BasicDBObject call() throws Exception {
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		BasicDBObject item = coll.findOne();
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		//dbobject.put("AddItemRequestContainer");
		
		BasicDBObject responsedbo = new BasicDBObject();
		
		return responsedbo;
	}
	
}
