package actions;

import org.openqa.selenium.StaleElementReferenceException;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CEP extends ActionPage {

	/**
	 * This is class for CEP clear
	 * 
	 * @param logger logger instance
	 */
	public CEP(ExtentTest logger) {
		super(logger);

	}

	/**
	 * Selects the CEP clear option from dropdown
	 */
	public void CEPClear() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlAct_Input");
		pages.Utill().waitUntilElementisVisible("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[1]", 10);
		pages.Utill().click("//div[@id='ctl00_ContentPlaceHolder1_ddlAct_DropDown']//li[4]");
		pages.Utill().waitUntilLoaderisInvisible(50);
	}

	/**
	 * returns case ref no from search result
	 * 
	 * @return case refno
	 */
	public String getrefNo() {
		return pages.Utill().getText("//table[@id='ctl00_ContentPlaceHolder1_grdTaskList_ctl00']/tbody/tr[1]/td[5]");
	}

	/**
	 * Performs click action on Add Document button
	 */
	public void addDocument() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rdwClearCEP_C_btnAddDocument");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes clear comments as input and pass it to clear comments fields
	 * 
	 * @param comments clear comments
	 */
	public void clearComments(String comments) {
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_rdwClearCEP_C_txtClearedRemarks", comments);
	}

	/**
	 * Takes caserefno, clear comments as input and cleared the cep
	 * 
	 * @param refno    case reference number
	 * @param comments clear comments
	 * @throws Exception when case reference number not found
	 */
	public void clearComments(String refno, String comments) throws Exception {
		pages.Utill().click("(//td[text()='" + refno + "'])[2]");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_rdwClearCEP_C_txtClearedRemarks", comments);
		this.submit();
	}

	/**
	 * performs click action on submit button and handles success alert
	 * 
	 * @throws Exception when alert not present
	 */
	public void submit() throws Exception {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rdwClearCEP_C_ButtonSubmit_input");
//		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
	}

	/**
	 * performs click action on close button
	 */
	public void close() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_rdwClearCEP_C_ButtonClose_input");
//		pages.Utill().waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes document type, filepath as input and uploads the document to given
	 * docuemnt type
	 * 
	 * @param doctype type of document
	 * @param file    file name with path
	 * @throws Exception when file not found
	 */
	public void upload(String doctype, String file) throws Exception {
		pages.Utill().sendKeys("//td[text()='" + doctype + "']/../td[6]//span/input[2]", file);
		Thread.sleep(1000);
		this.addDocument();
	}

	/**
	 * Takes case ref no, document type, filepath as input and uploads the document
	 * to given docuemnt type
	 * 
	 * @param refno   case reference number
	 * @param doctype type of document
	 * @param file    file name with path
	 * @throws Exception when file not found
	 */
	public void upload(String refno, String doctype, String file) throws Exception {
		pages.Utill().click("//td[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		this.upload(doctype, file);
		this.submit();

	}

	/**
	 * Takes case ref no, document type, filepath and comments as input and uploads
	 * the document to given docuemnt type, pass the comments and save
	 * 
	 * @param refno    case reference number
	 * @param comments clear comments
	 * @param doctype  type of document
	 * @param file     file name with path
	 * @throws Exception when file not found
	 */
	public void upload(String refno, String comments, String doctype, String file) throws Exception {
		pages.Utill().click("(//td[text()='" + refno + "'])[2]");
		pages.Utill().waitUntilLoaderisInvisible(100);
		try {
			this.clearComments(comments);
		} catch (StaleElementReferenceException e) {
			logger.log(Status.WARNING,"StaleElementReferenceException");
			this.clearComments(comments);
		}
		this.upload(doctype, file);
		this.submit();
	}
}
