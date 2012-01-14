package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import org.apache.commons.codec.binary.Base64;

public class GetAttributesXSL extends ApiCall {
	
	private String productid;
	
	public GetAttributesXSL() throws Exception {
	}
	
	public String call() throws Exception {
		
		DBCursor cur = db.getCollection("US.eBayDetails.SiteDetails").find();
		Integer cnt = cur.count();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			
			//if (!site.equals("HongKong")) continue;
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel",  "ReturnAll");
			reqdbo.append("MessageID",    site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetAttributesXSL");
			Future<String> future =
				pool18.submit(new ApiCallTask(siteid, requestxml, "GetAttributesXSL"));
			future.get();
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		if (site == null) {
			site = "US";
		}
		writelog("GetAttributesXSL/"+site+".xml", responsexml);
		
		String data     = ((BasicDBObject) resdbo.get("XSLFile")).getString("FileContent");
		String filename = ((BasicDBObject) resdbo.get("XSLFile")).getString("FileName");
		
		String decoded = new String(Base64.decodeBase64(data));
		
		writelog("GetAttributesXSL/"+site+"."+filename, decoded);
		
		return "";
	}
}
