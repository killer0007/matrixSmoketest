package dashboard;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public class ReportGeneration {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public ReportGeneration(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger = logger;
		pages = new Pages(driver,logger);
	}
	public void reportGeneration() {
		String stage=pages.Utill().getValue("ddlAct");
		if(!stage.equals("8")) {
			pages.Utill().selectByValue("ddlAct", "8");
			pages.Utill().waitUntilLoaderisInvisible(50);
		}	
	}
	public void GetNext() {
		pages.Utill().click("btnGetNext");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().click("imgHome");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void CaseRefNo(String refno) {
		pages.Utill().sendKeys("txtCaserefNo", refno);
	}
	
	public void ClientRefNo(String refno) {
		pages.Utill().sendKeys("txtClientrefNo", refno);
	}
	public void  FirstName(String name) {
		pages.Utill().sendKeys("txtFirstName", name);
	}
	public void  LastName(String name) {
		pages.Utill().sendKeys("txtLastName", name);
	}
	public void Client(String clientName) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", clientName);
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void Project(String project) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}
	public void RegisteredBy(String registeredBy) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlWorkflowTypeModal']", registeredBy);
	}
	public void ReportStatus(String status) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void Search() {
		pages.Utill().click("btnsearch");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void Search(String refno) {
		this.CaseRefNo(refno);
		this.Search();
	}
	public void Select(String refno) {
		pages.Utill().click("//span[text()='"+refno+"']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
}
