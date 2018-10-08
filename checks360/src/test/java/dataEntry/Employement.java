package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Employement extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Employement(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void employementcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[3]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(2);
	}

	public void Cep() {
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_RadTabStripForHistory']/div//li[3]");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	public String CepgetraisedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaisedBy")
				.trim();
	}

	public String CepgetraisedStage() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaiseState")
				.trim();
	}

	public String CepgetraisedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}

	public String CepgetclearedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblClearedByName")
				.trim();
	}

	public String CepgetclearedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblClearComments")
				.trim();
	}
	public String CephistoryDocument() {
		String dc = pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_gviewEmploymentCEPClear_ctl00_ctl04_lblDoc");
		return dc.replaceAll("[0-9]", "");


	}
}
