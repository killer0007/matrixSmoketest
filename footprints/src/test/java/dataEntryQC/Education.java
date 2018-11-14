package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Education extends dataEntry.Education {
	/**
	 * This is class for education in data entryqc
	 * 
	 * @param logger logger instance
	 */
	public Education(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
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
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	
	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
	}

	public String Component() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationComponent_Input");
	}

	public String InstituteName() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationInstitute_Input");
	}

	public String InstituteAddressLine1() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationInstituteDoorNoStreet");
	}

	public String InstituteCountry() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationInstituteCountry_Input");
	}

	public String getInstituteState() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationInstituteState_Input");
	}

	public String getInstituteCity() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationInstituteCity_Input");
	}

	public String BoardName() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationBoard_Input");
	}

	public String BoardAddressLine1() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationBoardDoorNoStreet");
	}

	public String BoardCountry() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationBoardCountry_Input");
	}

	public String getBoardState() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationBoardState_Input");
	}

	public String getBoardCity() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationBoardCity_Input");
	}

	public String NameOfCourse() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationNameOfCourse");
	}

	public String MajorSubject() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationTypeOfMajor");
	}

	public String TypeOfProgram() {
		return getValue("ctl00_ContentPlaceHolder1_ddlEducationTypeOfProgramName_Input");
	}

	public String CandidateNameinCertificate() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationCandidateNameInCertificate");
	}

	public String Enrollment() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationEnrollmentRegisterNo");
	}

	public String CGPA() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationCGPA");
	}

	public String CourseCommencementYear() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationCourseCommenceDate_dateInput");
	}

	public String CourseCompletionYear() {
		return getValue("ctl00_ContentPlaceHolder1_txtEducationCourseCompletionDate_dateInput");
	}

	public String Comments() {
		return getText("ctl00_ContentPlaceHolder1_txtEducationAdditionalComments");
	}
	public Map<String, String> gettwelveth() throws Exception{
		this.Component("12th");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("InstituteName", this.InstituteName());
		map.put("InstituteAddressLine1", this.InstituteAddressLine1());
		map.put("InstituteCountry", this.InstituteCountry());
		map.put("InstituteState", this.getInstituteState());
		map.put("InstituteCity", this.getInstituteCity());
		map.put("BoardName", this.BoardName());
		map.put("BoardAddressLine1", this.BoardAddressLine1());
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
		map.put("twelveth", this.getDocumentName("Mark Sheet"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> getugone() throws Exception{
		this.Component("UG1");
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("Component", this.Component());
		map.put("UG1InstituteName", this.InstituteName());
		map.put("UG1InstituteAddressLine1", this.InstituteAddressLine1());
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
		map.put("ugone", this.getDocumentName("Degree Certificate"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata(String component) throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("education");
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

