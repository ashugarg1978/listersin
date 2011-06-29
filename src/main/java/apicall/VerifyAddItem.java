package ebaytool.apicall;

public class VerifyAddItem extends ApiCall {
	
	private String email;
	
	public VerifyAddItem() throws Exception {
	}
	
	public VerifyAddItem(String email) throws Exception {
		this.email = email;
	}
	
	public String call() throws Exception {
		
		return "";
	}
	
	public String callback(String responsexml) throws Exception {
		
		return "";
	}
}
