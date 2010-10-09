package ebaytool;

import java.util.concurrent.*;
import java.util.*;
import ebaytool.Worker;

public class ThreadPoolTest {
	
    public static void main(String[] args) throws Exception {
		
        ExecutorService pool = Executors.newFixedThreadPool(18);
		
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
    }
	
}
