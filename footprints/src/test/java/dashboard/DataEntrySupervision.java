package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class DataEntrySupervision extends Utill{
	
	/** 
	 * This is class for Data Entry Supervision Stage
	 * 
	 * @param logger logger instance
	 */
	public DataEntrySupervision(WebDriver driver, ExtentTest logger) {
		super (driver,logger);
	}

	/**
	 * select data entry supervision from stages dropdown
	 */
	public void datanentrysupervision() {
		selectByValue("ddlAct", "1");
		waitUntilLoaderisInvisible(50);
	}

	/**
	 * Takes Case Reference number as input and select the case
	 * 
	 * @param caserefno Case Reference number
	 */
	public void view(String caserefno) {
		click("//span[text()='" + caserefno + "']/../following-sibling::td[14]/a");
	}

	/**
	 * Takes caserefno as input and pass it to caserefno field
	 * 
	 * @param refno caserefno
	 */
	public void caserefno(String refno) {
		clearElementText("txtCaserefNo");
		sendKeys("txtCaserefNo", refno);
	}

	/**
	 * Performs click action on search button
	 */
	public void search() {
		click("btnsearch");
		waitUntilLoaderisInvisible(60);

	}

	/**
	 * Takes the Case Reference number and returns the Case Reference number after
	 * rearch
	 * 
	 * @param refno Case Reference number
	 * @return refno Case Reference number
	 */
	public String getSearchResult(String refno) {
		this.search(refno);
		String re = getText("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
		if (re.equals(null))
			re = getText("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
		return re;
	}

	/**
	 * Performs click action in assign button
	 */
	public void assign() {
		click("btnBulkAllocation");
		waitUntilLoaderisInvisible(60);

	}

	/**
	 * Takes employee Name as input and assign the case to given employee
	 * 
	 * @param empname Employename
	 * @throws Exception employe not found in list
	 */
	public void assign(String empname) throws Exception {
		click("chkboxsingle");
		this.assign();
		//int count = pages.DbConnection().getAssignedCount(empname);
		//selectByLabel("ddlFilteredTMforAllocation", empname + " (" + count + ")");
		click("ddlFilteredTMforAllocation");
		click(".//*[@id='ddlFilteredTMforAllocation']//option[contains(text(),'"+empname+"')]");
		click("//div[@class='modal-dialog modal-lg']//button[text()='Ok']");
		Thread.sleep(1000);
		click("//div[@class='modal-content']//button[contains(text(),'Yes')]");
		waitUntilLoaderisInvisible(100);
	}

	/**
	 * Takes Case Reference number as input and search the number
	 * 
	 * @param refno Case Reference number
	 */
	public void search(String refno) {
		this.caserefno(refno);
		this.search();

	}

	/**
	 * Takes Case Reference Number and Employee Name as input assign the case to
	 * employee
	 * 
	 * @param refno   Case Reference number
	 * @param empname Employee Name
	 * @throws Exception when Employee Name not found
	 */
	public void assign(String refno, String empname) throws Exception {
		this.search(refno);
		this.assign(empname);

	}

	/**
	 * Takes sub component name and comments as input raise the insuff for given
	 * component with comments
	 * 
	 * @param checkname Sub component name
	 * @param comments  insuff raise comments
	 */
	public void raiseinsuff(String checkname, String comments) {
		sendKeys("//span[text()='" + checkname + " ']/..//following-sibling::td[3]//td[1]/input",
				comments);
		click("//span[text()='" + checkname + " ']/..//following-sibling::td[3]//td[2]/input");
		click("//input[@value='OK']");
	}

	/**
	 * Perform close action in popup
	 */
	public void close() {
		click("//span[text()='×']");
	}

	/**
	 * Takes Case Reference Number as input and assign the case to demoempl employee
	 * 
	 * @param refno Case Reference Number
	 * @throws Exception when employee name not found
	 */
	public void assigngetnext(String refno) throws Exception {
		this.search(refno);
		if(getSelectedValue("//select[@ng-model='Emp.Priority']").equals("Normal")) {
			selectByLabel("//select[@ng-model='Emp.Priority']", "High");
			waitUntilLoaderisInvisible(20);
			click("xpath:html/body/div[3]/div/div/table/tbody/tr[3]/td/button[1]");
			waitUntilLoaderisInvisible(20);
			}
		selectByLabel("Reserverfor", "demoempl");
		new DataEntry(driver,logger).datanentry();
		click("btnGetNext");
		waitUntilLoaderisInvisible(40);
		click("imgHome");
		waitUntilLoaderisInvisible(40);
		this.datanentrysupervision();
	}
}
