package verification;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Address extends dataEntryQC.Address implements Verification {
	public Address(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	/**
	 * click submit button on address data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnAddressSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnAddressAdd_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}

	public void RespondentName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressRespondentName", name);
	}

	public String RespondentName() {
		return getValue("ctl00_ContentPlaceHolder1_txtAddressRespondentName");
	}

	public void RelationshipToCandidate(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressRespondentDesignation", relationship);
	}

	public String RelationshipToCandidate() {
		return getValue("ctl00_ContentPlaceHolder1_txtAddressRespondentDesignation");
	}

	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressVerifierComments", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtAddressVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_DropDown']/div/ul//li[text()='"
					+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_DropDown']/div/ul//li[text()='" + mode
					+ "']");
		}
	}

	public String ModeOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_txtAddressDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_DropDown']/div/ul//li[text()='"
					+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressDateOfVerification", date);
	}

	public String DateOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_txtAddressDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
					+ name + "']");
		}
	}

	public String ServiceProvider() {
		return getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}

	public void Verification() throws Exception {
		Properties pro = veridata("address");
		this.addresscheck();
		this.document();
		String name = this.Component();
		if (name.equals("Current Address")) {
			this.UploadDocument("Verification Report", pro.getProperty("currentAddressproof"));
		} else if (name.equals("Permanent")) {
			this.UploadDocument("Verification Report", pro.getProperty("PerAddressproof"));
		}
		this.docclose();
		this.RespondentName(pro.getProperty("RespondentName"));
		this.RelationshipToCandidate(pro.getProperty("RelationshipToCandidate"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.ModeOfInitiation(pro.getProperty("ModeOfInitiation"));
		this.DateOfInitiation(getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}

	public Map<String, String> getCurrentAddress() throws Exception {
		this.addresscheck();
		this.Component("Current Address");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("AddressLine1", this.AddressLine1());
		map.put("Country", this.Country());
		map.put("State", this.getState());
		map.put("City", this.getCity());
		map.put("Pincode", this.Pincode());
		map.put("Landmark", this.Landmark());
		map.put("FromDate", this.FromDate());
		map.put("ToDate", this.ToDate());
		map.put("LandLordName", this.LandLordName());
		map.put("LandLordAddressLine1", this.LandLordAddressLine1());
		map.put("LandLordCountry", this.LandLordCountry());
		map.put("LandLordState", this.getLandLordState());
		map.put("LandLordCity", this.getLandLordCity());
		map.put("LandLordPincode", this.LandLordPincode());
		map.put("LandLordLandmark", this.LandLordLandmark());
		map.put("LandLordContactNo", this.LandLordContactNo());
		map.put("Comments", this.Comments());
		this.document();
		map.put("currentAddressproof", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("RespondentName", this.RespondentName());
		map.put("RelationshipToCandidate", this.RelationshipToCandidate());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ModeOfInitiation", this.ModeOfInitiation());
		map.put("DateOfInitiation", this.DateOfInitiation());
		map.put("ModeOfVerification", this.ModeOfVerification());
		map.put("DateOfVerification", this.DateOfVerification());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}

	public Map<String, String> PermanentAdress() throws Exception {
		this.addresscheck();
		this.Component("Permanent");
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("AddressLine1", this.AddressLine1());
		map.put("Country", this.Country());
		map.put("State", this.getState());
		map.put("City", this.getCity());
		map.put("Pincode", this.Pincode());
		map.put("Landmark", this.Landmark());
		map.put("FromDate", this.FromDate());
		map.put("ToDate", this.ToDate());
		map.put("LandLordName", this.LandLordName());
		map.put("LandLordAddressLine1", this.LandLordAddressLine1());
		map.put("LandLordCountry", this.LandLordCountry());
		map.put("LandLordState", this.getLandLordState());
		map.put("LandLordCity", this.getLandLordCity());
		map.put("LandLordPincode", this.LandLordPincode());
		map.put("LandLordLandmark", this.LandLordLandmark());
		map.put("LandLordContactNo", this.LandLordContactNo());
		map.put("permanentcomments", this.Comments());
		this.document();
		map.put("PerAddressproof", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("RespondentName", this.RespondentName());
		map.put("RelationshipToCandidate", this.RelationshipToCandidate());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ModeOfInitiation", this.ModeOfInitiation());
		map.put("DateOfInitiation", this.DateOfInitiation());
		map.put("ModeOfVerification", this.ModeOfVerification());
		map.put("DateOfVerification", this.DateOfVerification());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}

	public Map<String, String> filedata() throws Exception {
		String date = getCurrentDate("dd/MM/yyyy");
		String component = this.Component();
		Map<String, String> map = new LinkedHashMap<String, String>();
		Properties pro = veridata("address");
		if (component.equals("Current Address")) {
			map.put("Component", "Current Address");
			map.put("AddressLine1", pro.getProperty("AddressLine1"));
			map.put("Country", pro.getProperty("Country"));
			map.put("State", pro.getProperty("State"));
			map.put("City", pro.getProperty("City"));
			map.put("Pincode", pro.getProperty("Pincode"));
			map.put("Landmark", pro.getProperty("Landmark"));
			map.put("FromDate", pro.getProperty("FromDate"));
			map.put("ToDate", pro.getProperty("ToDate"));
			map.put("LandLordName", pro.getProperty("LandLordName"));
			map.put("LandLordAddressLine1", pro.getProperty("LandLordAddressLine1"));
			map.put("LandLordCountry", pro.getProperty("LandLordCountry"));
			map.put("LandLordState", pro.getProperty("LandLordState"));
			map.put("LandLordCity", pro.getProperty("LandLordCity"));
			map.put("LandLordPincode", pro.getProperty("LandLordPincode"));
			map.put("LandLordLandmark", pro.getProperty("LandLordLandmark"));
			map.put("LandLordContactNo", pro.getProperty("LandLordContactNo"));
			map.put("Comments", pro.getProperty("Comments"));
			map.put("currentAddressproof",
					new File(pro.getProperty("currentAddressproof")).getName().replaceAll(" ", ""));
			map.put("RespondentName", pro.getProperty("RespondentName"));
			map.put("RelationshipToCandidate", pro.getProperty("RelationshipToCandidate"));
			map.put("Ver_Comments", pro.getProperty("verComments"));
			map.put("ComponentStatus", pro.getProperty("ComponentStatus"));
			map.put("ModeOfInitiation", pro.getProperty("ModeOfInitiation"));
			map.put("DateOfInitiation", date);
			map.put("ModeOfVerification", pro.getProperty("ModeOfVerification"));
			map.put("DateOfVerification", date);
			map.put("ServiceProvider", pro.getProperty("ServiceProvider"));
			logger.log(Status.INFO, map.toString());
			return map;
		} else if (component.equals("Permanent")) {
			map.put("Component", "Permanent");
			map.put("AddressLine1", pro.getProperty("AddressLine1"));
			map.put("Country", pro.getProperty("Country"));
			map.put("State", pro.getProperty("State"));
			map.put("City", pro.getProperty("City"));
			map.put("Pincode", pro.getProperty("Pincode"));
			map.put("Landmark", pro.getProperty("Landmark"));
			map.put("FromDate", pro.getProperty("FromDate"));
			map.put("ToDate", pro.getProperty("ToDate"));
			map.put("LandLordName", pro.getProperty("LandLordName"));
			map.put("LandLordAddressLine1", pro.getProperty("LandLordAddressLine1"));
			map.put("LandLordCountry", pro.getProperty("LandLordCountry"));
			map.put("LandLordState", pro.getProperty("LandLordState"));
			map.put("LandLordCity", pro.getProperty("LandLordCity"));
			map.put("LandLordPincode", pro.getProperty("LandLordPincode"));
			map.put("LandLordLandmark", pro.getProperty("LandLordLandmark"));
			map.put("LandLordContactNo", pro.getProperty("LandLordContactNo"));
			map.put("permanentcomments", pro.getProperty("permanentcomments"));
			map.put("PerAddressproof", new File(pro.getProperty("PerAddressproof")).getName().replaceAll(" ", ""));
			map.put("RespondentName", pro.getProperty("RespondentName"));
			map.put("RelationshipToCandidate", pro.getProperty("RelationshipToCandidate"));
			map.put("Ver_Comments", pro.getProperty("verComments"));
			map.put("ComponentStatus", pro.getProperty("ComponentStatus"));
			map.put("ModeOfInitiation", pro.getProperty("ModeOfInitiation"));
			map.put("DateOfInitiation", date);
			map.put("ModeOfVerification", pro.getProperty("ModeOfVerification"));
			map.put("DateOfVerification", date);
			map.put("ServiceProvider", pro.getProperty("ServiceProvider"));
			logger.log(Status.INFO, map.toString());
			return map;
		} else
			throw new NotFoundException();
	}

	public void ReportComments() {
		click("ctl00_ContentPlaceHolder1_btnAddressAddComments_input");
		waitUntilLoaderisInvisible(100);
	}

	public void CloseReportComments() {
		click("ctl00_ContentPlaceHolder1_ModalClose_input");
		sleep(500);
	}

	

	@Override
	public void UpdateReportComments() {
		int count = driver.findElements(By.xpath("//*[@id='accordion']/div")).size();

		for (int i = 1; i < count; i++) {
			if (i > 1) {
				click("//*[@id='accordion']/div[" + Integer.toString(i) + "]//b");
			}
			String info = getText("//*[@id='accordion']/div[" + Integer.toString(i) + "]//td[2]/span");
			sendKeys("//*[@id='accordion']/div[" + Integer.toString(i) + "]//div[3]/div/p/..", info);
		}

	}
}
