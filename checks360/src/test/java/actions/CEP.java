package actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Properties;

import org.apache.bsf.engines.javascript.JavaScriptEngine;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class CEP extends ActionPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public CEP(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public void CEPClear() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlAct_Input");
		pages.Utill().wait_until_element_isvisible("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[1]", 10);
		pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[4]");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	public String getrefNo() {

		return pages.Utill().get_text("//table[@id='ctl00_ContentPlaceHolder1_grdTaskList_ctl00']/tbody/tr[1]/td[5]");
	}

	public void addDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwClearCEP_C_btnAddDocument");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	public void clearComments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_rdwClearCEP_C_txtClearedRemarks", comments);
	}

	public void submit() throws Exception{
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwClearCEP_C_ButtonSubmit_input");
//		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().confirmAlert();
	}

	public void close() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwClearCEP_C_ButtonClose_input");
//		pages.Utill().wait_until_loader_is_invisible(100);
	}
public void upload(String doctype, String file)throws Exception {
	pages.Utill().input_text("//td[text()='"+doctype+"']/../td[6]//span/input[2]", file);
	Thread.sleep(1000);
	this.addDocument();
}
public void upload(String refno, String doctype, String file) throws Exception {
	pages.Utill().click_element("//td[text()='"+refno+"']");
	pages.Utill().wait_until_loader_is_invisible(100);
	this.upload(doctype, file);
	this.submit();
	
}
public void upload(String refno,String comments, String doctype, String file) throws Exception {
	pages.Utill().click_element("//td[text()='"+refno+"']");
	pages.Utill().wait_until_loader_is_invisible(100);
	this.clearComments(comments);
	this.upload(doctype, file);
	this.submit();
	
}
}
