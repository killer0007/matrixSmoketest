package actions;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class CaseOwnerInsuffClear {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public CaseOwnerInsuffClear(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public void caseOwner() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlAct_Input");
		pages.Utill().wait_until_element_isvisible("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[1]", 10);
		pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[1]");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	public void caseSource(String source) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlWorkflowType_Input");
		pages.Utill().wait_until_element_isvisible("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[1]", 10);
		switch (source.toLowerCase()) {
		case "sp":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[1]");
			break;
		case "candidate":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[2]");
			break;
		case "client":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[3]");
			break;
		case "bulk":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[4]");
			break;
		case "iverify":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[5]");
			break;
		default:
			throw new InvalidSelectorException(source + " not found in list");
		}
pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void caserefno(String refno) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxCaseReference", refno);
	}

	public void firstName(String firstname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxFirstName", firstname);
	}

	public void lastName(String lastname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxLastName", lastname);
	}

	public void search() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSearch");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public String getrefNo() {

		return pages.Utill().get_text("//table[@id='ctl00_ContentPlaceHolder1_grdTaskList_ctl00']/tbody/tr[1]/td[5]");
	}

	public void search(String refno, String source) throws Exception {
		this.caserefno(refno);
		this.caseSource(source);
		this.search();

	}

	public void openCase() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdTaskList_ctl00_ctl04_btnClearInsuff");
		pages.Utill().wait_until_loader_is_invisible(60);
	}

	public String getComments(String componentname) {
		return pages.Utill().get_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[27]");
	}

	public String getRaisedFrom(String componentname) {
		return pages.Utill().get_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[29]");
	}

	public void clearComments(String componentname, String comments) {
		this.select(componentname);
		pages.Utill().input_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[30]/input",
				comments);
	}

	private void upload(String componentname) {
		pages.Utill().click_element(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[32]//input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	public void clear() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_btnClear_input");
	}

	public void cancel() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_btnCancel_input");
	}

	private void select(String componentname) {
		pages.Utill().click_element(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[1]//input");
	}

	public String rasiedBy(String componentname) {
		return pages.Utill().get_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[23]");
	}

	private void upload(String doctype, String filename) {
		pages.Utill().input_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwDocumentUpload_C_grdUploadDocuments_ctl00']//td[text()='Mark Sheet']/../td[6]/div/ul//input[1]",
				filename);
		this.addDocument();
	}

	public void upload(String componentname, String doctype, String filename) {
		this.upload(componentname);
		this.upload(doctype, filename);
	}

	private void addDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwDocumentUpload_C_btnSubmitAddedDocument");
		pages.Utill().wait_until_loader_is_invisible(70);
		this.close();
	}

	public void close() {
		pages.Utill().click_element(
				"//div[@id='RadWindowWrapper_ctl00_ContentPlaceHolder1_rdwDocumentUpload']//tr//td//span[contains(text(),'Close')]");
		;
	}
	public void insuffClear(String componentname, String comments, String doctype, String filename) {
		this.insuffClear(componentname, comments);
	
		
	}
public void insuffClear(String componentname, String comments) {
	this.clearComments(componentname, comments);
	this.clear();
	
	}

}
