package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Id {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Id(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void idcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[9]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(9);
	}

	public void history() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnHistoryPage_input");
		pages.Utill().wait_until_loader_is_invisible(50);
	}
	public String getraisedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaisedBy")
				.trim();
	}

	public String getraisedStage() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaiseState")
				.trim();
	}

	public String getraisedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}

	public String getclearedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblClearedByName")
				.trim();
	}

	public String getclearedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblClearComments")
				.trim();
	}
	public void close() {
		pages.Utill().click_element("//a[@class='rwCloseButton']");
	}
	public String historyDocument() {
		return pages.Utill().get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblDoc").replaceAll("[0-9]", "");
	}
}
