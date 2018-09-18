package pages;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;

import environment.Pages;

public class CaseTracker {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public CaseTracker(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		pages = new Pages(driver, logger);
	}
	public void  search(String refno) {
		pages.Utill().click_element("searchBox");
		pages.Utill().input_text("searchBox", refno);
		this.search();
		//pages.Utill().wait_until_loader_is_invisible(10);
	}
	public void search() {
		pages.Utill().click_element("//span[text()=' Search']");
		pages.Utill().wait_until_loader_is_invisible(10);
	}
	public void clickcase(String refno) {
		pages.Utill().click_element("linkText:"+refno);
	}
	public List<HashMap<String, String>> getcasedata() throws Exception {
		Thread.sleep(2000);
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		int row = driver.findElements(By.xpath("//div[@class='modal-content']//table[@id='grdTaskList']/tbody/tr")).size();
		for (int i = 0; i < row; i++) {
			HashMap<String, String> rowdata=new HashMap<String, String>();
			List<WebElement> col = driver.findElements(By.xpath("//div[@class='modal-content']//table[@id='grdTaskList']/tbody/tr["+(i+1)+"]/td"));
			for (int j = 0; j < col.size(); j++) {
				String temp = col.get(j).getText();
				
				switch(j) {
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
	}
	public void cancel() {
//		pages.Utill().click_element("//button[@class='close']");
		pages.Utill().closetab();
		pages.Utill().switchWindow(0);
	}
	public List<String> getcomponentname(List<HashMap<String, String>> input) {
		List< String> data = new ArrayList<String>();
		for (int i = 0; i < input.size(); i++) {
			data.add(input.get(i).get("ComponentName"));
		}
		return data;
	}
}
