package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class CaseTracker extends Utill{
	ExtentTest logger;

	/**
	 * This is class for CaseTracker Page
	 * 
	 * @param logger logger instance
	 */
	public CaseTracker(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}

	/**
	 * Takes case Reference Number as input pass the reference number to search box
	 * field and hit search
	 * 
	 * @param refno Case Reference Number
	 */
	public void search(String refno) {
		click("searchBox");
		sendKeys("searchBox", refno);
		this.search();
		// waitUntilLoaderisInvisible(10);
	}

	/**
	 * Performs click action on search button
	 */
	public void search() {
		click("//span[text()=' Search']");
		waitUntilLoaderisInvisible(10);
	}

	/**
	 * Takes case Reference Number as input and click on it
	 * 
	 * @param refno Case Reference Number
	 */
	public void clickcase(String refno) {
		click("linkText:" + refno);
		waitUntilElementHasText(
				"//div[@class='modal-content']//table[@id='grdTaskList']/tbody/tr[1]/td[1]/span", 10);

	}

	/**
	 * Takes Component name as input and returns current stage of that component
	 * from case tracker
	 * 
	 * @param componentName Sub component name
	 * @return Current Stage
	 */
	public String getCurrentStage(String componentName) {

		return getText("//span[text()='" + componentName + "']/../../td[5]");
	}

	/**
	 * Takes Component name and refno as input and returns current stage of that
	 * component from case tracker
	 * 
	 * @param refno         Case Reference Number
	 * @param componentName Sub component name
	 * @return Current Stage
	 */
	public String getCurrentStage(String refno, String componentName) {
		this.search(refno);
		this.clickcase(refno);
		return this.getCurrentStage(componentName);
	}

	/**
	 * Takes Component name as input and returns responsible Person from case
	 * tracker
	 * 
	 * @param componentName Sub component name
	 * @return name responsible person name
	 */
	public String responsiblePerson(String componentName) {

		return getText("//span[text()='" + componentName + "']/../../td[6]").trim();
	}

	/**
	 * Takes Component name and refno as input and returns responsible Person from
	 * case tracker
	 * 
	 * @param refno         Case Reference Number
	 * @param componentName Sub component name
	 * @return name responsible person name
	 */
	public String responsiblePerson(String refno, String componentName) {
		this.search(refno);
		this.clickcase(refno);
		return this.responsiblePerson(componentName).trim();
	}

	/**
	 * returns the entire case tracker details as list
	 * 
	 * @return List of hashmap
	 * @throws Exception case not found
	 */
	public List<HashMap<String, String>> getcasedata() throws Exception {
//		Thread.sleep(5000);
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		int row = driver.findElements(By.xpath("//div[@class='modal-content']//table[@id='grdTaskList']/tbody/tr"))
				.size();
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				HashMap<String, String> rowdata = new HashMap<String, String>();
				List<WebElement> col = driver.findElements(By
						.xpath("//div[@class='modal-content']//table[@id='grdTaskList']/tbody/tr[" + (i + 1) + "]/td"));
				for (int j = 0; j < col.size(); j++) {
					String temp = col.get(j).getText();

					switch (j) {
					case 0:
						rowdata.put("ComponentName", temp);
						break;
					case 1:
						rowdata.put("ReceivedOn", temp);
						break;
					case 2:
						rowdata.put("Status", temp);
						break;
					case 3:
						rowdata.put("ComponentOutcome", temp);
						break;
					case 4:
						rowdata.put("CurrentStage", temp);
						break;
					case 5:
						rowdata.put("PersonResponsible", temp);
						break;
					default:
						throw new NotFoundException();
					}
				}
				data.add(rowdata);
			}
			return data;
		} else {
			throw new NoSuchElementException(row + "  record found");
		}
	}

	/**
	 * closes the case tracker window
	 */
	public void cancel() {
//		click("//button[@class='close']");
		closeTab();
		switchWindow(0);
	}

	/**
	 * Takes case tracker details as input and returns only component name
	 * 
	 * @param input case tracker details
	 * @return component name
	 */
	public List<String> getcomponentname(List<HashMap<String, String>> input) {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < input.size(); i++) {
			data.add(input.get(i).get("ComponentName"));
		}
		return data;
	}
}
