package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Criminal extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for Criminal page in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Criminal(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	/**
	 * Select Criminal tab and switch to Criminal frame
	 */
	public void criminalcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[6]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(5);
	}
	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCriminalDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
}
