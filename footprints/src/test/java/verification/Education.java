package verification;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Education extends dataEntryQC.Education implements Verification {
	/**
	 * This is class for Education page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Education(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	

	/**
	 * click submit button on education data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {

		click("ctl00_ContentPlaceHolder1_btnEducationSaveSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}


	public void RespondentName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationNameOftheRespondent", name);
	}

	public String RespondentName() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationNameOftheRespondent");
	}

	public void RespondentDesignation(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationRespondentDesignation", relationship);
	}

	public String RespondentDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationRespondentDesignation");
	}
	public void RespondentContactNo(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationRespondentContactNoEmailID", relationship);
	}

	public String RespondentContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationRespondentContactNoEmailID");
	}
	
	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationVerifierRemarks", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationVerifierRemarks");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationVerifierMark_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationDateOfVerification", date);
	}

	public String DateOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}
	public void Verification() throws Exception {
		Properties pro = veridata("education");
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
		this.DateOfInitiation(getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
	public Map<String, String> gettwelveth() throws Exception{
		this.educationcheck();
		this.Component("12th");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("InstituteName", this.InstituteName());
//		map.put("InstituteAddressLine1", this.InstituteAddressLine1());
		map.put("InstituteCountry", this.InstituteCountry());
		map.put("InstituteState", this.getInstituteState());
		map.put("InstituteCity", this.getInstituteCity());
		map.put("BoardName", this.BoardName());
//		map.put("BoardAddressLine1", this.BoardAddressLine1());
		map.put("BoardCountry", this.BoardCountry());
		map.put("BoardState", this.getBoardState());
		map.put("BoardCity", this.getBoardCity());
		map.put("NameOfCourse", this.NameOfCourse());
		map.put("MajorSubject", this.MajorSubject());
		map.put("TypeOfProgram", this.TypeOfProgram());
		map.put("CandidateNameinCertificate", this.CandidateNameinCertificate());
		map.put("Enrollment", this.Enrollment());
		map.put("CGPA", this.CGPA());
		map.put("CourseCommencementYear", this.CourseCommencementYear());
		map.put("CourseCompletionYear", this.CourseCompletionYear());
		map.put("Comments", this.Comments());
		this.document();
		map.put("twelveth", this.getDocumentName("Verification Report"));
		this.docclose();
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
	public Map<String, String> getugone() throws Exception{
		this.educationcheck();
		this.Component("UG1");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("UG1InstituteName", this.InstituteName());
//		map.put("UG1InstituteAddressLine1", this.InstituteAddressLine1());
		map.put("UG1InstituteCountry", this.InstituteCountry());
		map.put("UG1InstituteState", this.getInstituteState());
		map.put("UG1InstituteCity", this.getInstituteCity());
		map.put("UG1BoardName", this.BoardName());
		map.put("UG1BoardAddressLine1", this.BoardAddressLine1());
		map.put("UG1BoardCountry", this.BoardCountry());
		map.put("UG1BoardState", this.getBoardState());
		map.put("UG1BoardCity", this.getBoardCity());
		map.put("UG1NameOfCourse", this.NameOfCourse());
		map.put("UG1MajorSubject", this.MajorSubject());
		map.put("UG1TypeOfProgram", this.TypeOfProgram());
		map.put("UG1CandidateNameinCertificate", this.CandidateNameinCertificate());
		map.put("UG1Enrollment", this.Enrollment());
		map.put("UG1CGPA", this.CGPA());
		map.put("UG1CourseCommencementYear", this.CourseCommencementYear());
		map.put("UG1CourseCompletionYear", this.CourseCompletionYear());
		map.put("UG1Comments", this.Comments());
		this.document();
		map.put("ugone", this.getDocumentName("Verification Report"));
		this.docclose();
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
		String date=getCurrentDate("dd/MM/yyyy");
		String component=this.Component();
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= veridata("education");
		if(component.equals("12th")) {
		map.put("Component", "12th");
		map.put("InstituteName", pro.getProperty("InstituteName"));
//		map.put("InstituteAddressLine1", pro.getProperty("InstituteAddressLine1"));
		map.put("InstituteCountry", pro.getProperty("InstituteCountry"));
		map.put("InstituteState", pro.getProperty("InstituteState"));
		map.put("InstituteCity", pro.getProperty("InstituteCity"));
		map.put("BoardName", pro.getProperty("BoardName"));
//		map.put("BoardAddressLine1", pro.getProperty("BoardAddressLine1"));
		map.put("BoardCountry", pro.getProperty("BoardCountry"));
		map.put("BoardState", pro.getProperty("BoardState"));
		map.put("BoardCity", pro.getProperty("BoardCity"));
		map.put("NameOfCourse", pro.getProperty("NameOfCourse"));
		map.put("MajorSubject", pro.getProperty("MajorSubject"));
		map.put("TypeOfProgram", pro.getProperty("TypeOfProgram").trim());
		map.put("CandidateNameinCertificate", pro.getProperty("CandidateNameinCertificate"));
		map.put("Enrollment", pro.getProperty("Enrollment"));
		map.put("CGPA", pro.getProperty("CGPA"));
		map.put("CourseCommencementYear", pro.getProperty("CourseCommencementYear"));
		map.put("CourseCompletionYear", pro.getProperty("CourseCompletionYear"));
		map.put("Comments", pro.getProperty("Comments"));
		map.put("twelveth", new File(pro.getProperty("twelveth")).getName().replaceAll(" ", ""));
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
		
	
	else if(component.equals("UG1")) {
		map.put("Component", "UG1");
		map.put("UG1InstituteName", pro.getProperty("UG1InstituteName"));
//		map.put("UG1InstituteAddressLine1", pro.getProperty("UG1InstituteAddressLine1"));
		map.put("UG1InstituteCountry", pro.getProperty("UG1InstituteCountry"));
		map.put("UG1InstituteState", pro.getProperty("UG1InstituteState"));
		map.put("UG1InstituteCity", pro.getProperty("UG1InstituteCity"));
		map.put("UG1BoardName", pro.getProperty("UG1BoardName"));
		map.put("UG1BoardAddressLine1", pro.getProperty("UG1BoardAddressLine1"));
		map.put("UG1BoardCountry", pro.getProperty("UG1BoardCountry"));
		map.put("UG1BoardState", pro.getProperty("UG1BoardState"));
		map.put("UG1BoardCity", pro.getProperty("UG1BoardCity"));
		map.put("UG1NameOfCourse", pro.getProperty("UG1NameOfCourse"));
		map.put("UG1MajorSubject", pro.getProperty("UG1MajorSubject"));
		map.put("UG1TypeOfProgram", pro.getProperty("UG1TypeOfProgram").trim());
		map.put("UG1CandidateNameinCertificate", pro.getProperty("UG1CandidateNameinCertificate"));
		map.put("UG1Enrollment", pro.getProperty("UG1Enrollment"));
		map.put("UG1CGPA", pro.getProperty("UG1CGPA"));
		map.put("UG1CourseCommencementYear", pro.getProperty("UG1CourseCommencementYear"));
		map.put("UG1CourseCompletionYear", pro.getProperty("UG1CourseCompletionYear"));
		map.put("UG1Comments", pro.getProperty("UG1Comments"));
		map.put("ugone", new File(pro.getProperty("ugone")).getName().replaceAll(" ", ""));
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
	else
		throw new NotFoundException();
	}
	public void ReportComments() {
		click("ctl00_ContentPlaceHolder1_btnEducationAddComments_input");
		waitUntilLoaderisInvisible(100);
	}
	public void CloseReportComments() {
		click("ctl00_ContentPlaceHolder1_ModalClose_input");
		sleep(500);
	}
	@Override
	public void UpdateReportComments() {
		int count=driver.findElements(By.xpath("//*[@id='accordion']/div")).size();
		
		for (int i = 1; i < count-1; i++) {
			if(i>1) {
				click("//*[@id='accordion']/div["+Integer.toString(i)+"]//b");	
			}		
			String info=getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[1]/span");
			sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[2]//div[3]/div/p/..", info);
			String info2=getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[3]/span");
			sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[4]//div[3]/div/p/..", info2);
		}
	}
	

	
}
