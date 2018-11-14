package client;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import environment.Utill;

public class ViewCandidateFinalReport extends Utill {

	public ViewCandidateFinalReport(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select case registration from the actions dropwdown
	 */
	public void Action() {
		super.click("ctl00_ContentPlaceHolder1_ddlAct_Input");
	}

	public void viewcandidatefinalreport() {
		String value = super.getText("ctl00_ContentPlaceHolder1_ddlAct_Input");
		if (!value.equals("View Candidate Final Report")) {
			this.Action();
			super.sleep(700);
			super.click("//*[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']/div/ul/li[2]");
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

	public String getSearchResult() {

		try {
			return super.getText("//*[@id='ctl00_ContentPlaceHolder1_grdCaseList_ctl00__0']/td[4]");
		} catch (NoSuchElementException e) {
			super.logger.log(Status.FAIL, e.getMessage());
			return null;
		}
	}
	public void ViewReport() {
		super.click("ctl00_ContentPlaceHolder1_grdCaseList_ctl00_ctl04_btnViewdoc_input");
	}
	public void Download() {
		super.click("ctl00_ContentPlaceHolder1_rdmAddDoc_C_grdFinalReportDocument_ctl00_ctl04_btnViewdoc");
	}
	
	public void Close() {
		super.click("ctl00_ContentPlaceHolder1_rdmAddDoc_C_btnCloseWindow");
	}
	
	public void Cancel() {
		super.click("//span[text()='Close']");
	}
}
