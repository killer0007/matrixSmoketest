package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {

	static WebDriver driver;
	static Properties loc;
	static Properties val;

	public static WebDriver getDriver() throws Exception{
		if (driver == null) {
			String downloadFilepath = getlocator().getProperty("downloadFilepath");
			String chrome_path = "D:\\gopi\\chromedriver.exe";
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
			chromoption.addArguments("start-maximized");
//			driver = new ChromeDriver();
			driver = new ChromeDriver(chromoption);
//			Dimension d = new Dimension(1382, 744);
//			driver.manage().window().setSize(d);
//			driver.manage().window().maximize();
		}
		return driver;
	}

	public static Properties getlocator() throws FileNotFoundException, IOException {
		if (loc == null) {
			loc = new Properties();
			loc.load(new FileInputStream(new File("./config.properties")));
			// src\test\resources\property

		}
		return loc;
	}

	public static Properties getvalue() throws FileNotFoundException, IOException {
		if (val == null) {
			val = new Properties();
			val.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		}
		return val;
	}

}
