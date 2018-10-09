package dataEntry;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public class CaseInformation {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public CaseInformation(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public void edit() throws Exception {
		pages.Utill().click_element("btnCaseSave_input");
		pages.Utill().confirmAlert();
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	public void CaseDocument() {
		pages.Utill().click_element("btnCaseDocument");
	}

	public String getDocumentName(String doctype) {
		String path = "//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']//*[text()='" + doctype
				+ "']/../td[5]//td[1]/span";
		if (this.isDoctypeValid(doctype)) {
			return pages.Utill().get_text(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
public void documentDownload(String doctype) {
	String path ="//table[@id='rwmCaseDocuments_C_gviewCaseDocuments_ctl00']//*[text()='"+doctype+"']/../td[5]//td[4]/input";
	if (this.isDoctypeValid(doctype)) {
		pages.Utill().click_element(path);
	} else {
		throw new NotFoundException(doctype);
	}
	
}
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

	public void cancel() {
		pages.Utill().click_element("rwmCaseDocuments_C_btnCaseDocumentCancels");
	}
}
