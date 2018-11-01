package dataEntry;

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

public class Id extends DataEntryPage {
	/**
	 * This is class for Id page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Id(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	/**
	 * Select Id tab and switch to Id frame
	 */
	public void idcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click("//*[@id='tabStrip']/div/ul/li[9]/a/span/span/span");
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='ID']");
		pages.Utill().SwitchFramebyIndex(9);
	}
	/**
	 * Perform click action on Document button
	 */
	public void document() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdAddDocuments_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
		if(!value.trim().equals(component)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul//li[text()='"
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

		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li"));
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
	 * Takes sub id component name as input and select from dropdwon
	 * 
	 * @param component sub id component name
	 */
	public void subIDComponent(String component) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlIdCheckComponent_Input");
		
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='"
							+ component + "']");
	}
	/**
	 * Takes name as input Pass it to name On Id
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdName", name);
	}
	/**
	 * returns name on id
	 * @return name on ID card
	 */
	public String NameonID() {
		String name=pages.Utill().getValue("ctl00_ContentPlaceHolder1_txtIdName");
		return name;
	}
	/**
	 * Takes id card number as input and pass it to ID Number
	 * @param number on Id card
	 */
public void IDNumber(String number) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdNumber", number);
	}
/**
 * Takes name as input and pass it to issue date (dd/mm/yyy)
 * @param date ID card issued date
 */
public void IssueDate(String date) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdIssueDate_dateInput", date);
}
/**
 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
 * @param date ID card Expiry date
 */
public void ExpiryDate(String date) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdExpireDate_dateInput", date);
}
/**
 * select India as country 
 * @deprecated country disabled for id card
 */
public void Country() {
	boolean re=false;
	if(re) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueCountry_Input", "India");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li[1]")));
	pages.Utill().click(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCountry_DropDown']/div/ul/li//text()='India'");
	pages.Utill().waitUntilLoaderisInvisible(100);
}
}
/**
 * select tamil nadu as state
 */
public void State() {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueState_Input", "tamil nadu");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul/li[1]")));
	pages.Utill().click(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
	pages.Utill().waitUntilLoaderisInvisible(100);
}

/**
 * select chennai as city
 */
public void City() {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_ddlIdIssueCity_Input", "chennai");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul/li[1]")));
	pages.Utill().click(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlIdIssueCity_DropDown']/div/ul//li[text()='Chennai']");
	pages.Utill().waitUntilLoaderisInvisible(100);
}
/**
 * Takes no as input and pass it to enrollment number field
 * @param no enrollment number
 */
public void EnrollmentNo(String no) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtEnrollId1", no);
}
/**
 * comments
 * 
 * @param comments address comments
 */
public void comments(String comments) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_txtIdComments", comments);
}

/**
 * click report insuff button
 */
public void ReportInsuff() {
	pages.Utill().click("ctl00_ContentPlaceHolder1_chkIdInsuff");
	pages.Utill().waitUntilLoaderisInvisible(100);
}

/**
 * Takes insufff comments as input and pass it
 * 
 * @param comments insuff raise comments
 */
public void Insuffcomm(String comments) {
	pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_chkComponentNotApplicable", comments);
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
 * click submit button on id data entry
 * @throws Exception WebDriverException
 */
public void submit() throws Exception{
	int count=driver.findElements(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlIdComponent_DropDown']/div/ul/li")).size();
	pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdSaveSubmit_input");
	pages.Utill().waitUntilLoaderisInvisible(100);
	if(count==2) {
		pages.Utill().SwitchDefault();	
	}
	pages.Utill().confirmAlert();
}
/**
 * performs click action on save button
 */
public void save() throws Exception {
	pages.Utill().click("ctl00_ContentPlaceHolder1_btnIdSave_input");
	pages.Utill().waitUntilLoaderisInvisible(100);
	pages.Utill().confirmAlert();
}
/**
 * Performs click action on add document button in document upload screen
 */
public void AddDocument() {
	pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_btnIdAddDocument_input");
	pages.Utill().waitUntilLoaderisInvisible(100);
}

/**
 * Takes document type as input and checks for given document type available in upload screen
 * @param doctype type of document
 * @return true when document ype was available
 */
public boolean isvaliddoctype(String doctype) {
pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00__0']/td[2]", 10);
	boolean re =false;
	String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_grdviewIdDocument_ctl00']/tbody/tr/td[2]";
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
	pages.Utill().click("ctl00_ContentPlaceHolder1_rwmCaseIdDocuments_C_btnIdDocumentCancel_input");
	pages.Utill().waitUntilLoaderisInvisible(100);
}
/**
 * Takes input from id.properties file and pass it to id check data entry of passport
 * @throws Exception webdriver exception
 */
public void Passport()throws Exception {
	Properties pro = pages.Utill().dedata("id");
	this.idcheck();
	this.Component("Passport");
	this.NameonID(pro.getProperty("PNameonID"));
	this.IDNumber(pro.getProperty("PIDNumber"));
	this.IssueDate(pro.getProperty("PIssueDate"));
	this.ExpiryDate(pro.getProperty("PExpiryDate"));
	this.State();
	this.City();
	this.document();
	this.UploadDocument("Passport Scan - Front", pro.getProperty("passportdoc"));
	this.docclose();
	this.comments(pro.getProperty("Pcomments"));
	this.submit();
}
/**
 * Takes input from id.properties file and pass it to id check data entry of Aadhar card
 * @throws Exception webdriver exception
 */
public void AadharCard()throws Exception {
	Properties pro = pages.Utill().dedata("id");
	this.idcheck();
	this.Component("Aadhaar Card");
	if(!(this.NameonID().length()>1)) {
	this.NameonID(pro.getProperty("NameonID"));
	this.IDNumber(pro.getProperty("IDNumber"));
	this.IssueDate(pro.getProperty("IssueDate"));
	this.ExpiryDate(pro.getProperty("ExpiryDate"));
	this.State();
	this.City();
	this.EnrollmentNo(pro.getProperty("EnrollmentNo"));
	this.comments(pro.getProperty("comments"));
	}
	this.submit();
}
}
