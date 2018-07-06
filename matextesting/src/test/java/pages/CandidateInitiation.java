package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.ReadEmail;
import testCases.Pages;

public class CandidateInitiation {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	
	public CandidateInitiation(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		
	}

	public String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
		return pr.getProperty(key);
	}

	public String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}
public String InitiateCandidate(int id) throws Exception {
	pages.Utill().click_element(getlocator("cc_Client_namedd"));
	pages.Utill().input_text(getlocator("cc_Client_name"), getvalue("cc_Client_name"));
	pages.Utill().click_element(".//*[@class='active-result highlighted']");
	pages.Wait().wait_until_loader_is_invisible();
	pages.Utill().input_text(getlocator("cc_CandidateName"), pages.Utill().candidateName());
		pages.Utill().click_element(getlocator("cc_MobileNo"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().input_text(getlocator("cc_MobileNo"), getvalue("cc_MobileNo"));
		pages.Utill().input_text(getlocator("cc_EmailID"), getvalue("cc_EmailID"));
		pages.Utill().input_text(getlocator("cc_HREmail"), getvalue("cc_HREmail"));
	
		pages.Utill().input_text(getlocator("cc_CanId"), Integer.toString(id));
		if (getlocator("cc_Client_name").equals("automation")) {
			pages.Utill().select_by_label(getlocator("cc_Region"), "East");
		}
		
		pages.Utill().click_element(getlocator("cc_RadioButtonList"));
		
		String loginId=pages.Utill().find(getlocator("cc_LoginID")).getAttribute("value");
		pages.Utill().click_element(getlocator("cc_save"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListadr", "1");
		//
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListemp", "2");
		//
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_DropDownListref", "2");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxedu");

		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbx10");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbx12");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxhighest");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxug1");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxdiploma");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkbxpg1");	
		pages.Utill().click_element(getlocator("cc_initiate"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().clickAlertbox();
		System.out.println("------------loginid is :_"+loginId+"--------------------------");
		return loginId;
//		Thread.sleep(4000);
//		String data =ReadEmail.read();
//		System.out.println(data);
//		System.out.println("-----------------result is :-"+data.contains(loginId)+"-------------");
			
}
public void Personal()  throws Exception{
	pages.Utill().SwitchFramebyIndex(0);
	
pages.Utill().input_text(getlocator("cp_Middlename"), getvalue("cp_Middlename"));
pages.Utill().input_text(getlocator("cp_Fathername"), getvalue("cp_Fathername"));

pages.Utill().select_by_label(getlocator("cp_Gender"), getvalue("cp_Gender"));
pages.Utill().select_by_label(getlocator("cp_MaritalStatus"), getvalue("cp_MaritalStatus"));
//cp_DOB
pages.Utill().input_text(getlocator("cpHR_Name"), getvalue("cpHR_Name"));
pages.Utill().input_text(getlocator("cp_EmpNo"), getvalue("cp_EmpNo"));
pages.Utill().input_text(getlocator("cp_Designation"), getvalue("cp_Designation"));
pages.Utill().input_text(getlocator("cp_Grade"), getvalue("cp_Grade"));
pages.Utill().input_text(getlocator("cp_JoinPlace"), getvalue("cp_JoinPlace"));
pages.Utill().input_text(getlocator("cp_PanNo"), getvalue("cp_PanNo"));
pages.Utill().input_text(getlocator("cp_ITPinNo"), getvalue("cp_ITPinNo"));
pages.Utill().input_text(getlocator("cp_PassportNo"), getvalue("cp_PassportNo"));
pages.Utill().input_text(getlocator("cp_Issue_Place"), getvalue("cp_Issue_Place"));
pages.Utill().input_text(getlocator("cp_Issue_State"), getvalue("cp_Issue_State"));
pages.Utill().input_text(getlocator("cp_Issue_Country"), getvalue("cp_Issue_Country"));
//cp_Issue_Date
//cp_Expire_Date
pages.Utill().input_text(getlocator("cp_MobileTwo1"), getvalue("cp_MobileTwo1"));
pages.Utill().input_text(getlocator("cp_VoterId"), getvalue("cp_VoterId"));
pages.Utill().input_text(getlocator("cp_VoterAddress"), getvalue("cp_VoterAddress"));
pages.Utill().input_text(getlocator("cp_IDProofNo"), getvalue("cp_IDProofNo"));
pages.Utill().input_text(getlocator("cp_SSNNo"), getvalue("cp_SSNNo"));

pages.Utill().input_text(getlocator("cp_AadharName"), pages.Utill().find(getlocator("cc_Candidate_Name")).getAttribute("value"));
pages.Utill().input_text(getlocator("cp_DrivingLicenseNo"), getvalue("cp_DrivingLicenseNo"));
pages.Utill().input_text(getlocator("cp_AadharNumber"), getvalue("cp_AadharNumber"));
pages.Utill().input_text(getlocator("cp_AadharEnrollNumber"), getvalue("cp_AadharEnrollNumber"));
pages.Utill().input_text(getlocator("cp_Comments"), getvalue("cp_Comments"));
pages.Utill().select_by_label(getlocator("cp_IsFresher"), getvalue("cp_IsFresher"));

	
	
}

}
