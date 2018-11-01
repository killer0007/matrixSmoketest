package verification;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Credit extends Verification {

	/**
	 * This is class for Credit page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Credit(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select Credit tab and switch to Credit frame
	 */
	public void creditcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[7]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Credit']");
		pages.Utill().SwitchFramebyIndex(6);
	}

	/**
	 * Performs click action on History button
	 */
	public void history() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnHistoryPage_input");
		pages.Utill().waitUntilLoaderisInvisible(50);
	}

	/**
	 * Performs click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCreditAddDocuments_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
		if (!value.trim().equals(component)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill()
						.click("//div[@id='ctl00_ContentPlaceHolder1_ddlcreditComponent_DropDown']/div/ul//li[text()='"
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
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlcreditComponent_DropDown']/div/ul/li"));
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
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCreditId_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='" + component + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes name as input Pass it to name On Id
	 * 
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditIdName", name);
	}

	/**
	 * Takes id card number as input and pass it to ID Number
	 * 
	 * @param number on Id card
	 */
	public void IDNumber(String number) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditIdNumber", number);
	}

	/**
	 * Takes name as input and pass it to issue date (dd/mm/yyy)
	 * 
	 * @param date ID card issued date
	 */
	public void IssueDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditIdIssueDate_dateInput", date);
	}

	/**
	 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
	 * 
	 * @param date ID card Expiry date
	 */
	public void ExpiryDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditIdExpireDate_dateInput", date);
	}

	/**
	 * select India as country
	 * 
	 * @deprecated country disabled for id card
	 */
	public void Country() {
		boolean re = false;
		if (re) {
			pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_Input", "India");
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_DropDown']/div/ul/li//text()='India'");
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCreditIssueState_Input", "Tamil Nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCreditIssueCity_Input", "Chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCity_DropDown']/div/ul/li[1]")));
		pages.Utill()
				.click("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditComments", comments);
	}

	/**
	 * click submit button on credit data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCreditSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCreditSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * Takes no as input and pass it to Enrollment number
	 * 
	 * @param no Enrollment number
	 */
	public void EnrollmentNo(String no) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditEnrollId1", no);
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_btnCreditAddDocument_input");
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
				"//*[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00__0']/td[2]",
				10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00']/tbody/tr/td[2]";
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
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_btnCreditDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
	}

	public String ID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditId_Input");
	}

	public String NameonID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdName");
	}

	public String IDNumber() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdNumber");
	}

	public String IssueDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdExpireDate_dateInput");
	}

	public String CountryofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_Input");
	}

	public String StateofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueState_Input");
	}

	public String CityofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueCity_Input");
	}

	public String EnrollmentNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditEnrollId1");
	}

	public String comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditComments");
	}

	public void VerifierName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreidtVerifierName", name);
	}

	public String VerifierName() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCreidtVerifierName");
	}

	public void VerifierDesignation(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditVerifierDesignation", relationship);
	}

	public String VerifierDesignation() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCreditVerifierDesignation");
	}

	public void VerifierContactNo(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditVerifierNo", relationship);
	}

	public String VerifierContactNo() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCreditVerifierNo");
	}

	public void VerifierEmail(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCredtiVerifierEmail", relationship);
	}

	public String VerifierEmail() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCredtiVerifierEmail");
	}

	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditVerfierComments", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCreditVerfierComments");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditVerifierMark_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCreditVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCreditVerifierMark_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCreditModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCreditModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCreditModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCreditDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCreditModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlCreditModeOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCreditModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCreditDateOfVerification");
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
		Properties pro = pages.Utill().veridata("credit");
		this.creditcheck();
		this.document();
		this.UploadDocument("Verification Report", pro.getProperty("Aadhardoc"));
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
