package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Credit extends DataEntryPage{
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for Credit page in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Credit(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	/**
	 * Select Credit tab and switch to Credit frame
	 */
	public void creditcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[7]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(6);
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
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblRaisedBy")
				.trim();
	}
/**
 * returns stage in which stage insuff raised
 * @return stage name
 */
	public String getraisedStage() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblRaiseState")
				.trim();
	}
/**
 * returns comments which given when the time if insuff raise
 * @return insuff raised comments
 */
	public String getraisedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}
/**
 * return employee name who cleared the insuff
 * @return name employee name
 */
	public String getclearedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblClearedByName")
				.trim();
	}
	/**
	 * return insuff clear comments
	 * @return comments insuff clear
	 */
	public String getclearedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblClearComments")
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
		return pages.Utill().get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblDoc").replaceAll("[0-9]", "");
	}
	/**
	 * Performs click action on Document button
	 */
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCreditAddDocuments_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
}
