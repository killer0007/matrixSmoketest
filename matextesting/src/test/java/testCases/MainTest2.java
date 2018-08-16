package testCases;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import environment.SendAttachmentInEmail;
import environment.Utill;


public class MainTest2 {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "AUTOMAA098";
	Map<String, String> map;
	public String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
		return pr.getProperty(key);
	}

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
		 ChromeOptions chromoption = new ChromeOptions();
		 chromoption.setHeadless(true);
		driver = new ChromeDriver();
		 Dimension d = new Dimension(1382, 744);
		 driver.manage().window().setSize(d);
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
		pages.loginpage().Login(getvalue("uname"));
		
		Assert.assertEquals(pages.Utill().find("ctl00_lblUsername").getText(), "Demotl");

	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "Login")
	public void caseregistration() throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "Prior TM");
		pages.Utill().click_element(getlocator("v_updation_out"));
		pages.Wait().wait_until_loader_is_invisible();
	
		
			pages.Utill().click_element(getlocator("vemp_upda_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdUpdation_ctl02_btnemp");
		Thread.sleep(1000);
		String currentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		for (String e : handles) {
			if (!(e.equals(currentWindow))) {
				driver.switchTo().window(e);
				break;
			}
		}
		System.out.println(driver.getTitle());
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions
				.presenceOfElementLocated(By.id(getlocator("vCompany_Type"))));

		pages.Utill().input_text((getlocator("Empl_CmpName")),
				(getvalue("Empl_CmpName")));
		w.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[text()='" + getvalue("Empl_CmpName") + "']")));
		pages.Utill().click_element("//*[text()='" + getvalue("Empl_CmpName") + "']");
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().input_text(getlocator("Empl_CmpAddr"), getvalue("Empl_CmpAddr"));
		pages.Utill().input_text(getlocator("Empl_Position"),
				getvalue("Empl_Position"));
		pages.Utill().input_text(getlocator("Empl_Department"),
				getvalue("Empl_Department"));
		pages.Utill().input_text(getlocator("Empl_HOAddr"), getvalue("Empl_HOAddr"));
		pages.Utill().input_text(getlocator("Empl_FromDt"), getvalue("Empl_FromDt"));
		pages.Utill().input_text(getlocator("Empl_ToDt"), getvalue("Empl_ToDt"));
		pages.Utill().input_text(getlocator("Empl_EmpCode"), getvalue("Empl_EmpCode"));

		pages.Utill().select_by_label(getlocator("Empl_EmplType"),
				getvalue("Empl_EmplType"));
		pages.Utill().input_text(getlocator("Empl_LastSalary"),
				getvalue("Empl_LastSalary"));

		pages.Utill().select_by_label(getlocator("Empl_CurrencyType"),
				getvalue("Empl_CurrencyType"));
		pages.Utill().select_by_label(getlocator("Empl_SalType"),
				getvalue("Empl_SalType"));

//		pages.Utill().input_text(getlocator("Empl_RepAuthName"),
//				getvalue("Empl_RepAuthName"));
//		pages.Utill().input_text(getlocator("Empl_RepAuthDesig"),
//				getvalue("Empl_RepAuthDesig"));
//		pages.Utill().input_text(getlocator("Empl_RepAuthMobile1"),
//				getvalue("Empl_RepAuthMobile1"));
//		pages.Utill().input_text(getlocator("Empl_RepAuthEmail"),
//				getvalue("Empl_RepAuthEmail"));
//		pages.Utill().input_text(getlocator("Empl_HRName"), getvalue("Empl_HRName"));
		pages.Utill().input_text(getlocator("Empl_ReasonLeave"),
				getvalue("Empl_ReasonLeave"));
		pages.Utill().input_text(getlocator("Company_VerifierName"),
				getvalue("Company_VerifierName"));
		pages.Utill().input_text(getlocator("vemp_VerifierDesignation"),
				getvalue("vemp_VerifierDesignation"));
		pages.Utill().input_text(getlocator("vemp_VerifierContact"),
				getvalue("vemp_VerifierContact"));
		pages.Utill().input_text(getlocator("vemp_VerifierEmail"),
				getvalue("vemp_VerifierEmail"));

		pages.Utill().select_by_label(getlocator("vemp_ConfirmationMode"),
				getvalue("vemp_ConfirmationMode"));
		//-----------------------------
		
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RevertReceivedFrom", "Reporting Authority");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentName", "ragavan");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentDesig", "Assistant General Manager, HR");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentDepartment", "Admin");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentContact", getvalue("Empl_RepAuthMobile1"));
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentMobile", getvalue("Empl_RepAuthMobile1"));
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_RespondentEmail", getvalue("Empl_RepAuthEmail"));
		
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompany_FullTime_PartTime", "Full Time");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyEligibleforrehire", "Yes");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtRehireComments", "Eligible for rehire");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyIsthedocumentauthentic", "Yes");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtIsthedocumentauthentic", "document authentic");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyAnyIssuesPers", "No");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtIsAnyPertaining", "issues pertaining");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_CdtCompanyNoticeServe", "Yes");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_RightCandidateEmployment1_TxtNoticeServe", "Sufficient notice period serve");
		
		//--------------------------------
		pages.Utill().select_by_label(getlocator("vemp_Typeofrevert"),
				getvalue("vemp_Typeofrevert"));
		pages.Utill().select_by_label(getlocator("vemp_VerificationSource"),
				getvalue("vemp_VerificationSource"));
		pages.Utill().input_text(getlocator("vemp_VerComments"),
				getvalue("vemp_VerComments"));
		pages.Utill().click_element(getlocator("vemp_date"));
		w.until(ExpectedConditions
				.presenceOfElementLocated(By.id(getlocator("vemp_today"))));
		pages.Utill().click_element(getlocator("vemp_today"));
		pages.Utill().click_element(getlocator("vemp_green"));

		pages.Utill().handle_Alert();
		driver.switchTo().window(currentWindow);
	}
	

	
	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception{
		// pages.loginpage().Logout();
		Thread.sleep(10000);
//		driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
//		 driver.quit();
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}
	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}
}
