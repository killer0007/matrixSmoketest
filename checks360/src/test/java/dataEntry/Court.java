package dataEntry;

import java.util.List;

import javax.activity.InvalidActivityException;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

public class Court extends DataEntryPage{
	/**
	 * This is class for Court page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Court(ExtentTest logger) {
		super(logger);
	}
	/**
	 * Select Court tab and switch to Court frame
	 */
	public void courtcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[8]/a/span/span/span");
		pages.Utill().SwitchFramebyIndex(7);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCourtDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul/li//text()='"
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
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul/li"));
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
	 * Takes int as input and perform click action
	 * 
	 * @param i 1 for SAME check 0 for OTHERS check
	 * @throws InvalidActivityException invalid data 0 and 1 only acceptable
	 */
	public void CopyComponentDatafrom(byte i) throws InvalidActivityException {
		if (i == 1) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_rbtCourtCopySame_1");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
		else if (i == 0) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_rbtCourtCopySame_0");
		pages.Utill().wait_until_loader_is_invisible(100);
		}
		
		else
			throw new InvalidActivityException(Integer.toString(i));
	}

	/**
	 * Takes address line 1 as input and pass it to address field
	 * 
	 * @param address line 1
	 */
	public void AddressLine1(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCourtAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlCourtState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtState_DropDown']/div/ul/li//text()='Tamil Nadu'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlCourtCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtCity_DropDown']/div/ul/li//text()='Chennai'");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCourtPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCourtLandmark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCourtFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCourtToDate_dateInput", date);
	}

	/**
	 * Perform click action on till date
	 */
	public void TillDate() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkTillDate");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCourtComments", comments);
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkCourtInsuff");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCourtInsuffRemark", comments);
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
