package client;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

abstract class Home {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

public Home(ExtentTest logger) {
	
	driver = BaseClass.getWebDriver();
	this.logger = logger;
	pages = new Pages(logger);
}
public abstract void Action();

}
