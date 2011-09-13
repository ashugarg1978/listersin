package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.*;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.*;

public class downloadFile extends ApiCall {
	
	public downloadFile() throws Exception {
	}
	
	public String call() throws Exception {
		
		callback("");
		
		return "";
	}
	
	public String callback(String filename) throws Exception {
		
		String savedir = "/var/www/ebaytool.jp/logs/apicall/downloadFile";
		
		FileDataSource fds = new FileDataSource(savedir+"/"+filename);
		MimeMultipart mmp = new MimeMultipart(fds);
		BodyPart bp = mmp.getBodyPart(1);
		
		ZipInputStream zis = new ZipInputStream(bp.getInputStream());
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			log("unzip: " +entry);
			int count;
			byte data[] = new byte[1024];
			
            FileOutputStream fos = new FileOutputStream(savedir+"/"+entry.getName());
            BufferedOutputStream dest = new BufferedOutputStream(fos, 1024);
            while ((count = zis.read(data, 0, 1024)) != -1) {
				dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
		}
		zis.close();

		/* read xml file */
		
		
		
		return "";
	}
}
