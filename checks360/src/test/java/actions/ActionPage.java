package actions;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

abstract class ActionPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is abstract class for Action pages in portal
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public ActionPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	/**
	 * used for selecting case source from dropdown
	 * 
	 * @param source case source dropdown value
	 * @throws Exception when given case source not found in dropwdown
	 */
	public void caseSource(String source) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlWorkflowType_Input");
		pages.Utill().wait_until_element_isvisible(
				"//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[1]", 10);
		Thread.sleep(1000);
		switch (source.toLowerCase()) {
		case "sp":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[1]");
			break;
		case "candidate":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[2]");
			break;
		case "client":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[3]");
			break;
		case "bulk":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[4]");
			break;
		case "iverify":
			pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[5]");
			break;
		default:
			throw new InvalidSelectorException(source + " not found in list");
		}
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Takes refno as input and pass it to case ref no field
	 * 
	 * @param refno case reference number
	 */
	public void caserefno(String refno) {
		pages.Utill().clear_element_text("ctl00_ContentPlaceHolder1_TextBoxCaseReference");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxCaseReference", refno);
	}

	/**
	 * Takes Firstname as input and pass it to First Name field
	 * 
	 * @param firstname candidate first name
	 */
	public void firstName(String firstname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxFirstName", firstname);
	}

	/**
	 * Takes LastName as input and pass it to Last Name field
	 * 
	 * @param lastname candidate last name
	 */
	public void lastName(String lastname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxLastName", lastname);
	}

	/**
	 * click on the search button and wait until loader is invisible
	 */
	public void search() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSearch");
		pages.Utill().wait_until_loader_is_invisible(10);
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
