package client;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import environment.Utill;

public class VerifyCandidateDetails extends Utill {

	public VerifyCandidateDetails(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	/**
	 * Select case registration from the actions dropwdown
	 */
	public void Action() {
		super.click("ctl00_ContentPlaceHolder1_ddlAct_Input");
	}

	public void verifyvandidatedetails() {
		String value = super.getValue("ctl00_ContentPlaceHolder1_ddlAct_Input");
		if (!value.equals("Verify Candidate Details")) {
			this.Action();
			super.sleep(700);
			super.click("//*[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']/div/ul/li[1]");
			super.waitUntilLoaderisInvisible(100);
		}
	}

	/**
	 * Takes case reference number as input and pass it to case ref no field
	 * 
	 * @param refno case reference number
	 */
	public void clientrefno(String refno) {
		super.sendKeys("ctl00_ContentPlaceHolder1_TextBoxClientReference", refno);
	}

	public void caserefno(String refno) {
		super.sendKeys("ctl00_ContentPlaceHolder1_TextBoxCaseReference", refno);
	}

	public void FirstName(String fname) {
		super.sendKeys("ctl00_ContentPlaceHolder1_TextBoxFirstName", fname);
	}

	public void LastName(String lname) {
		super.sendKeys("ctl00_ContentPlaceHolder1_TextBoxLastName", lname);
	}

	public void Search() {
		super.click("ctl00_ContentPlaceHolder1_btnSearch");
		super.waitUntilLoaderisInvisible(50);
	}

	public void Search(String refno) {
		this.caserefno(refno);
		this.Search();
	}

	public void OpenCase(String refno) {
		this.Search(refno);
		super.click("ctl00_ContentPlaceHolder1_grdCandidateList_ctl00_ctl04_btnViewAllComponents");
		super.waitUntilLoaderisInvisible(40);
		if (super.getValue("btnApplyChanges_input").equals("Proceed")) {
			super.click("btnApplyChanges_input");
			super.waitUntilLoaderisInvisible(50);
		}
	}

	public String getSearchResult() {
		try {
			return super.getText("//*[@id='ctl00_ContentPlaceHolder1_grdCandidateList_ctl00__0']/td[9]");
		} catch (NoSuchElementException e) {
			super.logger.log(Status.FAIL, e.getMessage());
			return null;
		}
	}

	public void QuickSubmit() {
		super.click("ctl00_ContentPlaceHolder1_grdCandidateList_ctl00_ctl04_btnSubmitAllComponents");
		super.waitUntilLoaderisInvisible(50);
	}
	public void priority(String priority) {
	String value =super.getValue("ctl00_ContentPlaceHolder1_rwVerificationReadyComponent_C_ddlPriority_Input")	;
	if(!value.equals(priority)) {
		super.click("ctl00_ContentPlaceHolder1_rwVerificationReadyComponent_C_ddlPriority_Input");
		super.sleep(300);
		super.click("//div[@id='ctl00_ContentPlaceHolder1_rwVerificationReadyComponent_C_ddlPriority_DropDown']/div/ul/li[text()='"+priority+"']");
	}
	}
	public void submit() {
		super.click("ctl00_ContentPlaceHolder1_rwVerificationReadyComponent_C_btnComponentSave");
		super.waitUntilLoaderisInvisible(50);
	}
	public void FilterComponents() {
		String [] components= {"Credit Check 1","Current Address Criminal Check","Database","Panel1","Medical Test"};
		for (int i = 0; i < components.length; i++) {
		super.click("//span[text()='"+components[i]+"']/ancestor::td[2]/preceding-sibling::td[1]//input");
//			driver.findElement(By.xpath("//span[text()='"+components[i]+"']/ancestor::td[2]/preceding-sibling::td[1]//input")).click();
		super.waitUntilLoaderisInvisible(40);
		}
		this.priority("High");
		this.submit();
	}
	public void Home() {
		super.SwitchDefault();
		super.click("imgHome");
		super.waitUntilLoaderisInvisible(80);
	}
}
