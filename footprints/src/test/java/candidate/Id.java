package candidate;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Id extends dataEntryQC.Id{

	public Id(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	public void idcheck() {
		super.SwitchDefault();
		if (!this.getTitle().equals("Identity")) {
			super.click("linkText:Voter ID");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",10);
		}
		super.SwitchFramebyIndex(0);
	}

	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}

	public void viewUpload() {
		super.click("ctl00_ContentPlaceHolder1_btnIdAddDocuments_input");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",100);
	}

	public void docclose() {
		super.click("ctl00_ContentPlaceHolder1_rwmCaseIDDocuments_C_btnIdDocumentCancel");
		super.sleep(400);
	}

	public void saveNext() {
		try {
			super.click("ctl00_ContentPlaceHolder1_btnIdSaveAndNext_input");
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
			logger.log(Status.WARNING, e.getMessage().toString());
			super.sleep(300);
			super.click("ctl00_ContentPlaceHolder1_btnIdSaveAndNext_input");
		}
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",100);
		super.SwitchDefault();
	}
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmCaseIDDocuments_C_btnIDAddDocument_input");
		waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",50);
	}

	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIDDocuments_C_grdviewIdDocument_ctl00__0']/td[2]", 10);
		boolean re =false;
		String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIDDocuments_C_grdviewIdDocument_ctl00']/tbody/tr/td[2]";
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
	public void AssemblyConstituency(String value) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtasmblycons", value);
	}
	public String AssemblyConstituency() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtasmblycons");
	}
	public void VoterID()throws Exception {
		Properties pro = candidatedata("id");
		this.idcheck();
		this.NameonID(pro.getProperty("NameonID"));
		this.IDNumber(pro.getProperty("IDNumber"));
		this.IssueDate(pro.getProperty("IssueDate"));
		this.ExpiryDate(pro.getProperty("ExpiryDate"));
		this.State();
		this.City();
		this.AssemblyConstituency(pro.getProperty("AssemblyConstituency"));
		this.viewUpload();
		this.UploadDocument("Voter ID - Back", pro.getProperty("Aadhardoc"));
		this.docclose();
		this.saveNext();
	}
	public String getStatusColor() {
		return super.getCssValue("//a[text()='Voter ID']/../following-sibling::td/span", "color");
	}
	public String getStatus() {
		return super.getText("//a[text()='Voter ID']/../following-sibling::td/span");
	}
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCaseIDDocuments_C_grdviewIdDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	public Map<String, String> VoterId() throws Exception{
		this.idcheck();
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("NameonID", this.NameonID());
		map.put("IDNumber", this.IDNumber());
		map.put("IssueDate", this.IssueDate());
		map.put("ExpiryDate", this.ExpiryDate());
		map.put("CountryofIssue", this.CountryofIssue());
		map.put("StateofIssue", this.StateofIssue());
		map.put("CityofIssue", this.CityofIssue());
		map.put("Assembly", this.AssemblyConstituency());
		this.viewUpload();
		map.put("Aadhardoc", this.getDocumentName("Voter ID - Back"));
		this.docclose();
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= candidatedata("id");
		map.put("NameonID", pro.getProperty("NameonID"));
		map.put("IDNumber", pro.getProperty("IDNumber"));
		map.put("IssueDate", pro.getProperty("IssueDate"));
		map.put("ExpiryDate", pro.getProperty("ExpiryDate"));
		map.put("CountryofIssue", pro.getProperty("CountryofIssue"));
		map.put("StateofIssue", pro.getProperty("StateofIssue"));
		map.put("CityofIssue", pro.getProperty("CityofIssue"));
		map.put("Assembly", "assembly");
		map.put("Aadhardoc", new File(pro.getProperty("Aadhardoc")).getName().replaceAll(" ", ""));
		return map;
	}
}
