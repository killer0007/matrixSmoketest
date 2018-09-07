package environment;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Utill;
import pages.*;

public class Pages {
	private WebDriver driver;
	private ExtentTest logger;
	private Login login;
	private Utill utill;
	private Home home;
	CaseRegistration caseregistration;
	public Pages(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}
	
	public Utill Utill() {
		return (utill == null) ? utill = new Utill(driver, logger) : utill;
	}
	public Login Login() {
		return (login == null) ? login = new Login(driver, logger) : login;
	}
	public Home Home() {
		return (home == null) ? home = new Home(driver, logger) : home;
	}
	
	public CaseRegistration CaseRegistration() {
		return (caseregistration == null) ? caseregistration = new CaseRegistration(driver, logger) : caseregistration;
	}
}
