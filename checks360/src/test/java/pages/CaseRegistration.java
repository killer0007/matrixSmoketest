package pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class CaseRegistration {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public CaseRegistration(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		pages = new Pages(driver, logger);
	}
public String getTitle() {
	return pages.Utill().get_text(".//*[@id='ctl00_ContentPlaceHolder1_divAddVal']/h2/table/tbody/tr/td[1]");
}
	public void selectClient(String name) throws InterruptedException {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlClient_Input");
		Thread.sleep(1000);
		pages.Utill().click_element("//li[text()='" + name + "']");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void selectProject(String name) throws InterruptedException {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlProject_Input");
		Thread.sleep(1000);
		pages.Utill().click_element("//li[text()='" + name + "']");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void FirstName(String fname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFirstName", fname);
	}

	public void LastName(String lname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLastName", lname);
	}

	public void DOB(String dob) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtDateofBirth_dateInput", dob);
	}

	public void FatherFirstName(String fname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFatherFirstName", fname);
	}

	public void FatherLastName(String lname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFatherLastName", lname);
	}

	public void Email(String email) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmail", email);
	}

	public void LinkedIn(String linkedin) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFirstName", linkedin);
	}

	public void Nationality(String nation) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLastName", nation);
	}

	public void LandlineNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtDateofBirth_dateInput", no);
	}

	public void MobileNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFatherFirstName", no);
	}

	public void EmergencyContactNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtFatherLastName", no);
	}

	public void EmergencyContactPerson(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmail", name);
	}

	public void CandidateID(String id) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEmail", id);
	}

	public String getalertcolor(String id, String attribute) {
		String color = pages.Utill().getcssvalue(id, attribute);
		return color;
	}

	public void addEditComponent() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddComponent_input");
		pages.Utill().wait_until_loader_is_invisible(20);
	}

	public void clickfresher(boolean value) {
		if (value) {
			pages.Utill().click_element("_rfdSkinnedctl00_ContentPlaceHolder1_rblFresher_0");
		} else {
			pages.Utill().click_element("_rfdSkinnedctl00_ContentPlaceHolder1_rblFresher_1");
		}
	}

	public String getCaseOwner() {
		return pages.Utill().get_text("ctl00_ContentPlaceHolder1_lblCaseOwnerName");
	}

	public void candidateid() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']//text()='Candidate ID'");
	}

	public void empid() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']//text()='Employee ID'");
	}

	public void addNewCase() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddNext_input");
	}

	public void selectcheck(String name) {
		pages.Utill().click_element("//td[text()='" + name + "']/../td[2]//label");
	}

	public void notApplicable(String name, String remarks) {
		pages.Utill().click_element("//td[text()='" + name + "']/../td[6]//label");
		pages.Utill().input_text("//td[text()='" + name + "']/../td[6]//textarea", remarks);
	}

	public void raiseInsuff(String name, String comments) {
		pages.Utill().click_element("//td[text()='" + name + "']/../td[7]//label");
		pages.Utill().input_text("//td[text()='" + name + "']/../td[7]//textarea", comments);
	}

	public void cep(String name, String comments, String releasedate) {
		pages.Utill().click_element("//td[text()='" + name + "']/../td[8]//label");
		pages.Utill().input_text("//td[text()='" + name + "']/../td[8]//textarea", comments);
		pages.Utill().input_text(
				"//td[text()='" + name + "']/../td[8]//tbody/tr/td/div/table/tbody/tr/td/span/input[1]", releasedate);
	}

	public void documentupload(String name, String file, String doctype) {
		this.clickupload(name);
		this.upload(file, doctype);
		this.addDocument();
		this.docupClose();

	}

	public void upload(String file, String doctype) {
		pages.Utill().input_text("//td[text()='" + doctype + "']/following-sibling::td[3]//@value='Select'", file);
	}

	public void clickupload(String name) {
		pages.Utill().click_element("//td[text()='" + name + "']/../td[10]//input[1]");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void addDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnAddDocument_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void documentupload(String file) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCaseDocument_input");
		// write code for upload
	}

	public void docupCancel() {
		pages.Utill().click_element("//a[@class='rwCloseButton']");

	}

	public void docupClose() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnDocumentCancel");

	}

	public void save() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSave_input");
	}

	public void submit() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSaveSubmit_input");
	}

	public void cancel() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCancel");
	}
}
