package ebaytool.apicall;

import com.mongodb.*;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class GetSuggestedCategories extends ApiCall implements Callable {

  private String query;
	
	public GetSuggestedCategories() throws Exception {
	}
  
	public GetSuggestedCategories(String[] args) throws Exception {
    this.query = args[0];
	}
  
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.put("WarningLevel", "High");
		reqdbo.put("Query", query);
		reqdbo.put("MessageID", query);
    
		String reqxml = convertDBObject2XML(reqdbo, "GetSuggestedCategories");
    
		Future<String> future = pool18.submit(new ApiCallTask(0, reqxml, "GetSuggestedCategories"));
		String result = future.get();
		
		return result;
	}
	
	public String callback(String resxml) throws Exception {
    
		BasicDBObject resdbo = convertXML2DBObject(resxml);
		String cid = resdbo.getString("CorrelationID");
    
		writelog("GetSuggestedCategories/" + cid + ".xml", resxml);
    
		return resxml;
	}
}
