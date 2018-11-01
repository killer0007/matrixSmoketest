package dashboard;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
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
	public Verification(WebDriver driver, ExtentTest logger) {
		
		this.driver=driver;
		this.logger = logger;
		pages = new Pages(driver,logger);
	}

	/**
	 * select data entry from stages dropdown
	 */
	public void verification() {
		String stage=pages.Utill().getValue("ddlAct");
		if(!stage.equals("6")) {
			pages.Utill().selectByValue("ddlAct", "6");
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
		pages.Utill().click("Button3");
		pages.Utill().waitUntilLoaderisInvisible(100);
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
		String value=pages.Utill().getSelectedValue("//select[@ng-model='PanelHomePageModal.ddlInitiation']");
		if(!value.equals(status)) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlInitiation']", status);
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}
	public void GetNext() {
		pages.Utill().click("btnGetNext");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().click("imgHome");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void Select(String refno) {
		pages.Utill().click("//span[text()='"+refno+"']");
		pages.Utill().waitUntilLoaderisInvisible(100);
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
	public void twelveth(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Education", "12th");
		this.Select(refno);
	}
	public void UGone(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Education", "UG1");
		this.Select(refno);
	}
	public void CurrentEmployment(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Employment", "Current/Latest Employment");
		this.Select(refno);
	}
	public void PreviousEmployment(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Employment", "Previous Employment");
		this.Select(refno);
	}
	public void Reference(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Reference", "Reference 1");
		this.Select(refno);
	}
	public void Database(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Database", "Database");
		this.Select(refno);
	}
	public void CurrentAddressCriminalCheck(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Criminal", "Current Address Criminal Check");
		this.Select(refno);
	}
	public void PermanentCriminalCheck(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Criminal", "Permanent Criminal Check");
		this.Select(refno);
	}
	public void CurrentAddressCourtCheck(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Court", "Current Address Court Check");
		this.Select(refno);
	}
	public void PermanentCourtCheck(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Court", "Permanent Court Check");
		this.Select(refno);
	}
	public void Credit(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Credit", "Credit Check 1");
		this.Select(refno);
	}
	public void Passport(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "ID", "Passport");
		this.Select(refno);
	}
	public void AadhaarCard(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "ID", "Aadhaar Card");
		this.Select(refno);
	}
	public void Panel1(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Drug & Medical", "Panel1");
		this.Select(refno);
	}
}
