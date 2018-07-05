package pages;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import environment.DbConnection;

public class temp implements IInvokedMethodListener  {

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		System.out.println("start");
		System.out.println(method.getTestMethod().getMethodName());
		System.out.println(method.getTestResult().getInstanceName());
		System.out.println(method.getTestResult().getName());
		System.out.println(testResult.getName());
		System.out.println("end");
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		System.out.println("before");
	}


	

	
	
}