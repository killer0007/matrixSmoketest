package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class Verification extends Utill{
	

	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public Verification(WebDriver driver, ExtentTest logger) {
		
		super(driver,logger);
	}

	/**
	 * select data entry from stages dropdown
	 */
	public void verification() {
		String stage=getValue("ddlAct");
		if(!stage.equals("6")) {
			selectByValue("ddlAct", "6");
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
		click("Button3");
		waitUntilLoaderisInvisible(100);
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
		String value=getSelectedValue("//select[@ng-model='PanelHomePageModal.ddlInitiation']");
		if(!value.equals(status)) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlInitiation']", status);
		waitUntilLoaderisInvisible(100);
		}
	}
	public void GetNext() {
		click("btnGetNext");
		waitUntilLoaderisInvisible(100);
		click("imgHome");
		waitUntilLoaderisInvisible(100);
	}
	public void Select(String refno) {
		click("//span[text()='"+refno+"']");
		waitUntilLoaderisInvisible(100);
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
	public void VoterID(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "ID", "Voter ID");
		this.Select(refno);
	}
	public void Panel1(String refno) {
		this.VRInitiateStatus("Verification Confirmation Pending");
		this.Search(refno, "Drug & Medical", "Panel1");
		this.Select(refno);
	}
}
