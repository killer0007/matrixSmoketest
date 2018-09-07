package environment;

import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import javax.activity.InvalidActivityException;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import environment.Pages;

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
		// String path ="./Screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}

		return path;
	}

	public WebElement find(String path) {
		// try {
		if (path.startsWith("./") || path.startsWith("/") || path.startsWith("(//")) {
			// logger.info("performing actions on " + path);
			return driver.findElement(By.xpath(path));
		} 
		else if (path.startsWith("xpath")) {
			String loc []=path.split(":");
			return driver.findElement(By.xpath(loc[1]));
		}
		else if (path.startsWith("name")) {
			String loc []=path.split(":");
			return driver.findElement(By.name(loc[1]));
		}
		else if (path.startsWith("css")) {
			String loc []=path.split(":");
			return driver.findElement(By.cssSelector(loc[1]));
		}
		else if (path.startsWith("link")) {
			String loc []=path.split(":");
			return driver.findElement(By.linkText(loc[1]));
		}
		else if (path.startsWith("partiallink")) {
			String loc []=path.split(":");
			return driver.findElement(By.partialLinkText(loc[1]));
		}
		else if (path.startsWith("class")) {
			String loc []=path.split(":");
			return driver.findElement(By.className(loc[1]));
		}	
			else {
			// logger.info("performing actions on " + path);
			return driver.findElement(By.id(path));
		}
		// }
		// catch (Exception e) {
		// throw new NoSuchElementException(e.toString() + path);
		// }
	}

	public void input_text(String path, String text) {
		pages.Utill().find(path).sendKeys(text);
		logger.log(Status.PASS, "Typing text '" + text + "' into text field '" + path + "'.");
		
	}

	public String get_text(String path)  {
		
			String msg = pages.Utill().find(path).getText();
			logger.log(Status.PASS, "msg = " + msg + ".");
			return msg;
		
	}

	public void select_by_label(String path, String value)  {

			Select sel = new Select(pages.Utill().find(path));
			sel.selectByVisibleText(value);
			logger.log(Status.PASS, "Selecting options from selection list '" + path + "' by label " + value + ".");

	}

	public void choose_file(String path, String file) throws Exception {
		pages.Utill().input_text(path, file);
		logger.log(Status.PASS, "uploading file");
	}

	public void click_element(String path) {

			pages.Utill().find(path).click();
			logger.log(Status.PASS, "Clicking element '" + path + "'");
		

	}

	public int candidateid() {
		Random ran = new Random();
		int num = ran.nextInt(9000000) + 1000000;
		return num;
	}

	public String candidateName() {
		String name[] = { "Vishal", "Arjun", "Jagan", "Kavin", "Sadam", "Akash", "Sasi", "Santhosh",
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
				logger.log(Status.PASS, "getting value from table  :" + re);
				return re;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());

		}
	}

	public int Get_Matching_xpath_count(String path) {
		logger.log(Status.PASS, "getting xpath count for " + path);
		return driver.findElements(By.xpath(path)).size();
	}

	
	public List<WebElement> Get_webelement_list(String path) {

		List<WebElement> li = driver.findElements(By.xpath(path));
		logger.log(Status.PASS, "getting webelement list of :" + path);
		return li;
	}
	
	

	public String getTitle() {
		String title = driver.getTitle();
		logger.log(Status.PASS, "getting page title as  " + title);
		return title;
	}

	public void GoTo(String url) {
		driver.navigate().to(url);
		logger.log(Status.INFO, "navigating to " + url);
		System.out.println(driver.getTitle());
		logger.log(Status.INFO, driver.getTitle());
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
		logger.log(Status.PASS, "performing mouse over on :" + id);
		action = null;
	}

	public void SwitchFramebyIndex(int i) {
		driver.switchTo().frame(i);
		logger.log(Status.PASS, "switching frame by index " + i);
	}
	public void switchWindow(int index) {
		ArrayList tabs= new ArrayList(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index).toString());
		
	}

	public void scrollTo(String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", pages.Utill().find(id));
		logger.log(Status.PASS, "scrolling into view of :" + id);

	}

	public void SwitchDefault() {
		driver.switchTo().defaultContent();
		logger.log(Status.PASS, "switching to default frame");
	}

	public void FileUpload(String id, String filename) throws Exception {
		pages.Utill().click_element(id);
		Thread.sleep(1000);
		setClipboardData(filename);
		Robot robot = new Robot();
		// System.out.println("start");
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		// System.out.println("end");
	}

	public void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	// library build by gopinath
	public void alert_shouldbe_present() {
		Alert alert = driver.switchTo().alert();
		String te = alert.getText();
		logger.log(Status.PASS, "alert text is : " + te);
		alert.accept();
		logger.log(Status.PASS, "Alet accepted");
	}

	public void checkbox_shouldbe_selected(String id) throws Exception {
		boolean b = pages.Utill().find("id").isSelected();
		logger.log(Status.PASS, "check box selected state is : " + b);
		if (!b) {
			throw new Exception("check box not selected");
		}
	}

	public void checkbox_shouldnotbe_selected(String id) throws Exception {
		boolean b = pages.Utill().find("id").isSelected();
		logger.log(Status.PASS, "check box selected state is : " + b);
		if (b) {
			throw new Exception("check box selected");
		}
	}

	public void clear_element_text(String id) {
		pages.Utill().find(id).clear();
		logger.log(Status.PASS, "text cleared for : " + id);
	}

	public void click_link(String link) {
		driver.findElement(By.linkText(link)).click();
		logger.log(Status.PASS, "Clicking link : " + link);

	}

	public void close_all_browsers() {
		driver.quit();
		logger.log(Status.PASS, "all browsers are closed ");
	}

	public void close_browser() {
		driver.close();
		logger.log(Status.PASS, "browser closed ");
	}

	public void double_click_element(String id) {
		WebElement ele = pages.Utill().find(id);
		Actions action = new Actions(driver);
		action.doubleClick(ele).build().perform();
		logger.log(Status.PASS, "double clicking in element : " + id);
	}

	public void element_shouldbe_enabled(String id) throws Exception {
		boolean b = pages.Utill().find(id).isEnabled();
		logger.log(Status.PASS, "element enabled status is : " + b);
		if (!b) {
			throw new Exception(id + "  webelement not enabled");
		}
	}

	public void element_shouldbe_disabled(String id) throws Exception {
		boolean b = pages.Utill().find(id).isDisplayed();
		logger.log(Status.PASS, "element enabled status is : " + b);

		if (!b) {
			logger.log(Status.FAIL, id + " :element not disabled");
			throw new Exception(id + "  webelement not enabled");
		}
	}

	public void element_shouldbe_focused(String id) throws Exception {
		WebElement ele = pages.Utill().find(id);
		boolean b = ele == driver.switchTo().activeElement();

		if (!b) {
			logger.log(Status.FAIL, id + "  webelement is not focused instead of focusing element is  "
					+ driver.switchTo().activeElement());
			throw new Exception(id + "  webelement is not focused instead of focusing element is  "
					+ driver.switchTo().activeElement());
		}
		logger.log(Status.PASS, id + "  : is focused");

	}

	public void element_shouldbe_visible(String id) throws Exception {
		String css = pages.Utill().find(id).getCssValue("display");
		if (css.equalsIgnoreCase("none")) {
			logger.log(Status.FAIL, id + "  is not visible");
			throw new Exception("element is not visible");
		}
		logger.log(Status.PASS, id + "  is visible");

	}

	public void element_should_contains(String id, String text)  throws Exception{
		String t = pages.Utill().get_text(id);
		if(t.contains(text)) {
			logger.log(Status.PASS, id + " contains expected text");	
		}
		else {
			logger.log(Status.FAIL, id + "  contains text as : " +t);
			throw new Exception(id + "  contains text as : " +t);
		}
	}
	public void element_shouldnotbe_visible(String id) throws Exception {
		String css = pages.Utill().find(id).getCssValue("display");
		if (!(css.equalsIgnoreCase("none"))) {
			logger.log(Status.FAIL, id + "  is visible");
			throw new Exception("element is  visible");
		}
		logger.log(Status.PASS, id + "  is not visible");
	}
	public void element_shouldnot_contains(String id, String text)  throws Exception{
		String t = pages.Utill().get_text(id);
		if(!(t.contains(text))) {
			logger.log(Status.PASS, id + " contains expected text");	
		}
		else {
			logger.log(Status.FAIL, id + "  contains text as : " +t);
			throw new Exception(id + "  contains text as : " +t);
		}
	}
	public String get_element_attribute(String id, String attribute) {
		String attri =pages.Utill().find(id).getAttribute(attribute);
		logger.log(Status.PASS,"getting value of : "+id+attribute);
		return attri;
	}
	public void wait_until_dropdownload(String path, int TimeOut)  {
		try {
		final String id=path;
//		Thread.sleep(1500);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeOut))
				.pollingEvery(Duration.ofMillis(200));
//		.ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = pages.Utill().find(id);
				StringBuffer res = new StringBuffer(ele.getCssValue("display"));
//				String res = ele.getCssValue("display");
				if (!res.toString().equals("none")) {
					 System.out.println("success " + res);
					 res=null;
					return ele;
				} else {
					 System.out.println("failed :" + res);
					 res=null;
					return null;
				}
			}
		});
		}
		catch(NoSuchElementException e){
			System.out.println("done");
		}
	}
	public void wait_until_loader_is_invisible(int TimeOut)  {
		try {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeOut))
				.pollingEvery(Duration.ofMillis(200));
//		.ignoring(NoSuchElementException.class);
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement ele = pages.Utill().find("loading-bar-spinner");
				StringBuffer res = new StringBuffer(ele.getCssValue("display"));
//				String res = ele.getCssValue("display");
				if (!res.toString().equals("block")) {
//					 System.out.println("success " + res);
					 res=null;
					return ele;
				} else {
//					 System.out.println("failed :" + res);
					 res=null;
					return null;
				}
			}
		});
		}
		catch(NoSuchElementException e){
			System.out.println("done");
		}
	}
public void wait_until_element_isvisible(String path, int Timeout) {
	WebDriverWait wait = new WebDriverWait(driver, Timeout);
	wait.until(ExpectedConditions.visibilityOf(pages.Utill().find(path)));
	wait=null;
	
}
	public boolean isimage(String url) {
		try {
			
			Image image = ImageIO.read(new URL(url));
			if(image != null){
			  return true;
			    
			}else{
			    return false;
			}
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
}
