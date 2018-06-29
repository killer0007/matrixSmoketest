package pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import testCases.Pages;

public class ReportTM {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;

	public ReportTM(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}
}
