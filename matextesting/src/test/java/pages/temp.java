package pages;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

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

public class temp   {

	public static void main(String[] args) {
		int timeout=10;
		int loopcount=(timeout*1000)/200;
		System.out.println(loopcount);
		System.out.println((loopcount*200));
	}

	
	
}