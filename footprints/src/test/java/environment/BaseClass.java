package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {

	static WebDriver driver;
	static Properties loc;
	static Properties val;
/**
 * Creates webdriver and returns the instance
 * @return WebDriver instance
 * @throws Exception WebDriver exception
 */
	public static WebDriver getDriver() throws Exception{
		if (driver == null) {
			String downloadFilepath = getlocator().getProperty("downloadFilepath");
			String chrome_path = getlocator().getProperty("chromedriver");
			System.setProperty("webdriver.chrome.driver", chrome_path);
			ChromeOptions chromoption = new ChromeOptions();
			HashMap<String, Object> config = new HashMap<String, Object>();
			config.put("profile.default_content_settings.popups", 0);
			config.put("download.default_directory", downloadFilepath);
			config.put("download.prompt_for_download", false);
			config.put("download.directory_upgrade", true);
//					config.put("plugins.always_open_pdf_externally", true);
			chromoption.setExperimentalOption("prefs", config);
			chromoption.addArguments("--disable-notifications");
//			chromoption.setHeadless(true);
//			chromoption.addArguments("start-maximized");
//			driver = new ChromeDriver();
			driver = new ChromeDriver(chromoption);
			Dimension d = new Dimension(1382, 744);
			driver.manage().window().setSize(d);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
		return driver;
	}
	public static WebDriver getWebDriver() {
		return driver;
	}
/**
 * Read the config.properties file and return property object
 * @return Properties config gile property
 * @throws FileNotFoundException when config file missing
 * @throws IOException when file stream failed
 */
	public static Properties getlocator() throws FileNotFoundException, IOException {
		if (loc == null) {
			loc = new Properties();
			loc.load(new FileInputStream(new File("./src\\test\\resources\\testdata\\config.properties")));
			// src\test\resources\property

		}
		return loc;
	}

}
