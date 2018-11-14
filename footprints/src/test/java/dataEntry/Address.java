package dataEntry;

import java.util.List;
import java.util.Properties;
import javax.activity.InvalidActivityException;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Address extends DataEntryPage {

	/**
	 * This is class for address page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Address(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Select Address tab and switch to address frame
	 */
	public void addresscheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Address']");
		SwitchFramebyIndex(0);
	}

	/**
	 * Perform click action on Document button
	 */
	public void document() {
		click("ctl00_ContentPlaceHolder1_btnAddressDocument_input");
		waitUntilLoaderisInvisible(100);
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
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		click("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnDocumentCancel_input");
		waitUntilLoaderisInvisible(100);
	}

	

	/**
	 * Takes Document type as input Perform download action on document
	 * 
	 * @param doctype Type Of document
	 */
	public void downloaddoc(String doctype) {
		String path = "//*[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[4]/input";
		if (this.isvaliddoctype(doctype)) {
			click(path);
		} else {
			throw new NotFoundException(doctype);
		}
	}

	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 * @throws Exception webdriverException
	 */
	public void Component(String component) throws Exception{
		String value=getValue("ctl00_ContentPlaceHolder1_ddlComponent_Input");
		if(!value.trim().equals(component)) {
		click("ctl00_ContentPlaceHolder1_ddlComponent_Input");
		if (verifyddvalue(component)) {
			super
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
		} else {
			throw new NotFoundException(component);
		}
		waitUntilLoaderisInvisible(100);
		}
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
	 * @throws Exception invalid data 0 and 1 only acceptable
	 */
	public void CopyComponentDatafrom(int i, String component) throws Exception {
		
		if (i == 1) {
			click("ctl00_ContentPlaceHolder1_rbtCopySame_1");
		waitUntilLoaderisInvisible(100);
		click("ctl00_ContentPlaceHolder1_ddlSameAs_Input");
		Thread.sleep(1000);
		click("//div[@id='ctl00_ContentPlaceHolder1_ddlSameAs_DropDown']/div/ul//li[text()='"+component+"']");
		waitUntilLoaderisInvisible(100);
	}
		else if (i == 0) {
			click("ctl00_ContentPlaceHolder1_rbtCopySame_0");
		waitUntilLoaderisInvisible(100);
		click("ctl00_ContentPlaceHolder1_ddlCopy_Input");
		Thread.sleep(1000);
		click("//div[@id='ctl00_ContentPlaceHolder1_ddlCopy_DropDown']/div/ul//li[text()='"+component+"']");
		waitUntilLoaderisInvisible(100);
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
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlAddressState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlAddressCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressCity_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlAddressCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandMark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressToDate_dateInput", date);
	}

	/**
	 * Perform click action on till date
	 */
	public void TillDate() {
		click("ctl00_ContentPlaceHolder1_chkTillDate");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * choose to select the rented yes or not
	 * 
	 * @param option true or false
	 */
	public void isRented(boolean option) {
		if (option)
			click("ctl00_ContentPlaceHolder1_rbResidenceType_0");
		else
			click("ctl00_ContentPlaceHolder1_rbResidenceType_1");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes landlord name as input
	 * @param name land lard name
	 */
	public void Name(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandLordName", name);
	}

	/**
	 * Takes input as landlord address
	 * 
	 * @param address landlord address
	 */
	public void LandlordAddress(String address) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandLordAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void LandLordState() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlLandLordState_Input", "tamil nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void LandLordCity() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlLandLordCity_Input", "chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordCity_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlLandLordCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to LandLord pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void LandLordPincode(String pincode) {
		sendKeys("ctl00_ContentPlaceHolder1_txtLandLordPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to LandLord Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandLordLandMark(String Landmark) {
		sendKeys("ctl00_ContentPlaceHolder1_txtLandLordLandmark", Landmark);
	}

	/**
	 * Takes contact number as input and pass it to landlord contact number
	 * 
	 * @param number contact number
	 */
	public void ContactNo(String number) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressLandLordContactNo", number);
	}

	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressComments", comments);
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		click("ctl00_ContentPlaceHolder1_chkAddressInsuff");
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtAddressInsuffRemark", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		click("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
	}

	/**
	 * Takes not applicable comments as input and pass it
	 * 
	 * @param comments not applicable comments
	 */
	public void Notapplicablecomm(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
	}
	/**
	 * click submit button on address data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		click("ctl00_ContentPlaceHolder1_btnAddressSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnAddressAdd_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnAddDocument_input");
		waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		boolean re =false;
		waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00__0']/td[2]", 10);
		String path="//table[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00']/tbody/tr/td[2]";
		List<WebElement> list =driver.findElements(By.xpath(path));
		if(list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				String t=list.get(i).getText().trim();
				logger.log(Status.INFO, t);
				if(t.equals(doctype)) {
					re=true;
					break;
				}
			}
		}
		else {
			logger.log(Status.FAIL, "no element found");
		}
		return re;
	}
	/**
	 * Takes document type and file as input and uploads the document
	 * @param doctype type of document
	 * @param file file name
	 */
	public void UploadDocument(String doctype, String file) {
		if(this.isvaliddoctype(doctype)) {
		sendKeys("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		WaitforFileUpdate(doctype, file);
		
		this.AddDocument();
		waitUntilLoaderisInvisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * Takes input from address.properties file and pass it to address data entry
	 * @throws Exception WebDriverException
	 */
	public void CurrentAddress() throws Exception {
		Properties pro=dedata("address");
		this.addresscheck();
		this.Component("Current Address");
		this.AddressLine1(pro.getProperty("AddressLine1"));
		this.State();
		this.City();
		this.Pincode(pro.getProperty("Pincode"));
		this.LandMark(pro.getProperty("Landmark"));
		this.FromDate(FormateDate(pro.getProperty("FromDate")));
		this.ToDate(FormateDate(pro.getProperty("ToDate")));
		this.isRented(true);
		this.Name(pro.getProperty("LandLordName"));
		this.LandlordAddress(pro.getProperty("LandLordAddressLine1"));
		this.LandLordState();
		this.LandLordCity();
		this.LandLordPincode(pro.getProperty("LandLordPincode"));
		this.LandLordLandMark(pro.getProperty("LandLordLandmark"));
		this.ContactNo(pro.getProperty("LandLordContactNo"));
		this.document();
		this.UploadDocument("Address Proof", pro.getProperty("currentAddressproof"));
		this.docclose();
		this.comments(pro.getProperty("Comments"));
		this.submit();
//		this.save();
		
	}
	/**
	 * Takes component and source component as input and set this same as address
	 * @param component sub component name
	 * @param sourcecomponent source component name 
	 * @throws Exception WebDriverException
	 */
	public void sameascurrent(String component, String sourcecomponent) throws Exception {
		Properties pro=dedata("address");
		this.addresscheck();
		this.Component(component);
		this.CopyComponentDatafrom(1, sourcecomponent);
		this.document();
		this.UploadDocument("Address Proof", pro.getProperty("PerAddressproof"));
		this.docclose();
		this.comments(dedata("address").getProperty("permanentcomments"));
//		this.save();
		this.submit();
	}
}
