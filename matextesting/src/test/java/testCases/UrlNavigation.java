package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;


public class UrlNavigation {
	WebDriver driver;
	Pages pages;
	ExtentTest logger;
	@BeforeTest
	public void start() throws IOException {
		
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		driver = new ChromeDriver();
		pages = new Pages(driver,logger);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://192.168.2.16/MatexTesting");
	}
	@Test(priority=1)
	public void login() throws Exception {
		pages.loginpage().Login("demotl", "pass@123");
		Assert.assertEquals(pages.Utill().find("ctl00_lblUsername").getText(), "Demotl");
	}
	@Test(priority=2)
	public void demotlLogin() throws InterruptedException {
		List<WebElement> menu_title=driver.findElements(By.xpath(".//*[@class='sf-menu sf-js-enabled sf-shadow']/li"));
		for (int i = 0; i < menu_title.size(); i++) {
			String title=menu_title.get(i).getText();
			pages.Utill().find("//*[text()='"+title+"']").click();
			List<WebElement> label=driver.findElements(By.xpath(".//*[text()='"+title+"']/following-sibling::ul/li/a"));
			for(int j=0;j<label.size();j++) {
				pages.Utill().find("//*[text()='"+title+"']").click();
				String li=label.get(j).getText();
				pages.Utill().find("//*[text()='"+li+"']").click();
				//label.get(j).click();
				String page_title=driver.getTitle();
				System.out.println(page_title);
				Thread.sleep(2000);
			}
		}
	}
	@AfterTest
	public void teardown() {
		//loginpage.Logout();
		
		//driver.close();
	}
}
