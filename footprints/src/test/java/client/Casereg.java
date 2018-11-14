package client;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import pages.CaseRegistration;

public class Casereg extends CaseRegistration {

	/**
	 * This is class for client case registration
	 * 
	 * @param logger logger instance
	 */
	public Casereg(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
public void casereg() {
	String value=getText("ctl00_ContentPlaceHolder1_ddlAct_Input");
	if (!value.equals("Case Registration")) {
		this.Action();
		sleep(700);
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']/div/ul/li[8]");
		waitUntilLoaderisInvisible(100);
	}
	
	
}
	/**
	 * Select case registration from the actions dropwdown
	 */
	public void Action() {
		click("ctl00_ContentPlaceHolder1_ddlAct_Input");
	}

	/**
	 * Takes case reference number as input and pass it to case ref no field
	 * 
	 * @param refno case reference number
	 */
	public void clientrefno(String refno) {
		sendKeys("ctl00_ContentPlaceHolder1_TextBoxClientReference", refno);
	}

	/**
	 * Click search button
	 */
	public void Search() {
		click("ctl00_ContentPlaceHolder1_btnSearch");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * click Register New Case button
	 */
	public void Registercase() {
		click("ctl00_ContentPlaceHolder1_ButtonRegisterNewCase_input");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select the check box which related to
	 * component name
	 * 
	 * @param componentname name of sub component
	 */
	public void selectcheck(String componentname) {
		click("//td[text()='" + componentname + "']/../td[2]//input");
		waitUntilLoaderisInvisible(100);
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
		return getText("//tr[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[4]");
	}



	/**
	 * Returns the of Attachment Availability
	 * 
	 * @return Attachment Availability Documents not attached
	 */
	public String AttachementAvailability() {
		return getText("ctl00_ContentPlaceHolder1_lblDisplayAttachmentAvailability");
	}

	/**
	 * Performs click action on all component(s) radio button
	 */
	public void AllComponents() {
		click("ctl00_ContentPlaceHolder1_rbtnAllComponents");
	}

	/**
	 * Performs click action on selected component(s) radio button
	 */
	public void SelectedComponents() {
		click("ctl00_ContentPlaceHolder1_rbtnSelectedComponents");
	}

	/**
	 * Performs click action on Register button
	 */
	public void Register() {
		click("ctl00_ContentPlaceHolder1_btnRegister_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Performs click action on Cancel button
	 */
	public void Cancel() {
		click("ctl00_ContentPlaceHolder1_btnCancel_input");
	}

	
	/**
	 * case registration action after searching of particular case
	 * Gets case details from table and map it to hashmap
	 * keys are caserefno, clientrefno, candidatename, dob, recievedon, duedate
	 * @return table data (hashmap)
	 */
public Map<String, String> getcasedetails() { 
	Map<String, String> data = new HashMap<String, String>();
	String caserefno=getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[4]");
	String clientrefno=getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[5]");
	String candidatename=getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[6]");
	String dob=getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[7]");
	String recievedon=getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[9]");
	String duedate=getText("//*[@id='ctl00_ContentPlaceHolder1_grdClientRegisteredCaseList_ctl00__0']/td[11]");
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
	Search(caserefnumber);
	return getcasedetails();
}
/**
 * Takes case registration details as input with hashmap format
 * 
 * @param data hash map contains registration details
 * @throws Exception element not found
 */
public void registercase(Map<String, String> data) throws Exception {
	
	selectProject(data.get("ProjectName"));
	FirstName(data.get("CandidateName"));
	LastName(data.get("lastname"));
	DOB(getDob());
	gender("male");
	Email(data.get("CandidateName") + "@ggmail.com");
	FatherFirstName("fname");
	FatherLastName("lname");
	LinkedIn(data.get("CandidateName") + " linkedin");
	maritalStatus("Single");
	Nationality("Indian");
	LandlineNumber(mobileno());
	MobileNumber(mobileno());
	EmergencyContactNumber(mobileno());
	EmergencyContactPerson(data.get("CandidateName"));
	CandidateID(data.get("CandidateId"));
	clickfresher(false);
	uploadcaseDoc(data.get("doctype"), data.get("filename"));
	SelectedComponents();
}
}
