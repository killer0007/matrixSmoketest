package dataEntryQC;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public abstract class DataEntryQCPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public DataEntryQCPage(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}
	/**
	 * Performs click action on History button
	 */
	public void history() {

		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnHistoryPage_input");
		pages.Utill().wait_until_loader_is_invisible(50);
	}
	/**
	 * returns name who raised insuff
	 * @return name employee name
	 */
	public String getraisedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaisedBy")
				.trim();
	}
	/**
	 * returns stage in which stage insuff raised
	 * @return stage name
	 */
	public String getraisedStage() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaiseState")
				.trim();
	}
	/**
	 * returns comments which given when the time if insuff raise
	 * @return insuff raised comments
	 */
	public String getraisedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}
	/**
	 * return employee name who cleared the insuff
	 * @return name employee name
	 */
	public String getclearedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblClearedByName")
				.trim();
	}
	/**
	 * return insuff clear comments
	 * @return comments insuff clear
	 */
	public String getclearedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblClearComments")
				.trim();
	}
	/**
	 * Performs click action on close button in document upload popup
	 */
	public void close() {
		pages.Utill().click_element("//a[@class='rwCloseButton']");
	}
	/**
	 * returns document name which uploaded for insuff clear
	 * @return document name
	 */
	public String historyDocument() {
		String dc = pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblDoc");
		return dc.replaceAll("[0-9]", "");
	}
/**
 * Performs click action on insuff tab in history popup
 */
	public void Insuff() {
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_RadTabStripForHistory']/div//li[1]");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Performs click action on Redo tab in history popup
	 */
	public void Redo() {
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_RadTabStripForHistory']/div//li[2]");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes doctype and file name as input and waits for given document to upload
	 * @param doctype type of document
	 * @param filepath file to upload
	 */
	public void WaitforFileUpdate(String doctype,String filepath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String file=new File(filepath).getName();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[text()='"+doctype+"']/../td[5]//div/ul/li[1]/span/span"), file));
	}
	
public abstract void document();
public abstract void submit() throws Exception;	
public abstract void save() throws Exception;
public abstract void ReportInsuff();
public abstract void Insuffcomm(String comments);
public abstract void Notapplicable();
public abstract void Notapplicablecomm(String comments);
public abstract String getDocumentName(String doctype);
public abstract void docclose();
}
