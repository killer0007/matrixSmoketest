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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import testCases.Pages;

public class OperationTM {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	WebDriverWait w;

	public OperationTM(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		w = new WebDriverWait(driver, 100);
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

	public void Address(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "Residence TM");
		driver.findElement(By.linkText(no)).click();
		pages.Wait().wait_until_loader_is_invisible();

	}

	public void Education(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "Academic TM");
		String x = pages.Utill().get_text(getlocator("v_all_out"));
		if (!(x.equals("0"))) {
			pages.Utill().click_element(getlocator("v_all_out"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_normal"))));
			String y = pages.Utill().get_text(getlocator("v_normal"));
			if (!(y.equals("0"))) {
				pages.Utill().click_element(getlocator("v_normal"));
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_table"))));
				int index=0;
				String ss="";
				List<WebElement> table_list=driver.findElements(By.xpath("//*[@id='"+getlocator("v_table")+"']/tbody/tr/td[8]/a"));
				for (int i = 0; i < table_list.size(); i++) {
					String n=table_list.get(i).getText();
					if(n.equals(no)) {
						index=i+2;
						pages.Utill().click_element("//*[@id='"+getlocator("v_table")+"']/tbody/tr["+index+"]/td[5]/input");
						pages.Wait().wait_until_loader_is_invisible();
						w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_table"))));
						pages.Utill().select_by_label(getlocator("v_doctype"), "Email ID Proof");
						String file=System.getProperty("user.dir") + "\\Documents\\edu_one.pdf";
						pages.Utill().choose_file(getlocator("v_upload"), file);
						
						ss=pages.Utill().clickAlertbox();
						System.out.println(ss);
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnclose2");
						pages.Utill().click_element("//*[@id='"+getlocator("v_table")+"']/tbody/tr["+index+"]/td[1]/input[8]");
						pages.Utill().click_element(getlocator("v_edu_vr"));
						pages.Wait().wait_until_loader_is_invisible();
						
						w.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_btnInitiateVR")));
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnInitiateVR");
						pages.Wait().wait_until_loader_is_invisible();
						ss=pages.Utill().clickAlertbox();
						System.out.println(ss);
						
						break;
					}
				}
				if((index==0) || ss.equals("")) {
					System.out.println("education vr initiation failed");
					logger.fail("education vr initiation failed");
				}
				else {
					logger.pass("education vr initiation completed");
				}
			} else {
				logger.fail("no value in education allocation dashboard");
				System.out.println("no value in education allocation dashboard");
			}
		} else {
			logger.fail("no value in education allocation dashboard");
			System.out.println("no value in education allocation dashboard");
		}
		
	}
}
