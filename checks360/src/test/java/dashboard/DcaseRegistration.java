package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class DcaseRegistration {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Case Registration Stage
	 * 
	 * @param logger logger instance
	 */
	public DcaseRegistration(ExtentTest logger) {
		
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * select Case Registration from stages dropdown
	 */
	public void caseRegistration() {
		pages.Utill().select_by_value("ddlAct", "0");
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
}
