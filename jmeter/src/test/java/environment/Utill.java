package environment;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;
import javax.activity.InvalidActivityException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testcase.Pages;


public class Utill {
	WebDriver driver;
	
	protected final Pages pages;

	public Utill(WebDriver driver) {
		this.driver = driver;
		
		pages = new Pages(driver);
	}

	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";
//		String path ="./Screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}

		return path;
	}

	public WebElement find(String path) {
//		try {
			if ( path.startsWith("./") || path.startsWith("/") || path.startsWith("(//")) {
				// logger.info("performing actions on " + path);
				return driver.findElement(By.xpath(path));
			} else {
				// logger.info("performing actions on " + path);
				return driver.findElement(By.id(path));
			}
//		} 
//		catch (Exception e) {
//			throw new NoSuchElementException(e.toString() + path);
//		}
	}

	public void input_text(String path, String text) throws Exception {
		// try {

		pages.Utill().find(path).sendKeys(text);
		
		// logger.info();
		// } catch (Exception e) {
		// throw new Exception(e.toString());
		// }
	}

	public String get_text(String path) throws Exception {
		try {

			String msg = pages.Utill().find(path).getText();
			
			return msg;
		} catch (Exception e) {

			throw new Exception(e.toString());
		}

	}

	public void select_by_label(String path, String value) throws Exception {
		try {
			// Selecting options from selection list 'xpath=.//*[@id='ddltype1']' by label
			// Course Completion Certificate.Sele

			Select sel = new Select(pages.Utill().find(path));
			sel.selectByVisibleText(value);
		
		} catch (Exception e) {

			throw new Exception(e.toString());
		}
	}

	public void choose_file(String path, String file) throws Exception {
		pages.Utill().input_text(path, file);
		
	}

	public void click_element(String path) throws Exception {

		try {

			pages.Utill().find(path).click();
			
		} catch (StaleElementReferenceException e) {
			Thread.sleep(1000);
			pages.Utill().find(path).click();
			
		}
//		catch (ElementNotVisibleException e) {
//			pages.Wait().visibilityOfElement(path);
//			pages.Utill().click_element(path);
//		}

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

			if (re.equals("")) {
				throw new InvalidActivityException();
			} else {
				
				return re;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());

		}
	}

	public int Get_Matching_xpath_count(String path) {
	
		return driver.findElements(By.xpath(path)).size();
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

	public List<WebElement> Get_webelement_list(String path) {
		
		List<WebElement> li = driver.findElements(By.xpath(path));
		
		return li;
	}

	public String clickAlertbox() throws Exception {
		try {
			Thread.sleep(1500);
			WebDriverWait w = new WebDriverWait(driver, 60);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
			String result = pages.Utill().get_text("//*[@class='m_content']");
			pages.Utill().click_element("ok");
			System.out.println(result);
		
			return result;
		} catch (WebDriverException e) {
			Thread.sleep(1000);
			WebDriverWait w = new WebDriverWait(driver, 60);
			w.until(ExpectedConditions.elementToBeClickable(pages.Utill().find("ok")));
			Thread.sleep(1000);
			String result = pages.Utill().get_text("//*[@class='m_content']");
			pages.Utill().click_element("ok");
			System.out.println(result);
			
			return result;
		}
	}

	public String getTitle() {
		String title = driver.getTitle();
		
		return title;
	}

	public void GoTo(String url) {
		driver.navigate().to(url);
	
		System.out.println(driver.getTitle());
		
	}

	public String getcurrentdate() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date currentMonth = new Date();
		return df.format(currentMonth).toString();

	}

	public void search_and_select(String id, String value) throws Exception {
		pages.Utill().click_element(".//*[@id='" + id + "']/a");
		pages.Utill().input_text(".//*[@id='" + id + "']//div/div/input", value);
		Thread.sleep(1000);
		pages.Utill().click_element(".//*[@id='" + id + "']/div/ul/li");

	}
	public void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public static String getdatetime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now); // 2016/11/16 12:08:43
		// return "mmmmm";
	}
	public void mouseover(String id) {
		Actions action = new Actions(driver);
		action.moveToElement(pages.Utill().find(id));
		action.build().perform();
		
		action=null;
	}
	public void SwitchFramebyIndex(int i) {
		driver.switchTo().frame(i);
		
	}
	public void scrollTo(String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", pages.Utill().find(id));
	
		
	}
	public void SwitchDefault() {
		driver.switchTo().defaultContent();
		
	}
	public void FileUpload(String id, String filename) throws Exception{
		pages.Utill().click_element(id);
		Thread.sleep(1000);
		setClipboardData(filename);
		 Robot robot = new Robot();
//		 System.out.println("start");
         robot.keyPress(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_V);
         robot.keyRelease(KeyEvent.VK_CONTROL);
         robot.keyPress(KeyEvent.VK_ENTER);
         robot.keyRelease(KeyEvent.VK_ENTER);
//         System.out.println("end");
	}
	public  void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	public String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
		return pr.getProperty(key);
	}
}
