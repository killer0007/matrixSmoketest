package livetesting;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.*;

public class Base {
	protected WebDriver driver;
	protected String matrixNo = null;
	protected String downloadFilepath = null;
	protected String filename = null;
	protected String filepath = null;
	Properties config = new Properties();

	@BeforeSuite
	public void suitesetup() throws Exception {
		String current = System.getProperty("user.dir");
		config.load(new FileInputStream(new File(current + "/config.properties")));
		downloadFilepath = config.getProperty("downloadFilepath");
		filename = config.getProperty("filename");
		filepath = config.getProperty("filepath");
	}

	@BeforeTest
	public void browerSetup() throws Exception {

		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put("profile.default_content_settings.popups", 0);
		config.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", config);
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(this.config.getProperty("url"));

		Utility utill = new Utility(driver);
		utill.deleteFiles(new File(downloadFilepath));
	}

	@Test(priority = 1, enabled = true)
	public void login() {
		driver.findElement(By.id("txtUsername")).sendKeys(this.config.getProperty("uname"));
		driver.findElement(By.id("txtPassword")).sendKeys(this.config.getProperty("pass"));
		driver.findElement(By.id("btnLogin")).click();
		String name = driver.findElement(By.id("ctl00_lblUsername")).getText();
		assertEquals("Demotl", name);
	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "login")
	public void dataentry() throws Exception {
		String name = "ctl00_ContentPlaceHolder1_TabContainer1_TabPanel1_CandidateHome1_Candidate_";
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		Utility util = new Utility(driver);
		this.navigate("Daily Activity", "Data Entry");
		
		WebElement mat = driver.findElement(By.id("ctl00_ContentPlaceHolder1_grdCandidate_ctl02_btnMatrixRefNo"));
		matrixNo=mat.getText();
		driver.findElement(By.linkText(matrixNo)).click();
		util.wait_until_loader_is_invisible();
		driver.findElement(By.id(name + "Fathername")).clear();
		driver.findElement(By.id(name + "Fathername")).sendKeys("fname");
		util.selectbyindex(name + "DBCheckApplicable", 2);
		util.selectbyindex(name + "AadharApplicable", 2);
		util.selectbyindex(name + "DrivingLicenseApplicable", 2);
		util.selectbyindex(name + "PassportApplicable", 2);
		util.selectbyindex(name + "VoterIDApplicable", 2);
		util.selectbyindex(name + "DrugCheckApplicable", 2);
		util.selectbyindex(name + "DrugCheckMedical", 2);
		util.selectbyindex(name + "IsFresher", 2);
		util.selectbyindex(name + "RTWCheckApplicable", 2);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel1_save")).click();

		util.wait_until_loader_is_invisible();
		util.clickAlertbox();
		driver.get(this.config.getProperty("url") + "/Matrix/UserHome.aspx");
		// driver.get("http://192.168.2.16/MatexTesting/Matrix/UserHome.aspx");
		this.navigate("Daily Activity", "Data Entry");
		driver.findElement(By.linkText(matrixNo)).click();
		util.wait_until_loader_is_invisible();
		String fname = driver.findElement(By.id(name + "Fathername")).getAttribute("value");
		assertEquals("fname", fname);

	}

	@Test(priority = 2, enabled = true, dependsOnMethods = "dataentry")
	public void fileupload() throws Exception {
		String name = "ctl00_ContentPlaceHolder1_";
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel1_btnAttachment")).click();
		Utility util = new Utility(driver);

		driver.findElement(By.id(name + "TabContainer1_TabPanel1_Button1")).click();
		;

		util.wait_until_loader_is_invisible();
		util.selectbyindex(name + "ddlDocumentType", 1);
		driver.findElement(By.id(name + "fup")).sendKeys(filepath + "\\" + filename);
		util.wait_until_loader_is_invisible();
		util.clickAlertbox();

		String actual = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_upGrid']/tbody/tr[2]/td[1]"))
				.getText();
		assertEquals(filename, actual);
	}

	@Test(priority = 3, enabled = true, dependsOnMethods = "fileupload")
	public void filedownload() throws Exception {
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_upGrid_ctl02_downdoc")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnContactClose")).click();

		// driver.findElement(By.id("ctl00_btnHome")).click();
		Utility util = new Utility(driver);
		assertTrue(util.isfileexist(new File(downloadFilepath), filename));

	}

	@Test(priority = 4, enabled = true, dependsOnMethods = "filedownload")
	public void reportpreview() throws Exception {
		this.navigate("Dashboard", "Report TM Dashboard");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_grdteammember_ctl02_MatrixRefNo")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnPublishandPreview")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_btn_preview")).click();
		String currentw = driver.getWindowHandle();
		Set<String> childw = driver.getWindowHandles();
		for (String e : childw) {
			if (!(e.equals(currentw))) {
				driver.switchTo().window(e);
			}
		}
		Screen sc = new Screen();
		Pattern pdfdown = new Pattern(this.config.getProperty("down"));
		Pattern save = new Pattern(this.config.getProperty("save"));
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		System.out.println("key press");
		sc.click(pdfdown);
		robot.keyRelease(KeyEvent.VK_TAB);
		sc.click(save);
		driver.close();

	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

	public void navigate(String title, String page) {
		driver.findElement(By.xpath("//a[text()='" + title + "']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By name = By.xpath("//a[text()='" + page + "']");
		wait.until(ExpectedConditions.presenceOfElementLocated(name));
		driver.findElement(name).click();
	}
}
