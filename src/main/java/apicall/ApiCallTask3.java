package ebaytool.apicall;

import com.mongodb.*;
import ebaytool.apicall.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.xml.XMLSerializer;

public class ApiCallTask3 implements Callable {
	
	private Integer siteid;
	private String requestxml;
	private String callname; // todo: get from caller class.
	private String resulttype; // todo: get from caller class.
	private String basedir;
	private BasicDBObject configdbo;
	
	public ApiCallTask3(Integer siteid, String requestxml, String callname) throws Exception {
		this.siteid = siteid;
		this.requestxml = requestxml;
		this.callname = callname;
		this.resulttype = "";

		basedir = System.getProperty("user.dir");
		configdbo = convertXML2DBObject(readfile(basedir+"/config/config.xml"));
	}
	
	public ApiCallTask3(Integer siteid, String requestxml, String callname, String resulttype) throws Exception {
		this.siteid     = siteid;
		this.requestxml = requestxml;
		this.callname   = callname;
		this.resulttype = resulttype;

		basedir = System.getProperty("user.dir");
		configdbo = convertXML2DBObject(readfile(basedir+"/config/config.xml"));
	}
	
	public String call() throws Exception {
		
		/* make log directory for each call */
		boolean exists = (new File(basedir+"/logs/apicall/"+callname)).exists();
		if (exists) {
			
		} else {
			new File(basedir+"/logs/apicall/"+callname).mkdir();
		}
		
		if (siteid == 100) siteid = 0;
		
		//String callname = this.getClass().toString().replace("class ebaytool.apicall.", "");
		//callname = new Throwable().getStackTrace()[3].getClassName();
		
		/*
		StackTraceElement[] ste = new Throwable().getStackTrace();
		for (StackTraceElement st : ste) {
			System.out.println(st.getClassName());
		}
		*/
		
		/* Sandbox */
		String apiurl   = configdbo.getString("openapiurl");
		String appname  = configdbo.getString("appname");

        URL url = new URL(apiurl);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "text/xml");
		conn.setRequestProperty("X-EBAY-API-VERSION", configdbo.getString("compatlevel"));
		conn.setRequestProperty("X-EBAY-API-CALL-NAME", callname);
		conn.setRequestProperty("X-EBAY-API-SITE-ID", siteid.toString());
		conn.setRequestProperty("X-EBAY-API-APP-ID",  appname);
		conn.setRequestProperty("X-EBAY-API-REQUEST-ENCODING",  "XML");
		//conn.setRequestProperty("X-EBAY-API-RESPONSE-ENCODING",  "JSON");
		
		// todo: trap network error.
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(requestxml);
		osw.flush();
		osw.close();
        conn.connect();
		
		String savedir = basedir+"/logs/apicall/"+callname;
		String filename = "_tmp."+siteid.toString()+".xml";
		FileWriter fstream = new FileWriter(savedir+"/"+filename);
		BufferedWriter out = new BufferedWriter(fstream);
		
		/* handle http response */
        InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line;
		String responsexml = "";
		while ((line = br.readLine()) != null) {
			if (resulttype.equals("filename")) {
				// todo: Should I add linefeed?
				out.write(line);
			} else {
				responsexml = responsexml + line;
			}
		}
		br.close();
		out.close();
		
		String result = "";
		
		//if (true) return result; // when you want to skip collback.
		
		/* callback */
		try {
			ApiCall task = (ApiCall) Class.forName("ebaytool.apicall."+callname).newInstance();
			if (resulttype.equals("filename")) {
				result = task.callback(savedir+"/"+filename);
			} else {
				result = task.callback(responsexml);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// todo: many same methods...
	public BasicDBObject convertXML2DBObject(String xml) {
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		//xmlSerializer.setTypeHintsCompatibility(true);
		xmlSerializer.setTypeHintsEnabled(false);
		
		net.sf.json.JSON json = xmlSerializer.read(xml);
		
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		
		return dbobject;
	}
	
	public String readfile(String filename) throws Exception {
		
		String data = "";
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			data = data + line;
		}
		br.close();
		
		return data;
	}
}
