package livetesting;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.activity.InvalidActivityException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Utility {
	WebDriver driver;

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVisibilityOfData(String path, int timeout) throws java.util.concurrent.TimeoutException {
		String result = driver.findElement(By.xpath(path)).getCssValue("visibility");
		int time = 0;
		int t = 2 * timeout;
		while (result.equals("hidden") && time < t) {
			try {
				Thread.sleep(500);
				result = driver.findElement(By.xpath(path)).getCssValue("visibility");
				System.out.println("next" + result + time + result);
				if (result.equals("visible")) {
					break;
				} else {
					time++;
					continue;
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		if (time >= t) {
			throw new java.util.concurrent.TimeoutException("element is not visible after " + timeout + " seconds");
		}

	}

	public int candidateid() {
		Random ran = new Random();
		int num = ran.nextInt(9000000) + 1000000;
		return num;
	}

	public String candidateName() {
		String name[] = { "Vishal", "Arjun", "Jagan", "Kavin", "Sadam", "Akash", "Sasi", "Santhosh", "Abinaya",
				"Ayyappan", "Mani", "Vignesh", "Hari", "Divya", "Rahul" };
		List<String> answersList = Arrays.asList(name);
		Collections.shuffle(answersList);
		return answersList.get(3);
	}

	public String assignToDETM(String cadName, int cadId) throws Exception {
		// pages = new Pages(driver);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtCandidateName")).sendKeys(cadName);

		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtCandidateid")).sendKeys(Integer.toString(cadId));
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_butnSearch")).click();
		this.wait_until_loader_is_invisible();
		String no = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_grdCRT']/tbody/tr[2]/td[4]"))
				.getText();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_grdCRT_ctl02_chkCRTSelect")).click();
		WebElement tm = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlTM"));
		Select sel = new Select(tm);
		sel.selectByVisibleText("demotl");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnAssign")).click();
		this.wait_until_loader_is_invisible();
		Thread.sleep(1000);
		String temp = this.clickAlertbox();
		System.out.println(temp);

		return no;

	}

	public void wait_until_loader_is_invisible() throws InterruptedException {
		// Pages pages=new Pages(driver);
		// System.out.println("Start time"+java.time.LocalTime.now());
		String res;
		try {

			res = driver.findElement(By.id("ctl00_UpdateProgress1")).getCssValue("display");
		} catch (StaleElementReferenceException e) {
			Thread.sleep(1000);
			res = driver.findElement(By.id("ctl00_UpdateProgress1")).getCssValue("display");
		}
		while (res.equals("block")) {
			Thread.sleep(200);
			res = driver.findElement(By.id("ctl00_UpdateProgress1")).getCssValue("display");
			if (!(res.equals("block"))) {
				// System.out.println("end time"+java.time.LocalTime.now());
				break;
			} else {
				// System.out.println(res);
				continue;

			}
		}
	}
	public String GetTableCellValue(String id, int row, int col) throws InvalidActivityException {
		try {
			String re =driver.findElement(By.xpath("//table[@id='" + id + "']/tbody/tr[" + row + "]/td[" + col + "]")).getText();
			//String re = find("//table[@id='" + id + "']/tbody/tr[" + row + "]/td[" + col + "]")

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
		try {
			Thread.sleep(1500);
			WebDriverWait w = new WebDriverWait(driver, 60);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));

			String result = driver.findElement(By.xpath("//*[@class='m_content']")).getText();
			driver.findElement(By.id("ok")).click();
			System.out.println(result);
			return result;
		} catch (WebDriverException e) {
			Thread.sleep(1000);
			WebDriverWait w = new WebDriverWait(driver, 60);
			w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ok"))));
			Thread.sleep(1000);
			String result = driver.findElement(By.xpath("//*[@class='m_content']")).getText();
			driver.findElement(By.id("ok")).click();
			System.out.println(result);

			return result;
		}
	}

	public void selectbyindex(String locator, int index) {
		WebElement ele = driver.findElement(By.id(locator));
		Select sel = new Select(ele);
		sel.selectByIndex(index);
	}

	public void selectbytext(String locator, String text) {
		WebElement ele = driver.findElement(By.id(locator));
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}

	public void deleteFiles(File folder) throws IOException {
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				file.delete();
				System.out.println("deleted file");
			} else if (file.isDirectory()) {
				deleteFiles(file);
				System.out.println("deleted dir");
			}
		}
	}

	public Boolean isfileexist(File folder, String filename) throws Exception {
//		boolean re = this.waitforfile(folder);
		Thread.sleep(2000);
//		if (re) {
			File[] files = folder.listFiles();
			if (files[0].isFile()) {
				String name = files[0].getName();
//				System.out.println("file name is :"+name);
//				System.out.println("parent name :"+files[0].getAbsolutePath());
				if (name.equals(filename)) {
					
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
//		} else {
//			return false;
//		}

	}

	private Boolean waitforfile(File folder) throws Exception{
		File[] files = folder.listFiles();
		int i=0;
		System.out.println("lenght is :"+files.length);
		while(files.length==0 && i<10) {
			Thread.sleep(200);
			files = folder.listFiles();
			System.out.println("looping :"+i);
			i++;
		}
		if(files.length==0) {
			System.out.println("file empty");
			return false;
		}
		else if (files.length>0) {
			return true;
		}
		else {
			System.out.println("file empty2");
			return false;
		}
	}
	public String handle_Alert() throws InterruptedException {
		String text;

		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			
			return text;
		} catch (NoAlertPresentException e) {
			System.out.println("NoAlertPresentException");
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			// pages.Wait().wait_until_loader_is_invisible();
			
			return text;
		} catch (UnhandledAlertException e) {
			System.out.println("UnhandledAlertException");
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			// pages.Wait().wait_until_loader_is_invisible();
			
			return text;
		}
	}
	public void wait_until_spinner_is_invisible(String id) throws InterruptedException {
		// Pages pages=new Pages(driver);
		// System.out.println("Start time"+java.time.LocalTime.now());
		//ctl00_ContentPlaceHolder1_overlayScreen_Laod_11
		//ctl00_ContentPlaceHolder1_overlayScreen
		String res;
		try {
			res = driver.findElement(By.id(id)).getCssValue("display");
			
		} catch (StaleElementReferenceException e) {
			Thread.sleep(1000);
			res = driver.findElement(By.id(id)).getCssValue("display");
		}
		while (res.equals("block")) {
			Thread.sleep(200);
			res = driver.findElement(By.id(id)).getCssValue("display");
			if (!(res.equals("block"))) {
				// System.out.println("end time"+java.time.LocalTime.now());
				break;
			} else {
				// System.out.println(res);
				continue;

			}
		}
	}
}
