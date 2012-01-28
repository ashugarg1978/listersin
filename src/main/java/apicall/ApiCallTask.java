package ebaytool.apicall;

import ebaytool.apicall.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

public class ApiCallTask implements Callable {
	
	private Integer siteid;
	private String requestxml;
	private String callname; // todo: get from caller class.
	private String resulttype; // todo: get from caller class.
	
	public ApiCallTask(Integer siteid, String requestxml, String callname) throws Exception {
		this.siteid = siteid;
		this.requestxml = requestxml;
		this.callname = callname;
		this.resulttype = "";
	}
	
	public ApiCallTask(Integer siteid, String requestxml, String callname, String resulttype) throws Exception {
		this.siteid     = siteid;
		this.requestxml = requestxml;
		this.callname   = callname;
		this.resulttype = resulttype;
	}
	
	public String call() throws Exception {
		
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
		String apiurl   = "https://api.sandbox.ebay.com/ws/api.dll";
		String devname  = "e60361cd-e306-496f-ad7d-ba7b688e2207";
		String appname  = "Yoshihir-1b29-4aad-b39f-1be3a37e06a7";
		String certname = "8118c1eb-e879-47f3-a172-2b08ca680770";
		
		/* Production */
		if (false) {
			System.out.println("Connecting to ebay *Production* api.");
			apiurl   = "https://api.ebay.com/ws/api.dll";
			devname  = "e60361cd-e306-496f-ad7d-ba7b688e2207";
			appname  = "Yoshihir-dd83-40fd-a943-659c40507758";
			certname = "8681eef3-fba8-41cf-b2ca-3686152ac1b7";
		}
		
        URL url = new URL(apiurl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "text/xml");
		conn.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "753");
		conn.setRequestProperty("X-EBAY-API-CALL-NAME", callname);
		conn.setRequestProperty("X-EBAY-API-SITEID", siteid.toString());

		conn.setRequestProperty("X-EBAY-API-DEV-NAME",  devname);
		conn.setRequestProperty("X-EBAY-API-APP-NAME",  appname);
		conn.setRequestProperty("X-EBAY-API-CERT-NAME", certname);
		
		// todo: trap network error.
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(requestxml);
		osw.flush();
		osw.close();
        conn.connect();
		
		String savedir = "/var/www/ebaytool.jp/logs/apicall/"+callname;
		String filename = callname+"."+siteid.toString()+".xml";
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
	
}
