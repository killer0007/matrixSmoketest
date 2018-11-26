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

	public Basic(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public void FatherFirstName(String firstName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtFatherFirstName", firstName);
	}

	public void FatherLastName(String lastName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtFatherLastName", lastName);
	}

	public void Fresher(boolean value) {
		if (value) {
			super.click("ctl00_ContentPlaceHolder1_rblFresher_0");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 100);
			super.confirmAlert();
		}
	}

	public void MobileNumber(String mobno) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtMobileNumber1", mobno);
	}

	public void LandlineNumber(String no) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtLandLine", no);
	}

	public void EmergencyContactNumber(String no) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtEmergencyContact", no);
	}

	public void EmergencyContactPerson(String name) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson", name);
	}

	public void Nationality(String nation) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtNationality", nation);
	}

	public void Proceed() {
		super.click("btnApplyChanges_input");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 50);
	}

	public void Gender(String gender) {
		String value = super.getValue("ctl00_ContentPlaceHolder1_ddlGender_Input");
		if (!value.equals(gender)) {
			super.click("ctl00_ContentPlaceHolder1_ddlGender_Input");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[text()='" + gender + "']");

		}
	}

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
	

	public void EmplyeeID(String id) {
		String value = super.getValue("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		if (!value.equals("Employee ID")) {
			super.click("ctl00_ContentPlaceHolder1_ddlIDType_Input");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']/div/ul/li[text()='Employee ID']");
		}
		super.sendKeys("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
	}

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

	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}

	public void LOA() {
		if (!this.getTitle().equals("LOA")) {
			super.click("linkText:Declaration");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 10);
		}
	}

	public void Agree() {
		super.click("chkAgree");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 50);
	}

	public void comments(String com) {
		super.sendKeys("txtCandidateComment", com);
	}

	public void Upload() {
		super.click("btnCaseDocument");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1", 100);
	}

	public void docclose() {
		super.click("class:rwCloseButton");
	}

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

	public void WaitforFileUpdate(String doctype, String filepath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String file = new File(filepath).getName();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//*[text()='" + doctype + "']/../td[5]//div/ul/li[1]/span/span"), file));
		String name = getText("//*[text()='" + doctype + "']/../td[5]//div/ul/li[1]/span/span");
		logger.log(Status.INFO, name);
	}

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

	public String confirmAlert() {
		By loc = By.id("cnsubmit");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(loc));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(loc)));
		String msg = this.getText("//*[@id='ConfirmBoxNew']/div/div[1]");
		driver.findElement(loc).click();
		return msg.trim();

	}

	public String getStatusColor() {
		return super.getCssValue("//a[text()='Basic Details']/../following-sibling::td/span", "color");
	}

	public String getStatus() {
		return super.getText("//a[text()='Basic Details']/../following-sibling::td/span");
	}
}
