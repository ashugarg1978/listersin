package ebaytool.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.ParentPackage;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

@ParentPackage("json-default")
public class UserAction extends ActionSupport {
	
	private String msg;
	private Map<String,Object> json;
	
    public String execute() throws Exception {
		
		setMsg("oiuh");
		
		return SUCCESS;
	}
	
	@Action(value="/items",
			results={@Result(name="success",type="json")}
			)
	public String items() throws Exception {
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = new BasicDBObject();
		query.put("deleted", 0);
		query.put("UserID", "testuser_hal");
		
		json = new HashMap<String,Object>();
		
		DBCursor cur = coll.find(query).limit(40);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			String id = item.get("_id")+"";
			json.put(id, item);
		}
		
		return SUCCESS;
	}
	
	public String summary() throws Exception {
		
		String[] userids = {"testuser_hal",
							"testuser_chiba",
							"testuser_aichi",
							"testuser_tokyo",
							"testuser_kanagawa"};
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = new BasicDBObject();
		query.put("deleted", 0);
		query.put("UserID", new BasicDBObject("$in", userids));
		
		json = new HashMap<String,Object>();
		
		Long cnt = coll.count(query);
		json.put("cnt", cnt);
		
		return SUCCESS;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
		return;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public Map<String,Object> getJson() {
		return json;
	}
}
