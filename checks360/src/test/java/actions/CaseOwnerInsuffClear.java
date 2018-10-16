package actions;

import java.util.Properties;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.BaseClass;
import environment.Pages;

public class CaseOwnerInsuffClear extends ActionPage {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for case owner insuff clear
	 * 
	 * @param driver webdriver instance
	 * @param logger logger instance
	 */
	public CaseOwnerInsuffClear(ExtentTest logger) {
		super(logger);
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * selects case owner insuff clear from dropdown
	 */

	public void caseOwner() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_ddlAct_Input");
		pages.Utill().wait_until_element_isvisible("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[1]", 10);
		pages.Utill().click_element("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[1]");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	/**
	 * perform click action on clear button in list
	 */
	public void openCase() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdTaskList_ctl00_ctl04_btnClearInsuff");
		pages.Utill().wait_until_loader_is_invisible(60);
	}

	/**
	 * Takes the component name as input and returns insuff raised comments
	 * 
	 * @param componentname like current address, permanent etc.,
	 * @return insuff raised comments
	 */
	public String getComments(String componentname) {
		return pages.Utill().get_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[27]");
	}

	/**
	 * Takes the component name as input and return the name which stage insuff
	 * raised
	 * 
	 * @param componentname like current address, permanent etc.,
	 * @return name of stage
	 */
	public String getRaisedFrom(String componentname) {
		return pages.Utill().get_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[29]");
	}

	/**
	 * Takes component name and clear comments as input and pass the comments
	 * 
	 * @param componentname like current address, permanent etc.,
	 * @param comments      insuff clear comments
	 */
	public void clearComments(String componentname, String comments) {
		this.select(componentname);
		pages.Utill().input_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[30]/input",
				comments);
	}

	/**
	 * Takes component name as input and click on upload button
	 * 
	 * @param componentname like current address, permanent etc.,
	 */
	private void upload(String componentname) {
		pages.Utill().click_element(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[32]//input");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

	/**
	 * Performs click action on clear insuff button
	 */
	public void clear() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_btnClear_input");
	}

	/**
	 * Performs click action on cancel button
	 */
	public void cancel() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_btnCancel_input");
	}

	/**
	 * Takes Component name as input and select the check box
	 * 
	 * @param componentname like current address, permanent etc.,
	 */

	private void select(String componentname) {
		pages.Utill().click_element(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[1]//input");
	}

	/**
	 * Takes the component name as input and returns the name who raised insuff
	 * 
	 * @param componentname like current address, permanent etc.,
	 * @return name who raised insuff
	 */
	public String rasiedBy(String componentname) {
		return pages.Utill().get_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwInsuffClearWindow_C_grdInsuffClear_ctl00']//td[text()='"
						+ componentname + "']/../td[23]");
	}

	/**
	 * Takes document type and file path as input and uploads the given file
	 * 
	 * @param doctype  type of document
	 * @param filename file name with file path
	 * @throws Exception when document not found
	 */
	private void upload(String doctype, String filename) throws Exception {
		Thread.sleep(1000);
		pages.Utill().input_text(
				"//table[@id='ctl00_ContentPlaceHolder1_rdwDocumentUpload_C_grdUploadDocuments_ctl00']//td[text()='"
						+ doctype + "']/../td[6]/div/ul/li/span/input[2]",
				filename);
		Thread.sleep(1000);
		this.addDocument();
	}

	/**
	 * Takes componentname, document type and file path as input and uploads the
	 * given file
	 * 
	 * @param doctype       type of document
	 * @param filename      file name with file path
	 * @param componentname like current address, permanent etc.,
	 * @throws Exception when document not found
	 */
	public void upload(String componentname, String doctype, String filename) throws Exception {
		this.upload(componentname);
		this.upload(doctype, filename);
	}

	/**
	 * Returns the reference number from table
	 * 
	 * @return reference number
	 */
	public String getrefNo() {
		return pages.Utill().get_text("//table[@id='ctl00_ContentPlaceHolder1_grdTaskList_ctl00']/tbody/tr[1]/td[5]");
	}

	/**
	 * performs the click action on Add Document button and closes the upload popup
	 */
	private void addDocument() {
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_rdwDocumentUpload_C_btnSubmitAddedDocument");
		pages.Utill().wait_until_loader_is_invisible(70);
		this.close();
	}

	/**
	 * takes the list of components and uploads the documents
	 * 
	 * @param componentlist List of component name
	 * @throws Exception when file not found
	 */
	public void uploadMultiplecom(String[] componentlist) throws Exception {
		Properties loc = BaseClass.getlocator();
		for (int i = 0; i < componentlist.length; i++) {
			String name = componentlist[i].toString();
			switch (name) {
			case "Current Address":
				this.upload(name);
				this.upload("Address Proof", loc.getProperty("addressinsuffdoc"));
				break;
			case "UG1":
				this.upload(name);
				this.upload("Degree Certificate", loc.getProperty("eduinsuffdoc"));
				break;
			case "Current/Latest Employment":
				this.upload(name);
				this.upload("Relieving Letter", loc.getProperty("empinsuffdoc"));
				break;
			case "Reference 1":
				this.upload(name);
				this.upload("Others", loc.getProperty("refinsuffdoc"));
				break;
			case "Aadhaar Card":
				this.upload(name);
				this.upload("Aadhaar Id - Front", loc.getProperty("idinsuffdoc"));
				break;
			case "Current Address Criminal Check":
				this.upload(name);
				this.upload("Address Proof", loc.getProperty("criminalinsuffdoc"));
				break;
			case "Current Address Court Check":
				this.upload(name);
				this.upload("Address Proof", loc.getProperty("courtinsuffdoc"));
				break;
			case "Credit Check 1":
				this.upload(name);
				this.upload("Aadhaar Id - Back", loc.getProperty("creditinsuffdoc"));
				break;
			case "Database":
				this.upload(name);
				this.upload("Others", loc.getProperty("dbinsuffdoc"));
				break;
			case "Panel1":
				break;
			default:
				throw new NotFoundException(name);
			}

		}
		for (int i = 0; i < componentlist.length; i++) {
			String name = componentlist[i].toString();
			this.clearComments(name, name + " clear");
		}
		this.clear();
		pages.Utill().confirmAlert();

	}

	/**
	 * performs the click action on close button
	 */
	public void close() {
		pages.Utill().click_element(
				"//div[@id='RadWindowWrapper_ctl00_ContentPlaceHolder1_rdwDocumentUpload']//tr//td//span[contains(text(),'Close')]");
		;
	}

	/**
	 * Takes componentname and comments as input pass the comments in clear comments
	 * and clear the insuff
	 * 
	 * @param componentname like current address, permanent etc.,
	 * @param comments      insuff clear comments
	 */
	public void insuffClear(String componentname, String comments) {
		this.clearComments(componentname, comments);
		this.clear();

	}

}
