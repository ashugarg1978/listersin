package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class downloadFile extends ApiCall {
	
	public downloadFile() throws Exception {
	}
	
	public String call() throws Exception {
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS");
		Date now = new Date();
		String timestamp = sdf.format(now).toString();
		writelog("downloadFile/"+timestamp+".xml", responsexml);
		
		BasicDBObject resdbo = convertXML2DBObject(responsexml);
		
		return "";
	}
}
