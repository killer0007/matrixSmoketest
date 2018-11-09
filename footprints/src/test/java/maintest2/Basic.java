package maintest2;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
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

import dashboard.ReportGeneration;
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
	protected String contractName = null;
	protected String clientName = null;
	protected String projectName = null;
	protected String candidateName = null;
	protected String candidateId = null;
	protected String lastName = null;
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
		driver = new BaseClass().getDriver();
		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(config.getProperty("url"));
		contractName = config.getProperty("contractname");
		clientName = config.getProperty("clientname");
		projectName = config.getProperty("projectname");

	}

	@BeforeMethod
	public void setup(Method method) throws FileNotFoundException, IOException {
		logger = extent.createTest(method.getName());
		logger.pass(method.getName() + " Started");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver,logger);

	}

	/**
	 * Login action
	 */
	@Test(priority = 1, enabled = true)
	public void Login() throws Exception {
		refno = "HDFC000726";
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		//pages.Verification().verification();
	}

	
	@Test(priority = 4, enabled = true)
	public void dataEntry() throws Exception {
		refno = "HDFC000733";
		ReportGeneration reportgeneration=pages.ReportGeneration();
		reportgeneration.reportGeneration();
		reportgeneration.Search(refno);
		reportgeneration.Select(refno);

	}
	@Test(priority = 38, enabled = true)
	public void ReportGenerationSubmit() throws Exception {
		ReportGeneration reportgeneration=pages.ReportGeneration();
		pages.Utill().SwitchDefault();
		reportgeneration.GenerateReport();
		List<String> op=reportgeneration.getReportComponents();
		List<String> components = new ArrayList<>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		Collections.sort(op);
		Collections.sort(components);
		assertEquals(op, components);
		reportgeneration.GenerateReportCheckbox();
		reportgeneration.ReportComments("completed");
		reportgeneration.ReportTemplate("New Standard Template");
		reportgeneration.CaseStatus("Clear");
		reportgeneration.previewReport();
		pages.Utill().switchWindow(1);
		String source=driver.getPageSource();
		boolean re=true;
		if(!source.contains("report/ReportViewer.aspx")) {
			logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(Utill.getScreenshot(driver)).build());
			re=false;
		}
		pages.Utill().closeTab();
		pages.Utill().switchWindow(0);
		reportgeneration.submit();
		assertTrue(re);
	}
	
	@Test(priority = 38, enabled = true, dependsOnMethods = "ReportGenerationSubmit")
	public void ReportValidationSupervisor() throws Exception {
		pages.ReportValidationSupervision().reportValidationSupervision();
		pages.ReportValidationSupervision().assign(refno, "demoempl");
		List<String> components= new ArrayList<String>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		SoftAssert sf = new SoftAssert();
		List<HashMap<String, String>> data =pages.CaseTracker().getcasedata();
		for (HashMap<String, String> d:data) {
			if(components.contains(d.get("ComponentName"))) {
				sf.assertEquals(d.get("CurrentStage"), "Report Generation QC Pending");
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}
	@Test(priority = 40, enabled = true, dependsOnMethods = "ReportValidationSupervisor")
	public void ReportValidation() throws Exception {
		pages.ReportValidation().reportValidation();
		pages.ReportValidation().Search(refno);
		pages.ReportValidation().Select(refno);
		pages.ReportGeneration().GenerateReport();
	}
	

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
		} else {
			logger.pass(method.getName() + " completed");
		}

	}

	@AfterTest
	public void teardown() throws Exception {
//		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
	
}
