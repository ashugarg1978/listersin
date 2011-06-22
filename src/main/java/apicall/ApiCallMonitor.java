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
		
		String logstr = "";
		String logstrprev = "";
		
		while (true) {
			
			Date now = new Date();
			
			logstr = pool18.getPoolSize()
				+ " => "+pool18.getActiveCount()
				+ " => "+pool18.getCompletedTaskCount()
				+ " : "+pool18.getTaskCount();
			
			if (pool18.isShutdown())    logstr += "[shutdown]";
			if (pool18.isTerminating()) logstr += "[terminating]";
			if (pool18.isTerminated())  logstr += "[terminated]";
			
			if (!logstr.equals(logstrprev))
				System.out.println(sdf.format(now).toString()+" "+logstr);
			
			logstrprev = logstr;
			
			Thread.sleep(1000);
			
			if (pool18.isTerminated()) break;
		}
		
		return "";
	}
	
}
