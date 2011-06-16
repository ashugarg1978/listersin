package ebaytool.apicall;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

public class ApiCallTask implements Callable {
	
	private Integer siteid;
	private String requestxml;
	private String callname; // todo: get from caller class.
	
	public ApiCallTask(Integer siteid, String requestxml, String callname) throws Exception {
		this.siteid = siteid;
		this.requestxml = requestxml;
		this.callname = callname;
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
		
        URL url = new URL("https://api.sandbox.ebay.com/ws/api.dll");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "text/xml");
        conn.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "725");
        conn.setRequestProperty("X-EBAY-API-CALL-NAME", callname);
        conn.setRequestProperty("X-EBAY-API-SITEID", siteid.toString());
        conn.setRequestProperty("X-EBAY-API-DEV-NAME",  "e60361cd-e306-496f-ad7d-ba7b688e2207");
        conn.setRequestProperty("X-EBAY-API-APP-NAME",  "Yoshihir-1b29-4aad-b39f-1be3a37e06a7");
        conn.setRequestProperty("X-EBAY-API-CERT-NAME", "8118c1eb-e879-47f3-a172-2b08ca680770");
		
		/*
        conn.setRequestProperty("X-EBAY-API-DEV-NAME",  "e60361cd-e306-496f-ad7d-ba7b688e2207");
        conn.setRequestProperty("X-EBAY-API-APP-NAME",  "Yoshihir-dd83-40fd-a943-659c40507758");
        conn.setRequestProperty("X-EBAY-API-CERT-NAME", "8681eef3-fba8-41cf-b2ca-3686152ac1b7");
		*/
		
		// todo: trap network error.
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(requestxml);
		osw.flush();
		osw.close();
		
        conn.connect();
		
		//System.out.println(conn.getResponseMessage());
		
		/* handle http response */
        InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line;
		String responsexml = "";
		while ((line = br.readLine()) != null) {
			responsexml = responsexml + line;
		}
		br.close();
		
		return responsexml;
	}
	
}
