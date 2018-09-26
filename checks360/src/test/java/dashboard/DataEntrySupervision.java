package dashboard;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class DataEntrySupervision {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public DataEntrySupervision(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public void datanentrysupervision() {
		pages.Utill().select_by_value("ddlAct", "1");
		pages.Utill().wait_until_loader_is_invisible(50);
	}

	public void view(String caserefno) {
		pages.Utill().click_element("//span[text()='" + caserefno + "']/../following-sibling::td[14]/a");
	}

	public void caserefno(String refno) {
		pages.Utill().input_text("txtCaserefNo", refno);
	}

	public void search() {
		pages.Utill().click_element("btnsearch");
		pages.Utill().wait_until_loader_is_invisible(60);

	}
	public String getSearchResult(String refno) {
		this.search(refno);
		String re=pages.Utill().get_text("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
		if(re.equals(null))
			re=pages.Utill().get_text("//*[@id='grdTaskList']/tbody/tr/td[2]/span").trim();
			return re;
	}

	public void assign() {
		pages.Utill().click_element("btnBulkAllocation");
		pages.Utill().wait_until_loader_is_invisible(60);

	}

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

	public void search(String refno) {
		this.caserefno(refno);
		this.search();

	}

	public void assign(String refno, String empname) throws Exception {
		this.search(refno);
		this.assign(empname);

	}

	public void raiseinsuff(String checkname, String comments) {
		pages.Utill().input_text("//span[text()='" + checkname + " ']/..//following-sibling::td[3]//td[1]/input",
				comments);
		pages.Utill().click_element("//span[text()='" + checkname + " ']/..//following-sibling::td[3]//td[2]/input");
		pages.Utill().click_element("//input[@value='OK']");
	}

	public void close() {
		pages.Utill().click_element("//span[text()='×']");
	}
}
