package ebaytool.apicall;

import ebaytool.apicall.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

public class Daemon {
	
	private ThreadPoolExecutor pool;
	
	public Daemon() throws Exception {
		pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
	}
	
	public void start() throws Exception {
		
		ServerSocket serversocket = new ServerSocket(8181, 10);
		System.out.println("waiting port 8181");
		
		while (true) {
			
			Socket socket = serversocket.accept();
			System.out.println("accept from "+socket.getInetAddress().toString());
			
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
			out.write("connected.\r\n");
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = "";
			
			do {
				try {
					
					message = in.readLine();
					System.out.println("client > "+message);
					
					if (message.equals("AddItems")) {
						
						pool.submit(new AddItems());
						
					} else if (message.equals("GetSellerList")) {
						
						pool.submit(new GetSellerList());
						
					} 
					
				} catch (Exception e) {
					System.out.println("client > "+e.toString());
				}
				
			} while (!message.equals("bye") && !message.equals("shutdown"));
			
			socket.close();
			
			if (message.equals("shutdown")) {
				System.out.println("shutdown...");
				serversocket.close();
				break;
			}
		}
		
		return;
	}
	
    public static void main(String[] args) throws Exception {
		
		Daemon daemon = new Daemon();
		daemon.start();
		
		return;
	}
	
}
