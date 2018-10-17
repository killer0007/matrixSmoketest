package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import environment.BaseClass;
import environment.Pages;

public class CaseRegistration {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Service Provider case registration
	 * 
	 * @param logger logger instance
	 */
	public CaseRegistration(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * Select case registration from the Stages dropwdown
	 */
	public void casereg() {
		pages.Utill().select_by_value("ddlAct", "0");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	/**
	 * return of title of page
	 * 
	 * @return oage title
	 */
	public String getTitle() {
		return pages.Utill().get_text(".//*[@id='ctl00_ContentPlaceHolder1_divAddVal']/h2/table/tbody/tr/td[1]");
	}

	/**
	 * Takes Client name as input and selects given name from Client dropdown
	 * 
	 * @param name Client name
	 * @throws InterruptedException Time out interruption
	 */
	public void selectClient(String name) throws InterruptedException {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlClient_Input");

		try {
			pages.Utill().click_element("//li[text()='" + name + "']");
		} catch (ElementNotVisibleException e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			Thread.sleep(2000);
			pages.Utill().click_element("//li[text()='" + name + "']");
		} catch (ElementNotInteractableException e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			Thread.sleep(2000);
			pages.Utill().click_element("//li[text()='" + name + "']");
		}
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Takes Project name as input and selects given name from project dropdown
	 * 
	 * @param name Project name
	 * @throws InterruptedException Time out interruption
	 */
	public void selectProject(String name) throws InterruptedException {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlProject_Input");
		Thread.sleep(1000);
		try {
			pages.Utill().click_element("//li[text()='" + name + "']");
		} catch (ElementNotVisibleException e) {
			Thread.sleep(1000);
			pages.Utill().click_element("//li[text()='" + name + "']");
		}
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Takes candidate First name as input and pass it to first name field
	 * 
	 * @param fname candidate first name
	 */
	public void FirstName(String fname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFirstName", fname);
	}

	/**
	 * Takes candidate last name as input and pass it to last name field
	 * 
	 * @param lname candidate Last name
	 */
	public void LastName(String lname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLastName", lname);
	}

	/**
	 * Takes candidate date of birth as input and pass it to dob field
	 * 
	 * @param dob candidate date of birth
	 */
	public void DOB(String dob) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtDateofBirth_dateInput", dob);
	}

	/**
	 * Takes candidates Father First name as input and pass it to Father first name
	 * field
	 * 
	 * @param fname candidates Father first name
	 */
	public void FatherFirstName(String fname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFatherFirstName", fname);
	}

	/*
	 * * Takes candidates Father last name as input and pass it to Father Last name
	 * field
	 * 
	 * @param lname candidates Father last name
	 */
	public void FatherLastName(String lname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFatherLastName", lname);
	}

	/**
	 * Takes candidate email id as input and pass it to Email field
	 * 
	 * @param email Candidate Email ID
	 */
	public void Email(String email) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmail", email);
	}

	/**
	 * Takes candidates LinkedIn ID as input and pass it to LinkedIn ID field
	 * 
	 * @param linkedin LinkedIn ID
	 */
	public void LinkedIn(String linkedin) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLinkedIn", linkedin);
	}

	/**
	 * Takes candidates Nationality as input and pass it to Nationality field
	 * 
	 * @param nation Nationality
	 */
	public void Nationality(String nation) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtNationality", nation);
	}

	/**
	 * Takes candidates Landline Number as input and pass it to Landline Number
	 * field
	 * 
	 * @param no Landline Number
	 */
	public void LandlineNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLandLine", no);
	}

	/**
	 * Takes candidates Mobile Number as input and pass it to Mobile Number field
	 * 
	 * @param no Mobile Number
	 */
	public void MobileNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtMobileNumberFirst", no);
	}

	/**
	 * Takes candidates Emergency Contact Number as input and pass it to Emergency
	 * Contact Number field
	 * 
	 * @param no Emergency Contact Number
	 */
	public void EmergencyContactNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtMobileNumberSecond", no);
	}

	/**
	 * Takes candidates Emergency Contact Person as input and pass it to Emergency
	 * Contact Person field
	 * 
	 * @param name Emergency Contact Person
	 */
	public void EmergencyContactPerson(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson", name);
	}

	/**
	 * Takes candidate id as input and pass it to Candidate ID field
	 * 
	 * @param id Candidate ID
	 */
	public void CandidateID(String id) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
	}

	/**
	 * Takes id and attribute as input and return the color of field
	 * 
	 * @param id        locator value
	 * @param attribute name of attribute in that locator
	 * @return color value of color code
	 */
	public String getalertcolor(String id, String attribute) {
		String color = pages.Utill().getcssvalue(id, attribute);
		logger.log(Status.PASS, color);
		return color;
	}

	/**
	 * Performs click action on Add Edit Components button
	 */
	public void addEditComponent() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddComponent_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Takes boolean value as input true means fresher is yes false means fresher is
	 * no
	 * 
	 * @param value whether fresher or not
	 */
	public void clickfresher(boolean value) {
		if (value) {
			pages.Utill().executescript(
					"document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_0').checked='checked'");

		} else {
			pages.Utill().executescript(
					"document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_1').checked='checked'");
		}
	}

	/**
	 * Return case owner name
	 * 
	 * @return name of case owner
	 */
	public String getCaseOwner() {
		return pages.Utill().get_text("ctl00_ContentPlaceHolder1_lblCaseOwnerName");
	}

	/**
	 * Select candidate id from dropdown
	 */
	public void candidateid() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']//text()='Candidate ID'");
	}

	/**
	 * Select Employee id from dropdown
	 */
	public void empid() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']//text()='Employee ID'");
	}

	/**
	 * Perform click action on Add New Case button
	 */
	public void addNewCase() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddNext_input");
	}

	/**
	 * Takes component name as input and select the check box which related to
	 * component name
	 * 
	 * @param componentname name of sub component
	 */
	public void selectcheck(String componentname) {
		pages.Utill().click_element("//td[text()='" + componentname + "']/../td[2]//input");
	}

	/**
	 * Takes component name and remarks as input select the not applicable check box
	 * and enter the remarks on remarks field
	 * 
	 * @param componentname name of sub component
	 * @param remarks       not applicable remarks
	 */
	public void notApplicable(String componentname, String remarks) {
		pages.Utill().click_element("//td[text()='" + componentname + "']/../td[6]//input");
		pages.Utill().input_text("//td[text()='" + componentname + "']/../td[6]//textarea", remarks);
	}

	/**
	 * Takes component name and comments as input select the insuff check box and
	 * enter the comments in insuff comments field
	 * 
	 * @param componentname name of sub component
	 * @param comments      insuff raise comments
	 */
	public void raiseInsuff(String componentname, String comments) {
		pages.Utill().click_element("//td[text()='" + componentname + "']/../td[7]//input");
		pages.Utill().input_text("//td[text()='" + componentname + "']/../td[7]//textarea", comments);
	}

	/**
	 * Takes component name, comments and cep relaease date as input select the CEP
	 * check box and enter the comments in CEP comments field
	 * 
	 * @param componentname name of sub component
	 * @param comments      CEP raise comments
	 * @param releasedate   CEP release date
	 */
	public void cep(String componentname, String comments, String releasedate) {
		pages.Utill().click_element("//td[text()='" + componentname + "']/../td[8]//input");
		pages.Utill().input_text("//td[text()='" + componentname + "']/../td[8]//textarea", comments);
		pages.Utill().input_text(
				"//td[text()='" + componentname + "']/../td[8]//tbody/tr/td/div/table/tbody/tr/td/span/input[1]",
				releasedate);
	}

	/**
	 * Takes Component name, File path and document type as input Performs click
	 * action in upload button Performs document upload
	 * 
	 * @param componentName Name of sub component
	 * @param file          file name with filepath
	 * @param doctype       Type of document
	 */
	public void documentupload(String componentName, String file, String doctype) {
		this.clickupload(componentName);
		this.upload(file, doctype);
		this.addDocument();
		this.docupClose();

	}

	/**
	 * Takes component name and document type as input Perform click action on
	 * upload button Get uploaded document name and close the popup
	 * 
	 * @param componentName sub component name
	 * @param doctype       type of document
	 * @return file name name document to been uploaded
	 */

	public String getDocumentName(String componentName, String doctype) {
		this.clickupload(componentName);
		String docname = pages.Utill()
				.get_text("//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='"
						+ doctype + "']/../td[6]//span");
		this.docupClose();
		return docname.replaceAll("[0-9]", "");

	}

	/**
	 * Takes document type and file path as input Click on Case Documents button
	 * upload given file to document type click add document button close the popup
	 * 
	 * @param doctype  Type of document
	 * @param fileName File path with file name
	 */
	public void uploadcaseDoc(String doctype, String fileName) {
		this.uploadcaseDoc();
		pages.Utill().wait_until_loader_is_invisible(100);
		if (this.isDoctypeValid(doctype, 1)) {
			pages.Utill().input_text(
					"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='"
							+ doctype + "']/../td[5]//div/ul/li/span/input[2]",
					fileName);
		} else
			throw new NotFoundException(doctype);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnAddCaseDocument_input");
		pages.Utill().wait_until_loader_is_invisible(50);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");

	}

	/**
	 * Takes document type as input and delete the uploaded document
	 * 
	 * @param doctype Type of Document to been uploaded
	 * @throws Exception file not found exception
	 */
	public void deleteComponentdoc(String doctype) throws Exception {
		if (this.isDoctypeValid(doctype, 0)) {
			String path = "//*[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='"
					+ doctype + "']/../td[7]//td[2]/input";
			pages.Utill().click_element(path);
		} else
			throw new NotFoundException(doctype);
		pages.Utill().confirmAlert();
		pages.Utill().wait_until_loader_is_invisible(50);

	}

	/**
	 * Takes document type as input and delete the uploaded document
	 * 
	 * @param doctype Type of Document to been uploaded
	 * @throws Exception file not found exception
	 */
	public void deleteCasedoc(String doctype) throws Exception {
		if (this.isDoctypeValid(doctype, 1)) {
			String path = "//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='"
					+ doctype + "']/../td[6]//td[2]/input";
			pages.Utill().click_element(path);
		} else
			throw new NotFoundException(doctype);
		pages.Utill().confirmAlert();
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	/**
	 * Takes document type as input and return file name of uploaded case document
	 * 
	 * @param doctype Type of document
	 * @return filename name of uploaded document
	 */
	public String getuploadcaseDoc(String doctype) {
		this.uploadcaseDoc();
		pages.Utill().wait_until_loader_is_invisible(100);
		//
		String docname = null;
		if (this.isDoctypeValid(doctype, 1)) {
			docname = pages.Utill().get_text(
					"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='"
							+ doctype + "']/../td[5]//span");
		} else
			throw new NotFoundException(doctype);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");
		return docname.replaceAll("[0-9]", "");
	}

	/**
	 * Takes file path with file name as input and
	 * 
	 * @param file    File path to be upload
	 * @param doctype Type of document
	 * @throws NotFoundException when given document type not found
	 */
	public void upload(String file, String doctype) {
//		pages.Utill().input_text("//td[text()='" + doctype + "']/following-sibling::td[3]//@value='Select'", file);
		if (this.isDoctypeValid(doctype, 0)) {
			pages.Utill().input_text(
					"//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='" + doctype
							+ "']/../td[6]//input[2]",
					file);
		} else
			throw new NotFoundException(doctype);
	}

	/**
	 * Takes component name as input and click on upload button
	 * 
	 * @param componentName sub component name
	 */
	public void clickupload(String componentName) {
		pages.Utill().click_element("//td[text()='" + componentName + "']/../td[10]//input[1]");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Perform click action on Add Document button
	 */
	public void addDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnAddDocument_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Performs click action on Upload Documents
	 */
	public void uploadcaseDoc() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCaseDocument_input");
	}

	/**
	 * Performs click action on cancel icon in upload popup
	 */
	public void docupCancel() {
		pages.Utill().click_element("//a[@class='rwCloseButton']");

	}

	/**
	 * Performs click action on cancel icon in upload popup
	 */
	public void docupClose() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnDocumentCancel");

	}

	/**
	 * Performs click action on save button
	 */
	public void save() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSave_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Performs click action on submit button
	 */
	public void submit() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSaveSubmit_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Performs click action on cancel button
	 */
	public void cancel() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCancel");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	/**
	 * Takes Gender as input it should be either male or female select the gender
	 * from dropwdown based on input
	 * 
	 * @param gender male or female
	 * @throws Exception except male and female any input given it throws
	 */
	public void gender(String gender) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlGender_Input");
		Thread.sleep(1000);
		if (gender.equalsIgnoreCase("male")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Male'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[2]");
		} else if (gender.equalsIgnoreCase("female")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Female'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[3]");
		} else {
			throw new NotFoundException(gender);
		}
	}

	/**
	 * Takes Marital Status as input it should be either Single or married or
	 * divorced select the Marital Status from dropwdown based on input
	 * 
	 * @param status Single or married or divorced
	 * @throws Exception except Single , married and divorced any input given it
	 *                   throws
	 */
	public void maritalStatus(String status) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
		Thread.sleep(2000);
		if (status.equalsIgnoreCase("Single")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Single'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[2]");
		} else if (status.equalsIgnoreCase("married")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Married'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[3]");
		} else if (status.equalsIgnoreCase("divorced")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Divorced'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[4]");
		}

		else {
			throw new NotFoundException(status);
		}
	}

	/**
	 * Returns all document type displayed in upload popup as list
	 * 
	 * @return list document types
	 */
	public List<String> getDisplayedComponents() {
		List<String> data = new ArrayList<String>();
		List<WebElement> loc = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_grdComponentDetails_ctl00']/tbody/tr/td[3]"));
		for (int i = 0; i < loc.size(); i++) {
			String name = loc.get(i).getText().toString().trim();
			if (name.length() != 0) {
				data.add(name);
			} else {
				continue;
			}
		}
		return data;
	}

	/**
	 * Takes case registration details as input with hashmap format
	 * 
	 * @param data    hash map contains registration details
	 * @param fresher true for fresher yes
	 * @throws Exception element not found
	 */
	public void registercase(HashMap<String, String> data, boolean fresher) throws Exception {
		CaseRegistration casereg = pages.CaseRegistration();
		casereg.selectClient(data.get("ClientName"));
		casereg.selectProject(data.get("ProjectName"));
		casereg.FirstName(data.get("CandidateName"));
		casereg.LastName(data.get("lastname"));
		casereg.DOB(pages.Utill().getdob());
		casereg.gender("male");
		casereg.Email(data.get("CandidateName") + "@ggmail.com");
		casereg.FatherFirstName("fname");
		casereg.FatherLastName("lname");
		casereg.LinkedIn(data.get("CandidateName") + " linkedin");
		casereg.maritalStatus("Single");
		casereg.Nationality("Indian");
		casereg.LandlineNumber(pages.Utill().mobileno());
		casereg.MobileNumber(pages.Utill().mobileno());
		casereg.EmergencyContactNumber(pages.Utill().mobileno());
		casereg.EmergencyContactPerson(data.get("CandidateName"));
		casereg.CandidateID(data.get("CandidateId"));
		casereg.clickfresher(fresher);
		casereg.addEditComponent();
	}

	/**
	 * Takes case registration details as input with hashmap format
	 * 
	 * @param data hash map contains registration details
	 * @throws Exception element not found
	 */
	public void registercase(HashMap<String, String> data) throws Exception {
		CaseRegistration casereg = pages.CaseRegistration();
		casereg.selectClient(data.get("ClientName"));
		casereg.selectProject(data.get("ProjectName"));
		casereg.FirstName(data.get("CandidateName"));
		casereg.LastName(data.get("lastname"));
		casereg.DOB(data.get("DateofBirth"));
		casereg.gender("male");
		casereg.Email(data.get("CandidateName") + "@ggmail.com");
		casereg.FatherFirstName("fname");
		casereg.FatherLastName("lname");
		casereg.LinkedIn(data.get("CandidateName") + " linkedin");
		casereg.maritalStatus("Single");
		casereg.Nationality("Indian");
		casereg.LandlineNumber(pages.Utill().mobileno());
		casereg.MobileNumber(pages.Utill().mobileno());
		casereg.EmergencyContactNumber(pages.Utill().mobileno());
		casereg.EmergencyContactPerson(data.get("CandidateName"));
		casereg.CandidateID(data.get("CandidateId"));
		casereg.clickfresher(false);
		casereg.addEditComponent();
	}

	/**
	 * Returns the value of Components Count
	 * 
	 * @return count Components Count
	 */
	public int getCheckCount() {
		return Integer.parseInt(pages.Utill().get_text("ctl00_ContentPlaceHolder1_lblComponentCount"));
	}

	/**
	 * Takes component name as input and returns true when given component has
	 * selected
	 * 
	 * @param componentname name of sub component
	 * @return boolean true = component selected , false = not selected
	 */
	public boolean isSelected(String componentname) {
		return pages.Utill().isSelected("//td[text()='" + componentname + "']/../td[2]//input");
	}

	/**
	 * Takes component name as input and returns true when given component has
	 * enabled
	 * 
	 * @param componentname name of sub component
	 * @return boolean true = component enabled , false = not enabled
	 */
	public boolean isEnabled(String componentname) {
		return pages.Utill().isEnabled("//td[text()='" + componentname + "']/../td[2]//input");
	}

	/**
	 * Takes Document Type and integer as input checks the given document type
	 * available in upload popup
	 * 
	 * @param doctype Type of Document in upload popup
	 * @param b       1 means case docuemnts, 0 means componens documents
	 * @return boolean true means document type valid, false means invalid document
	 *         type
	 */
	public boolean isDoctypeValid(String doctype, int b) {
		String path = "";
		if (b == 1) {
			path = "//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']/tbody/tr/td[2]";
		} else {
			path = "//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']/tbody/tr/td[3]";
		}

		List<WebElement> list = driver.findElements(By.xpath(path));
//		System.out.println("length is  : "+list.size());
		if (list.size() > 0) {
			List<String> doc = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				doc.add(list.get(i).getText().trim());
//				System.out.println(list.get(i).getText().trim());
			}
			if (doc.contains(doctype)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
