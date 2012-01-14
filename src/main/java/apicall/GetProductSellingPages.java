package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.*;
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
		product.append("@productID", productid);
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
		
		
		// XML to HTML
		String logpath = "/var/www/ebaytool.jp/logs/apicall";
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer
			(new StreamSource(logpath+"/GetAttributesXSL/US.syi_attributes.xsl"));
		//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		transformer.transform
			(new StreamSource(logpath+"/GetProductSellingPages/decoded2.xml"),
			 new StreamResult(new FileOutputStream(logpath+"/GetProductSellingPages/decoded.html")));
		
		//String json = resdbo.toString();
		
		return responsexml;
	}
}
