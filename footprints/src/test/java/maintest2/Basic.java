package maintest2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
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
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
	}

	@Test(priority = 4, enabled = true)
	public void dataEntry() throws Exception {
		refno = "HDFC000670";
		ReportGeneration rg=pages.ReportGeneration();
		rg.reportGeneration();
		rg.Search(refno);
		rg.Select(refno);
		screenshot();
//		rg.GenerateReport();
//		List<String> op=rg.getReportComponents();
//		List<String> components = new ArrayList<>(Arrays.asList(pages.CaseRegistration().getcomponents()));
//		Collections.sort(op);
//		Collections.sort(components);
//		assertEquals(op, components);
//		rg.GenerateReportCheckbox();
//		rg.ReportComments("completed");
//		pages.Utill().SwitchDefault();
//		rg.ReportTemplate("New Standard Template");
//		rg.CaseStatus("Clear");
//		rg.previewReport();
//		pages.Utill().switchWindow(1);
//		System.out.println(driver.getCurrentUrl());
		
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
	private String FilterFileName(String FilePath) {
		return FilePath.substring(FilePath.lastIndexOf("\\")).replace("\\", "");
	}
	private void screenshot() throws IOException {
		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";
		final Screenshot screenshot = new AShot().shootingStrategy(
                new ViewportPastingStrategy(1000)).takeScreenshot(driver);
        final BufferedImage image = screenshot.getImage();
        ImageIO.write(image, "PNG", new File(path));


	}
}
