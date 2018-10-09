package dataEntry;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Criminal extends DataEntryPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Criminal(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void criminalcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[6]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(5);
	}

	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCriminalDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
}
