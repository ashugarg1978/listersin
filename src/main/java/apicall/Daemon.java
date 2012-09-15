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
            
      String callclass = in.readLine();
			if (callclass.equals("shutdown")) break;
			
      ArrayList<String> als = new ArrayList<String>();
      
      String line = "";
      while ((line = in.readLine()) != null) {
        if (line.equals("")) break;
        als.add(line.replace("\\n", "\n"));
      }
      
      String[] messages = new String[als.size()];
      messages = (String[]) als.toArray(messages);
			
      System.out.println(sdf.format(new Date()).toString()
                         +" "+callclass+" ("+als.size()+" args)");
      for (String tmpstr : messages) {
        System.out.println(sdf.format(new Date()).toString()+" - "+tmpstr);
      }
      
			try {
        
        // ref: http://stackoverflow.com/questions/5142821/illegalargumentexception-wrong-number-of-arguments-in-java-constructor-newinsta
        // ref: http://stackoverflow.com/questions/5760569/problem-with-constructing-class-using-reflection-and-array-arguments
        
				Callable task = null;
				Class apiclass = Class.forName("ebaytool.apicall."+callclass);
				
				if (als.size() == 0) {
					Constructor cnst = apiclass.getConstructor();
					task = (Callable) cnst.newInstance();
				} else {
					Constructor cnst = apiclass.getConstructor(String[].class);
					task = (Callable) cnst.newInstance(new Object[] {messages});
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
		out.println("shutdown\n\n");
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
