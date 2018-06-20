package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import testCases.Pages;

public class LoginPage {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
public LoginPage(WebDriver driver,ExtentTest logger) {
	this.driver=driver;
	this.logger=logger;
	pages=new Pages(driver,logger);
}
public void Login(String uname, String pass) throws Exception {
	pages.Utill().input_text("//*[@id='txtUsername']", uname);
	pages.Utill().input_text("//*[@id='txtPassword']", pass);
	pages.Utill().click_element("btnLogin");
}
public void Logout() throws Exception {
	//pages=new Pages(driver);
	pages.Utill().click_element("ctl00_btnLogout");
	WebDriverWait w=new WebDriverWait(driver, 10);
	w.until(ExpectedConditions.presenceOfElementLocated(By.id("cOK")));
	pages.Utill().click_element("cOK");
}
}
