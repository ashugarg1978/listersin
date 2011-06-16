package ebaytool.actions;
 
import com.mongodb.*;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public BaseAction() throws Exception {
		if (db == null) {
			db = new Mongo().getDB("ebay");
		}
	}
	
    @Override
	public void setServletContext(ServletContext context) {
        this.context = context;
    }
	
    @Override
	public void setSession(Map<String, Object> session) {
        this.session = session;
		
		if (session.get("email") != null) {
			BasicDBObject query = new BasicDBObject();
			query.put("email", session.get("email").toString());
			user = (BasicDBObject) db.getCollection("users").findOne(query);
		}
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
	
}
