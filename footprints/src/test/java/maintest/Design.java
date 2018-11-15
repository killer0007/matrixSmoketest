package maintest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;

public  interface Design  { 
	public void beforeSuit();
	public void beforetest() throws FileNotFoundException, IOException, Exception;
	public void setup(Method method) throws FileNotFoundException, IOException;
	public void login() throws InterruptedException, Exception ;
	public void tearDown(ITestResult result, Method method) throws IOException;
	public void teardown() throws Exception;
	public void afterSuite(); 
}
