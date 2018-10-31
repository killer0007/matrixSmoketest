package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Id extends DataEntryQCPage{
	/**
	 * This is class for Id page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Id(ExtentTest logger) {
		super(logger);
	}
	/**
	 * Select Id tab and switch to Id frame
	 */
	public void idcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[9]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='ID']");
		pages.Utill().SwitchFramebyIndex(9);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdAddDocuments_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
		if(!value.trim().equals(component)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
		pages.Utill().sleep(700);
		if (verifyddvalue(component)) {
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
//			System.err.println(component);
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
		//pages.Utill().sleep(1000);
		pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li[1]", 10);
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li"));
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
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdCheckComponent_Input");
		
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='"
							+ component + "']");
	}
	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkIdInsuff");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_chkComponentNotApplicable", comments);
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
	 * click submit button on id data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		//int count=driver.findElements(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li")).size();
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		//if(count==2) {
			pages.Utill().SwitchDefault();	
		//}
		pages.Utill().confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_btnIdDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00__0']/td[2]", 10);
		boolean re =false;
		String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00']/tbody/tr/td[2]";
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

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
	}

	public String NameonID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdName");
	}

	public String IDNumber() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdNumber");
	}

	public String IssueDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdExpireDate_dateInput");
	}

	public String CountryofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdIssueCountry_Input");
	}

	public String StateofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdIssueState_Input");
	}

	public String CityofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdIssueCity_Input");
	}

	public String EnrollmentNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEnrollId1");
	}

	public String comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdComments");
	}
	public LinkedHashMap<String, String> Aadharcard() throws Exception{
		this.Component("Aadhaar Card");
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("NameonID", this.NameonID());
		map.put("IDNumber", this.IDNumber());
		map.put("IssueDate", this.IssueDate());
		map.put("ExpiryDate", this.ExpiryDate());
		map.put("CountryofIssue", this.CountryofIssue());
		map.put("StateofIssue", this.StateofIssue());
		map.put("CityofIssue", this.CityofIssue());
		map.put("EnrollmentNo", this.EnrollmentNo());
		map.put("comments", this.comments());
		this.document();
		map.put("Aadhardoc", this.getDocumentName("Aadhaar Id - Front"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> PassPort() throws Exception{
		this.Component("Passport");
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("PNameonID", this.NameonID());
		map.put("PIDNumber", this.IDNumber());
		map.put("PIssueDate", this.IssueDate());
		map.put("PExpiryDate", this.ExpiryDate());
		map.put("PCountryofIssue", this.CountryofIssue());
		map.put("PStateofIssue", this.StateofIssue());
		map.put("PCityofIssue", this.CityofIssue());
		map.put("Pcomments", this.comments());
		this.document();
		map.put("passportdoc", this.getDocumentName("Passport Scan - Front"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> filedata(String component) throws Exception{
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("id");
		if(component.equals("Aadhaar Card")) {
		map.put("Component", "Aadhaar Card");
		map.put("NameonID", pro.getProperty("NameonID"));
		map.put("IDNumber", pro.getProperty("IDNumber"));
		map.put("IssueDate", pro.getProperty("IssueDate"));
		map.put("ExpiryDate", pro.getProperty("ExpiryDate"));
		map.put("CountryofIssue", pro.getProperty("CountryofIssue"));
		map.put("StateofIssue", pro.getProperty("StateofIssue"));
		map.put("CityofIssue", pro.getProperty("CityofIssue"));
		map.put("EnrollmentNo", pro.getProperty("EnrollmentNo"));
		map.put("comments", pro.getProperty("comments"));
		map.put("Aadhardoc", new File(pro.getProperty("Aadhardoc")).getName().replaceAll(" ", ""));
		return map;
		}
		else if(component.equals("Passport")) {
			map.put("Component", "Passport");
			map.put("PNameonID", pro.getProperty("PNameonID"));
			map.put("PIDNumber", pro.getProperty("PIDNumber"));
			map.put("PIssueDate", pro.getProperty("PIssueDate"));
			map.put("PExpiryDate", pro.getProperty("PExpiryDate"));
			map.put("PCountryofIssue", pro.getProperty("PCountryofIssue"));
			map.put("PStateofIssue", pro.getProperty("PStateofIssue"));
			map.put("PCityofIssue", pro.getProperty("PCityofIssue"));
			map.put("Pcomments", pro.getProperty("Pcomments"));
			map.put("passportdoc", new File(pro.getProperty("passportdoc")).getName().replaceAll(" ", ""));
			return map;
		}
		else
			throw new NotFoundException();
	}
}
