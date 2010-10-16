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
	
	private String userid;
	private String site;
	private String chunkidx;
	private String requestxml;
	
	public AddItems (String userid, String site, String chunkidx, String requestxml) {
		this.userid     = userid;
		this.site       = site;
		this.chunkidx   = chunkidx;
		this.requestxml = requestxml;
	}
	
	public BasicDBObject call() throws Exception {
		
		writelog("AIs.req."+userid+"."+site+"."+chunkidx+".xml", requestxml);
		
		String responsexml = callapi(requestxml);
		
		writelog("AIs.res."+userid+"."+site+"."+chunkidx+".xml", responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		return responsedbo;
	}
	
}
