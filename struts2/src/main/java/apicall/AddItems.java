package ebaytool.apicall;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.mongodb.util.*;

import ebaytool.apicall.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.*;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import javax.xml.XMLConstants;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

import org.bson.types.ObjectId;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class AddItems extends ApiCall implements Callable {
	
	private String userid;
	private String site;
	private String chunkidx;
	private String[] itemids;
	private String requestxml;
	
	public AddItems (String userid, String site, String chunkidx, String[] itemids, 
					 String requestxml) {
		this.userid     = userid;
		this.site       = site;
		this.chunkidx   = chunkidx;
		this.itemids    = itemids;
		this.requestxml = requestxml;
	}
	
	public BasicDBObject call() throws Exception {
		
		String logfile = "AIs.req."+userid+"."+site+"."+chunkidx+".xml";
		writelog(logfile, requestxml);
		
		for (int x=0; x<5; x++) {
			System.out.println("call "+userid+"."+site+"."+chunkidx+"."+x+":"+itemids[x]);
		}
		
		// todo: why Country error occures?
		/* XML Validation */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder parser = dbf.newDocumentBuilder();
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
		
		/* call api */
		String responsexml = callapi(0, requestxml);
		
		writelog("AIs.res."+userid+"."+site+"."+chunkidx+".xml", responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		System.out.println(responsedbo.get("Ack").toString());
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		BasicDBList dbl = (BasicDBList) responsedbo.get("AddItemResponseContainer");
		for (Object item : dbl) {
			
			int correlationid =
				Integer.parseInt(((BasicDBObject) item).getString("CorrelationID"));
			
			String itemid    = ((BasicDBObject) item).getString("ItemID");
			String starttime = ((BasicDBObject) item).getString("StartTime");
			String endtime   = ((BasicDBObject) item).getString("EndTime");
			
			BasicDBObject upditem = new BasicDBObject();
			upditem.put("ItemID", itemid);
			upditem.put("ListingDetails.StartTime", starttime);
			upditem.put("ListingDetails.EndTime", endtime);
			upditem.put("ext.status", "");
			
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(itemids[correlationid-1]));
			
			BasicDBObject update = new BasicDBObject();
			update.put("$set", upditem);
			
			WriteResult result = coll.update(query, update);
			System.out.println(correlationid+" : "+result);
			
		}
		
		return responsedbo;
	}
	
}
