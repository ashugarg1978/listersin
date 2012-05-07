package ebaytool.actions;
 
import com.mongodb.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.xml.XMLSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
 
public class BaseAction extends ActionSupport implements ServletContextAware,
														 SessionAware,
														 ServletRequestAware,
														 ServletResponseAware,
														 ParameterAware {
	
    protected ServletContext context = null;
    protected Map<String, Object> session = null;
    protected Map<String, String[]> parameters = null;
    protected HttpServletRequest request = null;
    protected HttpServletResponse response = null;
	
	protected DB db;
	protected BasicDBObject user;
	protected String basedir;
	protected String version;
	protected BasicDBObject configdbo;
	protected int daemonport;
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public BaseAction() throws Exception {
	}
	
    @Override
	public void setServletContext(ServletContext context) {
        this.context = context;
		
		try {
			
			basedir = context.getRealPath("");
			basedir = basedir.replace("/usr/local/apache-tomcat/webapps", "/var/www");
			version = basedir;
			
			basedir = basedir.replaceAll("##.+$", "");
			version = version.replaceAll("^.+##", "");
			
			log.debug("basedir: "+basedir+" "+version);
			
			configdbo = convertXML2DBObject(readfile(basedir+"/config/config.xml"));
			daemonport = Integer.parseInt(configdbo.getString("daemonport"));
			
			MongoConnect mc = MongoConnect.getInstance(configdbo.getString("database"));
			db = mc.getDB();
			
			if (session.get("email") != null) {
				BasicDBObject query = new BasicDBObject();
				query.put("email", session.get("email").toString());
				user = (BasicDBObject) db.getCollection("users").findOne(query);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
    }
	
    @Override
	public void setSession(Map<String, Object> session) {
        this.session = session;
    }
	
    @Override
	public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
	
    @Override
	public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
	public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }
	
	// todo: many same methods...
	public BasicDBObject convertXML2DBObject(String xml) {
		
		XMLSerializer xmlSerializer = new XMLSerializer(); 
		//xmlSerializer.setTypeHintsCompatibility(true);
		xmlSerializer.setTypeHintsEnabled(false);
		
		net.sf.json.JSON json = xmlSerializer.read(xml);
		
		BasicDBObject dbobject = (BasicDBObject) com.mongodb.util.JSON.parse(json.toString());
		
		return dbobject;
	}
	
	public String readfile(String filename) throws Exception {
		
		String data = "";
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			data = data + line;
		}
		br.close();
		
		return data;
	}
}
