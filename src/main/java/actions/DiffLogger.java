package ebaytool.actions;

import com.mongodb.*;
import java.io.*;
import org.json.JSONObject;

public class DiffLogger {
	
	
	public void savediff (String id, String beforestr, String afterstr) throws Exception {
		
		JSONObject before = new JSONObject(beforestr);
		JSONObject after  = new JSONObject(afterstr);
		
		FileWriter fstream = new FileWriter("/var/www/ebaytool.jp/logs/diff/"+id+".0.js");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(before.toString(4));
		out.close();
		
		fstream = new FileWriter("/var/www/ebaytool.jp/logs/diff/"+id+".1.js");
		out = new BufferedWriter(fstream);
		out.write(after.toString(4));
		out.close();
		
		return;
	}	
}
