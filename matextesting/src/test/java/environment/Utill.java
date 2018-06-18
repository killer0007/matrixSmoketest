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
import org.openqa.selenium.support.ui.WebDriverWait;

import userLogin.Pages;

public class Utill {
	WebDriver driver;
protected final Pages pages;
	public Utill(WebDriver driver) {
		this.driver = driver;
		pages=new Pages(driver);
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
			if (path.startsWith("//") || path.startsWith(".//")) {
				return driver.findElement(By.xpath(path));
			} else {
				return driver.findElement(By.id(path));
			}
		} catch (Exception e) {
			throw new NoSuchElementException(e.toString());
		}
	}

	public int candidateid() {
		Random ran = new Random();
		int num = ran.nextInt(9000000) + 1000000;
		return num;
	}
	public String candidateName() {
		String name[]= {"Vishal", "Arjun", "Jagan", "Kavin", "Sadam", "Akash", "Sasi", "Santhosh", "Deepika", "Abinaya", "Ayyappan", "Mani", "Vignesh", "Hari", "Divya", "Rahul"};
		List<String> answersList = Arrays.asList(name);
		Collections.shuffle(answersList);
		return answersList.get(3);
	}

	public String GetTableCellValue(String id, int row, int col) throws InvalidActivityException { 
		try {
			String re = find("//table[@id='" + id + "']/tbody/tr[" + row + "]/td[" + col + "]").getText();
			if (re.equals("")) {
				throw new InvalidActivityException();
			} else {
				return re;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
			
		}
	}
	public String clickAlertbox() {
		//Pages pages=new Pages(driver);
		WebDriverWait w =new WebDriverWait(driver, 60);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
		String result=pages.Utill().find("//*[@class='m_content']").getText();
		pages.Utill().find("ok").click();
		return result;
	}
}
