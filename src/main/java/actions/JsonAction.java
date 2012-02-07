package ebaytool.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import com.mongodb.*;
import ebaytool.actions.BaseAction;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
//import org.json.JSONObject;
//import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.bson.types.ObjectId;

@ParentPackage("json-default")
@Result(name="success",type="json")
public class JsonAction extends BaseAction {
	
	//protected Logger log = Logger.getLogger(this.getClass());
	
	public JsonAction() throws Exception {
	}
	
	private LinkedHashMap<String,Object> json;
	
	public LinkedHashMap<String,Object> getJson() {
		return json;
	}
	
	@Action(value="/json/hash")
	public String hash() throws Exception {
		
		if (true) {
			//json = getinitdata();
			log.debug("JsonAction.hash() called.");
			return SUCCESS;
		}
		
		json = new LinkedHashMap<String,Object>();
		
		// todo: SiteDetails in each country?
		DBCollection coll = db.getCollection("US.eBayDetails.SiteDetails");
		DBCursor cur = coll.find();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			
			LinkedHashMap<String,Object> hash = new LinkedHashMap<String,Object>();
			
			hash.put("SiteID", siteid.toString());
			
			hash.put("category", children(site, 0));
			((LinkedHashMap) hash.get("category")).put("grandchildren", new ArrayList());
			((LinkedHashMap) hash.get("category")).put("features",      new ArrayList());
			
			String[] category0 = new String[1];
			category0[0] = "0";
			hash.put("Categories",             grandchildren2(site, category0, 1, null));
			
			hash.put("ShippingType",           shippingtype(site));
			hash.put("ShippingServiceDetails", shippingservicedetails(site));
			hash.put("DispatchTimeMaxDetails", dispatchtimemaxdetails(site));
			hash.put("ShippingPackageDetails", shippingpackagedetails(site));
			hash.put("CountryDetails",         countrydetails(site));
			hash.put("CurrencyDetails",        currencydetails(site));
			
			hash.put("ShippingLocationDetails",
					 getebaydetails(site+".eBayDetails.ShippingLocationDetails",
									"ShippingLocation",
									"Description"));
			
			json.put(site, hash);
		}
		
		return SUCCESS;
	}
	
	public LinkedHashMap<String,Object> initdata() {
		
		log.debug("start initdata");
		LinkedHashMap<String,Object> initdata = new LinkedHashMap<String,Object>();
		if (true) {
			//return initdata;
		}
		
		// todo: SiteDetails in each country?
		DBCollection coll = db.getCollection("US.eBayDetails.SiteDetails");
		DBCursor cur = coll.find();
		while (cur.hasNext()) {
			DBObject row = cur.next();
			
			String  site   = row.get("Site").toString();
			Integer siteid = Integer.parseInt(row.get("SiteID").toString());
			
			LinkedHashMap<String,Object> hash = new LinkedHashMap<String,Object>();
			
			hash.put("SiteID", siteid.toString());
			
			//hash.put("category", children(site, 0));
			//((LinkedHashMap) hash.get("category")).put("grandchildren", new ArrayList());
			//((LinkedHashMap) hash.get("category")).put("features",      new ArrayList());
			
			log.debug(site+" gc2");
			String[] category0 = new String[1];
			category0[0] = "0";
			//hash.put("Categories",             grandchildren2(site, category0, 1, null));
			
			log.debug(site+" shippingtype");
			hash.put("ShippingType",           shippingtype(site));
			hash.put("ShippingServiceDetails", shippingservicedetails(site));
			hash.put("DispatchTimeMaxDetails", dispatchtimemaxdetails(site));
			hash.put("ShippingPackageDetails", shippingpackagedetails(site));
			hash.put("CountryDetails",         countrydetails(site));
			hash.put("CurrencyDetails",        currencydetails(site));
			
			log.debug(site+" shippinglocation");
			hash.put("ShippingLocationDetails",
					 getebaydetails(site+".eBayDetails.ShippingLocationDetails",
									"ShippingLocation",
									"Description"));
			
			initdata.put(site, hash);
			//break;
		}
		log.debug("done initdata");
		
		return initdata;
	}
	
	@Action(value="/json/items")
	public String items() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		LinkedHashMap<String,Object> items = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<String,BasicDBObject> sellingquery = getsellingquery();
		
		/* connect to database */
		DBCollection coll = db.getCollection("items");
		
		/* handling post parameters */
		int limit = 40;
		int offset = 0;
		if (parameters.containsKey("limit"))
			limit = Integer.parseInt(((String[]) parameters.get("limit"))[0]);
		if (parameters.containsKey("offset"))
			offset = Integer.parseInt(((String[]) parameters.get("offset"))[0]);
		
		/* query */
		BasicDBObject query = getFilterQuery();
		
		/* field */
		BasicDBObject field = new BasicDBObject();
		
		field.put("mod.Title",  1);
		field.put("mod.Site",   1);
		field.put("mod.PictureDetails.PictureURL",   1);
		field.put("mod.PictureDetails.GalleryURL",   1);
		
		field.put("org.Seller.UserID", 1);
		field.put("org.ItemID", 1);
		field.put("org.StartPrice", 1);
		field.put("org.ListingDetails.ViewItemURL",  1);
		field.put("org.ListingDetails.EndTime",      1);
		field.put("org.SellingStatus.ListingStatus", 1);
		
		BasicDBObject sort = new BasicDBObject();
		sort.put("ListingDetails.EndTime", 1);
		
        Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Japan/Tokyo"));
		SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
		Date now = new Date();
		//String today = sdf.format(calendar.getTime());
		
		DBCursor cur = coll.find(query, field).limit(limit).skip(offset).sort(sort);
		json.put("cnt", cur.count());
		while (cur.hasNext()) {
			
			DBObject item = cur.next();
			DBObject org = (DBObject) item.get("org");
			
			String id = item.get("_id").toString();
			
			/* price */
			DBObject sp = (DBObject) org.get("StartPrice");
			Float startprice = Float.parseFloat(sp.get("#text").toString());
			item.put("price", sp.get("@currencyID")+" "+startprice.intValue());
			
			/* endtime */
			if (((DBObject) org.get("ListingDetails")).containsField("EndTime")) {
				formatter.applyPattern("yyyy-MM-dd");
				String endtime = ((DBObject) org.get("ListingDetails")).get("EndTime").toString();
				Date dfendtime = sdf.parse(endtime.replace("T", " ").replace(".000Z", ""));
				item.put("dfnow", sdf.format(now));
				item.put("dfend", sdf.format(dfendtime));
				if (formatter.format(now).equals(formatter.format(dfendtime))) {
					formatter.applyPattern("h:mm a");
					//formatter.applyPattern("MM-dd HH:mm");
				} else {
					formatter.applyPattern("MMM d");
					//formatter.applyPattern("MM-dd HH:mm");
				}
				item.put("endtime", formatter.format(dfendtime));
			}
			
			item.removeField("_id");
			
			/* add */
			items.put(id, item);
		}
		json.put("items", items);
		
		return SUCCESS;
	}
	
	@Actions({
		@Action(value="/json/item"),
		@Action(value="/json/save-item")
	})
	public String item() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		DBCollection coll = db.getCollection("items");
		
		/* handling post parameters */
		String id = parameters.get("id")[0];
		
		/* query */
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		/* execute query */
		BasicDBObject item = (BasicDBObject) coll.findOne(query);
		BasicDBObject mod = (BasicDBObject) item.get("org");
		item.put("id", item.get("_id").toString());
		
		/* categorypath */
		// todo: update old categoryid to current active categoryid
		Integer categoryid =
			Integer.parseInt(((BasicDBObject) mod.get("PrimaryCategory")).getString("CategoryID"));
		categoryid = mapcategoryid(item.getString("Site"), categoryid);
		((BasicDBObject) mod.get("PrimaryCategory")).put("CategoryID", categoryid.toString());
		
		List path = categorypath(mod.getString("Site"), categoryid);
		item.put("categorypath", path);
		
		/* grandchildren */
		String[] pathstr = new String[path.size()+1];
		pathstr[0] = "0";
		for (int i = 0; i < path.size(); i++) {
			pathstr[i+1] = path.get(i).toString();
		}
		BasicDBObject children2 = children2(mod.getString("Site"), pathstr);
		
		LinkedHashMap<Integer,String> path2 = categorypath2(mod.getString("Site"), categoryid);
		
		String categoryname = "";
		for (Integer cid : path2.keySet()) {
			if (!categoryname.equals("")) categoryname += " > ";
			categoryname += path2.get(cid);
		}
		item.put("categorypath2", path2);
		item.put("categoryname", categoryname);
		
		/* shipping */
		item.put("shippingtype", shippingtypelabel2(item));
		
		/*
		if (item.containsField("ShippingDetails")) {
			BasicDBObject sd = (BasicDBObject) mod.get("ShippingDetails");
			if (sd.containsField("ShippingType")) {
				String st = sd.get("ShippingType").toString();
			}
		}
		*/
		
		/* remove fields */
		item.removeField("_id");
		
		BasicDBObject ebaydetails =
			(BasicDBObject) db.getCollection(mod.getString("Site")+".eBayDetails").findOne();
		
		BasicDBObject categoryfeatures =
			(BasicDBObject) db.getCollection(mod.getString("Site")+".CategoryFeatures").findOne();
		
		json.put("item", item);
		json.put("Categories", children2.get("Categories"));
		json.put("eBayDetails", ebaydetails);
		json.put("CategoryFeatures", categoryfeatures);
		
		return SUCCESS;
	}
	
	@Action(value="/json/save")
	public String save() throws Exception {
		
		String id   = ((String[]) parameters.get("id"))[0];
		String form = ((String[]) parameters.get("json"))[0];
		log.debug("save id:"+id);
		log.debug(form);
		
		BasicDBObject item = (BasicDBObject) com.mongodb.util.JSON.parse(form);
		
		/* CategoryName */
		/*
		Integer categoryid =
			Integer.parseInt(((BasicDBObject) item.get("PrimaryCategory")).getString("CategoryID"));
		
		LinkedHashMap<Integer,String> categorypath =
			categorypath2(item.getString("Site"), categoryid);
		
		String categoryname = "";
		for (Integer cid : categorypath.keySet()) {
			if (!categoryname.equals("")) categoryname += ":";
			categoryname += categorypath.get(cid);
		}
		((BasicDBObject) item.get("PrimaryCategory")).put("CategoryName", categoryname);
		*/
		
		/* ShippingType */
		BasicDBObject ssd = (BasicDBObject) ((BasicDBObject) item.get("ext")).get("shippingtype");
		LinkedHashMap<String,LinkedHashMap> smap = shippingmap();
		for (String st : smap.keySet()) {
			
			String dmst = ((Map) smap.get(st)).get("domestic").toString();
			String intl = ((Map) smap.get(st)).get("international").toString();
			
			if (ssd.getString("domestic").equals(dmst)
				&& ssd.getString("international").equals(intl)) {
				
				((BasicDBObject) item.get("ShippingDetails")).put("ShippingType", st);
				break;
			}
		}
		
		/* AttributeSetArray */
		BasicDBObject attributesetarray = new BasicDBObject();
		BasicDBObject attributeset = new BasicDBObject();
		attributeset.put("@attributeSetID", item.getString("vcsid"));
		
		BasicDBList attributes = new BasicDBList();
		String removeattr = "";
		Pattern p = Pattern.compile("^attr(|_t|_d|_required_)([0-9]+)_([0-9]+)(|_m|_d|_y|_c)$");
		for (String key : item.keySet()) {
			
			Matcher m = p.matcher(key);
			//if (m.matches() == false) continue;
			
			while (m.find()) {
				log.debug("save key:"+key+" ("+m.group(3)+")");
				
				BasicDBObject attribute = new BasicDBObject();
				attribute.put("@attributeID", m.group(3));
				
				if (m.group(1).equals("")) {
					if (item.get(key).getClass().toString()
						.equals("class com.mongodb.BasicDBList")) {
						BasicDBList values = new BasicDBList();
						for (Object tmpvalue : (BasicDBList) item.get(key)) {
							values.add(new BasicDBObject("ValueID", tmpvalue.toString()));
						}
						attribute.put("Value", values);
					} else {
						attribute.put("Value", new BasicDBObject("ValueID", item.getString(key)));
					}
				} else if (m.group(1).equals("_t")) {
					attribute.put("Value", new BasicDBObject("ValueLiteral", item.getString(key)));
				}
				
				attributes.add(attribute);
				removeattr += key+"|";
			}
		}
		attributeset.put("Attribute", attributes);
		attributesetarray.put("AttributeSet", attributeset);
		item.put("AttributeSetArray", attributesetarray);
		
		/* Delete */
		item.removeField("ButtonLoad");
		item.removeField("aus_form_changed");
		item.removeField("vcsid");
		
		String[] removeattrs = removeattr.split("\\|");
		try {
			for (String key : removeattrs) {
				log.debug("remove: "+key);
				item.removeField(""+key);
			}
		} catch (Exception e) {
			log.debug(e.toString());
		}
		
		DBCollection coll = db.getCollection("items");
		DBCollection coll2 = db.getCollection("itemsdiff");
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		BasicDBObject before = (BasicDBObject) coll.findOne(query);
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", item);
		
		WriteResult result = coll.update(query, update);
		
		coll2.remove(query);
		
		item.put("_id", new ObjectId(id));
		coll2.insert(item);
		
		BasicDBObject after  = (BasicDBObject) coll2.findOne(query);
		
		/* save before and after file for diff */
		DiffLogger dl = new DiffLogger();
		dl.savediff(id, before.toString(), after.toString());
		
		if (false) {
			/* for debug */
			json = new LinkedHashMap<String,Object>();
			json.put("result", result);
			json.put("update", item);
			return SUCCESS;
		} else {
			/* chaining action to item() */
			return "item";
		}
	}
	
	@Action(value="/json/copy")
	public String copy() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		DBCollection coll = db.getCollection("items");
		
		BasicDBObject query = getFilterQuery();
		
		BasicDBObject field = new BasicDBObject();
		field.put("ItemID", 0);
		
		Integer d = 0;
		
		ArrayList<DBObject> newdblist = new ArrayList<DBObject>();
		//DBCursor cur = coll.find(query, field).snapshot();
		DBCursor cur = coll.find(query).snapshot();
		while (cur.hasNext()) {
			DBObject item = cur.next();
			
			item.removeField("_id");
			item.removeField("ItemID");
			item.removeField("SellingStatus");
			
			newdblist.add(item);
		}
		
		coll.insert(newdblist, WriteConcern.SAFE);
		
		if (true) {
			return SUCCESS;
		}
		
		// todo: sort result
		//DBCursor cur = coll.find(query, field).snapshot();
		//json.put("count", cur.count());
		List<DBObject> dblist = coll.find(query, field).snapshot().toArray();
		//while (cur.hasNext()) {
		for (DBObject item : dblist) {
			//DBObject item = cur.next();
			String id = item.get("_id").toString();
			
			item.removeField("_id");
			item.removeField("SellingStatus");
			
			BasicDBList dbl = (BasicDBList) ((BasicDBObject) item.get("ext")).get("labels");
			dbl.add("copied");

			if (true) {
				json.put("item", item);
				return SUCCESS;
			}
			
			coll.insert(item, WriteConcern.SAFE);
			d++;
		}
		json.put("d", d.toString());
		
		return SUCCESS;
	}
	
	@Action(value="/json/delete")
	public String delete() throws Exception {

		json = new LinkedHashMap<String,Object>();
		
		BasicDBObject query = getFilterQuery();
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("ext.deleted", 1));
		
		WriteResult result = db.getCollection("items").update(query, update, false, true);
		
		json.put("result", result);
		
		return SUCCESS;
	}
	
	@Action(value="/json/add")
	public String add() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
		for (String id : (String[]) parameters.get("id")) {
			ids.add(new ObjectId(id));
		}
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new BasicDBObject("$in", ids));
		query.put("ext.status", new BasicDBObject("$ne", "add"));
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("ext.status", "add"));
		
		WriteResult result = db.getCollection("items").update(query, update, false, true);
		
		json.put("result", result);
		
		Socket socket = new Socket("localhost", 8181);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("AddItems "+session.get("email"));
		out.close();
		socket.close();
		
		return SUCCESS;
	}
	
	@Action(value="/json/relist")
	public String relist() throws Exception {
		
		json = new LinkedHashMap<String,Object>();
		
		ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
		for (String id : (String[]) parameters.get("id")) {
			ids.add(new ObjectId(id));
		}
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new BasicDBObject("$in", ids));
		query.put("ext.status", new BasicDBObject("$ne", "relist"));
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("ext.status", "relist"));
		
		WriteResult result = db.getCollection("items").update(query, update, false, true);
		
		json.put("result", result);
		
		Socket socket = new Socket("localhost", 8181);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("RelistItem "+session.get("email"));
		out.close();
		socket.close();
		
		return SUCCESS;
	}
	
	@Action(value="/json/revise")
	public String revise() throws Exception {
		
		
		return SUCCESS;
	}
	
	@Action(value="/json/import")
	public String importitems() throws Exception {
		
		Socket socket = new Socket("localhost", 8181);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("GetSellerList");
		out.close();
		socket.close();
		
		return SUCCESS;
	}
	
	@Action(value="/json/end")
	public String end() throws Exception {
		
		ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
		for (String id : (String[]) parameters.get("id")) {
			ids.add(new ObjectId(id));
		}
		
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new BasicDBObject("$in", ids));
		
		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("ext.status", "end"));
		
		WriteResult result = db.getCollection("items").update(query, update, false, true);
		
		Socket socket = new Socket("localhost", 8181);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("EndItems");
		out.close();
		socket.close();
		
		return SUCCESS;
	}
	
	/* don't use getxxxxx as method name */
	@Action(value="/json/productsearchresults")
	public String productsearchresults() throws Exception {
		
		String words = "";
		String csid  = "";

		if (parameters.containsKey("ProductSearch.QueryKeywords")) {
			words = ((String[]) parameters.get("ProductSearch.QueryKeywords"))[0];
		} else {
			return SUCCESS;
		}
		
		if (parameters.containsKey("ProductSearch.CharacteristicSetIDs.ID")) {
			csid  = ((String[]) parameters.get("ProductSearch.CharacteristicSetIDs.ID"))[0];
		} else {
			return SUCCESS;
		}
		
		/* GetProductSearchResultID */
		Socket socket = new Socket("localhost", 8181);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("GetProductSearchResults "+csid+" "+words);
		String result = in.readLine();
		
		out.close();
		in.close();
		socket.close();
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		xmlSerializer.setTypeHintsEnabled(false);
		net.sf.json.JSON tmpjson = xmlSerializer.read(result);
		BasicDBObject dbo = (BasicDBObject) com.mongodb.util.JSON.parse(tmpjson.toString());
		
		json = new LinkedHashMap<String,Object>();
		json.put("result", dbo);
		
		return SUCCESS;
	}

	@Action(value="/json/productsellingpages")
	public String productsellingpages() throws Exception {
		
		String productid      = ((String[]) parameters.get("productid"))[0];
		String attributesetid = ((String[]) parameters.get("attributesetid"))[0];
		
		Socket socket = new Socket("localhost", 8181);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
		out.println("GetProductSellingPages "+productid+" "+attributesetid);
		String result = in.readLine();
		
		out.close();
		in.close();
		socket.close();
		
		/*
		Pattern p = Pattern.compile("<script[^>]*>(.*?)</script>");
		Matcher m = p.matcher(result);
		while(m.find()) {
			log.debug(m.group());
        }
		*/
		result = result.replaceAll("_L_I_N_E_F_E_E_D_", "\n");
		
		json = new LinkedHashMap<String,Object>();
		json.put("result", result);
		
		return SUCCESS;
	}
	
	@Action(value="/json/parsesellingpage")
	public String parsesellingpage() throws Exception {
		
		String attributesetid = ((String[]) parameters.get("vcsid"))[0];
		
		Pattern p = Pattern.compile("^attr(|_t|_d|_required_)([0-9]+)_([0-9]+)(|_m|_d|_y|_c)$");
			
		JSONObject attributeset = new JSONObject();
		attributeset.put("@id", attributesetid);
		
		String xml = "<SelectedAttributes>\n";
		xml += "<AttributeSet id=\""+attributesetid+"\">\n";
		
		for (String key : parameters.keySet()) {
			
			log.debug("key:"+key);
			Matcher m = p.matcher(key);
			//if (m.matches() == false) continue;
			
			while (m.find()) {
				
				xml += "<Attribute id=\""+m.group(3)+"\">\n";
				for (String val : (String[]) parameters.get(key)) {
					if (m.group(1).equals("_t")) {
						xml += "<Value><Name>"+val+"</Name></Value>\n";
					} else {
						xml += "<Value id=\""+val+"\"/>\n";
					}
				}
				xml += "</Attribute>\n";
			}
		}
		
		xml += "</AttributeSet>\n";
		xml += "</SelectedAttributes>\n";
		
		/*
		JSONArray attributes = new JSONArray();
		for (String key : parameters.keySet()) {
			
			JSONArray values = new JSONArray();
			values.setExpandElements(true);
			for (String val : (String[]) parameters.get(key)) {
				JSONObject value = new JSONObject();
				value.put("@id", val);
				values.add(value);
			}
			
			JSONObject attribute = new JSONObject();
			attribute.put("Value", values);
			attributes.add(attribute);
		}
		//attributes.setExpandElements(true);
		attributeset.put("Attribute", attributes);
		
		JSONObject selectedattributes = new JSONObject();
		selectedattributes.put("AttributeSet", attributeset);
		attributeset.getJSONArray("Attribute").setExpandElements(true);
		
		//JSONObject jso = JSONObject.fromObject(attributeset);
		//jso.getJSONArray("Attribute").setExpandElements(true);
		
		XMLSerializer xmls = new XMLSerializer();
		xmls.setObjectName("SelectedAttributes");
		xmls.setTypeHintsEnabled(false);
		String xml = xmls.write(selectedattributes);
		*/
		
		log.debug(xml);
		
		// XML to HTML
		String logpath = "/var/www/ebaytool.jp/logs/apicall";
		
		
		String decoded = "";
		FileReader fr = new FileReader(logpath+"/GetProductSellingPages/decoded.xml");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			decoded = decoded + "\n" + line;
		}
		br.close();

		decoded = decoded.replace("<SelectedAttributes><AttributeSet id='1785'/></SelectedAttributes>", xml);
		
		FileWriter fstream = new FileWriter(logpath+"/GetProductSellingPages/decoded_selected.xml");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(decoded);
		out.close();
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer
			(new StreamSource(logpath+"/GetAttributesXSL/US.syi_attributes.xsl"));
		
		transformer.transform
			(new StreamSource(logpath+"/GetProductSellingPages/decoded_selected.xml"),
			 new StreamResult(new FileOutputStream
							  (logpath+"/GetProductSellingPages/decoded_selected.html")));
		
		decoded = "";
		fr = new FileReader(logpath+"/GetProductSellingPages/decoded_selected.html");
		br = new BufferedReader(fr);
		while ((line = br.readLine()) != null) {
			decoded = decoded + "\n" + line;
		}
		br.close();
		
		json = new LinkedHashMap<String,Object>();
		json.put("result", decoded);
		
		return SUCCESS;
	}
	
	@Action(value="/json/summary")
	public String summary() throws Exception {
		json = summarydata();
		return SUCCESS;
	}
	
	public LinkedHashMap<String,Object> summarydata() throws Exception {
		LinkedHashMap<String,Object> summarydata = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<String,BasicDBObject> selling = getsellingquery();
		
		ArrayList<String> userids = new ArrayList<String>();
		for (Object userid : ((BasicDBObject) user.get("userids")).keySet()) {
			userids.add(userid.toString());
		}
		
		DBCollection coll = db.getCollection("items");
		
		LinkedHashMap<String,Long> allsummary = new LinkedHashMap<String,Long>();
		for (String k : selling.keySet()) {
			BasicDBObject query = new BasicDBObject();
			query = selling.get(k);
			query.put("org.Seller.UserID", new BasicDBObject("$in", userids));
			
			Long cnt = coll.count(query);
			allsummary.put(k, cnt);
		}
		summarydata.put("alluserids", allsummary);
		
		for (String u : userids) {
			LinkedHashMap<String,Long> summary = new LinkedHashMap<String,Long>();
			for (String k : selling.keySet()) {
				BasicDBObject query = new BasicDBObject();
				query = selling.get(k);
				query.put("org.Seller.UserID", u);
				
				Long cnt = coll.count(query);
				summary.put(k, cnt);
			}
			summarydata.put(u, summary);
		}
		
		return summarydata;
	}

	private LinkedHashMap<String,BasicDBObject> getsellingquery() {
		
		BasicDBObject allitems  = new BasicDBObject();
		BasicDBObject scheduled = new BasicDBObject();
		BasicDBObject active    = new BasicDBObject();
		BasicDBObject sold      = new BasicDBObject();
		BasicDBObject unsold    = new BasicDBObject();
		BasicDBObject saved     = new BasicDBObject();
		BasicDBObject trash     = new BasicDBObject();
		
		allitems.put("deleted", new BasicDBObject("$exists", 0));
		
		scheduled.put("deleted",    new BasicDBObject("$exists", 0));
		scheduled.put("org.ItemID", new BasicDBObject("$exists", 0));
		
		active.put("deleted",    new BasicDBObject("$exists", 0));
		active.put("org.ItemID", new BasicDBObject("$exists", 1));
		active.put("org.SellingStatus.ListingStatus", "Active");
		
		sold.put("deleted",    new BasicDBObject("$exists", 0));
		sold.put("org.ItemID", new BasicDBObject("$exists", 1));
		sold.put("org.SellingStatus.QuantitySold", new BasicDBObject("$gte", "1"));
		
		unsold.put("deleted",    new BasicDBObject("$exists", 0));
		unsold.put("org.ItemID", new BasicDBObject("$exists", 1));
		unsold.put("org.SellingStatus.ListingStatus", "Completed");
		unsold.put("org.SellingStatus.QuantitySold", "0");
		
		saved.put("deleted",    new BasicDBObject("$exists", 0));
		saved.put("org.ItemID", new BasicDBObject("$exists", 0));
		
		trash.put("deleted", new BasicDBObject("$exists", 1));
		
		
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
	
	private LinkedHashMap<Integer,String> categorypath2(String site, Integer categoryid) {
		
		LinkedHashMap<Integer,String> path = new LinkedHashMap<Integer,String>();
		LinkedHashMap<Integer,String> pathtmp = new LinkedHashMap<Integer,String>();
		ArrayList<Integer> cidtmp = new ArrayList<Integer>();
		
		BasicDBObject query = new BasicDBObject();
		DBCollection coll = db.getCollection(site+".Categories");
		
		while (true) {
			query.put("CategoryID", categoryid.toString());
			BasicDBObject row = (BasicDBObject) coll.findOne(query);
			
			path.put(Integer.parseInt(row.getString("CategoryID")), row.getString("CategoryName"));
			cidtmp.add(0, Integer.parseInt(row.getString("CategoryID")));
			
			if (row.getString("CategoryLevel").equals("1")) break;
			categoryid = Integer.parseInt(row.getString("CategoryParentID"));
		}
		
		for (Integer cid : cidtmp) {
			pathtmp.put(cid, path.get(cid));
		}
		
		return pathtmp;
	}
	
	private ArrayList categorypath(String site, Integer categoryid) {
		
		ArrayList path = new ArrayList();
		BasicDBObject query = new BasicDBObject();
		DBCollection coll = db.getCollection(site+".Categories");
		
		while (true) {
			log.debug(categoryid);
			query.put("CategoryID", categoryid.toString());
			BasicDBObject row = (BasicDBObject) coll.findOne(query);
			path.add(0, Integer.parseInt(row.getString("CategoryID")));
			
			if (row.getString("CategoryLevel").equals("1")) break;
			categoryid = Integer.parseInt(row.getString("CategoryParentID"));
		}
		
		return path;
	}
	
	@Action(value="/json/gc2")
	public String gc2() {
		
		/* handling post parameters */
		String site = ((String[]) parameters.get("site"))[0];
		String path = ((String[]) parameters.get("path"))[0];
		String[] arrpath = path.split("\\.");
		
		json = new LinkedHashMap<String,Object>();
		json.put("gc2", children2(site, arrpath));
		
		return SUCCESS;
	}
	
	private BasicDBObject grandchildren2(String site,
										 String[] path,
										 int recursive,
										 BasicDBObject features) {
		
		BasicDBObject result = new BasicDBObject();
		
		BasicDBObject field = new BasicDBObject();
		field.put("CategoryID",   1);
		field.put("CategoryName", 1);
		
		BasicDBObject query = new BasicDBObject();
		
		DBCollection coll    = db.getCollection(site+".Categories");
		DBCollection collspc = db.getCollection(site+".CategorySpecifics");
		DBCollection collft  = db.getCollection(site+".CategoryFeatures.Category");
		
		/* CategoryFeatures SiteDefaults */
		if (features == null) {
			features = getsitedefaults(site);
		}
		
		/* CategoryID */
		String categoryid = path[0];
		
		query = new BasicDBObject();
		if (categoryid.equals("0")) {
			query.put("CategoryLevel", "1");
		} else {
			// todo: use CategoryLevel for query? -> slow.
			query.put("CategoryParentID", categoryid);
			//query.put("CategoryID", new BasicDBObject("$ne", categoryid));
			
			/* CategoryFeatures */
			BasicDBObject dboft = 
				(BasicDBObject) collft.findOne(new BasicDBObject("CategoryID", categoryid));
			if (dboft != null) {
				for (Object o : dboft.keySet()) {
					features.put(o.toString(), dboft.get(o.toString()));
				}
			}
			
		}
		DBCursor cur = coll.find(query, field).sort(new BasicDBObject("_id", 1));
		if (cur.count() == 0) {
			return null;
		}
		
		while (cur.hasNext()) {
			BasicDBObject row = (BasicDBObject) cur.next();
			String key = "c"+row.getString("CategoryID");
			if (row.getString("CategoryID").equals(categoryid)) continue;
			
			/* CategorySpecifics */
			DBObject dbo = collspc.findOne(new BasicDBObject("CategoryID",
															 row.getString("CategoryID")));
			if (dbo != null) {
				dbo.removeField("_id");
				dbo.removeField("CategoryID");
				row.put("CategorySpecifics", dbo);
			}
			
			/* children */
			if (path.length >= 2 && row.getString("CategoryID").equals(path[1])) {
				
				String[] shifted = new String[path.length - 1];
				for (int i = 1; i < path.length; i++) {
					shifted[i - 1] = path[i];
				}
				
				BasicDBObject tmpchildren = grandchildren2(site, shifted, 1,
														   (BasicDBObject) features.copy());
				if (tmpchildren != null) {
					row.put("children", tmpchildren);
				}
				
			} else if (recursive == 1) {
				
				String[] shifted = new String[1];
				shifted[0] = row.getString("CategoryID");
				
				BasicDBObject tmpchildren = grandchildren2(site, shifted, 0,
														   (BasicDBObject) features.copy());
				if (tmpchildren != null) {
					row.put("children", tmpchildren);
				}
				
			} else if (recursive == 0) {
				
				BasicDBObject query2 = new BasicDBObject();
				query2.put("CategoryParentID", row.getString("CategoryID"));
				Long cnt = coll.count(query2);
				if (cnt > 0) {
					row.put("children", cnt);
				}
				
			}
			
			/* when it is a leaf category */
			if (!row.containsField("children")) {
				
				/* CategoryFeatures */
				//BasicDBObject features2 = (BasicDBObject) features.clone();
				BasicDBObject features2 = (BasicDBObject) features.copy();
				BasicDBObject dboft = (BasicDBObject)
					collft.findOne(new BasicDBObject("CategoryID", row.getString("CategoryID")));
				if (dboft != null) {
					for (Object o : dboft.keySet()) {
						features.put(o.toString(), dboft.get(o.toString()));
						if (row.getString("CategoryID").equals("16709")) {
							log.debug(o.toString()+":"+dboft.get(o.toString()));
						}
					}
					log.debug("features:"+row.getString("CategoryID"));
				}
				row.put("CategoryFeatures", features2);
			}
			
			row.removeField("_id");
			row.removeField("CategoryID");
			
			result.put(key, row);
		}
		
		return result;
	}
	
	private BasicDBObject children2(String site, String[] path) {
		
		BasicDBObject result = new BasicDBObject();
		BasicDBObject categories = new BasicDBObject();
		
		DBCollection coll    = db.getCollection(site+".Categories");
		DBCollection collft  = db.getCollection(site+".CategoryFeatures");
		DBCollection collftc = db.getCollection(site+".CategoryFeatures.Category");
		DBCollection collspc = db.getCollection(site+".CategorySpecifics");
		DBCollection coll2cs = db.getCollection(site+".Category2CS.Category");
		
		DBObject dbo = collft.findOne(null, new BasicDBObject("SiteDefaults", true));
		BasicDBObject features = (BasicDBObject) dbo.get("SiteDefaults");
		
		BasicDBObject field = new BasicDBObject();
		field.put("CategoryID",   1);
		field.put("CategoryName", 1);
		
		for (String categoryid : path) {
			
			/* CategoryFeatures */
			BasicDBObject dboft = 
				(BasicDBObject) collftc.findOne(new BasicDBObject("CategoryID", categoryid));
			if (dboft != null) {
				for (Object o : dboft.keySet()) {
					features.put(o.toString(), dboft.get(o.toString()));
				}
			}
			
			BasicDBObject query = new BasicDBObject();
			if (categoryid.equals("0")) {
				query.put("CategoryLevel", "1");
			} else {
				query.put("CategoryParentID", categoryid);
			}
			
			DBCursor cursor = coll.find(query, field).sort(new BasicDBObject("_id", 1));
			if (cursor.count() == 0) {
				continue;
			}
			
			BasicDBObject tmpchildren = new BasicDBObject();
			while (cursor.hasNext()) {
				BasicDBObject row = (BasicDBObject) cursor.next();
				
				if (row.getString("CategoryID").equals(categoryid)) continue;
				
				BasicDBObject childinfo = new BasicDBObject();

				childinfo.put("name", row.getString("CategoryName"));
				
				DBCursor cursor2 = coll.find(new BasicDBObject("CategoryParentID",
															   row.getString("CategoryID")));
				
				childinfo.put("children", cursor2.count());
				
				if (row.getString("CategoryID").equals(path[path.length-1])) {
					childinfo.put("CategoryFeatures", features);
					
					/* CategorySpecifics */
					DBObject dbospc = collspc.findOne
						(new BasicDBObject("CategoryID", row.getString("CategoryID")));
					if (dbospc != null) {
						dbospc.removeField("_id");
						childinfo.put("CategorySpecifics", dbospc);
					}
					
					/* Category2CS */
					DBObject dbo2cs = coll2cs.findOne
						(new BasicDBObject("CategoryID", row.getString("CategoryID")));
					if (dbo2cs != null) {
						dbo2cs.removeField("_id");
						childinfo.put("Category2CS", dbo2cs);
					}
				}
				
				tmpchildren.put("c"+row.getString("CategoryID"), childinfo);
			}
			categories.put("c"+categoryid, tmpchildren);
		}
		
		result.put("Categories", categories);
		
		return result;
	}

	private BasicDBObject getsitedefaults(String site) {
		
		DBCollection collection = db.getCollection(site+".CategoryFeatures");
		DBObject dbo = collection.findOne(null, new BasicDBObject("SiteDefaults", true));
		BasicDBObject sitedefaults = (BasicDBObject) dbo.get("SiteDefaults");
		
		return sitedefaults;
	}

	private LinkedHashMap<String,LinkedHashMap> children(String site, Integer categoryid) {
		
		LinkedHashMap<String,LinkedHashMap> data = new LinkedHashMap<String,LinkedHashMap>();
		LinkedHashMap<Integer,String>       name = new LinkedHashMap<Integer,String>();
		LinkedHashMap<Integer,Object>   children = new LinkedHashMap<Integer,Object>();
		
		ArrayList<Integer> arrchildren = new ArrayList<Integer>();
		ArrayList<Integer> leafval = new ArrayList<Integer>();
		leafval.add(0);
		
		BasicDBObject query = new BasicDBObject();
		if (categoryid == 0) {
			query.put("CategoryLevel", "1");
		} else {
			query.put("CategoryParentID", categoryid.toString());
			query.put("CategoryID", new BasicDBObject("$ne", categoryid.toString()));
		}
		
		DBCollection coll = db.getCollection(site+".Categories");
		DBCursor cur = coll.find(query);
		if (cur.count() > 0) {
			while (cur.hasNext()) {
				BasicDBObject row = (BasicDBObject) cur.next();
				Integer childid = Integer.parseInt(row.get("CategoryID").toString());
				String childname = row.get("CategoryName").toString();
				
				name.put(childid, childname);
				arrchildren.add(childid);
				
				if (row.get("LeafCategory") != null
					&& row.get("LeafCategory").toString().equals("true")) {
					children.put(childid, "children");
				}
				
			}
			children.put(categoryid, arrchildren);
		} else {
			children.put(categoryid, "leaf");
		}
		
		data.put("name", name);
		data.put("children", children);
		
		return data;
	}

	@Action(value="/json/grandchildren")
	public String grandchildren() {
		
		json = new LinkedHashMap<String,Object>();
		
		LinkedHashMap<Integer,String>  name          = new LinkedHashMap<Integer,String>();
		LinkedHashMap<Integer,Object>  children      = new LinkedHashMap<Integer,Object>();
		LinkedHashMap<Integer,Integer> grandchildren = new LinkedHashMap<Integer,Integer>();
		
		/* handling post parameters */
		String site    = ((String[]) parameters.get("site"))[0];
		String pathstr = ((String[]) parameters.get("pathstr"))[0];
		
		String[] arrs = pathstr.split("\\.");
		for (String s : arrs) {
			Integer categoryid = Integer.parseInt(s);
			grandchildren.put(categoryid, 1);
			LinkedHashMap p = children(site, categoryid);
			json.put("chk"+categoryid.toString(), p.get("children").getClass().toString());
			
			if (((LinkedHashMap) p.get("children")).get(categoryid)
				.getClass().toString().equals("class java.lang.String")) {
				continue;
			}
			
			ArrayList childids = (ArrayList) ((LinkedHashMap) p.get("children")).get(categoryid);
			for (Object ochildid : childids) {
				Integer childid = Integer.parseInt(ochildid.toString());
				LinkedHashMap c = children(site, childid);
				LinkedHashMap gchildids = (LinkedHashMap) c.get("children");
				for (Object ogchildid : gchildids.keySet()) {
					Integer gchildid = Integer.parseInt(ogchildid.toString());
					
					if (gchildids.get(gchildid).getClass().toString()
						.equals("class java.lang.String")) {
						children.put(gchildid, "leaf");
					} else {
						children.put(gchildid, (ArrayList<Integer>) gchildids.get(gchildid));
					}
				}
				LinkedHashMap names = (LinkedHashMap) c.get("name");
				for (Object tmpname : names.keySet()) {
					name.put(Integer.parseInt(tmpname.toString()), names.get(tmpname).toString());
				}
			}
		}
		
		json.put("grandchildren", grandchildren);
		json.put("children", children);
		json.put("name", name);
		
		return SUCCESS;
	}
	
	@Action(value="/json/categoryfeatures")
	public String categoryfeatures() {
		json = new LinkedHashMap<String,Object>();
		
		/* handling post parameters */
		String site       = ((String[]) parameters.get("site"))[0];
		String categoryid = ((String[]) parameters.get("categoryid"))[0];
		
		
		/* DurationSet */
		BasicDBObject query = new BasicDBObject();
		BasicDBObject keys = new BasicDBObject();
		keys.put("FeatureDefinitions.ListingDurations.ListingDuration", 1);
		
		DBCollection collection = db.getCollection(site+".CategoryFeatures");
		DBObject res = collection.findOne(query, keys);
		BasicDBObject fd  = (BasicDBObject) res.get("FeatureDefinitions");
		BasicDBObject lds = (BasicDBObject)  fd.get("ListingDurations");
		BasicDBList   ald = (BasicDBList)   lds.get("ListingDuration");
		
		LinkedHashMap<Integer,LinkedHashMap> durationset =
			new LinkedHashMap<Integer,LinkedHashMap>();
		for (Object ld : ald) {
			BasicDBObject lddbo = (BasicDBObject) ld;
			
			String durationsetid = lddbo.get("@durationSetID").toString();
			Integer setid = Integer.parseInt(durationsetid);
			LinkedHashMap<String,String> dset = new LinkedHashMap<String,String>();
			
			// todo: only chinese auction?
			dset.put("Days_1", "1 Day");
			
			// todo: more easy way to handle string or array
			String classname = lddbo.get("Duration").getClass().toString();
			if (classname.equals("class java.lang.String")) {
				
				dset.put(lddbo.getString("Duration"),
						 lddbo.getString("Duration"));
				
			} else {
				
				for (Object d : (BasicDBList) lddbo.get("Duration")) {
					String dname = d.toString();
					String dval = dname
						.replaceAll("^Days_([\\d]+)$", "$1 Days")
						.replace("GTC", "Good 'Til Cancelled");
					dset.put(dname, dval);
				}
				
			}
			
			durationset.put(setid, dset);
		}
		//log.debug(durationset);
		json.put("durationset", durationset);
		
		
		/* SiteDefaults */
		DBObject dbo = collection.findOne(null, new BasicDBObject("SiteDefaults", true));
		BasicDBObject   sd = (BasicDBObject) dbo.get("SiteDefaults");
		BasicDBList   sdld = (BasicDBList)    sd.get("ListingDuration");
		
		LinkedHashMap<String,LinkedHashMap> typedefault = new LinkedHashMap<String,LinkedHashMap>();
		
		for (Object ldo : sdld) {
			String   type = ((BasicDBObject) ldo).get("@type").toString();
			Integer setid = Integer.parseInt(((BasicDBObject) ldo).get("#text").toString());
			typedefault.put(type, durationset.get(setid));
		}
		
		json.put("sdld", sdld);
		json.put("sd", sd);
		json.put("typedefault", typedefault);
		/*
		for (String key : ((BasicDBObject) dbo.get("SiteDefaults")).keySet()) {
			json.put("k-"+key, dbo.get("key"));
		}
		for (Object ld : (List) sd.get("ListingDuration")) {
		//json.put("ld-"+ld, ((List) ld).get("@type"));
		}
		*/
		
		/* CategoryPath */
		DBCollection coll2 = db.getCollection(site+".CategoryFeatures.Category");
		LinkedHashMap<Integer,String> path = categorypath2(site, Integer.parseInt(categoryid));
		for (Integer cid : path.keySet()) {
			dbo = coll2.findOne(new BasicDBObject("CategoryID", cid.toString()));
			json.put("p-"+cid.toString(), dbo);
			if (dbo != null && dbo.containsField("ConditionValues")) {
				
				BasicDBObject cv = (BasicDBObject) dbo.get("ConditionValues");
				LinkedHashMap<Integer,String> cvl = new LinkedHashMap<Integer,String>();
				for (Object cvo : (BasicDBList) cv.get("Condition")) {
					cvl.put(Integer.parseInt(((BasicDBObject) cvo).getString("ID")),
							((BasicDBObject) cvo).getString("DisplayName"));
				}
				
				((BasicDBObject) dbo.get("ConditionValues")).put("Condition", cvl);
				sd.put("ConditionValues", dbo.get("ConditionValues"));
			}
		}
		json.put("path", path);
		
		sd.put("ListingDuration", typedefault);
		
		HashMap<String,Map> cf = new HashMap<String,Map>();
		cf.put(categoryid, sd);
		json.put("features", cf);
		
		return SUCCESS;
	}
	
	// todo: check UK:courier?
	public LinkedHashMap<String,LinkedHashMap> shippingtype(String site) {
		
		LinkedHashMap<String,LinkedHashMap>    map = new LinkedHashMap<String,LinkedHashMap>();
		LinkedHashMap<String,String>      domestic = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String> international = new LinkedHashMap<String,String>();
		
		if (site.equals("US")) {
			domestic.put("Flat"      , "Flat: same cost to all buyers");
			domestic.put("Calculated", "Calculated: Cost varies by buyer location");
			domestic.put("Freight"   , "Freight: large items over 150 lbs.");
		} else {
			domestic.put("Calculated", "Calculated: Cost varies by buyer location");
			domestic.put("Flat"      , "Flat: same cost to all buyers");
		}
		domestic.put("NoShipping", "No shipping: Local pickup only");
		
		international.put("Flat"      , "Flat: same cost to all buyers");
		international.put("Calculated", "Calculated: Cost varies by buyer location");
		international.put("NoShipping", "No international shipping");
		
		map.put("domestic"     , domestic);
		map.put("international", international);
		
		return map;
	}
	
	// todo: reverse function?
	private LinkedHashMap<String,LinkedHashMap> shippingmap() {
		
		LinkedHashMap<String,LinkedHashMap> map = new LinkedHashMap<String,LinkedHashMap>();
		LinkedHashMap<String,String>    tmpmap1 = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String>    tmpmap2 = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String>    tmpmap3 = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String>    tmpmap4 = new LinkedHashMap<String,String>();
		LinkedHashMap<String,String>    tmpmap5 = new LinkedHashMap<String,String>();
		
		tmpmap1.put("domestic",      "Flat");
		tmpmap1.put("international", "Flat");
		map.put("Flat"                               , tmpmap1);
		
		tmpmap2.put("domestic",      "Calculated");
		tmpmap2.put("international", "Calculated");
		map.put("Calculated"                         , tmpmap2);
		
		tmpmap3.put("domestic",      "Flat");
		tmpmap3.put("international", "Calculated");
		map.put("FlatDomesticCalculatedInternational", tmpmap3);
		
		tmpmap4.put("domestic",      "Calculated");
		tmpmap4.put("international", "Flat");
		map.put("CalculatedDomesticFlatInternational", tmpmap4);
		
		// todo: check "Freight" is only web?
		tmpmap5.put("domestic",      "Freight");
		tmpmap5.put("international", "???");
		map.put("FreightFlat"                        , tmpmap5);
		
		return map;
	}
	
	private LinkedHashMap<String,String> getebaydetails(String coll, String key, String value) {
		LinkedHashMap<String,String> hash = new LinkedHashMap<String,String>();
		
		BasicDBObject query = new BasicDBObject();
		BasicDBObject field = new BasicDBObject();
		field.put(key, 1);
		field.put(value, 1);
		
		DBCursor cursor = db.getCollection(coll).find(query, field);
		while (cursor.hasNext()) {
			BasicDBObject row  = (BasicDBObject) cursor.next();
			hash.put(row.getString(key), row.getString(value));
		}
		
		return hash;
	}
	
	private LinkedHashMap<String,String> countrydetails(String site) {
		
		LinkedHashMap<String,String> hash = new LinkedHashMap<String,String>();
		
		BasicDBObject query = new BasicDBObject();
		
		BasicDBObject field = new BasicDBObject();
		field.put("Country", 1);
		field.put("Description", 1);
		
		DBCollection collection = db.getCollection(site+".eBayDetails.CountryDetails");
		DBCursor cursor = collection.find(query, field);
		while (cursor.hasNext()) {
			BasicDBObject row  = (BasicDBObject) cursor.next();
			
			hash.put(row.getString("Country"),
					 row.getString("Description"));
		}
		
		return hash;
	}
	
	private LinkedHashMap<String,String> currencydetails(String site) {
		
		LinkedHashMap<String,String> hash = new LinkedHashMap<String,String>();
		
		BasicDBObject query = new BasicDBObject();
		
		BasicDBObject field = new BasicDBObject();
		field.put("Currency", 1);
		field.put("Description", 1);
		
		DBCollection collection = db.getCollection(site+".eBayDetails.CurrencyDetails");
		DBCursor cursor = collection.find(query, field);
		while (cursor.hasNext()) {
			BasicDBObject row  = (BasicDBObject) cursor.next();
			
			hash.put(row.getString("Currency"),
					 row.getString("Description"));
		}
		
		return hash;
	}
	
	private LinkedHashMap<String,String> shippingtypelabel2(BasicDBObject item) {
		LinkedHashMap<String,String> label = new LinkedHashMap<String,String>();
		
		BasicDBObject mod = (BasicDBObject) item.get("org");
		BasicDBObject sd = (BasicDBObject) mod.get("ShippingDetails");
		if (sd.containsField("ShippingType")) {
			String shippingtype = sd.getString("ShippingType");
			if (shippingtype.equals("Flat")) {
				label.put("domestic",      "Flat");
				label.put("international", "Flat");
			} else if (shippingtype.equals("Calculated")) {
				label.put("domestic",      "Calculated");
				label.put("international", "Calculated");
			} else if (shippingtype.equals("FlatDomesticCalculatedInternational")) {
				label.put("domestic",      "Flat");
				label.put("international", "Calculated");
			} else if (shippingtype.equals("CalculatedDomesticFlatInternational")) {
				label.put("domestic",      "Calculated");
				label.put("international", "Flat");
			} else if (shippingtype.equals("FreightFlat")) {
				label.put("domestic",      "Freight");
				label.put("international", "(?)");
			}
			if (!sd.containsField("InternationalShippingServiceOption")) {
				label.put("international", "NoShipping");
			}
		} else {
			label.put("domestic",      "NoShipping");
			label.put("international", "NoShipping");
		}
		
		return label;
	}
	
	// todo: replace with shippingtypelabel2() method.
	private LinkedHashMap<String,String> shippingtypelabel(String site, String type) {
		
		LinkedHashMap<String,String> label = new LinkedHashMap<String,String>();
		
		LinkedHashMap<String,LinkedHashMap> types = shippingtype(site);
		LinkedHashMap<String,LinkedHashMap> map   = shippingmap();
		
		label.put("domestic",
				  (String) types.get("domestic").get(map.get(type).get("domestic")));
		
		label.put("international",
				  (String) types.get("international").get(map.get(type).get("international")));
		
		return label;
	}
	
	private LinkedHashMap<String,String> dispatchtimemaxdetails(String site) {
		
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		
		DBCollection collection = db.getCollection(site+".eBayDetails.DispatchTimeMaxDetails");
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			BasicDBObject row = (BasicDBObject) cursor.next();
			
			String k = row.getString("DispatchTimeMax");
			String v = row.getString("Description");
			
			map.put(k, v);
		}
		
		return map;
	}
	
	private LinkedHashMap<String,LinkedHashMap> shippingservicedetails(String site) {
		
		LinkedHashMap<String,LinkedHashMap> map = new LinkedHashMap<String,LinkedHashMap>();
		
		DBCollection collection = db.getCollection(site+".eBayDetails.ShippingServiceDetails");
		DBCursor cursor = collection.find(new BasicDBObject("ValidForSellingFlow", "true"));
		while (cursor.hasNext()) {
			DBObject row = cursor.next();
			
			String ss = row.get("ShippingService").toString();
			
			row.removeField("_id");
			row.removeField("UpdateTime");
			
			map.put(ss, (LinkedHashMap) row.toMap());
		}
		
		return map;
	}
	
	private LinkedHashMap<String,LinkedHashMap> shippingpackagedetails(String site) {
		
		LinkedHashMap<String,LinkedHashMap> map = new LinkedHashMap<String,LinkedHashMap>();
		
		DBCollection collection = db.getCollection(site+".eBayDetails.ShippingPackageDetails");
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject row = cursor.next();
			
			String id = row.get("ShippingPackage").toString();
			
			row.removeField("_id");
			row.removeField("UpdateTime");
			
			map.put(id, (LinkedHashMap) row.toMap());
		}
		
		return map;
	}

	private Integer mapcategoryid(String site, Integer categoryid) {
		
		log.debug("map: "+categoryid);
		DBObject newdbo = db.getCollection(site+".CategoryMappings")
			.findOne(new BasicDBObject("@oldID", categoryid.toString()));
		if (newdbo != null) {
			log.debug("map: "+categoryid+" -> "+newdbo.get("@id").toString());
			categoryid = Integer.parseInt(newdbo.get("@id").toString());
		}
		
		return categoryid;
	}
	
	private BasicDBObject getFilterQuery() {
		
		/* allways filter with userids */
		ArrayList<String> userids = new ArrayList<String>();
		for (Object userid : ((BasicDBObject) user.get("userids")).keySet()) {
			userids.add(userid.toString());
		}
		
		BasicDBObject query = new BasicDBObject();
		
		if (parameters.containsKey("id")) {
			
			ArrayList<ObjectId> ids = new ArrayList<ObjectId>();
			for (String id : (String[]) parameters.get("id")) {
				ids.add(new ObjectId(id));
			}
			query.put("_id", new BasicDBObject("$in", ids));
			query.put("org.Seller.UserID", new BasicDBObject("$in", userids));
			
		} else {
			
			String selling = "";
			String userid  = "";
			String title   = "";
			String itemid  = "";
			
			LinkedHashMap<String,BasicDBObject> sellingquery = getsellingquery();
			
			if (parameters.containsKey("selling"))
				selling = ((String[]) parameters.get("selling"))[0];
			
			if (!selling.equals(""))
				query = sellingquery.get(selling);
			
			// notice: put UserID here, because sellingquery above.
			query.put("org.Seller.UserID", new BasicDBObject("$in", userids));
			
			// todo: check the UserID exists in users collection.
			if (parameters.containsKey("UserID"))
				userid = ((String[]) parameters.get("UserID"))[0];
			
			if (!userid.equals(""))
				query.put("org.Seller.UserID", userid);
			
			if (parameters.containsKey("Title"))
				title = ((String[]) parameters.get("Title"))[0];
			
			if (!title.equals(""))
				query.put("mod.Title", Pattern.compile(title));
			
			if (parameters.containsKey("ItemID")) 
				itemid = ((String[]) parameters.get("ItemID"))[0];
			
			if (!itemid.equals(""))
				query.put("org.ItemID", itemid);
			
		}
		
		return query;
	}
}
