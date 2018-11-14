package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class ReportGenerationSupervision extends Utill{


	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public ReportGenerationSupervision(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	public void reportGenerationSupervision() {
		String stage=getValue("ddlAct");
		if(!stage.equals("7")) {
			selectByValue("ddlAct", "7");
			waitUntilLoaderisInvisible(50);
		}	
	}
	public void CaseRefNo(String refno) {
		sendKeys("txtCaserefNo", refno);
	}
	
	public void ClientRefNo(String refno) {
		sendKeys("txtClientrefNo", refno);
	}
	public void  FirstName(String name) {
		sendKeys("txtFirstName", name);
	}
	public void  LastName(String name) {
		sendKeys("txtLastName", name);
	}
	public void Client(String clientName) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", clientName);
		waitUntilLoaderisInvisible(100);
	}
	public void Project(String project) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}
	public void RegisteredBy(String registeredBy) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlWorkflowTypeModal']", registeredBy);
	}
	public void ReportStatus(String status) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void  WorkstartFrom(String date) {
		sendKeys("txtfromdate", date);
	}
	public void  WorkstartTo(String date) {
		sendKeys("txtTodate", date);
	}
	public void Status(String status) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void Search() {
		click("btnsearch");
		waitUntilLoaderisInvisible(100);
	}
	public void Search(String refno) {
		this.CaseRefNo(refno);
		this.Search();
	}
	public void assigngetnext(String refno) {
		this.Search(refno);
		if(getSelectedValue("//select[@ng-model='Emp.Priority']").equals("Normal")) {
			selectByLabel("//select[@ng-model='Emp.Priority']", "High");
			waitUntilLoaderisInvisible(20);
			click("xpath:html/body/div[3]/div/div/table/tbody/tr[3]/td/button[1]");
			waitUntilLoaderisInvisible(20);
			}
			selectByLabel("Reserverfor", "demoempl");
			waitUntilLoaderisInvisible(100);
			ReportGeneration rg = new ReportGeneration(driver, logger);
			rg.reportGeneration();
			rg.GetNext();
			this.reportGenerationSupervision();
			
	}
	/**
	 * Performs click action in assign button
	 */
	public void assign() {
		click("btnBulkAllocation");
		waitUntilLoaderisInvisible(60);

	}
	/**
	 * Takes employee Name as input and assign the case to given employee
	 * 
	 * @param empname Employename
	 * @throws Exception employe not found in list
	 */
	public void assign(String empname) throws Exception {
		click("chkboxsingle");
		this.assign();
		//int count = pages.DbConnection().getAssignedCount(empname);
		//selectByLabel("ddlFilteredTMforAllocation", empname + " (" + count + ")");
		click("ddlFilteredTMforAllocation");
		click(".//*[@id='ddlFilteredTMforAllocation']//option[contains(text(),'"+empname+"')]");
		click("//div[@class='modal-dialog modal-lg']//button[text()='Ok']");
		Thread.sleep(1000);
		click("//div[@class='modal-content']//button[contains(text(),'Yes')]");
		waitUntilLoaderisInvisible(100);
	}
	
	public void assign(String refno,String empname) throws Exception {
		this.Search(refno);
		this.assign(empname);
	}
}
