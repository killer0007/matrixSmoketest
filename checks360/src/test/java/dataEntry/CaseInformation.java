package dataEntry;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class CaseInformation {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Case information part in data entry
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public CaseInformation(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * Perform click action on Edit button
	 * 
	 * @throws Exception when confirmation alert not found
	 */
	public void edit() throws Exception {
		pages.Utill().click_element("btnCaseSave_input");
		pages.Utill().confirmAlert();
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Perform click action on CaseDocument button
	 */
	public void CaseDocument() {
		pages.Utill().click_element("btnCaseDocument");
	}

	/**
	 * Takes Document Type as input and returns the uploaded file name
	 * 
	 * @param doctype Type Of Document
	 * @return File name which uploaded in portal
	 */
	public String getDocumentName(String doctype) {
		String path = "//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']//*[text()='" + doctype
				+ "']/../td[5]//td[1]/span";
		if (this.isDoctypeValid(doctype)) {
			return pages.Utill().get_text(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Takes Document type as input and download the document file
	 * 
	 * @param doctype Type Of Document
	 */
	public void documentDownload(String doctype) {
		String path = "//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']//*[text()='" + doctype
				+ "']/../td[5]//td[4]/input";
		if (this.isDoctypeValid(doctype)) {
			pages.Utill().click_element(path);
		} else {
			throw new NotFoundException(doctype);
		}

	}

	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public boolean isDoctypeValid(String doctype) {
		String path = "//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']/tbody/tr/td[2]";
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
	 * Performs click action on cancel button in document upload popup
	 */
	public void cancel() {
		pages.Utill().click_element("rwmCaseDocuments_C_btnCaseDocumentCancels");
	}
}
