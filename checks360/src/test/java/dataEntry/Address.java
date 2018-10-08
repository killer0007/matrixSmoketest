package dataEntry;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Address extends DataEntryPage{
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Address(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void addresscheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[1]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(0);
	}
	}
