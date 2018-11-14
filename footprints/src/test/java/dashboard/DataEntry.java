package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class DataEntry extends Utill{
	/**
	 * This is class for Data Entry Stage
	 * @param logger logger instance
	 */
	public DataEntry(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * select data entry from stages dropdown
	 */
	public void datanentry() {
		selectByValue("ddlAct", "2");
		waitUntilLoaderisInvisible(50);
	}

	/**
	 * Takes First Name as input and pass it to First Name field
	 * 
	 * @param firstname candidate First Name
	 */
	public void firstname(String firstname) {
		sendKeys("txtFirstName", firstname);
	}

	/**
	 * Takes Last Name as input and pass it to Last Name field
	 * 
	 * @param lastname candidate Last Name
	 */
	public void lasttname(String lastname) {
		sendKeys("txtLastName", lastname);
	}

	/**
	 * Takes caserefno as input and pass it to caserefno field
	 * 
	 * @param no caserefno
	 */
	public void caserefno(String no) {
		sendKeys("txtCaserefNo", no);
	}

	/**
	 * Takes clientrefno as input and pass it to clientrefno field
	 * 
	 * @param no clientrefno
	 */
	public void clientrefno(String no) {
		sendKeys("txtClientrefNo", no);
	}

	/**
	 * Takes client name as input and selects the name from Client dropdown
	 * 
	 * @param name Client Name
	 */
	public void client(String name) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", name);
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Takes Project name as input and selects the name from Project dropdown
	 * 
	 * @param name Project Name
	 */
	public void project(String name) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", name);
	}

	/**
	 * Performs click action on search button
	 */
	public void search() {
		click("btnsearch");
		waitUntilLoaderisInvisible(10);
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
	 * Takes Candidate first name, last name as input perform search based on input
	 * and return reference number
	 * 
	 * @param firstname Candidate First Name
	 * @param lastname  Candidate Last Name
	 * @return refno Case Reference number
	 * @throws Exception when refno not found
	 */
	public String getrefno(String firstname, String lastname) throws Exception {
		this.firstname(firstname);
		this.lasttname(lastname);
		this.search();
		String no = getTableCellValue("grdTaskList", 1, 2);
		return no;
	}

	/**
	 * Takes Case Reference number as input and select the case
	 * 
	 * @param refno Case Reference number
	 */
	public void selectcase(String refno) {
		click("//span[text()='" + refno + "']");
		waitUntilLoaderisInvisible(100);
	}

}
