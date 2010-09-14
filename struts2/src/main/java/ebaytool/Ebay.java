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
		
		
		json = new HashMap<String,Object>();
		
		Mongo m = new Mongo();
		
		DB db = m.getDB("ebay");
		
		DBCollection coll = db.getCollection("items");
		
		DBObject obj = coll.findOne();
		
		json.put("aa", obj);
		
		
		String ss = obj.get("id") + ":";
		setMsg(ss);
		//setMsg("in execute() method");
		
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
