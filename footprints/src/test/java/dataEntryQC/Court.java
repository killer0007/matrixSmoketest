package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Court extends dataEntry.Court{
	/**
	 * This is class for Court page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Court(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	
	
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {
		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCourtDocument_C_grdCourtDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	public String Component() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
	}

	public String AddressLine1() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtAddress");
	}

	public String Country() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCourtCountry_Input");
	}

	public String getState() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCourtState_Input");
	}

	public String getCity() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCourtCity_Input");
	}

	public String Pincode() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtPincode");
	}

	public String Landmark() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtLandmark");
	}

	public String FromDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtFromDate_dateInput");
	}

	public String ToDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtToDate_dateInput");
	}

	public String Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtCourtComments");
	}
	
	public Map<String, String> CurrentAddress() throws Exception{
		this.Component("Current Address Court Check");
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
		map.put("currentcommments", this.Comments());
		this.document();
		map.put("currentAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> PermanentAdress() throws Exception{
		this.Component("Permanent Court Check");
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
		map.put("permanentcomments", this.Comments());
		this.document();
		map.put("perAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata(String component) throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("address");
		Properties court= dedata("court");
		if(component.equals("Current Address Court Check")) {
		map.put("Component", "Current Address Court Check");
		map.put("AddressLine1", pro.getProperty("AddressLine1"));
		map.put("Country", pro.getProperty("Country"));
		map.put("State", pro.getProperty("State"));
		map.put("City", pro.getProperty("City"));
		map.put("Pincode", pro.getProperty("Pincode"));
		map.put("Landmark", pro.getProperty("Landmark"));
		map.put("FromDate", pro.getProperty("FromDate"));
		map.put("ToDate", pro.getProperty("ToDate"));
		map.put("currentcommments", court.getProperty("currentcommments"));
		map.put("currentAddressproof", new File(court.getProperty("currentAddressproof")).getName().replaceAll(" ", ""));
		return map;
		}
		else if(component.equals("Permanent Court Check")) {
			map.put("Component", "Permanent Court Check");
			map.put("AddressLine1", pro.getProperty("AddressLine1"));
			map.put("Country", pro.getProperty("Country"));
			map.put("State", pro.getProperty("State"));
			map.put("City", pro.getProperty("City"));
			map.put("Pincode", pro.getProperty("Pincode"));
			map.put("Landmark", pro.getProperty("Landmark"));
			map.put("FromDate", pro.getProperty("FromDate"));
			map.put("ToDate", pro.getProperty("ToDate"));
			map.put("permanentcomments", court.getProperty("permanentcomments"));
			map.put("perAddressproof", new File(court.getProperty("perAddressproof")).getName().replaceAll(" ", ""));

			return map;
		}
		else
			throw new NotFoundException();
	}
}
