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

public class Employment extends Verification{
	/**
	 * This is class for Employement page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Employment(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select Employment tab and switch to Employment frame
	 */
	public void employementcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[3]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Employment']");
		pages.Utill().SwitchFramebyIndex(2);
	}
	/**
	 * Performs click action on Document button
	 */

	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEmploymentDocumentUpload_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
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
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
		if(!value.trim().equals(component)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill().click(
						"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul//li[text()='"
								+ component + "']");
				pages.Utill().waitUntilLoaderisInvisible(100);
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
try {
	Thread.sleep(1500);
} catch (InterruptedException e) {
	e.printStackTrace();
}
		List<WebElement> list = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul/li"));
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
	 * Takes Company name as input and pass it TCS Ltd
	 * 
	 * @param name name of Company
	 */
	public void CompanyName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEmploymentCompany_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentCompany_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentCompany_DropDown']/div/ul//li[text()='" + name + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes Company address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void CompanyAddressLine1(String address) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtOfficeDetails", address);
	}

	/**
	 * select tamil nadu as Company state
	 */
	public void CompanyState() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEmployementState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as Company city
	 */
	public void CompanyCity() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEmployementCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes candidate name as input
	 * 
	 * @param name of candidate as per employer record
	 */
	public void NameasperEmployersRecord(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtnameEmpRec", name);
	}

	/**
	 * Takes employeeid as input
	 * 
	 * @param id employee ID
	 */
	public void EmployeeID(String id) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmployeeID", id);
	}

	/**
	 * Takes Employee Designation as input
	 * 
	 * @param designation Employee Designation
	 */
	public void EmpDesignation(String designation) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmployeeDesignation", designation);
	}

	/**
	 * Takes Employee Department name as input
	 * 
	 * @param department Employee Department name
	 */
	public void EmpDepartment(String department) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmployeeDepartment", department);
	}

	/**
	 * Takes from date as input
	 * 
	 * @param date date of join
	 */
	public void EmpFromDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtFromDate_dateInput", date);
	}

	/**
	 * 
	 * @param date date of releaving
	 */
	public void EmpToDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtToDate_dateInput", date);
	}

	/**
	 * set Till date as to date in employement date
	 */
	public void TillDate() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkTillDate");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes last CTC as input
	 * 
	 * @param ctc last CTC
	 */
	public void LastCTC(String ctc) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtLastSalaryDrawn", ctc);
	}

	/**
	 * Takes currency type INR
	 * 
	 * @param currency type of currency
	 */
	public void Currency(String currency) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input");
		if(!value.equals(currency)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input");
		WebDriverWait wait =new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCurrencyType_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCurrencyType_DropDown']/div/ul//li[text()='" + currency + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes string as input it means period for CTC like Per week, annum
	 * 
	 * @param period limit
	 */
	public void Period(String period) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input");
		if(!value.equals(period)) {
			
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_DropDown']/div/ul/li[1]")));
			pages.Utill()
			.click("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_DropDown']/div/ul//li[text()='"
					+ period + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes type of employment as input permanent or contract
	 * 
	 * @param type of employment
	 */
	public void TypeOfEmployment(String type) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input");
		if(!value.equals(type)) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input", type);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentType_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentType_DropDown']/div/ul//li[text()='" + type + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes name as input
	 * 
	 * @param name contact person name
	 */
	public void ContactPerson1Name(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1Name", name);
	}

	/**
	 * Takes person designation as input
	 * 
	 * @param designation contact person designation
	 */
	public void ContactPerson1Designation(String designation) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1Designation", designation);
	}

	/**
	 * Takes Relationship as input
	 * 
	 * @param type relation ship with employee
	 */
	public void Relationship(String type) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRelationship1_Input");
		if(!value.equals(type)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlRelationship1_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship1_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship1_DropDown']/div/ul//li[text()='" + type + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes name of relationship if relationship in other than dropdown value
	 * 
	 * @param name person one name
	 */
	public void ContactPerson1IfOthers(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1IfOthers", name);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 1 field
	 * 
	 * @param no number of contact person 1
	 */
	public void ContactPerson1ContactNo1(String no) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo1", no);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 2 field
	 * 
	 * @param no number of contact person 2
	 */
	public void ContactPerson1ContactNo2(String no) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo2", no);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson1EmailID1(String email) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID1", email);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson1EmailID2(String email) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID2", email);
	}

	/**
	 * Takes fax as input and pass it to fax no 1 field
	 * 
	 * @param fax email id of contact person 1
	 */
	public void ContactPerson1FaxNo1(String fax) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1FaxNo1", fax);
	}

	/**
	 * Takes name as input
	 * 
	 * @param name contact person name
	 */
	public void ContactPerson2Name(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson2Name", name);
	}

	/**
	 * Takes person designation as input
	 * 
	 * @param designation contact person designation
	 */
	public void ContactPerson2Designation(String designation) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson2Designation", designation);
	}

	/**
	 * Takes Relationship as input
	 * 
	 * @param type relation ship with employee
	 */
	public void Relationship2(String type) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRelationship2_Input");
		if(!value.equals(type)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlRelationship2_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship2_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship2_DropDown']/div/ul//li[text()='" + type + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes name of relationship if relationship in other than dropdown value
	 * 
	 * @param name person 2
	 */
	public void ContactPerson2IfOthers(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson2IfOthers", name);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 1 field
	 * 
	 * @param no number of contact person 1
	 */
	public void ContactPerson2ContactNo1(String no) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson2ContactNo1", no);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 2 field
	 * 
	 * @param no number of contact person 2
	 */
	public void ContactPerson2ContactNo2(String no) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo2", no);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson2EmailID1(String email) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson2EmailID1", email);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson2EmailID2(String email) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson2EmailID2", email);
	}

	/**
	 * Takes fax as input and pass it to fax no 1 field
	 * 
	 * @param fax email id of contact person 1
	 */
	public void ContactPerson2FaxNo1(String fax) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson2FaxNo1", fax);
	}

	/**
	 * Takes reason as input reason for job change
	 * 
	 * @param reason for job change
	 */
	public void ReasonForLeaving(String reason) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtReasonOfLeaving", reason);
	}

	/**
	 * takes yes or no as input it means terminated or not yes means terminated no
	 * means not
	 * 
	 * @param answer yes or no
	 */
	public void Everterminated(String answer) {
		if (answer.contains("yes")) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_RadioButtonListTerminatedOrLaidOff_0");
		} else if (answer.contains("no")) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_RadioButtonListTerminatedOrLaidOff_1");
		}
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes reason as input and pass it to reasons
	 * 
	 * @param reason for job
	 */
	public void Reasons(String reason) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtTerminatedOrLaidOffComments", reason);
	}

	/**
	 * additional comments
	 * 
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCommentsIfAny", comments);
	}
	/**
	 * click submit button on employment data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEmploymentSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();	
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEmploymentSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_btnEmploymentAddDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_grdviewEmploymentDocument_ctl00__0']/td[2]", 10);
		boolean re =false;
		String path="//*[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_grdviewEmploymentDocument_ctl00']/tbody/tr/td[2]";
		List<WebElement> list =driver.findElements(By.xpath(path));
		if(list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				String t=list.get(i).getText().trim();
				logger.log(Status.INFO, t);
				if(t.equals(doctype)) {
					re=true;
					break;
				}
			}
		}
		else {
			logger.log(Status.FAIL, "no element found");
		}
		return re;
	}
	/**
	 * Takes document type and file as input and uploads the document
	 * @param doctype type of document
	 * @param file file name
	 */
	public void UploadDocument(String doctype, String file) {
		if(this.isvaliddoctype(doctype)) {
		pages.Utill().sendKeys("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		super.WaitforFileUpdate(doctype, file);
		this.AddDocument();
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_btnEmploymentDocumentClose_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
	}

	public String EmployerName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentCompany_Input");
	}

	public String EmployerAddressLIne1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtOfficeDetails");
	}

	public String EmployerCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentCountry_Input");
	}

	public String EmployerState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmployementState_Input");
	}

	public String EmployerCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmployementCity_Input");
	}

	public String EmployerNameasperEmployers() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtnameEmpRec");
	}

	public String EmploymentEmployeeID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmployeeID");
	}

	public String EmploymentDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmployeeDesignation");
	}

	public String EmploymentDepartment() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmployeeDepartment");
	}

	public String EmploymentFromDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtFromDate_dateInput");
	}

	public String EmploymentToDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtToDate_dateInput");
	}

	public String EmploymentLastCTC() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtLastSalaryDrawn");
	}

	public String EmploymentCurrency() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input");
	}

	public String EmploymentPeriod() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input");
	}

	public String EmploymentTypeOfEmployment() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input");
	}

	public String PersonName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1Name");
	}

	public String PersonDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1Designation");
	}

	public String PersonRelationship() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRelationship1_Input");
	}

	public String PersonIfOther() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1IfOthers");
	}

	public String PersonContactNo1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo1");
	}

	public String PersonContactNo2() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo2");
	}

	public String PersonEmailID1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID1");
	}

	public String PersonEmailID2() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID2");
	}

	public String PersonFaxNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1FaxNo1");
	}

	public String ReasonForLeaving() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtReasonOfLeaving");
	}

	public String Reason() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtTerminatedOrLaidOffComments");
	}

	public String Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCommentsIfAny");
	}
	public void RespondentName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentRespondentName", name);
	}

	public String RespondentName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmploymentRespondentName");
	}

	public void RespondentDesignation(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentRespondentDesignation", relationship);
	}

	public String RespondentDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmploymentRespondentDesignation");
	}
	public void RespondentContactNo(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentRespondentContactNoEmailID", relationship);
	}

	public String RespondentContactNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmploymentRespondentContactNoEmailID");
	}
	
	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentVerifierRemarks", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmploymentVerifierRemarks");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmploymentDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmploymentDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}
	public void Verification() throws Exception {
		Properties pro = pages.Utill().veridata("employment");
		this.employementcheck();
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
		this.DateOfInitiation(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
	public Map<String, String> CurrentEmp() throws Exception{
		this.employementcheck();
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
		this.employementcheck();
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
		String date=pages.Utill().getCurrentDate("dd/MM/yyyy");
		String component=this.Component();
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().veridata("employment");
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
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEmploymentAddComments");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void CloseReportComments() {
		pages.Utill().click("ModalClose");
		pages.Utill().sleep(500);
	}
	public void UpdateReportComments() {
		int count=driver.findElements(By.xpath("//*[@id='accordion']/div")).size();
		
		for (int i = 1; i < count-1; i++) {
			if(i>1) {
				pages.Utill().click("//*[@id='accordion']/div["+Integer.toString(i)+"]//b");	
			}		
			String info=pages.Utill().getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[1]/span");
			pages.Utill().sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[2]//div[3]/div/p/..", info);
			String info2=pages.Utill().getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[3]/span");
			pages.Utill().sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[4]//div[3]/div/p/..", info2);
		}
	}
}
