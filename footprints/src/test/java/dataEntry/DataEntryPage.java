package dataEntry;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import environment.Utill;

public abstract class DataEntryPage extends Utill {

	public DataEntryPage(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
		
	}
	/**
	 * Performs click action on History button
	 */
	public void history() {
		click("ctl00_ContentPlaceHolder1_btnHistoryPage_input");
		waitUntilLoaderisInvisible(50);
	}
	/**
	 * returns name who raised insuff
	 * @return name employee name
	 */
	public String getraisedBy() {
		return super
				.getText("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaisedBy")
				.trim();
	}
	/**
	 * returns stage in which stage insuff raised
	 * @return stage name
	 */
	public String getraisedStage() {
		return super
				.getText("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaiseState")
				.trim();
	}
	/**
	 * returns comments which given when the time if insuff raise
	 * @return insuff raised comments
	 */
	public String getraisedComments() {
		return super
				.getText("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblRaisedRemarks")
				.trim();
	}
	/**
	 * return employee name who cleared the insuff
	 * @return name employee name
	 */
	public String getclearedBy() {
		return super
				.getText("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblClearedByName")
				.trim();
	}
	/**
	 * return insuff clear comments
	 * @return comments insuff clear
	 */
	public String getclearedComments() {
		return super
				.getText("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblClearComments")
				.trim();
	}
	/**
	 * Performs click action on close button in document upload popup
	 */
	public void close() {
		click("//a[@class='rwCloseButton']");
	}
	/**
	 * returns document name which uploaded for insuff clear
	 * @return document name
	 */
	public String historyDocument() {
		String dc = super
				.getText("ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_grdInsuffHistory_ctl00_ctl04_lblDoc");
		return dc.replaceAll("[0-9]", "");
	}
/**
 * Performs click action on insuff tab in history popup
 */
	public void Insuff() {
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_RadTabStripForHistory']/div//li[1]");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Performs click action on Redo tab in history popup
	 */
	public void Redo() {
		click(
				"//*[@id='ctl00_ContentPlaceHolder1_rdmHistoryPopup1_C_RadTabStripForHistory']/div//li[2]");
		waitUntilLoaderisInvisible(100);
	}
	/**
	 * Takes doctype and file name as input and waits for given document to upload
	 * @param doctype type of document
	 * @param filepath file to upload
	 */
	public void WaitforFileUpdate(String doctype,String filepath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String file=new File(filepath).getName();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[text()='"+doctype+"']/../td[5]//div/ul/li[1]/span/span"), file));
		String name =getText("//*[text()='"+doctype+"']/../td[5]//div/ul/li[1]/span/span");
		logger.log(Status.INFO, name);
//		try {
//			logger.info(name, MediaEntityBuilder.createScreenCaptureFromPath(Utill.getScreenshot(driver)).build());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("----------------------------"+name+"---------------");
	}
	
public abstract void document();
public abstract void submit() throws Exception;	
public abstract void save() throws Exception;
public abstract void ReportInsuff();
public abstract void Insuffcomm(String comments);
public abstract void Notapplicable();
public abstract void Notapplicablecomm(String comments);
}

