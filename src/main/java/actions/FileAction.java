package ebaytool.actions;

import ebaytool.actions.BaseAction;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

public class FileAction extends BaseAction {
  
	private List<File>   fileUpload            = new ArrayList<File>();
	private List<String> fileUploadContentType = new ArrayList<String>();
	private List<String> fileUploadFileName    = new ArrayList<String>();
	private List<String> savedfilename         = new ArrayList<String>();
  
	public FileAction() throws Exception {
	}
  
	/**
   * ref: http://www.mkyong.com/struts2/struts-2-upload-multiple-files-example/
   */
  @Action(value="/file/upload", results={@Result(name="success",location="uploaded.jsp")})
	public String upload() throws Exception {
    
		String savedir  = basedir + "/webroot/itemimage";
    String user_id  = user.getString("_id");
		String id       = ((String[]) parameters.get("id"))[0];
		String userid   = ((String[]) parameters.get("userid"))[0];
		String divclass = ((String[]) parameters.get("divclass"))[0];
    log.debug("divclass:"+divclass);
    
		int i = 0;
    for (File file: fileUpload) {
      
			String timestampstr = basetimestamp.replaceAll("[-+Z\\s:]", "");
			String extension = fileUploadFileName.get(i).replaceAll("^.+\\.", "");
			String tmpname = user_id+"_"+id+"_"+timestampstr+"_"+i+"."+extension;
			
			File savefile = new File(savedir + "/" + tmpname);
      FileUtils.copyFile(file, savefile);
			
      String url = "http://" + configdbo.getString("hostname") + "/itemimage/" + tmpname;
			
			String[] args = {"UploadSiteHostedPictures", 
                       session.get("email").toString(),
                       userid,
                       url};
			
			String epsurl = writesocket(args);
			
			savedfilename.add(i, epsurl);
      
			i++;
    }
    
		return SUCCESS;
	}
  
  public List<File> getFileUpload() {
		return fileUpload;
	}
  
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}
  
	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}
  
	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
  
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}
    
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	
	public List<String> getSavedfilename() {
		return savedfilename;
	}
  
}
