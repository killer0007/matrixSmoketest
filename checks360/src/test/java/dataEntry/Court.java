package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Court extends DataEntryPage{
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Court(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void courtcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[8]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(7);
	}

	
}
