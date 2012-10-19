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
    
		DBCollection coll = db.getCollection("users");
    
		BasicDBObject sort = new BasicDBObject();
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
        user.put("created_local", sdf.format(created));
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
}
