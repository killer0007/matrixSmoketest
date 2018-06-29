package testCases;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Utill;
import environment.Wait;
import pages.*;


public class Pages {
	WebDriver driver;
	ExtentTest logger;

	public Pages(WebDriver driver,ExtentTest logger) {
		this.driver = driver;
		this.logger=logger;
	}

	public LoginPage loginpage() {
		LoginPage login = new LoginPage(driver,logger);
		return login;
	}

	public Caseregistration CaseRegistration() {
		Caseregistration casereg = new Caseregistration(driver,logger);
		return casereg;
	}
	public Utill Utill() {
		Utill u = new Utill(driver,logger);
		return u;
	}

	public Wait Wait() {
		Wait w = new Wait(driver,logger);
		return w;
	}
	public DataEntryTM DataEntryTM() {
		DataEntryTM de = new DataEntryTM(driver,logger);
		return de;
	}
	public Assignor Assignor() {
		Assignor as=new Assignor(driver,logger);
		return as;
	}
	
	public OperationTL OperationTL() {
		OperationTL tl=new OperationTL(driver,logger);
		return tl;
	}
	
	public OperationTM OperationTM() {
		OperationTM tm=new OperationTM(driver,logger);
		return tm;
	}
	public ReportTL ReportTL() {
		ReportTL rtl=new ReportTL(driver,logger);
		return rtl;
	}
	public ReportTM ReportTM() {
		ReportTM rtm=new ReportTM(driver,logger);
		return rtm;
	}
}
