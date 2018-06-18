package userLogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class UrlNavigation {
	WebDriver driver;
	@BeforeTest
	public void start() throws IOException {
		
		String chrome_path = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chrome_path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("url");
	}
	@Test
	public void demotlLogin() {
		List<WebElement> url_list=driver.findElements(By.xpath(""));
		HashMap<String, String> result=new HashMap<String, String>();
		List<String> domain=new ArrayList<String>();
		for (int i = 0; i < url_list.size(); i++) {
			String temp=url_list.get(i).getAttribute("href");
			domain.add(temp);
		}
		for (int j = 0; j < domain.size(); j++) {
			driver.get(domain.get(j));
			String title = driver.getTitle();
			result.put(domain.get(j), title);
		}
		System.out.println(result);
	}
	@AfterTest
	public void teardown() {
		//loginpage.Logout();
		
		driver.close();
	}
}
