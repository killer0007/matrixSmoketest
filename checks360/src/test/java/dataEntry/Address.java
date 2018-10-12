package dataEntry;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class Address extends DataEntryPage{
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for address page in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public Address(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		this.driver=driver;
		this.logger=logger;
		pages=new Pages(driver, logger);
	}
	/**
	 * Select Address tab and switch to address frame
	 */
	public void addresscheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[1]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(0);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddressDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {
		
		String path="//table[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']//*[text()='"+doctype+"']/../td[5]//td[1]/span";
		if(this.isDoctypeValid(doctype)) {
			return pages.Utill().get_text(path).trim().replaceAll("[0-9]", "");
		}
		else {
			throw new NotFoundException(doctype);
		}
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnDocumentCancel_input");
	}
	/**
	 * Takes Document Type as input checks the given document type
	 * available in upload popup
	 * 
	 * @param doctype Type of Document in upload popup
	 * @return boolean true means document type valid, false means invalid document
	 *         type
	 */
	public boolean isDoctypeValid(String doctype) {
		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']/tbody/tr/td[2]";
		List<WebElement> list = driver.findElements(By.xpath(path));
		if (list.size() > 0) {
			List<String> doc = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				doc.add(list.get(i).getText().trim());
			}
			if (doc.contains(doctype)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	 * Takes Document type as input
	 * Perform download action on document
	 * @param doctype Type Of document
	 */
	public void downloaddoc(String doctype) {
		String path="//*[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']//*[text()='"+doctype+"']/../td[5]//td[4]/input";
		if (this.isDoctypeValid(doctype)) {
			pages.Utill().click_element(path);
		} else {
			throw new NotFoundException(doctype);
		}
	}
	}
