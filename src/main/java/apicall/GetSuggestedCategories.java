package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class GetSuggestedCategories extends ApiCall implements Callable {
	
	private String site;
  private String query;
	
	public GetSuggestedCategories() throws Exception {
	}
  
	public GetSuggestedCategories(String[] args) throws Exception {
		this.site  = args[0];
    this.query = args[1];
	}
  
	public String call() throws Exception {
		
		/* Read from cache file */
		String cachefile = basedir + "/logs/apicall/GetSuggestedCategories";
		cachefile += "/" + site + "." + query + ".xml";
		if ((new File(cachefile)).exists()) {
			log("read from cache");
			String responsexml = readfile(cachefile);
			return responsexml;
		}
		
		int siteid = getSiteID(site);
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.put("WarningLevel", "High");
		reqdbo.put("Query", query);
		reqdbo.put("MessageID", site + "." + query);
    
		String reqxml = convertDBObject2XML(reqdbo, "GetSuggestedCategories");
    
		Future<String> future = 
			pool18.submit(new ApiCallTask(siteid, reqxml, "GetSuggestedCategories"));
		
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
