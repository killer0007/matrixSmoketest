package pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import environment.Utill;

public class CaseRegistration extends Utill {
	
	/**
	 * This is class for Service Provider case registration
	 * 
	 * @param logger logger instance
	 */
	public CaseRegistration(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select case registration from the Stages dropwdown
	 */
	public void casereg() {
		selectByValue("ddlAct", "0");
		waitUntilLoaderisInvisible(50);
	}

	/**
	 * return of title of page
	 * 
	 * @return page title
	 */
	public String getTitle() {
		return getText(".//*[@id='ctl00_ContentPlaceHolder1_divAddVal']/h2/table/tbody/tr/td[1]");
	}

	/**
	 * Takes Client name as input and selects given name from Client dropdown
	 * 
	 * @param name Client name
	 * @throws InterruptedException Time out interruption
	 */
	public void selectClient(String name) throws InterruptedException {
		click("ctl00_ContentPlaceHolder1_ddlClient_Input");
		try {
			click("//li[text()='" + name + "']");
		} catch (ElementNotVisibleException e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			Thread.sleep(2000);
			click("//li[text()='" + name + "']");
		} catch (ElementNotInteractableException e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			Thread.sleep(2000);
			click("//li[text()='" + name + "']");
		}
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Takes Project name as input and selects given name from project dropdown
	 * 
	 * @param name Project name
	 * @throws InterruptedException Time out interruption
	 */
	public void selectProject(String name) throws InterruptedException {
		click("ctl00_ContentPlaceHolder1_ddlProject_Input");
		Thread.sleep(1000);
		try {
			click("//li[text()='" + name + "']");
		} catch (ElementNotVisibleException e) {
			Thread.sleep(1000);
			click("//li[text()='" + name + "']");
		}
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Takes candidate First name as input and pass it to first name field
	 * 
	 * @param fname candidate first name
	 */
	public void FirstName(String fname) {
		sendKeys("ctl00_ContentPlaceHolder1_txtFirstName", fname);
	}

	/**
	 * Takes candidate last name as input and pass it to last name field
	 * 
	 * @param lname candidate Last name
	 */
	public void LastName(String lname) {
		sendKeys("ctl00_ContentPlaceHolder1_txtLastName", lname);
	}

	/**
	 * Takes candidate date of birth as input and pass it to dob field
	 * 
	 * @param dob candidate date of birth
	 */
	public void DOB(String dob) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDateofBirth_dateInput", dob);
	}

	/**
	 * Takes candidates Father First name as input and pass it to Father first name
	 * field
	 * 
	 * @param fname candidates Father first name
	 */
	public void FatherFirstName(String fname) {
		sendKeys("ctl00_ContentPlaceHolder1_txtFatherFirstName", fname);
	}

	/*
	 * * Takes candidates Father last name as input and pass it to Father Last name
	 * field
	 * 
	 * @param lname candidates Father last name
	 */
	public void FatherLastName(String lname) {
		sendKeys("ctl00_ContentPlaceHolder1_txtFatherLastName", lname);
	}

	/**
	 * Takes candidate email id as input and pass it to Email field
	 * 
	 * @param email Candidate Email ID
	 */
	public void Email(String email) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEmail", email);
	}

	/**
	 * Takes candidates LinkedIn ID as input and pass it to LinkedIn ID field
	 * 
	 * @param linkedin LinkedIn ID
	 */
	public void LinkedIn(String linkedin) {
		sendKeys("ctl00_ContentPlaceHolder1_txtLinkedIn", linkedin);
	}

	/**
	 * Takes candidates Nationality as input and pass it to Nationality field
	 * 
	 * @param nation Nationality
	 */
	public void Nationality(String nation) {
		sendKeys("ctl00_ContentPlaceHolder1_txtNationality", nation);
	}

	/**
	 * Takes candidates Landline Number as input and pass it to Landline Number
	 * field
	 * 
	 * @param no Landline Number
	 */
	public void LandlineNumber(String no) {
		sendKeys("ctl00_ContentPlaceHolder1_txtLandLine", no);
	}

	/**
	 * Takes candidates Mobile Number as input and pass it to Mobile Number field
	 * 
	 * @param no Mobile Number
	 */
	public void MobileNumber(String no) {
		sendKeys("ctl00_ContentPlaceHolder1_txtMobileNumberFirst", no);
	}

	/**
	 * Takes candidates Emergency Contact Number as input and pass it to Emergency
	 * Contact Number field
	 * 
	 * @param no Emergency Contact Number
	 */
	public void EmergencyContactNumber(String no) {
		sendKeys("ctl00_ContentPlaceHolder1_txtMobileNumberSecond", no);
	}

	/**
	 * Takes candidates Emergency Contact Person as input and pass it to Emergency
	 * Contact Person field
	 * 
	 * @param name Emergency Contact Person
	 */
	public void EmergencyContactPerson(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson", name);
	}

	/**
	 * Takes candidate id as input and pass it to Candidate ID field
	 * 
	 * @param id Candidate ID
	 */
	public void CandidateID(String id) {
		sendKeys("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
	}

	/**
	 * Takes id and attribute as input and return the color of field
	 * 
	 * @param id        locator value
	 * @param attribute name of attribute in that locator
	 * @return color value of color code
	 */
	public String getalertcolor(String id, String attribute) {
		String color = getCssValue(id, attribute);
		logger.log(Status.PASS, color);
		return color;
	}

	/**
	 * Performs click action on Add Edit Components button
	 */
	public void addEditComponent() {
		click("ctl00_ContentPlaceHolder1_btnAddComponent_input");
		waitUntilLoaderisInvisible(10);
		sleep(500);
	}

	/**
	 * Takes boolean value as input true means fresher is yes false means fresher is
	 * no
	 * 
	 * @param value whether fresher or not
	 */
	public void clickfresher(boolean value) {
		if (value) {
			executeScript(
					"document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_0').checked='checked'");

		} else {
			executeScript(
					"document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_1').checked='checked'");
		}
	}

	/**
	 * Return case owner name
	 * 
	 * @return name of case owner
	 */
	public String getCaseOwner() {
		return getText("ctl00_ContentPlaceHolder1_lblCaseOwnerName");
	}

	/**
	 * Select candidate id from dropdown
	 */
	public void candidateid() {
		click("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']//text()='Candidate ID'");
	}

	/**
	 * Select Employee id from dropdown
	 */
	public void empid() {
		click("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']//text()='Employee ID'");
	}

	/**
	 * Perform click action on Add New Case button
	 */
	public void addNewCase() {
		click("ctl00_ContentPlaceHolder1_btnAddNext_input");
	}

	/**
	 * Takes component name as input and select the check box which related to
	 * component name
	 * 
	 * @param componentname name of sub component
	 */
	public void selectcheck(String componentname) {
		click("//td[text()='" + componentname + "']/../td[2]//input");
	}

	/**
	 * Takes component name and remarks as input select the not applicable check box
	 * and enter the remarks on remarks field
	 * 
	 * @param componentname name of sub component
	 * @param remarks       not applicable remarks
	 */
	public void notApplicable(String componentname, String remarks) {
		click("//td[text()='" + componentname + "']/../td[6]//input");
		sendKeys("//td[text()='" + componentname + "']/../td[6]//textarea", remarks);
	}

	/**
	 * Takes component name and comments as input select the insuff check box and
	 * enter the comments in insuff comments field
	 * 
	 * @param componentname name of sub component
	 * @param comments      insuff raise comments
	 */
	public void raiseInsuff(String componentname, String comments) {
		click("//td[text()='" + componentname + "']/../td[7]//input");
		sendKeys("//td[text()='" + componentname + "']/../td[7]//textarea", comments);
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
		click("//td[text()='" + componentname + "']/../td[8]//input");
		sendKeys("//td[text()='" + componentname + "']/../td[8]//textarea", comments);
		sendKeys(
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
		String docname = super
				.getText("//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='"
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
		waitUntilLoaderisInvisible(100);
		if (this.isDoctypeValid(doctype, 1)) {
			sendKeys(
					"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='"
							+ doctype + "']/../td[5]//div/ul/li/span/input[2]",
					fileName);
			this.WaitforFileUpdate(doctype, fileName);
		} else
			throw new NotFoundException(doctype);
		click("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnAddCaseDocument_input");
		waitUntilLoaderisInvisible(50);
		click("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");

	}
	/**
	 * Takes doctype and file name as input and waits for given document to upload
	 * @param doctype type of document
	 * @param filepath file to upload
	 */
	public void WaitforFileUpdate(String doctype,String filepath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String file=new File(filepath).getName();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[text()='"+doctype+"']/../td[5]//div/ul/li[1]/span/span"), file));
		String name =getText("//*[text()='"+doctype+"']/../td[5]//div/ul/li[1]/span/span");
		logger.log(Status.INFO, name);
//		try {
//			logger.info(name, MediaEntityBuilder.createScreenCaptureFromPath(Utill.getScreenshot(driver)).build());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("----------------------------"+name+"---------------");
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
			click(path);
		} else
			throw new NotFoundException(doctype);
		confirmAlert();
		waitUntilLoaderisInvisible(50);

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
			click(path);
		} else
			throw new NotFoundException(doctype);
		confirmAlert();
		waitUntilLoaderisInvisible(50);
	}

	/**
	 * Takes document type as input and return file name of uploaded case document
	 * 
	 * @param doctype Type of document
	 * @return filename name of uploaded document
	 */
	public String getuploadcaseDoc(String doctype) {
		this.uploadcaseDoc();
		waitUntilLoaderisInvisible(100);
		//
		String docname = null;
		if (this.isDoctypeValid(doctype, 1)) {
			docname = getText(
					"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='"
							+ doctype + "']/../td[5]//span");
		} else
			throw new NotFoundException(doctype);
		click("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");
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
//		sendKeys("//td[text()='" + doctype + "']/following-sibling::td[3]//@value='Select'", file);
		if (this.isDoctypeValid(doctype, 0)) {
			sendKeys(
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
		click("//td[text()='" + componentName + "']/../td[10]//input[1]");
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Perform click action on Add Document button
	 */
	public void addDocument() {
		click("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnAddDocument_input");
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Performs click action on Upload Documents
	 */
	public void uploadcaseDoc() {
		click("ctl00_ContentPlaceHolder1_btnCaseDocument_input");
	}

	/**
	 * Performs click action on cancel icon in upload popup
	 */
	public void docupCancel() {
		click("//a[@class='rwCloseButton']");

	}

	/**
	 * Performs click action on cancel icon in upload popup
	 */
	public void docupClose() {
		click("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnDocumentCancel");

	}

	/**
	 * Performs click action on save button
	 */
	public void save() {
		click("ctl00_ContentPlaceHolder1_btnSave_input");
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Performs click action on submit button
	 */
	public void submit() {
		click("ctl00_ContentPlaceHolder1_btnSaveSubmit_input");
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Performs click action on cancel button
	 */
	public void cancel() {
		click("ctl00_ContentPlaceHolder1_btnCancel");
		waitUntilLoaderisInvisible(50);
	}

	/**
	 * Takes Gender as input it should be either male or female select the gender
	 * from dropwdown based on input
	 * 
	 * @param gender male or female
	 * @throws Exception except male and female any input given it throws
	 */
	public void gender(String gender) throws Exception {
		click("ctl00_ContentPlaceHolder1_ddlGender_Input");
		Thread.sleep(1000);
		if (gender.equalsIgnoreCase("male")) {
			// click("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Male'");
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[2]");
		} else if (gender.equalsIgnoreCase("female")) {
			// click("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Female'");
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[3]");
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
		click("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
		Thread.sleep(2000);
		if (status.equalsIgnoreCase("Single")) {
			// click("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Single'");
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[2]");
		} else if (status.equalsIgnoreCase("married")) {
			// click("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Married'");
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[3]");
		} else if (status.equalsIgnoreCase("divorced")) {
			// click("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Divorced'");
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[4]");
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
		
		this.selectClient(data.get("ClientName"));
		this.selectProject(data.get("ProjectName"));
		this.FirstName(data.get("CandidateName"));
		this.LastName(data.get("lastname"));
		this.DOB(getDob());
		this.gender("male");
		this.Email(data.get("CandidateName") + "@ggmail.com");
		this.FatherFirstName("fname");
		this.FatherLastName("lname");
		this.LinkedIn(data.get("CandidateName") + " linkedin");
		this.maritalStatus("Single");
		this.Nationality("Indian");
		this.LandlineNumber(mobileno());
		this.MobileNumber(mobileno());
		this.EmergencyContactNumber(mobileno());
		this.EmergencyContactPerson(data.get("CandidateName"));
		this.CandidateID(data.get("CandidateId"));
		this.clickfresher(fresher);
		this.addEditComponent();
	}

	/**
	 * Takes case registration details as input with hashmap format
	 * 
	 * @param data hash map contains registration details
	 * @throws Exception element not found
	 */
	public void registercase(HashMap<String, String> data) throws Exception {
		
		this.selectClient(data.get("ClientName"));
		this.selectProject(data.get("ProjectName"));
		this.FirstName(data.get("CandidateName"));
		this.LastName(data.get("lastname"));
		this.DOB(getDob());
		this.gender("male");
		this.Email(data.get("CandidateName") + "@ggmail.com");
		this.FatherFirstName("fname");
		this.FatherLastName("lname");
		this.LinkedIn(data.get("CandidateName") + " linkedin");
		this.maritalStatus("Single");
		this.Nationality("Indian");
		this.LandlineNumber(mobileno());
		this.MobileNumber(mobileno());
		this.EmergencyContactNumber(mobileno());
		this.EmergencyContactPerson(data.get("CandidateName"));
		this.CandidateID(data.get("CandidateId"));
		this.clickfresher(false);
		this.addEditComponent();
	}

	/**
	 * Returns the value of Components Count
	 * 
	 * @return count Components Count
	 */
	public int getCheckCount() {
		return Integer.parseInt(getText("ctl00_ContentPlaceHolder1_lblComponentCount"));
	}

	/**
	 * Takes component name as input and returns true when given component has
	 * selected
	 * 
	 * @param componentname name of sub component
	 * @return boolean true = component selected , false = not selected
	 */
	public boolean isSelected(String componentname) {
		return isSelected("//td[text()='" + componentname + "']/../td[2]//input");
	}

	/**
	 * Takes component name as input and returns true when given component has
	 * enabled
	 * 
	 * @param componentname name of sub component
	 * @return boolean true = component enabled , false = not enabled
	 */
	public boolean isEnabled(String componentname) {
		return isEnabled("//td[text()='" + componentname + "']/../td[2]//input");
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
		sleep(1000);
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
	/**
	 * Takes 1 components form each check and return as array
	 * @return list of components
	 */
	public String [] getcomponents() {
		String [] compoents= {"Permanent","Current Address","12th","UG1","Current/Latest Employment","Previous Employment","Reference 1","Aadhaar Card","Passport","Current Address Criminal Check","Permanent Criminal Check","Current Address Court Check","Permanent Court Check","Credit Check 1","Panel1","Database"};
		return compoents;
	}
	
}
