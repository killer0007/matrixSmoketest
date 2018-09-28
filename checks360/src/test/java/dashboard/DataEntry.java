package dashboard;

import org.apache.commons.math3.exception.NoDataException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public class DataEntry {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public DataEntry(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public void datanentry() {
		pages.Utill().select_by_value("ddlAct", "2");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	public void firstname(String firstname) {
		pages.Utill().input_text("txtFirstName", firstname);
	}

	public void lasttname(String lastname) {
		pages.Utill().input_text("txtLastName", lastname);
	}

	public void caserefno(String no) {
		pages.Utill().input_text("txtCaserefNo", no);
	}

	public void clientrefno(String no) {
		pages.Utill().input_text("txtClientrefNo", no);
	}

	public void client(String name) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", name);
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void project(String name) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", name);
	}

	public void search() {
		pages.Utill().click_element("btnsearch");
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void search(String refno) {
		this.caserefno(refno);
		this.search();
	}

	public String getSearchResult(String refno) {
		this.search(refno);
		String re = pages.Utill().get_text("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
		if (re.equals(null))
			re = pages.Utill().get_text("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
		return re;

	}

	public String getrefno(String firstname, String lastname) throws NoDataException {
		this.firstname(firstname);
		this.lasttname(lastname);
		this.search();
		String no = pages.Utill().GetTableCellValue("grdTaskList", 1, 2);

		return no;
	}

	public void selectcase(String refno) {
		pages.Utill().click_element("//span[text()='" + refno + "']");
		pages.Utill().wait_until_loader_is_invisible(100);
	}
	
}
