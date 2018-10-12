package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Employment extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for Employement page in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Employment(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	/**
	 * Select Employment tab and switch to Employment frame
	 */
	public void employementcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[3]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(2);
	}
/**
 * Performs click action in Cep tab in Document screen
 */
	public void Cep() {
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_RadTabStripForHistory']/div//li[3]");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * returns name who raised CEP
	 * @return name employee name
	 */
	public String CepgetraisedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaisedBy")
				.trim();
	}
	/**
	 * returns stage in which stage CEP raised
	 * @return stage name
	 */
	public String CepgetraisedStage() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaiseState")
				.trim();
	}
	/**
	 * returns comments which given when the time if CEP raise
	 * @return CEP raised comments
	 */
	public String CepgetraisedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}
	/**
	 * return employee name who cleared the CEP
	 * @return name employee name
	 */
	public String CepgetclearedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblClearedByName")
				.trim();
	}
	/**
	 * return CEP clear comments
	 * @return comments CEP clear
	 */
	public String CepgetclearedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblClearComments")
				.trim();
	}
	/**
	 * returns document name which uploaded for CEP clear
	 * @return document name
	 */
	public String CephistoryDocument() {
		String dc = pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblDoc");
		return dc.replaceAll("[0-9]", "");


	}
	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnEmploymentDocumentUpload_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
}
