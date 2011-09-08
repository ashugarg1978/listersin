package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class GetCategorySpecifics extends ApiCall {
	
	public GetCategorySpecifics() throws Exception {
	}
	
	public String call() throws Exception {
		
		DBCursor cur = db.getCollection("US.eBayDetails.SiteDetails").find();
		Integer cnt = cur.count();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			
			BasicDBObject reqdbo = new BasicDBObject();
			reqdbo.append("RequesterCredentials", new BasicDBObject("eBayAuthToken", admintoken));
			reqdbo.append("WarningLevel", "High");
			reqdbo.append("DetailLevel",  "ReturnAll");
			reqdbo.append("CategorySpecificsFileInfo", "true");
			reqdbo.append("MessageID",    site);
			
			String requestxml = convertDBObject2XML(reqdbo, "GetCategorySpecifics");
			pool18.submit(new ApiCallTask(siteid, requestxml, "GetCategorySpecifics"));
			
			break;
		}
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		String site = resdbo.getString("CorrelationID");
		writelog("GetCategorySpecifics/"+site+".xml", responsexml);
		
		String taskid = resdbo.getString("TaskReferenceID");
		String fileid = resdbo.getString("FileReferenceID");

		// todo: check existing downloaded file.
		String dlfilename = taskid+"."+fileid+"dat";
		
		
		/* downloadFile */
		BasicDBObject reqdbo = new BasicDBObject();
		reqdbo.append("taskReferenceId", taskid);
		reqdbo.append("fileReferenceId", fileid);
		
		JSONObject jso = JSONObject.fromObject(reqdbo.toString());
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("downloadFileRequest");
		xmls.setNamespace(null, "http://www.ebay.com/marketplace/services");
		xmls.setTypeHintsEnabled(false);
		String requestxml = xmls.write(jso);
		
		//String requestxml = convertDBObject2XML(reqdbo, "downloadFile");
		pool18.submit(new ApiCallTask(0, requestxml, "downloadFile"));
		
		writelog("GetCategorySpecifics/dl."+site+".xml", requestxml);
		
		return "";
	}
}
