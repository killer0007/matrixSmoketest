package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import environment.Pages;

public class CaseRegistration {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public CaseRegistration(WebDriver driver, ExtentTest logger) {
		// super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public void casereg() {
		pages.Utill().select_by_value("ddlAct", "0");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	public String getTitle() {
		return pages.Utill().get_text(".//*[@id='ctl00_ContentPlaceHolder1_divAddVal']/h2/table/tbody/tr/td[1]");
	}

	public void selectClient(String name) throws InterruptedException {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlClient_Input");

		try {
			pages.Utill().click_element("//li[text()='" + name + "']");
		} catch (ElementNotVisibleException e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			Thread.sleep(2000);
			pages.Utill().click_element("//li[text()='" + name + "']");
		} catch (ElementNotInteractableException e) {
			logger.log(Status.WARNING, e.getMessage().toString());
			Thread.sleep(2000);
			pages.Utill().click_element("//li[text()='" + name + "']");
		}
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void selectProject(String name) throws InterruptedException {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlProject_Input");
		Thread.sleep(1000);
		try {
			pages.Utill().click_element("//li[text()='" + name + "']");
		} catch (ElementNotVisibleException e) {
			Thread.sleep(1000);
			pages.Utill().click_element("//li[text()='" + name + "']");
		}
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
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void clickfresher(boolean value) {
		if (value) {
			pages.Utill().executescript(
					"document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_0').checked='checked'");

		} else {
			pages.Utill().executescript(
					"document.getElementById('ctl00_ContentPlaceHolder1_rblFresher_1').checked='checked'");
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
		pages.Utill().click_element("//td[text()='" + name + "']/../td[6]//input");
		pages.Utill().input_text("//td[text()='" + name + "']/../td[6]//textarea", remarks);
	}

	public void raiseInsuff(String name, String comments) {
		pages.Utill().click_element("//td[text()='" + name + "']/../td[7]//input");
		pages.Utill().input_text("//td[text()='" + name + "']/../td[7]//textarea", comments);
	}

	public void cep(String name, String comments, String releasedate) {
		pages.Utill().click_element("//td[text()='" + name + "']/../td[8]//input");
		pages.Utill().input_text("//td[text()='" + name + "']/../td[8]//textarea", comments);
		pages.Utill().input_text(
				"//td[text()='" + name + "']/../td[8]//tbody/tr/td/div/table/tbody/tr/td/span/input[1]", releasedate);
	}

	public void documentupload(String componentName, String file, String doctype) {
		this.clickupload(componentName);
		this.upload(file, doctype);
		this.addDocument();
		this.docupClose();

	}

	public String getDocumentName(String componentName, String doctype) {
		this.clickupload(componentName);
		String docname = pages.Utill()
				.get_text("//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='"
						+ doctype + "']/../td[6]//span");
		this.docupClose();
		return docname.replaceAll("[0-9]", "");

	}

	public void uploadcaseDoc(String doctype, String fileName) {
		this.uploadcaseDoc();
		pages.Utill().wait_until_loader_is_invisible(100);
		if(this.isDoctypeValid(doctype,1)) {
		pages.Utill().input_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='" + doctype
						+ "']/../td[5]//div/ul/li/span/input[2]",
				fileName);
		}
		else
			throw new NotFoundException(doctype);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnAddCaseDocument_input");
		pages.Utill().wait_until_loader_is_invisible(50);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");

	}

	public void deleteComponentdoc(String doctype) throws Exception {
		if(this.isDoctypeValid(doctype,0)) {
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='" + doctype
				+ "']/../td[7]//td[2]/input";
		pages.Utill().click_element(path);
		}
		else 
			throw new NotFoundException(doctype);
		pages.Utill().confirmAlert();
		pages.Utill().wait_until_loader_is_invisible(50);

	}

	public void deleteCasedoc(String doctype) throws Exception {
		if(this.isDoctypeValid(doctype,1)) {
		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='"
				+ doctype + "']/../td[6]//td[2]/input";
		pages.Utill().click_element(path);
		}
		else 
			throw new NotFoundException(doctype);
		pages.Utill().confirmAlert();
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	public String getuploadcaseDoc(String doctype) {
		this.uploadcaseDoc();
		pages.Utill().wait_until_loader_is_invisible(100);
		//
		String docname=null;
		if(this.isDoctypeValid(doctype,1)) {
		docname = pages.Utill()
				.get_text("//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']//td[text()='"
						+ doctype + "']/../td[5]//span");
		}
		else 
			throw new NotFoundException(doctype);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwCaseDocument_C_btnCaseDocumentCancel");
		return docname.replaceAll("[0-9]", "");
	}

	public void upload(String file, String doctype) {
//		pages.Utill().input_text("//td[text()='" + doctype + "']/following-sibling::td[3]//@value='Select'", file);
		if(this.isDoctypeValid(doctype,0)) {
		pages.Utill()
				.input_text("//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']//td[text()='"
						+ doctype + "']/../td[6]//input[2]", file);
		}
		else throw new NotFoundException(doctype);
	}

	public void clickupload(String componentName) {
		pages.Utill().click_element("//td[text()='" + componentName + "']/../td[10]//input[1]");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void addDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnAddDocument_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void uploadcaseDoc() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCaseDocument_input");
	}

	public void docupCancel() {
		pages.Utill().click_element("//a[@class='rwCloseButton']");

	}

	public void docupClose() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnDocumentCancel");

	}

	public void save() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSave_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void submit() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSaveSubmit_input");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void cancel() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCancel");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	public void gender(String gender) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlGender_Input");
		Thread.sleep(1000);
		if (gender.equalsIgnoreCase("male")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Male'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[2]");
		} else if (gender.equalsIgnoreCase("female")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']//text()='Female'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[3]");
		} else {
			throw new NotFoundException(gender);
		}
	}

	public void maritalStatus(String status) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
		Thread.sleep(2000);
		if (status.equalsIgnoreCase("Single")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Single'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[2]");
		} else if (status.equalsIgnoreCase("married")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Married'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[3]");
		} else if (status.equalsIgnoreCase("divorced")) {
			// pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']//text()='Divorced'");
			pages.Utill().click_element("//*[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[4]");
		}

		else {
			throw new NotFoundException(status);
		}
	}

	public List<String> getDisplayedComponents() {
		List<String> data = new ArrayList<String>();
		List<WebElement> loc = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_grdComponentDetails_ctl00']/tbody/tr/td[3]"));
		for (int i = 0; i < loc.size(); i++) {
			String name = loc.get(i).getText().toString().trim();
			if (name.length() != 0) {
				data.add(name);
			} else {
				continue;
			}
		}
		return data;
	}

	public void registercase(HashMap<String, String> data, boolean fresher) throws Exception {
		CaseRegistration casereg = pages.CaseRegistration();
		casereg.selectClient(data.get("ClientName"));
		casereg.selectProject(data.get("ProjectName"));
		casereg.FirstName(data.get("CandidateName"));
		casereg.LastName(data.get("lastname"));
		casereg.DOB(pages.Utill().getdob());
		casereg.gender("male");
		casereg.Email(data.get("CandidateName") + "@ggmail.com");
		casereg.FatherFirstName("fname");
		casereg.FatherLastName("lname");
		casereg.LinkedIn(data.get("CandidateName") + " linkedin");
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

	public void registercase(HashMap<String, String> data) throws Exception {
		CaseRegistration casereg = pages.CaseRegistration();
		casereg.selectClient(data.get("ClientName"));
		casereg.selectProject(data.get("ProjectName"));
		casereg.FirstName(data.get("CandidateName"));
		casereg.LastName(data.get("lastname"));
		casereg.DOB(data.get("DateofBirth"));
		casereg.gender("male");
		casereg.Email(data.get("CandidateName") + "@ggmail.com");
		casereg.FatherFirstName("fname");
		casereg.FatherLastName("lname");
		casereg.LinkedIn(data.get("CandidateName") + " linkedin");
		casereg.maritalStatus("Single");
		casereg.Nationality("Indian");
		casereg.LandlineNumber(pages.Utill().mobileno());
		casereg.MobileNumber(pages.Utill().mobileno());
		casereg.EmergencyContactNumber(pages.Utill().mobileno());
		casereg.EmergencyContactPerson(data.get("CandidateName"));
		casereg.CandidateID(data.get("CandidateId"));
		casereg.clickfresher(false);
		casereg.addEditComponent();
	}

	public int getCheckCount() {
		return Integer.parseInt(pages.Utill().get_text("ctl00_ContentPlaceHolder1_lblComponentCount"));
	}

	public boolean isSelected(String componentname) {
		return pages.Utill().isSelected("//td[text()='" + componentname + "']/../td[2]//input");
	}

	public boolean isEnabled(String componentname) {
		return pages.Utill().isEnabled("//td[text()='" + componentname + "']/../td[2]//input");
	}
// 1 for case documents
	public boolean isDoctypeValid(String doctype, int b) {
		String path="";
		if(b==1) {
			path = "//table[@id='ctl00_ContentPlaceHolder1_rwCaseDocument_C_grdCaseDocument_ctl00']/tbody/tr/td[2]";	
		}
		else {
			path = "//table[@id='ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdDocumentList_ctl00']/tbody/tr/td[3]";	
		}
		
		List<WebElement> list = driver.findElements(By.xpath(path));
//		System.out.println("length is  : "+list.size());
		if (list.size() > 0) {
			List<String> doc = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				doc.add(list.get(i).getText().trim());
//				System.out.println(list.get(i).getText().trim());
			}
			if (doc.contains(doctype)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
