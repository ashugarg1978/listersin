package ebaytool.apicall;

import com.mongodb.BasicDBObject;

import ebaytool.apicall.ApiCall;

import java.util.concurrent.*;

public class SetNotificationPreferences extends ApiCall implements Callable {
	
	private String userid;
	private String requestxml;
	
	public SetNotificationPreferences (String userid, String requestxml) {
		this.userid     = userid;
		this.requestxml = requestxml;
		//this.requestxml = convertDBObject2XML(requestdbobject);
	}
	
	public BasicDBObject call() throws Exception {
		
		String responsexml = callapi(0, requestxml);
		writelog("SNP.req."+userid, requestxml);
		writelog("SNP.res."+userid, responsexml);
		
		BasicDBObject responsedbo = convertXML2DBObject(responsexml);
		
		return responsedbo;
	}
	
}
