package verification;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

public class Criminal extends dataEntryQC.Criminal implements Verification {

	/**
	 * This is class for Criminal page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Criminal(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Criminal tab and switch to Criminal frame
	 */
	public void criminalcheck() {
		SwitchDefault();
//		click("//*[@id='tabStrip']/div/ul/li[6]/a/span/span/span");
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Criminal']");
		SwitchFramebyIndex(5);
	}
	/**
	 * Performs click action on Document button
	 */

	public void document() {
		click("ctl00_ContentPlaceHolder1_btnCriminalDocument_input");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=getValue("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
		if(!value.trim().equals(component)) {
		click("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
		if (verifyddvalue(component)) {
			
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalComponent_DropDown']/div/ul//li[text()='"
							+ component + "']");
			waitUntilLoaderisInvisible(100);
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
		sleep(1000);
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
	 * Takes int as input and perform click action
	 * 
	 * @param i 1 for SAME check 0 for OTHERS check
	 * @param component name of component
	 * @throws InvalidActivityException invalid data 0 and 1 only acceptable
	 */
	public void CopyComponentDatafrom(int i, String component) throws InvalidActivityException {
		if (i == 1) {
			click("ctl00_ContentPlaceHolder1_rbtCriminalCopySame_1");
		waitUntilLoaderisInvisible(100);
		click("ctl00_ContentPlaceHolder1_ddlCriminalSameAs_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(find("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalSameAs_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalSameAs_DropDown']/div/ul//li[text()='"+component+"']");
		waitUntilLoaderisInvisible(100);
		}
		else if (i == 0) {
			click("ctl00_ContentPlaceHolder1_rbtCriminalCopySame_0");
		waitUntilLoaderisInvisible(100);
		click("ctl00_ContentPlaceHolder1_ddlCriminalCopy_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(find("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalCopy_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalCopy_DropDown']/div/ul//li[text()='"+component+"']");
		waitUntilLoaderisInvisible(100);
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
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlCriminalState_Input","Tamil Nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlCriminalCity_Input", "Chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalLandmark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalToDate_dateInput", date);
	}

	/**
	 * Perform click action on till date
	 */
	public void TillDate() {
		click("ctl00_ContentPlaceHolder1_chkTillDate");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalComments", comments);
	}

	
	/**
	 * Takes police station name as input and pass it to text field 
	 * @param stationName police station name
	 */
	public void PoliceStation(String stationName) {
		sendKeys("ctl00_ContentPlaceHolder1_txtPoliceStation", stationName);
	}
	/**
	 * additional comments
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalComments", comments);
	}
	/**
	 * click submit button on reference data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		click("ctl00_ContentPlaceHolder1_btnCriminalSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();	
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnCriminalAdd_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_btnCriminalAddDocument_input");
		waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_grdCriminalDocumentList_ctl00__0']/td[2]", 10);
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
		sendKeys("//*[text()='"+doctype+"']/../td[5]//span/input[2]", file);
		super.WaitforFileUpdate(doctype, file);
		this.AddDocument();
		waitUntilLoaderisInvisible(100);
		}
		else {
			throw new NotFoundException(doctype);
		}
		
	}
/**
	 * Perform close action on close button in document upload popup
	 */
	public void docclose() {
		click("ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_btnCriminalDocumentCancel_input");
		waitUntilLoaderisInvisible(100);
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
	public void RespondentName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalRespondentName", name);
	}

	public String RespondentName() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalRespondentName");
	}

	

	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalVerifierComments", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			sleep(300);
			click("ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCriminalDateOfVerification", date);
	}

	public String DateOfVerification() {
		return getValue("ctl00_ContentPlaceHolder1_txtCriminalDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}
	public void Verification() throws Exception {
		Properties pro = veridata("criminal");
		this.criminalcheck();
		this.document();
		String name = this.Component();
		if(name.equals("Current Address Criminal Check")) {
			this.UploadDocument("Verification Report", pro.getProperty("currentAddressproof"));
		}
		else if (name.equals("Permanent Criminal Check")){
			this.UploadDocument("Verification Report", pro.getProperty("perAddressproof"));
		}
		this.docclose();
		this.RespondentName(pro.getProperty("RespondentName"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.ModeOfInitiation(pro.getProperty("ModeOfInitiation"));
		this.DateOfInitiation(getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
	public Map<String, String> CurrentAddress() throws Exception{
		this.criminalcheck();
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
		map.put("currentAddressproof", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("RespondentName", this.RespondentName());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ModeOfInitiation", this.ModeOfInitiation());
		map.put("DateOfInitiation", this.DateOfInitiation());
		map.put("ModeOfVerification", this.ModeOfVerification());
		map.put("DateOfVerification", this.DateOfVerification());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}
	public Map<String, String> PermanentAdress() throws Exception{
		this.criminalcheck();
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
		map.put("perAddressproof", this.getDocumentName("Verification Report"));
		this.docclose();
		map.put("RespondentName", this.RespondentName());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ModeOfInitiation", this.ModeOfInitiation());
		map.put("DateOfInitiation", this.DateOfInitiation());
		map.put("ModeOfVerification", this.ModeOfVerification());
		map.put("DateOfVerification", this.DateOfVerification());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}
	public Map<String, String> filedata() throws Exception{
	String date= getCurrentDate("dd/MM/yyyy");
		String component=this.Component();
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= dedata("address");
		Properties cri= veridata("criminal");
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
		map.put("RespondentName", cri.getProperty("RespondentName"));
		map.put("Ver_Comments", cri.getProperty("verComments"));
		map.put("ComponentStatus", cri.getProperty("ComponentStatus"));
		map.put("ModeOfInitiation", cri.getProperty("ModeOfInitiation"));
		map.put("DateOfInitiation", date);
		map.put("ModeOfVerification", cri.getProperty("ModeOfVerification"));
		map.put("DateOfVerification", date);
		map.put("ServiceProvider", cri.getProperty("ServiceProvider"));
		logger.log(Status.INFO, map.toString());
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
			map.put("RespondentName", cri.getProperty("RespondentName"));
			map.put("Ver_Comments", cri.getProperty("verComments"));
			map.put("ComponentStatus", cri.getProperty("ComponentStatus"));
			map.put("ModeOfInitiation", cri.getProperty("ModeOfInitiation"));
			map.put("DateOfInitiation", date);
			map.put("ModeOfVerification", cri.getProperty("ModeOfVerification"));
			map.put("DateOfVerification", date);
			map.put("ServiceProvider", cri.getProperty("ServiceProvider"));
			logger.log(Status.INFO, map.toString());

			return map;
		}
		else
			throw new NotFoundException();
	}
	public void ReportComments() {
		click("ctl00_ContentPlaceHolder1_btnCriminalAddComments");
		waitUntilLoaderisInvisible(100);
	}
	public void CloseReportComments() {
		click("ModalClose");
		sleep(500);
	}
	

	@Override
	public void UpdateReportComments() {
		int count = driver.findElements(By.xpath("//*[@id='accordion']/div")).size();

		for (int i = 1; i < count; i++) {
			if (i > 1) {
				click("//*[@id='accordion']/div[" + Integer.toString(i) + "]//b");
			}
			String info = getText("//*[@id='accordion']/div[" + Integer.toString(i) + "]//td[2]/span");
			sendKeys("//*[@id='accordion']/div[" + Integer.toString(i) + "]//div[3]/div/p/..", info);
		}

	}
}
