package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import userLogin.Pages;

public class Assignor {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
public Assignor(WebDriver driver,ExtentTest logger) {
	this.driver=driver;
	this.logger=logger;
	pages = new Pages(driver,logger);
}
public String getlocator(String key) throws FileNotFoundException, IOException {
	Properties pr = new Properties();
	pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
	return pr.getProperty(key);
}

public String getvalue(String key) throws FileNotFoundException, IOException {
	Properties pr = new Properties();
	pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
	return pr.getProperty(key);
}
public void assigncase(String refno) throws IOException, InterruptedException {
	pages.CaseRegistration().navigateTo("Dashboard", "Assignor");
	WebElement ele=pages.Utill().find(getlocator("ass_emp"));
	if(ele.getText()!="0") {
		ele.click();
		pages.Wait().wait_until_loader_is_invisible();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_lbtnNormal")));
		ele=pages.Utill().find("ctl00_ContentPlaceHolder1_lbtnNormal");
		
		if(ele.getText()!="0") {
			ele.click();
			String table=".//*[@id='ctl00_ContentPlaceHolder1_grdEmployment']/tbody/tr/td[3]/a";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(table)));
			List<WebElement> table_data=driver.findElements(By.xpath(table));
			
			for (int i = 0; i < table_data.size(); i++) {
				String id=table_data.get(i).getText();
				if(id==refno) {
					pages.Utill().find("//*[text()='"+refno+"']/../../td[1]/input[1]").click();
					Select sel = new Select(pages.Utill().find("ctl00_ContentPlaceHolder1_ddlAssigntoEmployment"));
					sel.selectByVisibleText("demotl - Chennai");
					pages.Utill().find("ctl00_ContentPlaceHolder1_ingEmploymentAssign").click();
					pages.Wait().wait_until_loader_is_invisible();
					String ss = pages.Utill().clickAlertbox();
					System.out.println(ss);
					break;
				}
				else {
					continue;
				}
			}
		}
		else {
			System.out.println("no value on normal");
		}
		
	}
	else {
		System.out.println("empty record");
	}
}
private void assign() {
	
}
}
