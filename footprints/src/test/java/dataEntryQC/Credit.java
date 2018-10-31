package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Credit extends DataEntryQCPage{
	/**
	 * This is class for Credit page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Credit(ExtentTest logger) {
		super(logger);
	}
	/**
	 * Select Credit tab and switch to Credit frame
	 */
	public void creditcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[7]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Credit']");
		pages.Utill().SwitchFramebyIndex(6);
	}
	/**
	 * Performs click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCreditAddDocuments_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
		if(!value.trim().equals(component)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlcreditComponent_DropDown']/div/ul//li[text()='"
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
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCreditId_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='"
							+ component + "']");
			pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * click submit button on credit data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCreditSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();	
		pages.Utill().confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCreditSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkCreditInsuff");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCreditInsuffRemarks", comments);
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
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00__0']/td[2]", 10);
		boolean re =false;
		String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00']/tbody/tr/td[2]";
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
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_btnCreditDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
	}

	public String ID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditId_Input");
	}

	public String NameonID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdName");
	}

	public String IDNumber() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdNumber");
	}

	public String IssueDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdIssueDate_dateInput");
	}

	public String ExpiryDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditIdExpireDate_dateInput");
	}

	public String CountryofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_Input");
	}

	public String StateofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueState_Input");
	}

	public String CityofIssue() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCreditIssueCity_Input");
	}

	public String EnrollmentNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditEnrollId1");
	}

	public String comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCreditComments");
	}
	
	public LinkedHashMap<String, String> credit() throws Exception{
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("ID", this.ID());
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
	public LinkedHashMap<String, String> filedata() throws Exception{
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("credit");
		map.put("Component", "Credit Check 1");
		map.put("ID", pro.getProperty("ID"));
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
}
