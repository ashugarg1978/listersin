package ebaytool;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.*;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.concurrent.*;
import java.util.*;
import ebaytool.GetSellerList;

public class ThreadPool {
	
	private ExecutorService pool;

	private String token = "AgAAAA**AQAAAA**aAAAAA**KHmBTA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoD5mKpw2dj6x9nY+seQ**Q0UBAA**AAMAAA**vIfXjO5I7JEMxVTJem2CIu9tUmKl1ybRTAGc4Bo/RNktrvd+MQ0NMHvUp7qRyWknHZ10fPIGLaSKq0FDQDQVg8hQafeYcmtfPcxvHnESRPSx6IIcad4GPne8vJjvzRgj1quv40pVatq4mId5tRU8D1DwEm930K3JShD92Z+8AXG6qO8TVBf/r4auftBdGNnwStY/01gz0dUXyDhyi3G94yu9Cv8HcyhAvM67yUQKW+45A9WnWuRCrxVgx3xYFUKhTT+8tJb4KtDgH65zfQuk4og6TvqD6qO85FPS+hSpAX7dFYxFPgw5R61VXJBm4LD4seJA1/E+2fA1Ge5UUplH0aS8hTs0yZYIeBx2WHs9OhV5HaAY5lj2kNm3h59GbheSsBfjReMk/Yxm3X9rLRalw20utx4Z4MU+JZgMePouNAcceDHsFRylE+e2nnDfddx3peQOpwrbEtIm9fOqBahBs7MAy+IVVY8CcvoEn+Msoevz18jpTj0P+1h/fBvdliedAPOmMuiafYfqtYmIfTSTWIJzAfvcpBsZD3cW+ilo6GfJ4875x2R221qEUwS1AYT1GIK5Ctip/pKAxKT/ugf18PtLd3FJ5jVWziTsFFZ07ZVjihShtsXLsORQBInvMqE1PgniJ3Hpdsqp85eIo1pwhlLBD/2rsCRTodGOFX9t47RMST1WKAjzAqPW0XnqfPvYfuII7kaqL/YT0pV/eyNzdiFjtXklWGDSPNdQfoSC1Uh7mxMXNxx5HHlV98QS/jTB";
	
	public ThreadPool () {
		pool = Executors.newFixedThreadPool(18);
	}
	
    public static void main(String[] args) throws Exception {
		
		ThreadPool threadpool = new ThreadPool();
		threadpool.getSellerList();
		
		return;
    }
	
	private String getSellerList() throws Exception {
		
		BasicDBObject pagination = new BasicDBObject();
		pagination.put("EntriesPerPage", "3");
		pagination.put("PageNumber", "1");
		
		BasicDBObject dbobject = new BasicDBObject();
		dbobject.put("DetailLevel", "ReturnAll");
		dbobject.put("WarningLevel", "High");
		dbobject.put("RequesterCredentials", new BasicDBObject("eBayAuthToken", token));
		dbobject.put("StartTimeFrom", "2010-06-01 00:00:00");
		dbobject.put("StartTimeTo",   "2010-08-01 00:00:00");
		dbobject.put("Pagination", pagination);
		dbobject.put("Sort", "1");
		
		Future<BasicDBObject> future = pool.submit(new GetSellerList(dbobject));
		BasicDBObject result = future.get();
		
		int pages = Integer.parseInt(((BasicDBObject) result.get("PaginationResult"))
									 .get("TotalNumberOfPages").toString());
		
		for (int i=2; i<=pages; i++) {
			BasicDBObject dbocopy = (BasicDBObject) dbobject.clone();
			((BasicDBObject) dbocopy.get("Pagination")).put("PageNumber", i);
			pool.submit(new GetSellerList(dbocopy));
		}
		
		return "OK";
	}
	
}
