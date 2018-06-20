package environment;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.activity.InvalidActivityException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import testCases.Pages;

public class Utill {
	WebDriver driver;
	ExtentTest logger;
	protected final Pages pages;

	public Utill(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
	}

	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}

		return path;
	}

	public WebElement find(String path) {
		try {
			if (path.startsWith("//") || path.startsWith(".//") || path.startsWith("/")|| path.startsWith("./")) {
				//logger.info("performing actions on " + path);
				return driver.findElement(By.xpath(path));
			} else {
				//logger.info("performing actions on " + path);
				return driver.findElement(By.id(path));
			}
		} catch (Exception e) {
			throw new NoSuchElementException(e.toString() + path);
		}
	}

	public void input_text(String path, String text) throws Exception {
		try {
			logger.info("Typing text '"+text+"' into text field '"+path+"'.");
			pages.Utill().find(path).sendKeys(text);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	public String get_text(String path) throws Exception {
		try {
		
					String msg=pages.Utill().find(path).getText();
					logger.info("msg = "+msg+".");
					return msg;
		} catch (Exception e) {

			throw new Exception(e.toString());
		}

	}

	public void select_by_label(String path, String value) throws Exception {
		try {
			//	Selecting options from selection list 'xpath=.//*[@id='ddltype1']' by label Course Completion Certificate.Sele
			logger.info("Selecting options from selection list '"+path+"' by label "+value+".");
			Select sel = new Select(pages.Utill().find(path));
			sel.selectByVisibleText(value);
		} catch (Exception e) {

			throw new Exception(e.toString());
		}
	}
public void click_element(String path) throws Exception {
	try {
		//	Clicking element 'xpath=.//*[@id='ok']'
		logger.info("Clicking element '"+path+"'");
		pages.Utill().find(path).click();
	} catch (Exception e) {

		throw new Exception(e.toString());
	}
}
	public int candidateid() {
		Random ran = new Random();
		int num = ran.nextInt(9000000) + 1000000;
		return num;
	}

	public String candidateName() {
		String name[] = { "Vishal", "Arjun", "Jagan", "Kavin", "Sadam", "Akash", "Sasi", "Santhosh", "Deepika",
				"Abinaya", "Ayyappan", "Mani", "Vignesh", "Hari", "Divya", "Rahul" };
		List<String> answersList = Arrays.asList(name);
		Collections.shuffle(answersList);
		return answersList.get(3);
	}

	public String GetTableCellValue(String id, int row, int col) throws InvalidActivityException {
		try {
			String re = find("//table[@id='" + id + "']/tbody/tr[" + row + "]/td[" + col + "]").getText();
			logger.info("getting value from table  :"+re);
			if (re.equals("")) {
				throw new InvalidActivityException();
			} else {
				return re;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());

		}
	}

	public String clickAlertbox() throws Exception {
		WebDriverWait w = new WebDriverWait(driver, 60);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
		//String result = pages.Utill().find("//*[@class='m_content']").getText();
		String result=pages.Utill().get_text("//*[@class='m_content']");
		pages.Utill().click_element("ok");
		//pages.Utill().find("ok").click();
		return result;
	}
}
