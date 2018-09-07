package livetesting;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;

public class temp {
public static void main(String[] args) throws IOException {
	WebDriver driver =new ChromeDriver();
	driver.get("http://192.168.2.16/MatexTesting");
	String externalJS = Files.toString( new File("D:\\gopi\\login.js"), Charset.forName("utf-8"));
//System.out.println(externalJS);
	// Execute, assume no arguments, and no value to return
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript(externalJS);
	
}
}
