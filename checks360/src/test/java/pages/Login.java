package pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Login {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Login(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger=logger;
		pages = new Pages(driver, logger);
	}

	public void setUserName(String uname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtUserName", uname);
	}

	public void setPassword(String pass) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtPassword", pass);
	}

	public void clickLogin() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnLogin");
	}

	public boolean verifyProjectName(String name) {
		if (pages.Utill().get_text("ctl00_lblHeader").equals(name)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyLogo() {
		return pages.Utill().isimage("http://192.168.2.17:97/Images/c360_logo_green.png");
	}

	public void clickForgorpassword() {
		pages.Utill().click_link("Forgot Password");
	}
	public void userLogin(String name, String pass) {
		this.setUserName(name);
		this.setPassword(pass);
		this.clickLogin();
		pages.Utill().wait_until_loader_is_invisible(40);
	}
	
}
