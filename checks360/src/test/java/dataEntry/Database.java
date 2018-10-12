package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Database extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for Database page in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Database(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	/**
	 * Select Database tab and switch to Database frame
	 */
	public void databasecheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[5]/a/span/span/span");
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
}
