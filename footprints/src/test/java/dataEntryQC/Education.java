package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Education extends DataEntryQCPage {
	/**
	 * This is class for education in data entryqc
	 * 
	 * @param logger logger instance
	 */
	public Education(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	public void educationcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Education']");
		pages.Utill().SwitchFramebyIndex(1);
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 * @throws Exception webdriverException
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
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(
				By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul/li"));
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
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_gviewEducationDocument_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
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
	 * Performs click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEducationAddDocuments_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click(
				"ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_btnEducationDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * click submit button on education data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		int count = driver
				.findElements(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlEducationComponent_DropDown']/div/ul/li"))
				.size();
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnEducationSaveSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		// System.out.println("-----------count--------"+count);
		if (count == 2) {
			pages.Utill().SwitchDefault();
		}
//	 
		pages.Utill().confirmAlert();
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
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkEducationInsuff");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEducationInsuffRemarks", comments);
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

	public String InstituteState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationInstituteState_Input");
	}

	public String InstituteCity() {
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

	public String BoardState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlEducationBoardState_Input");
	}

	public String BoardCity() {
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
	public LinkedHashMap<String, String> twelveth() throws Exception{
		this.Component("12th");
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("InstituteName", this.InstituteName());
		map.put("InstituteAddressLine1", this.InstituteAddressLine1());
		map.put("InstituteCountry", this.InstituteCountry());
		map.put("InstituteState", this.InstituteState());
		map.put("InstituteCity", this.InstituteCity());
		map.put("BoardName", this.BoardName());
		map.put("BoardAddressLine1", this.BoardAddressLine1());
		map.put("BoardCountry", this.BoardCountry());
		map.put("BoardState", this.BoardState());
		map.put("BoardCity", this.BoardCity());
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
		map.put("twelveth", this.getDocumentName("Mark Sheet"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> ugone() throws Exception{
		this.Component("UG1");
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("UG1InstituteName", this.InstituteName());
		map.put("UG1InstituteAddressLine1", this.InstituteAddressLine1());
		map.put("UG1InstituteCountry", this.InstituteCountry());
		map.put("UG1InstituteState", this.InstituteState());
		map.put("UG1InstituteCity", this.InstituteCity());
		map.put("UG1BoardName", this.BoardName());
		map.put("UG1BoardAddressLine1", this.BoardAddressLine1());
		map.put("UG1BoardCountry", this.BoardCountry());
		map.put("UG1BoardState", this.BoardState());
		map.put("UG1BoardCity", this.BoardCity());
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
		map.put("ugone", this.getDocumentName("Degree Certificate"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> filedata(String component) throws Exception{
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("education");
		if(component.equals("12th")) {
		map.put("Component", "12th");
		map.put("InstituteName", pro.getProperty("InstituteName"));
		map.put("InstituteAddressLine1", pro.getProperty("InstituteAddressLine1"));
		map.put("InstituteCountry", pro.getProperty("InstituteCountry"));
		map.put("InstituteState", pro.getProperty("InstituteState"));
		map.put("InstituteCity", pro.getProperty("InstituteCity"));
		map.put("BoardName", pro.getProperty("BoardName"));
		map.put("BoardAddressLine1", pro.getProperty("BoardAddressLine1"));
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
		return map;
		}
		
	
	else if(component.equals("UG1")) {
		map.put("Component", "UG1");
		map.put("UG1InstituteName", pro.getProperty("UG1InstituteName"));
		map.put("UG1InstituteAddressLine1", pro.getProperty("UG1InstituteAddressLine1"));
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
		return map;
	}
	else
		throw new NotFoundException();
	}
}

