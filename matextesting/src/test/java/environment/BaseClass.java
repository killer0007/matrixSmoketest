package environment;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

	static WebDriver driver;
	public static WebDriver getDriver(){
		if(driver==null){
			
			String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chrome_path);
			 ChromeOptions chromoption = new ChromeOptions();
			 chromoption.setHeadless(true);
			driver = new ChromeDriver();
			 Dimension d = new Dimension(1382, 744);
			 driver.manage().window().setSize(d);
			driver.manage().window().maximize();
		}
		return driver;
	}
	

}
