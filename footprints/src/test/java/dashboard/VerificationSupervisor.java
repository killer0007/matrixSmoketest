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
	public void CaseRefNo(String refno) {
		pages.Utill().input_text("txtCaserefNo", refno);
	}
	
	public void ClientRefNo(String refno) {
		pages.Utill().input_text("txtClientrefNo", refno);
	}
	public void  FirstName(String name) {
		pages.Utill().input_text("txtFirstName", name);
	}
	public void  LastName(String name) {
		pages.Utill().input_text("txtLastName", name);
	}
	public void Client(String clientName) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", clientName);
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	public void Project(String project) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}
	public void RegisteredBy(String registeredBy) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlWorkflowTypeModal']", registeredBy);
	}
	public void  WorkstartFrom(String date) {
		pages.Utill().input_text("txtfromdate", date);
	}
	public void  WorkstartTo(String date) {
		pages.Utill().input_text("txtTodate", date);
	}
	public void Status(String status) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void Checks(String check) {
		String value=pages.Utill().getSelectedvalue("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']");
		if(!value.equals(check)) {
			pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']", check);
			pages.Utill().wait_until_loader_is_invisible(100);
		}
	}
	public void Components(String component) {
		String value=pages.Utill().getSelectedvalue("//select[@ng-model='PanelHomePageModal.ddlComponentModal']");
		if(!value.equals(component)) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlComponentModal']", component);
		}
	}
	public void Workflow(String flow) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlAssignmentModeModal']", flow);
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	public void Search() {
		pages.Utill().click_element("btnsearch");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	public void Search(String refno) {
		this.CaseRefNo(refno);
		this.Search();
	}
	public void Search(String refno, String check,String component) {
		this.CaseRefNo(refno);
		this.Checks(check);
		this.Components(component);
		this.Search();
	}
	public void assigngetnext(String refno, String check,String component) {
		this.Search(refno, check, component);
		if(pages.Utill().getSelectedvalue("//select[@ng-model='Emp.Priority']").equals("Normal")) {
			pages.Utill().select_by_label("//select[@ng-model='Emp.Priority']", "High");
			pages.Utill().wait_until_loader_is_invisible(20);
			pages.Utill().click_element("xpath:html/body/div[3]/div/div/table/tbody/tr[3]/td/button[1]");
			pages.Utill().wait_until_loader_is_invisible(20);
			}
			pages.Utill().select_by_label("Reserverfor", "demoempl");
			pages.Utill().wait_until_loader_is_invisible(100);
			pages.Verification().verification();
			pages.Verification().GetNext();
			pages.VerificationSupervisor().verificationsupervisor();
			
	}

	
}
