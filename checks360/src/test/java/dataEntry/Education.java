package dataEntry;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

public class Education extends DataEntryPage {
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
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[2]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(1);
	}
	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnEducationAddDocuments_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlEducationComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul/li//text()='"
							+ component + "'");
		} else {
			throw new NotFoundException(component);
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
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul/li"));
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
	 * Takes institute name as input and pass it
	 * Anna University
	 * @param name name of institution
	 */
	public void InstituteName(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEducationInstitute_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstitute_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstitute_DropDown']/div/ul/li//text()='"+name+"'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes Institute address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void InstituteAddressLine1(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationInstituteDoorNoStreet", address);
	}

	/**
	 * select tamil nadu as Institute state
	 */
	public void InstituteState() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEducationInstituteState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteState_DropDown']/div/ul/li//text()='Tamil Nadu'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as Institute city
	 */
	public void InstituteCity() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_DropDown']/div/ul/li//text()='Chennai'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes Board name as input and pass it
	 * Anna University
	 * @param name name of institution
	 */
	public void BoardName(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEducationBoard_Input", name);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoard_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoard_DropDown']/div/ul/li//text()='"+name+"'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes Board address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void BoardAddressLine1(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationBoardDoorNoStreet", address);
	}

	/**
	 * select tamil nadu as Board state
	 */
	public void BoardState() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEducationBoardState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardState_DropDown']/div/ul/li//text()='Tamil Nadu'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as Board city
	 */
	public void BoardCity() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEducationBoardCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationBoardCity_DropDown']/div/ul/li//text()='Chennai'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes course name as input pass it to Name Of Course
	 * @param name Name Of Course
	 */
	public void NameOfCourse(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationNameOfCourse",name);
	}
	/**
	 * Takes Major Subject name as input
	 * @param subject Major Subject name
	 */
	public void MajorSubject(String subject) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationTypeOfMajor",subject);
	}
	/**
	 * Takes Program name as input
	 * @param Program name of Program
	 */
	public void TypeOfProgram(String Program) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_Input", Program);
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_DropDown']/div/ul/li//text()='"+Program+"'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes Candidate name as input
	 * @param name name of Candidate
	 */
	public void CandidateNameinCertificate(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationCandidateNameInCertificate", name);
	}
	/**
	 * Takes Enrollment number or registration number as input
	 * @param enrollment registration number
	 */
	public void Enrollment(String enrollment) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationEnrollmentRegisterNo", enrollment);
	}
	/**
	 * Takes percentage or CGPA as input
	 * @param percentage percentage of marks
	 */
	public void Percentage(String percentage) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationCGPA", percentage);
	}
	/**
	 * Takes CommencementYear as input
	 * @param CommencementYear year of college joining
	 */
	public void CourseCommencementYear (String CommencementYear) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationCourseCommenceDate_dateInput", CommencementYear);
	}
	/**
	 * Takes CourseCompletionYear as input
	 * @param CompletionYear year of Course Completion
	 */
	public void CourseCompletionYear(String CompletionYear) {
		pages.Utill().input_text("ctl00$ContentPlaceHolder1$txtEducationCourseCompletionDate$dateInput", CompletionYear);
	}
	/**
	 * Takes gap reason as input
	 * @param gapreason reason for gap
	 */
	public void Gap(String gapreason) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rbtnEducationGap_0");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationGapReasons", gapreason);
	}
	/**
	 * additional comments
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationAdditionalComments", comments);
	}
	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkEducationInsuff");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEducationInsuffRemarks", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
	}
	
}
