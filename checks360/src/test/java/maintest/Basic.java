package maintest;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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
import environment.BaseClass;
import environment.Pages;
import environment.Utill;
@Listeners(environment.Listener.class)
public class Basic extends Design {
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String ContractName = null;
	protected String ClientName=null;
	protected String ProjectName=null;
	protected String CandidateName=null;
	protected String CandidateId=null;
	protected String lastname=null;
	protected String refno=null;

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

	@Test(priority=1,enabled=true)
	public void Login() throws Exception {
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		HashMap<String, String> data =pages.DbConnection().getLastCase(ProjectName);
		pages.Home().clickRegister();
		CandidateName = data.get("firstname");
		CandidateId = Integer.toString(pages.Utill().candidateid());
		lastname = data.get("lastname");
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", CandidateName);
		datas.put("CandidateId", CandidateId);
		datas.put("ClientName", ClientName);
		datas.put("ProjectName", ProjectName);
		datas.put("lastname", lastname);
		datas.put("DateofBirth", data.get("DateofBirth"));
		pages.CaseRegistration().registercase(datas);
		String msg = pages.Utill().confirmAlert();
		System.out.println(msg);
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
