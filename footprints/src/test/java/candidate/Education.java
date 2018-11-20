package candidate;

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

public class Education extends dataEntryQC.Education {

	public Education(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public void educationcheck() {
		super.SwitchDefault();
		if (!this.getTitle().equals("Education")) {
			super.click("linkText:UG1");
			super.waitUntilLoaderisInvisible(10);
		}
		super.SwitchFramebyIndex(0);
	}

	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}

	public void viewUpload() {
		super.click("ctl00_ContentPlaceHolder1_btnUploadDocument_input");
		super.waitUntilLoaderisInvisible(100);
	}

	public void docclose() {
		super.click("ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_btnEducationDocumentCancel");
	}

	public void saveNext() {
		super.click("ctl00_ContentPlaceHolder1_btnEducationSaveContinue_input");
		super.waitUntilLoaderisInvisible(100);
		super.SwitchDefault();
	}

	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmCaseEducationDocuments_C_btnEducationAddDocument_input");
		super.waitUntilLoaderisInvisible(50);
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
		waitUntilElementHasText(
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
			sendKeys("//*[text()='" + doctype + "']/../td[5]//span/input[2]", file);
			super.WaitforFileUpdate(doctype, file);
			this.AddDocument();
		} else {
			throw new NotFoundException(doctype);
		}

	}

	public void UGone() throws Exception {
		this.educationcheck();
		Properties pro = candidatedata("education");
		this.InstituteName(pro.getProperty("UG1InstituteName"));
		this.InstituteAddressLine1(pro.getProperty("UG1InstituteAddressLine1"));
		this.NameOfCourse(pro.getProperty("UG1NameOfCourse"));
		this.MajorSubject(pro.getProperty("UG1MajorSubject"));
		this.TypeOfProgram(pro.getProperty("UG1TypeOfProgram"));
		this.CandidateNameinCertificate(pro.getProperty("UG1CandidateNameinCertificate"));
		this.Enrollment(pro.getProperty("UG1Enrollment"));
		this.Percentage(pro.getProperty("UG1CGPA"));
		this.CourseCommencementYear(FormateDate(pro.getProperty("UG1CourseCommencementYear")));
		this.CourseCompletionYear(FormateDate(pro.getProperty("UG1CourseCompletionYear")));
		this.viewUpload();
		this.UploadDocument("Degree Certificate", pro.getProperty("ugone"));
		this.docclose();
		this.saveNext();
	}
	public String getStatusColor() {
		return super.getCssValue("//a[text()='UG1']/../following-sibling::td/span", "color");
	}
	public String getStatus() {
		return super.getText("//a[text()='UG1']/../following-sibling::td/span");
	}
	public Map<String, String> getugone() throws Exception{
		this.educationcheck();
		Map<String , String> map=new LinkedHashMap<String, String>();
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
		this.viewUpload();
		map.put("ugone", this.getDocumentName("Degree Certificate"));
		this.docclose();
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= candidatedata("education");
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
		map.put("ugone", new File(pro.getProperty("ugone")).getName().replaceAll(" ", ""));
		return map;
	
	}
}
