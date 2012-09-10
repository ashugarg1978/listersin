package ebaytool.apicall;

import java.util.concurrent.*;

public class Test extends ApiCall implements Callable {
	
	public Test(String[] args) throws Exception {
        for (String arg : args) {
            System.out.println("arg:"+arg);
        }
	}
    
	public String call() throws Exception {
		return "";
	}
    
}
