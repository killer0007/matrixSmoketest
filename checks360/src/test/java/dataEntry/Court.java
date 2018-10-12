package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Court extends DataEntryPage{
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for Court page in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Court(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	/**
	 * Select Court tab and switch to Court frame
	 */
	public void courtcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[8]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(7);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCourtDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
}
