package ebaytool.apicall;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import ebaytool.apicall.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.*;

import javax.net.ssl.HttpsURLConnection;
//import javax.xml.validation;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;


public class AddItems extends ApiCall implements Callable {
	
	private String userid;
	private String site;
	private String chunkidx;
	private String requestxml;
	
	public AddItems (String userid, String site, String chunkidx, String requestxml) {
		this.userid     = userid;
		this.site       = site;
		this.chunkidx   = chunkidx;
		this.requestxml = requestxml;
	}
	
	public BasicDBObject call() throws Exception {
		
		String logfile = "AIs.req."+userid+"."+site+"."+chunkidx+".xml";
		writelog(logfile, requestxml);
		
		/* XML Validation */
		/*
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(new File("/var/www/ebaytool/logs/apixml/"+logfile));
		
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		Source schemaFile = new StreamSource(new File("/var/www/ebaytool/data/ebaySvc.xsd"));
		Schema schema = factory.newSchema(schemaFile);
		
		Validator validator = schema.newValidator();
		
		try {
			validator.validate(new DOMSource(document));
		} catch (SAXException e) {
			System.out.println(e.toString());
		}
		*/
		
		/* call api */
		String responsexml = callapi(0, requestxml);
		
		
		writelog("AIs.res."+userid+"."+site+"."+chunkidx+".xml", responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		System.out.println(responsedbo.get("Ack").toString());
		
		return responsedbo;
	}
	
}
