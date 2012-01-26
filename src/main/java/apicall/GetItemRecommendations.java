package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import org.apache.commons.codec.binary.Base64;

public class GetItemRecommendations extends ApiCall {
	
	private String productid;
	
	public GetItemRecommendations() throws Exception {
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("DetailLevel",  "ReturnAll");
		//reqdbo.append("MessageID",    site);
		
		BasicDBObject item = new BasicDBObject();
		item.put("Site", "US");
		item.put("PrimaryCategory", new BasicDBObject("CategoryID", "9355"));
		
		BasicDBObject container = new BasicDBObject();
		container.put("Item", item);
		container.append("ListingFlow", "AddItem");
		container.append("Query", "blackberry 9810 unlocked");
		container.append("RecommendationEngine", "SuggestedAttributes");
		
		reqdbo.append("GetRecommendationsRequestContainer", container);
		
		String requestxml = convertDBObject2XML(reqdbo, "GetItemRecommendations");
		pool18.submit(new ApiCallTask(0, requestxml, "GetItemRecommendations"));
		
		writelog("GetItemRecommendations/test.xml", requestxml);
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetItemRecommendations/"+site+".xml", responsexml);

		return "";
	}
}
