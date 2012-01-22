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
	private String attributesetid;
	
	public GetProductSellingPages() throws Exception {
		this.productid      = "117984:2:21418:2973043373:399582263:04d3441a3b1502e7d47cb87912668538:1:1:1:1422410458";
		this.attributesetid = "5918";
	}
	
	public GetProductSellingPages(String productid, String attributesetid) throws Exception {
		this.productid      = productid;
		this.attributesetid = attributesetid;
	}
	
	public String call() throws Exception {
		
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
		reqdbo.append("WarningLevel", "High");
		reqdbo.append("DetailLevel",  "ReturnAll");
		reqdbo.append("MessageID",    "US");
		
		BasicDBObject product = new BasicDBObject();
		product.append("@productID", productid);
		product.append("CharacteristicsSet", new BasicDBObject("AttributeSetID", attributesetid));
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
		
		//String productid = ((BasicDBObject) resdbo.get("Product")).getString("@id");
		//log("productid: "+productid);
		
		decoded = decoded.replace(" & ", " &amp; "); // todo: fix escape
		decoded = decoded.replace("<Products>", "");
		decoded = decoded.replace("</Products>", "");
		decoded = decoded.replace("<Product id=", "<eBay id=");
		decoded = decoded.replace("</Product>", "</eBay>");
		//decoded = decoded.replace("</Product>", "<API.XSL.Overrides><Show><ItemSpecificsOnly/></Show></API.XSL.Overrides></eBay>");
		
		BasicDBObject decodeddbo = convertXML2DBObject(decoded);
		BasicDBObject attrs = (BasicDBObject) decodeddbo.get("Attributes");
		BasicDBObject attr = (BasicDBObject) attrs.get("AttributeSet");
		String attributesetid =	attr.getString("@id");

		log("attributesetid:"+attributesetid);
		
		decoded = decoded.replace("<DataElements>", "<SelectedAttributes><AttributeSet id='"+attributesetid+"'/></SelectedAttributes><DataElements>");
		
		//decoded = decoded.replaceAll("&", "&amp;");
		//decoded = decoded.replace("<eBay>", "<eBay><SelectedAttributes><AttributeSet id='5918' categoryId='171485'/></SelectedAttributes>");
		
		writelog("GetProductSellingPages/decoded.xml", decoded);
		//writelog("GetProductSellingPages/new.xml", newxml);
		
		
		// XML to HTML
		String logpath = "/var/www/ebaytool.jp/logs/apicall";
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer
			(new StreamSource(logpath+"/GetAttributesXSL/US.syi_attributes.xsl"));
		//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		transformer.transform
			(new StreamSource(logpath+"/GetProductSellingPages/decoded.xml"),
			 new StreamResult(new FileOutputStream(logpath+"/GetProductSellingPages/decoded.html")));
		
		//String json = resdbo.toString();
		String html = readfile(logpath+"/GetProductSellingPages/decoded.html");
		
		return html;
	}
}
