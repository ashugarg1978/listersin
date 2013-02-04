package ebaytool.actions;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.mongodb.*;
import ebaytool.actions.BaseAction;
//import ebaytool.actions.JsonAction;
import java.io.*;
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
		
		/*
		for (String s : session.keySet()) {
			log.debug(s + ":" + session.get(s).toString());
		}
		*/
		
		String locale = request.getLocale().toString();
		if (session.containsKey("WW_TRANS_I18N_LOCALE")) {
			locale = session.get("WW_TRANS_I18N_LOCALE").toString();
		}
		log.debug("locale: " + request.getLocale().toString() + " -> " + locale);
		
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
		String rssurl;
		if (locale.equals("ja") || locale.equals("ja_JP")) {
			rssurl = "http://feedblog.ameba.jp/rss/ameblo/listersin/rss20.xml";
		} else {
			rssurl = "http://listers.in/blog/feed/";
		}
		log.debug(rssurl);
		URL feedUrl = new URL(rssurl);
		SyndFeedInput input = new SyndFeedInput();
		feed = input.build(new XmlReader(feedUrl));
		
		return SUCCESS;
	}
	
	@Action(value="/page/signup_confirm", results={@Result(name="success",location="signup.jsp"),
		                                           @Result(name="signup_complete",type="redirect",location="/page/index")})
	public String signup_confirm() {
		
		// todo: also check ip address.
		String tmptoken = ((String[]) parameters.get("t"))[0];
		
		BasicDBObject query = new BasicDBObject();
		query.put("status", "temporary registration");
		query.put("tmptoken", tmptoken);
		query.put("tmptoken_expiration", new BasicDBObject("$gt", basetimestamp));
		
		BasicDBObject tempuser = (BasicDBObject) db.getCollection("users").findOne(query);
		if (tempuser != null) {
			log.debug("temporary user found:"+tempuser.getString("email"));
      
			// 30 days expiration
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
      sdf.setLenient(false);
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

	@Action(value="/page/reset_password", results={@Result(name="success",location="resetpassword.jsp")})
	public String reset_password() {
		
		// todo: also check ip address.
		String tmptoken = ((String[]) parameters.get("t"))[0];
    
		BasicDBObject query = new BasicDBObject();
		query.put("status", "free trial");
		query.put("tmptoken", tmptoken);
		query.put("tmptoken_expiration", new BasicDBObject("$gt", basetimestamp));
    
		user = (BasicDBObject) db.getCollection("users").findOne(query);
		if (user == null) {
      
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
    String[] args = {"FetchToken", email, sessionid, username};
    String result = writesocket(args); // wait
        
		/* SetNotificationPreferences */
    args = new String[]{"SetNotificationPreferences", email, username};
    writesocket_async(args); // not wait
        
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String end   = formatter.format(cal.getTime());
		cal.add(Calendar.DATE, -29); // max 119
		String start = formatter.format(cal.getTime());
        
    // todo: don't wait following api call.
    
		/* GetSellerList */
    args = new String[]{"GetSellerList", email, username, "Start", start, end};
    writesocket_async(args); // not wait
    
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		end   = formatter.format(cal.getTime());
		cal.add(Calendar.DATE, -30); // what is max?
		start = formatter.format(cal.getTime());
    
		/* GetMemberMessages */
    args = new String[]{"GetMemberMessages", email, username,
                        start+"T00:00:00.000Z",
                        end+"T00:00:00.000Z"};
    writesocket_async(args); // not wait
    
		return SUCCESS;
	}
	
	@Action(value="/page/receivenotify", results={@Result(name="success",location="receivenotify.jsp")})
	public String receivenotify() throws Exception {
		
		// read XML
		String notifyxml = "";
		String line = "";
		BufferedReader br = request.getReader();
		while ((line = br.readLine()) != null) {
			if (line.indexOf("<soapenv:") >= 0
				|| line.indexOf("</soapenv:") >= 0
				|| line.indexOf("<ebl:") >= 0
				|| line.indexOf("</ebl:") >= 0) {
			} else {
				notifyxml += line + "\n";
			}
		}
		
		// save xml file
		FileWriter fstream0 = new FileWriter(basedir+"/logs/apicall/notification/res.xml");
		BufferedWriter out0 = new BufferedWriter(fstream0);
		out0.write(notifyxml);
		out0.close();
		
		// convert XML to JSON
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		xmlSerializer.setTypeHintsEnabled(false);
		net.sf.json.JSON notifyjson = xmlSerializer.read(notifyxml);
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(notifyjson.toString());
		
		String eventname = dbobject.getString("NotificationEventName");
		String userid = dbobject.getString("RecipientUserID");
		log.debug("notify: "+userid+" "+eventname);
		
		String savedir = basedir + "/logs/apicall/notification/" + basetimestamp.substring(0,10);
		log.debug("savedir:"+savedir);
		
		/* make log directory for each call */
		if (!(new File(savedir)).exists()) {
			new File(savedir).mkdir();
		}
		
		// save xml file
		FileWriter fstream = new FileWriter(savedir+"/"+userid+"."+eventname+"."+basetimestamp+".xml");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(notifyxml);
		out.close();
		
		//BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne
		//	(new BasicDBObject("userids."+userid, new BasicDBObject("$exists", true)));
    
		BasicDBObject userdbo = (BasicDBObject) db.getCollection("users").findOne
			(new BasicDBObject("userids2", userid));
    
		String itemid = "";
		
		if (eventname.equals("AskSellerQuestion")) {
			
			BasicDBObject mm   = (BasicDBObject) dbobject.get("MemberMessage");
			BasicDBObject mme  = (BasicDBObject) mm.get("MemberMessageExchange");
			BasicDBObject item = (BasicDBObject) mme.get("Item");
			itemid = item.getString("ItemID");
      
			/* GetMemberMessage */
			String[] args = {"GetMemberMessages", userdbo.getString("email"), userid, itemid};
			String result = writesocket(args);
			
		} else if (eventname.indexOf("Item") == 0) {
			
			BasicDBObject item = (BasicDBObject) dbobject.get("Item");
			itemid = item.getString("ItemID");
            
			/* GetItem */
      String[] args = {"GetItem", userdbo.getString("email"), userid, itemid};
      String result = writesocket(args);
            
		}
		
		// todo: are ItemUnsold and ItemClosed same?
		if (eventname.equals("ItemUnsold")) {
			
			DBCollection itemcoll = db.getCollection("items."+userdbo.getString("_id"));
			
			BasicDBObject query = new BasicDBObject();
			query.put("UserID", userid);
			query.put("org.ItemID", itemid);
			
			BasicDBObject item = (BasicDBObject) itemcoll.findOne(query);
			
			if (item.containsField("setting")) {
				
				BasicDBObject setting = (BasicDBObject) item.get("setting");
				BasicDBObject autorelist = (BasicDBObject) setting.get("autorelist");
				
				log.debug("ItemUnsold autorelist["+autorelist.getString("enabled")+"]");
				log.debug("ItemUnsold addbestoffer["+autorelist.getString("addbestoffer")+"]");
				if (autorelist.getString("enabled").equals("on")) {
					
					// Add best offer
					if (autorelist.getString("addbestoffer").equals("true")) {
						BasicDBObject set = new BasicDBObject();
						set.put("mod.BestOfferDetails.BestOfferEnabled", "true");
						
						BasicDBObject update = new BasicDBObject();
						update.put("$set", set);
						
						itemcoll.update(query, update, false, false, WriteConcern.SAFE);
						
						item = (BasicDBObject) itemcoll.findOne(query);
					}
					
					// todo: append log "relisting..."
					// todo: taskid => status ? status = relist, taskid = 9999999 ?
					String taskid = "autorelist_"+basetimestamp+"_"+itemid;
					
					BasicDBObject update = new BasicDBObject();
					update.put("$set", new BasicDBObject("status", taskid));
					
					WriteResult writeresult = itemcoll.update(query, update, false, true);
					
                    String[] args = {"RelistItem", userdbo.getString("email"), taskid};
                    String result = writesocket(args);
                    
				}
			}
		}
		
		return SUCCESS;
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
