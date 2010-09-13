package ebaytool;

import com.opensymphony.xwork2.ActionSupport;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

public class Ebay extends ActionSupport {
	
	private String msg;
	
    public String execute() throws Exception {
		
		Mongo m = new Mongo();
		
		DB db = m.getDB("ebay");
		
		DBCollection coll = db.getCollection("items");
		
		DBObject obj = coll.findOne();
		
		
		String title = obj.get("Title").toString();
		setMsg(title);
		
		return SUCCESS;
    }
	
	public void setMsg(String msg) {
		this.msg = msg;
		return;
	}
	
	public String getMsg() {
		return msg;
	}
}
