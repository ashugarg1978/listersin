package ebaytool.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	@Action(value="/", results={@Result(name="loggedin",location="users.jsp")})
	public String execute() {
		
		ActionContext context = ActionContext.getContext();
		Map request = (Map) context.getParameters();
		
		String email = "";
		String password = "";
		if (request.get("email") != null)
			email = ((String[]) request.get("email"))[0];
		
		if (request.get("password") != null)
			password = ((String[]) request.get("password"))[0];
		
		if (email.equals("fd3s.boost@gmail.com")) {
			return "loggedin";
		}
		
		return SUCCESS;
	}
	
}
