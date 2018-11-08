package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
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
	public VerificationSupervisor(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger = logger;
		pages = new Pages(driver,logger);
	}

	/**
	 * select Case Registration from stages dropdown
	 */
	public void verificationsupervisor() {
		pages.Utill().selectByValue("ddlAct", "5");
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
	public void  WorkstartFrom(String date) {
		pages.Utill().sendKeys("txtfromdate", date);
	}
	public void  WorkstartTo(String date) {
		pages.Utill().sendKeys("txtTodate", date);
	}
	public void Status(String status) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void Checks(String check) {
		String value=pages.Utill().getSelectedValue("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']");
		if(!value.equals(check)) {
			pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']", check);
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}
	public void Components(String component) {
		String value=pages.Utill().getSelectedValue("//select[@ng-model='PanelHomePageModal.ddlComponentModal']");
		if(!value.equals(component)) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlComponentModal']", component);
		}
	}
	public void Workflow(String flow) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlAssignmentModeModal']", flow);
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void Search() {
		pages.Utill().click("btnsearch");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void Search(String refno) {
		this.CaseRefNo(refno);
		this.Search();
	}
	/**
	 * Performs click action in assign button
	 */
	public void assign() {
		pages.Utill().click("btnBulkAllocation");
		pages.Utill().waitUntilLoaderisInvisible(60);

	}

	public void Search(String refno, String check,String component) {
		this.CaseRefNo(refno);
		this.Checks(check);
		this.Components(component);
		this.Search();
	}
	public void assigngetnext(String refno, String check,String component) {
		this.Search(refno, check, component);
		if(pages.Utill().getSelectedValue("//select[@ng-model='Emp.Priority']").equals("Normal")) {
			pages.Utill().selectByLabel("//select[@ng-model='Emp.Priority']", "High");
			pages.Utill().waitUntilLoaderisInvisible(20);
			pages.Utill().click("xpath:html/body/div[3]/div/div/table/tbody/tr[3]/td/button[1]");
			pages.Utill().waitUntilLoaderisInvisible(20);
			}
			pages.Utill().selectByLabel("Reserverfor", "demoempl");
			pages.Utill().waitUntilLoaderisInvisible(100);
			pages.Verification().verification();
			pages.Verification().GetNext();
			this.verificationsupervisor();
			
	}
	
	public void assign(String refno, String epmName) {
		this.Search(refno);
		pages.Utill().click("chkboxallselect");
		this.assign();
		pages.Utill().click("ddlFilteredTMforAllocation");
		pages.Utill().click(".//*[@id='ddlFilteredTMforAllocation']//option[contains(text(),'"+epmName+"')]");
		pages.Utill().click("//div[@class='modal-dialog modal-lg']//button[text()='Ok']");
		pages.Utill().sleep(1000);
		pages.Utill().click("//div[@class='modal-content']//button[contains(text(),'Yes')]");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
}
