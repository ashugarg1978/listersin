package ebaytool;

import com.mongodb.BasicDBObject;

import ebaytool.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

public class GetSellerList extends ApiCall {
	
	public GetSellerList (BasicDBObject requestdbobject) {
		this.callname = "GetSellerList";
		this.requestdbobject = requestdbobject;
	}
	
	public BasicDBObject call() throws Exception {
		
		String debug = "page:"+((BasicDBObject) requestdbobject
								.get("Pagination")).get("PageNumber").toString();
		System.out.println(debug);
		
		BasicDBObject responsedbobject = callapi();
		
		debug = debug + " -> " + responsedbobject.get("PageNumber").toString();
		System.out.println(debug);
		
		return responsedbobject;
	}
	
}
