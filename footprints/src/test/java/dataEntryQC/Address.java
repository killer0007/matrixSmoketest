package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Address extends DataEntryQCPage {


	/**
	 * This is class for address in data entryqc
	 * 
	 * @param logger logger instance
	 */
	public Address(WebDriver driver,ExtentTest logger) {
	
	super(driver,logger);
	}
public void addresscheck() {
	pages.Utill().SwitchDefault();
	pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Address']");
	pages.Utill().SwitchFramebyIndex(0);
}
/**
 * Takes component name as input and select from dropdwon
 * 
 * @param component sub component name
 * @throws Exception webdriverException
 */
public void Component(String component) throws Exception{
	String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlComponent_Input");
	if(!value.trim().equals(component)) {
	pages.Utill().click("ctl00_ContentPlaceHolder1_ddlComponent_Input");
	if (verifyddvalue(component)) {
		pages.Utill()
				.click("//div[@id='ctl00_ContentPlaceHolder1_ddlComponent_DropDown']/div/ul//li[text()='"
						+ component + "']");
	} else {
		throw new NotFoundException(component);
	}
	pages.Utill().waitUntilLoaderisInvisible(100);
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
//	System.out.println("lenght is :"+list.size());
	if (list.size() > 0) {
		boolean re = false;
		for (int i = 0; i < list.size(); i++) {
			String t = list.get(i).getText();
//			System.out.println("success : "+t+"  -  "+i);
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

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlComponent_Input");
	}

	public String AddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressAddress");
	}

	public String Country() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressCountry_Input");
	}

	public String State() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressState_Input");
	}

	public String City() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlAddressCity_Input");
	}

	public String Pincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressPincode");
	}

	public String Landmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandMark");
	}

	public String FromDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressFromDate_dateInput");
	}

	public String ToDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressToDate_dateInput");
	}

	public String LandLordName() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordName");
	}

	public String LandLordAddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordAddress");
	}

	public String LandLordCountry() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlLandLordCountry_Input");
	}

	public String LandLordState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlLandLordState_Input");
	}

	public String LandLordCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlLandLordCity_Input");
	}

	public String LandLordPincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtLandLordPincode");
	}

	public String LandLordLandmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtLandLordLandmark");
	}

	public String LandLordContactNo() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordContactNo");
	}

	public String Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtAddressComments");
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
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
		boolean re =false;
		pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmAddressDocument_C_grdDocumentList_ctl00__0']/td[2]", 10);
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
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnAddressDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmAddressDocument_C_btnDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
public LinkedHashMap<String, String> CurrentAddress() throws Exception{
	this.Component("Current Address");
	LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
	map.put("Component", this.Component());
	map.put("AddressLine1", this.AddressLine1());
	map.put("Country", this.Country());
	map.put("State", this.State());
	map.put("City", this.City());
	map.put("Pincode", this.Pincode());
	map.put("Landmark", this.Landmark());
	map.put("FromDate", this.FromDate());
	map.put("ToDate", this.ToDate());
	map.put("LandLordName", this.LandLordName());
	map.put("LandLordAddressLine1", this.LandLordAddressLine1());
	map.put("LandLordCountry", this.LandLordCountry());
	map.put("LandLordState", this.LandLordState());
	map.put("LandLordCity", this.LandLordCity());
	map.put("LandLordPincode", this.LandLordPincode());
	map.put("LandLordLandmark", this.LandLordLandmark());
	map.put("LandLordContactNo", this.LandLordContactNo());
	map.put("Comments", this.Comments());
	this.document();
	map.put("currentAddressproof", this.getDocumentName("Address Proof"));
	this.docclose();
	this.submit();
	return map;
}
public LinkedHashMap<String, String> PermanentAdress() throws Exception{
	this.Component("Permanent");
	LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
	map.put("Component", this.Component());
	map.put("AddressLine1", this.AddressLine1());
	map.put("Country", this.Country());
	map.put("State", this.State());
	map.put("City", this.City());
	map.put("Pincode", this.Pincode());
	map.put("Landmark", this.Landmark());
	map.put("FromDate", this.FromDate());
	map.put("ToDate", this.ToDate());
	map.put("LandLordName", this.LandLordName());
	map.put("LandLordAddressLine1", this.LandLordAddressLine1());
	map.put("LandLordCountry", this.LandLordCountry());
	map.put("LandLordState", this.LandLordState());
	map.put("LandLordCity", this.LandLordCity());
	map.put("LandLordPincode", this.LandLordPincode());
	map.put("LandLordLandmark", this.LandLordLandmark());
	map.put("LandLordContactNo", this.LandLordContactNo());
	map.put("permanentcomments", this.Comments());
	this.document();
	map.put("PerAddressproof", this.getDocumentName("Address Proof"));
	this.docclose();
	this.submit();
	return map;
}
public LinkedHashMap<String, String> filedata(String component) throws Exception{
	LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
	Properties pro= pages.Utill().dedata("address");
	if(component.equals("Current Address")) {
	map.put("Component", "Current Address");
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
	map.put("LandLordContactNo", pro.getProperty("LandLordContactNo"));
	map.put("Comments", pro.getProperty("Comments"));
	map.put("currentAddressproof", new File(pro.getProperty("currentAddressproof")).getName().replaceAll(" ", ""));
	return map;
	}
	else if(component.equals("Permanent")) {
		map.put("Component", "Permanent");
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
		map.put("LandLordContactNo", pro.getProperty("LandLordContactNo"));
		map.put("permanentcomments", pro.getProperty("permanentcomments"));
		map.put("PerAddressproof", new File(pro.getProperty("PerAddressproof")).getName().replaceAll(" ", ""));
		return map;
	}
	else
		throw new NotFoundException();
}
/**
 * click report insuff button
 */
public void ReportInsuff() {
	pages.Utill().click("ctl00_ContentPlaceHolder1_chkAddressInsuff");
}

/**
 * Takes insufff comments as input and pass it
 * 
 * @param comments insuff raise comments
 */
public void Insuffcomm(String comments) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtAddressInsuffRemark", comments);
}

/**
 * click not applicable button
 */
public void Notapplicable() {
	pages.Utill().click("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
}

/**
 * Takes not applicable comments as input and pass it
 * 
 * @param comments not applicable comments
 */
public void Notapplicablecomm(String comments) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtComponentNotApplicableRemarks", comments);
}
/**
 * click submit button on address data entry
 * @throws Exception WebDriverException
 */
public void submit() throws Exception{
	pages.Utill().click("ctl00_ContentPlaceHolder1_btnAddressSubmit_input");
	pages.Utill().waitUntilLoaderisInvisible(100);
	pages.Utill().SwitchDefault();
	pages.Utill().confirmAlert();
}
/**
 * performs click action on save button
 */
public void save() throws Exception {
	pages.Utill().click("ctl00_ContentPlaceHolder1_btnAddressAdd_input");
	pages.Utill().waitUntilLoaderisInvisible(100);
	pages.Utill().confirmAlert();
}
}
