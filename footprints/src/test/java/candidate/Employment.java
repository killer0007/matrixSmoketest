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

public class Employment extends dataEntryQC.Employment {

	public Employment(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public void employmentcheck() {
		super.SwitchDefault();
		if (!this.getTitle().equals("Employment")) {
			super.click("linkText:Current/Latest Employment");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",10);
		}
		super.SwitchFramebyIndex(0);
	}

	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}

	public void viewUpload() {
		super.click("ctl00_ContentPlaceHolder1_btnUploadDocument_input");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",100);
	}

	public void docclose() {
		super.click("ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_btnEmploymentDocumentClose");
		super.sleep(400);
	}

	public void saveNext() {
		try {
			super.click("ctl00_ContentPlaceHolder1_btnEmploymentSaveContinue_input");
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
			logger.log(Status.WARNING, e.getMessage().toString());
			super.sleep(300);
			super.click("ctl00_ContentPlaceHolder1_btnEmploymentSaveContinue_input");
		}
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",100);
		super.SwitchDefault();
	}

	public boolean isvaliddoctype(String doctype) {
		waitUntilElementHasText(
				".//*[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_gviewEmploymentDocument_ctl00__0']/td[2]",
				10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_gviewEmploymentDocument_ctl00']/tbody/tr/td[2]";
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

	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_btnEmploymentAddDocument_input");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",50);
	}

	public void UploadDocument(String doctype, String file) {
		if (this.isvaliddoctype(doctype)) {
			sendKeys("//*[text()='" + doctype + "']/../td[5]//span/input[2]", file);
			super.WaitforFileUpdate(doctype, file);
			this.AddDocument();
		} else {
			throw new NotFoundException(doctype);
		}

	}

	public void currentEmployment() throws Exception {
		Properties pro = candidatedata("employment");
		this.employmentcheck();
		this.CompanyName(pro.getProperty("cEmployerName"));
		this.EmployeeID(pro.getProperty("cEmploymentEmployeeID"));
		this.EmpDesignation(pro.getProperty("cEmploymentDesignation"));
		this.EmpDepartment(pro.getProperty("cEmploymentDepartment"));
		this.EmpFromDate(pro.getProperty("cEmploymentFromDate"));
		this.EmpToDate(pro.getProperty("cEmploymentToDate"));
		this.LastCTC(pro.getProperty("cEmploymentLastCTC"));
		this.Currency(pro.getProperty("cEmploymentCurrency"));
		this.Period(pro.getProperty("cEmploymentPeriod"));
		this.TypeOfEmployment(pro.getProperty("cEmploymentTypeOfEmployment"));
		this.ContactPerson1Name(pro.getProperty("cPersonName"));
		this.ContactPerson1Designation(pro.getProperty("cPersonDesignation"));
		this.Relationship(pro.getProperty("cPersonRelationship"));
		this.ContactPerson1ContactNo1(pro.getProperty("cPersonContactNo1"));
		this.ContactPerson1EmailID1(pro.getProperty("cPersonEmailID1"));
		this.ReasonForLeaving(pro.getProperty("cReasonForLeaving"));
		this.viewUpload();
		this.UploadDocument("Offer Letter", pro.getProperty("currentemp"));
		this.docclose();
		this.saveNext();

	}

	public String getStatusColor() {
		return super.getCssValue("//a[text()='Current/Latest Employment']/../following-sibling::td/span", "color");
	}

	public String getStatus() {
		return super.getText("//a[text()='Current/Latest Employment']/../following-sibling::td/span");
	}
	public String PersonContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo1");
	}
	public String PersonEmailID() {
		return getValue("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID1");
	}
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_gviewEmploymentDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	public Map<String, String> CurrentEmp() throws Exception {
		this.employmentcheck();
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("cEmployerName", this.EmployerName());
		map.put("cEmployerCountry", this.EmployerCountry());
		map.put("cEmployerState", this.EmployerState());
		map.put("cEmployerCity", this.EmployerCity());
		map.put("cEmploymentEmployeeID", this.EmploymentEmployeeID());
		map.put("cEmploymentDesignation", this.EmploymentDesignation());
		map.put("cEmploymentDepartment", this.EmploymentDepartment());
		map.put("cEmploymentFromDate", this.EmploymentFromDate());
		map.put("cEmploymentToDate", this.EmploymentToDate());
		map.put("cEmploymentLastCTC", this.EmploymentLastCTC());
		map.put("cEmploymentCurrency", this.EmploymentCurrency());
		map.put("cEmploymentPeriod", this.EmploymentPeriod());
		map.put("cEmploymentTypeOfEmployment", this.EmploymentTypeOfEmployment());
		map.put("cPersonName", this.PersonName());
		map.put("cPersonDesignation", this.PersonDesignation());
		map.put("cPersonContactNo1", this.PersonContactNo());
		map.put("cPersonEmailID1", this.PersonEmailID());
		map.put("cReasonForLeaving", this.ReasonForLeaving());
		this.viewUpload();
		map.put("currentemp", this.getDocumentName("Offer Letter"));
		this.docclose();
		return map;
	}

	public Map<String, String> filedata() throws Exception {
		Map<String, String> map = new LinkedHashMap<String, String>();
		Properties pro = candidatedata("employment");
		map.put("cEmployerName", pro.getProperty("cEmployerName"));
		map.put("cEmployerCountry", pro.getProperty("cEmployerCountry"));
		map.put("cEmployerState", pro.getProperty("cEmployerState"));
		map.put("cEmployerCity", pro.getProperty("cEmployerCity"));
		map.put("cEmploymentEmployeeID", pro.getProperty("cEmploymentEmployeeID"));
		map.put("cEmploymentDesignation", pro.getProperty("cEmploymentDesignation"));
		map.put("cEmploymentDepartment", pro.getProperty("cEmploymentDepartment"));
		map.put("cEmploymentFromDate", pro.getProperty("cEmploymentFromDate"));
		map.put("cEmploymentToDate", pro.getProperty("cEmploymentToDate"));
		map.put("cEmploymentLastCTC", pro.getProperty("cEmploymentLastCTC"));
		map.put("cEmploymentCurrency", pro.getProperty("cEmploymentCurrency"));
		map.put("cEmploymentPeriod", pro.getProperty("cEmploymentPeriod"));
		map.put("cEmploymentTypeOfEmployment", pro.getProperty("cEmploymentTypeOfEmployment"));
		map.put("cPersonName", pro.getProperty("cPersonName"));
		map.put("cPersonDesignation", pro.getProperty("cPersonDesignation"));
		map.put("cPersonContactNo1", pro.getProperty("cPersonContactNo1"));
		map.put("cPersonEmailID1", pro.getProperty("cPersonEmailID1"));
		map.put("cReasonForLeaving", pro.getProperty("cReasonForLeaving"));
		map.put("currentemp", new File(pro.getProperty("currentemp")).getName().replaceAll(" ", ""));
		return map;
	}

}
