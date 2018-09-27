package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Database extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Database(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void databasecheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[5]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(4);
	}

	
}
