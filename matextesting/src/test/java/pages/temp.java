package pages;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import environment.SendAttachmentInEmail;
import testCases.*;

public class temp {
	WebDriver driver;

	@Test
	public void getnextMonth() {
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		ChromeOptions chromption = new ChromeOptions();
		chromption.setHeadless(true);
		driver = new ChromeDriver(chromption);
		Dimension d = new Dimension(1382, 744);
		driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		driver.get("http://192.168.2.16/MatexTesting");
		System.out.println(driver.getTitle());
		 System.out.println(driver.manage().window().getSize()); //output: (1382, 744)
		String path =getScreenshot(driver);
		System.out.println(path);
	}
	@AfterTest
	public void aftertest() {
//		SendAttachmentInEmail email = new SendAttachmentInEmail();
//		
//		assertTrue(email.sendhtmlemail());
	}
	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}

		return path;
	}

	
}
