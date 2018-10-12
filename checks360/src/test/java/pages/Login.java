package pages;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public class Login {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Login Page
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Login(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	/**
	 * Takes User name as input and pass it to user name field
	 * 
	 * @param uname Login user name
	 */
	public void setUserName(String uname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtUserName", uname);
	}

	/**
	 * Takes Password as input and pass it to password field
	 * 
	 * @param pass Login password
	 */
	public void setPassword(String pass) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtPassword", pass);
	}

	/**
	 * Performs click action in Login button
	 */
	public void clickLogin() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnLogin");
	}

	/**
	 * Takes user name as input and compares with logged in account name
	 * 
	 * @param name user name
	 * @return boolean true means valid user
	 */
	public boolean verifyProjectName(String name) {
		if (pages.Utill().get_text("ctl00_lblHeader").equals(name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check checks 360 logo is valid image or not
	 * 
	 * @return true when valid image
	 */
	public boolean verifyLogo() {
		return pages.Utill().isimage("http://192.168.2.17:97/Images/c360_logo_green.png");
	}

	/**
	 * Performs click action on forgot password link
	 */
	public void clickForgorpassword() {
		pages.Utill().click_link("Forgot Password");
	}

	/**
	 * Takes user name and password as input and complete the login
	 * 
	 * @param name user name
	 * @param pass password
	 */
	public void userLogin(String name, String pass) {
		this.setUserName(name);
		this.setPassword(pass);
		this.clickLogin();
		pages.Utill().wait_until_loader_is_invisible(40);
	}

}
