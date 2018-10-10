package maintest;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class temp {
	public static WebDriver driver;

	public static void main(String[]  args) throws MalformedURLException, InterruptedException{
WebDriver driver= new ChromeDriver();
driver.get("http://www.google.com");
System.out.println(driver.manage().window().getSize());
driver.manage().window().maximize();
System.out.println(driver.manage().window().getSize());
driver.close();
	}
}
