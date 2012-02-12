package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import net.sf.json.xml.XMLSerializer;

public class Daemon {
	
	public static String basedir;
	public static int port;
	
	public Daemon() throws Exception {
		
		basedir = System.getProperty("user.dir");
		
		String data = "";
		
		FileReader fr = new FileReader(basedir+"/config/config.xml");
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			data = data + line;
		}
		br.close();
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		xmlSerializer.setTypeHintsEnabled(false);
		net.sf.json.JSON json = xmlSerializer.read(data);
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		
		port = Integer.parseInt(dbobject.getString("daemonport"));
	}
	
	public void start() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println("-------------------------------------------------");
		System.out.println(sdf.format(now).toString()+" ebaytoold started.");
		System.out.println(sdf.format(now).toString()+" basedir: "+basedir);
		System.out.println(sdf.format(now).toString()+" port: "+port);
		
		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		ServerSocket serversocket = new ServerSocket(port, 10);
		
		pool.submit(new ApiCallMonitor());
		
		while (true) {
			
			Socket socket = serversocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			String message = "";
			
			message = in.readLine();
			
			if (message.equals("shutdown")) break;
			
			System.out.println(sdf.format(new Date()).toString()+" "+message);
			
			String[] arrmsg = message.split(" ");
			
			Callable task = null;
			Class apiclass = Class.forName("ebaytool.apicall."+arrmsg[0]);
			
			try {
				
				if (arrmsg.length == 6) {
					Constructor cnst = apiclass.getConstructor
						(String.class, String.class, String.class, String.class, String.class);
					task = (Callable) cnst.newInstance
						(arrmsg[1], arrmsg[2], arrmsg[3], arrmsg[4], arrmsg[5]);
				} else if (arrmsg.length == 4) {
					Constructor cnst = apiclass.getConstructor
						(String.class, String.class, String.class);
					task = (Callable) cnst.newInstance(arrmsg[1], arrmsg[2], arrmsg[3]);
				} else if (arrmsg.length == 3) {
					Constructor cnst = apiclass.getConstructor(String.class, String.class);
					task = (Callable) cnst.newInstance(arrmsg[1], arrmsg[2]);
				} else if (arrmsg.length == 2) {
					Constructor cnst = apiclass.getConstructor(String.class);
					task = (Callable) cnst.newInstance(arrmsg[1]);
				} else {
					Constructor cnst = apiclass.getConstructor();
					task = (Callable) cnst.newInstance();
				}
				
				Future<String> f = pool.submit(task);
				String result = "";
				
				result = f.get();
				out.println(result);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			out.close();
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
		
		Socket socket = new Socket("localhost", port);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println("shutdown");
		out.close();
		socket.close();
		
		return;
	}
	
    public static void main(String[] args) {
		
		try {
			
			Daemon daemon = new Daemon();
			String action = args[0];
			
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
