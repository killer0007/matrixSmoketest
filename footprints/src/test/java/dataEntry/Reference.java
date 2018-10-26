package dataEntry;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Reference extends DataEntryPage {

	/**
	 * This is class for Reference page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Reference(ExtentTest logger) {
		super(logger);
	}
	/**
	 * Select Reference tab and switch to Reference frame
	 */
	public void referencecheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[4]/a/span/span/span");
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li//span[text()='Reference']");
		pages.Utill().SwitchFramebyIndex(3);
	}
	/**
	 * Perform click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnRefDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlRefType_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlRefType_DropDown']/div/ul//li[text()='"
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
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlReferType_Input");
		if(!value.equals(type)) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlReferType_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlReferType_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlReferType_DropDown']/div/ul//li[text()='"+type+"']");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
	}
	/**
	 * Takes name as input and pass it to Referrer name field
	 * @param name referrer name
	 */
	public void RefName(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtReferenceName", name);
	}
	/**
	 * Takes designation as input and pass it to designation field
	 * @param designation referrer designation
	 */
	public void RefDesignation(String designation) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefDes", designation);
	}
	/**
	 * Takes contact no as input and pass it to contact no field
	 * @param contactno referrer contact no
	 */
	public void	RefContactNo(String contactno) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefContactNo1", contactno);
	}
	/**
	 * Takes email id as input and pass it to email id field
	 * @param emailid referrer email id
	 */
	public void RefEmailId(String emailid) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefEmailId1", emailid);
	}
	/**
	 * Takes address id as input and pass it to address line1 field
	 * @param address referrer address
	 */
	public void RefAddressLine1(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefererDoorStreet", address);
	}
	/**
	 * Takes Organization name as input and pass it
	 * Anna University
	 * @param name name of institution
	 */
	public void OrganizationName(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlOrgName_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlOrgName_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlOrgName_DropDown']/div/ul//li[text()='"+name+"']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes Organization address line 1 as input and pass it to address field
	 * 
	 * @param address address of organization
	 */
	public void OrganizationAddressLine1(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtOrgDoorNo", address);
	}

	/**
	 * select tamil nadu as Organization state
	 */
	public void OrganizationState() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlRefState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as Organization city
	 */
	public void OrganizationCity() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlRefCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes landmark as input and pass it to landmark
	 * @param landmark for institute
	 */
	public void LandMark(String landmark) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtOrgLandmark", landmark);
	}
	/**
	 * additional comments
	 * 
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefComments", comments);
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_CheckRefReportInsuff");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefReportInsuff", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
	}

	/**
	 * click cep raise check box
	 */
	public void cep() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_CheckRefReportYTR");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * takes date as input and pass it to cep relieving date date
	 * 
	 * @param date date of relieve
	 */
	public void cepRelievingDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtExpectedRelievingDate_dateInput_wrapper", date);

	}

	/**
	 * Takes comments as input and pass it to cep raise comments
	 * 
	 * @param comments ecp raise comments
	 */
	public void CepComments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefReportYTR", comments);
	}
	/**
	 * select tamil nadu as state
	 */
	public void RefState() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlRefererState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefererState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefererState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void RefCity() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlRefererCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlRefererCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlRefererCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void OrgPincode(String pincode) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtOrgPincode", pincode);
	}
	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void RefPincode(String pincode) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtRefererPincode", pincode);
	}
	/**
	 * click submit button on reference data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnRefSaveSubmit_input");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnRefSave_input");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_btnAddDocument_Ref_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	pages.Utill().wait_element_has_text("//*[@id='ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_grdDocumentList_Ref_ctl00__0']/td[2]", 10);
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
		pages.Utill().input_text("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		super.WaitforFileUpdate(doctype, file);
		this.AddDocument();
		pages.Utill().wait_until_loader_is_invisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_btnDocumentClose_Ref_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

/**
 * Takes input from reference.properties file and pass it to education data entry of reference one
 * @throws Exception webdriver
 */
	public void referenceone() throws Exception {
		Properties pro = pages.Utill().dedata("reference");
		this.referencecheck();
		this.ReferenceType(pro.getProperty("ReferenceType"));
		this.RefName(pro.getProperty("ReferrerName"));
		this.RefDesignation(pro.getProperty("ReferrerDesignation"));
		this.RefContactNo(pro.getProperty("ReferrerContactNo"));
		this.RefEmailId(pro.getProperty("ReferrerEmailId"));
		this.RefAddressLine1(pro.getProperty("ReferrerAddressLine1"));
		this.RefState();
		this.RefCity();
		this.RefPincode(pro.getProperty("ReferrerPincode"));
		this.OrganizationName(pro.getProperty("Organizationname"));
		//this.OrganizationAddressLine1(pro.getProperty("OrganizationAddressLine1"));
		//this.OrgPincode(pro.getProperty("OrganizationPinCode"));
		//this.LandMark(pro.getProperty("OrganizationLandmark"));
		this.document();
		this.UploadDocument("Others", pro.getProperty("refonedoc"));
		this.docclose();
		this.Comments(pro.getProperty("Comments"));
		this.submit();
	}
}
