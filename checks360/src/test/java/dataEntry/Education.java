package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Education extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for Education page in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Education(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	/**
	 * Select Education tab and switch to Education frame
	 */
	public void educationcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[2]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(1);
	}
	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnEducationAddDocuments_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
}
