package livetesting;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.*;



import environment.Utill;

public class Base2 {
	protected WebDriver driver;
	protected String matrixNo = "DEMOTAC152";
	protected String downloadFilepath = null;
	protected String filename = null;
	protected String filepath = null;
	protected String cadname = null;
	protected String addressfilename=null;
	protected int cadid = 0;
	Properties config = new Properties();
	Utility utill;

	@BeforeSuite
	public void suitesetup() throws Exception {
		String current = System.getProperty("user.dir");
		config.load(new FileInputStream(new File(current + "/config.properties")));
		downloadFilepath = config.getProperty("downloadFilepath");
		filename = config.getProperty("filename");
		filepath = config.getProperty("filepath");
		addressfilename=config.getProperty("addresspdf");

	}

	@BeforeTest
	public void browerSetup() throws Exception {

		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put("profile.default_content_settings.popups", 0);
		config.put("download.default_directory", downloadFilepath);
		config.put("download.prompt_for_download", false);
		config.put("download.directory_upgrade", true);
		// config.put("plugins.always_open_pdf_externally", true);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", config);
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(this.config.getProperty("url"));

		utill = new Utility(driver);
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

	@Test(priority = 2, enabled = false, dependsOnMethods = "login")
	public void dataentry() throws Exception {
		// case registration
		this.navigate("Dashboard", "Case Registration");
		String name = "ctl00_ContentPlaceHolder1_CaseRegistrationUC_";
		driver.findElement(By.id(name + "txtClientName")).sendKeys(this.config.getProperty("clientname"));
		String cname = driver
				.findElement(
						By.xpath("//*[@id='ctl00_ContentPlaceHolder1_CaseRegistrationUC_ace1_completionListElem']/li"))
				.getText();
		if (cname.equals(this.config.getProperty("clientname"))) {
			driver.findElement(
					By.xpath("//*[@id='ctl00_ContentPlaceHolder1_CaseRegistrationUC_ace1_completionListElem']/li"))
					.click();
			// utill = new Utility(driver);
			cadname = utill.candidateName();
			cadid = utill.candidateid();
			driver.findElement(By.id(name + "Case_CandidateName")).sendKeys(cadname);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_CaseRegistrationUC_Case_CandidateId"))
					.sendKeys(Integer.toString(cadid));
			utill.selectbyindex(name + "TypeofMedium", 1);
			driver.findElement(By.id(name + "btnRegister")).click();
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
			utill.clickAlertbox();
			// data entry tl

			this.navigate("Daily Activity", "Assign Cases");
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtCandidateName")).sendKeys(cadname);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtCandidateid")).sendKeys(Integer.toString(cadid));
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_butnSearch")).click();
			utill.wait_until_loader_is_invisible();
			matrixNo = utill.GetTableCellValue("ctl00_ContentPlaceHolder1_grdCRT", 2, 4);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_grdCRT_ctl02_chkCRTSelect")).click();
			utill.selectbytext("ctl00_ContentPlaceHolder1_ddlTM", "demotl");
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnAssign")).click();

			utill.wait_until_loader_is_invisible();
			Thread.sleep(1000);
			String temp = utill.clickAlertbox();
			System.out.println(temp);
			// ctl00_ContentPlaceHolder1_butnSearch
			// data entry
			name = "ctl00_ContentPlaceHolder1_TabContainer1_TabPanel1_CandidateHome1_Candidate_";
			this.navigate("Daily Activity", "Data Entry");

			driver.findElement(By.linkText(matrixNo)).click();
			utill.wait_until_loader_is_invisible();
			driver.findElement(By.id(name + "Fathername")).clear();
			driver.findElement(By.id(name + "Fathername")).sendKeys("fname");
			utill.selectbyindex(name + "DBCheckApplicable", 2);
			utill.selectbyindex(name + "AadharApplicable", 2);
			utill.selectbyindex(name + "DrivingLicenseApplicable", 2);
			utill.selectbyindex(name + "PassportApplicable", 2);
			utill.selectbyindex(name + "VoterIDApplicable", 2);
			utill.selectbyindex(name + "DrugCheckApplicable", 2);
			utill.selectbyindex(name + "DrugCheckMedical", 2);
			utill.selectbyindex(name + "IsFresher", 2);
			utill.selectbyindex(name + "RTWCheckApplicable", 2);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel1_save")).click();

			utill.wait_until_loader_is_invisible();
			utill.clickAlertbox();
			driver.get(this.config.getProperty("url") + "/Matrix/UserHome.aspx");
			// driver.get("http://192.168.2.16/MatexTesting/Matrix/UserHome.aspx");
			this.navigate("Daily Activity", "Data Entry");
			driver.findElement(By.linkText(matrixNo)).click();
			utill.wait_until_loader_is_invisible();
			String fname = driver.findElement(By.id(name + "Fathername")).getAttribute("value");
			assertEquals("fname", fname);
		} else {
			new Exception(this.config.getProperty("clientname") + " not found");
		}
		// driver.findElement()

		// String name =
		// "ctl00_ContentPlaceHolder1_TabContainer1_TabPanel1_CandidateHome1_Candidate_";

	}

	@Test(priority = 2, enabled = false, dependsOnMethods = "dataentry")
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
		boolean re = driver.findElement(By.xpath("//td[text()='Testpdf.pdf']")).isDisplayed();
		// String actual =
		// driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_upGrid']/tbody/tr[2]/td[1]"))
		// .getText();
		assertTrue(re);
	}

	@Test(priority = 3, enabled = false, dependsOnMethods = "fileupload")
	public void filedownload() throws Exception {
		driver.findElement(By.xpath("//td[text()='Testpdf.pdf']/following-sibling::td[2]/input")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnContactClose")).click();

		// driver.findElement(By.id("ctl00_btnHome")).click();
		Utility util = new Utility(driver);
		assertTrue(util.isfileexist(new File(downloadFilepath), filename));

	}

	@Test(priority = 4, enabled = false)
	public void address() throws Exception {
		// demo
		//matrixNo = "AUTOMAA126";
		this.navigate("Daily Activity", "Data Entry");

		driver.findElement(By.linkText(matrixNo)).click();
		utill.wait_until_loader_is_invisible();
		// address data entry
		driver.findElement(By.id("__tab_ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2")).click();
		utill.selectbytext("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_Type",
				"Present");
		utill.wait_until_loader_is_invisible();
		driver.findElement(
				By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_Address"))
				.sendKeys("chennai");
		driver.findElement(
				By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_Pincode"))
				.sendKeys("123456");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='123456']")));
		// Thread.sleep(1500);
		driver.findElement(By.xpath("//*[text()='123456']")).click();
		utill.wait_until_loader_is_invisible();
		utill.selectbyindex(
				"ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_NotApplicable", 1);
		utill.wait_until_loader_is_invisible();
		utill.selectbyindex(
				"ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_CtNotApplicable", 2);
		utill.wait_until_loader_is_invisible();
		driver.findElement(
				By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_FromDt"))
				.sendKeys("Jan-2017");
		driver.findElement(
				By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_StayPeriod"))
				.click();
		driver.findElement(
				By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_ClientAddr_Tilldate"))
				.click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TabContainer1_TabPanel2_CandidateAddress1_save")).click();

		utill.wait_until_loader_is_invisible();
		utill.clickAlertbox();
		String t = "ctl00_ContentPlaceHolder1_ImgBtnSearch";
		driver.findElement(By.id(t)).click();
		try {
			Alert alert = driver.switchTo().alert();
			t = alert.getText();

			alert.accept();
			utill.wait_until_loader_is_invisible();
			assertTrue(true);
		} catch (NoAlertPresentException e) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			t = alert.getText();
			alert.accept();
			utill.wait_until_loader_is_invisible();
			assertTrue(true);

		} catch (UnhandledAlertException e) {
			System.out.println("UnhandledAlertException");
			Alert alert = driver.switchTo().alert();
			t = alert.getText();

			alert.accept();
			utill.wait_until_loader_is_invisible();
			assertTrue(true);

		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test(priority = 5, enabled = false)
	public void addressverify() throws Exception {
		//matrixNo = "AUTOMAA126";
		this.navigate("Dashboard", "Residence TM");
		driver.findElement(By.linkText(matrixNo)).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_RightCandidateAddress1_ClientAddr_FromDt")).sendKeys("Jan-2017");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_RightCandidateAddress1_ClientAddr_ToDt")).sendKeys("Dec-2017");
		utill.selectbytext("ctl00_ContentPlaceHolder1_RightCandidateAddress1_ClientAddr_Typeofrevert", "Verbal-Final");
		utill.selectbytext("ctl00_ContentPlaceHolder1_CandidateAddressFile_Upload1_ddlDocumentType", "Physical Verification Report");
		utill.wait_until_loader_is_invisible();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_CandidateAddressFile_Upload1_fup")).sendKeys(filepath + "\\" + addressfilename);
		utill.wait_until_loader_is_invisible();
		utill.clickAlertbox();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_img1")).click();
		utill.handle_Alert();
		utill.wait_until_loader_is_invisible();
		driver.get(this.config.getProperty("url") + "/Matrix/UserHome.aspx");
	}
	@Test(priority=6, enabled=true)
	public void assignReport(String no) throws Exception {
		matrixNo = "AUTOMAA126";
		try {
			this.navigate("Dashboard", "Report TL Dashboard");
		} catch (Exception e) {
			driver.get(this.config.getProperty("url") + "/ReportPrepare/AssignCase.aspx");
			
		}
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_MatrixRef")).sendKeys(matrixNo);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ApplyFilter")).click();
		
		
//		utill.wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
//		String fcount = pages.Utill().get_text(getlocator("re_final"));
//		if (Integer.parseInt(fcount) == 1) {
//			pages.Utill().click_element(getlocator("re_final"));
//			pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("re_matno"))));
//			String refno = pages.Utill().get_text(getlocator("re_matno"));
//			if (refno.equalsIgnoreCase(no)) {
//				pages.Utill().click_element(getlocator("re_check"));
//				pages.Utill().click_element(getlocator("re_pri"));
//				pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
//				pages.Utill().click_element(getlocator("re_check"));
//				// Thread.sleep(2000);
//				pages.Utill().select_by_label(getlocator("re_tmname"), getvalue("re_tmname"));
//				pages.Utill().click_element(getlocator("re_assi"));
//				pages.Wait().wait_until_spinner_is_invisible("ctl00_ContentPlaceHolder1_overlayScreen");
//			} else {
//				System.out.println(no + "  not found in table");
//				logger.log(Status.FAIL, no + "  not found in table");
//				String temp = Utill.getScreenshot(driver);
//				logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
//			}
//		} else {
//			System.out.println("final is :" + fcount);
//			logger.log(Status.FAIL, "final is :" + fcount);
//			String temp = Utill.getScreenshot(driver);
//			logger.fail("", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
//			throw new Exception("final is :" + fcount);
//
//		}

	}

	// @Test(priority = 4, enabled = true, dependsOnMethods = "filedownload")
	@Test(priority = 4, enabled = false)
	public void reportpreview() throws Exception {

		this.navigate("Dashboard", "Report TM Dashboard");
		driver.findElement(By.xpath(".//*[@id='aspnetForm']/div[3]/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/button"))
				.click();
		driver.findElement(
				By.xpath("//*[@id='aspnetForm']/div[3]/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/ul/li[1]/div/input"))
				.sendKeys("automation");
		driver.findElement(By
				.xpath("//*[@id='aspnetForm']/div[3]/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/ul/li[3]/a/label/input"))
				.click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ApplyFilter")).click();
		matrixNo = driver.findElement(By.id("ctl00_ContentPlaceHolder1_grdteammember_ctl02_MatrixRefNo")).getText();
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
		//
		this.waitForImage(this.config.getProperty("down"), 5);
		sc.click(pdfdown);
		robot.keyRelease(KeyEvent.VK_TAB);
		sc.click(save);
		driver.close();
		driver.switchTo().window(currentw);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnContactClose")).click();
		driver.get(this.config.getProperty("url") + "/Matrix/UserHome.aspx");

		System.out.println(matrixNo);
	}

	@Test(priority = 5, enabled = false, dependsOnMethods = "reportpreview")
	public void crtdocumentupload() throws Exception {
		// matrixNo = "DEMOTAC152";
		this.navigate("CRT", "CRT Document Upload");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtMatrixRefNo")).sendKeys(matrixNo);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_butnSearch")).click();
		utill.selectbyindex("ctl00_ContentPlaceHolder1_ddlUploadType", 0);
		utill.selectbytext("ctl00_ContentPlaceHolder1_ddltype", "Personal");
		utill.selectbytext("ctl00_ContentPlaceHolder1_ddlDocumentTypegen", "Passport");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_addfup")).sendKeys(filepath + "\\" + filename);
		utill.wait_until_loader_is_invisible();
		String msg = utill.clickAlertbox();
		System.out.println(msg);
		boolean re = msg.contains("The following file(s) are already uploaded")
				|| msg.contains("Document Uploaded Successfully");
		assertTrue(re);
	}

	@Test(priority = 6, enabled = false, dependsOnMethods = "crtdocumentupload")
	public void reportdoc() throws Exception {
		try {
			this.navigate("Dashboard", "Report TM Dashboard");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[text()='" + matrixNo + "'])[2]")).click();
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddl_subcheck_type_title")).click();
			driver.findElement(By.xpath("//span[text()=' Personal']")).click();
			WebElement source = driver.findElement(By.xpath("//a[text()='" + filename + "']"));
			WebElement target = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ReportDocument"));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true);", target);

			Actions action = new Actions(driver);
			action.dragAndDrop(source, target).build().perform();
			driver.findElement(By.xpath("//a[text()='" + filename + "']")).click();

			Screen sc = new Screen();
			Pattern pdfverify = new Pattern(this.config.getProperty("pdfverify"));
			Match m = sc.exists(pdfverify.exact());
			System.out.println(m.toString());
			assertTrue(true);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage().toString());
			assertTrue(false);
		}

	}

	@AfterTest
	public void teardown() {
		// driver.quit();
	}

	private boolean isImagePresent(String image) {
		boolean status = false;
		Screen screen = new Screen();
		try {
			screen.find(image);
			status = true;
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	private void waitForImage(String image, int time) throws InterruptedException {
		for (int i = 0; i < time; i++) {
			if (isImagePresent(image)) {
				break;
			} else {
				Thread.sleep(1000);
			}
		}
	}

	private void navigate(String title, String page) {
		driver.findElement(By.xpath("//a[text()='" + title + "']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By name = By.xpath("//a[text()='" + page + "']");
		wait.until(ExpectedConditions.presenceOfElementLocated(name));
		driver.findElement(name).click();
	}

}
