package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Reference extends dataEntry.Reference {
	public Reference(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
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
		return getValue("ctl00_ContentPlaceHolder1_txtContactNo1");
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
		map.put("OrganizationState", this.getOrganizationState());
		map.put("OrganizationCity", this.getOrganizationCity());
		map.put("Comments", this.Comments());
		this.document();
		map.put("refonedoc", this.getDocumentName("Others"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("reference");
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
