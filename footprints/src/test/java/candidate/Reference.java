package candidate;

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

public class Reference extends dataEntryQC.Reference {

	public Reference(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	public void referencecheck() {
		super.SwitchDefault();
		if (!this.getTitle().equals("Reference")) {
			super.click("linkText:Reference 1");
			super.waitUntilLoaderisInvisible(10);
		}
		super.SwitchFramebyIndex(0);
	}

	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}

	public void viewUpload() {
		super.click("ctl00_ContentPlaceHolder1_btnRefDocument_input");
		super.waitUntilLoaderisInvisible(100);
	}

	public void docclose() {
		super.click("ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_btnDocumentClose_Ref");
		super.sleep(300);
	}

	public void saveNext() {
		super.click("ctl00_ContentPlaceHolder1_btnRefSaveSubmit_input");
		super.waitUntilLoaderisInvisible(100);
		super.SwitchDefault();
	}
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_btnAddDocument_Ref_input");
		waitUntilLoaderisInvisible(30);
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
		}
		else {
			throw new NotFoundException(doctype);
		}
	}
	public void ReferenceType(String type) {
		if(type.equals("Academic")) {
			boolean re =super.isSelected("ctl00_ContentPlaceHolder1_rbtReferenceType_0");
			if(!re) {
				super.click("ctl00_ContentPlaceHolder1_rbtReferenceType_0");
				super.waitUntilLoaderisInvisible(50);
			}
		}
		else if(type.equals("Professional")) {
			boolean re =super.isSelected("ctl00_ContentPlaceHolder1_rbtReferenceType_1");
			if(!re) {
				super.click("ctl00_ContentPlaceHolder1_rbtReferenceType_1");
				super.waitUntilLoaderisInvisible(50);
			}
		}
	}
	
	public void RefEmailId(String emailid) {
		sendKeys("ctl00_ContentPlaceHolder1_txtRefEmailId1", emailid);
	}
	public void	RefContactNo(String contactno) {
		sendKeys("ctl00_ContentPlaceHolder1_txtContactNo", contactno);
	}
	public void referenceone() throws Exception {
		Properties pro = candidatedata("reference");
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
		this.viewUpload();
		this.UploadDocument("Others", pro.getProperty("refonedoc"));
		this.docclose();
		this.saveNext();
	}
	public String getStatusColor() {
		return super.getCssValue("//a[text()='Reference 1']/../following-sibling::td/span", "color");
	}
	public String getStatus() {
		return super.getText("//a[text()='Reference 1']/../following-sibling::td/span");
	}
	public String ReferrerContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactNo");
	}
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rdwRefAddDocument_C_grdDocumentList_Ref_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	public Map<String, String> Referenceone() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		this.referencecheck();
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
		this.viewUpload();
		map.put("refonedoc", this.getDocumentName("Others"));
		this.docclose();
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= candidatedata("reference");
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
		map.put("refonedoc", new File(pro.getProperty("refonedoc")).getName().replaceAll(" ", ""));
		return map;
		}
}
