package verification;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	 * Select Id tab and switch to Id frame
	 */
	public void idcheck() {
		SwitchDefault();
//		click("//*[@id='tabStrip']/div/ul/li[9]/a/span/span/span");
		click("//*[@id='tabStrip']/div/ul/li//span[text()='ID']");
		SwitchFramebyIndex(9);
	}

	/**
	 * Perform click action on Document button
	 */
	public void document() {
		click("ctl00_ContentPlaceHolder1_btnIdAddDocuments_input");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
		if (!value.trim().equals(component)) {
			click("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
			sleep(500);
			if (verifyddvalue(component)) {
				click("//div[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul//li[text()='"
						+ component + "']");
				waitUntilLoaderisInvisible(100);
			} else {
				throw new NotFoundException(component);
			}
		}
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) {

		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
					break;
				} else {
					re = false;
				}
			}
			return re;
		} else {
			return false;
		}
	}

	/**
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		click("ctl00_ContentPlaceHolder1_ddlIdCheckComponent_Input");

		click(
				"//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='" + component + "']");
	}

	/**
	 * Takes name as input Pass it to name On Id
	 * 
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdName", name);
	}

	/**
	 * Takes id card number as input and pass it to ID Number
	 * 
	 * @param number on Id card
	 */
	public void IDNumber(String number) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdNumber", number);
	}

	/**
	 * Takes name as input and pass it to issue date (dd/mm/yyy)
	 * 
	 * @param date ID card issued date
	 */
	public void IssueDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdIssueDate_dateInput", date);
	}

	/**
	 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
	 * 
	 * @param date ID card Expiry date
	 */
	public void ExpiryDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdExpireDate_dateInput", date);
	}

	/**
	 * select India as country
	 * 
	 * @deprecated country disabled for id card
	 */
	public void Country() {
		boolean re = false;
		if (re) {
			sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueCountry_Input", "India");
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li//text()='India'");
			waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes no as input and pass it to enrollment number field
	 * 
	 * @param no enrollment number
	 */
	public void EnrollmentNo(String no) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEnrollId1", no);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtIdComments", comments);
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

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnIdSave_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_btnIdAddDocument_input");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes document type as input and checks for given document type available in
	 * upload screen
	 * 
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		waitUntilElementHasText(
				"//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00__0']/td[2]", 10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00']/tbody/tr/td[2]";
		List<WebElement> list = driver.findElements(By.xpath(path));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText().trim();
				logger.log(Status.INFO, t);
				if (t.equals(doctype)) {
					re = true;
					break;
				}
			}
		} else {
			logger.log(Status.FAIL, "no element found");
		}
		return re;
	}

	/**
	 * Takes document type and file as input and uploads the document
	 * 
	 * @param doctype type of document
	 * @param file    file name
	 */
	public void UploadDocument(String doctype, String file) {
		if (this.isvaliddoctype(doctype)) {
			sendKeys("//*[text()='" + doctype + "']/../td[5]//span/input[2]", file);
			super.WaitforFileUpdate(doctype, file);
			this.AddDocument();
			waitUntilLoaderisInvisible(100);
		} else {
			throw new NotFoundException(doctype);
		}

	}
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		click("ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_btnIdDocumentCancel_input");
		waitUntilLoaderisInvisible(100);
	}

	public String Component() {
		return getValue("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
	}

	public String NameonID() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdName");
	}

	public String IDNumber() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdNumber");
	}

	public String IssueDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdExpireDate_dateInput");
	}

	public String CountryofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_ddlIdIssueCountry_Input");
	}

	public String StateofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_ddlIdIssueState_Input");
	}

	public String CityofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_ddlIdIssueCity_Input");
	}

	public String EnrollmentNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtEnrollId1");
	}

	public String comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtIdComments");
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
		if(name.equals("Passport")) {
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
			String info=getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[1]/span");
			sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[2]//div[3]/div/p/..", info);
			String info2=getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[3]/span");
			sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[4]//div[3]/div/p/..", info2);
		}
	}
}
