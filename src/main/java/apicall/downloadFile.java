package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.*;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class downloadFile extends ApiCall {
	
	public downloadFile() throws Exception {
	}
	
	public String call() throws Exception {
		
		callback("");
		
		return "";
	}
	
	public String callback(String site) throws Exception {
		
		String savedir = "/var/www/ebaytool.jp/logs/apicall/downloadFile";
		
		FileDataSource fds = new FileDataSource(savedir+"/"+site+".raw");
		MimeMultipart mmp = new MimeMultipart(fds);
		BodyPart bp = mmp.getBodyPart(1);
		
		ZipInputStream zis = new ZipInputStream(bp.getInputStream());
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			log("unzip: "+entry+" "+site);
			int count;
			byte data[] = new byte[4096];
			
            FileOutputStream fos = new FileOutputStream(savedir+"/"+site+".xml");
            BufferedOutputStream dest = new BufferedOutputStream(fos, 4096);
            while ((count = zis.read(data, 0, 4096)) != -1) {
				dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
		}
		zis.close();
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		
		File file = new File(savedir+"/"+site+".xml");
		
		net.sf.json.JSON json = xmlSerializer.readFromFile(file);
		
		log(site+" done 0.5?");
		BasicDBObject resdbo = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		log(site+" done 1?");
		
		DBCollection coll = db.getCollection(site+".CategorySpecifics");
		if (db.collectionExists(site+".CategorySpecifics")) {
			coll.drop();
		}
		log(site+" done 2?");
		coll.insert((List<DBObject>) resdbo.get("Recommendations"));
		log(site+" done?");
		
		return "";
	}
	
	private static String readFileAsString(String filePath) throws java.io.IOException{
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f = null;
		try {
			f = new BufferedInputStream(new FileInputStream(filePath));
			f.read(buffer);
		} finally {
			if (f != null) try { f.close(); } catch (IOException ignored) { }
		}
		return new String(buffer);
	}
	
}
