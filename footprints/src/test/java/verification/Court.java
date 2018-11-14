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

public class Court extends dataEntryQC.Court implements Verification {
	/**
	 * This is class for Court page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Court(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}


	/**
	 * click submit button on reference data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnCourtSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnCourtAdd_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}

	
	public void Proceedings(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlProceeding_Input");
		if (value.equals(name)) {
			click("ctl00_ContentPlaceHolder1_ddlProceeding_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlProceeding_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlProceeding_DropDown']/div/ul//li[text()='" + name + "']");
		}
	}

	public void DateofSearch(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDateofSearch_dateInput", date);
	}

	public void NameofCourt(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_ddlCourtName", name);
	}

	public void CourtType(String type) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtType", type);
	}

	public void Jurisdiction(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtJurisdiction", name);
	}

	public void Result(String result) {
		String record = getValue("ctl00_ContentPlaceHolder1_ddlCourtResult_Input");
		if (record.equals(result)) {
			click("ctl00_ContentPlaceHolder1_ddlCourtResult_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtResult_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtResult_DropDown']/div/ul//li[text()='" + result + "']");
		}
	}

	public void addCourtbtn() {
		click("ctl00_ContentPlaceHolder1_btnCourtResult_input");
		waitUntilLoaderisInvisible(100);
	}

	public void addCourt() {
		Properties pro = veridata("court");
		this.Proceedings(pro.getProperty("Proceedings"));
		this.DateofSearch(getCurrentDate("dd/MM/yyyy"));
		this.NameofCourt(pro.getProperty("NameofCourt"));
		this.CourtType(pro.getProperty("CourtType"));
		this.Jurisdiction(pro.getProperty("Jurisdiction"));
		this.Result(pro.getProperty("Result"));
		this.addCourtbtn();
	}

	

	public void RespondentName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtRespondentName", name);
	}

	public String RespondentName() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtRespondentName");
	}

	public void RelationshipToCandidate(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtRespondentDesignation", relationship);
	}

	public String RelationshipToCandidate() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtRespondentDesignation");
	}

	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtVerifierComments", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtDateOfVerification", date);
	}

	public String DateOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}

	public void Verification() throws Exception {
		Properties pro = veridata("court");
		this.courtcheck();
		this.addCourt();
		this.document();
		String name = this.Component();
		if (name.equals("Current Address Court Check")) {
			this.UploadDocument("Verification Report", pro.getProperty("currentAddressproof"));
		} else if (name.equals("Permanent Court Check")) {
			this.UploadDocument("Verification Report", pro.getProperty("perAddressproof"));
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
	public Map<String, String> CurrentAddress() throws Exception{
		this.courtcheck();
		this.Component("Current Address Court Check");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("AddressLine1", this.AddressLine1());
		map.put("Country", this.Country());
		map.put("State", this.getState());
		map.put("City", this.getCity());
		map.put("Pincode", this.Pincode());
		map.put("Landmark", this.Landmark());
		map.put("FromDate", this.FromDate());
		map.put("ToDate", this.ToDate());
		map.put("currentcommments", this.Comments());
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
	public Map<String, String> PermanentAdress() throws Exception{
		this.courtcheck();
		this.Component("Permanent Court Check");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("AddressLine1", this.AddressLine1());
		map.put("Country", this.Country());
		map.put("State", this.getState());
		map.put("City", this.getCity());
		map.put("Pincode", this.Pincode());
		map.put("Landmark", this.Landmark());
		map.put("FromDate", this.FromDate());
		map.put("ToDate", this.ToDate());
		map.put("permanentcomments", this.Comments());
		this.document();
		map.put("perAddressproof", this.getDocumentName("Verification Report"));
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
	public Map<String, String> filedata() throws Exception{
		String date = getCurrentDate("dd/MM/yyyy");
		String component=this.Component();
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("address");
		Properties court= veridata("court");
		if(component.equals("Current Address Court Check")) {
		map.put("Component", "Current Address Court Check");
		map.put("AddressLine1", pro.getProperty("AddressLine1"));
		map.put("Country", pro.getProperty("Country"));
		map.put("State", pro.getProperty("State"));
		map.put("City", pro.getProperty("City"));
		map.put("Pincode", pro.getProperty("Pincode"));
		map.put("Landmark", pro.getProperty("Landmark"));
		map.put("FromDate", pro.getProperty("FromDate"));
		map.put("ToDate", pro.getProperty("ToDate"));
		map.put("currentcommments", court.getProperty("currentcommments"));
		map.put("currentAddressproof", new File(court.getProperty("currentAddressproof")).getName().replaceAll(" ", ""));
		map.put("RespondentName", court.getProperty("RespondentName"));
		map.put("RelationshipToCandidate", court.getProperty("RelationshipToCandidate"));
		map.put("Ver_Comments", court.getProperty("verComments"));
		map.put("ComponentStatus", court.getProperty("ComponentStatus"));
		map.put("ModeOfInitiation", court.getProperty("ModeOfInitiation"));
		map.put("DateOfInitiation", date);
		map.put("ModeOfVerification", court.getProperty("ModeOfVerification"));
		map.put("DateOfVerification", date);
		map.put("ServiceProvider", court.getProperty("ServiceProvider"));
		logger.log(Status.INFO, map.toString());
		return map;
		}
		else if(component.equals("Permanent Court Check")) {
			map.put("Component", "Permanent Court Check");
			map.put("AddressLine1", pro.getProperty("AddressLine1"));
			map.put("Country", pro.getProperty("Country"));
			map.put("State", pro.getProperty("State"));
			map.put("City", pro.getProperty("City"));
			map.put("Pincode", pro.getProperty("Pincode"));
			map.put("Landmark", pro.getProperty("Landmark"));
			map.put("FromDate", pro.getProperty("FromDate"));
			map.put("ToDate", pro.getProperty("ToDate"));
			map.put("permanentcomments", court.getProperty("permanentcomments"));
			map.put("perAddressproof", new File(court.getProperty("perAddressproof")).getName().replaceAll(" ", ""));
			map.put("RespondentName", court.getProperty("RespondentName"));
			map.put("RelationshipToCandidate", court.getProperty("RelationshipToCandidate"));
			map.put("Ver_Comments", court.getProperty("verComments"));
			map.put("ComponentStatus", court.getProperty("ComponentStatus"));
			map.put("ModeOfInitiation", court.getProperty("ModeOfInitiation"));
			map.put("DateOfInitiation", date);
			map.put("ModeOfVerification", court.getProperty("ModeOfVerification"));
			map.put("DateOfVerification", date);
			map.put("ServiceProvider", court.getProperty("ServiceProvider"));
			logger.log(Status.INFO, map.toString());
			return map;
		}
		else
			throw new NotFoundException();
	}
	public void ReportComments() {
		click("ctl00_ContentPlaceHolder1_btnCourtAddComments_input");
		waitUntilLoaderisInvisible(100);
	}
	public void CloseReportComments() {
		click("ModalClose");
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
