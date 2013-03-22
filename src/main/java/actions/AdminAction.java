package ebaytool.actions;

import com.mongodb.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.bson.types.ObjectId;

public class AdminAction extends BaseAction {
  
  protected BasicDBList users = new BasicDBList();
  
	public AdminAction() throws Exception {
	}
  
  public BasicDBList getUsers() {
    return users;
  }
  
	@Action(value="/admin/index", results={@Result(name="success",location="index.jsp")})
	public String execute() throws Exception {
    
    session.put("admin", "1");
    
		DBCollection coll = db.getCollection("users");
    
		BasicDBObject sort = new BasicDBObject();
		sort.put("lastused", -1);
		sort.put("_id", -1);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
		sdf.setLenient(false);
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    
		DBCursor cursor = coll.find().sort(sort);
		while (cursor.hasNext()) {
			BasicDBObject user = (BasicDBObject) cursor.next();
      
      if (user.containsField("created")) {
        Date created = sdf.parse(user.getString("created").replace("T", " ").replace(".000Z", ""));
        sdf.setTimeZone(TimeZone.getTimeZone("Japan"));
        user.put("created_local", sdf.format(created).replace("+0900", ""));
      }
      
      if (user.containsField("lastused")) {
        Date lused = sdf.parse(user.getString("lastused").replace("T", " ").replace(".000Z", ""));
        sdf.setTimeZone(TimeZone.getTimeZone("Japan"));
        user.put("lastused_local", sdf.format(lused).replace("+0900", ""));
      }
			
      users.add(user);
    }    
    
    return SUCCESS;
  }  
  
	@Action(value="/admin/deleteuser", results={@Result(name="success",location="index.jsp")})
  public String deleteuser() {
    
		String id = ((String[]) parameters.get("id"))[0];
    log.debug("deleteuser id:"+id);
    
    BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
    
    db.getCollection("users").remove(query);
    
    return SUCCESS;
  }
	
  @Action(value="/admin/signin", results={@Result(name="success",type="redirect",location="/page/index")})
  public String signin() {
    
    String email = ((String[]) parameters.get("email"))[0];
    
		session.remove("email");
    session.put("email", email);
    
    return SUCCESS;
  }
	
  @Action(value="/admin/updatesummary", results={@Result(name="success",type="redirect",location="/admin/index")})
  public String updatesummary() {
		
		BasicDBObject summaries = new BasicDBObject();
		
		DBCursor cursor = db.getCollection("users").find();
		while (cursor.hasNext()) {
			
			BasicDBObject user = (BasicDBObject) cursor.next();
			
			if (!user.containsField("userids2")) continue;
			
      DBCollection itemcoll = db.getCollection("items."+user.getString("_id"));
      
      // build the $projection operation
      ArrayList<String> arrdate = new ArrayList<String>();
      arrdate.add("$org.ListingDetails.EndTime");
      arrdate.add(basetimestamp.replace(" ", "T").replace("+0000", ".000Z"));
      
      // Now the $group operation
      DBObject groupkey = new BasicDBObject();
      groupkey.put("UserID", "$UserID");
      groupkey.put("ListingStatus", "$org.SellingStatus.ListingStatus");
      groupkey.put("ended", new BasicDBObject("$lt", arrdate));
      
      DBObject groupfields = new BasicDBObject();
      groupfields.put("_id", groupkey);
      groupfields.put("items", new BasicDBObject("$sum", 1));
      
      DBObject group = new BasicDBObject("$group", groupfields);
			
			BasicDBObject summary = new BasicDBObject();
			
      // run aggregation
      AggregationOutput output = itemcoll.aggregate(group);
      for (Object o: output.results()) {
				
        BasicDBObject tmpo = (BasicDBObject) o;
				BasicDBObject tmpid = (BasicDBObject) tmpo.get("_id");
				
				String userid = tmpid.getString("UserID");
				
				String listingstatus = "empty";
				if (tmpid.containsField("ListingStatus")) {
					listingstatus = tmpid.getString("ListingStatus");
				}
				
				String ended = tmpid.getString("ended");
				
				if (!summary.containsField(userid)) {
					summary.put(userid, new BasicDBObject());
				}
				
				BasicDBObject tmpsummary = (BasicDBObject) summary.get(userid);
				if (!tmpsummary.containsField(listingstatus)) {
					tmpsummary.put(listingstatus, new BasicDBObject());
				}
				
				BasicDBObject tmpls = (BasicDBObject) tmpsummary.get(listingstatus);
				if (ended.equals("true")) {
					tmpls.put("truez", tmpo.getInt("items"));
				} else {
					tmpls.put("falsez", tmpo.getInt("items"));
				}
				
      }
			
			summaries.put(user.getString("email"), summary);
		}
		
		for (String email : summaries.keySet()) {
			
			BasicDBObject summary = (BasicDBObject) summaries.get(email);
			
      for (String userid : summary.keySet()) {
				
				BasicDBObject query = new BasicDBObject();
				query.put("email", email);
				query.put("userids2.username", userid);
				
				BasicDBObject update = new BasicDBObject();
				update.put("$set", new BasicDBObject("userids2.$.summary", summary.get(userid)));
				
				db.getCollection("users").update(query, update);
      }
			
    }    
		
    return SUCCESS;
  }

}
