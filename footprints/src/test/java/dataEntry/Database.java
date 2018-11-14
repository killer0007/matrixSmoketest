package dataEntry;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;

public class Database extends DataEntryPage {
	/**
	 * This is class for Database page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Database(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Database tab and switch to Database frame
	 */
	public void databasecheck() {
		SwitchDefault();
//		click("//*[@id='tabStrip']/div/ul/li[5]/a/span/span/span");
		click("//*[@id='tabStrip']/div/ul/li//span[text()='DataBase']");
		SwitchFramebyIndex(4);
	}
	/**
	 * Performs click action on Document button
	 */
	@Override
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
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul/li[1]")));
			click("//div[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
			waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes name as input Pass it to name On Id
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseNameOnID", name);
	}
	/**
	 * Takes id card number as input and pass it to ID Number
	 * @param number on Id card
	 */
public void IDNumber(String number) {
	sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIDNumber", number);
	}
/**
 * Takes name as input and pass it to issue date (dd/mm/yyy)
 * @param date ID card issued date
 */
public void IssueDate(String date) {
	sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIssueDate_dateInput", date);
}
/**
 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
 * @param date ID card Expiry date
 */
public void ExpiryDate(String date) {
	sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseExpiryDate_dateInput", date);
}
/**
 * select India as country 
 * @deprecated country disabled for id card
 */
public void Country() {
	boolean re=false;
	if(re) {
	sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_Input", "India");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul/li[1]")));
	click(
			"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul//li[text()='India']");
	waitUntilLoaderisInvisible(100);
}
}
/**
 * select tamil nadu as state
 */
public void State() {
	sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_Input","Tamil Nadu");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul/li[1]")));
	click(
			"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
	waitUntilLoaderisInvisible(100);
}

/**
 * select chennai as city
 */
public void City() {
	sendKeys("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_Input","chennai");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul/li[1]")));
	click(
			"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul//li[text()='Chennai']");
	waitUntilLoaderisInvisible(100);
}
/**
 * Takes no as input and pass it to Enrollment number
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
 * click report insuff button
 */
public void ReportInsuff() {
	click("ctl00_ContentPlaceHolder1_chkDataBaseInSuff");
	waitUntilLoaderisInvisible(100);
}

/**
 * Takes insufff comments as input and pass it
 * 
 * @param comments insuff raise comments
 */
public void Insuffcomm(String comments) {
	sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseInSuffComments", comments);
}

/**
 * click not applicable button
 */
public void Notapplicable() {
	click("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
	waitUntilLoaderisInvisible(100);
}

/**
 * Takes not applicable comments as input and pass it
 * 
 * @param comments not applicable comments
 */
public void Notapplicablecomm(String comments) {
	sendKeys("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
}
/**
 * click submit button on database data entry
 * @throws Exception WebDriverException
 */
public void submit() throws Exception{
	click("ctl00_ContentPlaceHolder1_btnDataBaseSaveSubmit_input");
	waitUntilLoaderisInvisible(100);
	SwitchDefault();
	confirmAlert();
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
 * Takes document type as input and checks for given document type available in upload screen
 * @param doctype type of document
 * @return true when document ype was available
 */
public boolean isvaliddoctype(String doctype) {
waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_gviewDataBaseDocuments_ctl00__0']/td[2]", 10);
	boolean re =false;
	String path="//*[@id='ctl00_ContentPlaceHolder1_rwmDataBaseDocuments_C_gviewDataBaseDocuments_ctl00']/tbody/tr/td[2]";
	List<WebElement> list =driver.findElements(By.xpath(path));
	if(list.size()>0) {
		for (int i = 0; i < list.size(); i++) {
			String t=list.get(i).getText().trim();
			logger.log(Status.INFO, t);
			if(t.equals(doctype)) {
				re=true;
				break;
			}
		}
	}
	else {
		logger.log(Status.FAIL, "no element found");
	}
	return re;
}
/**
 * Takes document type and file as input and uploads the document
 * @param doctype type of document
 * @param file file name
 */
public void UploadDocument(String doctype, String file) {
	if(this.isvaliddoctype(doctype)) {
	sendKeys("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
	super.WaitforFileUpdate(doctype, file);
	this.AddDocument();
	waitUntilLoaderisInvisible(100);
	}
	else {
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
/**
 * Takes input from database.properties file and pass it to database data entry
 * @throws Exception webdriver exception
 */
public void database() throws Exception {
	Properties pro = dedata("database");
	this.databasecheck();
	this.subIDComponent(pro.getProperty("IdType"));
	this.NameonID(pro.getProperty("NameonID"));
	this.IDNumber(pro.getProperty("IDNumber"));
	this.IssueDate(pro.getProperty("IssueDate"));
	this.ExpiryDate(pro.getProperty("ExpiryDate"));
	this.State();
	this.City();
	this.EnrollmentNo(pro.getProperty("EnrollmentNo"));
	this.document();
	this.UploadDocument("Others", pro.getProperty("dbdoc"));
	this.docclose();
	this.comments(pro.getProperty("comments"));
	this.submit();
}
}
