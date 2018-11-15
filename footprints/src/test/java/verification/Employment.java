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

public class Employment extends dataEntryQC.Employment implements Verification{
	/**
	 * This is class for Employement page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Employment(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	
	/**
	 * click submit button on employment data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		click("ctl00_ContentPlaceHolder1_btnEmploymentSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();	
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}
	
	public void RespondentName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentRespondentName", name);
	}

	public String RespondentName() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmploymentRespondentName");
	}

	public void RespondentDesignation(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentRespondentDesignation", relationship);
	}

	public String RespondentDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmploymentRespondentDesignation");
	}
	public void RespondentContactNo(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentRespondentContactNoEmailID", relationship);
	}

	public String RespondentContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmploymentRespondentContactNoEmailID");
	}
	
	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentVerifierRemarks", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmploymentVerifierRemarks");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmploymentDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentDateOfVerification", date);
	}

	public String DateOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_txtEmploymentDateOfVerification");
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
		Properties pro = veridata("employment");
		this.employmentcheck();
		this.document();
		String name = this.Component();
		if(name.equals("Current/Latest Employment")) {
			this.UploadDocument("Verification Report", pro.getProperty("currentemp"));
		}
		else if (name.equals("Previous Employment")){
			this.UploadDocument("Verification Report", pro.getProperty("preemp"));
		}
		this.docclose();
		this.RespondentName(pro.getProperty("RespondentName"));
		this.RespondentContactNo(pro.getProperty("RespondentContactNo"));
		this.RespondentDesignation(pro.getProperty("RespondentDesignation"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.ModeOfInitiation(pro.getProperty("ModeOfInitiation"));
		this.DateOfInitiation(getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
	public Map<String, String> CurrentEmp() throws Exception{
		this.employmentcheck();
		this.Component("Current/Latest Employment");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("cEmployerName", this.EmployerName());
		map.put("cEmployerAddressLIne1", this.EmployerAddressLIne1());
		map.put("cEmployerCountry", this.EmployerCountry());
		map.put("cEmployerState", this.EmployerState());
		map.put("cEmployerCity", this.EmployerCity());
		//map.put("cEmployerNameasperEmployers", this.EmployerNameasperEmployers());
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
		map.put("currentemp", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("RespondentName", this.RespondentName());
		map.put("RespondentDesignation", this.RespondentDesignation());
		map.put("RespondentContactNo", this.RespondentContactNo());
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
	public Map<String, String> PerviousEmp() throws Exception{
		this.employmentcheck();
		this.Component("Previous Employment");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("Component", this.Component());
		map.put("pEmployerName", this.EmployerName());
		map.put("pEmployerAddressLIne1", this.EmployerAddressLIne1());
		map.put("pEmployerCountry", this.EmployerCountry());
		map.put("pEmployerState", this.EmployerState());
		map.put("pEmployerCity", this.EmployerCity());
		//map.put("pEmployerNameasperEmployers", this.EmployerNameasperEmployers());
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
		map.put("preemp", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("RespondentName", this.RespondentName());
		map.put("RespondentDesignation", this.RespondentDesignation());
		map.put("RespondentContactNo", this.RespondentContactNo());
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
		String date=getCurrentDate("dd/MM/yyyy");
		String component=this.Component();
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= veridata("employment");
		if(component.equals("Current/Latest Employment")) {
		map.put("Component", "Current/Latest Employment");
		map.put("cEmployerName", pro.getProperty("cEmployerName"));
		map.put("cEmployerAddressLIne1", pro.getProperty("cEmployerAddressLIne1"));
		map.put("cEmployerCountry", pro.getProperty("cEmployerCountry"));
		map.put("cEmployerState", pro.getProperty("cEmployerState"));
		map.put("cEmployerCity", pro.getProperty("cEmployerCity"));
		//map.put("cEmployerNameasperEmployers", pro.getProperty("cEmployerNameasperEmployers"));
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
		map.put("RespondentName", pro.getProperty("RespondentName"));
		map.put("RespondentDesignation", pro.getProperty("RespondentDesignation"));
		map.put("RespondentContactNo", pro.getProperty("RespondentContactNo"));
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
		
	
	else if(component.equals("Previous Employment")) {
		map.put("Component", "Previous Employment");
		map.put("pEmployerName", pro.getProperty("pEmployerName"));
		map.put("pEmployerAddressLIne1", pro.getProperty("pEmployerAddressLIne1"));
		map.put("pEmployerCountry", pro.getProperty("pEmployerCountry"));
		map.put("pEmployerState", pro.getProperty("pEmployerState"));
		map.put("pEmployerCity", pro.getProperty("pEmployerCity"));
		//map.put("pEmployerNameasperEmployers", pro.getProperty("pEmployerNameasperEmployers"));
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
		map.put("RespondentName", pro.getProperty("RespondentName"));
		map.put("RespondentDesignation", pro.getProperty("RespondentDesignation"));
		map.put("RespondentContactNo", pro.getProperty("RespondentContactNo"));
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
		click("ctl00_ContentPlaceHolder1_btnEmploymentAddComments");
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
