package ebaytool.apicall;

import com.mongodb.*;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class GetApiAccessRules extends ApiCall implements Callable {
	
	public GetApiAccessRules() throws Exception {
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		
		String requestxml = convertDBObject2XML(reqdbo, "GetApiAccessRules");
		
		writelog("GetApiAccessRules/req.xml", requestxml);
		
		pool18.submit(new ApiCallTask(0, requestxml, "GetApiAccessRules"));
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		writelog("GetApiAccessRules/res.xml", responsexml);
		
		return "";
	}
}
