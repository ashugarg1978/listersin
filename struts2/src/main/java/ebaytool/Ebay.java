package ebaytool;

import com.opensymphony.xwork2.ActionSupport;

public class Ebay extends ActionSupport {
	
	private String msg;
	
    public String execute() throws Exception {
		
		/* do something */
		setMsg("hogehogefoobar");
		
		return SUCCESS;
    }
	
	public void setMsg(String msg) {
		this.msg = msg;
		return;
	}
	
	public String getMsg() {
		return msg;
	}
}
