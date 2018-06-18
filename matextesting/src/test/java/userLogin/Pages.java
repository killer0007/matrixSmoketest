package userLogin;

import org.openqa.selenium.WebDriver;

import environment.Utill;
import environment.Wait;
import pages.*;


public class Pages {
	WebDriver driver;

	public Pages(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage loginpage() {
		LoginPage login = new LoginPage(driver);
		return login;
	}

	public Caseregistration CaseRegistration() {
		Caseregistration casereg = new Caseregistration(driver);
		return casereg;
	}
	public Utill Utill() {
		Utill u = new Utill(driver);
		return u;
	}

	public Wait Wait() {
		Wait w = new Wait(driver);
		return w;
	}
	public DataEntryTM DataEntryTM() {
		DataEntryTM de = new DataEntryTM(driver);
		return de;
	}
}
