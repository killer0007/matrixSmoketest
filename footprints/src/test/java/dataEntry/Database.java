package dataEntry;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

public class Database extends DataEntryPage {
	/**
	 * This is class for Database page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Database(ExtentTest logger) {
		super(logger);
	}
	/**
	 * Select Database tab and switch to Database frame
	 */
	public void databasecheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[5]/a/span/span/span");
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li//span[text()='DataBase']");
		pages.Utill().SwitchFramebyIndex(4);
	}
	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnDataBaseDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_Input");
//		pages.Utill().sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIDComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
			pages.Utill().wait_until_loader_is_invisible(100);
	}
	
	/**
	 * Takes name as input Pass it to name On Id
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseNameOnID", name);
	}
	/**
	 * Takes id card number as input and pass it to ID Number
	 * @param number on Id card
	 */
public void IDNumber(String number) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIDNumber", number);
	}
/**
 * Takes name as input and pass it to issue date (dd/mm/yyy)
 * @param date ID card issued date
 */
public void IssueDate(String date) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseIssueDate_dateInput", date);
}
/**
 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
 * @param date ID card Expiry date
 */
public void ExpiryDate(String date) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseExpiryDate_dateInput", date);
}
/**
 * select India as country 
 * @deprecated country disabled for id card
 */
public void Country() {
	boolean re=false;
	if(re) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_Input", "India");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCountry_DropDown']/div/ul//li[text()='India']");
	pages.Utill().wait_until_loader_is_invisible(100);
}
}
/**
 * select tamil nadu as state
 */
public void State() {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_Input", "tamil nadu");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * select chennai as city
 */
public void City() {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_Input", "chennai");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_dockIDDetails_C_ddlDataBaseIssueCity_DropDown']/div/ul//li[text()='Chennai']");
	pages.Utill().wait_until_loader_is_invisible(100);
}
/**
 * Takes no as input and pass it to Enrollment number
 * @param no Enrollment number
 */
public void EnrollmentNo(String no) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_dockIDDetails_C_txtDataBaseEnrollid", no);
}
/**
 * comments
 * 
 * @param comments address comments
 */
public void comments(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtDataBaseComments", comments);
}

/**
 * click report insuff button
 */
public void ReportInsuff() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkDataBaseInSuff");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * Takes insufff comments as input and pass it
 * 
 * @param comments insuff raise comments
 */
public void Insuffcomm(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtDataBaseInSuffComments", comments);
}

/**
 * click not applicable button
 */
public void Notapplicable() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * Takes not applicable comments as input and pass it
 * 
 * @param comments not applicable comments
 */
public void Notapplicablecomm(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
}
/**
 * click submit button on database data entry
 * @throws Exception WebDriverException
 */
public void submit() throws Exception{
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnDataBaseSaveSubmit_input");
	pages.Utill().wait_until_loader_is_invisible(100);
	pages.Utill().SwitchDefault();
	pages.Utill().confirmAlert();
}
/**
 * performs click action on save button
 */
public void save() throws Exception {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnDataBaseSave_input");
	pages.Utill().wait_until_loader_is_invisible(100);
	pages.Utill().confirmAlert();
}
/**
 * Takes input from database.properties file and pass it to database data entry
 * @throws Exception webdriver exception
 */
public void database() throws Exception {
	Properties pro = pages.Utill().dedata("database");
	this.databasecheck();
	this.subIDComponent(pro.getProperty("IdType"));
	this.NameonID(pro.getProperty("NameonID"));
	this.IDNumber(pro.getProperty("IDNumber"));
	this.IssueDate(pro.getProperty("IssueDate"));
	this.ExpiryDate(pro.getProperty("ExpiryDate"));
	this.State();
	this.City();
	this.EnrollmentNo(pro.getProperty("EnrollmentNo"));
	this.comments(pro.getProperty("comments"));
	this.submit();
}
}
