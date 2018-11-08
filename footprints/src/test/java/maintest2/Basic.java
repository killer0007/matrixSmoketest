package maintest2;

import static org.testng.Assert.assertEquals;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
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
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;
import verification.Address;
import verification.Court;
import verification.Credit;
import verification.Criminal;
import verification.Database;
import verification.Drug;
import verification.Education;
import verification.Employment;
import verification.Id;
import verification.Reference;
import verification.VerificationInitiate;


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

	@Test(priority = 27, enabled = true)
	public void ReportGenerationSupervision() throws Exception {
		pages.ReportGenerationSupervision().reportGenerationSupervision();
		pages.ReportGenerationSupervision().assign(refno,"demoempl");
		List<String> components= new ArrayList<String>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		pages.Home().CaseTracker();
		pages.CaseTracker().search(refno);
		pages.CaseTracker().clickcase(refno);
		SoftAssert sf = new SoftAssert();
		List<HashMap<String, String>> data =pages.CaseTracker().getcasedata();
		for (HashMap<String, String> d:data) {
			if(components.contains(d.get("ComponentName"))) {
				sf.assertEquals(d.get("CurrentStage"), "Report Generation Pending");
			}
		}
		pages.CaseTracker().cancel();
		sf.assertAll();
	}
	@Test(priority = 28, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void AddressReportGeneration() throws Exception {
		pages.ReportGeneration().reportGeneration();
		pages.ReportGeneration().Search(refno);
		pages.ReportGeneration().Select(refno);
		Address add = new Address(driver, logger);
		Map<String, String> actual=add.CurrentAddress();
		Map<String, String> expected=add.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=add.PermanentAdress();
		Map<String, String> perexpected=add.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 29, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void EducationReportGeneration() throws Exception {
		Education edu = new Education(driver, logger);
		Map<String, String> actual=edu.twelveth();
		Map<String, String> expected=edu.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=edu.ugone();
		Map<String, String> perexpected=edu.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 30, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void EmploymentReportGeneration() throws Exception {
		Employment emp = new Employment(driver, logger);
		Map<String, String> actual=emp.CurrentEmp();
		Map<String, String> expected=emp.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=emp.PerviousEmp();
		Map<String, String> perexpected=emp.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 31, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void ReferenceReportGeneration() throws Exception {
		Reference ref = new Reference(driver, logger);
		Map<String, String> actual=ref.Referenceone();
		Map<String, String> expected=ref.filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 32, enabled = false, dependsOnMethods = "ReportGenerationSupervision")
	public void DatabaseReportGeneration() throws Exception {
		
	}
	@Test(priority = 33, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void CriminalReportGeneration() throws Exception {
		Criminal criminal = new Criminal(driver, logger);
		Map<String, String> actual=criminal.CurrentAddress();
		Map<String, String> expected=criminal.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=criminal.PermanentAdress();
		Map<String, String> perexpected=criminal.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 34, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void CreditReportGeneration() throws Exception {
		Credit credit = new Credit(driver, logger);
		Map<String, String> actual=credit.credit();
		Map<String, String> expected=credit.filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 35, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void CourtReportGeneration() throws Exception {
		Court court = new Court(driver, logger);
		Map<String, String> actual=court.CurrentAddress();
		Map<String, String> expected=court.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=court.PermanentAdress();
		Map<String, String> perexpected=court.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 36, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void DrugReportGeneration() throws Exception {
		Drug drug = new Drug(driver, logger);
		Map<String, String> actual=drug.drug();
		Map<String, String> expected=drug.filedata();
		assertEquals(actual, expected);
	}
	@Test(priority = 37, enabled = true, dependsOnMethods = "ReportGenerationSupervision")
	public void IdReportGeneration() throws Exception {
		Id id = new Id(driver, logger);
		Map<String, String> actual=id.PassPort();
		Map<String, String> expected=id.filedata();
		assertEquals(actual, expected);
		Map<String, String> peractual=id.Aadharcard();
		Map<String, String> perexpected=id.filedata();
		assertEquals(peractual, perexpected);
	}
	@Test(priority = 4, enabled = false)
	public void dataEntry() throws Exception {
		refno = "HDFC000670";
		ReportGeneration rg=pages.ReportGeneration();
		rg.reportGeneration();
		rg.Search(refno);
		rg.Select(refno);
		screenshot();
		rg.GenerateReport();
		List<String> op=rg.getReportComponents();
		List<String> components = new ArrayList<>(Arrays.asList(pages.CaseRegistration().getcomponents()));
		Collections.sort(op);
		Collections.sort(components);
		assertEquals(op, components);
		rg.GenerateReportCheckbox();
		rg.ReportComments("completed");
		pages.Utill().SwitchDefault();
		rg.ReportTemplate("New Standard Template");
		rg.CaseStatus("Clear");
		pages.Utill().deleteFiles(new File("D:\\gopi\\filedownload"));
		rg.previewReport();
		pages.Utill().switchWindow(1);
		pages.Utill().closeTab();
		pages.Utill().switchWindow(0);
		String path=pages.Utill().getFileName(new File("D:\\gopi\\filedownload"));
		System.out.println(path);
	}
	

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Utill.getScreenshot(driver);
			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			logger.log(Status.INFO, refno);
//			String pagesource = driver.getPageSource();
//			if (result.getThrowable().getMessage().contains("The following asserts failed")) {
//				logger.log(Status.FAIL, result.getThrowable().getMessage());
//			} else if (pagesource.contains("Images/message.png")) {
//				logger.log(Status.WARNING, "Your last session was terminated");
//				pages.Utill().click_element("ctl00_ContentPlaceHolder1_urls");
//				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
//			} else if (pagesource.contains("ctl00_ContentPlaceHolder1_txtUserName")) {
//				logger.log(Status.WARNING, "Your last session was closed by user");
//				pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
//			} else {
//				logger.log(Status.WARNING, method.getName() + " navigating to home page due to error");
//				driver.get(config.getProperty("url") + "/Web/dashboard.aspx");
//				pages.Utill().wait_until_loader_is_invisible(80);
//			}

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
	protected String FilterFileName(String FilePath) {
		return FilePath.substring(FilePath.lastIndexOf("\\")).replace("\\", "");
	}
	private void screenshot() throws IOException {
		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";
		final Screenshot screenshot = new AShot().shootingStrategy(
                new ViewportPastingStrategy(1000)).takeScreenshot(driver);
        final BufferedImage image = screenshot.getImage();
        ImageIO.write(image, "PNG", new File(path));


	}
	private static Map<String, String> mode(){
		Map<String, String> map = new HashMap<>();
		map.put("Permanent", "In Person");
		map.put("Current Address", "In Person");
		map.put("12th", "Email (Preffered)");
		map.put("UG1", "Email (Preffered)");
		map.put("Current/Latest Employment", "Email");
		map.put("Previous Employment", "Email");
		map.put("Reference 1", "Phone");
		map.put("Current Address Criminal Check", "In Person");
		map.put("Permanent Criminal Check", "In Person");
		map.put("Current Address Court Check", "In Person");
		map.put("Permanent Court Check", "In Person");
		map.put("Database", "Online");
		map.put("Credit Check 1", "Online");
		map.put("Passport", "Online");
		map.put("Aadhaar Card", "Online");
		map.put("Panel1", "In Person");
		return map;
	}
}
