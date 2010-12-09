package ebaytool.apicall;

import ebaytool.apicall.ApiCall;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApiCallMonitor extends ApiCall {
	
	public ApiCallMonitor() throws Exception {
	}
	
	public String call() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		while (true) {
			
			Date now = new Date();
			
			String logstr = sdf.format(now).toString()
				+ " "+pool18.getTaskCount()
				+ "/"+pool18.getActiveCount()
				+ "/"+pool18.getCompletedTaskCount();
			
			if (pool18.isShutdown()) logstr += "[shutdown]";
			if (pool18.isTerminating()) logstr += "[terminating]";
			if (pool18.isTerminated()) logstr += "[terminated]";
			
			System.out.println(logstr);
			
			Thread.sleep(1000);
			
			if (pool18.isTerminated()) break;
		}
		
		return "";
	}
	
}
