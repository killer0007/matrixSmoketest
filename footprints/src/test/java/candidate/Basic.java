package candidate;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class Basic extends Utill {

	public Basic(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public void FatherFirstName(String firstName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtFatherFirstName", firstName);
	}

	public void FatherLastName(String lastName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtFatherLastName", lastName);
	}

	public void Fresher(boolean value) {
		if (value) {
			super.click("ctl00_ContentPlaceHolder1_rblFresher_0");
			super.waitUntilLoaderisInvisible(100);
			super.confirmAlert();
		}
	}

	public void MobileNumber(String mobno) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtMobileNumber1", mobno);
	}

	public void LandlineNumber(String no) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtLandLine", no);
	}

	public void EmergencyContactNumber(String no) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtEmergencyContact", no);
	}

	public void EmergencyContactPerson(String name) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtContactPerson", name);
	}

	public void Nationality(String nation) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtNationality", nation);
	}

	public void Gender(String gender) {
		String value = super.getText("ctl00_ContentPlaceHolder1_ddlGender_Input");
		if (!value.equals(gender)) {
			super.click("ctl00_ContentPlaceHolder1_ddlGender_Input");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlGender_DropDown']/div/ul/li[text()='" + gender + "']");
		}
	}

	public void MaritalStatus(String status) {
		String value = super.getText("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
		if (!value.equals(status)) {
			super.click("ctl00_ContentPlaceHolder1_ddlMaritalStatus_Input");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[text()='" + status
					+ "']");
		}

	}

	public void EmplyeeID(String id) {
		String value = super.getText("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		if (!value.equals("Employee ID")) {
			super.click("ctl00_ContentPlaceHolder1_ddlIDType_Input");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlIDType_DropDown']/div/ul/li[text()='Employee ID']");
		}
		super.sendKeys("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
	}

	public void CandidateID(String id) {
		String value = super.getText("ctl00_ContentPlaceHolder1_ddlIDType_Input");
		if (!value.equals("Candidate ID")) {
			super.click("ctl00_ContentPlaceHolder1_ddlIDType_DropDown");
			super.sleep(500);
			super.click(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus_DropDown']/div/ul/li[text()='Candidate ID']");
		}
		super.sendKeys("ctl00_ContentPlaceHolder1_txtClientCandidateID", id);
	}

	public void SaveNext() {
		super.click("ctl00_ContentPlaceHolder1_btnSaveNext_input");
		super.waitUntilLoaderisInvisible(100);
	}
	/**
	 * Tales data from file and completes data entry for basic details
	 */
	public void basic() {
		Properties data = super.candidatedata("basic");
		if(!this.getTitle().equals("Basic Details")) {
			super.click("gvComponentList_ctl00_ctl05_lnkComponent");
			super.waitUntilLoaderisInvisible(10);
		}
		super.SwitchFramebyIndex(0);
		this.FatherFirstName(data.getProperty("FatherFirstName"));
		this.FatherLastName(data.getProperty("FatherLastName"));
		this.MobileNumber(data.getProperty("MobileNumber"));
		this.LandlineNumber(data.getProperty("LandlineNumber"));
		this.EmergencyContactNumber(data.getProperty("EmergencyContactNumber"));
		this.EmergencyContactPerson(data.getProperty("EmergencyContactPerson"));
		this.Gender(data.getProperty("Gender"));
		this.MaritalStatus(data.getProperty("MaritalStatus"));
		this.Nationality(data.getProperty("Nationality"));
		this.EmplyeeID(data.getProperty("EmplyeeID"));
		this.SaveNext();
	}
	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}
}
