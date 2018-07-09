package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.time.Duration;

import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;

import environment.ReadEmail;
import testCases.Pages;

public class CandidateInitiation {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;

	public CandidateInitiation(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);

	}

	public String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
		return pr.getProperty(key);
	}

	public String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}

	public String InitiateCandidate(int id) throws Exception {
		pages.Utill().click_element(getlocator("cc_Client_namedd"));
		pages.Utill().input_text(getlocator("cc_Client_name"), getvalue("cc_Client_name"));
		pages.Utill().click_element(".//*[@class='active-result highlighted']");
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().input_text(getlocator("cc_CandidateName"), pages.Utill().candidateName());
		pages.Utill().click_element(getlocator("cc_MobileNo"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().input_text(getlocator("cc_MobileNo"), getvalue("cc_MobileNo"));
		pages.Utill().input_text(getlocator("cc_EmailID"), getvalue("cc_EmailID"));
		pages.Utill().input_text(getlocator("cc_HREmail"), getvalue("cc_HREmail"));
		pages.Utill().input_text(getlocator("cc_CanId"), Integer.toString(id));
		if (getlocator("cc_Client_name").equals("automation")) {
			pages.Utill().select_by_label(getlocator("cc_Region"), "East");
		}
		pages.Utill().click_element(getlocator("cc_RadioButtonList"));
		String loginId = pages.Utill().find(getlocator("cc_LoginID")).getAttribute("value");
		pages.Utill().click_element(getlocator("cc_save"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxlast");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListemp", "2");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListref", "2");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxedu");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbx10");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbx12");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxhighest");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxug1");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxdiploma");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxpg1");
		pages.Utill().click_element(getlocator("cc_initiate"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().clickAlertbox();
		System.out.println("------------loginid is :_" + loginId + "--------------------------");
		return loginId;

	}

	public void Personal() throws Exception {
		wait_until_loader_is_invisible();

		pages.Utill().SwitchFramebyIndex(0);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(pages.Utill().find(getlocator("cp_Middlename"))));
		pages.Utill().input_text(getlocator("cp_Middlename"), getvalue("cp_Middlename"));
		pages.Utill().input_text(getlocator("cp_Fathername"), getvalue("cp_Fathername"));
		pages.Utill().select_by_label(getlocator("cp_Gender"), getvalue("cp_Gender"));
		pages.Utill().select_by_label(getlocator("cp_MaritalStatus"), getvalue("cp_MaritalStatus"));
		pages.Utill().click_element(getlocator("cp_DOB"));
		EnterDate("01-January-1984", "CandidateHome1_CanDobdate_title", "CandidateHome1_CanDobdate_prevArrow");
		pages.Utill().input_text(getlocator("cpHR_Name"), getvalue("cpHR_Name"));
		pages.Utill().input_text(getlocator("cp_EmpNo"), getvalue("cp_EmpNo"));
		pages.Utill().input_text(getlocator("cp_Designation"), getvalue("cp_Designation"));
		pages.Utill().input_text(getlocator("cp_Grade"), getvalue("cp_Grade"));
		pages.Utill().input_text(getlocator("cp_JoinPlace"), getvalue("cp_JoinPlace"));
		pages.Utill().input_text(getlocator("cp_PanNo"), getvalue("cp_PanNo"));
		pages.Utill().input_text(getlocator("cp_ITPinNo"), getvalue("cp_ITPinNo"));
		pages.Utill().input_text(getlocator("cp_PassportNo"), getvalue("cp_PassportNo"));
		pages.Utill().input_text(getlocator("cp_Issue_Place"), getvalue("cp_Issue_Place"));
		pages.Utill().input_text(getlocator("cp_Issue_State"), getvalue("cp_Issue_State"));
		pages.Utill().input_text(getlocator("cp_Issue_Country"), getvalue("cp_Issue_Country"));
		pages.Utill().scrollTo(getlocator("cp_Expire_Date"));
		pages.Utill().click_element(getlocator("cp_Expire_Date"));
		EnterDate("20-June-2018", "CandidateHome1_Candidate_EXdate_title", "CandidateHome1_Candidate_EXdate_prevArrow");
		pages.Utill().input_text(getlocator("cp_MobileTwo1"), getvalue("cp_MobileTwo1"));
		pages.Utill().input_text(getlocator("cp_VoterId"), getvalue("cp_VoterId"));
		pages.Utill().input_text(getlocator("cp_VoterAddress"), getvalue("cp_VoterAddress"));
		pages.Utill().input_text(getlocator("cp_IDProofNo"), getvalue("cp_IDProofNo"));
		pages.Utill().input_text(getlocator("cp_SSNNo"), getvalue("cp_SSNNo"));
		pages.Utill().click_element(getlocator("cp_Issue_Date"));
		EnterDate("15-July-2010", "CandidateHome1_CandidateIsDate_title", "CandidateHome1_CandidateIsDate_prevArrow");
		pages.Utill().input_text(getlocator("cp_AadharName"),
				pages.Utill().find(getlocator("cc_Candidate_Name")).getAttribute("value"));
		pages.Utill().input_text(getlocator("cp_DrivingLicenseNo"), getvalue("cp_DrivingLicenseNo"));
		pages.Utill().input_text(getlocator("cp_AadharNumber"), getvalue("cp_AadharNumber"));
		pages.Utill().input_text(getlocator("cp_AadharEnrollNumber"), getvalue("cp_AadharEnrollNumber"));
		pages.Utill().input_text(getlocator("cp_Comments"), getvalue("cp_Comments"));
		// Thread.sleep(5000);
		pages.Utill().select_by_label(getlocator("cp_IsFresher"), getvalue("cp_IsFresher"));
		pages.Utill().scrollTo(getlocator("cp_save"));
		pages.Wait().visibilityOfElement(getlocator("cp_save"));
		pages.Utill().click_element(getlocator("cp_save"));
		System.out.println("wait starting");
		driver.switchTo().defaultContent();
		wait_until_loader_is_invisible();
		pages.Utill().SwitchFramebyIndex(0);
		System.out.println(pages.Utill().get_text("regMessage"));
		pages.Utill().click_element("//span[text()='Ok']");
	}

	public void address(String id) throws Exception {
		DataFactory df = new DataFactory();
		// for (int i = 0; i < count; i++) {
		pages.Utill().input_text(id + "_ClientAddr_Address", df.getAddress());
		pages.Utill().input_text(id + "_ClientAddr_State", "Tamil nadu");
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='Tamil nadu']")));
		pages.Utill().click_element(".//*[text()='Tamil nadu']");
		pages.Utill().input_text(id + "_ClientAddr_City", "Chennai");
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='Chennai']")));
		pages.Utill().click_element(".//*[text()='Chennai']");
		pages.Utill().input_text(id + "_ClientAddr_Pincode", "123456");
		pages.Utill().select_by_label(id + "_ClientAddr_ResiType", "Hostel");
		pages.Utill().input_text(id + "_ClientAddr_Landmark", df.getAddress());
		if (id.equals("ctl05")) {
			pages.Utill().click_element(id + "_ClientAddr_FromDt");
			EnterDate("10-May-2010", id + "_ClientAddrFrmDate_title", id + "_ClientAddrFrmDate_prevArrow");
			pages.Utill().click_element(id + "_ClientAddr_Tilldate");
		} else if (id.equals("ctl08")) {
			pages.Utill().click_element(id + "_ClientAddr_FromDt");
			EnterDate("21-April-1998", id + "_ClientAddrFrmDate_title", id + "_ClientAddrFrmDate_prevArrow");
			pages.Utill().click_element(id + "_ClientAddr_ToDt");
			EnterDate("20-May-2010", id + "_ClientAddrToDate_title", id + "_ClientAddrToDate_prevArrow");
		}
		pages.Utill().input_text(id + "_ClientAddr_PoliceSt", "near" + df.getCity());
		pages.Utill().input_text(id + "_ClientAddr_StayPeriod", "2");
		pages.Utill().input_text(id + "_ClientAddr_PoliceSt", "station" + df.getCity());
		pages.Utill().input_text(id + "_ClientAddr_Mobile1", df.getNumberText(10));
		pages.Utill().input_text(id + "_ClientAddr_MobileTwo1", df.getNumberText(10));
		pages.Utill().select_by_label(id + "_ClientAddr_LocType", "Local");
		pages.Utill().input_text(id + "_ClientAddr_Loc", df.getCity());
		pages.Utill().input_text(id + "_ClientAddr_OthLocation", "location");
		pages.Utill().input_text(id + "_ClientAddr_Others", "others");
		pages.Utill().input_text(id + "_ClientAddr_Comments", "comments");
		pages.Utill().scrollTo("btnsavehtml");
		pages.Wait().visibilityOfElement("btnsavehtml");
		pages.Utill().click_element("btnsavehtml");
		System.out.println("wait starting");
		driver.switchTo().defaultContent();
		wait_until_loader_is_invisible();
		pages.Utill().SwitchFramebyIndex(1);
		System.out.println(pages.Utill().get_text("regMessage"));
		pages.Utill().click_element("//span[text()='Ok']");
	}

	public void Edcucation() throws Exception {
		String id = "ctl04";
		pages.Utill().input_text(id + "_ClientEdu_Name", "Carmel Convent School");
		WebDriverWait w = new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Carmel Convent School']")));
		pages.Utill().click_element("//*[text()='Carmel Convent School']");
		pages.Utill().input_text(id + "_ClientEdu_Address", "chennai");
		pages.Utill().input_text(id + "_ClientEdu_DegreeName", "10");
		pages.Utill().input_text(id + "_ClientEdu_StudentID", "897987");
		pages.Utill().input_text(id + "_ClientEdu_Gap", "no");
		
		pages.Utill().click_element(id + "_ClientEdu_StartDt");
		EnterEdudate("Apr-2009", "MonthPicker_"+id+"_ClientEdu_StartDt");
		pages.Utill().input_text(id+"_ClientEdu_NameofDegree", "10");
		pages.Utill().click_element(id + "_ClientEdu_EndDt");
		EnterEdudate("Aug-2010", "MonthPicker_"+id+"_ClientEdu_EndDt");
		pages.Utill().input_text(id+"_ClientEdu_MajorSubject", "tamil");
		pages.Utill().click_element(id + "_ClientEdu_PassYear");
		EnterEdudate("Sep-2010", "MonthPicker_"+id+"_ClientEdu_PassYear");
		
		
	}

	public void wait_until_loader_is_invisible() throws InterruptedException {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200));
		// .ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = pages.Utill().find("ctl00_ContentPlaceHolder1_DivMainFrame");
				String res = ele.getCssValue("display");
				if (res.equals("none")) {
					System.out.println("success " + res);
					return ele;
				} else {
					System.out.println("failed :" + res);
					return null;
				}
			}
		});
	}

	public void FluentWait() {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200));
		// .ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = pages.Utill().find("ctl00_ContentPlaceHolder1_DivMainFrame");
				String res = ele.getCssValue("display");
				if (res.equals("none")) {
					System.out.println("success " + res);
					return ele;
				} else {
					System.out.println("failed :" + res);
					return null;
				}
			}
		});
	}

	public void EnterDate(String date, String id, String arrow) throws Exception {
		// "CandidateHome1_CanDobdate_title"
		// "CandidateHome1_CanDobdate_prevArrow"
		String t = pages.Utill().get_text(id);
		System.out.println(date);
		String[] pdate = date.replaceAll("-", " ").split("\\s+");
		String[] gdate = t.replaceAll(",", "").split("\\s+");
		if (pdate[2].equals(gdate[1])) {
			if (pdate[1].equals(gdate[0])) {
				System.out.println("click " + pdate[0]);
				pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + " " + pdate[0] + "')]");
			} else {

				System.out.println("click title");
				pages.Utill().click_element(id);

				Thread.sleep(1000);
				pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + ", " + pdate[2] + "')]");
				Thread.sleep(1000);
				pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + " " + pdate[0] + "')]");

			}
		} else {
			pages.Utill().click_element(id);
			Thread.sleep(1000);
			pages.Utill().click_element(id);
			Thread.sleep(1000);
			String year = pages.Utill().get_text(id);
			String y[] = year.split("-");
			while (!(Integer.parseInt(y[0]) - 1 <= Integer.parseInt(pdate[2])
					&& Integer.parseInt(y[1]) + 1 >= Integer.parseInt(pdate[2]))) {
				pages.Utill().click_element(arrow);
				year = pages.Utill().get_text(id);
				y = year.split("-");

			}
			Thread.sleep(1000);
			pages.Utill().click_element("//div[text()='" + pdate[2] + "']");
			Thread.sleep(1500);
			pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + ", " + pdate[2] + "')]");
			Thread.sleep(1500);
			System.out.println("//*[contains(@title,'" + pdate[1] + " " + pdate[0] + "')]");
			pages.Utill().click_element("//*[contains(@title,'" + pdate[1] + " " + pdate[0] + "')]");
			//
		}
	}
	public void EnterEdudate(String dates, String id) throws Exception{
//		String dates = "Feb-2000";
//		String id="MonthPicker_ctl04_ClientEdu_EndD";
	
		String mon = dates.split("-")[0];
		String year = dates.split("-")[1];
		pages.Utill().click_element(".//*[@id='"+id+"']/div[1]/table/tbody/tr/td[2]/a/span");
		String status = "true";
		while (status.equals("true")) {
			try {
				status = pages.Utill().find("//span[text()='" + year + "']/..").getAttribute("aria-disabled");
			} catch (NoSuchElementException e) {
				System.out.println("exception raised");
			}
			System.out.println("statsu is :" + status);
			if (status.equals("true")) {
				pages.Utill().click_element(
						"//*[@id='"+id+"']/div[1]/table/tbody/tr/td[1]/a/span[1]/i");
				System.out.println("clicking on arrow");
			}
			Thread.sleep(1000);
		}
		System.out.println("found");
		
//		 pages.Utill().click_element("//span[text()='"+year+"']");
		pages.Utill().find(id).findElement(By.xpath("//span[text()='"+year+"']")).click();
		 Thread.sleep(1000);
//		 pages.Wait().visibilityOfElement("//span[text()='"+mon+".']");
//		 pages.Utill().click_element("//span[text()='"+mon+".']");
		 pages.Utill().find(id).findElement(By.xpath("//span[text()='"+mon+".']")).click();
		 
	}
}
