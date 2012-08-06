package ebaytool.actions;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.mongodb.*;
import ebaytool.actions.BaseAction;
import ebaytool.actions.JsonAction;
import ebaytool.apicall.FetchToken;
import ebaytool.apicall.GetSellerList;
import ebaytool.apicall.GetSessionID;
import ebaytool.apicall.SetNotificationPreferences;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@ParentPackage("json-default")
public class PageAction extends BaseAction {
	
	protected LinkedHashMap<String, Object> initjson;
	protected SyndFeed feed;
	
	public PageAction() throws Exception {
	}
	
	public BasicDBObject getUser() {
		return user;
	}
	
	public LinkedHashMap<String, Object> getInitjson() {
		return initjson;
	}
	
	public SyndFeed getFeed() {
		return feed;
	}
	
	/* todo: session management in useraction json request */
	
	@Action(value="/page/index", results={@Result(name="alreadyloggedin",location="user.jsp"),
		                                  @Result(name="loggedin",type="redirect",location="/page/index")})
	public String execute() throws Exception {
		
		DBCollection coll = db.getCollection("users");
		
		BasicDBObject query = new BasicDBObject();
		user  = new BasicDBObject();
		
		String email = "";
		String password = "";
		
		if (session.get("email") != null) {
			
			query.put("email", session.get("email").toString());
			user = (BasicDBObject) coll.findOne(query);
			initjson = new LinkedHashMap<String, Object>();
			
			if (user != null) {
				session.put("email", user.get("email").toString());
				
				BasicDBObject hash = new BasicDBObject();
				DBObject row = db.getCollection("US.eBayDetails")
					.findOne(null, new BasicDBObject("SiteDetails", 1));
				BasicDBList sitedetails = (BasicDBList) row.get("SiteDetails");
				for (Object sitedbo : sitedetails) {
					hash.put(((BasicDBObject) sitedbo).getString("Site"), null);
				}
				
				initjson.put("hash", hash.toString());

				/* TimeZone IDs */
				BasicDBObject timezoneids = new BasicDBObject();
				for (String tzid : TimeZone.getAvailableIDs()) {
					//if (tzid.length() <= 2) continue;
					//if (!tzid.substring(0, 3).equals("Etc")) continue;
					if (tzid.length() <= 3) continue;
                    
					TimeZone tz = TimeZone.getTimeZone(tzid);
					timezoneids.put(tzid, tz.getDisplayName());
				}
				TreeMap<String,String> sortedids = new TreeMap<String,String>();
				sortedids.putAll((HashMap) timezoneids);

				BasicDBObject timezoneids2 = new BasicDBObject();
				timezoneids2.putAll(sortedids);
				
				initjson.put("timezoneids", timezoneids2);
				
				/* Schedule Days */
				initjson.put("scheduledays", getScheduleDays());
				
				return "alreadyloggedin";
			}
		}
		
		if (parameters.get("email") != null && parameters.get("password") != null) {
			
			email    = ((String[]) parameters.get("email"))[0];
			password = ((String[]) parameters.get("password"))[0];
			
			// todo: encrypt password
			// todo: check expiration date and time.
			query.put("email",    email);
			query.put("password", password);
			query.put("status",   "free trial");
			
			user = (BasicDBObject) coll.findOne(query);
			
			if (user != null) {
				session.put("email", user.get("email").toString());
				log.debug("loggedin: "+user.get("email").toString());
				return "loggedin";
			}
		}
		
		/* Read Blog RSS */
		URL feedUrl = new URL("http://listers.in/blog/feed/");
		SyndFeedInput input = new SyndFeedInput();
		feed = input.build(new XmlReader(feedUrl));
		
		return SUCCESS;
	}
	
	@Action(value="/page/signup_confirm", results={@Result(name="success",location="signup.jsp"),
		                                           @Result(name="signup_complete",type="redirect",location="/page/index")})
	public String signup_confirm() {
		
		// todo: also check ip address.
		String tmptoken  = ((String[]) parameters.get("t"))[0];
		
		BasicDBObject query = new BasicDBObject();
		query.put("status", "temporary registration");
		query.put("tmptoken", tmptoken);
		query.put("tmptoken_expiration", new BasicDBObject("$gt", basetimestamp));
		
		BasicDBObject tempuser = (BasicDBObject) db.getCollection("users").findOne(query);
		if (tempuser != null) {
			log.debug("temporary user found:"+tempuser.getString("email"));
			
			// 30 days expiration
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			String expiration = sdf.format(cal.getTime());
			
			// change user status
			BasicDBObject set = new BasicDBObject();
			set.put("status", "free trial");
			set.put("expiration", expiration);
			
			db.getCollection("users").update(query, new BasicDBObject("$set", set));
			
			session.put("email", tempuser.getString("email"));
			
			return "signup_complete";
		}
		
		return SUCCESS;
	}
	
	@Action(value="/page/logout", results={@Result(name="success",location="index.jsp")})
	public String logout() {
		
		session.remove("email");
		
		return SUCCESS;
	}

	@Action(value="/page/cancelaccount", results={@Result(name="success",location="index.jsp")})
	public String cancelaccount() {
		
		// drop items.[userid] collection
		db.getCollection("items."+user.getString("_id")).drop();
		
		// todo: move instead delete
		BasicDBObject query = new BasicDBObject();
		db.getCollection("users").remove(new BasicDBObject("email", user.getString("email")));
		
		session.remove("email");
		
		return SUCCESS;
	}
	
	// todo: forgot password
	// todo: change password
	
	@Action(value="/page/accept", results={@Result(name="success",type="redirect",location="/page/index")})
	public String accept() throws Exception {
		
		String username  = ((String[]) parameters.get("username"))[0];
		String email     = user.get("email").toString();
		String sessionid = user.get("sessionid").toString();
		
		/* FetchToken */
		Socket socket = new Socket("localhost", daemonport);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("FetchToken "+email+" "+sessionid+" "+username);
		in.readLine(); // wait
		
		out.close();
		in.close();
		socket.close();
		
		/* SetNotificationPreferences */
		socket = new Socket("localhost", daemonport);
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
		socket = new Socket("localhost", daemonport);
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
		
		// read XML
		String notifyxml = "";
		String line = "";
		BufferedReader br = request.getReader();
		while ((line = br.readLine()) != null) {
			notifyxml += line + "\n";
		}
		
		// convert XML to JSON
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		xmlSerializer.setTypeHintsEnabled(false);
		net.sf.json.JSON notifyjson = xmlSerializer.read(notifyxml);
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(notifyjson.toString());
		
		BasicDBObject soapenvbody = (BasicDBObject) dbobject.get("soapenv:Body");
		BasicDBObject itemresponse = (BasicDBObject) soapenvbody.get("GetItemResponse");
		BasicDBObject item = new BasicDBObject();
		BasicDBObject org = (BasicDBObject) itemresponse.get("Item");
		BasicDBObject mod = (BasicDBObject) org.copy();
		
		String eventname = itemresponse.getString("NotificationEventName");
		String xmltimestamp = itemresponse.getString("Timestamp").replaceAll("\\.", "_");
		
		String userid = ((BasicDBObject) org.get("Seller")).getString("UserID");
		String itemid = org.getString("ItemID");
		
		// save xml file
		FileWriter fstream = new FileWriter
			(basedir+"/logs/apicall/notification/"+userid+"."+eventname+"."+xmltimestamp+".xml");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(notifyxml);
		out.close();
		
		log.debug("notify: "+userid+" "+eventname+" "+itemid);
		
		// todo: merge to GetItem callback()?
		
		// get user info
		BasicDBObject userquery = new BasicDBObject();
		userquery.put("userids."+userid, new BasicDBObject("$exists", 1));
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne(userquery);
		
		DBCollection itemcoll = db.getCollection("items."+userdbo.getString("_id"));
		
		/* delete fields which is not necessary in AddItem families */
		BasicDBList movefields = (BasicDBList) configdbo.get("removefield");
		for (Object fieldname : movefields) {
			movefield(mod, fieldname.toString());
		}
		
		// todo: timezone doesn't work
		// todo: use GMT for logging?
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Japan/Tokyo"));
		Date now = new Date();
		String timestamp = sdf.format(now);
		
		BasicDBObject query = new BasicDBObject();
		query.put("org.Seller.UserID", userid);
		query.put("org.ItemID",        itemid);
		
		BasicDBObject set = new BasicDBObject();
		set.append("org", org);
		set.append("mod", mod);
		
		BasicDBObject update = new BasicDBObject();
		update.append("$set",  set);
		
		itemcoll.update(query, update, true, false);
		
		// replace item with latest data.
		item = (BasicDBObject) itemcoll.findOne(query);
		
		// todo: are ItemUnsold and ItemClosed same?
		if (eventname.equals("ItemUnsold")) {
			
			if (item.containsField("setting")) {
				
				BasicDBObject setting = (BasicDBObject) item.get("setting");
				BasicDBObject autorelist = (BasicDBObject) setting.get("autorelist");
				
				log.debug("ItemUnsold autorelist["+autorelist.getString("enabled")+"]");
				log.debug("ItemUnsold addbestoffer["+autorelist.getString("addbestoffer")+"]");
				if (autorelist.getString("enabled").equals("on")) {
					
					// Add best offer
					if (autorelist.getString("addbestoffer").equals("true")) {
						set = new BasicDBObject();
						set.put("mod.BestOfferDetails.BestOfferEnabled", "true");
						
						update = new BasicDBObject();
						update.put("$set", set);
						
						itemcoll.update(query, update, false, false, WriteConcern.SAFE);
						
						item = (BasicDBObject) itemcoll.findOne(query);
					}
					
					// todo: append log "relisting..."
					// todo: taskid => status ? status = relist, taskid = 9999999 ?
					String taskid = "autorelist_"+timestamp+"_"+itemid;
					
					update = new BasicDBObject();
					update.put("$set", new BasicDBObject("status", taskid));
					
					WriteResult result = itemcoll.update(query, update, false, true);
					
					Socket socket = new Socket("localhost", daemonport);
					PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);
					sout.println("RelistItem "+userdbo.getString("email")+" "+taskid);
					sout.close();
					socket.close();
				}
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * todo: duplicate in GetItem
	 * ref: https://jira.mongodb.org/browse/JAVA-260
	 */
	private void movefield(DBObject dbo, String field) throws Exception {
		
		String[] path = field.split("\\.", 2);
		
		if (!dbo.containsField(path[0])) return;
		
		String classname = dbo.get(path[0]).getClass().toString();
		
		/* leaf */
		if (path.length == 1) {
			dbo.removeField(path[0]);
			return;
		}
		
		/* not leaf */
		if (classname.equals("class com.mongodb.BasicDBList")) {
			BasicDBList orgdbl = (BasicDBList) dbo.get(path[0]);
			for (int i = 0; i < orgdbl.size(); i++) {
				movefield((DBObject) orgdbl.get(i), path[1]);
			}
		} else if (classname.equals("class com.mongodb.BasicDBObject")) {
			movefield((DBObject) dbo.get(path[0]), path[1]);
		}
		
		return;
	}
	
	private BasicDBObject getScheduleDays() {
		
		BasicDBObject days = new BasicDBObject();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE, MMM dd");
		sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
		sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		Calendar cal = Calendar.getInstance();
		for (int i=0; i<=21; i++) {
			cal.add(Calendar.DATE, 1);
			String val = sdf1.format(cal.getTime());
			String str = sdf2.format(cal.getTime());
			
			days.put(val, str);
		}
		
		return days;
	}
	
}
