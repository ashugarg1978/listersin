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
	public String execute() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		Map request = context.getParameters();
		Map session = context.getSession();
		
		DB db = new Mongo().getDB("ebay");
		DBCollection coll = db.getCollection("users");
		
		BasicDBObject query = new BasicDBObject();
		BasicDBObject user  = new BasicDBObject();
		
		String email = "";
		String password = "";
		if (session.get("email") != null) {
			query.put("email", session.get("email").toString());
			user = (BasicDBObject) coll.findOne(query);
			
			session.put("email", user.get("email").toString());
			
			return "loggedin";
		}
		if (request.get("email") != null && request.get("password") != null) {
			email    = ((String[]) request.get("email"))[0];
			password = ((String[]) request.get("password"))[0];
			
			query.put("email", email);
			//query.put("password", password);
			user = (BasicDBObject) coll.findOne(query);
			
			session.put("email", user.get("email").toString());
			
			return "loggedin";
		}
		
		return SUCCESS;
	}
	
	@Action(value="/logout", results={@Result(name="success",location="index.jsp")})
	public String logout() {
		
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();
		
		session.remove("email");
		
		return SUCCESS;
	}
}
