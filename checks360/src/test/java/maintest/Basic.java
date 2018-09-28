package maintest;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import actions.CaseOwnerInsuffClear;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;

@Listeners(environment.Listener.class)
public class Basic  {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 100, Font.BOLD);

	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String ContractName = null;
	protected String ClientName = null;
	protected String ProjectName = null;
	protected String CandidateName = null;
	protected String CandidateId = null;
	protected String lastname = null;
	protected String refno = null;
	protected String uname = null;

	@BeforeSuite
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/matrixflow.html");
		reporter.config().setDocumentTitle("Matrix test report");
		reporter.config().setReportName("test report");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);

	}

	@BeforeTest
	public void beforetest() throws FileNotFoundException, IOException {
		driver = BaseClass.getDriver();
		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(config.getProperty("url"));
		ContractName = config.getProperty("contractname");
		ClientName = config.getProperty("clientname");
		ProjectName = config.getProperty("projectname");

	}

	@BeforeMethod
	public void setup(Method method) throws FileNotFoundException, IOException {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver, logger);

	}

	@Test(priority = 29, enabled = true, groups = {"insuff" })
	public void TC_SPCEP_001() throws Exception {
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		String[] checks = { "Current Address", "UG1", "Previous Employment", "Reference 3"};
		String[] cep = { "Current/Latest Employment", "Previous Employment 2", "Reference 1", "Reference 2"};
		pages.Home().clickRegister();
		CandidateName = pages.Utill().candidateName();
		CandidateId = Integer.toString(pages.Utill().candidateid());
		lastname = pages.Utill().candidateName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", CandidateName);
		datas.put("CandidateId", CandidateId);
		datas.put("ClientName", ClientName);
		datas.put("ProjectName", ProjectName);
		datas.put("lastname", lastname);
		pages.CaseRegistration().registercase(datas, false);
		for (int i = 0; i < checks.length; i++) {
			pages.CaseRegistration().selectcheck(checks[i].toString());
		}
		for (int i = 0; i < cep.length; i++) {
			pages.CaseRegistration().selectcheck(cep[i].toString());
//			pages.CaseRegistration().notApplicable(cep[i].toString(), "not applicable");
			pages.CaseRegistration().cep(cep[i].toString(), "cep raised", "10/11/2018");
			//not applicable
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(ProjectName);
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		List<HashMap<String, String>> data = pages.CaseTracker().getcasedata();
		SoftAssert sf = new SoftAssert();
		for (int i = 0; i < data.size(); i++) {
			String name = data.get(i).get("ComponentName").trim();
			if(Arrays.asList(checks).contains(name)) {
				if (data.get(i).get("Status").equals("WIP")) {
					sf.assertTrue(true, "success");
					//System.out.println(name+" : "+data.get(i).get("Status"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("CurrentStage").equals("Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
					//System.out.println(name+" : "+data.get(i).get("CurrentStage"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("PersonResponsible").equals("Team Leader")) {
					sf.assertTrue(true, "success");
					//System.out.println(name+" : "+data.get(i).get("PersonResponsible"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
			else if(Arrays.asList(cep).contains(name)) {
				if (data.get(i).get("Status").equals("CEP Onhold")) {
					sf.assertTrue(true, "success");
					//System.out.println(name+" : "+data.get(i).get("Status"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("CurrentStage").equals("CEP Raised - Data Entry Assignment Pending")) {
					sf.assertTrue(true, "success");
					//System.out.println(name+" : "+data.get(i).get("CurrentStage"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
				if (data.get(i).get("PersonResponsible").equals("Team Leader")) {
					sf.assertTrue(true, "success");
					//System.out.println(name+" : "+data.get(i).get("PersonResponsible"));
				} else {
					sf.assertTrue(false, data.get(i).get("ComponentName"));
				}
			}
			
			}
		pages.CaseTracker().cancel();
		sf.assertAll();
	
	}
	@Test(priority = 30, enabled = true, groups = {"insuff" })
	public void TC_SPCEP_002() throws Exception {
		pages.Home().Actions();
		pages.CEP().CEPClear();
		pages.CEP().search(refno, "SP");
		String no =pages.CEP().getrefNo();
		assertEquals(no, refno);
	}
	@Test(priority = 31, enabled = true, groups = {"insuff" })
	public void TC_SPCEP_003() throws Exception {
		Properties loc=BaseClass.getlocator();
		pages.CEP().upload(refno,"comments cep", "Relieving Letter", loc.getProperty("addressinsuffdoc"));
		pages.Home().workStage();
		pages.DataEntrySupervision().datanentrysupervision();
		pages.DataEntrySupervision().assigngetnext(refno);
		pages.DataEntry().datanentry();
		pages.DataEntry().search(refno);
		pages.DataEntry().selectcase(refno);
		
		
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
			String pagesource =driver.getPageSource();
			if(result.getThrowable().getMessage().contains("The following asserts failed")) {
				logger.log(Status.FAIL, result.getThrowable().getMessage());
			}
			else if(pagesource.contains("Images/message.png")) {
				logger.log(Status.WARNING, "Your last session was terminated");
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_urls");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			}
			else if (pagesource.contains("ctl00_ContentPlaceHolder1_txtUserName")) {
				logger.log(Status.WARNING, "Your last session was closed by user");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			}
			else {
				logger.log(Status.WARNING, method.getName()+" navigating to home page due to error");
				driver.get(config.getProperty("url") + "/Web/dashboard.aspx");
				pages.Utill().wait_until_loader_is_invisible(80);
			}
			
		} else {
			logger.pass(method.getName() + " completed");
		}
		

	}

	@AfterTest
	public void teardown() throws Exception {
		// pages.loginpage().Logout();
//		Thread.sleep(10000);
//		driver.close();
	}

	@AfterSuite
	public void afterSuite() {

		extent.flush();
//		 driver.quit();

	}
//	private String getvalue(String key) throws FileNotFoundException, IOException {
//		Properties pr = new Properties();
//		pr.load(new FileInputStream(new File("./config.properties")));
//		return pr.getProperty(key);
//	}
}
