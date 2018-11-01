package verification;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Id extends Verification {
	/**
	 * This is class for Id page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Id(ExtentTest logger) {
		super(logger);
	}

	/**
	 * Select Id tab and switch to Id frame
	 */
	public void idcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[9]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='ID']");
		pages.Utill().SwitchFramebyIndex(9);
	}

	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdAddDocuments_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
		if (!value.trim().equals(component)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill().click("//div[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul//li[text()='"
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

		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li"));
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
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdCheckComponent_Input");

		pages.Utill().click(
				"//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='" + component + "']");
	}

	/**
	 * Takes name as input Pass it to name On Id
	 * 
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdName", name);
	}

	/**
	 * Takes id card number as input and pass it to ID Number
	 * 
	 * @param number on Id card
	 */
	public void IDNumber(String number) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdNumber", number);
	}

	/**
	 * Takes name as input and pass it to issue date (dd/mm/yyy)
	 * 
	 * @param date ID card issued date
	 */
	public void IssueDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdIssueDate_dateInput", date);
	}

	/**
	 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
	 * 
	 * @param date ID card Expiry date
	 */
	public void ExpiryDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdExpireDate_dateInput", date);
	}

	/**
	 * select India as country
	 * 
	 * @deprecated country disabled for id card
	 */
	public void Country() {
		boolean re = false;
		if (re) {
			pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueCountry_Input", "India");
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li//text()='India'");
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul/li[1]")));
		pages.Utill()
				.click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul/li[1]")));
		pages.Utill()
				.click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes no as input and pass it to enrollment number field
	 * 
	 * @param no enrollment number
	 */
	public void EnrollmentNo(String no) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEnrollId1", no);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdComments", comments);
	}

	/**
	 * click submit button on id data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_btnIdAddDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes document type as input and checks for given document type available in
	 * upload screen
	 * 
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		pages.Utill().waitUntilElementHasText(
				"//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00__0']/td[2]", 10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00']/tbody/tr/td[2]";
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
			pages.Utill().sendKeys("//*[text()='" + doctype + "']/../td[5]//span/input[2]", file);
			super.WaitforFileUpdate(doctype, file);
			this.AddDocument();
			pages.Utill().waitUntilLoaderisInvisible(100);
		} else {
			throw new NotFoundException(doctype);
		}

	}

	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_btnIdDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
	}

	public String NameonID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdName");
	}

	public String IDNumber() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdNumber");
	}

	public String IssueDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdExpireDate_dateInput");
	}

	public String CountryofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdIssueCountry_Input");
	}

	public String StateofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdIssueState_Input");
	}

	public String CityofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdIssueCity_Input");
	}

	public String EnrollmentNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEnrollId1");
	}

	public String comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdComments");
	}

	public void VerifierName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierName", name);
	}

	public String VerifierName() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtIdVerifierName");
	}

	public void VerifierDesignation(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierDesignation", relationship);
	}

	public String VerifierDesignation() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtIdVerifierDesignation");
	}

	public void VerifierContactNo(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierNo", relationship);
	}

	public String VerifierContactNo() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtIdVerifierNo");
	}

	public void VerifierEmail(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdVerifierEmail", relationship);
	}

	public String VerifierEmail() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtIdVerifierEmail");
	}

	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdVerfierComments", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtIdVerfierComments");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdVerifierMark_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlIdVerifierMark_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//*[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtIdDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtIdDateOfVerification");
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
		Properties pro = pages.Utill().veridata("id");
		this.idcheck();
		this.document();
		String name = this.Component();
		if(name.equals("Passport")) {
			this.UploadDocument("Verification Report", pro.getProperty("passportdoc"));
		}
		this.docclose();
		this.VerifierName(pro.getProperty("VerifierName"));
		this.VerifierDesignation(pro.getProperty("VerifierDesignation"));
		this.VerifierContactNo(pro.getProperty("VerifierContactNo"));
		this.VerifierEmail(pro.getProperty("VerifierEmail"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.ModeOfInitiation(pro.getProperty("ModeOfInitiation"));
		this.DateOfInitiation(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
}
