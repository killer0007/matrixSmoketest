package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;


public class Database extends dataEntry.Database{

	public Database(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_gviewDataBaseDocuments_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	
	public String IdType() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_Input");
	}

	public String NameonID() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseNameOnID");
	}

	public String IDNumber() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIDNumber");
	}

	public String IssueDate() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseExpiryDate_dateInput");
	}

	public String CountryofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_Input");
	}

	public String StateofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_Input");
	}

	public String CityofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_Input");
	}

	public String EnrollmentNo() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseEnrollid");
	}

	public String comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseComments");
	}
	public Map<String, String> getdatabase() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("IdType", this.IdType());
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
		map.put("dbdoc", this.getDocumentName("Others"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("database");
		map.put("IdType", pro.getProperty("IdType"));
		map.put("NameonID", pro.getProperty("NameonID"));
		map.put("IDNumber", pro.getProperty("IDNumber"));
		map.put("IssueDate", pro.getProperty("IssueDate"));
		map.put("ExpiryDate", pro.getProperty("ExpiryDate"));
		map.put("CountryofIssue", pro.getProperty("CountryofIssue"));
		map.put("StateofIssue", pro.getProperty("StateofIssue"));
		map.put("CityofIssue", pro.getProperty("CityofIssue"));
		map.put("EnrollmentNo", pro.getProperty("EnrollmentNo"));
		map.put("comments", pro.getProperty("comments"));
		map.put("dbdoc", new File(pro.getProperty("dbdoc")).getName().replaceAll(" ", ""));
		return map;
		}
}
