package testCases;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import environment.SendAttachmentInEmail;
import environment.Utill;
import pages.temp;

public class MainTest2 {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "DEMOTAD057";

	@BeforeSuite
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/demo.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	

	@BeforeTest
	public void start() throws IOException {
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
//		 ChromeOptions chromoption = new ChromeOptions();
//		 chromoption.setHeadless(true);
		driver = new ChromeDriver();
//		 Dimension d = new Dimension(1382, 744);
//		 driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		driver.get(getvalue("url"));
	}

	@BeforeMethod
	public void setup(Method method) throws Exception{
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		pages = new Pages(driver, logger);
		System.out.println("before method");
		//pages.Utill().GoTo(getvalue("url") + "/Matrix/UserHome.aspx");
	}

	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		pages.loginpage().Login("Abin8375");	
		pages.CandidateInitiation().wait_until_loader_is_invisible();
		pages.Utill().SwitchFramebyIndex(0);
		Thread.sleep(2000);
		pages.Utill().click_element("CandidateHome1_Candidate_DOB");
		String t=pages.Utill().get_text("CandidateHome1_CanDobdate_title");
		String cdate ="01 January 2018";
		System.out.println(cdate);
		String[] pdate=cdate.split("\\s+");

//		String t="Juy, 2018";
		String[] gdate=t.replaceAll(",", "").split("\\s+");
//		for(String s:gdate) {
//			System.out.println(s);
//		}	
		if(pdate[2].equals(gdate[1])) {
			if(pdate[1].equals(gdate[0])) {
				System.out.println("click " +pdate[0]);
				pages.Utill().click_element("//*[contains(@title,'"+pdate[1]+" "+pdate[0]+"')]");
			}
			else {
				//month not matching
				System.out.println("click title");
				pages.Utill().click_element("CandidateHome1_CanDobdate_title");
				//*[contains(@title,'Jan') AND contains(@title,'2018')]
				Thread.sleep(1000);
				pages.Utill().click_element("//*[contains(@title,'"+pdate[1]+", "+pdate[2]+"')]");
				Thread.sleep(1000);
				pages.Utill().click_element("//*[contains(@title,'"+pdate[1]+" "+pdate[0]+"')]");
				
				
			}
		}
		else {
			//for yesr
			System.out.println("duble click on title");
			pages.Utill().click_element("CandidateHome1_CanDobdate_title");
			Thread.sleep(1000);
			pages.Utill().click_element("CandidateHome1_CanDobdate_title");
			Thread.sleep(1000);
			String year = pages.Utill().get_text("CandidateHome1_CanDobdate_title");
			//get range
			//split
			//check
			if(true) {
				//click yr
				//click month
				//click date
			}
			else {
				//click again
				//click month
				//click date
			}
		}
	
	}

	

	
	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath("."+temp).build());
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() {
		// pages.loginpage().Logout();
		//driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		// driver.quit();
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}
	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}
}
