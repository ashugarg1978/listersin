package ebaytool.actions;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.TimeFilter;
import com.ebay.sdk.call.GetSellerListCall;
import com.ebay.sdk.helper.Utils;
import com.ebay.sdk.helper.ui.ControlEntryTypes;
import com.ebay.sdk.helper.ui.ControlTagItem;
import com.ebay.sdk.helper.ui.GuiUtil;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.BestOfferDetailsType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.ListingDetailsType;
import com.ebay.soap.eBLBaseComponents.PaginationResultType;
import com.ebay.soap.eBLBaseComponents.PaginationType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.ParentPackage;

import org.apache.log4j.Logger;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

@ParentPackage("json-default")
public class UserAction extends ActionSupport {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	private static Mongo m;
	private static DB db;
	
	public UserAction() throws Exception {
		m = new Mongo();
		db = m.getDB("ebay");
	}
	
	private LinkedHashMap<String,Object> json;
	
	public LinkedHashMap<String,Object> getJson() {
		return json;
	}
	
	@Action(value="/items", results={@Result(name="success",type="json")})
	public String items() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<String,BasicDBObject> sellingquery = getsellingquery();
		
		/* connect to database */
		//Mongo m = new Mongo();
		//DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		/* handling post parameters */
		ActionContext context = ActionContext.getContext();
		Map request = (Map) context.getParameters();
		
		int limit  = Integer.parseInt(((String[]) request.get("limit"))[0]);
		int offset = Integer.parseInt(((String[]) request.get("offset"))[0]);
		
		/* query */
		BasicDBObject query = new BasicDBObject();
		query = sellingquery.get(((String[]) request.get("selling"))[0]);
		
		BasicDBObject field = new BasicDBObject();
		field.put("UserID", 1);
		field.put("ItemID", 1);
		field.put("Title", 1);
		field.put("Site", 1);
		field.put("StartPrice", 1);
		field.put("ListingDetails.ViewItemURL", 1);
		field.put("ListingDetails.EndTime", 1);
		field.put("PictureDetails.PictureURL", 1);
		field.put("SellingStatus.ListingStatus", 1);
		field.put("SellingStatus.CurrentPrice", 1);
		field.put("SellingStatus.CurrentPrice@currencyID", 1);
		field.put("status", 1);
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("ListingDetails.EndTime", -1);
		
		DBCursor cur = coll.find(query, field).limit(limit).skip(offset).sort(sort);
		while (cur.hasNext()) {
			DBObject item = cur.next();
			
			String id = item.get("_id").toString();
			//DBObject price = (DBObject) item.get("SellingStatus");
			item.put("price", ((DBObject) item.get("SellingStatus")).get("CurrentPrice").toString());
			
			json.put(id, item);
		}
		
		json.put("cnt", cur.count());
		//json.put("cnt", cur.size());
		
		for (Object k : request.keySet()) {
			json.put(k.toString(), request.get(k));
		}
		json.put("selling", request.get("selling"));
		
		return SUCCESS;
	}
	
	@Action(value="/summary", results={@Result(name="success",type="json")})
	public String summary() throws Exception {
		
		LinkedHashMap<String,BasicDBObject> selling = getsellingquery();
		
		String[] userids = {"testuser_aichi",
							"testuser_hal",
							"testuser_chiba",
							"testuser_tokyo",
							"testuser_kanagawa"};
		
		//Mongo m = new Mongo();
		//DB db = m.getDB("ebay");
		DBCollection coll = db.getCollection("items");
		
		json = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<String,Long> allsummary = new LinkedHashMap<String,Long>();
		for (String k : selling.keySet()) {
			BasicDBObject query = new BasicDBObject();
			query = selling.get(k);
			query.put("UserID", new BasicDBObject("$in", userids));
			
			Long cnt = coll.count(query);
			allsummary.put(k, cnt);
		}
		json.put("alluserids", allsummary);
		
		for (String u : userids) {
			LinkedHashMap<String,Long> summary = new LinkedHashMap<String,Long>();
			for (String k : selling.keySet()) {
				BasicDBObject query = new BasicDBObject();
				query = selling.get(k);
				query.put("UserID", u);
				
				Long cnt = coll.count(query);
				summary.put(k, cnt);
			}
			json.put(u, summary);
		}
		
		return SUCCESS;
	}

	private LinkedHashMap<String,BasicDBObject> getsellingquery() {
		
		BasicDBObject allitems  = new BasicDBObject();
		BasicDBObject scheduled = new BasicDBObject();
		BasicDBObject active    = new BasicDBObject();
		BasicDBObject sold      = new BasicDBObject();
		BasicDBObject unsold    = new BasicDBObject();
		BasicDBObject saved     = new BasicDBObject();
		BasicDBObject trash     = new BasicDBObject();
		
		allitems.put("deleted", 0);
		
		scheduled.put("deleted", 0);
		scheduled.put("ItemID", new BasicDBObject("$exists", 0));
		
		active.put("deleted", 0);
		active.put("ItemID", new BasicDBObject("$exists", 1));
		active.put("SellingStatus.ListingStatus", "Active");
		
		sold.put("deleted", 0);
		sold.put("ItemID", new BasicDBObject("$exists", 1));
		sold.put("SellingStatus.QuantitySold", new BasicDBObject("$gte", "1"));
		
		unsold.put("deleted", 0);
		unsold.put("ItemID", new BasicDBObject("$exists", 1));
		unsold.put("SellingStatus.ListingStatus", "Completed");
		unsold.put("SellingStatus.QuantitySold", "0");
		
		saved.put("deleted", 0);
		saved.put("ItemID", new BasicDBObject("$exists", 0));
		
		trash.put("deleted", 1);
		
		
		LinkedHashMap<String,BasicDBObject> selling = new LinkedHashMap<String,BasicDBObject>();
		selling.put("allitems",  allitems);
		selling.put("scheduled", scheduled);
		selling.put("active",    active);
		selling.put("sold",      sold);
		selling.put("unsold",    unsold);
		selling.put("saved",     saved);
		selling.put("trash",     trash);
		
		return selling;
	}
	
	@Action(value="/test")
	public String test() throws Exception {
		
		/*
		  <ServerUrl></ServerUrl>
		  <EpsServerUrl></EpsServerUrl>
		  <SignInUrl>https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn</SignInUrl>
		*/
		
		ApiContext apiContext = new ApiContext();
		apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");
		apiContext.setEpsServerUrl("https://api.sandbox.ebay.com/ws/api.dll");
		apiContext.setSignInUrl("https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn");
		
		
		ApiAccount ac = new ApiAccount();
		ac.setDeveloper("e60361cd-e306-496f-ad7d-ba7b688e2207");
		ac.setApplication("Yoshihir-1b29-4aad-b39f-1be3a37e06a7");
		ac.setCertificate("8118c1eb-e879-47f3-a172-2b08ca680770");
		
		ApiCredential apiCred = new ApiCredential();
		apiCred.seteBayToken("AgAAAA**AQAAAA**aAAAAA**BSF/TA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mFow6dj6x9nY+seQ**Q0UBAA**AAMAAA**JJDsfE+wQFK+EWdZ26syt/D3f1YfYL236IUFs4qTucxrgq0cSr5Pk0YD35LuwFQHLwwJ6Mbz3l+qsQZkF05V/WbkYgbvLu8HG6AtdtL6VdIo941fiW9+XFOvdMgdPI+zFnSp7HNec9+f0plQ7tutQUuu2/t5GmWEEFB3hP9jDVDZ5nD/uNY+332WybjTSwkUUwTQW74fcnEZJB8Afr+gYk9kt71yD5iZMMc6Pbj38FcrYPDLIrhIh5yC7S/Z3EGkUxqPqJuC0SO6iisuG4wG28zJ5vJqQ4lfa+M9VIlOhBADdo2KFaF37wVR0sakHBmmXRDRNfJ5M4DxHX0mW1Y9gHXrjO9IRuG77kjeCA+yvauYJU/QMuidcftlP/QwXZEd4oI1nj/fUyPZbtNMbichZn/5AmEfpJfD3tRkfEyW9QL0Xobw4i5QOi8MVSE2jh+3OjK4J0iKK4naQ9Xiv43uOjPTVbR6Ii3hgGBC+nOXVqgh2DmQpX4vznTl92f5WEyFuNZpLuiyNs3T/QbQ/7ps5POBAnfCwDQMSVBdvRPPOxcXwb8D8uPk2H71VarDxPFYzpew/KVBjpB1nhwXvXWT5uKyq86cYQSanEK7QxUDGu5PcTT2eYB/cZ4lDoviF135Z91ULc4WiVzUpeoHQ7et+eED7bw4VD0HeFfTKwqoQ0VZvPfs3IVhLGswtrlrWmklmMUBuy2d4GF3UInZOUvkkcKw7Sk/nmfLhueixz4iQkWDSi+wxeOumVk86mNoc93H");
		
		apiCred.setApiAccount(ac);
		apiContext.setApiCredential(apiCred);
		
		//GetSellerListRequestType req = new GetSellerListRequestType();
		
		GetSellerListCall api = new GetSellerListCall(apiContext);
		
		api.setUserID("testuser_hal");
		
		TimeFilter tf;
		tf = GuiUtil.getTimeFilterFromFields("2010-06-01", "2010-09-01");
		api.setStartTimeFilter(tf);
		
        ItemType[] retItems = api.getEntireSellerList();
		
		return SUCCESS;
	}
}
