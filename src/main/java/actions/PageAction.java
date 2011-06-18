package ebaytool.actions;

import com.mongodb.*;
import ebaytool.actions.BaseAction;
import ebaytool.apicall.FetchToken;
import ebaytool.apicall.GetSellerList;
import ebaytool.apicall.GetSessionID;
import java.io.*;
import java.net.Socket;
import java.util.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@ParentPackage("json-default")
public class PageAction extends BaseAction {
	
	public PageAction() throws Exception {
	}
	
	public BasicDBObject getUser() {
		return user;
	}
	
	/* todo: session management in useraction json request */
	
	@Action(value="/page/index", results={@Result(name="loggedin",location="user.jsp")})
	public String execute() throws Exception {
		
		DBCollection coll = db.getCollection("users");
		
		BasicDBObject query = new BasicDBObject();
		user  = new BasicDBObject();
		
		String email = "";
		String password = "";
		
		
		if (session.get("email") != null) {
			
			query.put("email", session.get("email").toString());
			user = (BasicDBObject) coll.findOne(query);
			
			if (user != null) {
				session.put("email", user.get("email").toString());
				return "loggedin";
			}
		}
		
		if (parameters.get("email") != null && parameters.get("password") != null) {
			
			email    = ((String[]) parameters.get("email"))[0];
			password = ((String[]) parameters.get("password"))[0];
			
			query.put("email", email);
			query.put("password", password);
			user = (BasicDBObject) coll.findOne(query);
			
			if (user != null) {
				session.put("email", user.get("email").toString());
				return "loggedin";
			}
		}
		
		return SUCCESS;
	}
	
	@Action(value="/page/logout", results={@Result(name="success",location="index.jsp")})
	public String logout() {
		
		session.remove("email");
		
		return SUCCESS;
	}
	
	@Action(value="/page/register", results={@Result(name="success",type="json")})
	public String register() throws Exception {
		
		// todo: password validation, check existing user record.
		if (parameters.get("email") != null
			&& parameters.get("password") != null
			&& parameters.get("password2") != null) {
			
			BasicDBObject user = new BasicDBObject();
			user.put("email",    ((String[]) parameters.get("email"))[0]);
			user.put("password", ((String[]) parameters.get("password"))[0]);
			
			DB db = new Mongo().getDB("ebay");
			WriteResult result = db.getCollection("users").insert(user, WriteConcern.SAFE);
		}
		
		return SUCCESS;
	}
	
	/* todo: redirect to ebay */
	@Action(value="/page/addaccount", results={@Result(name="success",location="addaccount.jsp")})
	public String addaccount() throws Exception {
		
		GetSessionID gsi = new GetSessionID();
		String sessionid = gsi.call();
		
		BasicDBObject query = new BasicDBObject();
		query.put("email", session.get("email").toString());
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("sessionid", sessionid));
		
		db.getCollection("users").update(query, update);
		
		user  = new BasicDBObject();
		user.put("sessionid", sessionid);
		
		return SUCCESS;
	}
	
	@Action(value="/page/accept", results={@Result(name="success",location="user.jsp")})
	public String accept() throws Exception {
		
		String username  = ((String[]) parameters.get("username"))[0];
		
		String email     = user.get("email").toString();
		String sessionid = user.get("sessionid").toString();
		
		// todo: call via ebaytoold
		FetchToken ft = new FetchToken(email, sessionid, username);
		String result = ft.call();
		
		/* SetNotificationPreferences */
		Socket socket = new Socket("localhost", 8181);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("SetNotificationPreferences "+email+" "+username);
		out.close();
		socket.close();
		
		return SUCCESS;
	}
	
	@Action(value="/page/receivenotify", results={@Result(name="success",location="receivenotify.jsp")})
	public String receivenotify() throws Exception {
		
		String notifyxml = "";
		String line = "";
		
		BufferedReader br = request.getReader();
		
		while ((line = br.readLine()) != null) {
			notifyxml += line + "\n";
		}
		
		GetSellerList gsl = new GetSellerList();
		gsl.parsenotifyxml(notifyxml);
		
		return SUCCESS;
	}

}
