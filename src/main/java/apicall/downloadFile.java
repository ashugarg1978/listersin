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
			byte data[] = new byte[1024];
			
            FileOutputStream fos = new FileOutputStream(savedir+"/"+site+".xml");
            BufferedOutputStream dest = new BufferedOutputStream(fos, 1024);
            while ((count = zis.read(data, 0, 1024)) != -1) {
				dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
		}
		zis.close();
		
		/* read xml file */
		File file = new File(savedir+"/"+site+".xml");
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		net.sf.json.JSON json = xmlSerializer.readFromFile(file);
		BasicDBObject resdbo = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		
		DBCollection coll = db.getCollection(site+".CategorySpecifics");
		if (db.collectionExists(site+".CategorySpecifics")) {
			coll.drop();
		}
		coll.insert((List<DBObject>) resdbo.get("Recommendations"));
		
		return "";
	}
}
