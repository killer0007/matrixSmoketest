package candidate;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import environment.Utill;

public class Basic extends Utill {
/**
 * This is page design for basic details in candidate data entry
 * @param driver WebDriver
 * @param logger Report logger
 */
	public Basic(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
/**
 * Takes name as input and pass it to First name field
 * @param firstName of candidate
 */
	public void FatherFirstName(String firstName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtFatherFirstName", firstName);
	}
/**
 * Takes name as input and pass it to last name field
 * @param lastName of candidate
 */
	public void FatherLastName(String lastName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtFatherLastName", lastName);
	}
/**
 * Takes the boolean value true for fresher is yes
 * @param value fresher or not
 */
	public void Fresher(boolean value) {
		if (value) {
			super.click("ctl00_ContentPlaceHolder1_rblFresher_0");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 100);
			super.confirmAlert();
		}
	}
/**
 * Takes mobile number as input and pass it to Mobile no field
 * @param mobno candidates mobile number
 */
	public void MobileNumber(String mobno) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtMobileNumber1", mobno);
	}
/**
 * Takes land line number as input and pass it to landline number field
 * @param no land line number
 */
	public void LandlineNumber(String no) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtLandLine", no);
	}
/**
 * Takes contact number as input and pass it to Emergency contact number field
 * @param no Emergency contact number
 */
	public void EmergencyContactNumber(String no) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtEmergencyContact", no);
	}
/**
 * Takes contact person name as input and pass it to Emergency contact person name field
 * @param name contact person name
 */
	public void EmergencyContactPerson(String name) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson", name);
	}
/**
 * Takes nationality as input and pass it to Nationality field
 * @param nation Nationality
 */
	public void Nationality(String nation) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtNationality", nation);
	}
/**
 * Performs click action on Proceed button
 */
	public void Proceed() {
		super.click("btnApplyChanges_input");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 50);
	}
/**
 * Takes Gender type as input and select the gender type
 * @param gender type
 */
	public void Gender(String gender) {
		String value = super.getValue("ctl00_ContentPlaceHolder1_ddlGender_Input");
		if (!value.equals(gender)) {
			super.click("ctl00_ContentPlaceHolder1_ddlGender_Input");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[text()='" + gender + "']");

		}
	}
/**
 * Takes marital status as input and select the status
 * @param status maritial status
 */
	public void MaritalStatus(String status) {
		String value = super.getValue("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
		if (!value.equals(status)) {
			try {
				super.click("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
			} catch (WebDriverException e) {
				System.out.println(e.getMessage());
				logger.log(Status.WARNING, e.getMessage());
				super.sleep(700);
				super.click("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
			}
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[text()='" + status
					+ "']");
		}

	}
	
/**
 * Takes Employee Id as input and pas it to Employee ID filed
 * @param id Employee ID
 */
	public void EmplyeeID(String id) {
		String value = super.getValue("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		if (!value.equals("Employee ID")) {
			super.click("ctl00_ContentPlaceHolder1_ddlIDType_Input");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']/div/ul/li[text()='Employee ID']");
		}
		super.sendKeys("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
	}
/**
 * Takes candidate Id as input and pass it to candidate Id field
 * @param id candidate id
 */
	public void CandidateID(String id) {
		String value = super.getText("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		if (!value.equals("Candidate ID")) {
			super.click("ctl00_ContentPlaceHolder1_ddlIDType_DropDown");
			super.sleep(500);
			super.click(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[text()='Candidate ID']");
		}
		super.sendKeys("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
	}
/**
 * Performs click action on save next button
 */
	public void SaveNext() {
		super.click("ctl00_ContentPlaceHolder1_btnSaveNext_input");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 100);
		super.SwitchDefault();
	}

	/**
	 * Tales data from file and completes data entry for basic details
	 */
	public void basic() {
		Properties data = super.candidatedata("basic");
		if (!this.getTitle().equals("Basic Details")) {
			super.click("linkText:Basic Details");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 10);
		}
		super.SwitchFramebyIndex(0);
		this.FatherFirstName(data.getProperty("FatherFirstName"));
		this.FatherLastName(data.getProperty("FatherLastName"));
		this.MobileNumber(data.getProperty("MobileNumber"));
		this.LandlineNumber(data.getProperty("LandlineNumber"));
		this.EmergencyContactNumber(data.getProperty("EmergencyContactNumber"));
		this.EmergencyContactPerson(data.getProperty("EmergencyContactPerson"));
		this.Gender(data.getProperty("Gender"));
		this.MaritalStatus(data.getProperty("MaritalStatus"));
		this.Nationality(data.getProperty("Nationality"));
		this.EmplyeeID(data.getProperty("EmplyeeID"));
		this.SaveNext();
	}
/**
 * returns title of current sub check
 */
	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}
/**
 * Performs click on declaration hyper link
 */
	public void LOA() {
		if (!this.getTitle().equals("LOA")) {
			super.click("linkText:Declaration");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 10);
		}
	}
/**
 * Performs click action in Agree button in declaration page
 */
	public void Agree() {
		super.click("chkAgree");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 50);
	}
	/**
	 * Takes comments as input and pass it to comments field
	 */
	public void comments(String com) {
		super.sendKeys("txtCandidateComment", com);
	}
/**
 * Performs click action on case document upload button
 */
	public void Upload() {
		super.click("btnCaseDocument");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 100);
	}
/**
 * close upload pop up
 */
	public void docclose() {
		super.click("class:rwCloseButton");
	}
/**
 * Performs click action on save next button
 */
	public void saveNext() {
		try {
			super.sleep(500);
			super.click("ctl00_ContentPlaceHolder1_btnRefSaveSubmit_input");
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
			logger.log(Status.WARNING, e.getMessage().toString());
			super.sleep(300);
			super.click("ctl00_ContentPlaceHolder1_btnRefSaveSubmit_input");
		}
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 100);
		super.SwitchDefault();
	}
/**
 * Perform click action on add document button on the document upload screen
 */
	public void AddDocument() {
		click("rwCaseDocument_C_btnAddCaseDocument_input");
		waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 30);
	}

	/**
	 * Takes document type as input and checks for given document type available in
	 * upload screen
	 * 
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		waitUntilElementHasText("//*[@id='rwCaseDocument_C_grdCaseDocument_ctl00__0']/td[2]", 10);
		boolean re = false;
		String path = "//*[@id='rwCaseDocument_C_grdCaseDocument_ctl00']/tbody/tr/td[2]";
		List<WebElement> list = driver.findElements(By.xpath(path));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText().trim();
				logger.log(Status.INFO, t);
				if (t.equals(doctype)) {
					re = true;
					break;
				}
			}
		} else {
			logger.log(Status.FAIL, "no element found");
		}
		return re;
	}

	/**
	 * Takes document type and file as input and uploads the document
	 * 
	 * @param doctype type of document
	 * @param file    file name
	 */
	public void UploadDocument(String doctype, String file) {
		if (this.isvaliddoctype(doctype)) {
			sendKeys("//*[text()='" + doctype + "']/../td[5]//span/input[2]", file);
			this.WaitforFileUpdate(doctype, file);
			this.AddDocument();
		} else {
			throw new NotFoundException(doctype);
		}
	}
/**
 * Takes file name name and file type as input and waits until file to be uploaded
 * @param doctype document type name
 * @param filepath uploaded file path
 */
	public void WaitforFileUpdate(String doctype, String filepath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String file = new File(filepath).getName();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//*[text()='" + doctype + "']/../td[5]//div/ul/li[1]/span/span"), file));
		String name = getText("//*[text()='" + doctype + "']/../td[5]//div/ul/li[1]/span/span");
		logger.log(Status.INFO, name);
	}
/**
 * Performs click action on submit cif button
 */
	public void submitCIF() {
		try {
			super.sleep(400);
			super.click("btnSave");
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
			logger.log(Status.WARNING, e.getMessage().toString());
			super.sleep(300);
			super.click("btnSave");
		}

		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 50);
	}
/**
 * compete the declaration page activities
 */
	public void Declaration() {
		this.LOA();
		this.Agree();
		this.comments("completed");
		this.Upload();
		this.UploadDocument("Authorization Letter", "D:\\gopi\\checks360\\pdf\\Reference one.pdf");
		this.docclose();
		this.submitCIF();
		String warning = this.confirmAlert();
		// System.out.println(warning);
		logger.log(Status.INFO, warning);
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 40);
		String msg = this.confirmAlert();
		logger.log(Status.INFO, msg);
		// System.out.println(msg);
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 40);
	}
/**
 * click on confirmation alert pop up
 */
	public String confirmAlert() {
		By loc = By.id("cnsubmit");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(loc));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(loc)));
		String msg = this.getText("//*[@id='ConfirmBoxNew']/div/div[1]");
		driver.findElement(loc).click();
		return msg.trim();

	}
	/**
	 * returns the color of Basic Details text
	 * @return color of Basic Details text
	 */
	public String getStatusColor() {
		return super.getCssValue("//a[text()='Basic Details']/../following-sibling::td/span", "color");
	}
	/**
	 * Returns the Status of  Basic Details check in candidate data entry screen
	 * @return status of  Basic Details
	 */
	public String getStatus() {
		return super.getText("//a[text()='Basic Details']/../following-sibling::td/span");
	}
}
