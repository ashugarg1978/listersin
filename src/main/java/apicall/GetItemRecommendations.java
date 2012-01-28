package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import org.apache.commons.codec.binary.Base64;

public class GetItemRecommendations extends ApiCall {
	
	private String categoryid;
	private String query;
	
	public GetItemRecommendations() throws Exception {
	}
	
	public GetItemRecommendations(String categoryid, String query) throws Exception {
		this.categoryid = categoryid;
		this.query = query;
	}
	
	public String call() throws Exception {
		
		//String tmptoken = "AgAAAA**AQAAAA**aAAAAA**tzUjTw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wNmYujAZKEpwqdj6x9nY+seQ**bwYBAA**AAMAAA**ePoopeUk1B6r20McoOgQ3e3JkxioqhJPeYW7xflFSKuxbImCPWBqPM5fzpk7WfBCBCACRN7uJyt2hE4Jz33tmOddLnuwzaUIEyhYoIU5kPT2R8J6OvhJzgHT5sLmwkiX1LEa84JfPPgshxK6PjaQhfAb0l+YSTxz9e86OXbUawtXg3nMu33h1aYSD0W0pdSp65KcTXm0VRZimdGHcEZ0laQeSoSG5vxZW8qVLbHcYFuYjySx1Tyu0StMAFMWBrwIES+oX5e1wkoZgPojLDZBlgus2ZW8mLckz6Kdozw9k+uVCp+v5GJXXGEWRabhsqqd7D/aYNNeramp3ZgQx/jVioRZBWUa529JShBkKz8kbCbx3I4cjjRKFcNhDHStxZHCs0qm06J2Be5/9V/KzT36Rx+rM4Nu4BMSFJ2XiG0Fn32nMjkglapQaQSYkL+osspwqUfRU2pDeKhHXZbNjBpJBYRiA7WytYAPEhNyuvOQmD9iVydWY6YKVNaRGUIjVhppmUZeiCb7Tyl4o/xCZzoPpr0haEorAKmnBlge9bC+1HmlyK9OhzKlB7vvAi5ew9H7DTq5KClwdlR2N8lQhKt+5578qtuBDgIZv8P3ri51ycy8NSFQvqMa/0E3UcI3eCuJl+VPBXbw5Y4JtQl8H5NDfoeA04mB5Ln5YK2D8DMJW4Vy6uVfDbeFFrziZ2AYLsqUUC6XdpTkEW2hh6XQsKCtGI3A5A65PFGEzoRQSM/La4th7bnKcsEQp/NiNS7mhpHH";
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("DetailLevel",  "ReturnAll");
		//reqdbo.append("MessageID",    site);
		
		BasicDBObject item = new BasicDBObject();
		item.put("Site", "US");
		item.put("Currency", "USD");
		item.put("PrimaryCategory", new BasicDBObject("CategoryID", categoryid));
		item.put("Title", query);
		
		BasicDBObject container = new BasicDBObject();
		container.put("Item", item);
		container.append("ListingFlow", "AddItem");
		container.append("Query", query);
		container.append("RecommendationEngine", "SuggestedAttributes");
		
		reqdbo.append("GetRecommendationsRequestContainer", container);
		
		String requestxml = convertDBObject2XML(reqdbo, "GetItemRecommendations");

		/*
		String logpath = "/var/www/ebaytool.jp/logs/apicall";
		requestxml = "";
		FileReader fr = new FileReader(logpath+"/GetItemRecommendations/request.xml");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			requestxml += line;
		}
		br.close();
		*/
		
		pool18.submit(new ApiCallTask(0, requestxml, "GetItemRecommendations"));
		
		writelog("GetItemRecommendations/test.xml", requestxml);
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetItemRecommendations/"+site+".xml", responsexml);
		
		log(responsexml);
		
		return "";
	}
}
