package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Criminal extends DataEntryQCPage {
	public Criminal(ExtentTest logger) {
		super(logger);
	}

	/**
	 * Select Criminal tab and switch to Criminal frame
	 */
	public void criminalcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[6]/a/span/span/span");
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li//span[text()='Criminal']");
		pages.Utill().SwitchFramebyIndex(5);
	}
	/**
	 * Performs click action on Document button
	 */
	@Override
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCriminalDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
		if(!value.trim().equals(component)) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
		if (verifyddvalue(component)) {
			
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
			pages.Utill().wait_until_loader_is_invisible(100);
		} else {
			throw new NotFoundException(component);
		}
		}
	}

	/**
	 * Takes component name as input and checks given name exist in dropwdown or not
	 * 
	 * @param component sub component name
	 * @return true when component valid
	 */
	private boolean verifyddvalue(String component) {
		pages.Utill().sleep(1000);
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
					break;
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
	 * click submit button on reference data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCriminalSubmit_input");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().SwitchDefault();	
		pages.Utill().confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCriminalAdd_input");
		pages.Utill().wait_until_loader_is_invisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkCriminalInsuff");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCriminalInsuffRemark", comments);
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
			return pages.Utill().get_text(path).trim().replaceAll("[0-9]", "");
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
	pages.Utill().wait_element_has_text("//*[@id='ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_grdCriminalDocumentList_ctl00__0']/td[2]", 10);
		boolean re =false;
		String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_grdCriminalDocumentList_ctl00']/tbody/tr/td[2]";
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
		pages.Utill().input_text("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		super.WaitforFileUpdate(doctype, file);
		this.AddDocument();
		pages.Utill().wait_until_loader_is_invisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_btnCriminalAddDocument_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_btnCriminalDocumentCancel_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	public String Component() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
	}

	public String AddressLine1() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_txtCriminalAddress");
	}

	public String Country() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlCriminalCountry_Input");
	}

	public String State() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlCriminalState_Input");
	}

	public String City() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlCriminalCity_Input");
	}

	public String Pincode() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_txtCriminalPincode");
	}

	public String Landmark() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_txtCriminalLandmark");
	}

	public String FromDate() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_txtCriminalFromDate_dateInput");
	}

	public String ToDate() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_txtCriminalToDate_dateInput");
	}

	public String Comments() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_txtCriminalComments");
	}

	public String PoliceStation() {
		return pages.Utill().getvalue("ctl00_ContentPlaceHolder1_txtPoliceStation");
	}
	public LinkedHashMap<String, String> CurrentAddress() throws Exception{
		this.Component("Current Address Criminal Check");
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
		map.put("cpolicestation", this.PoliceStation());
		map.put("ccomments", this.Comments());
		this.document();
		map.put("currentAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> PermanentAdress() throws Exception{
		this.Component("Permanent Criminal Check");
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
		map.put("ppolicestation", this.PoliceStation());
		map.put("pcomments", this.Comments());
		this.document();
		map.put("perAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> filedata(String component) throws Exception{
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("address");
		Properties cri= pages.Utill().dedata("criminal");
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
