package ebaytool.actions;

import com.mongodb.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ebaytool.apicall.GetSellerList;
import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

public class IndexAction extends ActionSupport {
	
	public BasicDBObject user;

	public BasicDBObject getUser() {
		return user;
	}
	
	/* todo: session management in useraction json request */
	
	@Action(value="/", results={@Result(name="loggedin",location="user.jsp")})
	public String execute() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		Map request = context.getParameters();
		Map session = context.getSession();
		
		DB db = new Mongo().getDB("ebay");
		DBCollection coll = db.getCollection("users");
		
		BasicDBObject query = new BasicDBObject();
		user  = new BasicDBObject();
		
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
	
	@Action(value="/receivenotify", results={@Result(name="success",location="receivenotify.jsp")})
	public String receivenotify() throws Exception {
		
		String notifyxml = "";
		String line = "";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		BufferedReader br = request.getReader();
		
		while ((line = br.readLine()) != null) {
			notifyxml += line + "\n";
		}
		
		GetSellerList gsl = new GetSellerList();
		gsl.parsenotifyxml(notifyxml);
		
		return SUCCESS;
	}
	
}
