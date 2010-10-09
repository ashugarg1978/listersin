package ebaytool;

import java.util.concurrent.*;

public class Worker implements Runnable {
	
	private int i;
	
	Worker (int j) {
		i = j;
	}
	
	public void run() {
		
		for (int k=0; k<20; k++) {
			
			System.out.println(i+" : "+k);
			
			try {
				Thread.sleep(1000*(i+1));
			} catch (Exception e) {
				
			}
			
		}

	}
	
}
