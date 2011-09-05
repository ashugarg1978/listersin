package ebaytool.actions;

import com.mongodb.*;
import org.apache.log4j.Logger;

public class MongoConnect {
	
	private static MongoConnect _instance;
	private DB db;
	
	private MongoConnect() throws Exception {
		db = new Mongo().getDB("ebay");
		
		Logger log = Logger.getLogger(this.getClass());
		log.debug("single connect?");
	}
	
	public static synchronized MongoConnect getInstance() throws Exception {
		
		if (_instance == null) {
			_instance = new MongoConnect();
		}
		
		return _instance;
	}
	
	public DB getDB() {
		return db;
	}
}
