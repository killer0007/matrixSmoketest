package verification;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Court extends Verification {
	/**
	 * This is class for Court page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Court(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select Court tab and switch to Court frame
	 */
	public void courtcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[8]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Court']");
		pages.Utill().SwitchFramebyIndex(7);
	}

	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCourtDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
		if (!value.trim().equals(component)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill()
						.click("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul//li[text()='"
								+ component + "']");
				pages.Utill().waitUntilLoaderisInvisible(100);
			} else {
				throw new NotFoundException(component);
			}
		}
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) {
//		pages.Utill().sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul/li[1]")));
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
					break;
				} else {
					re = false;
				}
			}
			return re;
		} else {
			return false;
		}

	}

	/**
	 * Takes address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void AddressLine1(String address) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCourtState_Input", "Tamil Nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtState_DropDown']/div/ul/li[1]")));
		pages.Utill()
				.click("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCourtCity_Input", "Chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtLandmark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtToDate_dateInput", date);
	}

	/**
	 * Perform click action on till date
	 */
	public void TillDate() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkTillDate");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtComments", comments);
	}

	/**
	 * click submit button on reference data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCourtSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCourtAdd_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCourtDocument_C_btnCourtAddDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes document type as input and checks for given document type available in
	 * upload screen
	 * 
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		pages.Utill().waitUntilElementHasText(
				"//*[@id='ctl00_ContentPlaceHolder1_rwmCourtDocument_C_grdCourtDocumentList_ctl00__0']/td[2]", 10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmCourtDocument_C_grdCourtDocumentList_ctl00']/tbody/tr/td[2]";
		List<WebElement> list = driver.findElements(By.xpath(path));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText().trim();
				logger.log(Status.INFO, t);
				if (t.equals(doctype)) {
					re = true;
					break;
				}
			}
		} else {
			logger.log(Status.FAIL, "no element found");
		}
		return re;
	}

	/**
	 * Takes document type and file as input and uploads the document
	 * 
	 * @param doctype type of document
	 * @param file    file name
	 */
	public void UploadDocument(String doctype, String file) {
		if (this.isvaliddoctype(doctype)) {
			pages.Utill().sendKeys("//*[text()='" + doctype + "']/../td[5]//span/input[2]", file);
			super.WaitforFileUpdate(doctype, file);
			this.AddDocument();
			pages.Utill().waitUntilLoaderisInvisible(100);
		} else {
			throw new NotFoundException(doctype);
		}

	}

	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCourtDocument_C_btnCourtDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {
		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCourtDocument_C_grdCourtDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	public void Proceedings(String name) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlProceeding_Input");
		if (value.equals(name)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlProceeding_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlProceeding_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlProceeding_DropDown']/div/ul//li[text()='" + name + "']");
		}
	}

	public void DateofSearch(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDateofSearch_dateInput", date);
	}

	public void NameofCourt(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCourtName", name);
	}

	public void CourtType(String type) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtType", type);
	}

	public void Jurisdiction(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtJurisdiction", name);
	}

	public void Result(String result) {
		String record = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtResult_Input");
		if (record.equals(result)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCourtResult_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtResult_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtResult_DropDown']/div/ul//li[text()='" + result + "']");
		}
	}

	public void addCourtbtn() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCourtResult_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	public void addCourt() {
		Properties pro = pages.Utill().veridata("court");
		this.Proceedings(pro.getProperty("Proceedings"));
		this.DateofSearch(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.NameofCourt(pro.getProperty("NameofCourt"));
		this.CourtType(pro.getProperty("CourtType"));
		this.Jurisdiction(pro.getProperty("Jurisdiction"));
		this.Result(pro.getProperty("Result"));
		this.addCourtbtn();
	}

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
	}

	public String AddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtAddress");
	}

	public String Country() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtCountry_Input");
	}

	public String getState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtState_Input");
	}

	public String getCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtCity_Input");
	}

	public String Pincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtPincode");
	}

	public String Landmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtLandmark");
	}

	public String FromDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtFromDate_dateInput");
	}

	public String ToDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtToDate_dateInput");
	}

	public String Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtComments");
	}

	public void RespondentName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtRespondentName", name);
	}

	public String RespondentName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtRespondentName");
	}

	public void RelationshipToCandidate(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtRespondentDesignation", relationship);
	}

	public String RelationshipToCandidate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtRespondentDesignation");
	}

	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtVerifierComments", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtVerifierTypeofRevert_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}

	public void Verification() throws Exception {
		Properties pro = pages.Utill().veridata("court");
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
		this.DateOfInitiation(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(pages.Utill().getCurrentDate("dd/MM/yyyy"));
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
		String date = pages.Utill().getCurrentDate("dd/MM/yyyy");
		String component=this.Component();
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("address");
		Properties court= pages.Utill().veridata("court");
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
}
