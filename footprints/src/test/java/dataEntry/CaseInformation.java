package dataEntry;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class CaseInformation extends Utill{
	

	/**
	 * This is class for Case information part in data entry
	 * 
	 * @param logger logger instance
	 */
	public CaseInformation(WebDriver driver, ExtentTest logger) {
		
		super(driver,logger);
	}

	/**
	 * Perform click action on Edit button
	 * 
	 * @throws Exception when confirmation alert not found
	 */
	public void edit() throws Exception {
		click("btnCaseSave_input");
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Perform click action on CaseDocument button
	 */
	public void CaseDocument() {
		click("btnCaseDocument");
	}

	/**
	 * Takes Document Type as input and returns the uploaded file name
	 * 
	 * @param doctype Type Of Document
	 * @return File name which uploaded in portal
	 */
	public String getDocumentName(String doctype) {
		String path = "//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']//*[text()='" + doctype
				+ "']/../td[5]//td[1]/span";
		if (this.isDoctypeValid(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Takes Document type as input and download the document file
	 * 
	 * @param doctype Type Of Document
	 */
	public void documentDownload(String doctype) {
		String path = "//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']//*[text()='" + doctype
				+ "']/../td[5]//td[4]/input";
		if (this.isDoctypeValid(doctype)) {
			click(path);
		} else {
			throw new NotFoundException(doctype);
		}

	}

	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public boolean isDoctypeValid(String doctype) {
		String path = "//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']/tbody/tr/td[2]";
		List<WebElement> list = driver.findElements(By.xpath(path));
		if (list.size() > 0) {
			List<String> doc = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				doc.add(list.get(i).getText().trim());
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
	 * Performs click action on cancel button in document upload popup
	 */
	public void cancel() {
		click("rwmCaseDocuments_C_btnCaseDocumentCancels");
	}
	/**
	 * Return Client name
	 * @return name Client name
	 */
	public String Client() {
		
		return getValue("txtClientName");
	}
	/**
	 * Return Project name
	 * @return Project Project name
	 */
	public String Project() {
		return getValue("txtProjectName");
	}
	/**
	 * Return Case Owner
	 * @return name Case Owner name
	 */
	public String CaseOwner() {
		return getValue("txtCaseOwner");
	}
	/**
	 * Return First Name
	 * @return Name First Name
	 */
	public String FirstName() {
		return getValue("txtFirstName");
	}
	/**
	 * Return Last Name
	 * @return Name Last Name
	 */
	public String LastName() {
		return getValue("txtLastName");
	}
	/**
	 * Return dob
	 * @return dob date of birth
	 */
	public String dob() {
		return getValue("txtDateofBirth");
	}
	/**
	 * Return Email
	 * @return id Email id
	 */
	public String Email() {
		return getValue("txtEmail");
	}
	/**
	 * Return Father First Name 
	 * @return name Father First Name 
	 */
	public String FatherFirstName () {
		return getValue("txtFatherFirstName");
	}
	/**
	 * Return Father Last Name 
	 * @return name Father Last Name 
	 */
	public String FatherLastName () {
		return getValue("txtFatherLastName");
	}
	/**
	 * Return LinkedIn ID
	 * @return id LinkedIn ID
	 */
	public String LinkedInID() {
		return getValue("txtLinkedIn");
	}
	/**
	 * Return Nationality
	 * @return name Nationality
	 */
	public String Nationality() {
		return getValue("txtNationality");
	}
	/**
	 * Return LandLine Number
	 * @return number LandLine Number
	 */
	public String LandLineNumber() {
		return getValue("txtLandLine");
	}
	/**
	 * Return Mobile Number
	 * @return number Mobile Number
	 */
	public String MobileNumber() {
		return getValue("txtMobileNumber1");
	}
	/**
	 * Return Emergency Contact Number
	 * @return number Emergency Contact Number
	 */
	public String EmergencyContactNumber() {
		return getValue("txtMobileNumberSecond");
	}
	/**
	 * Return Emergency Contact Person
	 * @return name Emergency Contact Person
	 */
	public String EmergencyContactPerson() {
		return getValue("txtCntPerson");
	}
	/**
	 * Return candidate ID
	 * @return id candidate ID
	 */
	public String candidateID() {
		return getValue("txtClientCandidateID");
	}
	
}
