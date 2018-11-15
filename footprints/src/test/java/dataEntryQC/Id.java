package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Id extends dataEntry.Id{
	/**
	 * This is class for Id page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Id(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
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
	/**
	 * click submit button on id data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		click("ctl00_ContentPlaceHolder1_btnIdSaveSubmit_input");
		waitUntilLoaderisInvisible(100);
			SwitchDefault();	
		confirmAlert();
	}
	public Map<String, String> Aadharcard() throws Exception{
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
		map.put("Aadhardoc", this.getDocumentName("Aadhaar Id - Front"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> PassPort() throws Exception{
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
		map.put("passportdoc", this.getDocumentName("Passport Scan - Front"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata(String component) throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("id");
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
			return map;
		}
		else
			throw new NotFoundException();
	}
}
