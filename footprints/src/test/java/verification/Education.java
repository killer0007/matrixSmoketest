package verification;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Education extends Verification {
	/**
	 * This is class for Education page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Education(ExtentTest logger) {
		super(logger);
	}

	/**
	 * Select Education tab and switch to Education frame
	 */
	public void educationcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Education']");
		pages.Utill().SwitchFramebyIndex(1);
	}

	/**
	 * Performs click action on Document button
	 */

	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEducationAddDocuments_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationComponent_Input");
		if (!value.trim().equals(component)) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEducationComponent_Input");
			if (verifyddvalue(component)) {
				pages.Utill().click(
						"//div[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul//li[text()='"
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
		pages.Utill().sleep(1000);
		List<WebElement> list = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
					break;
				}
			}
			return re;
		} else {
			return false;
		}

	}

	/**
	 * Takes institute name as input and pass it Anna University
	 * 
	 * @param name name of institution
	 */
	public void InstituteName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEducationInstitute_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstitute_DropDown']/div/ul/li[1]")));
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstitute_DropDown']/div/ul//li[text()='"
				+ name + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes Institute address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void InstituteAddressLine1(String address) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationInstituteDoorNoStreet", address);
	}

	/**
	 * select tamil nadu as Institute state
	 */
	public void InstituteState() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEducationInstituteState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as Institute city
	 */
	public void InstituteCity() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes Board name as input and pass it Anna University
	 * 
	 * @param name name of institution
	 */
	public void BoardName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEducationBoard_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoard_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoard_DropDown']/div/ul//li[text()='" + name + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes Board address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void BoardAddressLine1(String address) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationBoardDoorNoStreet", address);
	}

	/**
	 * select tamil nadu as Board state
	 */
	public void BoardState() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEducationBoardState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as Board city
	 */
	public void BoardCity() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlEducationBoardCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes course name as input pass it to Name Of Course
	 * 
	 * @param name Name Of Course
	 */
	public void NameOfCourse(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationNameOfCourse", name);
	}

	/**
	 * Takes Major Subject name as input
	 * 
	 * @param subject Major Subject name
	 */
	public void MajorSubject(String subject) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationTypeOfMajor", subject);
	}

	/**
	 * Takes Program name as input
	 * 
	 * @param Program name of Program
	 */
	public void TypeOfProgram(String Program) {
		// System.out.println(Program);
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_Input");
		if (!value.equals(Program.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_DropDown']/div/ul//li[text()='"
							+ Program + "']");
//		pages.Utill().waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes Candidate name as input
	 * 
	 * @param name name of Candidate
	 */
	public void CandidateNameinCertificate(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationCandidateNameInCertificate", name);
	}

	/**
	 * Takes Enrollment number or registration number as input
	 * 
	 * @param enrollment registration number
	 */
	public void Enrollment(String enrollment) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationEnrollmentRegisterNo", enrollment);
	}

	/**
	 * Takes percentage or CGPA as input
	 * 
	 * @param percentage percentage of marks
	 */
	public void Percentage(String percentage) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationCGPA", percentage);
	}

	/**
	 * Takes CommencementYear as input
	 * 
	 * @param CommencementYear year of college joining
	 */
	public void CourseCommencementYear(String CommencementYear) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationCourseCommenceDate_dateInput", CommencementYear);
	}

	/**
	 * Takes CourseCompletionYear as input
	 * 
	 * @param CompletionYear year of Course Completion
	 */
	public void CourseCompletionYear(String CompletionYear) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationCourseCompletionDate_dateInput", CompletionYear);
	}

	/**
	 * Takes gap reason as input
	 * 
	 * @param gapreason reason for gap
	 */
	public void Gap(String gapreason) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rbtnEducationGap_0");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationGapReasons", gapreason);
	}

	/**
	 * additional comments
	 * 
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationAdditionalComments", comments);
	}

	/**
	 * click submit button on education data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {

		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEducationSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEducationSave_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_btnEducationAddDocument_input");
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
				"//*[@id='ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_gviewEducationDocument_ctl00__0']/td[2]",
				10);
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_gviewEducationDocument_ctl00']/tbody/tr/td[2]";
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
	 * Takes document type and file as input and uploads the document
	 * 
	 * @param doctype type of document
	 * @param file    file name
	 */
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
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_btnEducationDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationComponent_Input");
	}

	public String InstituteName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationInstitute_Input");
	}

	public String InstituteAddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationInstituteDoorNoStreet");
	}

	public String InstituteCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationInstituteCountry_Input");
	}

	public String getInstituteState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationInstituteState_Input");
	}

	public String getInstituteCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_Input");
	}

	public String BoardName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationBoard_Input");
	}

	public String BoardAddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationBoardDoorNoStreet");
	}

	public String BoardCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationBoardCountry_Input");
	}

	public String getBoardState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationBoardState_Input");
	}

	public String getBoardCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationBoardCity_Input");
	}

	public String NameOfCourse() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationNameOfCourse");
	}

	public String MajorSubject() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationTypeOfMajor");
	}

	public String TypeOfProgram() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_Input");
	}

	public String CandidateNameinCertificate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationCandidateNameInCertificate");
	}

	public String Enrollment() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationEnrollmentRegisterNo");
	}

	public String CGPA() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationCGPA");
	}

	public String CourseCommencementYear() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationCourseCommenceDate_dateInput");
	}

	public String CourseCompletionYear() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtEducationCourseCompletionDate_dateInput");
	}

	public String Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtEducationAdditionalComments");
	}
	public void RespondentName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationNameOftheRespondent", name);
	}

	public String RespondentName() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtEducationNameOftheRespondent");
	}

	public void RespondentDesignation(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationRespondentDesignation", relationship);
	}

	public String RespondentDesignation() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtEducationRespondentDesignation");
	}
	public void RespondentContactNo(String relationship) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationRespondentContactNoEmailID", relationship);
	}

	public String RespondentContactNo() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtEducationRespondentContactNoEmailID");
	}
	
	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationVerifierRemarks", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtEducationVerifierRemarks");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtEducationDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtEducationDateOfVerification");
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
	public void Verification() throws Exception {
		Properties pro = pages.Utill().veridata("education");
		this.educationcheck();
		this.document();
		String name = this.Component();
		if(name.equals("12th")) {
			this.UploadDocument("Verification Report", pro.getProperty("twelveth"));
		}
		else if (name.equals("UG1")){
			this.UploadDocument("Verification Report", pro.getProperty("ugone"));
		}
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
}
