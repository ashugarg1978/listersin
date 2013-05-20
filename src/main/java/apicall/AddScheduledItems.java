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
  
	public AddScheduledItems(String[] args) throws Exception {
    this.from = args[0];
    this.to   = args[1];
	}
  
	public String call() throws Exception {
    
    // todo: check running thread.
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setLenient(false);
    
    Date datefrom = sdf.parse(from);
    Date dateto   = sdf.parse(to);
    
    DBCollection users = db.getCollection("users");
    DBCursor cursor = users.find().snapshot();
		while (cursor.hasNext()) {
      
      BasicDBObject user = (BasicDBObject) cursor.next();
      DBCollection items = db.getCollection("items."+user.getString("_id"));
      
      BasicDBObject query = new BasicDBObject();
      query.put("opt.ScheduleType", "listersin");
      query.put("opt.ScheduleTime", new BasicDBObject("$gte", datefrom).append("$lte", dateto));
      
      DBCursor cur = items.find(query).sort(new BasicDBObject("opt.ScheduleTime", 1));
			if (cur.count() == 0) continue;
      
      log("email:" + user.getString("email"));
      while (cur.hasNext()) {
        BasicDBObject item = (BasicDBObject) cur.next();
        BasicDBObject opt = (BasicDBObject) item.get("opt");
        log("schedule: "+opt.getString("ScheduleTime")+" ("+item.getString("UserID")+")");
      }
      
      sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
      Date now = new Date();
      String timestamp = sdf.format(now);
      
      String email = user.getString("email");
      String taskid = "addscheduleditems_"+timestamp;
      
      BasicDBObject set = new BasicDBObject();
      set.put("status", taskid);
      
      BasicDBObject unset = new BasicDBObject();
      unset.put("opt.ScheduleType", 1);
      unset.put("opt.ScheduleTime", 1);
      
      BasicDBObject update = new BasicDBObject();
      update.put("$set", set);
      update.put("$unset", unset);
      
      WriteResult result = items.update(query, update, false, true);
      
      /* call AddItems */
      String[] args = {email, taskid};
      Future<String> future = pool18.submit(new AddItems(args));
      String responsexml = future.get();
    }
    
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
    
		return "";
	}
}
