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

public class Drug extends Verification {
	public Drug(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	public void drugCheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Drug & Medical']");
		pages.Utill().SwitchFramebyIndex(8);
	}

	public void RespondentName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDrugMedicalRespondentName", name);
	}

	public String RespondentName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDrugMedicalRespondentName");
	}

	public void RespondentDesignation(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDrugMedicalRespondentDesignation", relationship);
	}

	public String RespondentDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDrugMedicalRespondentDesignation");
	}

	public void RespondentContactNo(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtRespondentContactNoEmail", relationship);
	}

	public String RespondentContactNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtRespondentContactNoEmail");
	}

	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDrugMedicalVerifierComments", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDrugMedicalVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalVerifierMark_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlDrugMedicalVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalVerifierMark_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlDrugMedicalModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDrugMedicalDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDrugMedicalDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlDrugMedicalOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDrugMedicalDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDrugMedicalDateOfVerification");
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

	public void Component(String name) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalComponent_Input");
		if (!value.equals(name.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlDrugMedicalComponent_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalComponent_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//*[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalComponent_DropDown']/div/ul//li[text()='"
							+ name + "']");
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalComponent_Input");
	}

	public void DrugTest(String name) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalElement_Input");
		if (!value.equals(name.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlDrugMedicalElement_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalElement_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click("//*[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalElement_DropDown']/div/ul//li[text()='"
							+ name + "']");
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	public String DrugTest() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalElement_Input");
	}

	public void Result(String name) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalResult_Input");
		if (!value.equals(name.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlDrugMedicalResult_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalResult_DropDown']/div/ul/li[1]")));
			pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_ddlDrugMedicalResult_DropDown']/div/ul//li[text()='"
					+ name + "']");
			pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	public String Result() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlDrugMedicalResult_Input");
	}

	public void Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtDrugMedicalResultComments", comments);
	}

	public String Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtDrugMedicalResultComments");
	}
	public void add() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnDrugMedicalAdd_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void addDrug() {
		Properties pro = pages.Utill().veridata("drug");
		this.Component("Panel1");
		this.DrugTest(pro.getProperty("DrugTest"));
		this.Result(pro.getProperty("Result"));
		this.Comments(pro.getProperty("Comments"));
		this.add();
	}
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnDrugMedicalDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
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
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmDurgMedicalDocuments_C_btnDrugMedicalDocumentAddDocument_input");
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
				"//*[@id='ctl00_ContentPlaceHolder1_rwmDurgMedicalDocuments_C_gviewDrugMedicalDocuments_ctl00__0']/td[2]",
				10);
		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmDurgMedicalDocuments_C_gviewDrugMedicalDocuments_ctl00']/tbody/tr/td[2]";
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
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmDurgMedicalDocuments_C_btnDrugMedicalDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * click submit button on education data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnDrugMedicalSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnDrugMedicalSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmDurgMedicalDocuments_C_gviewDrugMedicalDocuments_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	public void Verification() throws Exception {
		Properties pro = pages.Utill().veridata("drug");
		this.drugCheck();
		this.addDrug();
		this.document();
		this.UploadDocument("Verification Report", pro.getProperty("panel1"));
		this.docclose();
		this.RespondentName(pro.getProperty("RespondentName"));
		this.RespondentContactNo(pro.getProperty("RespondentContactNo"));
		this.RespondentDesignation(pro.getProperty("RespondentDesignation"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.ModeOfInitiation(pro.getProperty("ModeOfInitiation"));
		this.DateOfInitiation(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
	public Map<String, String> drug() throws Exception{
		this.drugCheck();
		Map<String , String> map=new LinkedHashMap<String, String>();
		this.document();
		map.put("panel1", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("DrugTest", pages.Utill().getText("//tr[@id='ctl00_ContentPlaceHolder1_gviewDrugCheckElement_ctl00__0']/td[3]"));
		map.put("Result", pages.Utill().getText("//tr[@id='ctl00_ContentPlaceHolder1_gviewDrugCheckElement_ctl00__0']/td[4]"));
		map.put("Comments", pages.Utill().getText("//tr[@id='ctl00_ContentPlaceHolder1_gviewDrugCheckElement_ctl00__0']/td[5]"));
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
		String date=pages.Utill().getCurrentDate("dd/MM/yyyy");
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().veridata("drug");
		map.put("panel1", new File(pro.getProperty("panel1")).getName().replaceAll(" ", ""));
		map.put("DrugTest", pro.getProperty("DrugTest"));
		map.put("Result", pro.getProperty("Result"));
		map.put("Comments", pro.getProperty("Comments"));
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
}
