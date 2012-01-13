package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class GetProductSearchResults extends ApiCall {
	
	public GetProductSearchResults() throws Exception {
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("DetailLevel",  "ReturnAll");
		reqdbo.append("MessageID",    "US");
		
		BasicDBObject productsearch = new BasicDBObject();
		productsearch.append("QueryKeywords", "blackberry");
		productsearch.append("CharacteristicSetIDs", new BasicDBObject("ID", "1785"));
		reqdbo.append("ProductSearch", productsearch);
		
		String requestxml = convertDBObject2XML(reqdbo, "GetProductSearchResults");
		Future<String> future =
			pool18.submit(new ApiCallTask(0, requestxml, "GetProductSearchResults"));
		String result = future.get();
		
		return result;
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetProductSearchResults/"+site+".xml", responsexml);
		
		return responsexml;
	}
}
