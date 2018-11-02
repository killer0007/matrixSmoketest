package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Reference extends DataEntryQCPage {
	public Reference(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * click submit button on reference data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnRefSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnRefSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_CheckRefReportInsuff");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtRefReportInsuff", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
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
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
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
				"//*[@id='ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_grdDocumentList_Ref_ctl00__0']/td[2]", 10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_grdDocumentList_Ref_ctl00']/tbody/tr/td[2]";
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
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_btnDocumentClose_Ref_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Select Reference tab and switch to Reference frame
	 */
	public void referencecheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[4]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Reference']");
		pages.Utill().SwitchFramebyIndex(3);
	}

	/**
	 * Perform click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnRefDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlRefType_Input");
		if (verifyddvalue(component)) {
			pages.Utill().click("//div[@id='ctl00_ContentPlaceHolder1_ddlRefType_DropDown']/div/ul//li[text()='"
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
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRefType_Input");
	}

	public String ReferenceType() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlReferType_Input");
	}

	public String ReferrerName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtReferenceName");
	}

	public String ReferrerDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtRefDes");
	}

	public String ReferrerContactNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtRefContactNo1");
	}

	public String ReferrerEmailId() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtRefEmailId1");
	}

	public String ReferrerAddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtRefererDoorStreet");
	}

	public String ReferrerCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRefererCountry_Input");
	}

	public String ReferrerState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRefererState_Input");
	}

	public String ReferrerCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRefererCity_Input");
	}

	public String ReferrerPincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtRefererPincode");
	}

	public String Organizationname() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlOrgName_Input");
	}

	public String OrganizationAddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtOrgDoorNo");
	}

	public String OrganizationCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRefCountry_Input");
	}

	public String OrganizationState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRefState_Input");
	}

	public String OrganizationCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRefCity_Input");
	}

	public String OrganizationPinCode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtOrgPincode");
	}

	public String OrganizationLandmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtOrgLandmark");
	}

	public String Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtRefComments");
	}
	public Map<String, String> Referenceone() throws Exception{
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
		map.put("OrganizationState", this.OrganizationState());
		map.put("OrganizationCity", this.OrganizationCity());
		map.put("Comments", this.Comments());
		this.document();
		map.put("refonedoc", this.getDocumentName("Others"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("reference");
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
		return map;
		}
		
}
