package pages;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
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
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

import atu.testrecorder.ATUTestRecorder;
import environment.SendAttachmentInEmail;
import testCases.*;

public class temp {
 
public static void main(String[] args) throws Exception{
	String url="http://192.168.2.16/MatexTesting/Matrix/CourtCheckVerification.aspx?Page=CTTM&CourtCandidateID=90af7cf4-5672-45e8-8ae5-2e639295857b&SubcheckID=09f6528d-6667-4ff9-8889-13d223b36405&TMId=60cfb21f-188f-484a-a903-ee3ccea07e35";
	String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", chrome_path);
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://192.168.2.16/MatexTesting");
	driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("demotl");
	driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys("pass@123");
	driver.findElement(By.id("btnLogin")).click();
	Thread.sleep(2000);
	driver.get(url);
	
	driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_gvCourtVerification_ctl02_ddlCourtName_chosen']/a")).click();
	driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_gvCourtVerification_ctl02_ddlCourtName_chosen']/div/div/input")).sendKeys("All High Courts of India");
	driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_gvCourtVerification_ctl02_ddlCourtName_chosen']/div/ul/li")).click();
	
	
}
}
