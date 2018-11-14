package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Credit extends dataEntry.Credit{
	/**
	 * This is class for Credit page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Credit(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Credit tab and switch to Credit frame
	 */
	
	
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	public String Component() {
		return getValue("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
	}

	public String ID() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCreditId_Input");
	}

	public String NameonID() {
		return getValue("ctl00_ContentPlaceHolder1_txtCreditIdName");
	}

	public String IDNumber() {
		return getValue("ctl00_ContentPlaceHolder1_txtCreditIdNumber");
	}

	public String IssueDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtCreditIdIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtCreditIdExpireDate_dateInput");
	}

	public String CountryofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_Input");
	}

	public String StateofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueState_Input");
	}

	public String CityofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueCity_Input");
	}

	public String EnrollmentNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtCreditEnrollId1");
	}

	public String comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtCreditComments");
	}
	
	public Map<String, String> credit() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("ID", this.ID());
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
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("credit");
		map.put("Component", "Credit Check 1");
		map.put("ID", pro.getProperty("ID"));
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
}
