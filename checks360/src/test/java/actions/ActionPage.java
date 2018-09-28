package actions;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

abstract class ActionPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	public ActionPage(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	public void caseSource(String source) throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlWorkflowType_Input");
		pages.Utill().wait_until_element_isvisible(
				"//div[@id='ctl00_ContentPlaceHolder1_ddlWorkflowType_DropDown']//li[1]", 10);
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

	public void caserefno(String refno) {
		pages.Utill().clear_element_text("ctl00_ContentPlaceHolder1_TextBoxCaseReference");
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxCaseReference", refno);
	}

	public void firstName(String firstname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxFirstName", firstname);
	}

	public void lastName(String lastname) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_TextBoxLastName", lastname);
	}

	public void search() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnSearch");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public abstract String getrefNo();

	public void search(String refno, String source) throws Exception {
		this.caserefno(refno);
		this.caseSource(source);
		this.search();

	}
}
