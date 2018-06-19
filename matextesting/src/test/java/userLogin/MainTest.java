package userLogin;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeoutException;

import javax.activity.InvalidActivityException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import environment.Utill;

public class MainTest {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "DEMOTAF046";

	@BeforeSuite
	public void beforeSuit() {
		reporter = new ExtentHtmlReporter("./Reports/learn_automation2.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeTest
	public void start() throws IOException {
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://192.168.2.16/MatexTesting");
	}

	@BeforeMethod
	public void setup(Method method) {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		pages = new Pages(driver,logger);
		System.out.println("before method");
	}

	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		pages.loginpage().Login("demotl", "pass@123");
		Assert.assertEquals(pages.Utill().find("ctl00_lblUsername").getText(), "Deotl");
	
	}

	@Test(priority = 2, enabled = false, dependsOnMethods="Login")
	public void caseregistration() throws InterruptedException, NoSuchElementException, TimeoutException {
		candid = pages.Utill().candidateid();
		candidateName = pages.Utill().candidateName();
		String re = pages.CaseRegistration().caseRegistration("Demo Testing - Test", candid, candidateName);
		Assert.assertEquals(re, "Registered Successfully.");
	}

	@Test(priority = 3, enabled = false, dependsOnMethods="caseregistration")
	public void aasignToDE() throws NoSuchElementException, InterruptedException, InvalidActivityException {
		pages = new Pages(driver,logger);
		pages.CaseRegistration().navigateTo("Daily Activity", "Assign Cases");
		MatrixRefNo = pages.CaseRegistration().assignToDETM(candidateName, candid);

		System.out.println(MatrixRefNo);
	}

	@Test(priority = 4, enabled = false, dependsOnMethods="aasignToDE")
	public void dataentry() throws Exception {
		pages = new Pages(driver,logger);
		pages.CaseRegistration().navigateTo("Daily Activity", "Data Entry");
		pages.Utill().find("ctl00_ContentPlaceHolder1_txtMatrixRefNo").sendKeys(MatrixRefNo);
		pages.Utill().find("ctl00_ContentPlaceHolder1_butnSearch").click();
		pages.Wait().wait_until_loader_is_invisible();
		String no = pages.Utill().find("ctl00_ContentPlaceHolder1_grdCandidate_ctl02_btnMatrixRefNo").getText();
		if (no.equals(MatrixRefNo)) {
			driver.findElement(By.linkText(MatrixRefNo)).click();
			pages.Wait().wait_until_loader_is_invisible();
			 pages.DataEntryTM().Personal();
			 logger.pass("personal records saved successfully");
			
			 logger.info("address check starting");
			 pages.DataEntryTM().AddressCheck();
			 logger.info("address check completed");
			
			 logger.info("Education check starting");
			 pages.DataEntryTM().EducationCheck();
			 logger.info("Education check completed");
			
			 logger.info("Employment check starting");
			 pages.DataEntryTM().EmploymentCheck();
			 logger.info("Employment check completed");
			
			 logger.info("Reference check starting");
			 pages.DataEntryTM().ReeferenceCheck();
			 logger.info("Reference check completed");
			
			 logger.info("Gap check starting");
			 pages.DataEntryTM().GapCheck();
			 logger.info("Gap check completed");
			
			 logger.info("Facis check starting");
			 pages.DataEntryTM().FacisCheck();
			 logger.info("Facis check completed");
			
			 logger.info("Credit check starting");
			 pages.DataEntryTM().CreditCheck();
			 logger.info("Credit check completed");
			
			 logger.info("BV check starting");
			 pages.DataEntryTM().BvCheck();
			 logger.info("BV check completed");
			
			 logger.info("IT check starting");
			 pages.DataEntryTM().ItCheck();
			 logger.info("IT check completed");
			
			 logger.info("PF check starting");
			 pages.DataEntryTM().PfCheck();
			 logger.info("PF check completed");
			String t = pages.DataEntryTM().getlocator("de_submit");
			pages.Utill().find(t).click();
			//pages.Wait().wait_until_loader_is_invisible();
			try {
				System.out.println("try block");
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (NoAlertPresentException e) {
				System.out.println("NoAlertPresentException");
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (UnhandledAlertException e) {
				System.out.println("UnhandledAlertException");
				WebDriverWait w = new WebDriverWait(driver, 10);
				w.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				t = alert.getText();
				logger.info(t);
				alert.accept();
				pages.Wait().wait_until_loader_is_invisible();
				logger.pass("data entry completed");
				assertTrue(true);
			} catch (Exception e) {
				System.out.println("Exception");
				assertTrue(false);
			}
		} else {
			assertTrue("Matrix ref no not found", false);
		}
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
	public void teardown() {
		// pages.loginpage().Logout();
		// driver.close();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		// driver.quit();
	}
}
