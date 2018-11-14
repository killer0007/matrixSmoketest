package client;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

import pages.CaseRegistration;

public class Casereg extends CaseRegistration implements Home {

	/**
	 * This is class for client case registration
	 * 
	 * @param logger logger instance
	 */
	public Casereg(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
public void casereg() {
	String value=pages.Utill().getText("ctl00_ContentPlaceHolder1_ddlAct_Input");
	if (!value.equals("Case Registration")) {
		this.Action();
		pages.Utill().sleep(700);
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']/div/ul/li[8]");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	
	
}
	/**
	 * Select case registration from the actions dropwdown
	 */
	public void Action() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlAct_Input");
	}

	/**
	 * Takes case reference number as input and pass it to case ref no field
	 * 
	 * @param refno case reference number
	 */
	public void clientrefno(String refno) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_TextBoxClientReference", refno);
	}

	/**
	 * Click search button
	 */
	public void Search() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnSearch");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * click Register New Case button
	 */
	public void Registercase() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ButtonRegisterNewCase_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select the check box which related to
	 * component name
	 * 
	 * @param componentname name of sub component
	 */
	public void selectcheck(String componentname) {
		pages.Utill().click("//td[text()='" + componentname + "']/../td[2]//input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes case reference number as input pass the case ref no into client ref no
	 * field click search button
	 * 
	 * @param refno case reference number
	 */
	public void Search(String refno) {
		this.clientrefno(refno);
		this.Search();

	}

	/**
	 * returns the reference number after search action
	 * 
	 * @return refno case reference number
	 */
	public String getRefno() {
		return pages.Utill()
				.getText("//tr[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[4]");
	}



	/**
	 * Returns the of Attachment Availability
	 * 
	 * @return Attachment Availability Documents not attached
	 */
	public String AttachementAvailability() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_lblDisplayAttachmentAvailability");
	}

	/**
	 * Performs click action on all component(s) radio button
	 */
	public void AllComponents() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rbtnAllComponents");
	}

	/**
	 * Performs click action on selected component(s) radio button
	 */
	public void SelectedComponents() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rbtnSelectedComponents");
	}

	/**
	 * Performs click action on Register button
	 */
	public void Register() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnRegister_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Performs click action on Cancel button
	 */
	public void Cancel() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCancel_input");
	}

	
	/**
	 * case registration action after searching of particular case
	 * Gets case details from table and map it to hashmap
	 * keys are caserefno, clientrefno, candidatename, dob, recievedon, duedate
	 * @return table data (hashmap)
	 */
public Map<String, String> getcasedetails() { 
	Map<String, String> data = new HashMap<String, String>();
	String caserefno=pages.Utill().getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[4]");
	String clientrefno=pages.Utill().getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[5]");
	String candidatename=pages.Utill().getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[6]");
	String dob=pages.Utill().getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[7]");
	String recievedon=pages.Utill().getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[9]");
	String duedate=pages.Utill().getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[11]");
	data.put("caserefno", caserefno);
	data.put("clientrefno", clientrefno);
	data.put("candidatename", candidatename);
	data.put("dob", dob);
	data.put("recievedon", recievedon);
	data.put("duedate", duedate);
	return data;
}
/**
 * Takes case reference number as input and pass it to caserefno field
 * click search button and return case details which shown in table
 * @param caserefnumber case reference number
 * @return table data (hashmap)
 */
public Map<String, String> getcasedetails(String caserefnumber) { 
	this.Search(caserefnumber);
	return this.getcasedetails();
}
/**
 * Takes case registration details as input with hashmap format
 * 
 * @param data hash map contains registration details
 * @throws Exception element not found
 */
public void registercase(Map<String, String> data) throws Exception {
	CaseRegistration casereg = pages.CaseRegistration();
	casereg.selectProject(data.get("ProjectName"));
	casereg.FirstName(data.get("CandidateName"));
	casereg.LastName(data.get("lastname"));
	casereg.DOB(pages.Utill().getDob());
	casereg.gender("male");
	casereg.Email(data.get("CandidateName") + "@ggmail.com");
	casereg.FatherFirstName("fname");
	casereg.FatherLastName("lname");
	casereg.LinkedIn(data.get("CandidateName") + " linkedin");
	casereg.maritalStatus("Single");
	casereg.Nationality("Indian");
	casereg.LandlineNumber(pages.Utill().mobileno());
	casereg.MobileNumber(pages.Utill().mobileno());
	casereg.EmergencyContactNumber(pages.Utill().mobileno());
	casereg.EmergencyContactPerson(data.get("CandidateName"));
	casereg.CandidateID(data.get("CandidateId"));
	casereg.clickfresher(false);
	super.uploadcaseDoc(data.get("doctype"), data.get("filename"));
	this.SelectedComponents();
}
}
