package ebaytool;

import java.util.concurrent.*;
import java.util.*;
import ebaytool.Worker;
import org.json.*;

public class ThreadPool {
	
	private ExecutorService pool;
	
	public ThreadPool () {
		pool = Executors.newFixedThreadPool(18);
	}
	
    public static void main(String[] args) throws Exception {
		
		ThreadPool threadpool = new ThreadPool();
		threadpool.getSellerList();
		
    }
	
	private String getSellerList() throws Exception {
		
		String token = "AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB";
		
		JSONObject json = new JSONObject();
		json.put("DetailLevel", "ReturnAll");
		json.put("WarningLevel", "High");
		json.put("RequesterCredentials", new JSONObject().put("eBayAuthToken", token));
		json.put("StartTimeFrom", "2010-06-01 00:00:00");
		json.put("StartTimeTo",   "2010-08-01 00:00:00");
		json.put("Pagination", new JSONObject().put("EntriesPerPage", "50"));
		json.put("Sort", "1");
		String xml = org.json.XML.toString(json);
		
		Future<String> future = pool.submit(new Worker("GetSellerList", xml));
		String result = future.get();
		
		System.out.println(result);
		
		/*
		  
		Set<Future<String>> set = new HashSet<Future<String>>();
		
        for (int i = 0; i < 10; i++) {
			
			System.out.println("pool.submit :"+i);
			Future<String> future = pool.submit(new Worker(i));
			
			set.add(future);
        }
		
		for (Future<String> future : set) {
			String resultfromworker = future.get();
			System.out.println(resultfromworker);
		}
		*/
		
		return "ok";
	}
	
	
}
