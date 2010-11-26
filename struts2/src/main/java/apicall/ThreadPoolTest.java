package ebaytool.apicall;

import java.util.concurrent.*;
import java.util.*;

public class ThreadPoolTest {
	
	private ThreadPoolExecutor pool;
	
    final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(5);
	
	public ThreadPoolTest() throws Exception {
		pool = new ThreadPoolExecutor(18, 18, 10, TimeUnit.SECONDS, queue);
	}
	
}
