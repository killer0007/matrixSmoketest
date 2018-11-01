package dataEntryQC;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Court extends DataEntryQCPage{
	/**
	 * This is class for Court page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Court(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Court tab and switch to Court frame
	 */
	public void courtcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Court']");
		pages.Utill().SwitchFramebyIndex(7);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCourtDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
		if(!value.trim().equals(component)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
			pages.Utill().waitUntilLoaderisInvisible(100);
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
//		pages.Utill().sleep(1000);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul/li[1]")));
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul/li"));
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
	 * click report insuff button
	 */
	public void ReportInsuff() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkCourtInsuff");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCourtInsuffRemark", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
		pages.Utill().waitUntilLoaderisInvisible(100);
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
	 * click submit button on reference data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
//		int count=driver.findElements(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul/li")).size();
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCourtSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
//		if(count==2) {
			pages.Utill().SwitchDefault();	
//		}
		pages.Utill().confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCourtAdd_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCourtDocument_C_btnCourtAddDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCourtDocument_C_grdCourtDocumentList_ctl00__0']/td[2]", 10);
		boolean re =false;
		String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCourtDocument_C_grdCourtDocumentList_ctl00']/tbody/tr/td[2]";
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
		pages.Utill().sendKeys("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		super.WaitforFileUpdate(doctype, file);
		this.AddDocument();
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCourtDocument_C_btnCourtDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
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
			return pages.Utill().getText(path).trim().replaceAll("[0-9]", "");
		} else {
			throw new NotFoundException(doctype);
		}
	}

	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
	}

	public String AddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtAddress");
	}

	public String Country() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtCountry_Input");
	}

	public String State() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtState_Input");
	}

	public String City() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCourtCity_Input");
	}

	public String Pincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtPincode");
	}

	public String Landmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtLandmark");
	}

	public String FromDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtFromDate_dateInput");
	}

	public String ToDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtToDate_dateInput");
	}

	public String Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCourtComments");
	}
	public LinkedHashMap<String, String> CurrentAddress() throws Exception{
		this.Component("Current Address Court Check");
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
		map.put("currentcommments", this.Comments());
		this.document();
		map.put("currentAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> PermanentAdress() throws Exception{
		this.Component("Permanent Court Check");
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
		map.put("permanentcomments", this.Comments());
		this.document();
		map.put("perAddressproof", this.getDocumentName("Address Proof"));
		this.docclose();
		this.submit();
		return map;
	}
	public LinkedHashMap<String, String> filedata(String component) throws Exception{
		LinkedHashMap<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= pages.Utill().dedata("address");
		Properties court= pages.Utill().dedata("court");
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
