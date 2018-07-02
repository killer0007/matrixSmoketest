package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import environment.Utill;


public class UrlNavigation {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	int candid = 3015270;
	String candidateName = "gopi";
	String MatrixRefNo = "DEMOTAF140";
	Map<String, String> map;

	@BeforeSuite
	public void beforeSuit() {
		// reporter = new ExtentHtmlReporter("./Reports/matex.html");
		reporter = new ExtentHtmlReporter("./Reports/UrlNavigation.html");
		reporter.config().setDocumentTitle("Matrix test report");
		reporter.config().setReportName("test report");

		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeTest
	public void start() throws IOException {
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedrivernew.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		 ChromeOptions chromoption = new ChromeOptions();
		 chromoption.setHeadless(true);
		driver = new ChromeDriver();
		Dimension d = new Dimension(1382, 744);
		 //driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get(getvalue("url"));
		// driver.get("http://192.168.2.16/MatexTesting");
	}

	@BeforeMethod
	public void setup(Method method) {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");

		pages = new Pages(driver, logger);
		System.out.println("before method");
		map = new LinkedHashMap<String, String>();
	}

	@Test(priority = 1, enabled = true)
	public void demotlpage() throws Exception {
		pages.loginpage().Login(getvalue("uname"), getvalue("password"));
		final String path = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String finalqc = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		final String crt = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[3]/ul/li/a";
		final String DailyActivity = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[4]/ul/li/a";
		final String MIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[5]/ul/li/a";

		pages.MatrixPortalNavigation().geturl("Dashboard", map, path);
		pages.MatrixPortalNavigation().geturl("Final QC", map, finalqc);
		pages.MatrixPortalNavigation().geturl("CRT", map, crt);
		pages.MatrixPortalNavigation().geturl("Daily Activity", map, DailyActivity);
		pages.MatrixPortalNavigation().geturl("MIS", map, MIS);
		pages.Utill().printMap(map);
		pages.loginpage().Logout();
	}

	@Test(priority = 2, enabled = true)
	public void adminpage() throws Exception {
		pages.loginpage().Login("admin", "pass@123");
		final String masters = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		final String clients = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[2]/ul/li/a";
		final String Matrixuser = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[3]/ul/li/a";
		final String Approvals = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[4]/ul/li/a";
		final String ManagerMIS = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[5]/ul/li/a";
		Map<String, String> map = new LinkedHashMap<String, String>();

		 pages.MatrixPortalNavigation().geturl("Clients", map,clients);
		 pages.MatrixPortalNavigation().geturl("Matrix user", map,Matrixuser);
		// pages.MatrixPortalNavigation().geturl("Approvals", map,Approvals);
		 pages.MatrixPortalNavigation().geturl("Manager MIS", map,ManagerMIS);
		pages.MatrixPortalNavigation().geturl("Masters", map, masters);
		pages.Utill().printMap(map);

	}

	@AfterMethod
	public void tearDown(ITestResult result, Method method) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);

		
			logger.fail("null value", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip(result.getThrowable().getMessage());
		}
		else {
		}

		logger.pass(method.getName() + " completed");
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
		// SendAttachmentInEmail email = new SendAttachmentInEmail();
		// email.sendhtmlemail();

	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}
}
