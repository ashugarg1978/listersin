package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class AddScheduledItems extends ApiCall implements Callable {

    private String from;
    private String to;
	
	public AddScheduledItems() throws Exception {
	}
    
	public AddScheduledItems(String from, String to) throws Exception {
        this.from = from.replace("T", " ");
        this.to = to.replace("T", " ");
	}
    
	public String call() throws Exception {
        
        // todo: check running thread.
        
        DBCollection users = db.getCollection("users");
        DBCursor cursor = users.find().snapshot();
		while (cursor.hasNext()) {
            BasicDBObject user = (BasicDBObject) cursor.next();
            DBCollection items = db.getCollection("items."+user.getString("_id"));
            
            BasicDBObject query = new BasicDBObject();
            query.put("setting.schedule", new BasicDBObject("$gte", from).append("$lte", to));
            
            DBCursor cur = items.find(query).sort(new BasicDBObject("setting.schedule", 1));
			if (cur.count() == 0) {
                continue;
            }
            
            System.out.println("email:"+user.getString("email"));
            while (cur.hasNext()) {
                BasicDBObject item = (BasicDBObject) cur.next();
                BasicDBObject setting = (BasicDBObject) item.get("setting");
                System.out.println("schedule: "+setting.getString("schedule")
                                   +" ("+item.getString("UserID")+")");
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            Date now = new Date();
            String timestamp = sdf.format(now);
            
            String email = user.getString("email");
            String taskid = "addscheduleditems_"+timestamp;

            BasicDBObject set = new BasicDBObject();
            set.put("status", taskid);

            BasicDBObject unset = new BasicDBObject();
            unset.put("setting.schedule", 1);
            
            BasicDBObject update = new BasicDBObject();
            update.put("$set", set);
            update.put("$unset", unset);
            
            WriteResult result = items.update(query, update, false, true);
            
            /* call AddItems */
            Future<String> future = pool18.submit(new AddItems(email, taskid));
            String responsexml = future.get();
        }
        
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
        
		return "";
	}
}
