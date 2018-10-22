package dataEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activity.InvalidActivityException;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

public class Address extends DataEntryPage {

	/**
	 * This is class for address page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Address(ExtentTest logger) {
		super(logger);
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
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isDoctypeValid(doctype)) {
			return pages.Utill().get_text(path).trim().replaceAll("[0-9]", "");
		} else {
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
	 * Takes Document Type as input checks the given document type available in
	 * upload popup
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
	 * Takes Document type as input Perform download action on document
	 * 
	 * @param doctype Type Of document
	 */
	public void downloaddoc(String doctype) {
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[4]/input";
		if (this.isDoctypeValid(doctype)) {
			pages.Utill().click_element(path);
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) throws Exception{
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
		} else {
			throw new NotFoundException(component);
		}
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) throws Exception {
		Thread.sleep(2000);
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlComponent_DropDown']/div/ul/li"));
//		System.out.println("lenght is :"+list.size());
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
//				System.out.println("success : "+t+"  -  "+i);
				if (t.equals(component)) {
					re = true;
					break;
				} else {
					continue;
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
	public void CopyComponentDatafrom(int i, String component) throws Exception {
		
		if (i == 1) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_rbtCopySame_1");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlSameAs_Input");
		Thread.sleep(1000);
		pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlSameAs_DropDown']/div/ul//li[text()='"+component+"']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
		else if (i == 0) {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_rbtCopySame_0");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlCopy_Input");
		Thread.sleep(1000);
		pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlCopy_DropDown']/div/ul//li[text()='"+component+"']");
		pages.Utill().wait_until_loader_is_invisible(100);
}
		else
			throw new InvalidActivityException(Integer.toString(i));
	}

	/**
	 * Takes address line 1 as input and pass it to address field
	 * 
	 * @param address address line 1
	 */
	public void AddressLine1(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlAddressState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlAddressCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressLandMark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressToDate_dateInput", date);
	}

	/**
	 * Perform click action on till date
	 */
	public void TillDate() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkTillDate");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * choose to select the rented yes or not
	 * 
	 * @param option true or false
	 */
	public void isRented(boolean option) {
		if (option)
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_rbResidenceType_0");
		else
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_rbResidenceType_1");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes landlord name as input
	 * @param name land lard name
	 */
	public void Name(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressLandLordName", name);
	}

	/**
	 * Takes input as landlord address
	 * 
	 * @param address landlord address
	 */
	public void LandlordAddress(String address) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressLandLordAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void LandLordState() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlLandLordState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordState_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void LandLordCity() {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlLandLordCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordCity_DropDown']/div/ul/li[1]")));
		pages.Utill().click_element(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes pincode as input and pass it to LandLord pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void LandLordPincode(String pincode) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLandLordPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to LandLord Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandLordLandMark(String Landmark) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtLandLordLandmark", Landmark);
	}

	/**
	 * Takes contact number as input and pass it to landlord contact number
	 * 
	 * @param number contact number
	 */
	public void ContactNo(String number) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressLandLordContactNo", number);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressComments", comments);
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkAddressInsuff");
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtAddressInsuffRemark", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
	}

	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
	}
	/**
	 * click submit button on address data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddressSubmit_input");
		pages.Utill().wait_until_loader_is_invisible(100);
//		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAddressAdd_input");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Takes input from address.properties file and pass it to address data entry
	 * @throws Exception WebDriverException
	 */
	public void CurrentAddress() throws Exception {
		Properties pro=pages.Utill().dedata("address");
		this.addresscheck();
		this.Component("Current Address");
		this.AddressLine1(pro.getProperty("AddressLine1"));
		this.State();
		this.City();
		this.Pincode(pro.getProperty("Pincode"));
		this.LandMark(pro.getProperty("Landmark"));
		this.FromDate(pro.getProperty("FromDate"));
		this.ToDate(pro.getProperty("ToDate"));
		this.isRented(true);
		this.Name(pro.getProperty("LandLordName"));
		this.LandlordAddress(pro.getProperty("LandLordAddressLine1"));
		this.LandLordState();
		this.LandLordCity();
		this.LandLordPincode(pro.getProperty("LandLordPincode"));
		this.LandLordLandMark(pro.getProperty("LandLordLandmark"));
		this.ContactNo(pro.getProperty("LandLordContactNo"));
		this.comments(pro.getProperty("Comments"));
//		this.submit();
		this.save();
		
	}
	/**
	 * Takes component and source component as input and set this same as address
	 * @param component sub component name
	 * @param sourcecomponent source component name 
	 * @throws Exception WebDriverException
	 */
	public void sameascurrent(String component, String sourcecomponent) throws Exception {
		this.addresscheck();
		this.Component(component);
		this.CopyComponentDatafrom(1, sourcecomponent);
		this.comments(pages.Utill().dedata("address").getProperty("permanentcomments"));
		this.save();
	}
}
