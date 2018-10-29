package dataEntry;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Credit extends DataEntryPage{

	/**
	 * This is class for Credit page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Credit(ExtentTest logger) {
		super(logger);
	}
	/**
	 * Select Credit tab and switch to Credit frame
	 */
	public void creditcheck() {
		pages.Utill().SwitchDefault();
//		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li[7]/a/span/span/span");
		pages.Utill().click_element("//*[@id='tabStrip']/div/ul/li//span[text()='Credit']");
		pages.Utill().SwitchFramebyIndex(6);
	}
/**
 * Performs click action on History button
 */
	public void history() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnHistoryPage_input");
		pages.Utill().wait_until_loader_is_invisible(50);
	}
	/**
	 * returns name who raised insuff
	 * @return name employee name
	 */
	public String getraisedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblRaisedBy")
				.trim();
	}
/**
 * returns stage in which stage insuff raised
 * @return stage name
 */
	public String getraisedStage() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblRaiseState")
				.trim();
	}
/**
 * returns comments which given when the time if insuff raise
 * @return insuff raised comments
 */
	public String getraisedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}
/**
 * return employee name who cleared the insuff
 * @return name employee name
 */
	public String getclearedBy() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblClearedByName")
				.trim();
	}
	/**
	 * return insuff clear comments
	 * @return comments insuff clear
	 */
	public String getclearedComments() {
		return pages.Utill()
				.get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblClearComments")
				.trim();
	}
	/**
	 * Performs click action on close button in document upload popup
	 */
	public void close() {
		pages.Utill().click_element("//a[@class='rwCloseButton']");
	}
	/**
	 * returns document name which uploaded for insuff clear
	 * @return document name
	 */
	public String historyDocument() {
		return pages.Utill().get_text("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdCreditInsuffHistory_ctl00_ctl04_lblDoc").replaceAll("[0-9]", "");
	}
	/**
	 * Performs click action on Document button
	 */
	public void document() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCreditAddDocuments_input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes component name as input and select from dropdwon
	 * 
	 * @param component sub component name
	 */
	public void Component(String component) {
		String value=pages.Utill().getvalue("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
		if(!value.trim().equals(component)) {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlcreditComponent_Input");
		if (verifyddvalue(component)) {
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlcreditComponent_DropDown']/div/ul//li[text()='"
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

		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlcreditComponent_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(component)) {
					re = true;
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
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlCreditId_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul/li[1]")));
			pages.Utill()
					.click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlCreditId_DropDown']/div/ul//li[text()='"
							+ component + "']");
			pages.Utill().wait_until_loader_is_invisible(100);
	}
	/**
	 * Takes name as input Pass it to name On Id
	 * @param name on ID card
	 */
	public void NameonID(String name) {
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCreditIdName", name);
	}
	/**
	 * Takes id card number as input and pass it to ID Number
	 * @param number on Id card
	 */
public void IDNumber(String number) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCreditIdNumber", number);
	}
/**
 * Takes name as input and pass it to issue date (dd/mm/yyy)
 * @param date ID card issued date
 */
public void IssueDate(String date) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCreditIdIssueDate_dateInput", date);
}
/**
 * Takes name as input and pass it to Expiry date (dd/mm/yyy)
 * @param date ID card Expiry date
 */
public void ExpiryDate(String date) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCreditIdExpireDate_dateInput", date);
}
/**
 * select India as country 
 * @deprecated country disabled for id card
 */
public void Country() {
	boolean re=false;
	if(re) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_Input","India");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCountry_DropDown']/div/ul/li//text()='India'");
	pages.Utill().wait_until_loader_is_invisible(100);
}
}
/**
 * select tamil nadu as state
 */
public void State() {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlCreditIssueState_Input", "Tamil Nadu");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueState_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueState_DropDown']/div/ul//li[text()='Tamil Nadu']");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * select chennai as city
 */
public void City() {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_ddlCreditIssueCity_Input", "Chennai");
	new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
			By.xpath("//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCity_DropDown']/div/ul/li[1]")));
	pages.Utill().click_element(
			"//*[@id='ctl00_ContentPlaceHolder1_ddlCreditIssueCity_DropDown']/div/ul//li[text()='Chennai']");
	pages.Utill().wait_until_loader_is_invisible(100);
}
/**
 * comments
 * 
 * @param comments address comments
 */
public void comments(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCreditComments", comments);
}

/**
 * click report insuff button
 */
public void ReportInsuff() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_chkCreditInsuff");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * Takes insufff comments as input and pass it
 * 
 * @param comments insuff raise comments
 */
public void Insuffcomm(String comments) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCreditInsuffRemarks", comments);
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
 * click submit button on credit data entry
 * @throws Exception WebDriverException
 */
public void submit() throws Exception{
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCreditSaveSubmit_input");
	pages.Utill().wait_until_loader_is_invisible(100);
	pages.Utill().SwitchDefault();	
	pages.Utill().confirmAlert();
}
/**
 * performs click action on save button
 */
public void save() throws Exception {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnCreditSave_input");
	pages.Utill().wait_until_loader_is_invisible(100);
	pages.Utill().confirmAlert();
}
/**
 * Takes no as input and pass it to Enrollment number
 * @param no Enrollment number
 */
public void EnrollmentNo(String no) {
	pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCreditEnrollId1", no);
}

/**
 * Performs click action on add document button in document upload screen
 */
public void AddDocument() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_btnCreditAddDocument_input");
	pages.Utill().wait_until_loader_is_invisible(100);
}

/**
 * Takes document type as input and checks for given document type available in upload screen
 * @param doctype type of document
 * @return true when document ype was available
 */
public boolean isvaliddoctype(String doctype) {
pages.Utill().wait_element_has_text("//*[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00__0']/td[2]", 10);
	boolean re =false;
	String path="//*[@id='ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_grdviewCreditDocument_ctl00']/tbody/tr/td[2]";
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
 * Perform close action on close button in document upload popup
 */
public void docclose() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_rwmCaseCreditDocuments_C_btnCreditDocumentCancel_input");
	pages.Utill().wait_until_loader_is_invisible(100);
}
/**
 * takes input from credit.properties file and completes the data entry
 * @throws Exception webDriverException
 */
public void Creditone() throws Exception{
	Properties pro = pages.Utill().dedata("credit");
	this.creditcheck();
	this.subIDComponent(pro.getProperty("ID"));
	this.NameonID(pro.getProperty("NameonID"));
	this.IDNumber(pro.getProperty("IDNumber"));
	this.IssueDate(pro.getProperty("IssueDate"));
	this.ExpiryDate(pro.getProperty("ExpiryDate"));
	this.State();
	this.City();
		if (pro.getProperty("ID").equals("Aadhaar Card")) {
			this.EnrollmentNo(pro.getProperty("EnrollmentNo"));
		}
		this.document();
		this.UploadDocument("Aadhaar Id - Front", pro.getProperty("Aadhardoc"));
		this.docclose();
	this.comments(pro.getProperty("comments"));
	this.submit();
}
}
