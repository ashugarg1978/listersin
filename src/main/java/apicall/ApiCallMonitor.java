package ebaytool.apicall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApiCallMonitor extends ApiCall {
	
	public ApiCallMonitor() throws Exception {
	}
	
	public String call() throws Exception {
    
		String logstr = "";
		String logstrprev = "";
    
		String memstr = "";
		String memstrprev = "";
		
		while (true) {
      
      /* Log */
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
      
      /* Memory usage */
      long free  = Runtime.getRuntime().freeMemory()  / 1024 / 1024;
      long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
      long max   = Runtime.getRuntime().maxMemory()   / 1024 / 1024;
      
      memstr = "memory " + free + " " + total + " " + max;
      
			if (!memstr.equals(memstrprev)) {
				//log(memstr);
			}
			memstrprev = memstr;
      
      /* Sleep 1 second */
			Thread.sleep(1000);
			
			if (pool18.isTerminated()) {
				log("ApiCallMonitor stop.");
				break;
			}
		}
		
		return "";
	}
}
