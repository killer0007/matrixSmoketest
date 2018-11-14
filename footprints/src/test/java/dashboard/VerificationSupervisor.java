package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class VerificationSupervisor extends Utill{

	/**
	 * This is class for Case Registration Stage
	 * 
	 * @param logger logger instance
	 */
	public VerificationSupervisor(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * select Case Registration from stages dropdown
	 */
	public void verificationsupervisor() {
		selectByValue("ddlAct", "5");
		waitUntilLoaderisInvisible(100);
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
	public void  WorkstartFrom(String date) {
		sendKeys("txtfromdate", date);
	}
	public void  WorkstartTo(String date) {
		sendKeys("txtTodate", date);
	}
	public void Status(String status) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void Checks(String check) {
		String value=getSelectedValue("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']");
		if(!value.equals(check)) {
			selectByLabel("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']", check);
			waitUntilLoaderisInvisible(100);
		}
	}
	public void Components(String component) {
		String value=getSelectedValue("//select[@ng-model='PanelHomePageModal.ddlComponentModal']");
		if(!value.equals(component)) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlComponentModal']", component);
		}
	}
	public void Workflow(String flow) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlAssignmentModeModal']", flow);
		waitUntilLoaderisInvisible(100);
	}
	public void Search() {
		click("btnsearch");
		waitUntilLoaderisInvisible(100);
	}
	public void Search(String refno) {
		this.CaseRefNo(refno);
		this.Search();
	}
	/**
	 * Performs click action in assign button
	 */
	public void assign() {
		click("btnBulkAllocation");
		waitUntilLoaderisInvisible(60);

	}

	public void Search(String refno, String check,String component) {
		this.CaseRefNo(refno);
		this.Checks(check);
		this.Components(component);
		this.Search();
	}
	public void assigngetnext(String refno, String check,String component) {
		this.Search(refno, check, component);
		if(getSelectedValue("//select[@ng-model='Emp.Priority']").equals("Normal")) {
			selectByLabel("//select[@ng-model='Emp.Priority']", "High");
			waitUntilLoaderisInvisible(20);
			click("xpath:html/body/div[3]/div/div/table/tbody/tr[3]/td/button[1]");
			waitUntilLoaderisInvisible(20);
			}
			selectByLabel("Reserverfor", "demoempl");
			waitUntilLoaderisInvisible(100);
			Verification verification=new Verification(driver,logger);
			verification.GetNext();
			this.verificationsupervisor();
			
	}
	
	public void assign(String refno, String epmName) {
		this.Search(refno);
		click("chkboxallselect");
		this.assign();
		click("ddlFilteredTMforAllocation");
		click(".//*[@id='ddlFilteredTMforAllocation']//option[contains(text(),'"+epmName+"')]");
		click("//div[@class='modal-dialog modal-lg']//button[text()='Ok']");
		sleep(1000);
		click("//div[@class='modal-content']//button[contains(text(),'Yes')]");
		waitUntilLoaderisInvisible(100);
	}
}
