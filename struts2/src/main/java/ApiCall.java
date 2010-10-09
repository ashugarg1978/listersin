package ebaytool;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

public class ApiCall implements Callable {
	
	private String callname;
	public String requestxml;
	private String result;
	
	public String call() throws Exception {
		return "";
	}
	
	public String callapi(String callname, String xml) throws Exception {
		
		System.out.println(this.getClass());
		
        URL url = new URL("https://api.sandbox.ebay.com/ws/api.dll");
		
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		
		/* http request header */
        conn.setRequestProperty("Content-Type", "text/xml");
        conn.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "677");
        conn.setRequestProperty("X-EBAY-API-CALL-NAME", callname);
        conn.setRequestProperty("X-EBAY-API-SITEID", "0");
        conn.setRequestProperty("X-EBAY-API-DEV-NAME",  "e60361cd-e306-496f-ad7d-ba7b688e2207");
        conn.setRequestProperty("X-EBAY-API-APP-NAME",  "Yoshihir-1b29-4aad-b39f-1be3a37e06a7");
        conn.setRequestProperty("X-EBAY-API-CERT-NAME", "8118c1eb-e879-47f3-a172-2b08ca680770");
		
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(xml);
		osw.flush();
		osw.close();
		
        conn.connect();
		
		/* handle http response */
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String line;
		String response = "";
		while ((line = br.readLine()) != null) {
			response = response + line;
		}
		br.close();
		
		return response;
	}
	
}
