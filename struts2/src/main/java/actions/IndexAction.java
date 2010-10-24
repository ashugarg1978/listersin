package ebaytool.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	/* todo: session management in useraction json request */
	
	@Action(value="/", results={@Result(name="loggedin",location="user.jsp")})
	public String execute() {
		
		ActionContext context = ActionContext.getContext();
		Map request = context.getParameters();
		Map session = context.getSession();
		
		DB db = new Mongo().getDB("ebay");
		DBCollection coll = new DBCollection("users");
		
		BasicDBObject query = new BasicDBObject();
		
		String email = "";
		String password = "";
		if (session.get("email") != null) {
			query.put("email", session.get("email").toString());
			BasicDBObject user = (BasicDBObject) coll.findOne(query);
		}
		if (request.get("email") != null && request.get("password") != null) {
			query.put("email",    session.get("email").toString());
			query.put("password", session.get("password").toString());
			BasicDBObject user = (BasicDBObject) coll.findOne(query);
		}
		
		if (email.equals("fd3s.boost@gmail.com")) {
			return "loggedin";
		}
		
		return SUCCESS;
	}
	
}
