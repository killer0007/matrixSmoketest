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

public class Employment extends DataEntryQCPage {
	public Employment(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select Employment tab and switch to Employment frame
	 */
	public void employmentcheck() {
		pages.Utill().SwitchDefault();
//	pages.Utill().click("//*[@id='tabStrip']/div/ul/li[3]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Employment']");
		pages.Utill().SwitchFramebyIndex(2);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
		if (!value.trim().equals(component)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill().click(
						"//div[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul//li[text()='"
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
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul/li"));
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
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEmploymentDocumentUpload_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * click submit button on employment data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		int count = driver
				.findElements(
						By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEmploymentComponent_DropDown']/div/ul/li"))
				.size();
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEmploymentSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		// System.out.println("-----------count--------"+count);
		if (count == 2) {
			pages.Utill().SwitchDefault();
		}
		pages.Utill().confirmAlert();
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEmploymentSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkEmploymentInsuff");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEmploymentInsuffRemarks", comments);
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

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_grdviewEmploymentDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill()
				.click("ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_btnEmploymentDocumentClose_input");
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
		pages.Utill().waitUntilElementHasText(
				"//*[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_grdviewEmploymentDocument_ctl00__0']/td[2]",
				10);
		boolean re = false;
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rdwEmploymentAddDocument_C_grdviewEmploymentDocument_ctl00']/tbody/tr/td[2]";
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

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentComponent_Input");
	}

	public String EmployerName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentCompany_Input");
	}

	public String EmployerAddressLIne1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtOfficeDetails");
	}

	public String EmployerCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentCountry_Input");
	}

	public String EmployerState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmployementState_Input");
	}

	public String EmployerCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmployementCity_Input");
	}

	public String EmployerNameasperEmployers() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtnameEmpRec");
	}

	public String EmploymentEmployeeID() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmployeeID");
	}

	public String EmploymentDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmployeeDesignation");
	}

	public String EmploymentDepartment() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEmployeeDepartment");
	}

	public String EmploymentFromDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtFromDate_dateInput");
	}

	public String EmploymentToDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtToDate_dateInput");
	}

	public String EmploymentLastCTC() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtLastSalaryDrawn");
	}

	public String EmploymentCurrency() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCurrencyType_Input");
	}

	public String EmploymentPeriod() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmployeeSalaryType_Input");
	}

	public String EmploymentTypeOfEmployment() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEmploymentType_Input");
	}

	public String PersonName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1Name");
	}

	public String PersonDesignation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1Designation");
	}

	public String PersonRelationship() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlRelationship1_Input");
	}

	public String PersonIfOther() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1IfOthers");
	}

	public String PersonContactNo1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo1");
	}

	public String PersonContactNo2() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1ContactNo2");
	}

	public String PersonEmailID1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID1");
	}

	public String PersonEmailID2() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1EmailID2");
	}

	public String PersonFaxNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtContactPerson1FaxNo1");
	}

	public String ReasonForLeaving() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtReasonOfLeaving");
	}

	public String Reason() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtTerminatedOrLaidOffComments");
	}

	public String Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCommentsIfAny");
	}
	public Map<String, String> CurrentEmp() throws Exception{
		this.Component("Current/Latest Employment");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("cEmployerName", this.EmployerName());
		map.put("cEmployerAddressLIne1", this.EmployerAddressLIne1());
		map.put("cEmployerCountry", this.EmployerCountry());
		map.put("cEmployerState", this.EmployerState());
		map.put("cEmployerCity", this.EmployerCity());
		map.put("cEmployerNameasperEmployers", this.EmployerNameasperEmployers());
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
		map.put("cPersonRelationship", this.PersonRelationship());
		map.put("cPersonContactNo1", this.PersonContactNo1());
		map.put("cPersonContactNo2", this.PersonContactNo2());
		map.put("cPersonEmailID1", this.PersonEmailID1());
		map.put("cPersonEmailID2", this.PersonEmailID2());
		map.put("cPersonFaxNo", this.PersonFaxNo());
		map.put("cReasonForLeaving", this.ReasonForLeaving());
		map.put("cComments", this.Comments());
		this.document();
		map.put("currentemp", this.getDocumentName("Offer Letter"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> PerviousEmp() throws Exception{
		this.Component("Previous Employment");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("Component", this.Component());
		map.put("pEmployerName", this.EmployerName());
		map.put("pEmployerAddressLIne1", this.EmployerAddressLIne1());
		map.put("pEmployerCountry", this.EmployerCountry());
		map.put("pEmployerState", this.EmployerState());
		map.put("pEmployerCity", this.EmployerCity());
		map.put("pEmployerNameasperEmployers", this.EmployerNameasperEmployers());
		map.put("pEmploymentEmployeeID", this.EmploymentEmployeeID());
		map.put("pEmploymentDesignation", this.EmploymentDesignation());
		map.put("pEmploymentDepartment", this.EmploymentDepartment());
		map.put("pEmploymentFromDate", this.EmploymentFromDate());
		map.put("pEmploymentToDate", this.EmploymentToDate());
		map.put("pEmploymentLastCTC", this.EmploymentLastCTC());
		map.put("pEmploymentCurrency", this.EmploymentCurrency());
		map.put("pEmploymentPeriod", this.EmploymentPeriod());
		map.put("pEmploymentTypeOfEmployment", this.EmploymentTypeOfEmployment());
		map.put("pPersonName", this.PersonName());
		map.put("pPersonDesignation", this.PersonDesignation());
		map.put("pPersonRelationship", this.PersonRelationship());
		map.put("pPersonContactNo1", this.PersonContactNo1());
		map.put("pPersonContactNo2", this.PersonContactNo2());
		map.put("pPersonEmailID1", this.PersonEmailID1());
		map.put("pPersonEmailID2", this.PersonEmailID2());
		map.put("pPersonFaxNo", this.PersonFaxNo());
		map.put("pReasonForLeaving", this.ReasonForLeaving());
		map.put("pComments", this.Comments());
		this.document();
		map.put("preemp", this.getDocumentName("Relieving Letter"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata(String component) throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("employment");
		if(component.equals("Current/Latest Employment")) {
		map.put("Component", "Current/Latest Employment");
		map.put("cEmployerName", pro.getProperty("cEmployerName"));
		map.put("cEmployerAddressLIne1", pro.getProperty("cEmployerAddressLIne1"));
		map.put("cEmployerCountry", pro.getProperty("cEmployerCountry"));
		map.put("cEmployerState", pro.getProperty("cEmployerState"));
		map.put("cEmployerCity", pro.getProperty("cEmployerCity"));
		map.put("cEmployerNameasperEmployers", pro.getProperty("cEmployerNameasperEmployers"));
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
		map.put("cPersonRelationship", pro.getProperty("cPersonRelationship"));
		map.put("cPersonContactNo1", pro.getProperty("cPersonContactNo1"));
		map.put("cPersonContactNo2", pro.getProperty("cPersonContactNo2"));
		map.put("cPersonEmailID1", pro.getProperty("cPersonEmailID1"));
		map.put("cPersonEmailID2", pro.getProperty("cPersonEmailID2"));
		map.put("cPersonFaxNo", pro.getProperty("cPersonFaxNo"));
		map.put("cReasonForLeaving", pro.getProperty("cReasonForLeaving"));
		map.put("cComments", pro.getProperty("cComments"));
		map.put("currentemp", new File(pro.getProperty("currentemp")).getName().replaceAll(" ", ""));
		return map;
		}
		
	
	else if(component.equals("Previous Employment")) {
		map.put("Component", "Previous Employment");
		map.put("pEmployerName", pro.getProperty("pEmployerName"));
		map.put("pEmployerAddressLIne1", pro.getProperty("pEmployerAddressLIne1"));
		map.put("pEmployerCountry", pro.getProperty("pEmployerCountry"));
		map.put("pEmployerState", pro.getProperty("pEmployerState"));
		map.put("pEmployerCity", pro.getProperty("pEmployerCity"));
		map.put("pEmployerNameasperEmployers", pro.getProperty("pEmployerNameasperEmployers"));
		map.put("pEmploymentEmployeeID", pro.getProperty("pEmploymentEmployeeID"));
		map.put("pEmploymentDesignation", pro.getProperty("pEmploymentDesignation"));
		map.put("pEmploymentDepartment", pro.getProperty("pEmploymentDepartment"));
		map.put("pEmploymentFromDate", pro.getProperty("pEmploymentFromDate"));
		map.put("pEmploymentToDate", pro.getProperty("pEmploymentToDate"));
		map.put("pEmploymentLastCTC", pro.getProperty("pEmploymentLastCTC"));
		map.put("pEmploymentCurrency", pro.getProperty("pEmploymentCurrency"));
		map.put("pEmploymentPeriod", pro.getProperty("pEmploymentPeriod"));
		map.put("pEmploymentTypeOfEmployment", pro.getProperty("pEmploymentTypeOfEmployment"));
		map.put("pPersonName", pro.getProperty("pPersonName"));
		map.put("pPersonDesignation", pro.getProperty("pPersonDesignation"));
		map.put("pPersonRelationship", pro.getProperty("pPersonRelationship"));
		map.put("pPersonContactNo1", pro.getProperty("pPersonContactNo1"));
		map.put("pPersonContactNo2", pro.getProperty("pPersonContactNo2"));
		map.put("pPersonEmailID1", pro.getProperty("pPersonEmailID1"));
		map.put("pPersonEmailID2", pro.getProperty("pPersonEmailID2"));
		map.put("pPersonFaxNo", pro.getProperty("pPersonFaxNo"));
		map.put("pReasonForLeaving", pro.getProperty("pReasonForLeaving"));
		map.put("pComments", pro.getProperty("pComments"));
		map.put("preemp", new File(pro.getProperty("preemp")).getName().replaceAll(" ", ""));
		return map;
	}
	else
		throw new NotFoundException();
	}
}
