package ebaytool.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

public class UploadAction extends ActionSupport {
	
	private File uploadfile;
	private String savedfilename;
	
	@Action(value="/upload", results={@Result(name="success",location="uploaded.jsp")})
	public String upload() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		Map request = (Map) context.getParameters();
		String fileindex = ((String[]) request.get("fileindex"))[0];
		String id = ((String[]) request.get("id"))[0];
		
		String basedir = "/usr/local/apache-tomcat-7.0.2/webapps/ROOT";
		
		// todo: get basedir
		//ActionContext context = ActionContext.getContext();
		//ServletContext sc = (ServletContext) context.get(StrutsStatics.SERVLET_CONTEXT);
		//upload = new File(sc.getRealPath("/WEB-INF/itemimage"));
		
		savedfilename = id+"_"+fileindex+".jpg";
		File savefile = new File(basedir + "/itemimage/" + savedfilename);
		FileUtils.copyFile(uploadfile, savefile);
		
		return SUCCESS;
	}
	
	public File getUploadfile() {
		return uploadfile;
	}
	
	public void setUploadfile(File uploadedfile) {
		this.uploadfile = uploadedfile;
	}
	
	public String getSavedfilename() {
		return savedfilename;
	}
}
