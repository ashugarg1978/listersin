package ebaytool.actions;

import com.mongodb.*;
import ebaytool.actions.BaseAction;
import ebaytool.apicall.FetchToken;
import ebaytool.apicall.GetSellerList;
import ebaytool.apicall.GetSessionID;
import ebaytool.apicall.SetNotificationPreferences;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
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
			
			// todo: password encryption
			BasicDBObject user = new BasicDBObject();
			user.put("email",    ((String[]) parameters.get("email"))[0]);
			user.put("password", ((String[]) parameters.get("password"))[0]);
			
			WriteResult result = db.getCollection("users").insert(user, WriteConcern.SAFE);
		}
		
		return SUCCESS;
	}
	
	/* todo: redirect to ebay */
	@Action(value="/page/addaccount", results={@Result(name="success",location="addaccount.jsp")})
	public String addaccount() throws Exception {
		
		/* GetSessionID */
		Socket socket = new Socket("localhost", 8181);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("GetSessionID "+session.get("email").toString());
		String sessionid = in.readLine();
		
		out.close();
		in.close();
		socket.close();
		
		user = new BasicDBObject();
		user.put("sessionid", sessionid);
		
		// todo: redirect
		//@Result(location="${url}", type="redirect")                                       
		//The ${url} means "use the value of the getUrl method"                             
								
		return SUCCESS;
	}
	
	@Action(value="/page/accept", results={@Result(name="success",location="user.jsp")})
	public String accept() throws Exception {
		
		String username  = ((String[]) parameters.get("username"))[0];
		String email     = user.get("email").toString();
		String sessionid = user.get("sessionid").toString();
		
		/* FetchToken */
		Socket socket = new Socket("localhost", 8181);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("FetchToken "+email+" "+sessionid+" "+username);
		in.readLine(); // DO WAIT
		
		out.close();
		in.close();
		socket.close();
		
		/* SetNotificationPreferences */
		socket = new Socket("localhost", 8181);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("SetNotificationPreferences "+email+" "+username);
		//in.readLine(); // don't wait
		
		out.close();
		in.close();
		socket.close();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String end   = formatter.format(cal.getTime());
		cal.add(Calendar.DATE, -119);
		String start = formatter.format(cal.getTime());
		
		/* GetSellerList */
		socket = new Socket("localhost", 8181);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("GetSellerList "+email+" "+username+" Start "+start+" "+end);
		//in.readLine(); // don't wait
		
		out.close();
		in.close();
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
		
		//GetSellerList gsl = new GetSellerList();
		//gsl.parsenotifyxml(notifyxml);
		
		JSONObject json = (JSONObject) new XMLSerializer().read(notifyxml);
		
		JSONObject item = json
			.getJSONObject("soapenv:Body")
			.getJSONObject("GetItemResponse")
			.getJSONObject("Item");
		
		String notificationeventname = json
			.getJSONObject("soapenv:Body")
			.getJSONObject("GetItemResponse")
			.get("NotificationEventName")
			.toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS");
		Date now = new Date();
		String timestamp = sdf.format(now).toString();
		
		FileWriter fstream = new FileWriter("/var/www/ebaytool.jp/logs/apicall/Notification/"
											+ notificationeventname+"."+timestamp+".xml");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(notifyxml);
		out.close();
		
		// todo: event name operation
		
		String userid = ((JSONObject) item.get("Seller")).get("UserID").toString();
		String itemid = item.get("ItemID").toString();
		
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = new BasicDBObject();
		query.put("ext.UserID", userid);
		query.put("ItemID",     itemid);
		
		// todo: auto relist
		
		if (notificationeventname.equals("ItemEnded")) {
			
		}
		
		if (false) {
			
			BasicDBObject ext = new BasicDBObject();
			ext.put("UserID", userid);
			ext.put("labels", new BasicDBList());
			
			/* convert JSON to DBObject */
			DBObject dbobject = (DBObject) com.mongodb.util.JSON.parse(item.toString());
			dbobject.put("ext", ext);
			
			
			BasicDBObject update = new BasicDBObject();
			update.put("$set", dbobject);
			
			/* insert into mongodb */
			coll.findAndRemove(query);
			coll.update(query, update, true, true);
			
		}
		
		
		return SUCCESS;
	}

}
