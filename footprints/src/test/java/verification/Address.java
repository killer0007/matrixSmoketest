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

public class Address extends Verification {
	public Address(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	public void addresscheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[1]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Address']");
		pages.Utill().SwitchFramebyIndex(0);
	}

	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnAddressDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes Document type as input Perform download action on document
	 * 
	 * @param doctype Type Of document
	 */
	public void downloaddoc(String doctype) {
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[4]/input";
		if (this.isvaliddoctype(doctype)) {
			pages.Utill().click(path);
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 * @throws Exception webdriverException
	 */
	public void Component(String component) throws Exception {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlComponent_Input");
		if (!value.trim().equals(component)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill().click(
						"//div[@id='ctl00_ContentPlaceHolder1_ddlComponent_DropDown']/div/ul//li[text()='" + component
								+ "']");
			} else {
				throw new NotFoundException(component);
			}
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) throws Exception {
		Thread.sleep(2000);
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlComponent_DropDown']/div/ul/li"));
//		System.out.println("lenght is :"+list.size());
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
//				System.out.println("success : "+t+"  -  "+i);
				if (t.equals(component)) {
					re = true;
					break;
				} else {
					continue;
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
	 * @param address address line 1
	 */
	public void AddressLine1(String address) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlAddressState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlAddressCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressPincode", pincode);
	}
	
	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandMark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressToDate_dateInput", date);
	}

	/**
	 * Perform click action on till date
	 */
	public void TillDate() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkTillDate");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * choose to select the rented yes or not
	 * 
	 * @param option true or false
	 */
	public void isRented(boolean option) {
		if (option)
			pages.Utill().click("ctl00_ContentPlaceHolder1_rbResidenceType_0");
		else
			pages.Utill().click("ctl00_ContentPlaceHolder1_rbResidenceType_1");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes landlord name as input
	 * 
	 * @param name land lard name
	 */
	public void Name(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandLordName", name);
	}

	/**
	 * Takes input as landlord address
	 * 
	 * @param address landlord address
	 */
	public void LandlordAddress(String address) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandLordAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void LandLordState() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlLandLordState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void LandLordCity() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlLandLordCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to LandLord pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void LandLordPincode(String pincode) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtLandLordPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to LandLord Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandLordLandMark(String Landmark) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtLandLordLandmark", Landmark);
	}

	/**
	 * Takes contact number as input and pass it to landlord contact number
	 * 
	 * @param number contact number
	 */
	public void ContactNo(String number) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandLordContactNo", number);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressComments", comments);
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkAddressInsuff");
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressInsuffRemark", comments);
	}

	/**
	 * click submit button on address data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnAddressSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnAddressAdd_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnAddDocument_input");
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
		boolean re = false;
		pages.Utill().waitUntilElementHasText(
				"//*[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00__0']/td[2]", 10);
		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']/tbody/tr/td[2]";
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

	public void RespondentName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressRespondentName", name);
	}

	public String RespondentName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressRespondentName");
	}

	public void RelationshipToCandidate(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressRespondentDesignation", relationship);
	}

	public String RelationshipToCandidate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressRespondentDesignation");
	}

	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressVerifierComments", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressVerifierTypeofRevert_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressDateOfVerification");
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
	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlComponent_Input");
	}

	public String AddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressAddress");
	}

	public String Country() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressCountry_Input");
	}

	public String getState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressState_Input");
	}

	public String getCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressCity_Input");
	}

	public String Pincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressPincode");
	}

	public String Landmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandMark");
	}

	public String FromDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressFromDate_dateInput");
	}

	public String ToDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressToDate_dateInput");
	}

	public String LandLordName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordName");
	}

	public String LandLordAddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordAddress");
	}

	public String LandLordCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlLandLordCountry_Input");
	}

	public String getLandLordState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlLandLordState_Input");
	}

	public String getLandLordCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlLandLordCity_Input");
	}

	public String LandLordPincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtLandLordPincode");
	}

	public String LandLordLandmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtLandLordLandmark");
	}

	public String LandLordContactNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordContactNo");
	}

	public String Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtAddressComments");
	}
	
	public void Verification() throws Exception {
		Properties pro = pages.Utill().veridata("address");
		this.addresscheck();
		this.document();
		String name = this.Component();
		if(name.equals("Current Address")) {
			this.UploadDocument("Verification Report", pro.getProperty("currentAddressproof"));
		}
		else if (name.equals("Permanent")){
			this.UploadDocument("Verification Report", pro.getProperty("PerAddressproof"));
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
		this.addresscheck();
		this.Component("Current Address");
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
	public Map<String, String> PermanentAdress() throws Exception{
		this.addresscheck();
		this.Component("Permanent");
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
	public Map<String, String> filedata() throws Exception{
		String date=pages.Utill().getCurrentDate("dd/MM/yyyy");
		String component=this.Component();
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().veridata("address");
		if(component.equals("Current Address")) {
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
		map.put("currentAddressproof", new File(pro.getProperty("currentAddressproof")).getName().replaceAll(" ", ""));
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
		}
		else if(component.equals("Permanent")) {
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
		}
		else
			throw new NotFoundException();
	}
}
