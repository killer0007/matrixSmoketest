package dashboard;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.BaseClass;
import environment.Pages;

public class DataEntryQCSupervision {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Data Entry QC Supervision Stage
	 * 
	 * @param logger logger instance
	 */
	public DataEntryQCSupervision(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}

	/**
	 * select data entry supervision QC from stages dropdown
	 */
	public void datanentryqcsupervision() {
		pages.Utill().select_by_value("ddlAct", "3");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	/**
	 * Takes Case Reference number as input and select the case
	 * 
	 * @param caserefno Case Reference number
	 */
	public void view(String caserefno) {
		pages.Utill().click_element("//span[text()='" + caserefno + "']/../following-sibling::td[14]/a");
	}

	/**
	 * Takes caserefno as input and pass it to caserefno field
	 * 
	 * @param refno caserefno
	 */
	public void caserefno(String refno) {
		pages.Utill().clear_element_text("txtCaserefNo");
		pages.Utill().input_text("txtCaserefNo", refno);
	}

	/**
	 * Performs click action on search button
	 */
	public void search() {
		pages.Utill().click_element("btnsearch");
		pages.Utill().wait_until_loader_is_invisible(60);

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
	 * Performs click action in assign button
	 */
	public void assign() {
		pages.Utill().click_element("btnBulkAllocation");
		pages.Utill().wait_until_loader_is_invisible(60);

	}

	/**
	 * Takes employee Name as input and assign the case to given employee
	 * 
	 * @param empname Employename
	 * @throws Exception employe not found in list
	 */
	public void assign(String empname) throws Exception {
		pages.Utill().click_element("chkboxsingle");
		this.assign();
		int count = pages.DbConnection().getAssignedCount();
		pages.Utill().select_by_label("ddlFilteredTMforAllocation", empname + " (" + count + ")");
		pages.Utill().click_element("//div[@class='modal-dialog modal-lg']//button[text()='Ok']");
		Thread.sleep(1000);
		pages.Utill().click_element("//div[@class='modal-content']//button[contains(text(),'Yes')]");
		pages.Utill().wait_until_loader_is_invisible(100);
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
		pages.Utill().input_text("//span[text()='" + checkname + " ']/..//following-sibling::td[3]//td[1]/input",
				comments);
		pages.Utill().click_element("//span[text()='" + checkname + " ']/..//following-sibling::td[3]//td[2]/input");
		pages.Utill().click_element("//input[@value='OK']");
	}

	/**
	 * Perform close action in popup
	 */
	public void close() {
		pages.Utill().click_element("//span[text()='×']");
	}

	/**
	 * Takes Case Reference Number as input and assign the case to demoempl employee
	 * 
	 * @param refno Case Reference Number
	 * @throws Exception when employee name not found
	 */
	public void assigngetnext(String refno) throws Exception {
		this.search(refno);
		if(pages.Utill().getSelectedvalue("//select[@ng-model='Emp.Priority']").equals("Normal")) {
		pages.Utill().select_by_label("//select[@ng-model='Emp.Priority']", "High");
		pages.Utill().wait_until_loader_is_invisible(20);
		pages.Utill().click_element("xpath:html/body/div[3]/div/div/table/tbody/tr[3]/td/button[1]");
		pages.Utill().wait_until_loader_is_invisible(20);
		}
		pages.Utill().select_by_label("Reserverfor", "demoempl");
		pages.DataEntryQC().datanentryqc();
		pages.Utill().click_element("btnGetNext");
		pages.Utill().wait_until_loader_is_invisible(40);
		pages.Utill().click_element("imgHome");
		pages.Utill().wait_until_loader_is_invisible(40);
		this.datanentryqcsupervision();
	}
}
