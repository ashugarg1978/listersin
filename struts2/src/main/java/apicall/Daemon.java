package ebaytool.apicall;

import ebaytool.apicall.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class Daemon {
	
	public void start() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println(sdf.format(now).toString()+" ebaytoold started.");
		
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		ServerSocket serversocket = new ServerSocket(8181, 10);
		
		pool.submit(new ApiCallMonitor());
		
		while (true) {
			
			Socket socket = serversocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = "";
			
			message = in.readLine();
			if (message.equals("shutdown")) break;
			
			System.out.println(sdf.format(new Date()).toString()+" "+message);
			
			String[] arrmsg = message.split(" ");
			
			Callable task = null;
			if (arrmsg.length == 3) {
				Class apiclass = Class.forName("ebaytool.apicall."+arrmsg[0]);
				Constructor cnst = apiclass.getConstructor(String.class, String.class);
				task = (Callable) cnst.newInstance(arrmsg[1], arrmsg[2]);
			} else {
				task = (Callable) Class.forName("ebaytool.apicall."+message).newInstance();
			}
			
			Future f = pool.submit(task);
			
			try {
				f.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			in.close();
			socket.close();
		}
		
		ApiCall apicall = new ApiCall();
		apicall.shutdown();
		
		serversocket.close();
		pool.shutdown();
		
		now = new Date();
		System.out.println(sdf.format(now).toString()+" ebaytoold shutdown.");
		
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
	
    public static void main(String[] args) {
		
		Daemon daemon = new Daemon();
		String action = args[0];
		try {
			if (action.equals("stop")) {
				daemon.stop();
			} else {
				daemon.start();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return;
	}
	
}
