package ebaytool.apicall;

import ebaytool.apicall.GeteBayDetails;

import java.util.concurrent.*;
import java.util.*;

public class ThreadPoolTest {
	
	private ThreadPoolExecutor pool;
	
    final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(5);
	
	public ThreadPoolTest() throws Exception {
		//pool = new ThreadPoolExecutor(18, 18, 10, TimeUnit.SECONDS, queue);
		pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(18);
	}
	
	public void aaa() throws Exception {
		
		for (int i=1; i<=100; i++) {
			pool.submit(new GeteBayDetails());
			Thread.sleep(i*(i%3)+100);
			System.out.println(pool.getActiveCount()
							   + ":" + pool.getCompletedTaskCount()
							   + ":" + pool.getTaskCount());
		}
		
		for (int j=1; j<=100; j++) {
			Thread.sleep(1000);
			System.out.println(pool.getActiveCount()
							   + ":" + pool.getCompletedTaskCount()
							   + ":" + pool.getTaskCount());
		}
		
		return;
	}
	
    public static void main(String[] args) throws Exception {
		
		ThreadPoolTest tpt = new ThreadPoolTest();
		tpt.aaa();
		
		return;
	}
	
}
