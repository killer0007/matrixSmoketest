package maintest;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;
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
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import environment.BaseClass;
import environment.Pages;
import environment.Utill;

@Listeners(environment.Listener.class)
public class Basic {

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
	public void beforetest() throws Exception {
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

	@Test(priority = 29, enabled = true)
	public void TC_SPDOC_001() throws Exception {
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
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
//		refno = "HDFC000355";
//		pages.Utill().click_element("//*[text()='" + refno + "']");
//		pages.Utill().wait_until_loader_is_invisible(100);
		pages.CaseRegistration().addEditComponent();
		pages.CaseRegistration().uploadcaseDoc("Authorization Letter",
				BaseClass.getlocator().getProperty("addressinsuffdoc"));
		String name = pages.CaseRegistration().getuploadcaseDoc("Authorization Letter");
		assertEquals(name, "address.pdf");
		System.out.println(name);

	}

	@Test(priority = 30, enabled = true)
	public void TC_SPDOC_002() throws Exception {
		pages.CaseRegistration().selectcheck("Permanent");
		pages.CaseRegistration().documentupload("Permanent", BaseClass.getlocator().getProperty("addressinsuffdoc"),
				"Address Proof");
		String name = pages.CaseRegistration().getDocumentName("Permanent", "Address Proof");
		assertEquals(name, "address.pdf");
	}

	@Test(priority = 31, enabled = true)
	public void TC_SPDOC_003() throws Exception {
		pages.CaseRegistration().uploadcaseDoc();
		pages.Utill().wait_until_loader_is_invisible(100);
		if (pages.CaseRegistration().isDoctypeValid("Credit Form", 1)) {
			pages.Utill().input_text(
					"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='Credit Form']/../td[5]//div/ul/li/span/input[2]",
					BaseClass.getlocator().getProperty("creditinsuffdoc"));
		} else
			throw new NotFoundException("Credit Form");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnAddCaseDocument_input");
		pages.Utill().wait_until_loader_is_invisible(50);
		pages.Utill().click_element(
				"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='Credit Form']/../td[6]//td[2]/input");
		pages.Utill().confirmAlert();
		pages.Utill().wait_until_loader_is_invisible(50);
		int count = driver.findElements(By.xpath(
				"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='Credit Form']/../td[5]//span"))
				.size();
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");
//		System.out.println(count);
		assertTrue(count > 1);

	}

	@Test(priority = 32, enabled = true)
	public void TC_SPDOC_004() throws Exception {
		pages.Utill().click_element("//td[text()='Permanent']/../td[10]//input[1]");
		pages.Utill().wait_until_loader_is_invisible(10);
		if (pages.CaseRegistration().isDoctypeValid("Others", 0)) {
			pages.Utill().input_text(
					"//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='Others']/../td[6]//input[2]",
					BaseClass.getlocator().getProperty("creditinsuffdoc"));
		} else
			throw new NotFoundException("Others");
		pages.CaseRegistration().addDocument();
		pages.CaseRegistration().deleteComponentdoc("Others");
		pages.CaseRegistration().docupClose();
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		refno = pages.DbConnection().getLastrefno(ProjectName);
		System.out.println(refno);
	}

	// check documents in data entry
	@Test(priority = 33, enabled = true)
	public void TC_SPDOC_005() throws Exception {
//		uname = config.getProperty("uname");
//		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		pages.DataEntrySupervision().datanentrysupervision();
//		refno = "HDFC000458";
		pages.DataEntrySupervision().assigngetnext(refno);
		pages.DataEntry().datanentry();
		pages.DataEntry().search(refno);
		pages.DataEntry().selectcase(refno);
		pages.CaseInformation().edit();
		pages.CaseInformation().CaseDocument();
		String actual = pages.CaseInformation().getDocumentName("Authorization Letter");
		pages.CaseInformation().cancel();
		assertEquals(actual, "address.pdf");

	}

	@Test(priority = 34, enabled = true)
	public void TC_SPDOC_006() throws Exception {
		pages.DeAddress().addresscheck();
		pages.DeAddress().document();
		String actual = pages.DeAddress().getDocumentName("Address Proof");
		pages.DeAddress().docclose();
		assertEquals(actual, "address.pdf");
	}

	@Test(priority = 35, enabled = true)
	public void TC_SPDOC_007() throws Exception {
		File file = new File(BaseClass.getlocator().getProperty("downloadFilepath"));
		pages.Utill().deleteFiles(file);
		pages.Utill().SwitchDefault();
		pages.CaseInformation().CaseDocument();
		pages.CaseInformation().documentDownload("Authorization Letter");
		pages.CaseInformation().cancel();
		String actual = pages.Utill().isfileexist(file);
		assertEquals(actual, "address.pdf");
	}

	@Test(priority = 36, enabled = true)
	public void TC_SPDOC_008() throws Exception {
		File file = new File(BaseClass.getlocator().getProperty("downloadFilepath"));
		pages.Utill().deleteFiles(file);
		pages.Utill().SwitchDefault();
		pages.DeAddress().addresscheck();
		pages.DeAddress().document();
		pages.DeAddress().downloaddoc("Address Proof");
		pages.DeAddress().docclose();
		String actual = pages.Utill().isfileexist(file);
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("imgHome");
		assertEquals(actual, "address.pdf");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
			String pagesource = driver.getPageSource();
			if (result.getThrowable().getMessage().contains("The following asserts failed")) {
				logger.log(Status.FAIL, result.getThrowable().getMessage());
			} else if (pagesource.contains("Images/message.png")) {
				logger.log(Status.WARNING, "Your last session was terminated");
				pages.Utill().click_element("ctl00_ContentPlaceHolder1_urls");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			} else if (pagesource.contains("ctl00_ContentPlaceHolder1_txtUserName")) {
				logger.log(Status.WARNING, "Your last session was closed by user");
				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
			} else {
				logger.log(Status.WARNING, method.getName() + " navigating to home page due to error");
				driver.get(config.getProperty("url") + "/Web/dashboard.aspx");
				pages.Utill().wait_until_loader_is_invisible(80);
			}

		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception {
		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
}
