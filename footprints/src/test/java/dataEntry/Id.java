package dataEntry;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class Id extends DataEntryPage {
	/**
	 * This is class for Id page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Id(ExtentTest logger) {
		super(logger);
	}
	/**
	 * Select Id tab and switch to Id frame
	 */
	public void idcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[9]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(9);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnIdAddDocuments_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li//text()='"
							+ component + "'");
		} else {
			throw new NotFoundException(component);
		}
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) {

		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
				} else {
					re = false;
				}
			}
			return re;
		} else {
			return false;
		}
	}
	/**
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlIdCheckComponent_Input");
		
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='"
							+ component + "']");
	}
	/**
	 * Takes name as input Pass it to name On Id
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtIdName", name);
	}
	/**
	 * Takes id card number as input and pass it to ID Number
	 * @param number on Id card
	 */
public void IDNumber(String number) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtIdNumber", number);
	}
/**
 * Takes name as input and pass it to issue date (dd/mm/yyy)
 * @param date ID card issued date
 */
public void IssueDate(String date) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtIdIssueDate_dateInput", date);
}
/**
 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
 * @param date ID card Expiry date
 */
public void ExpiryDate(String date) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtIdExpireDate_dateInput", date);
}
/**
 * select India as country 
 * @deprecated country disabled for id card
 */
public void Country() {
	boolean re=false;
	if(re) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlIdIssueCountry_Input", "India");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li//text()='India'");
	pages.Utill().wait_until_loader_is_invisible(100);
}
}
/**
 * select tamil nadu as state
 */
public void State() {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlIdIssueState_Input", "tamil nadu");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * select chennai as city
 */
public void City() {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlIdIssueCity_Input", "chennai");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul//li[text()='Chennai']");
	pages.Utill().wait_until_loader_is_invisible(100);
}
/**
 * Takes no as input and pass it to enrollment number field
 * @param no enrollment number
 */
public void EnrollmentNo(String no) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtEnrollId1", no);
}
/**
 * comments
 * 
 * @param comments address comments
 */
public void comments(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtIdComments", comments);
}

/**
 * click report insuff button
 */
public void ReportInsuff() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkIdInsuff");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * Takes insufff comments as input and pass it
 * 
 * @param comments insuff raise comments
 */
public void Insuffcomm(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_chkComponentNotApplicable", comments);
}

/**
 * click not applicable button
 */
public void Notapplicable() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * Takes not applicable comments as input and pass it
 * 
 * @param comments not applicable comments
 */
public void Notapplicablecomm(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
}
}
