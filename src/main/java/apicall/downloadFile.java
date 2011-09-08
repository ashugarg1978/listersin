package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.activation.FileDataSource;
import javax.mail.internet.*;
import javax.mail.util.*;

public class downloadFile extends ApiCall {
	
	public downloadFile() throws Exception {
	}
	
	public String call() throws Exception {
		
		callback("");
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS");
		Date now = new Date();
		String timestamp = sdf.format(now).toString();
		writelog("downloadFile/"+timestamp+".xml", responsexml);
		
		
		FileDataSource fds = new FileDataSource
			("/var/www/ebaytool.jp/logs/apicall/downloadFile/2011-09-08_09-13-54.569.xml");
		
		ByteArrayDataSource bads = new ByteArrayDataSource(fds.getInputStream(), "multipart/*");
		
		MimeMultipart mm = new MimeMultipart(bads);
		log("getcount:"+mm.getCount());
		
		/*
		FileInputStream fis = new FileInputStream
			("/var/www/ebaytool.jp/logs/apicall/downloadFile/2011-09-08_09-13-54.569.xml");
		
		MimeBodyPart mbp = MimeMultipart.createMimeBodyPart(fis);
		
		
		MimeBodyPart bodyPart = new MimeBodyPart(fis);
		MimeMultipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(bodyPart);
		log("getcount:"+multiPart.getCount());
		//log(multiPart.getContent().toString());
		
		
		log("contenttype:"+fds.getContentType());
		
		//String decoded = MimeUtility.decodeText(responsexml);
		//log("decoded:"+decoded);
		
		
		//BasicDBObject resdbo = convertXML2DBObject(responsexml);
		*/
		
		return "";
	}
}
