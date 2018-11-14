package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;


public class Employment extends dataEntry.Employment {
	public Employment(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	

	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_grdviewEmploymentDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	
	public String Component() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
	}

	public String EmployerName() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmploymentCompany_Input");
	}

	public String EmployerAddressLIne1() {
		return getValue("ctl00_ContentPlaceHolder1_txtOfficeDetails");
	}

	public String EmployerCountry() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmploymentCountry_Input");
	}

	public String EmployerState() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmployementState_Input");
	}

	public String EmployerCity() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmployementCity_Input");
	}

	public String EmployerNameasperEmployers() {
		return getValue("ctl00_ContentPlaceHolder1_txtnameEmpRec");
	}

	public String EmploymentEmployeeID() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmployeeID");
	}

	public String EmploymentDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmployeeDesignation");
	}

	public String EmploymentDepartment() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmployeeDepartment");
	}

	public String EmploymentFromDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtFromDate_dateInput");
	}

	public String EmploymentToDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtToDate_dateInput");
	}

	public String EmploymentLastCTC() {
		return getValue("ctl00_ContentPlaceHolder1_txtLastSalaryDrawn");
	}

	public String EmploymentCurrency() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input");
	}

	public String EmploymentPeriod() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input");
	}

	public String EmploymentTypeOfEmployment() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input");
	}

	public String PersonName() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1Name");
	}

	public String PersonDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1Designation");
	}

	public String PersonRelationship() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRelationship1_Input");
	}

	public String PersonIfOther() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1IfOthers");
	}

	public String PersonContactNo1() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo1");
	}

	public String PersonContactNo2() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo2");
	}

	public String PersonEmailID1() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID1");
	}

	public String PersonEmailID2() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID2");
	}

	public String PersonFaxNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1FaxNo1");
	}

	public String ReasonForLeaving() {
		return getValue("ctl00_ContentPlaceHolder1_txtReasonOfLeaving");
	}

	public String Reason() {
		return getValue("ctl00_ContentPlaceHolder1_txtTerminatedOrLaidOffComments");
	}

	public String Comments() {
		return getText("ctl00_ContentPlaceHolder1_txtCommentsIfAny");
	}
	public Map<String, String> CurrentEmp() throws Exception{
		this.Component("Current/Latest Employment");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("cEmployerName", this.EmployerName());
		map.put("cEmployerAddressLIne1", this.EmployerAddressLIne1());
		map.put("cEmployerCountry", this.EmployerCountry());
		map.put("cEmployerState", this.EmployerState());
		map.put("cEmployerCity", this.EmployerCity());
		map.put("cEmployerNameasperEmployers", this.EmployerNameasperEmployers());
		map.put("cEmploymentEmployeeID", this.EmploymentEmployeeID());
		map.put("cEmploymentDesignation", this.EmploymentDesignation());
		map.put("cEmploymentDepartment", this.EmploymentDepartment());
		map.put("cEmploymentFromDate", this.EmploymentFromDate());
		map.put("cEmploymentToDate", this.EmploymentToDate());
		map.put("cEmploymentLastCTC", this.EmploymentLastCTC());
		map.put("cEmploymentCurrency", this.EmploymentCurrency());
		map.put("cEmploymentPeriod", this.EmploymentPeriod());
		map.put("cEmploymentTypeOfEmployment", this.EmploymentTypeOfEmployment());
		map.put("cPersonName", this.PersonName());
		map.put("cPersonDesignation", this.PersonDesignation());
		map.put("cPersonRelationship", this.PersonRelationship());
		map.put("cPersonContactNo1", this.PersonContactNo1());
		map.put("cPersonContactNo2", this.PersonContactNo2());
		map.put("cPersonEmailID1", this.PersonEmailID1());
		map.put("cPersonEmailID2", this.PersonEmailID2());
		map.put("cPersonFaxNo", this.PersonFaxNo());
		map.put("cReasonForLeaving", this.ReasonForLeaving());
		map.put("cComments", this.Comments());
		this.document();
		map.put("currentemp", this.getDocumentName("Offer Letter"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> PerviousEmp() throws Exception{
		this.Component("Previous Employment");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("Component", this.Component());
		map.put("pEmployerName", this.EmployerName());
		map.put("pEmployerAddressLIne1", this.EmployerAddressLIne1());
		map.put("pEmployerCountry", this.EmployerCountry());
		map.put("pEmployerState", this.EmployerState());
		map.put("pEmployerCity", this.EmployerCity());
		map.put("pEmployerNameasperEmployers", this.EmployerNameasperEmployers());
		map.put("pEmploymentEmployeeID", this.EmploymentEmployeeID());
		map.put("pEmploymentDesignation", this.EmploymentDesignation());
		map.put("pEmploymentDepartment", this.EmploymentDepartment());
		map.put("pEmploymentFromDate", this.EmploymentFromDate());
		map.put("pEmploymentToDate", this.EmploymentToDate());
		map.put("pEmploymentLastCTC", this.EmploymentLastCTC());
		map.put("pEmploymentCurrency", this.EmploymentCurrency());
		map.put("pEmploymentPeriod", this.EmploymentPeriod());
		map.put("pEmploymentTypeOfEmployment", this.EmploymentTypeOfEmployment());
		map.put("pPersonName", this.PersonName());
		map.put("pPersonDesignation", this.PersonDesignation());
		map.put("pPersonRelationship", this.PersonRelationship());
		map.put("pPersonContactNo1", this.PersonContactNo1());
		map.put("pPersonContactNo2", this.PersonContactNo2());
		map.put("pPersonEmailID1", this.PersonEmailID1());
		map.put("pPersonEmailID2", this.PersonEmailID2());
		map.put("pPersonFaxNo", this.PersonFaxNo());
		map.put("pReasonForLeaving", this.ReasonForLeaving());
		map.put("pComments", this.Comments());
		this.document();
		map.put("preemp", this.getDocumentName("Relieving Letter"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata(String component) throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("employment");
		if(component.equals("Current/Latest Employment")) {
		map.put("Component", "Current/Latest Employment");
		map.put("cEmployerName", pro.getProperty("cEmployerName"));
		map.put("cEmployerAddressLIne1", pro.getProperty("cEmployerAddressLIne1"));
		map.put("cEmployerCountry", pro.getProperty("cEmployerCountry"));
		map.put("cEmployerState", pro.getProperty("cEmployerState"));
		map.put("cEmployerCity", pro.getProperty("cEmployerCity"));
		map.put("cEmployerNameasperEmployers", pro.getProperty("cEmployerNameasperEmployers"));
		map.put("cEmploymentEmployeeID", pro.getProperty("cEmploymentEmployeeID"));
		map.put("cEmploymentDesignation", pro.getProperty("cEmploymentDesignation"));
		map.put("cEmploymentDepartment", pro.getProperty("cEmploymentDepartment"));
		map.put("cEmploymentFromDate", pro.getProperty("cEmploymentFromDate"));
		map.put("cEmploymentToDate", pro.getProperty("cEmploymentToDate"));
		map.put("cEmploymentLastCTC", pro.getProperty("cEmploymentLastCTC"));
		map.put("cEmploymentCurrency", pro.getProperty("cEmploymentCurrency"));
		map.put("cEmploymentPeriod", pro.getProperty("cEmploymentPeriod"));
		map.put("cEmploymentTypeOfEmployment", pro.getProperty("cEmploymentTypeOfEmployment"));
		map.put("cPersonName", pro.getProperty("cPersonName"));
		map.put("cPersonDesignation", pro.getProperty("cPersonDesignation"));
		map.put("cPersonRelationship", pro.getProperty("cPersonRelationship"));
		map.put("cPersonContactNo1", pro.getProperty("cPersonContactNo1"));
		map.put("cPersonContactNo2", pro.getProperty("cPersonContactNo2"));
		map.put("cPersonEmailID1", pro.getProperty("cPersonEmailID1"));
		map.put("cPersonEmailID2", pro.getProperty("cPersonEmailID2"));
		map.put("cPersonFaxNo", pro.getProperty("cPersonFaxNo"));
		map.put("cReasonForLeaving", pro.getProperty("cReasonForLeaving"));
		map.put("cComments", pro.getProperty("cComments"));
		map.put("currentemp", new File(pro.getProperty("currentemp")).getName().replaceAll(" ", ""));
		return map;
		}
		
	
	else if(component.equals("Previous Employment")) {
		map.put("Component", "Previous Employment");
		map.put("pEmployerName", pro.getProperty("pEmployerName"));
		map.put("pEmployerAddressLIne1", pro.getProperty("pEmployerAddressLIne1"));
		map.put("pEmployerCountry", pro.getProperty("pEmployerCountry"));
		map.put("pEmployerState", pro.getProperty("pEmployerState"));
		map.put("pEmployerCity", pro.getProperty("pEmployerCity"));
		map.put("pEmployerNameasperEmployers", pro.getProperty("pEmployerNameasperEmployers"));
		map.put("pEmploymentEmployeeID", pro.getProperty("pEmploymentEmployeeID"));
		map.put("pEmploymentDesignation", pro.getProperty("pEmploymentDesignation"));
		map.put("pEmploymentDepartment", pro.getProperty("pEmploymentDepartment"));
		map.put("pEmploymentFromDate", pro.getProperty("pEmploymentFromDate"));
		map.put("pEmploymentToDate", pro.getProperty("pEmploymentToDate"));
		map.put("pEmploymentLastCTC", pro.getProperty("pEmploymentLastCTC"));
		map.put("pEmploymentCurrency", pro.getProperty("pEmploymentCurrency"));
		map.put("pEmploymentPeriod", pro.getProperty("pEmploymentPeriod"));
		map.put("pEmploymentTypeOfEmployment", pro.getProperty("pEmploymentTypeOfEmployment"));
		map.put("pPersonName", pro.getProperty("pPersonName"));
		map.put("pPersonDesignation", pro.getProperty("pPersonDesignation"));
		map.put("pPersonRelationship", pro.getProperty("pPersonRelationship"));
		map.put("pPersonContactNo1", pro.getProperty("pPersonContactNo1"));
		map.put("pPersonContactNo2", pro.getProperty("pPersonContactNo2"));
		map.put("pPersonEmailID1", pro.getProperty("pPersonEmailID1"));
		map.put("pPersonEmailID2", pro.getProperty("pPersonEmailID2"));
		map.put("pPersonFaxNo", pro.getProperty("pPersonFaxNo"));
		map.put("pReasonForLeaving", pro.getProperty("pReasonForLeaving"));
		map.put("pComments", pro.getProperty("pComments"));
		map.put("preemp", new File(pro.getProperty("preemp")).getName().replaceAll(" ", ""));
		return map;
	}
	else
		throw new NotFoundException();
	}
}
