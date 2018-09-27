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

	
}
