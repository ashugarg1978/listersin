package ebaytool;

import ebaytool.ApiCall;

import java.io.*;
import java.net.URL;
import java.util.concurrent.*;
import javax.net.ssl.HttpsURLConnection;

public class GetSellerList extends ApiCall {
	
	public GetSellerList (String xml) {
		this.requestxml = xml;
	}
	
	public String call() throws Exception {
		
		System.out.println(requestxml);
		
		String responsexml = callapi("GetSellerList", requestxml);
		
		//return responsexml;
		
		return "done";
	}
	
	private String callback() {
		
		System.out.println("callback() in GetSellerList.java");
		
		return "result";
	}
}
