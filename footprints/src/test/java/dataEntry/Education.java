package dataEntry;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;

public class Education extends DataEntryPage {
	/**
	 * This is class for Education page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Education(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Education tab and switch to Education frame
	 */
	public void educationcheck() {
		SwitchDefault();
//		click("//*[@id='tabStrip']/div/ul/li[2]/a/span/span/span");
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Education']");
		SwitchFramebyIndex(1);
	}
	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		click("ctl00_ContentPlaceHolder1_btnEducationAddDocuments_input");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=getValue("ctl00_ContentPlaceHolder1_ddlEducationComponent_Input");
		if(!value.trim().equals(component)) {
		click("ctl00_ContentPlaceHolder1_ddlEducationComponent_Input");
		if (verifyddvalue(component)) {
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
			waitUntilLoaderisInvisible(100);
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
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul/li"));
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
	 * Takes institute name as input and pass it
	 * Anna University
	 * @param name name of institution
	 */
	public void InstituteName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_ddlEducationInstitute_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstitute_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstitute_DropDown']/div/ul//li[text()='"+name+"']");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes Institute address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void InstituteAddressLine1(String address) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationInstituteDoorNoStreet", address);
	}

	/**
	 * select tamil nadu as Institute state
	 */
	public void InstituteState() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlEducationInstituteState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as Institute city
	 */
	public void InstituteCity() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes Board name as input and pass it
	 * Anna University
	 * @param name name of institution
	 */
	public void BoardName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_ddlEducationBoard_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoard_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoard_DropDown']/div/ul//li[text()='"+name+"']");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes Board address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void BoardAddressLine1(String address) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationBoardDoorNoStreet", address);
	}

	/**
	 * select tamil nadu as Board state
	 */
	public void BoardState() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlEducationBoardState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as Board city
	 */
	public void BoardCity() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlEducationBoardCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardCity_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes course name as input pass it to Name Of Course
	 * @param name Name Of Course
	 */
	public void NameOfCourse(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationNameOfCourse",name);
	}
	/**
	 * Takes Major Subject name as input
	 * @param subject Major Subject name
	 */
	public void MajorSubject(String subject) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationTypeOfMajor",subject);
	}
	/**
	 * Takes Program name as input
	 * @param Program name of Program
	 */
	public void TypeOfProgram(String Program) {
		//System.out.println(Program);
		String value=getValue("ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_Input");
		if(!value.equals(Program.trim())) {
		click("ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_DropDown']/div/ul//li[text()='"+Program+"']");
//		waitUntilLoaderisInvisible(100);
		}
	}
	/**
	 * Takes Candidate name as input
	 * @param name name of Candidate
	 */
	public void CandidateNameinCertificate(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationCandidateNameInCertificate", name);
	}
	/**
	 * Takes Enrollment number or registration number as input
	 * @param enrollment registration number
	 */
	public void Enrollment(String enrollment) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationEnrollmentRegisterNo", enrollment);
	}
	/**
	 * Takes percentage or CGPA as input
	 * @param percentage percentage of marks
	 */
	public void Percentage(String percentage) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationCGPA", percentage);
	}
	/**
	 * Takes CommencementYear as input
	 * @param CommencementYear year of college joining
	 */
	public void CourseCommencementYear (String CommencementYear) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationCourseCommenceDate_dateInput", CommencementYear);
	}
	/**
	 * Takes CourseCompletionYear as input
	 * @param CompletionYear year of Course Completion
	 */
	public void CourseCompletionYear(String CompletionYear) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationCourseCompletionDate_dateInput",CompletionYear);
	}
	/**
	 * Takes gap reason as input
	 * @param gapreason reason for gap
	 */
	public void Gap(String gapreason) {
		click("ctl00_ContentPlaceHolder1_rbtnEducationGap_0");
		waitUntilLoaderisInvisible(100);
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationGapReasons", gapreason);
	}
	/**
	 * additional comments
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationAdditionalComments", comments);
	}
	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		click("ctl00_ContentPlaceHolder1_chkEducationInsuff");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtEducationInsuffRemarks", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		click("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
	}
	
	/**
	 * click submit button on education data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		int count=driver.findElements(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul/li")).size();
		click("ctl00_ContentPlaceHolder1_btnEducationSaveSubmit_input");
		waitUntilLoaderisInvisible(100);
		//System.out.println("-----------count--------"+count);
		if(count==2) {
			SwitchDefault();	
		}
//		 
		confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnEducationSave_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_btnEducationAddDocument_input");
		waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		boolean re =false;
	waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_gviewEducationDocument_ctl00__0']/td[2]", 10);
		String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_gviewEducationDocument_ctl00']/tbody/tr/td[2]";
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
		waitUntilLoaderisInvisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		click("ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_btnEducationDocumentCancel_input");
		waitUntilLoaderisInvisible(100);
	}
/**
 * 
 * Takes input from education.properties file and pass it to education data entry of 12th
 * @throws Exception webdriver exception
 */
	public void twelveth() throws Exception {
		this.educationcheck();
		Properties pro = dedata("education");
		this.Component("12th");
		this.InstituteName(pro.getProperty("InstituteName"));
		this.InstituteAddressLine1(pro.getProperty("InstituteAddressLine1"));
		this.BoardAddressLine1(pro.getProperty("BoardAddressLine1"));
		this.NameOfCourse(pro.getProperty("NameOfCourse"));
		this.MajorSubject(pro.getProperty("MajorSubject"));
		this.TypeOfProgram(pro.getProperty("TypeOfProgram"));
		this.CandidateNameinCertificate(pro.getProperty("CandidateNameinCertificate"));
		this.Enrollment(pro.getProperty("Enrollment"));
		this.Percentage(pro.getProperty("CGPA"));
		this.CourseCommencementYear(FormateDate(pro.getProperty("CourseCommencementYear")));
		this.CourseCompletionYear(FormateDate(pro.getProperty("CourseCompletionYear")));
		this.Comments(pro.getProperty("Comments"));
		this.document();
		this.UploadDocument("Mark Sheet", pro.getProperty("twelveth"));
		this.docclose();
//		this.save();
		this.submit();
	}
	/**
	 * Takes input from education.properties file and pass it to education data entry of UG1
	 * @throws Exception webdriver exception
	 */
	public void UGone() throws Exception {
		this.educationcheck();
		Properties pro = dedata("education");
		this.Component("UG1");
		this.InstituteName(pro.getProperty("UG1InstituteName"));
		this.InstituteAddressLine1(pro.getProperty("UG1InstituteAddressLine1"));
		//this.BoardAddressLine1(pro.getProperty("UG1BoardAddressLine1"));
		this.NameOfCourse(pro.getProperty("UG1NameOfCourse"));
		this.MajorSubject(pro.getProperty("UG1MajorSubject"));
		this.TypeOfProgram(pro.getProperty("UG1TypeOfProgram"));
		this.CandidateNameinCertificate(pro.getProperty("UG1CandidateNameinCertificate"));
		this.Enrollment(pro.getProperty("UG1Enrollment"));
		this.Percentage(pro.getProperty("UG1CGPA"));
		this.CourseCommencementYear(FormateDate(pro.getProperty("UG1CourseCommencementYear")));
		this.CourseCompletionYear(FormateDate(pro.getProperty("UG1CourseCompletionYear")));
		this.Comments(pro.getProperty("UG1Comments"));
		this.document();
		this.UploadDocument("Degree Certificate", pro.getProperty("ugone"));
		this.docclose();
//		this.save();
		this.submit();
	}
}
