package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import org.apache.commons.lang.StringEscapeUtils;

public class GetProductSellingPages extends ApiCall {
	
	private String productid;
	
	public GetProductSellingPages() throws Exception {
	}
	
	public GetProductSellingPages(String productid) throws Exception {
		this.productid = productid;
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("DetailLevel",  "ReturnAll");
		reqdbo.append("MessageID",    "US");
		
		BasicDBObject product = new BasicDBObject();
		product.append("@productID", "117211:2:19518:2812280820:393123620:2046a9f8ca9131bbb5cea20e33aeaeb1:1:1:1:1418724563");
		product.append("CharacteristicsSet", new BasicDBObject("AttributeSetID", "1785"));
		reqdbo.append("Product", product);
		reqdbo.append("UseCase", "AddItem");
		
		String requestxml = convertDBObject2XML(reqdbo, "GetProductSellingPages");
		writelog("GetProductSellingPages/req.xml", requestxml);
		Future<String> future =
			pool18.submit(new ApiCallTask(0, requestxml, "GetProductSellingPages"));
		String result = future.get();
		
		return result;
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetProductSellingPages/"+site+".xml", responsexml);
		
		String data = resdbo.getString("ProductSellingPagesData");
		String decoded = StringEscapeUtils.unescapeHtml(data);
		
		writelog("GetProductSellingPages/decoded.xml", decoded);
		
		//String json = resdbo.toString();
		
		return responsexml;
	}
}
