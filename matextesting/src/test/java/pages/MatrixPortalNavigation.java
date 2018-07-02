package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import environment.Utill;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;
import testCases.Pages;

public class MatrixPortalNavigation {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	WebDriverWait w;

	public MatrixPortalNavigation(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		w = new WebDriverWait(driver, 20);
	}

	public void geturl(String title, Map<String, String> map, String xpath) throws Exception {
		String currenturl=driver.getCurrentUrl();
		pages.Utill().click_element("//*[text()='" + title + "']");
		LinkedList<String> url = new LinkedList<String>();
		Thread.sleep(1500);
		List<WebElement> ele = driver.findElements(By.xpath(xpath));
		for (int i = 0; i < ele.size(); i++) {
			String te = ele.get(i).getText();
			url.add(te);

		}
		System.out.println(url);
		int len;
		if(title.equals("Masters") || title.equals("Approvals")) {
			len=url.size()-1;
		}
		else {
			len=url.size();
		}
		for (int i = 0; i < len; i++) {
			try {
				pages.Utill().click_element("//*[text()='" + title + "']");
				try {
					pages.Utill().click_element("//*[text()='" + url.get(i) + "']");
				} catch (ElementNotVisibleException e) {
					//(//*[text()='City'])[2]
					System.out.println(" exception is ElementNotVisibleException");
					System.out.println("(//*[text()='"+url.get(i)+"'])[2]");
					pages.Utill().click_element("(//*[text()='"+url.get(i)+"'])[2]");
				}
				map.put(url.get(i), pages.Utill().getTitle());
			} catch (NoSuchElementException e) {
				if (e.getMessage().toString().contains("//*[text()='" + title + "']")) {
					System.out.println("expected");
					pages.Utill().click_element(getpath(title));
					// driver.findElement(By.xpath(".//*[@id='ctl00_pnlMenu']/div/div[2]/div[2]/nav/ul/li[2]/ul/li[1]/a")).click();
					pages.Utill().click_element("//*[text()='" + url.get(i) + "']");
					map.put(url.get(i), pages.Utill().getTitle());
				}
				
			} 
//			catch (TimeoutException e) {
//				System.out.println(e.getMessage().toString());
//				String temp = Utill.getScreenshot(driver);
//				logger.fail(e.getMessage().toString(),
//						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
//				pages.Utill().GoTo(currenturl);
//			}
			finally {
				String titles = driver.getTitle();
				if (titles.contains("The resource cannot") || titles.contains("has not been pre-compiled")
						|| titles.contains("not found on the selected data source")) {
					String temp = Utill.getScreenshot(driver);
					logger.fail(pages.Utill().getTitle() + " for " + title + "->" + url.get(i),
							MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
					pages.Utill().GoTo(currenturl);

				} else if (driver.getTitle().isEmpty()) {
					logger.log(Status.WARNING, " title was empty for " + url.get(i));
				} else {
					logger.log(Status.PASS, pages.Utill().getTitle());
				}
			}
		}
	}

	public static void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	private String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}

	private static String getpath(String title) {
		switch (title) {
		case "Dashboard":
			System.out.println("dashboard");
			return ".//*[@id='ctl00_pnlMenu']/div/div[2]/div[2]/nav/ul/li[2]/ul/li[1]/a";
		case "CRT":
			System.out.println("crt");
			return ".//*[@id='ctl00_pnlMenu']/div/div[2]/div[2]/nav/ul/li[2]/ul/li[1]/a";

		default:
			return "not found " + title;
		}

	}
}