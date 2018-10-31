package verification;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class CaseInformation {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Case information part in data entry
	 * 
	 * @param logger logger instance
	 */
	public CaseInformation(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * Perform click action on Edit button
	 * 
	 * @throws Exception when confirmation alert not found
	 */
	public void edit() throws Exception {
		pages.Utill().click("btnCaseSave_input");
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Perform click action on CaseDocument button
	 */
	public void CaseDocument() {
		pages.Utill().click("btnCaseDocument");
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
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
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
			pages.Utill().click(path);
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
		pages.Utill().click("rwmCaseDocuments_C_btnCaseDocumentCancels");
	}
	/**
	 * Return Client name
	 * @return name Client name
	 */
	public String Client() {
		
		return pages.Utill().getValue("txtClientName");
	}
	/**
	 * Return Project name
	 * @return Project Project name
	 */
	public String Project() {
		return pages.Utill().getValue("txtProjectName");
	}
	/**
	 * Return Case Owner
	 * @return name Case Owner name
	 */
	public String CaseOwner() {
		return pages.Utill().getValue("txtCaseOwner");
	}
	/**
	 * Return First Name
	 * @return Name First Name
	 */
	public String FirstName() {
		return pages.Utill().getValue("txtFirstName");
	}
	/**
	 * Return Last Name
	 * @return Name Last Name
	 */
	public String LastName() {
		return pages.Utill().getValue("txtLastName");
	}
	/**
	 * Return dob
	 * @return dob date of birth
	 */
	public String dob() {
		return pages.Utill().getValue("txtDateofBirth");
	}
	/**
	 * Return Email
	 * @return id Email id
	 */
	public String Email() {
		return pages.Utill().getValue("txtEmail");
	}
	/**
	 * Return Father First Name 
	 * @return name Father First Name 
	 */
	public String FatherFirstName () {
		return pages.Utill().getValue("txtFatherFirstName");
	}
	/**
	 * Return Father Last Name 
	 * @return name Father Last Name 
	 */
	public String FatherLastName () {
		return pages.Utill().getValue("txtFatherLastName");
	}
	/**
	 * Return LinkedIn ID
	 * @return id LinkedIn ID
	 */
	public String LinkedInID() {
		return pages.Utill().getValue("txtLinkedIn");
	}
	/**
	 * Return Nationality
	 * @return name Nationality
	 */
	public String Nationality() {
		return pages.Utill().getValue("txtNationality");
	}
	/**
	 * Return LandLine Number
	 * @return number LandLine Number
	 */
	public String LandLineNumber() {
		return pages.Utill().getValue("txtLandLine");
	}
	/**
	 * Return Mobile Number
	 * @return number Mobile Number
	 */
	public String MobileNumber() {
		return pages.Utill().getValue("txtMobileNumber1");
	}
	/**
	 * Return Emergency Contact Number
	 * @return number Emergency Contact Number
	 */
	public String EmergencyContactNumber() {
		return pages.Utill().getValue("txtMobileNumberSecond");
	}
	/**
	 * Return Emergency Contact Person
	 * @return name Emergency Contact Person
	 */
	public String EmergencyContactPerson() {
		return pages.Utill().getValue("txtCntPerson");
	}
	/**
	 * Return candidate ID
	 * @return id candidate ID
	 */
	public String candidateID() {
		return pages.Utill().getValue("txtClientCandidateID");
	}
	
}
