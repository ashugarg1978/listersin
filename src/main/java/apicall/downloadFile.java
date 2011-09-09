package ebaytool.apicall;

import com.mongodb.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
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
	
	public String callback(String responsexml) throws Exception {
		
		//log(responsexml);
		
		//System.setProperty("mail.mime.multipart.ignoreexistingboundaryparameter", "true");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSS");
		Date now = new Date();
		String timestamp = sdf.format(now).toString();
		writelog("downloadFile/"+timestamp+".xml", responsexml);

		String[] arrxml = responsexml.split("\r\n\r\n");
		writelog("downloadFile/"+timestamp+".xml2", arrxml[1]);
		
		FileDataSource fds = new FileDataSource
			("/var/www/ebaytool.jp/logs/apicall/downloadFile/"+timestamp+".xml");
		FileDataSource fds2 = new FileDataSource
			("/var/www/ebaytool.jp/logs/apicall/downloadFile/"+timestamp+".xml2");
		
		Properties props = new Properties();
		//props.put("mail.mime.multipart.ignoremissingboundaryparameter", true);
		props.put("mail.mime.multipart.ignoreexistingboundaryparameter", true);
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session, fds.getInputStream());
		MimeMessage message2 = new MimeMessage(session, fds2.getInputStream());
		log(message.getContentType());
		//log(message.getContent().toString());
		
		Part part = (Part) message;
		Part part2 = (Part) message;
		if (part.isMimeType("multipart/*")) { // マルチパートの場合
			//MimeMultipart mmp = (MimeMultipart) part.getContent();
			//log("getcount:"+mmp.getCount());
			
			//ByteArrayDataSource bads = new ByteArrayDataSource
			//	(fds.getInputStream(), part.getContentType());
			
			//Multipart mp = (Multipart) part.getContent();
			Multipart mp = (Multipart) part.getContent();
			//Multipart mp = new MimeMultipart(bads);
			log("getcount:"+mp.getCount());
		}
		//log(p.toString());
		
		/*
		
		ByteArrayDataSource bads = new ByteArrayDataSource(fds.getInputStream(), "multipart/*");
		
		MimeMultipart mm = new MimeMultipart(bads);
		log("getcount:"+mm.getCount());
		*/
		
		/*
		String decoded = MimeUtility.decodeText(responsexml);
		log("decoded:"+decoded);
		
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
		
		
		
		//BasicDBObject resdbo = convertXML2DBObject(responsexml);
		*/
		
		return "";
	}
}
