package verification;

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

public class Criminal extends Verification {

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
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[6]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Criminal']");
		pages.Utill().SwitchFramebyIndex(5);
	}
	/**
	 * Performs click action on Document button
	 */

	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCriminalDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
		if(!value.trim().equals(component)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
		if (verifyddvalue(component)) {
			
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalComponent_DropDown']/div/ul//li[text()='"
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
	 * Takes int as input and perform click action
	 * 
	 * @param i 1 for SAME check 0 for OTHERS check
	 * @param component name of component
	 * @throws InvalidActivityException invalid data 0 and 1 only acceptable
	 */
	public void CopyComponentDatafrom(int i, String component) throws InvalidActivityException {
		if (i == 1) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_rbtCriminalCopySame_1");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCriminalSameAs_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(pages.Utill().find("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalSameAs_DropDown']/div/ul/li[1]")));
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalSameAs_DropDown']/div/ul//li[text()='"+component+"']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		}
		else if (i == 0) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_rbtCriminalCopySame_0");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCriminalCopy_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(pages.Utill().find("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalCopy_DropDown']/div/ul/li[1]")));
		pages.Utill().click("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalCopy_DropDown']/div/ul//li[text()='"+component+"']");
		pages.Utill().waitUntilLoaderisInvisible(100);
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
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCriminalState_Input","Tamil Nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlCriminalCity_Input", "Chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		pages.Utill().click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalCity_DropDown']/div/ul//li[text()='Chennai']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalLandmark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalToDate_dateInput", date);
	}

	/**
	 * Perform click action on till date
	 */
	public void TillDate() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_chkTillDate");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * comments
	 * 
	 * @param comments address comments
	 */
	public void comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalComments", comments);
	}

	
	/**
	 * Takes police station name as input and pass it to text field 
	 * @param stationName police station name
	 */
	public void PoliceStation(String stationName) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtPoliceStation", stationName);
	}
	/**
	 * additional comments
	 * @param comments additional comments
	 */
	public void Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalComments", comments);
	}
	/**
	 * click submit button on reference data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCriminalSubmit_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();	
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnCriminalAdd_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_btnCriminalAddDocument_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_grdCriminalDocumentList_ctl00__0']/td[2]", 10);
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
		pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCriminalDocument_C_btnCriminalDocumentCancel_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public String Component() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalComponent_Input");
	}

	public String AddressLine1() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCriminalAddress");
	}

	public String Country() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalCountry_Input");
	}

	public String getState() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalState_Input");
	}

	public String getCity() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalCity_Input");
	}

	public String Pincode() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCriminalPincode");
	}

	public String Landmark() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCriminalLandmark");
	}

	public String FromDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCriminalFromDate_dateInput");
	}

	public String ToDate() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCriminalToDate_dateInput");
	}

	public String Comments() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtCriminalComments");
	}

	public String PoliceStation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtPoliceStation");
	}
	public void RespondentName(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalRespondentName", name);
	}

	public String RespondentName() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCriminalRespondentName");
	}

	

	public void Ver_Comments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalVerifierComments", comments);
	}

	public String Ver_Comments() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCriminalVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_Input");
		if (!value.equals(status.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_DropDown']/div/ul//li[text()='"
							+ status + "']");
		}
	}

	public String ComponentStatus() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalVerifierTypeofRevert_Input");
	}

	public void ModeOfInitiation(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfInitiation() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfInitiation_Input");
	}

	public void DateOfInitiation(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalDateOfInitiation", date);
	}

	public String DateOfInitiation() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCriminalDateOfInitiation");
	}

	public void ModeOfVerification(String mode) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_Input");
		if (!value.equals(mode.trim())) {
			pages.Utill().sleep(300);
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_DropDown']/div/ul//li[text()='"
							+ mode + "']");
		}
	}

	public String ModeOfVerification() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlCriminalModeOfVerification_Input");
	}

	public void DateOfVerification(String date) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtCriminalDateOfVerification", date);
	}

	public String DateOfVerification() {
		return pages.Utill().getText("ctl00_ContentPlaceHolder1_txtCriminalDateOfVerification");
	}

	public void ServiceProvider(String name) {
		String value = pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			pages.Utill().click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			pages.Utill().click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}
	public void Verification() throws Exception {
		Properties pro = pages.Utill().veridata("criminal");
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
		this.DateOfInitiation(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.ModeOfVerification(pro.getProperty("ModeOfVerification"));
		this.DateOfVerification(pages.Utill().getCurrentDate("dd/MM/yyyy"));
		this.submit();
	}
}
