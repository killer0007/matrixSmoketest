package client;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

abstract class Home {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

public Home(WebDriver driver, ExtentTest logger) {
	this.driver = driver;
	this.logger = logger;
	pages = new Pages(driver, logger);
}
public abstract void Action();

}
