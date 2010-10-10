package ebaytool;

import com.mongodb.BasicDBObject;

import ebaytool.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

import java.util.HashMap;

public class GetSellerList extends ApiCall implements Callable {
	
	private int idx;
	private HashMap<Integer,BasicDBObject> mapdbo = new HashMap<Integer,BasicDBObject>();
	
	public GetSellerList (int idx, BasicDBObject requestdbobject) {
		this.idx = idx;
		mapdbo.put(idx, requestdbobject);
	}
	
	public BasicDBObject call() throws Exception {
		
		int idx = this.idx;
		
		String callname = "GetSellerList";
		BasicDBObject chkdbo = mapdbo.get(idx);
		
		System.out.println(idx+" req "+chkdbo.get("Pagination").toString());
		
		BasicDBObject resdbo = callapi(callname, chkdbo);
		
		System.out.println(idx+" res "+resdbo.get("Pagination").toString());
		
		return resdbo;
	}
	
}
