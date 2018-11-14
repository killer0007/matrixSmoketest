package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class Criminal extends dataEntry.Criminal {
	public Criminal(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	
	/**
	 * Takes Document type as input and return the name of uploaded document
	 * 
	 * @param doctype Type of Document
	 * @return document name
	 */
	public String getDocumentName(String doctype) {

		String path = "//table[@id='ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_grdCriminalDocumentList_ctl00']//*[text()='"
				+ doctype + "']/../td[5]//td[1]/span";
		if (this.isvaliddoctype(doctype)) {
			return getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	

	public String Component() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
	}

	public String AddressLine1() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalAddress");
	}

	public String Country() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCriminalCountry_Input");
	}

	public String getState() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCriminalState_Input");
	}

	public String getCity() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCriminalCity_Input");
	}

	public String Pincode() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalPincode");
	}

	public String Landmark() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalLandmark");
	}

	public String FromDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalFromDate_dateInput");
	}

	public String ToDate() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalToDate_dateInput");
	}

	public String Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalComments");
	}

	public String PoliceStation() {
		return getValue("ctl00_ContentPlaceHolder1_txtPoliceStation");
	}
	public Map<String, String> CurrentAddress() throws Exception{
		this.Component("Current Address Criminal Check");
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
		map.put("cpolicestation", this.PoliceStation());
		map.put("ccomments", this.Comments());
		this.document();
		map.put("currentAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> PermanentAdress() throws Exception{
		this.Component("Permanent Criminal Check");
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
		map.put("ppolicestation", this.PoliceStation());
		map.put("pcomments", this.Comments());
		this.document();
		map.put("perAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public Map<String, String> filedata(String component) throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("address");
		Properties cri= dedata("criminal");
		if(component.equals("Current Address Criminal Check")) {
		map.put("Component", "Current Address Criminal Check");
		map.put("AddressLine1", pro.getProperty("AddressLine1"));
		map.put("Country", pro.getProperty("Country"));
		map.put("State", pro.getProperty("State"));
		map.put("City", pro.getProperty("City"));
		map.put("Pincode", pro.getProperty("Pincode"));
		map.put("Landmark", pro.getProperty("Landmark"));
		map.put("FromDate", pro.getProperty("FromDate"));
		map.put("ToDate", pro.getProperty("ToDate"));
		map.put("cpolicestation", cri.getProperty("cpolicestation"));
		map.put("ccomments", cri.getProperty("ccomments"));
		map.put("currentAddressproof", new File(cri.getProperty("currentAddressproof")).getName().replaceAll(" ", ""));
		return map;
		}
		else if(component.equals("Permanent Criminal Check")) {
			map.put("Component", "Permanent Criminal Check");
			map.put("AddressLine1", pro.getProperty("AddressLine1"));
			map.put("Country", pro.getProperty("Country"));
			map.put("State", pro.getProperty("State"));
			map.put("City", pro.getProperty("City"));
			map.put("Pincode", pro.getProperty("Pincode"));
			map.put("Landmark", pro.getProperty("Landmark"));
			map.put("FromDate", pro.getProperty("FromDate"));
			map.put("ToDate", pro.getProperty("ToDate"));
			map.put("ppolicestation", cri.getProperty("ppolicestation"));
			map.put("pcomments", cri.getProperty("pcomments"));
			map.put("perAddressproof", new File(cri.getProperty("perAddressproof")).getName().replaceAll(" ", ""));

			return map;
		}
		else
			throw new NotFoundException();
	}
}
