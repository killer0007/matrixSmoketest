package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import userLogin.Pages;

public class LoginPage {
	protected final WebDriver driver;
	protected final Pages pages;
public LoginPage(WebDriver driver) {
	this.driver=driver;
	pages=new Pages(driver);
}
public void Login(String uname, String pass) {
	//pages=new Pages(driver);
	pages.Utill().find("txtUsername").sendKeys(uname);
	pages.Utill().find("//*[@id='txtPassword']").sendKeys(pass);
	pages.Utill().find("btnLogin").click();
}
public void Logout() {
	//pages=new Pages(driver);
	pages.Utill().find("ctl00_btnLogout").click();
	WebDriverWait w=new WebDriverWait(driver, 10);
	w.until(ExpectedConditions.presenceOfElementLocated(By.id("cOK")));
	pages.Utill().find("cOK").click();
}
}
