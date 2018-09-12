package environment;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Utill;
import pages.*;
import dashboard.*;

public class Pages {
	private WebDriver driver;
	private ExtentTest logger;
	private Login login;
	private Utill utill;
	private Home home;
	private CaseRegistration caseregistration;
	private DcaseRegistration dcaseregistration;
	private DbConnection db;
	private CaseTracker casetracker;
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
	public DcaseRegistration DcaseRegistration() {
		return (dcaseregistration == null) ? dcaseregistration = new DcaseRegistration(driver, logger) : dcaseregistration;
	}
	public DbConnection DbConnection() {
		return (db == null) ? db = new DbConnection() : db;
	}

	public CaseTracker CaseTracker() {
		return (casetracker == null) ? casetracker = new CaseTracker(driver, logger) : casetracker;
	}
}
