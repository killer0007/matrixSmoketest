package maintest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;

abstract class Design {
	public abstract void beforeSuit();
	public abstract void beforetest() throws FileNotFoundException, IOException;
	public abstract void setup(Method method) throws FileNotFoundException, IOException;
	public abstract void Login() throws InterruptedException, Exception ;
	public abstract void tearDown(ITestResult result, Method method) throws IOException;
	public abstract void teardown() throws Exception;
	public abstract void afterSuite();
}
