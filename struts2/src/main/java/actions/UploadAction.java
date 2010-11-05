package ebaytool.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
//import org.apache.struts2.StrutsStatics;

//import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;

public class UploadAction extends ActionSupport {
	
	private File PD_PURL_1;
	
	@Action(value="/upload", results={@Result(name="success",location="uploaded.jsp")})
	public String upload() throws Exception {
		
		String basedir = "/usr/local/apache-tomcat-7.0.2/webapps/ROOT";
		//ActionContext context = ActionContext.getContext();
		//ServletContext sc = (ServletContext) context.get(StrutsStatics.SERVLET_CONTEXT);
		
		//upload = new File(sc.getRealPath("/WEB-INF/itemimage"));
		
		File savefile = new File(basedir + "/");
		
		
		return SUCCESS;
	}
	
	public File getPD_PURL_1() {
		return PD_PURL_1;
	}
	
	public void setPD_PURL_1(File uploadedfile) {
		this.PD_PURL_1 = uploadedfile;
	}
	
	public File getUpload() {
		return upload;
	}
}
