package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class Address extends dataEntry.Address {


	/**
	 * This is class for address in data entryqc
	 * 
	 * @param logger logger instance
	 */
	public Address(WebDriver driver,ExtentTest logger) {
	
	super(driver,logger);
	}


	public String Component() {
		return super.getValue("ctl00_ContentPlaceHolder1_ddlComponent_Input");
	}

	public String AddressLine1() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressAddress");
	}

	public String Country() {
		return super.getValue("ctl00_ContentPlaceHolder1_ddlAddressCountry_Input");
	}

	public String getState() {
		return super.getValue("ctl00_ContentPlaceHolder1_ddlAddressState_Input");
	}

	public String getCity() {
		return super.getValue("ctl00_ContentPlaceHolder1_ddlAddressCity_Input");
	}

	public String Pincode() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressPincode");
	}

	public String Landmark() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressLandMark");
	}

	public String FromDate() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressFromDate_dateInput");
	}

	public String ToDate() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressToDate_dateInput");
	}

	public String LandLordName() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordName");
	}

	public String LandLordAddressLine1() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordAddress");
	}

	public String LandLordCountry() {
		return super.getValue("ctl00_ContentPlaceHolder1_ddlLandLordCountry_Input");
	}

	public String getLandLordState() {
		return super.getValue("ctl00_ContentPlaceHolder1_ddlLandLordState_Input");
	}

	public String getLandLordCity() {
		return super.getValue("ctl00_ContentPlaceHolder1_ddlLandLordCity_Input");
	}

	public String LandLordPincode() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtLandLordPincode");
	}

	public String LandLordLandmark() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtLandLordLandmark");
	}

	public String LandLordContactNo() {
		return super.getValue("ctl00_ContentPlaceHolder1_txtAddressLandLordContactNo");
	}

	public String Comments() {
		return super.getText("ctl00_ContentPlaceHolder1_txtAddressComments");
	}
public Map<String, String> getCurrentAddress() throws Exception{
	this.Component("Current Address");
	Map<String , String> map=new LinkedHashMap<String, String>();
	map.put("Component", this.Component());
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
	map.put("LandLordContactNo", this.LandLordContactNo());
	map.put("Comments", this.Comments());
	this.document();
	map.put("currentAddressproof", this.getDocumentName("Address Proof"));
	this.docclose();
	this.submit();
	return map;
}
public Map<String, String> PermanentAdress() throws Exception{
	this.Component("Permanent");
	Map<String , String> map=new LinkedHashMap<String, String>();
	map.put("Component", this.Component());
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
	map.put("LandLordContactNo", this.LandLordContactNo());
	map.put("permanentcomments", this.Comments());
	this.document();
	map.put("PerAddressproof", this.getDocumentName("Address Proof"));
	this.docclose();
	this.submit();
	return map;
}
public Map<String, String> filedata(String component) throws Exception{
	Map<String , String> map=new LinkedHashMap<String, String>();
	Properties pro= super.dedata("address");
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

}
