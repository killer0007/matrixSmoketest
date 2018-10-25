package dataEntry;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Employment extends DataEntryPage {

	/**
	 * This is class for Employement page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Employment(ExtentTest logger) {
		super(logger);
	}

	/**
	 * Select Employment tab and switch to Employment frame
	 */
	public void employementcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[3]/a/span/span/span");
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li//span[text()='Employment']");
		pages.Utill().SwitchFramebyIndex(2);
	}

	/**
	 * Performs click action in Cep tab in Document screen
	 */
	public void Cep() {
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_RadTabStripForHistory']/div//li[3]");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * returns name who raised CEP
	 * 
	 * @return name employee name
	 */
	public String CepgetraisedBy() {
		return pages.Utill()
				.get_text(
						"ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaisedBy")
				.trim();
	}

	/**
	 * returns stage in which stage CEP raised
	 * 
	 * @return stage name
	 */
	public String CepgetraisedStage() {
		return pages.Utill().get_text(
				"ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaiseState")
				.trim();
	}

	/**
	 * returns comments which given when the time if CEP raise
	 * 
	 * @return CEP raised comments
	 */
	public String CepgetraisedComments() {
		return pages.Utill().get_text(
				"ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}

	/**
	 * return employee name who cleared the CEP
	 * 
	 * @return name employee name
	 */
	public String CepgetclearedBy() {
		return pages.Utill().get_text(
				"ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblClearedByName")
				.trim();
	}

	/**
	 * return CEP clear comments
	 * 
	 * @return comments CEP clear
	 */
	public String CepgetclearedComments() {
		return pages.Utill().get_text(
				"ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblClearComments")
				.trim();
	}

	/**
	 * returns document name which uploaded for CEP clear
	 * 
	 * @return document name
	 */
	public String CephistoryDocument() {
		String dc = pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblDoc");
		return dc.replaceAll("[0-9]", "");

	}

	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnEmploymentDocumentUpload_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
		if(!value.trim().equals(component)) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill().click_element(
						"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul//li[text()='"
								+ component + "']");
				pages.Utill().wait_until_loader_is_invisible(100);
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
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEmploymentCompany_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentCompany_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentCompany_DropDown']/div/ul//li[text()='" + name + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes Company address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void CompanyAddressLine1(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtOfficeDetails", address);
	}

	/**
	 * select tamil nadu as Company state
	 */
	public void CompanyState() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEmployementState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as Company city
	 */
	public void CompanyCity() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEmployementCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes candidate name as input
	 * 
	 * @param name of candidate as per employer record
	 */
	public void NameasperEmployersRecord(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtnameEmpRec", name);
	}

	/**
	 * Takes employeeid as input
	 * 
	 * @param id employee ID
	 */
	public void EmployeeID(String id) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmployeeID", id);
	}

	/**
	 * Takes Employee Designation as input
	 * 
	 * @param designation Employee Designation
	 */
	public void EmpDesignation(String designation) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmployeeDesignation", designation);
	}

	/**
	 * Takes Employee Department name as input
	 * 
	 * @param department Employee Department name
	 */
	public void EmpDepartment(String department) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmployeeDepartment", department);
	}

	/**
	 * Takes from date as input
	 * 
	 * @param date date of join
	 */
	public void EmpFromDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFromDate_dateInput", date);
	}

	/**
	 * 
	 * @param date date of releaving
	 */
	public void EmpToDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtToDate_dateInput", date);
	}

	/**
	 * set Till date as to date in employement date
	 */
	public void TillDate() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkTillDate");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes last CTC as input
	 * 
	 * @param ctc last CTC
	 */
	public void LastCTC(String ctc) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLastSalaryDrawn", ctc);
	}

	/**
	 * Takes currency type INR
	 * 
	 * @param currency type of currency
	 */
	public void Currency(String currency) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input");
		if(!value.equals(currency)) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input");
		WebDriverWait wait =new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCurrencyType_DropDown']/div/ul/li[1]")));
			pages.Utill().click_element(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCurrencyType_DropDown']/div/ul//li[text()='" + currency + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
	}

	/**
	 * Takes string as input it means period for CTC like Per week, annum
	 * 
	 * @param period limit
	 */
	public void Period(String period) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input");
		if(!value.equals(period)) {
			
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_DropDown']/div/ul/li[1]")));
			pages.Utill()
			.click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_DropDown']/div/ul//li[text()='"
					+ period + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
	}

	/**
	 * Takes type of employment as input permanent or contract
	 * 
	 * @param type of employment
	 */
	public void TypeOfEmployment(String type) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input");
		if(!value.equals(type)) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input", type);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentType_DropDown']/div/ul/li[1]")));
			pages.Utill().click_element(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentType_DropDown']/div/ul//li[text()='" + type + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
	}

	/**
	 * Takes name as input
	 * 
	 * @param name contact person name
	 */
	public void ContactPerson1Name(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1Name", name);
	}

	/**
	 * Takes person designation as input
	 * 
	 * @param designation contact person designation
	 */
	public void ContactPerson1Designation(String designation) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1Designation", designation);
	}

	/**
	 * Takes Relationship as input
	 * 
	 * @param type relation ship with employee
	 */
	public void Relationship(String type) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlRelationship1_Input");
		if(!value.equals(type)) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlRelationship1_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship1_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship1_DropDown']/div/ul//li[text()='" + type + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
	}

	/**
	 * Takes name of relationship if relationship in other than dropdown value
	 * 
	 * @param name person one name
	 */
	public void ContactPerson1IfOthers(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1IfOthers", name);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 1 field
	 * 
	 * @param no number of contact person 1
	 */
	public void ContactPerson1ContactNo1(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo1", no);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 2 field
	 * 
	 * @param no number of contact person 2
	 */
	public void ContactPerson1ContactNo2(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo2", no);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson1EmailID1(String email) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID1", email);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson1EmailID2(String email) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID2", email);
	}

	/**
	 * Takes fax as input and pass it to fax no 1 field
	 * 
	 * @param fax email id of contact person 1
	 */
	public void ContactPerson1FaxNo1(String fax) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1FaxNo1", fax);
	}

	/**
	 * Takes name as input
	 * 
	 * @param name contact person name
	 */
	public void ContactPerson2Name(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson2Name", name);
	}

	/**
	 * Takes person designation as input
	 * 
	 * @param designation contact person designation
	 */
	public void ContactPerson2Designation(String designation) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson2Designation", designation);
	}

	/**
	 * Takes Relationship as input
	 * 
	 * @param type relation ship with employee
	 */
	public void Relationship2(String type) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlRelationship2_Input");
		if(!value.equals(type)) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlRelationship2_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship2_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship2_DropDown']/div/ul//li[text()='" + type + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
	}

	/**
	 * Takes name of relationship if relationship in other than dropdown value
	 * 
	 * @param name person 2
	 */
	public void ContactPerson2IfOthers(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson2IfOthers", name);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 1 field
	 * 
	 * @param no number of contact person 1
	 */
	public void ContactPerson2ContactNo1(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson2ContactNo1", no);
	}

	/**
	 * Takes mobile number as input and pass it to contact no 2 field
	 * 
	 * @param no number of contact person 2
	 */
	public void ContactPerson2ContactNo2(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo2", no);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson2EmailID1(String email) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson2EmailID1", email);
	}

	/**
	 * Takes email as input and pass it to email id 1 field
	 * 
	 * @param email email id of contact person 1
	 */
	public void ContactPerson2EmailID2(String email) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson2EmailID2", email);
	}

	/**
	 * Takes fax as input and pass it to fax no 1 field
	 * 
	 * @param fax email id of contact person 1
	 */
	public void ContactPerson2FaxNo1(String fax) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson2FaxNo1", fax);
	}

	/**
	 * Takes reason as input reason for job change
	 * 
	 * @param reason for job change
	 */
	public void ReasonForLeaving(String reason) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtReasonOfLeaving", reason);
	}

	/**
	 * takes yes or no as input it means terminated or not yes means terminated no
	 * means not
	 * 
	 * @param answer yes or no
	 */
	public void Everterminated(String answer) {
		if (answer.contains("yes")) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_RadioButtonListTerminatedOrLaidOff_0");
		} else if (answer.contains("no")) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_RadioButtonListTerminatedOrLaidOff_1");
		}
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes reason as input and pass it to reasons
	 * 
	 * @param reason for job
	 */
	public void Reasons(String reason) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtTerminatedOrLaidOffComments", reason);
	}

	/**
	 * additional comments
	 * 
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCommentsIfAny", comments);
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkEmploymentInsuff");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmploymentInsuffRemarks", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
	}

	/**
	 * click cep raise check box
	 */
	public void cep() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkCanVerifyNow");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * takes date as input and pass it to cep relieving date date
	 * 
	 * @param date date of relieve
	 */
	public void cepRelievingDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRelievingDate_dateInput", date);

	}

	/**
	 * Takes comments as input and pass it to cep raise comments
	 * 
	 * @param comments ecp raise comments
	 */
	public void CepComments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmploymentCEPRemarks", comments);
	}

	/**
	 * click submit button on employment data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		int count=driver.findElements(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul/li")).size();
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnEmploymentSubmit_input");
		pages.Utill().wait_until_loader_is_invisible(100);
		//System.out.println("-----------count--------"+count);
		if(count==2) {
			pages.Utill().SwitchDefault();	
		}
		pages.Utill().confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnEmploymentSave_input");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_btnEmploymentAddDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		pages.Utill().wait_element_has_text("//*[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_grdviewEmploymentDocument_ctl00__0']/td[2]", 10);
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
		pages.Utill().input_text("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		super.WaitforFileUpdate(doctype, file);
		this.AddDocument();
		pages.Utill().wait_until_loader_is_invisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_btnEmploymentDocumentClose_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes input from employment.properties file and pass it to employment data entry of current employment
	 * @throws Exception webdriver exception
	 */
public void currentEmployment() throws Exception{
	Properties pro = pages.Utill().dedata("employment");
	this.employementcheck();
	this.Component("Current/Latest Employment");
	this.CompanyName(pro.getProperty("cEmployerName"));
	this.NameasperEmployersRecord(pro.getProperty("cEmployerNameasperEmployers"));
	this.EmployeeID(pro.getProperty("cEmploymentEmployeeID"));
	this.EmpDesignation(pro.getProperty("cEmploymentDesignation"));
	this.EmpDepartment(pro.getProperty("cEmploymentDepartment"));
	this.EmpFromDate(pro.getProperty("cEmploymentFromDate"));
	this.EmpToDate(pro.getProperty("cEmploymentToDate"));
	this.LastCTC(pro.getProperty("cEmploymentLastCTC"));
	this.Currency(pro.getProperty("cEmploymentCurrency"));
	this.Period(pro.getProperty("cEmploymentPeriod"));
	this.TypeOfEmployment(pro.getProperty("cEmploymentTypeOfEmployment"));
	this.ContactPerson1Name(pro.getProperty("cPersonName"));
	this.ContactPerson1Designation(pro.getProperty("cPersonDesignation"));
	this.Relationship(pro.getProperty("cPersonRelationship"));
	this.ContactPerson1ContactNo1(pro.getProperty("cPersonContactNo1"));
	this.ContactPerson1ContactNo2(pro.getProperty("cPersonContactNo2"));
	this.ContactPerson1EmailID1(pro.getProperty("cPersonEmailID1"));
	this.ContactPerson1EmailID2(pro.getProperty("cPersonEmailID2"));
	this.ContactPerson1FaxNo1(pro.getProperty("cPersonFaxNo"));
	this.ReasonForLeaving(pro.getProperty("cReasonForLeaving"));
	this.document();
	this.UploadDocument("Offer Letter", pro.getProperty("currentemp"));
	this.docclose();
	this.Comments(pro.getProperty("cComments"));
//	this.save();
	this.submit();
	
}
/**
 * Takes input from employment.properties file and pass it to employment data entry of previous employment
 * @throws Exception webdriver exception
 */
public void perviousoneEmployment() throws Exception{
	Properties pro = pages.Utill().dedata("employment");
	this.employementcheck();
	this.Component("Previous Employment");
	this.CompanyName(pro.getProperty("pEmployerName"));
	this.NameasperEmployersRecord(pro.getProperty("pEmployerNameasperEmployers"));
	this.EmployeeID(pro.getProperty("pEmploymentEmployeeID"));
	this.EmpDesignation(pro.getProperty("pEmploymentDesignation"));
	this.EmpDepartment(pro.getProperty("pEmploymentDepartment"));
	this.EmpFromDate(pro.getProperty("pEmploymentFromDate"));
	this.EmpToDate(pro.getProperty("pEmploymentToDate"));
	this.LastCTC(pro.getProperty("pEmploymentLastCTC"));
	this.Currency(pro.getProperty("pEmploymentCurrency"));
	this.Period(pro.getProperty("pEmploymentPeriod"));
	this.TypeOfEmployment(pro.getProperty("pEmploymentTypeOfEmployment"));
	this.ContactPerson1Name(pro.getProperty("pPersonName"));
	this.ContactPerson1Designation(pro.getProperty("pPersonDesignation"));
	this.Relationship(pro.getProperty("pPersonRelationship"));
	this.ContactPerson1ContactNo1(pro.getProperty("pPersonContactNo1"));
	this.ContactPerson1ContactNo2(pro.getProperty("pPersonContactNo2"));
	this.ContactPerson1EmailID1(pro.getProperty("pPersonEmailID1"));
	this.ContactPerson1EmailID2(pro.getProperty("pPersonEmailID2"));
	this.ContactPerson1FaxNo1(pro.getProperty("pPersonFaxNo"));
	this.ReasonForLeaving(pro.getProperty("pReasonForLeaving"));
	this.document();
	this.UploadDocument("Relieving Letter", pro.getProperty("preemp"));
	this.docclose();
	this.Comments(pro.getProperty("pComments"));
//	this.save();
	this.submit();
	
}

}
