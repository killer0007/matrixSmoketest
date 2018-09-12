package dashboard;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class DataEntrySupervision {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	public DataEntrySupervision(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		pages = new Pages(driver, logger);
	}
	public void view(String caserefno) {
		pages.Utill().click_element("//span[text()='"+caserefno+"']/../following-sibling::td[14]/a");
	}
	public void raiseinsuff(String checkname, String comments) {
		pages.Utill().input_text("//span[text()='"+checkname+" ']/..//following-sibling::td[3]//td[1]/input", comments);
		pages.Utill().click_element("//span[text()='"+checkname+" ']/..//following-sibling::td[3]//td[2]/input");
		pages.Utill().click_element("//input[@value='OK']");
	}
	public void close() {
		pages.Utill().click_element("//span[text()='×']");
	}
}
