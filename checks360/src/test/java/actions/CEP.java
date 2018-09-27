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

public class CEP extends ActionPage{
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public CEP(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
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

	

}
