package ebaytool;

import com.mongodb.BasicDBObject;

import ebaytool.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

public class GetSellerList extends ApiCall implements Callable {
	
	private final BasicDBObject requestdbobject;
	
	public GetSellerList (BasicDBObject requestdbobject) {
		
		String debug = "GetSellerList constructor:"+((BasicDBObject) requestdbobject
								.get("Pagination")).get("PageNumber").toString();
		System.out.println(debug);
		
		this.requestdbobject = requestdbobject;
	}
	
	public BasicDBObject call() throws Exception {
		
		String callname = "GetSellerList";
		BasicDBObject requestdbobject = this.requestdbobject;
		
		String debug = "page:"+((BasicDBObject) requestdbobject
								.get("Pagination")).get("PageNumber").toString();
		System.out.println(debug);
		
		BasicDBObject responsedbobject = callapi(callname, requestdbobject);
		
		debug = debug + " -> " + responsedbobject.get("PageNumber").toString();
		System.out.println(debug);
		
		return responsedbobject;
	}
	
}
