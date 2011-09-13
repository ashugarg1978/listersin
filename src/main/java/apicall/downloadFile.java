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
		
		/* save zip file */
		String savedir = "/var/www/ebaytool.jp/logs/apicall/downloadFile";
		FileDataSource fds = new FileDataSource(savedir+"/"+filename);
		
		MimeMultipart mmp = new MimeMultipart(fds);
		
		BodyPart bp = mmp.getBodyPart(1);
		log("disposition:"+bp.getDisposition());
		
		File file = new File(savedir+"/"+filename+".zip");
		InputStream is = bp.getInputStream();
		OutputStream os = new FileOutputStream(file);
		
		byte buf[]=new byte[1024];
		int len;
		while ((len=is.read(buf)) > 0) {
			os.write(buf,0,len);
		}
		os.close();
		is.close();
		
		/* unzip file */
		BufferedOutputStream dest = null;
		FileInputStream fis = new FileInputStream(savedir+"/"+filename+".zip");
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			log("unzip: " +entry);
			int count;
			byte data[] = new byte[1024];
			
            FileOutputStream fos = new FileOutputStream(savedir+"/"+entry.getName());
            dest = new BufferedOutputStream(fos, 1024);
            while ((count = zis.read(data, 0, 1024)) != -1) {
				dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
		}
		zis.close();
		
		return "";
	}
}
