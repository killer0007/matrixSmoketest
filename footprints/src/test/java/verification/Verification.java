package verification;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public abstract class Verification {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Verification(WebDriver driver,ExtentTest logger) {
		this.driver =driver;
		this.logger = logger;
		pages = new Pages(driver,logger);
	}
	/**
	 * Takes doctype and file name as input and waits for given document to upload
	 * @param doctype type of document
	 * @param filepath file to upload
	 */
	public void WaitforFileUpdate(String doctype,String filepath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String file=new File(filepath).getName();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[text()='"+doctype+"']/../td[5]//div/ul/li[1]/span/span"), file));
	}
	public void UpdateReportComments() {
		int count=driver.findElements(By.xpath("//*[@id='accordion']/div")).size();
		
		for (int i = 1; i < count; i++) {
			if(i>1) {
				pages.Utill().click("//*[@id='accordion']/div["+Integer.toString(i)+"]//b");	
			}
			
			String info=pages.Utill().getText("//*[@id='accordion']/div["+Integer.toString(i)+"]//td[2]/span");
			pages.Utill().sendKeys("//*[@id='accordion']/div["+Integer.toString(i)+"]//div[3]/div/p/..", info);
		}
	}
}
