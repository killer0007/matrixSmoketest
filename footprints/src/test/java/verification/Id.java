package verification;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Id extends dataEntryQC.Id implements Verification {
	/**
	 * This is class for Id page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Id(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	
	/**
	 * click submit button on id data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnIdSaveSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}

	
	public void VerifierName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierName", name);
	}

	public String VerifierName() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdVerifierName");
	}

	public void VerifierDesignation(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierDesignation", relationship);
	}

	public String VerifierDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdVerifierDesignation");
	}

	public void VerifierContactNo(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierNo", relationship);
	}

	public String VerifierContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdVerifierNo");
	}

	public void VerifierEmail(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierEmail", relationship);
	}

	public String VerifierEmail() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdVerifierEmail");
	}

	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdVerfierComments", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdVerfierComments");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlIdVerifierMark_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlIdVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlIdVerifierMark_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlIdVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdDateOfVerification", date);
	}

	public String DateOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}
	public void Verification() throws Exception {
		Properties pro = veridata("id");
		this.idcheck();
		this.document();
		String name = this.Component();
		if(name.equals("Passport") || name.equals("Voter ID") ) {
			this.UploadDocument("Verification Report", pro.getProperty("passportdoc"));
		}
		this.docclose();
		this.VerifierName(pro.getProperty("VerifierName"));
		this.VerifierDesignation(pro.getProperty("VerifierDesignation"));
		this.VerifierContactNo(pro.getProperty("VerifierContactNo"));
		this.VerifierEmail(pro.getProperty("VerifierEmail"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.ModeOfInitiation(pro.getProperty("ModeOfInitiation"));
		this.DateOfInitiation(getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
	public Map<String, String> Aadharcard() throws Exception{
		this.idcheck();
		this.Component("Aadhaar Card");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("NameonID", this.NameonID());
		map.put("IDNumber", this.IDNumber());
		map.put("IssueDate", this.IssueDate());
		map.put("ExpiryDate", this.ExpiryDate());
		map.put("CountryofIssue", this.CountryofIssue());
		map.put("StateofIssue", this.StateofIssue());
		map.put("CityofIssue", this.CityofIssue());
		map.put("EnrollmentNo", this.EnrollmentNo());
		map.put("comments", this.comments());
		this.document();
		map.put("Aadhardoc", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("VerifierName", this.VerifierName());
		map.put("VerifierDesignation", this.VerifierDesignation());
		map.put("VerifierContactNo", this.VerifierContactNo());
		map.put("VerifierEmail", this.VerifierEmail());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ModeOfInitiation", this.ModeOfInitiation());
		map.put("DateOfInitiation", this.DateOfInitiation());
		map.put("ModeOfVerification", this.ModeOfVerification());
		map.put("DateOfVerification", this.DateOfVerification());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}
	public Map<String, String> PassPort() throws Exception{
		this.idcheck();
		this.Component("Passport");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("PNameonID", this.NameonID());
		map.put("PIDNumber", this.IDNumber());
		map.put("PIssueDate", this.IssueDate());
		map.put("PExpiryDate", this.ExpiryDate());
		map.put("PCountryofIssue", this.CountryofIssue());
		map.put("PStateofIssue", this.StateofIssue());
		map.put("PCityofIssue", this.CityofIssue());
		map.put("Pcomments", this.comments());
		this.document();
		map.put("passportdoc", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("VerifierName", this.VerifierName());
		map.put("VerifierDesignation", this.VerifierDesignation());
		map.put("VerifierContactNo", this.VerifierContactNo());
		map.put("VerifierEmail", this.VerifierEmail());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ModeOfInitiation", this.ModeOfInitiation());
		map.put("DateOfInitiation", this.DateOfInitiation());
		map.put("ModeOfVerification", this.ModeOfVerification());
		map.put("DateOfVerification", this.DateOfVerification());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		String component=this.Component();
		String date=getCurrentDate("dd/MM/yyyy");
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= veridata("id");
		if(component.equals("Aadhaar Card")) {
		map.put("Component", "Aadhaar Card");
		map.put("NameonID", pro.getProperty("NameonID"));
		map.put("IDNumber", pro.getProperty("IDNumber"));
		map.put("IssueDate", pro.getProperty("IssueDate"));
		map.put("ExpiryDate", pro.getProperty("ExpiryDate"));
		map.put("CountryofIssue", pro.getProperty("CountryofIssue"));
		map.put("StateofIssue", pro.getProperty("StateofIssue"));
		map.put("CityofIssue", pro.getProperty("CityofIssue"));
		map.put("EnrollmentNo", pro.getProperty("EnrollmentNo"));
		map.put("comments", pro.getProperty("comments"));
		map.put("Aadhardoc", new File(pro.getProperty("Aadhardoc")).getName().replaceAll(" ", ""));
		map.put("VerifierName", pro.getProperty("VerifierName"));
		map.put("VerifierDesignation", pro.getProperty("VerifierDesignation"));
		map.put("VerifierContactNo", pro.getProperty("VerifierContactNo"));
		map.put("VerifierEmail", pro.getProperty("VerifierEmail"));
		map.put("Ver_Comments", pro.getProperty("verComments"));
		map.put("ComponentStatus", pro.getProperty("ComponentStatus"));
		map.put("ModeOfInitiation", pro.getProperty("ModeOfInitiation"));
		map.put("DateOfInitiation", date);
		map.put("ModeOfVerification", pro.getProperty("ModeOfVerification"));
		map.put("DateOfVerification", date);
		map.put("ServiceProvider", pro.getProperty("ServiceProvider"));
		logger.log(Status.INFO, map.toString());
		return map;
		}
		else if(component.equals("Passport")) {
			map.put("Component", "Passport");
			map.put("PNameonID", pro.getProperty("PNameonID"));
			map.put("PIDNumber", pro.getProperty("PIDNumber"));
			map.put("PIssueDate", pro.getProperty("PIssueDate"));
			map.put("PExpiryDate", pro.getProperty("PExpiryDate"));
			map.put("PCountryofIssue", pro.getProperty("PCountryofIssue"));
			map.put("PStateofIssue", pro.getProperty("PStateofIssue"));
			map.put("PCityofIssue", pro.getProperty("PCityofIssue"));
			map.put("Pcomments", pro.getProperty("Pcomments"));
			map.put("passportdoc", new File(pro.getProperty("passportdoc")).getName().replaceAll(" ", ""));
			map.put("VerifierName", pro.getProperty("VerifierName"));
			map.put("VerifierDesignation", pro.getProperty("VerifierDesignation"));
			map.put("VerifierContactNo", pro.getProperty("VerifierContactNo"));
			map.put("VerifierEmail", pro.getProperty("VerifierEmail"));
			map.put("Ver_Comments", pro.getProperty("verComments"));
			map.put("ComponentStatus", pro.getProperty("ComponentStatus"));
			map.put("ModeOfInitiation", pro.getProperty("ModeOfInitiation"));
			map.put("DateOfInitiation", date);
			map.put("ModeOfVerification", pro.getProperty("ModeOfVerification"));
			map.put("DateOfVerification", date);
			map.put("ServiceProvider", pro.getProperty("ServiceProvider"));
			logger.log(Status.INFO, map.toString());
			return map;
		}
		else
			throw new NotFoundException();
	}
	public void ReportComments() {
		click("ctl00_ContentPlaceHolder1_btnIdAddComments");
		waitUntilLoaderisInvisible(100);
	}
	public void CloseReportComments() {
		click("ModalClose");
		sleep(500);
	}
	public void UpdateReportComments() {
		int count=driver.findElements(By.xpath("//*[@id='accordion']/div")).size();
		
		for (int i = 1; i < count-1; i++) {
			if(i>1) {
				click("//*[@id='accordion']/div["+Integer.toString(i)+"]//b");	
			}		
			String info=getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[2]/span");
			sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[3]//div[3]/div/p/..", info);
		}
	}
}
