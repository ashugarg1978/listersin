package ebaytool.apicall;

import ebaytool.apicall.*;

import java.util.concurrent.*;
import java.util.*;

public class Daemon {
	
	private ThreadPoolExecutor pool;
	
	public Daemon() throws Exception {
		pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
	}
	
	public void start() throws Exception {
		
		pool.submit(new GetSellerList());
		
		return;
	}
	
    public static void main(String[] args) throws Exception {
		
		Daemon d = new Daemon();
		d.start();
		
		return;
	}
	
}
