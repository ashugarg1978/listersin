package ebaytool;

import java.io.*;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

public class Daemon extends Thread {
	
    public static void main(String[] args)
    {
		System.out.println("main()\n");
		
		Daemon t = new Daemon();
		t.setDaemon(true);
		t.start();
		
		
		try {
			
			getSellerList();
			
			for (int i=0; i<10; i++) {
				System.out.println("main() method.["+i+"]\n");
				Thread.sleep(600);
			}
			
		} catch (Exception e) {
			
		}
    }
	
	public void run() {
		try {
			for (int i=0; i<10; i++) {
				System.out.println("run() method.["+i+"]\n");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			
		}
	}
	
	public static String getSellerList() throws Exception {
		
        URL url = new URL("https://api.sandbox.ebay.com/ws/api.dll");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
		
        conn.setRequestProperty("Content-Type", "text/xml");
        conn.setRequestProperty("X-EBAY-API-COMPATIBILITY-LEVEL", "677");
        conn.setRequestProperty("X-EBAY-API-CALL-NAME", "GetSellerList");
        conn.setRequestProperty("X-EBAY-API-SITEID", "0");
        conn.setRequestProperty("X-EBAY-API-DEV-NAME", "e60361cd-e306-496f-ad7d-ba7b688e2207");
        conn.setRequestProperty("X-EBAY-API-APP-NAME", "Yoshihir-1b29-4aad-b39f-1be3a37e06a7");
        conn.setRequestProperty("X-EBAY-API-CERT-NAME", "8118c1eb-e879-47f3-a172-2b08ca680770");
		
		String xmldata;
		xmldata = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><GetSellerListRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\"><WarningLevel>High</WarningLevel><RequesterCredentials><eBayAuthToken>AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB</eBayAuthToken></RequesterCredentials><DetailLevel>ReturnAll</DetailLevel><StartTimeFrom>2010-06-01 00:00:00</StartTimeFrom><StartTimeTo>2010-08-30 00:00:00</StartTimeTo><Pagination><EntriesPerPage>50</EntriesPerPage><PageNumber>1</PageNumber></Pagination><Sort>1</Sort></GetSellerListRequest>";
		
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		osw.write(xmldata);
		osw.flush();
		osw.close();
		
        //PrintWriter output = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
        //String fileContent = convertFileContent2String(XmlFileName);
		
        //output.println(fileContent);
        //output.close();
        conn.connect();
		
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String line;
		String allline;
		allline = "";
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			// Process line...
			allline = allline + line;
		}
		br.close();
		
		/* JAXB test */
		JAXBContext jc = JAXBContext.newInstance("test.jaxb");
		Unmarshaller unm = jc.createUnmarshaller();
		Object collection = (Object) unm.unmarshal
			(new java.io.FileInputStream("/var/www/dev.xboo.st/data/apixml/SiteDetails.xml"));
		
		//Collection collection = (Collection) jc.createUnmarshaller().unmarshal(allline);
		
		return "ok";
	}
}
