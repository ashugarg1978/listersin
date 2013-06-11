package ebaytool.apicall;

import com.mongodb.*;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class RestoreToken extends ApiCall implements Callable {
	
  private String email;
  private String xmlfilename;
  
	public RestoreToken() throws Exception {
	}
  
	public RestoreToken(String[] args) throws Exception {
    this.email = args[0];
    this.xmlfilename = args[1];
	}
  
	public String call() throws Exception {
    
    String fullname = basedir + "/logs/apicall/FetchToken/" + xmlfilename;
    
    String responsexml = readfile(fullname);
    
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		String[] messages = resdbo.getString("CorrelationID").split(" ");
		String email = messages[0];
    if (!email.matches(".+@.+")) {
      email = getemailfromtokenmap(email);
    }
		String username = messages[1];
    
    log("Restore: " + email + " " + username);
    
    resdbo.put("username", username);
    
    resdbo.removeField("@xmlns");
    resdbo.removeField("Ack");
    resdbo.removeField("CorrelationID");
    resdbo.removeField("Version");
    resdbo.removeField("Build");
    
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		
		BasicDBObject update = new BasicDBObject();
		update.put("$pull", new BasicDBObject("userids2", new BasicDBObject("username", username)));
    
		db.getCollection("users").update(query, update);
    
		update = new BasicDBObject();
		update.put("$push", new BasicDBObject("userids2", resdbo));
    
		db.getCollection("users").update(query, update);
    
		return "";
    
  }
}
