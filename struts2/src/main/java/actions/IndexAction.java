package ebaytool.actions;

import com.mongodb.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ebaytool.apicall.GetSellerList;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		
		//FileReader fr = new FileReader("/var/www/ebaytool/logs/receivenotify.log");
		//BufferedReader br = new BufferedReader(fr);
		
		while ((line = br.readLine()) != null) {
			notifyxml += line + "\n";
		}
		
		/* save xml file */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS");
		Date now = new Date();
		String logfile = "NTF."+sdf.format(now).toString()+".xml";
		writelog(logfile, notifyxml);
		
		GetSellerList gsl = new GetSellerList();
		gsl.parsenotifyxml(notifyxml);
		
		return SUCCESS;
	}
	
	public void writelog(String filename, String content) throws Exception {
		
		FileWriter fstream = new FileWriter("/var/www/ebaytool/logs/apixml/"+filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		out.close();
		
		return;
	}
}
