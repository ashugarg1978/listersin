package ebaytool.apicall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApiCallMonitor extends ApiCall {
	
	public ApiCallMonitor() throws Exception {
	}
	
	public String call() throws Exception {
		
		log("ApiCallMonitor start.");
		
		String logstr = "";
		String logstrprev = "";
		
		while (true) {
			
			logstr = pool18.getActiveCount()
				+ " : "+pool18.getCompletedTaskCount()
				+ " : "+pool18.getTaskCount()
				+ " thread(s).";
			
			if (pool18.isShutdown())    logstr += " shutdown";
			if (pool18.isTerminating()) logstr += " terminating";
			if (pool18.isTerminated())  logstr += " terminated";
			
			if (!logstr.equals(logstrprev)) {
				log(logstr);
			}
			
			logstrprev = logstr;
			
			Thread.sleep(1000);
			
			if (pool18.isTerminated()) {
				log("ApiCallMonitor stop.");
				break;
			}
		}
		
		return "";
	}
}
