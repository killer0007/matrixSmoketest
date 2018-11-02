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

public class Database extends Verification {
	/**
	 * This is class for Database page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Database(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select Database tab and switch to Database frame
	 */
	public void databasecheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='DataBase']");
		pages.Utill().SwitchFramebyIndex(4);
	}

	/**
	 * Performs click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnDataBaseDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_Input");
//		pages.Utill().sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//div[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul//li[text()='"
						+ component + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes name as input Pass it to name On Id
	 * 
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseNameOnID", name);
	}

	/**
	 * Takes id card number as input and pass it to ID Number
	 * 
	 * @param number on Id card
	 */
	public void IDNumber(String number) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIDNumber", number);
	}

	/**
	 * Takes name as input and pass it to issue date (dd/mm/yyy)
	 * 
	 * @param date ID card issued date
	 */
	public void IssueDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIssueDate_dateInput", date);
	}

	/**
	 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
	 * 
	 * @param date ID card Expiry date
	 */
	public void ExpiryDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseExpiryDate_dateInput", date);
	}

	/**
	 * select India as country
	 * 
	 * @deprecated country disabled for id card
	 */
	public void Country() {
		boolean re = false;
		if (re) {
			pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_Input", "India");
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul//li[text()='India']");
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_Input", "Tamil Nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes no as input and pass it to Enrollment number
	 * 
	 * @param no Enrollment number
	 */
	public void EnrollmentNo(String no) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseEnrollid", no);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseComments", comments);
	}

	/**
	 * click submit button on database data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnDataBaseSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnDataBaseSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_btnDataBaseAddDocuments_input");
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
				"//*[@id='ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_gviewDataBaseDocuments_ctl00__0']/td[2]",
				10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_gviewDataBaseDocuments_ctl00']/tbody/tr/td[2]";
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
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_btnDataBaseDocumentCancels_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	public String IdType() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_Input");
	}

	public String NameonID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseNameOnID");
	}

	public String IDNumber() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIDNumber");
	}

	public String IssueDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseExpiryDate_dateInput");
	}

	public String CountryofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_Input");
	}

	public String StateofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_Input");
	}

	public String CityofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_Input");
	}

	public String EnrollmentNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseEnrollid");
	}

	public String comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDataBaseComments");
	}
public void group(String groupName) {
	String group=pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_Input");
	if(!group.equals(groupName)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_DropDown']/div/ul/li[1]")));
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_DropDown']/div/ul//li[text()='"
				+ groupName + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
}
public void tag(String tagName) {
	String tag=pages.Utill().getValue("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_Input");
	if(!tag.equals(tagName)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_DropDown']/div/ul/li[1]")));
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_DropDown']/div/ul//li[text()='"
				+ tagName + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
}
public void addDBCheck() {
	String tagName="Record Found";
		pages.Utill().click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_DropDown']/div/ul/li[1]")));
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_DropDown']/div/ul//li[text()='"
				+ tagName + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_txtDataBaseComments", "found");
		pages.Utill().click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_imgBtnDataBaseOk");
		pages.Utill().waitUntilLoaderisInvisible(100);
}
	public void VerifierName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierName", name);
	}

	public String VerifierName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierName");
	}

	public void VerifierDesignation(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierDesignation", relationship);
	}

	public String VerifierDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierDesignation");
	}

	public void VerifierContactNo(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierContactNo", relationship);
	}

	public String VerifierContactNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierContactNo");
	}

	public void VerifierEmail(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierEmailID", relationship);
	}

	public String VerifierEmail() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierEmailID");
	}

	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierComments", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_DropDown']/div/ul/li[1]")));
			pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_DropDown']/div/ul//li[text()='"
					+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
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
		Properties pro = pages.Utill().veridata("database");
		this.databasecheck();
		this.group("Felony Check");
		this.tag("FELONY TAG");
		this.addDBCheck();
		this.document();
		this.UploadDocument("Verification Report", pro.getProperty("dbdoc"));
		this.docclose();
		this.VerifierName(pro.getProperty("VerifierName"));
		this.VerifierDesignation(pro.getProperty("VerifierDesignation"));
		this.VerifierContactNo(pro.getProperty("VerifierContactNo"));
		this.VerifierEmail(pro.getProperty("VerifierEmail"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.submit();
	}
}
