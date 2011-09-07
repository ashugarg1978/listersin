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
		if (callname.equals("downloadFile")) {
			url = new URL("https://storage.sandbox.ebay.com/FileTransferService");
		}
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "text/xml");
		
		if (callname.equals("downloadFile")) {
			conn.setRequestProperty("X-EBAY-SOA-OPERATION-NAME", "downloadFile");
			conn.setRequestProperty("X-EBAY-SOA-SECURITY-TOKEN", "AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB");
			//conn.setRequestProperty("X-EBAY-SOA-SERVICE-NAME", "");
			//conn.setRequestProperty("X-EBAY-SOA-SERVICE-VERSION", "");
		} else {
			conn.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "735");
			conn.setRequestProperty("X-EBAY-API-CALL-NAME", callname);
			conn.setRequestProperty("X-EBAY-API-SITEID", siteid.toString());
			conn.setRequestProperty("X-EBAY-API-DEV-NAME",  "e60361cd-e306-496f-ad7d-ba7b688e2207");
			conn.setRequestProperty("X-EBAY-API-APP-NAME",  "Yoshihir-1b29-4aad-b39f-1be3a37e06a7");
			conn.setRequestProperty("X-EBAY-API-CERT-NAME", "8118c1eb-e879-47f3-a172-2b08ca680770");
		}
		
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
		
		/* handle http response */
        InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line;
		String responsexml = "";
		while ((line = br.readLine()) != null) {
			responsexml = responsexml + line;
		}
		br.close();
		
		String result = "";
		
		/* callback */
		try {
			ApiCall task = (ApiCall) Class.forName("ebaytool.apicall."+callname).newInstance();
			result = task.callback(responsexml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
