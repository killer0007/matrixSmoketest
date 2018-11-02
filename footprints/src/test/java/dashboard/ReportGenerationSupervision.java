package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public class ReportGenerationSupervision {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public ReportGenerationSupervision(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger = logger;
		pages = new Pages(driver,logger);
	}
	public void reportGenerationSupervision() {
		String stage=pages.Utill().getValue("ddlAct");
		if(!stage.equals("7")) {
			pages.Utill().selectByValue("ddlAct", "7");
			pages.Utill().waitUntilLoaderisInvisible(50);
		}	
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
	public void  WorkstartFrom(String date) {
		pages.Utill().sendKeys("txtfromdate", date);
	}
	public void  WorkstartTo(String date) {
		pages.Utill().sendKeys("txtTodate", date);
	}
	public void Status(String status) {
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
	public void assigngetnext(String refno) {
		this.Search(refno);
		if(pages.Utill().getSelectedValue("//select[@ng-model='Emp.Priority']").equals("Normal")) {
			pages.Utill().selectByLabel("//select[@ng-model='Emp.Priority']", "High");
			pages.Utill().waitUntilLoaderisInvisible(20);
			pages.Utill().click("xpath:html/body/div[3]/div/div/table/tbody/tr[3]/td/button[1]");
			pages.Utill().waitUntilLoaderisInvisible(20);
			}
			pages.Utill().selectByLabel("Reserverfor", "demoempl");
			pages.Utill().waitUntilLoaderisInvisible(100);
			pages.ReportGeneration().reportGeneration();
			pages.ReportGeneration().GetNext();
			this.reportGenerationSupervision();
			
	}
}
