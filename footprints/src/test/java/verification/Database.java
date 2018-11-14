package verification;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Database extends dataEntryQC.Database implements Verification {
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
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='DataBase']");
		SwitchFramebyIndex(4);
	}

	/**
	 * Performs click action on Document button
	 */
	public void document() {
		click("ctl00_ContentPlaceHolder1_btnDataBaseDocument_input");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		click("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_Input");
//		sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul/li[1]")));
		click(
				"//div[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul//li[text()='"
						+ component + "']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes name as input Pass it to name On Id
	 * 
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseNameOnID", name);
	}

	/**
	 * Takes id card number as input and pass it to ID Number
	 * 
	 * @param number on Id card
	 */
	public void IDNumber(String number) {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIDNumber", number);
	}

	/**
	 * Takes name as input and pass it to issue date (dd/mm/yyy)
	 * 
	 * @param date ID card issued date
	 */
	public void IssueDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIssueDate_dateInput", date);
	}

	/**
	 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
	 * 
	 * @param date ID card Expiry date
	 */
	public void ExpiryDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseExpiryDate_dateInput", date);
	}

	/**
	 * select India as country
	 * 
	 * @deprecated country disabled for id card
	 */
	public void Country() {
		boolean re = false;
		if (re) {
			sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_Input", "India");
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul//li[text()='India']");
			waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_Input", "Tamil Nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes no as input and pass it to Enrollment number
	 * 
	 * @param no Enrollment number
	 */
	public void EnrollmentNo(String no) {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseEnrollid", no);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseComments", comments);
	}

	/**
	 * click submit button on database data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnDataBaseSaveSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnDataBaseSave_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_btnDataBaseAddDocuments_input");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes document type as input and checks for given document type available in
	 * upload screen
	 * 
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		waitUntilElementHasText(
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
			sendKeys("//*[text()='" + doctype + "']/../td[5]//span/input[2]", file);
			super.WaitforFileUpdate(doctype, file);
			this.AddDocument();
			waitUntilLoaderisInvisible(100);
		} else {
			throw new NotFoundException(doctype);
		}

	}

	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		click("ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_btnDataBaseDocumentCancels_input");
		waitUntilLoaderisInvisible(100);
	}

	public String IdType() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_Input");
	}

	public String NameonID() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseNameOnID");
	}

	public String IDNumber() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIDNumber");
	}

	public String IssueDate() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseExpiryDate_dateInput");
	}

	public String CountryofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_Input");
	}

	public String StateofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_Input");
	}

	public String CityofIssue() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_Input");
	}

	public String EnrollmentNo() {
		return getValue("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseEnrollid");
	}

	public String comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseComments");
	}
public void group(String groupName) {
	String group=getValue("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_Input");
	if(!group.equals(groupName)) {
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_DropDown']/div/ul//li[text()='"
				+ groupName + "']");
		waitUntilLoaderisInvisible(100);
	}
}
public void tag(String tagName) {
	String tag=getValue("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_Input");
	if(!tag.equals(tagName)) {
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_DropDown']/div/ul//li[text()='"
				+ tagName + "']");
		waitUntilLoaderisInvisible(100);
	}
}
public void addDBCheck() {
	String tagName="Record Found";
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_DropDown']/div/ul//li[text()='"
				+ tagName + "']");
		waitUntilLoaderisInvisible(100);
		sendKeys("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_txtDataBaseComments", "found");
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_imgBtnDataBaseOk");
		waitUntilLoaderisInvisible(100);
}
	public void VerifierName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierName", name);
	}

	public String VerifierName() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierName");
	}

	public void VerifierDesignation(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierDesignation", relationship);
	}

	public String VerifierDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierDesignation");
	}

	public void VerifierContactNo(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierContactNo", relationship);
	}

	public String VerifierContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierContactNo");
	}

	public void VerifierEmail(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierEmailID", relationship);
	}

	public String VerifierEmail() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierEmailID");
	}

	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierComments", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_DropDown']/div/ul//li[text()='"
					+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
	}

	public void ServiceProvider(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}
	public void Verification() throws Exception {
		Properties pro = veridata("database");
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
	public Map<String, String> databasedata() throws Exception{	
		this.databasecheck();
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("VerifierName", this.VerifierName());
		map.put("VerifierDesignation", this.VerifierDesignation());
		map.put("VerifierContactNo", this.VerifierContactNo());
		map.put("VerifierEmail", this.VerifierEmail());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= veridata("database");
		map.put("VerifierName", pro.getProperty("VerifierName"));
		map.put("VerifierDesignation", pro.getProperty("VerifierDesignation"));
		map.put("VerifierContactNo", pro.getProperty("VerifierContactNo"));
		map.put("VerifierEmail", pro.getProperty("VerifierEmail"));
		map.put("Ver_Comments", pro.getProperty("verComments"));
		map.put("ComponentStatus", pro.getProperty("ComponentStatus"));
		map.put("ServiceProvider", pro.getProperty("ServiceProvider"));
		logger.log(Status.INFO, map.toString());
		return map;
		
	}
	

	@Override
	public void UpdateReportComments() {
		int count = driver.findElements(By.xpath("//*[@id='accordion']/div")).size();

		for (int i = 1; i < count; i++) {
			if (i > 1) {
				click("//*[@id='accordion']/div[" + Integer.toString(i) + "]//b");
			}
			String info = getText("//*[@id='accordion']/div[" + Integer.toString(i) + "]//td[2]/span");
			sendKeys("//*[@id='accordion']/div[" + Integer.toString(i) + "]//div[3]/div/p/..", info);
		}

	}
}
