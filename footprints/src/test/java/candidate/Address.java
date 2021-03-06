package candidate;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Address extends dataEntryQC.Address {
/**
 * This is page design for Address sub check in candidate data entry screen
 * Takes Webdriver and logger as input and initialize the constructor
 * @param driver WebDriver
 * @param logger Report logger
 */
	public Address(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
/**
 * Click on Current address hyper link on candidate data entry screen
 */
	public void addresscheck() {
		super.SwitchDefault();
		if (!this.getTitle().equals("Address")) {
			super.click("linkText:Current Address");
			super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",10);
		}
		super.SwitchFramebyIndex(0);
	}
/**
 * returns the title of subcheck to be opened
 * @return name subcheck name
 */
	public String getTitle() {
		return super.getText("//*[@id='tabStrip']/div/ul/li/a/span/span/span");
	}
/**
 * Performs click action on view upload button
 */
	public void viewUpload() {
		super.click("ctl00_ContentPlaceHolder1_btnAddressDocument_input");
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",100);
	}
/**
 * Close document upload pop up
 */
	public void docclose() {
		super.click("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnDocumentCancel");
		super.sleep(400);
	}
/**
 * Performs click action on savenext button
 */
	public void saveNext() {
		try {
			super.click("ctl00_ContentPlaceHolder1_btnAddressSubmit_input");
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
			logger.log(Status.WARNING, e.getMessage().toString());
			super.sleep(300);
			super.click("ctl00_ContentPlaceHolder1_btnAddressSubmit_input");
		}
		super.waitUntilLoaderisInvisible("RadAjaxLoadingPanel1",100);
		super.SwitchDefault();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnAddDocument_input");
		super.sleep(100);
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
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * returns the color of Current address text
	 * @return color of Current address text
	 */
	public String getStatusColor() {
		return super.getCssValue("//a[text()='Current Address']/../following-sibling::td/span", "color");
	}
	/**
	 * Takes the subcheck name as input and return the text color
	 * @param subcheck subcomponent name
	 * @return color of given subcheck
	 */
	public String getStatusColor(String subcheck) {
		return super.getCssValue("//a[text()='"+subcheck+"']/../following-sibling::td/span", "color");
	}
	/**
	 * Returns the Status of current address check in candidate data entry screen
	 * @return status of current address
	 */
	public String getStatus() {
		return super.getText("//a[text()='Current Address']/../following-sibling::td/span");
	}
	/**
	 * Takes subcheck name as input and return the current status 
	 * @param subcheck subcomponent name
	 * @return status of given sub check
	 */
	public String getStatus(String subcheck) {
		return super.getText("//a[text()='"+subcheck+"']/../following-sibling::td/span");
	}
	/**
	 * Takes input data from address.property file and completes the data entry of current address check
	 */
	public void CurrentAddress() throws Exception {
		Properties pro = candidatedata("address");
		this.addresscheck();
		super.AddressLine1(pro.getProperty("AddressLine1"));
		super.State();
		super.City();
		super.Pincode(pro.getProperty("Pincode"));
		super.LandMark(pro.getProperty("Landmark"));
		super.FromDate(FormateDate(pro.getProperty("FromDate")));
		super.ToDate(FormateDate(pro.getProperty("ToDate")));
		super.isRented(true);
		super.Name(pro.getProperty("LandLordName"));
		super.LandlordAddress(pro.getProperty("LandLordAddressLine1"));
		super.LandLordState();
		super.LandLordCity();
		super.LandLordPincode(pro.getProperty("LandLordPincode"));
		super.LandLordLandMark(pro.getProperty("LandLordLandmark"));
		super.ContactNo(pro.getProperty("LandLordContactNo"));
		this.viewUpload();
		super.UploadDocument("Address Proof", pro.getProperty("currentAddressproof"));
		this.docclose();
		this.saveNext();
	}
	/**
	 * collects the address check data and return as map
	 * @return address data which given at the time of candidate data entry
	 */
	public Map<String, String> getCurrentAddress() throws Exception{
		this.addresscheck();
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("AddressLine1", this.AddressLine1());
		map.put("Country", this.Country());
		map.put("State", this.getState());
		map.put("City", this.getCity());
		map.put("Pincode", this.Pincode());
		map.put("Landmark", this.Landmark());
		map.put("FromDate", this.FromDate());
		map.put("ToDate", this.ToDate());
		map.put("LandLordName", this.LandLordName());
		map.put("LandLordAddressLine1", this.LandLordAddressLine1());
		map.put("LandLordCountry", this.LandLordCountry());
		map.put("LandLordState", this.getLandLordState());
		map.put("LandLordCity", this.getLandLordCity());
		map.put("LandLordPincode", this.LandLordPincode());
		map.put("LandLordLandmark", this.LandLordLandmark());
		this.viewUpload();
		map.put("currentAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		return map;
	}
	/**
	 * Takes the address.property file and convert it as map object and return
	 * @return details of current address from file
	 * @throws Exception File not found
	 */
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= super.candidatedata("address");
		map.put("AddressLine1", pro.getProperty("AddressLine1"));
		map.put("Country", pro.getProperty("Country"));
		map.put("State", pro.getProperty("State"));
		map.put("City", pro.getProperty("City"));
		map.put("Pincode", pro.getProperty("Pincode"));
		map.put("Landmark", pro.getProperty("Landmark"));
		map.put("FromDate", pro.getProperty("FromDate"));
		map.put("ToDate", pro.getProperty("ToDate"));
		map.put("LandLordName", pro.getProperty("LandLordName"));
		map.put("LandLordAddressLine1", pro.getProperty("LandLordAddressLine1"));
		map.put("LandLordCountry", pro.getProperty("LandLordCountry"));
		map.put("LandLordState", pro.getProperty("LandLordState"));
		map.put("LandLordCity", pro.getProperty("LandLordCity"));
		map.put("LandLordPincode", pro.getProperty("LandLordPincode"));
		map.put("LandLordLandmark", pro.getProperty("LandLordLandmark"));
		map.put("currentAddressproof", new File(pro.getProperty("currentAddressproof")).getName().replaceAll(" ", ""));
		return map;
		
	}
}
