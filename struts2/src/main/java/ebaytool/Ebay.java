package ebaytool;

import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

public class Ebay extends ActionSupport {
	
	private String msg;
	private Map<String,Object> json;
	
    public String execute() throws Exception {
		
		Mongo m = new Mongo();
		DB db = m.getDB("ebay");

		BasicDBObject query = new BasicDBObject();
		query.put("deleted", 0);
		
		json = new HashMap<String,Object>();
		
		DBCollection coll = db.getCollection("items");
		DBCursor cur = coll.find().limit(10);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			String id = (String)item.get("id");
			json.put(id, item);
		}
		
		setMsg("foo");
		
		return SUCCESS;
	}
	
	public String items() throws Exception {
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
