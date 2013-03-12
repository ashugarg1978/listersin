package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.xml.XMLSerializer;

public class GetMyMessages extends ApiCall implements Callable {
	
	private String email;
	private String userid;
  private String detaillevel;
  private String starttime;
  private String endtime;
  private String[] messageids;
  
	public GetMyMessages() throws Exception {
	}
	
	public GetMyMessages(String[] args) throws Exception {
    
		this.email  = args[0];
		this.userid = args[1];
		this.detaillevel = args[2];
    
    if (detaillevel.equals("ReturnMessages")) {
      
      messageids = new String[args.length - 3];
      
      for (int i=3; i<args.length; i++) {
        messageids[i-3] = args[i];
      }
      
    } else if (args.length == 5) {
      
      this.starttime = args[3];
      this.endtime = args[4];
      
    }
	}
  
	public String call() throws Exception {
    
		String token = gettoken(email, userid);
    
    BasicDBObject reqdbo = new BasicDBObject();
    reqdbo.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
    reqdbo.put("WarningLevel", "High");
    reqdbo.put("DetailLevel", detaillevel);
		reqdbo.put("MessageID", getnewtokenmap(email) + " " + userid);
    if (starttime != null) {
      reqdbo.put("StartTime", starttime);
    }
    if (endtime != null) {
      reqdbo.put("EndTime", endtime);
    }
    
    String requestxml = "";
    
    if (detaillevel.equals("ReturnMessages")) {
      
      reqdbo.put("MessageIDs", new BasicDBObject("MessageID", messageids));
      
      /* expand <e></e> elements */
      String jss = reqdbo.toString();
      
      JSONObject jso = JSONObject.fromObject(jss);
      
      JSONObject tmpids = jso.getJSONObject("MessageIDs");
      tmpids.getJSONArray("MessageID").setExpandElements(true);
      
      XMLSerializer xmls = new XMLSerializer();
      xmls.setObjectName("GetMyMessages");
      xmls.setNamespace(null, "urn:ebay:apis:eBLBaseComponents");
      xmls.setTypeHintsEnabled(false);
      
      requestxml = xmls.write(jso);
      
    } else {
      
      requestxml = convertDBObject2XML(reqdbo, "GetMyMessages");
      
    }
    
		writelog("GetMyMessages/" + userid + "." + detaillevel + ".req.xml", requestxml);
    
    Future<String> future = pool18.submit(new ApiCallTask(0, requestxml, "GetMyMessages"));
		future.get();
    
    // todo: next page (pagination)
    
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
    
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
    
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		String email  = getemailfromtokenmap(messages[0]);
		String userid = messages[1];
    
		writelog("GetMyMessages/" + userid + ".xml", responsexml);
    
		return "";
	}
  
}
