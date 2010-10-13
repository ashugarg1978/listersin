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
	
	private String requestxml;
	
	public AddItems (String requestxml) {
		this.requestxml = requestxml;
	}
	
	public BasicDBObject call() throws Exception {
		
		writelog("AIs.req.xml", requestxml);
		
		String responsexml = callapi("AddItems", requestxml);
		
		writelog("AIs.res.xml", responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		return responsedbo;
	}
	
}
