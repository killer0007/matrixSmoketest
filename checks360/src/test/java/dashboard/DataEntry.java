package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class DataEntry {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public DataEntry(ExtentTest logger) {
		
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * select data entry from stages dropdown
	 */
	public void datanentry() {
		pages.Utill().select_by_value("ddlAct", "2");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	/**
	 * Takes First Name as input and pass it to First Name field
	 * 
	 * @param firstname candidate First Name
	 */
	public void firstname(String firstname) {
		pages.Utill().input_text("txtFirstName", firstname);
	}

	/**
	 * Takes Last Name as input and pass it to Last Name field
	 * 
	 * @param lastname candidate Last Name
	 */
	public void lasttname(String lastname) {
		pages.Utill().input_text("txtLastName", lastname);
	}

	/**
	 * Takes caserefno as input and pass it to caserefno field
	 * 
	 * @param no caserefno
	 */
	public void caserefno(String no) {
		pages.Utill().input_text("txtCaserefNo", no);
	}

	/**
	 * Takes clientrefno as input and pass it to clientrefno field
	 * 
	 * @param no clientrefno
	 */
	public void clientrefno(String no) {
		pages.Utill().input_text("txtClientrefNo", no);
	}

	/**
	 * Takes client name as input and selects the name from Client dropdown
	 * 
	 * @param name Client Name
	 */
	public void client(String name) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", name);
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	/**
	 * Takes Project name as input and selects the name from Project dropdown
	 * 
	 * @param name Project Name
	 */
	public void project(String name) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", name);
	}

	/**
	 * Performs click action on search button
	 */
	public void search() {
		pages.Utill().click_element("btnsearch");
		pages.Utill().wait_until_loader_is_invisible(10);
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
		String re = pages.Utill().get_text("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
		if (re.equals(null))
			re = pages.Utill().get_text("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
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
		String no = pages.Utill().GetTableCellValue("grdTaskList", 1, 2);
		return no;
	}

	/**
	 * Takes Case Reference number as input and select the case
	 * 
	 * @param refno Case Reference number
	 */
	public void selectcase(String refno) {
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}

}
