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

public class Court extends DataEntryPage{
	/**
	 * This is class for Court page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Court(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Court tab and switch to Court frame
	 */
	public void courtcheck() {
		SwitchDefault();
//		click("//*[@id='tabStrip']/div/ul/li[8]/a/span/span/span");
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Court']");
		SwitchFramebyIndex(7);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		click("ctl00_ContentPlaceHolder1_btnCourtDocument_input");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=getValue("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
		if(!value.trim().equals(component)) {
		click("ctl00_ContentPlaceHolder1_ddlCourtComponent_Input");
		if (verifyddvalue(component)) {
			click("//div[@id='ctl00_ContentPlaceHolder1_ddlCourtComponent_DropDown']/div/ul//li[text()='"
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
//		sleep(1000);
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
	 * Takes int as input and perform click action
	 * 
	 * @param i 1 for SAME check 0 for OTHERS check
	 * @param component sub component name
	 * @throws InvalidActivityException invalid data 0 and 1 only acceptable
	 */
	public void CopyComponentDatafrom(int i, String component) throws InvalidActivityException {
		if (i == 1) {
			click("ctl00_ContentPlaceHolder1_rbtCourtCopySame_1");
		waitUntilLoaderisInvisible(100);
		click("ctl00_ContentPlaceHolder1_ddlCourtSameAs_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(find("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtSameAs_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtSameAs_DropDown']/div/ul//li[text()='"+component+"']");
		waitUntilLoaderisInvisible(100);
		}
		else if (i == 0) {
			click("ctl00_ContentPlaceHolder1_rbtCourtCopySame_0");
		waitUntilLoaderisInvisible(100);
		click("ctl00_ContentPlaceHolder1_ddlCourtCopy_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(find("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtCopy_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtCopy_DropDown']/div/ul//li[text()='"+component+"']");
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
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtAddress", address);
	}

	/**
	 * select tamil nadu as state
	 */
	public void State() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlCourtState_Input","Tamil Nadu");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCourtState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtState_DropDown']/div/ul//li[text()='Tamil Nadu']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * select chennai as city
	 */
	public void City() {
		sendKeys("ctl00_ContentPlaceHolder1_ddlCourtCity_Input", "Chennai");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlAddressState_DropDown']/div/ul/li[1]")));
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_ddlCourtCity_DropDown']/div/ul//li[text()='Chennai']");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes pincode as input and pass it to pincode field
	 * 
	 * @param pincode pincode of address
	 */
	public void Pincode(String pincode) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtPincode", pincode);
	}

	/**
	 * Takes Landmark as input and pass it to Landmark field
	 * 
	 * @param Landmark landmark near to address
	 */
	public void LandMark(String Landmark) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtLandmark", Landmark);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date from date of address
	 */
	public void FromDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtFromDate_dateInput", date);
	}

	/**
	 * Takes date as input date should be mm/yyyy format
	 * 
	 * @param date To date of address
	 */
	public void ToDate(String date) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtToDate_dateInput", date);
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
	public void Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtComments", comments);
	}

	/**
	 * click report insuff button
	 */
	public void ReportInsuff() {
		click("ctl00_ContentPlaceHolder1_chkCourtInsuff");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes insufff comments as input and pass it
	 * 
	 * @param comments insuff raise comments
	 */
	public void Insuffcomm(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtCourtInsuffRemark", comments);
	}

	/**
	 * click not applicable button
	 */
	public void Notapplicable() {
		click("ctl00_ContentPlaceHolder1_chkComponentNotApplicable");
		waitUntilLoaderisInvisible(100);
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
	 * click submit button on reference data entry
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception{
		click("ctl00_ContentPlaceHolder1_btnCourtSubmit_input");
		waitUntilLoaderisInvisible(100);
			SwitchDefault();	
		confirmAlert();
	}
	/**
	 * performs click action on save button
	 */
	public void save() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnCourtAdd_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
	}
	/**
	 * Performs click action on add document button in document upload screen
	 */
	public void AddDocument() {
		click("ctl00_ContentPlaceHolder1_rwmCourtDocument_C_btnCourtAddDocument_input");
		waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes document type as input and checks for given document type available in upload screen
	 * @param doctype type of document
	 * @return true when document ype was available
	 */
	public boolean isvaliddoctype(String doctype) {
	waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCourtDocument_C_grdCourtDocumentList_ctl00__0']/td[2]", 10);
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
		click("ctl00_ContentPlaceHolder1_rwmCourtDocument_C_btnCourtDocumentCancel_input");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes the input from court.properties file and pass it to current address court
	 * @param component name which check data to be imported
	 * @throws Exception webdriver exception
	 */
	public void CurrentAddress(String component) throws Exception{
		Properties pro = dedata("court");
		this.courtcheck();
		this.Component("Current Address Court Check");
		this.CopyComponentDatafrom(0,component);
		this.document();
		this.UploadDocument("Address Proof", pro.getProperty("currentAddressproof"));
		this.docclose();
		this.Comments(pro.getProperty("currentcommments"));
		this.submit();
	}
	/**
	 * Takes the input from court.properties file and pass it to previous address criminal
	 * @param component name which check data to be imported
	 * @throws Exception webdriver exception
	 */
	public void PermanentAddress(String component) throws Exception {
		Properties pro = dedata("court");
		this.courtcheck();
		this.Component("Permanent Court Check");
		this.CopyComponentDatafrom(0,component);
		this.document();
		this.UploadDocument("Address Proof", pro.getProperty("perAddressproof"));
		this.docclose();
		this.Comments(pro.getProperty("permanentcomments"));
		this.submit();
	}
}
