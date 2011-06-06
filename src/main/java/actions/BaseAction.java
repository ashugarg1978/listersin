package ebaytool.actions;
 
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
    @Override
	public void setServletContext(ServletContext context) {
        this.context = context;
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
	
}
