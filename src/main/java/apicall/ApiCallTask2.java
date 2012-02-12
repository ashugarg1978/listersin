package ebaytool.apicall;

import com.mongodb.*;
import ebaytool.apicall.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;
import net.sf.json.xml.XMLSerializer;

public class ApiCallTask2 implements Callable {
	
	private Integer siteid;
	private String requestxml;
	private String callname; // todo: get from caller class.
	private String site;
	private String basedir;
	private BasicDBObject configdbo;
	
	public ApiCallTask2(Integer siteid, String requestxml, String callname, String site)
		throws Exception {
		
		this.siteid     = siteid;
		this.requestxml = requestxml;
		this.callname   = callname;
		this.site       = site;

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
		
        URL url = new URL(configdbo.getString("apiftsurl"));
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "text/xml");
		
		conn.setRequestProperty("X-EBAY-SOA-OPERATION-NAME", "downloadFile");
		conn.setRequestProperty("X-EBAY-SOA-SECURITY-TOKEN", configdbo.getString("admintoken"));
		
		// todo: trap network error.
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(requestxml);
		osw.flush();
		osw.close();
        conn.connect();
		
		/* save response */
		File file = new File(basedir+"/logs/apicall/downloadFile/"+site+".raw");
		InputStream is = conn.getInputStream();
		OutputStream os = new FileOutputStream(file);
		
		byte buf[] = new byte[1024];
		int len;
		while ((len=is.read(buf)) > 0) {
			os.write(buf,0,len);
		}
		os.close();
		is.close();
		
		String result = "";
		
		/* callback */
		try {
			ApiCall task = (ApiCall) Class.forName("ebaytool.apicall."+callname).newInstance();
			result = task.callback(site);
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
