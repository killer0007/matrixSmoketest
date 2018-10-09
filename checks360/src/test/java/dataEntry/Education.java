package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Education extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Education(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void educationcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[2]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(1);
	}
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnEducationAddDocuments_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
}
