package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Reference extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Reference(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void referencecheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[4]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(3);
	}
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnRefDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
}
