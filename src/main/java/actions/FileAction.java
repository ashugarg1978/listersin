package ebaytool.actions;

//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
import ebaytool.actions.BaseAction;
import java.io.File;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

public class FileAction extends BaseAction {
	
	private File uploadfile;
	private String savedfilename;
	
	public FileAction() throws Exception {
	}
	
	@Action(value="/file/upload", results={@Result(name="success",location="uploaded.jsp")})
	public String upload() throws Exception {
		
		String fileindex = ((String[]) parameters.get("fileindex"))[0];
		String id = ((String[]) parameters.get("id"))[0];
		
		String savedir = "/var/www/ebaytool.jp/webroot/itemimage";
		
		// todo: get basedir
		//ActionContext context = ActionContext.getContext();
		//ServletContext sc = (ServletContext) context.get(StrutsStatics.SERVLET_CONTEXT);
		//upload = new File(sc.getRealPath("/WEB-INF/itemimage"));
		
		// todo: file extension
		savedfilename = id+"_"+fileindex+".jpg";
		
		File savefile = new File(savedir + "/" + savedfilename);
		
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
