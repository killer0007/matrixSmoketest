package dashboard;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class VerificationSupervisor {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Case Registration Stage
	 * 
	 * @param logger logger instance
	 */
	public VerificationSupervisor(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * select Case Registration from stages dropdown
	 */
	public void verificationsupervisor() {
		pages.Utill().select_by_value("ddlAct", "5");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	

}
