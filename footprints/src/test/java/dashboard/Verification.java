package dashboard;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class Verification {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public Verification(ExtentTest logger) {
		
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * select data entry from stages dropdown
	 */
	public void verification() {
		pages.Utill().select_by_value("ddlAct", "6");
		pages.Utill().wait_until_loader_is_invisible(50);
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
		pages.Utill().click_element("Button3");
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
	public void VRInitiateStatus(String status) {
		String value=pages.Utill().getSelectedvalue("//select[@ng-model='PanelHomePageModal.ddlInitiation']");
		if(!value.equals(status)) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlInitiation']", status);
		pages.Utill().wait_until_loader_is_invisible(100);
		}
	}
	public void GetNext() {
		pages.Utill().click_element("btnGetNext");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().click_element("imgHome");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	public void Select(String refno) {
		pages.Utill().click_element("//span[text()='"+refno+"']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	public void CurrentAddress(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Address", "Current Address");
		this.Select(refno);
	}
	public void Permanent(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Address", "Permanent");
		this.Select(refno);
	}
}
