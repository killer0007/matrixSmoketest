package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLinkedIn", linkedin);
	}

	public void Nationality(String nation) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtNationality", nation);
	}

	public void LandlineNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLandLine", no);
	}

	public void MobileNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtMobileNumberFirst", no);
	}

	public void EmergencyContactNumber(String no) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtMobileNumberSecond", no);
	}

	public void EmergencyContactPerson(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtContactPerson", name);
	}

	public void CandidateID(String id) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
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
//			pages.Utill().click_element("_rfdSkinnedctl00_ContentPlaceHolder1_rblFresher_0");
//			pages.Utill().clear_element_text("ctl00_ContentPlaceHolder1_rblFresher_0");
			pages.Utill().executescript("document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_0').checked='checked'");
			
		} else {
//			pages.Utill().click_element("_rfdSkinnedctl00_ContentPlaceHolder1_rblFresher_1");
			pages.Utill().executescript("document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_1').checked='checked'");
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
		pages.Utill().click_element("//td[text()='" + name + "']/../td[2]//input");
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

	public void gender(String gender) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlGender_Input");
		Thread.sleep(1000);
		if (gender.equalsIgnoreCase("male")) {
			//pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Male'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[2]");
		} else if (gender.equalsIgnoreCase("female")) {
			//pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Female'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[3]");
		} else {
			throw new NotFoundException(gender);
		}
	}

	public void maritalStatus(String status) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
		Thread.sleep(1000);
		if (status.equalsIgnoreCase("Single")) {
//			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Single'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[2]");
		} else if (status.equalsIgnoreCase("married")) {
//			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Married'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[3]");
		} else if (status.equalsIgnoreCase("divorced")) {
//			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Divorced'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[4]");
		}

		else {
			throw new NotFoundException(status);
		}
	}

	public List<String> getcomponentlist() {
		List<String> data = new ArrayList<String>();
		List<WebElement> ele = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_grdComponentDetails_ctl00']/tbody/tr/td[3]"));
		for (int i = 0; i < ele.size(); i++) {
			String name = ele.get(i).getText();
			if (name.length() > 1) {
				data.add(name);
			}
		}
		return data;
	}
	public void registercase(HashMap<String, String> data, boolean fresher) throws Exception {
		CaseRegistration casereg =pages.CaseRegistration();
		casereg.selectClient(data.get("ClientName"));
		casereg.selectProject(data.get("ProjectName"));
		casereg.FirstName(data.get("CandidateName"));
		casereg.LastName(data.get("lastname"));
		casereg.DOB("18/04/1995");
		casereg.gender("male");
		casereg.Email(data.get("CandidateName")+"@ggmail.com");
		casereg.FatherFirstName("fname");
		casereg.FatherLastName("lname");
		casereg.LinkedIn(data.get("CandidateName")+" linkedin");
		casereg.maritalStatus("Single");
		casereg.Nationality("Indian");
		casereg.LandlineNumber(pages.Utill().mobileno());
		casereg.MobileNumber(pages.Utill().mobileno());
		casereg.EmergencyContactNumber(pages.Utill().mobileno());
		casereg.EmergencyContactPerson(data.get("CandidateName"));
		casereg.CandidateID(data.get("CandidateId"));
		casereg.clickfresher(fresher);
		casereg.addEditComponent();
	}
}
