package actions;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

abstract class ActionPage extends Utill{
	/**
	 * This is abstract class for Action pages in portal
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public ActionPage(WebDriver driver, ExtentTest logger) {
		super(driver,logger);

	}

	/**
	 * used for selecting case source from dropdown
	 * 
	 * @param source case source dropdown value
	 * @throws Exception when given case source not found in dropwdown
	 */
	public void caseSource(String source) throws Exception {
		click("ctl00_ContentPlaceHolder1_ddlWorkflowType_Input");
		waitUntilElementisVisible(
				"//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[1]", 10);
		Thread.sleep(1000);
		switch (source.toLowerCase()) {
		case "sp":
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[1]");
			break;
		case "candidate":
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[2]");
			break;
		case "client":
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[3]");
			break;
		case "bulk":
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[4]");
			break;
		case "iverify":
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[5]");
			break;
		default:
			throw new InvalidSelectorException(source + " not found in list");
		}
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Takes refno as input and pass it to case ref no field
	 * 
	 * @param refno case reference number
	 */
	public void caserefno(String refno) {
		clearElementText("ctl00_ContentPlaceHolder1_TextBoxCaseReference");
		sendKeys("ctl00_ContentPlaceHolder1_TextBoxCaseReference", refno);
	}

	/**
	 * Takes Firstname as input and pass it to First Name field
	 * 
	 * @param firstname candidate first name
	 */
	public void firstName(String firstname) {
		sendKeys("ctl00_ContentPlaceHolder1_TextBoxFirstName", firstname);
	}

	/**
	 * Takes LastName as input and pass it to Last Name field
	 * 
	 * @param lastname candidate last name
	 */
	public void lastName(String lastname) {
		sendKeys("ctl00_ContentPlaceHolder1_TextBoxLastName", lastname);
	}

	/**
	 * click on the search button and wait until loader is invisible
	 */
	public void search() {
		click("ctl00_ContentPlaceHolder1_btnSearch");
		waitUntilLoaderisInvisible(10);
	}

	/*
	 * Abstract methode for get reference number from search table
	 */
	public abstract String getrefNo();

	/**
	 * Takes case ref no and case source as input and performs search function
	 * 
	 * @param refno  case reference number
	 * @param source case source
	 * @throws Exception when case not found
	 */
	public void search(String refno, String source) throws Exception {
		this.caserefno(refno);
		this.caseSource(source);
		this.search();

	}
}
