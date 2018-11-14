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

public class Reference extends dataEntryQC.Reference implements Verification {
	/**
	 * This is class for Reference page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Reference(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Reference tab and switch to Reference frame
	 */
	public void referencecheck() {
		SwitchDefault();
//		click("//*[@id='tabStrip']/div/ul/li[4]/a/span/span/span");
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Reference']");
		SwitchFramebyIndex(3);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		click("ctl00_ContentPlaceHolder1_btnRefDocument_input");
		waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		click("ctl00_ContentPlaceHolder1_ddlRefType_Input");
		if (verifyddvalue(component)) {
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlRefType_DropDown']/div/ul//li[text()='"
							+ component + "']");
		} else {
			throw new NotFoundException(component);
		}
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) {

		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlcreditComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
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
	 * Takes reference type as input
	 * @param type of reference
	 */
	public void ReferenceType(String type) {
		String value=getValue("ctl00_ContentPlaceHolder1_ddlReferType_Input");
		if(!value.equals(type)) {
		click("ctl00_ContentPlaceHolder1_ddlReferType_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlReferType_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlReferType_DropDown']/div/ul//li[text()='"+type+"']");
		waitUntilLoaderisInvisible(100);
		}
	}
	/**
	 * Takes name as input and pass it to Referrer name field
	 * @param name referrer name
	 */
	public void RefName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtReferenceName", name);
	}
	/**
	 * Takes designation as input and pass it to designation field
	 * @param designation referrer designation
	 */
	public void RefDesignation(String designation) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefDes", designation);
	}
	/**
	 * Takes contact no as input and pass it to contact no field
	 * @param contactno referrer contact no
	 */
	public void	RefContactNo(String contactno) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefContactNo1", contactno);
	}
	/**
	 * Takes email id as input and pass it to email id field
	 * @param emailid referrer email id
	 */
	public void RefEmailId(String emailid) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefEmailId1", emailid);
	}
	/**
	 * Takes address id as input and pass it to address line1 field
	 * @param address referrer address
	 */
	public void RefAddressLine1(String address) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefererDoorStreet", address);
	}
	/**
	 * Takes Organization name as input and pass it
	 * Anna University
	 * @param name name of institution
	 */
	public void OrganizationName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_ddlOrgName_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlOrgName_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlOrgName_DropDown']/div/ul//li[text()='"+name+"']");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes Organization address line 1 as input and pass it to address field
	 * 
	 * @param address address of organization
	 */
	public void OrganizationAddressLine1(String address) {
		sendKeys("ctl00_ContentPlaceHolder1_txtOrgDoorNo", address);
	}

	/**
	 * select tamil nadu as Organization state
	 */
	public void OrganizationState() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlRefState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as Organization city
	 */
	public void OrganizationCity() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlRefCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefCity_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes landmark as input and pass it to landmark
	 * @param landmark for institute
	 */
	public void LandMark(String landmark) {
		sendKeys("ctl00_ContentPlaceHolder1_txtOrgLandmark", landmark);
	}
	/**
	 * additional comments
	 * 
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefComments", comments);
	}
	/**
	 * select tamil nadu as state
	 */
	public void RefState() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlRefererState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefererState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefererState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void RefCity() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlRefererCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefererCity_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefererCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void OrgPincode(String pincode) {
		sendKeys("ctl00_ContentPlaceHolder1_txtOrgPincode", pincode);
	}
	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void RefPincode(String pincode) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefererPincode", pincode);
	}
	/**
	 * click submit button on reference data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		click("ctl00_ContentPlaceHolder1_btnRefSaveSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnRefSave_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_btnAddDocument_Ref_input");
		waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_grdDocumentList_Ref_ctl00__0']/td[2]", 10);
		boolean re =false;
		String path="//*[@id='ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_grdDocumentList_Ref_ctl00']/tbody/tr/td[2]";
		List<WebElement> list =driver.findElements(By.xpath(path));
		if(list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				String t=list.get(i).getText().trim();
				logger.log(Status.INFO, t);
				if(t.equals(doctype)) {
					re=true;
					break;
				}
			}
		}
		else {
			logger.log(Status.FAIL, "no element found");
		}
		return re;
	}
	/**
	 * Takes document type and file as input and uploads the document
	 * @param doctype type of document
	 * @param file file name
	 */
	public void UploadDocument(String doctype, String file) {
		if(this.isvaliddoctype(doctype)) {
		sendKeys("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		super.WaitforFileUpdate(doctype, file);
		this.AddDocument();
		waitUntilLoaderisInvisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_grdDocumentList_Ref_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		click("ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_btnDocumentClose_Ref_input");
		waitUntilLoaderisInvisible(100);
		
	}
	public String Component() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefType_Input");
	}

	public String ReferenceType() {
		return getValue("ctl00_ContentPlaceHolder1_ddlReferType_Input");
	}

	public String ReferrerName() {
		return getValue("ctl00_ContentPlaceHolder1_txtReferenceName");
	}

	public String ReferrerDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefDes");
	}

	public String ReferrerContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefContactNo1");
	}

	public String ReferrerEmailId() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefEmailId1");
	}

	public String ReferrerAddressLine1() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefererDoorStreet");
	}

	public String ReferrerCountry() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefererCountry_Input");
	}

	public String ReferrerState() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefererState_Input");
	}

	public String ReferrerCity() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefererCity_Input");
	}

	public String ReferrerPincode() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefererPincode");
	}

	public String Organizationname() {
		return getValue("ctl00_ContentPlaceHolder1_ddlOrgName_Input");
	}

	public String OrganizationAddressLine1() {
		return getValue("ctl00_ContentPlaceHolder1_txtOrgDoorNo");
	}

	public String OrganizationCountry() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefCountry_Input");
	}

	public String getOrganizationState() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefState_Input");
	}

	public String getOrganizationCity() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefCity_Input");
	}

	public String OrganizationPinCode() {
		return getValue("ctl00_ContentPlaceHolder1_txtOrgPincode");
	}

	public String OrganizationLandmark() {
		return getValue("ctl00_ContentPlaceHolder1_txtOrgLandmark");
	}

	public String Comments() {
		return getText("ctl00_ContentPlaceHolder1_txtRefComments");
	}
	public void RespondentName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefNameOfRespondent", name);
	}

	public String RespondentName() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefNameOfRespondent");
	}

	public void RespondentDesignation(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefRespondentDesignation", relationship);
	}

	public String RespondentDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefRespondentDesignation");
	}
	public void RespondentContactNo(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefRespondentContactNoEmail", relationship);
	}

	public String RespondentContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefRespondentContactNoEmail");
	}
	
	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefVerifierRemarks", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtRefVerifierRemarks");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlRefVerifierMark_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlRefVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlRefVerifierMark_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlRefVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlRefModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlRefModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlRefModeOfInitiation_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlRefModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_ddlRefModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtModeOfInitiationdate", date);
	}

	public String DateOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_txtModeOfInitiationdate");
	}

	public void ModeOfVerification(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlModeOfVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_ddlModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtModeOfVerificationDate", date);
	}

	public String DateOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_txtModeOfVerificationDate");
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
		Properties pro = veridata("reference");
		this.referencecheck();
		this.document();
		this.UploadDocument("Verification Report", pro.getProperty("refonedoc"));
		this.docclose();
		this.RespondentName(pro.getProperty("RespondentName"));
		this.RespondentContactNo(pro.getProperty("RespondentContactNo"));
		this.RespondentDesignation(pro.getProperty("RespondentDesignation"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.ModeOfInitiation(pro.getProperty("ModeOfInitiation"));
		this.DateOfInitiation(getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
	public Map<String, String> Referenceone() throws Exception{
		this.referencecheck();
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("ReferenceType", this.ReferenceType());
		map.put("ReferrerName", this.ReferrerName());
		map.put("ReferrerDesignation", this.ReferrerDesignation());
		map.put("ReferrerContactNo", this.ReferrerContactNo());
		map.put("ReferrerEmailId", this.ReferrerEmailId());
		map.put("ReferrerAddressLine1", this.ReferrerAddressLine1());
		map.put("ReferrerCountry", this.ReferrerCountry());
		map.put("ReferrerState", this.ReferrerState());
		map.put("ReferrerCity", this.ReferrerCity());
		map.put("ReferrerPincode", this.ReferrerPincode());
		map.put("Organizationname", this.Organizationname());
		map.put("OrganizationCountry", this.OrganizationCountry());
		map.put("OrganizationState", this.getOrganizationState());
		map.put("OrganizationCity", this.getOrganizationCity());
		map.put("Comments", this.Comments());
		this.document();
		map.put("refonedoc", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("RespondentName", this.RespondentName());
		map.put("RespondentDesignation", this.RespondentDesignation());
		map.put("RespondentContactNo", this.RespondentContactNo());
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
		String date =getCurrentDate("dd/MM/yyyy");
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= veridata("reference");
		map.put("Component", "Reference 1");
		map.put("ReferenceType", pro.getProperty("ReferenceType"));
		map.put("ReferrerName", pro.getProperty("ReferrerName"));
		map.put("ReferrerDesignation", pro.getProperty("ReferrerDesignation"));
		map.put("ReferrerContactNo", pro.getProperty("ReferrerContactNo"));
		map.put("ReferrerEmailId", pro.getProperty("ReferrerEmailId"));
		map.put("ReferrerAddressLine1", pro.getProperty("ReferrerAddressLine1"));
		map.put("ReferrerCountry", pro.getProperty("ReferrerCountry"));
		map.put("ReferrerState", pro.getProperty("ReferrerState"));
		map.put("ReferrerCity", pro.getProperty("ReferrerCity"));
		map.put("ReferrerPincode", pro.getProperty("ReferrerPincode"));
		map.put("Organizationname", pro.getProperty("Organizationname"));
		map.put("OrganizationCountry", pro.getProperty("OrganizationCountry"));
		map.put("OrganizationState", pro.getProperty("OrganizationState"));
		map.put("OrganizationCity", pro.getProperty("OrganizationCity"));
		map.put("Comments", pro.getProperty("Comments"));
		map.put("refonedoc", new File(pro.getProperty("refonedoc")).getName().replaceAll(" ", ""));
		map.put("RespondentName", pro.getProperty("RespondentName"));
		map.put("RespondentDesignation", pro.getProperty("RespondentDesignation"));
		map.put("RespondentContactNo", pro.getProperty("RespondentContactNo"));
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
	public void UpdateReportComments() {
		int count=driver.findElements(By.xpath("//*[@id='accordion']/div")).size();
		
		for (int i = 1; i < count-1; i++) {
			if(i>1) {
				click("//*[@id='accordion']/div["+Integer.toString(i)+"]//b");	
			}		
			String info=getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[1]/span");
			sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[2]//div[3]/div/p/..", info);
			String info2=getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[3]/span");
			sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[4]//div[3]/div/p/..", info2);
		}
	}
}
