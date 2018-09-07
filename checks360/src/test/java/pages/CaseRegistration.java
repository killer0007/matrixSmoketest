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

}
