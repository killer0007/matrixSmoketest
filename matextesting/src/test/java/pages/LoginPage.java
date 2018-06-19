package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import userLogin.Pages;

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
	//pages=new Pages(driver);
	//pages.Utill().find("//*[@id='txtUsername']").sendKeys(uname);
	pages.Utill().input_text("//*[@id='txtUsername']", uname);
	//pages.Utill().find("//*[@id='txtPassword']").sendKeys(pass);
	pages.Utill().input_text("//*[@id='txtPassword']", pass);
	//pages.Utill().find("btnLogin").click();
	pages.Utill().click_element("btnLogin");
}
public void Logout() {
	//pages=new Pages(driver);
	pages.Utill().find("ctl00_btnLogout").click();
	WebDriverWait w=new WebDriverWait(driver, 10);
	w.until(ExpectedConditions.presenceOfElementLocated(By.id("cOK")));
	pages.Utill().find("cOK").click();
}
}
