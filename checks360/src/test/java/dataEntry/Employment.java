package dataEntry;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

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
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[3]/a/span/span/span");
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
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill().click_element(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul/li//text()='"
							+ component + "'");
		} else {
			throw new NotFoundException(component);
		}
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) {

		List<WebElement> list = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
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
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentCompany_DropDown']/div/ul/li//text()='" + name + "'");
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
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementState_DropDown']/div/ul/li//text()='Tamil Nadu'");
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
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmployementCity_DropDown']/div/ul/li//text()='Chennai'");
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
		pages.Utill().input_text("ctl00$ContentPlaceHolder1$txtFromDate$dateInput", date);
	}

	/**
	 * Takes to date as input
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
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input", currency);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCurrencyType_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCurrencyType_DropDown']/div/ul/li//text()='" + currency + "'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes string as input it means period for CTC like Per week, annum
	 * 
	 * @param period limit
	 */
	public void Period(String period) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input", period);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_DropDown']/div/ul/li[1]")));
		pages.Utill()
				.click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_DropDown']/div/ul/li//text()='"
						+ period + "'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes type of employment as input permanent or contract
	 * 
	 * @param type of employment
	 */
	public void TypeOfEmployment(String type) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input", type);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentType_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentType_DropDown']/div/ul/li//text()='" + type + "'");
		pages.Utill().wait_until_loader_is_invisible(100);
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
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlRelationship1_Input", type);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship1_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship1_DropDown']/div/ul/li//text()='" + type + "'");
		pages.Utill().wait_until_loader_is_invisible(100);
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
	public void ontactPerson1EmailID1(String email) {
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
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlRelationship2_Input", type);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship2_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRelationship2_DropDown']/div/ul/li//text()='" + type + "'");
		pages.Utill().wait_until_loader_is_invisible(100);
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
	public void ontactPerson2EmailID1(String email) {
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
}
