package livetesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Base2 {
	static WebDriver driver;
	static String cadName=null;
	static int cadID=0;
	static String matrixNo="DEMOTAF348";
	@BeforeTest
	public static void browerSetup() {
		final String downloadFilepath = "";
		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put("profile.default_content_settings.popups", 0);
		config.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", config);
		options.addArguments("--disable-notifications");
		// DesiredCapabilities cap = DesiredCapabilities.chrome();
		// cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		// cap.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://192.168.2.16/matextesting");
	}

	@Test(priority = 1, enabled = true)
	public static void login() {
		driver.findElement(By.id("txtUsername")).sendKeys("demotl");
		driver.findElement(By.id("txtPassword")).sendKeys("pass@123");
		driver.findElement(By.id("btnLogin")).click();
		String name = driver.findElement(By.id("ctl00_lblUsername")).getText();
		assertEquals("Demotl", name);
	}

	@Test(priority = 2, enabled = false, dependsOnMethods = "login")
	public static void caseregistration() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Utility u = new Utility(driver);
	cadName = u.candidateName();
		cadID = u.candidateid();
		String clientName = "Demo Testing - Test";
		final String xp = "ctl00_ContentPlaceHolder1_CaseRegistrationUC";
		navigate("Dashboard", "Case Registration");
		driver.findElement(By.id(xp + "_txtClientName")).sendKeys(clientName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='" + clientName + "']"))));
		driver.findElement(By.xpath("//*[text()='" + clientName + "']")).click();
		WebElement medium = driver.findElement(By.id(xp + "_TypeofMedium"));
		Select sel = new Select(medium);
		sel.selectByIndex(1);
		try {
			WebElement hr = driver.findElement(By.id(xp + "_ddlSubGroup"));
			sel = new Select(hr);
			sel.selectByIndex(1);
		} catch (NoSuchElementException e) {
			System.out.println("hr dropdown not available");
		}
//		WebElement region = driver.findElement(By.id("ctl00_ContentPlaceHolder1_CaseRegistrationUC_drpTimesLocation"));
//		sel = new Select(region);
//		sel.selectByVisibleText("Mapletree");
		driver.findElement(By.id(xp + "_Case_CandidateName")).sendKeys(cadName);
		driver.findElement(By.id(xp + "_Case_CandidateId")).sendKeys(Integer.toString(cadID));
		driver.findElement(By.id(xp + "_Case_EmployeeId")).sendKeys("43232");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_CaseRegistrationUC_btnRegister")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
		String result = driver.findElement(By.xpath("//*[@class='m_content']")).getText();
		System.out.println(result);
		driver.findElement(By.id("ok")).click();
		assertEquals("Registered Successfully.", result);

	}

	@Test(priority = 3, enabled = true)
	public static void dataentry() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		String loc ="ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_";
		Utility util = new Utility(driver);
//		navigate("Daily Activity","Assign Cases");
//		System.out.println(cadName+" : "+cadID);
//		matrixNo=u.assignToDETM(cadName, cadID);
//		System.out.println(matrixNo);
//		u.clickAlertbox();
		navigate("Daily Activity","Data Entry");
		driver.findElement(By.linkText(matrixNo)).click();
		util.wait_until_loader_is_invisible();
		util.selectbyindex(loc+"LocType", 1);
		
		
		
		driver.findElement(By.id("__tab_ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2")).click();
		util.selectbyindex(loc+"Type", 2);
		util.wait_until_loader_is_invisible();
		driver.findElement(By.id(loc+"Address")).sendKeys("chennai");
		driver.findElement(By.id(loc+"Pincode")).sendKeys("123456");
		By code = By.xpath("//*[text()='123456']");
		wait.until(ExpectedConditions.presenceOfElementLocated(code));
		driver.findElement(code).click();
		util.wait_until_loader_is_invisible();
		driver.findElement(By.id(loc+"Landmark")).sendKeys("near it park");
		driver.findElement(By.id(loc+"FromDt")).sendKeys("May-2017");
		driver.findElement(By.id(loc+"ToDt")).sendKeys("May-2018");
		driver.findElement(By.id(loc+"StayPeriod")).sendKeys("2");
		driver.findElement(By.id(loc+"PoliceSt")).sendKeys("chennai");
		
		util.selectbyindex(loc+"LocType", 1);
		util.selectbyindex(loc+"NotApplicable", 1);
		util.selectbyindex(loc+"CtNotApplicable", 1);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_save")).click();
		util.wait_until_loader_is_invisible();
		util.clickAlertbox();
		
	}

	

	public static void navigate(String title, String page) {
		driver.findElement(By.xpath("//a[text()='" + title + "']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By name = By.xpath("//a[text()='" + page + "']");
		wait.until(ExpectedConditions.presenceOfElementLocated(name));
		driver.findElement(name).click();
	}
}
