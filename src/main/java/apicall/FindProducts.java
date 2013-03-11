package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class FindProducts extends ApiCall {
	
	private String findtype;
	private String keyword;
	
	public FindProducts() throws Exception {
	}
	
	public FindProducts(String[] args) throws Exception {
		this.findtype = args[0];
		this.keyword  = args[1];
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		//reqdbo.append("WarningLevel", "High");
		//reqdbo.append("DetailLevel",  "ReturnAll");
		reqdbo.append("IncludeSelector", "DomainHistogram");
		reqdbo.append("MaxEntries",   "20");
		reqdbo.append("MessageID",    findtype+"."+keyword);
		
		if (findtype.equals("QueryKeywords")) {
			
			reqdbo.append("QueryKeywords", keyword);
			
		} else if (findtype.equals("ProductID")) {
			
			reqdbo.append("IncludeSelector", "Details");
			
			BasicDBObject productid = new BasicDBObject();
			productid.put("@type", "Reference");
			productid.put("#text", keyword);
			reqdbo.append("ProductID", productid);
		}
		
		String requestxml = convertDBObject2XML(reqdbo, "FindProducts");
		Future<String> future = pool18.submit(new ApiCallTask3(0, requestxml, "FindProducts"));
		String result = future.get();
		
		writelog("FindProducts/req.xml", requestxml);
		
		return result;
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String cid = resdbo.getString("CorrelationID");
		
		writelog("FindProducts/"+cid+".xml", responsexml);
		
		/*
		writelog("FindProducts/res.json", responsexml);
		*/
		
		//String json = resdbo.toString();
		
		return responsexml;
	}
}
