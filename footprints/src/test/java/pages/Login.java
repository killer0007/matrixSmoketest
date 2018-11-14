package pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class Login extends Utill {
	ExtentTest logger;

	/**
	 * This is class for Login Page
	 * 
	 * @param logger logger instance
	 */
	public Login(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Takes User name as input and pass it to user name field
	 * 
	 * @param uname Login user name
	 */
	public void setUserName(String uname) {
		sendKeys("ctl00_ContentPlaceHolder1_txtUserName", uname);
	}

	/**
	 * Takes Password as input and pass it to password field
	 * 
	 * @param pass Login password
	 */
	public void setPassword(String pass) {
		sendKeys("ctl00_ContentPlaceHolder1_txtPassword", pass);
	}

	/**
	 * Performs click action in Login button
	 */
	public void clickLogin() {
		click("ctl00_ContentPlaceHolder1_btnLogin");
	}

	/**
	 * Takes user name as input and compares with logged in account name
	 * 
	 * @param name user name
	 * @return boolean true means valid user
	 */
	public boolean verifyProjectName(String name) {
		if (getText("ctl00_lblHeader").equals(name)) {
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
		return isImage("http://192.168.2.17:97/Images/c360_logo_green.png");
	}

	/**
	 * Performs click action on forgot password link
	 */
	public void clickForgorpassword() {
		clickLink("Forgot Password");
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
		waitUntilLoaderisInvisible(40);
	}

}
