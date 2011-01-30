package ebaytool.apicall;

import ebaytool.apicall.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

public class Daemon {
	
	public void start() throws Exception {
		
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		ServerSocket serversocket = new ServerSocket(8181, 10);
		
		pool.submit(new ApiCallMonitor());
		
		while (true) {
			
			Socket socket = serversocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = "";
			
			message = in.readLine();
			if (message.equals("shutdown")) break;
			
			System.out.println(message);
			Callable task = (Callable) Class.forName("ebaytool.apicall."+message).newInstance();
			pool.submit(task);
			
			in.close();
			socket.close();
		}
		
		ApiCall apicall = new ApiCall();
		apicall.shutdown();
		
		serversocket.close();
		pool.shutdown();
		
		return;
	}
	
	public void stop() throws Exception {
		
		Socket socket = new Socket("localhost", 8181);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("shutdown");
		out.close();
		socket.close();
		
		return;
	}
	
    public static void main(String[] args) throws Exception {
		
		Daemon daemon = new Daemon();
		String action = args[0];
		if (action.equals("stop")) {
			daemon.stop();
		} else {
			daemon.start();
		}
		
		return;
	}
	
}
