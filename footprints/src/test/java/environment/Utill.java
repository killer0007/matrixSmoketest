package environment;

/**
 * @author Gopinath N
 */
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import environment.Pages;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class Utill {
	public WebDriver driver;
	public ExtentTest logger;
//	protected final Pages pages;
	/**
	 * constructor to initiatize webdriver and logger
	 * 
	 * @param logger logger instance
	 */
	public Utill(WebDriver driver,ExtentTest logger) {
		this.driver=driver;
		this.logger = logger;
	}
	

	/**
	 * Takes the webdriver and return the path of screen shot to be taken
	 * 
	 * @param driver instance of webdriver
	 * @return screenshot path
	 */
	public static synchronized String getScreenshotparital(WebDriver driver) {
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
	/**
	 * Takes the webdriver and return the path of screen shot to be taken
	 * 
	 * @param driver instance of webdriver
	 * @return screenshot path
	 * @throws IOException 
	 */
	public static synchronized String getScreenshot(WebDriver driver) throws IOException {
		String path = System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis() + ".png";
		final Screenshot screenshot = new AShot().shootingStrategy(
                new ViewportPastingStrategy(1000)).takeScreenshot(driver);
        final BufferedImage image = screenshot.getImage();
        ImageIO.write(image, "PNG", new File(path));
		return path;
	}

	/**
	 * Take the locator value and return the webelement mention locators as follow
	 * xpath=xpath:value name=name:value cssSelector=css:value
	 * linkText=linkText:value partialLinkText=partiallink:value
	 * className=class:value id=value
	 * 
	 * @param path path of locator
	 * @return WebElement of given path
	 */
	public WebElement find(String path) {
		// try {
		if(!path.isEmpty() || !path.equals(null)) {
		if (path.startsWith("./") || path.startsWith("/") || path.startsWith("//")) {
			// logger.info("performing actions on " + path);
			return driver.findElement(By.xpath(path));
		} else if (path.startsWith("xpath")) {
			String loc[] = path.split(":");
			return driver.findElement(By.xpath(loc[1]));
		} else if (path.startsWith("name")) {
			String loc[] = path.split(":");
			return driver.findElement(By.name(loc[1]));
		} else if (path.startsWith("css")) {
			String loc[] = path.split(":");
			return driver.findElement(By.cssSelector(loc[1]));
		} else if (path.startsWith("linkText")) {
			String loc[] = path.split(":");
			return driver.findElement(By.linkText(loc[1]));
		} else if (path.startsWith("partiallink")) {
			String loc[] = path.split(":");
			return driver.findElement(By.partialLinkText(loc[1]));
		} else if (path.startsWith("class")) {
			String loc[] = path.split(":");
			return driver.findElement(By.className(loc[1]));
		} else {
			return driver.findElement(By.id(path));
		}
		}
		else {
			throw new NullPointerException(path);
		}
	}

	/**
	 * Takes the locator value and string value as input and pass the string value
	 * to Locater same as sendkeys in webdriver
	 * 
	 * @param path locator of input text
	 * @param text text for input
	 */
	public void sendKeys(String path, String text) {
		this.find(path).sendKeys(text);
		logger.log(Status.PASS, "Typing text '" + text + "' into text field '" + path + "'.");
	}

	/**
	 * Takes the locater value as input and return the inner text for that locator
	 * 
	 * @param path locator of element
	 * @return Inner text value as String
	 */
	public String getText(String path) {
		String msg = this.find(path).getText();
		int c = 0;
		while (msg.equals(null) || msg.isEmpty()) {
			this.sleep(200);
			msg = this.find(path).getText();
			c++;
			logger.log(Status.INFO, "loop count is :" + c);
//			System.out.println("loop count is :" + c);
			if (c > 10) {
				break;
			}
		}

		logger.log(Status.PASS, "msg = " + msg + ".");
		return msg;

	}

	public String getValue(String id) {
		String val=this.find(id).getAttribute("value").trim();
		logger.log(Status.PASS, "getting value from locator : "+id+" , "+val);
		return val;
	}

	/**
	 * Takes the Locater value and String value as input and select the given string
	 * value from select option
	 * 
	 * @param path  locator of select dropdown
	 * @param value value of select dropdown
	 */
	public void selectByLabel(String path, String value) {
		Select sel = new Select(this.find(path));
		sel.selectByVisibleText(value);
		logger.log(Status.PASS, "Selecting options from selection list '" + path + "' by label " + value + ".");

	}

	public String getSelectedValue(String locator) {
		Select sel = new Select(this.find(locator));
		return sel.getFirstSelectedOption().getText();

	}

	/**
	 * Takes the Locater value and String value as input and select the given string
	 * value from select option
	 * 
	 * @param path  locator of select dropdown
	 * @param value value of select dropdown
	 */
	public void selectByValue(String path, String value) {
		Select sel = new Select(this.find(path));
		sel.selectByValue(value);
		logger.log(Status.PASS, "Selecting options from selection list '" + path + "' by label " + value + ".");

	}

	/**
	 * Takes the locator value and file name as input and upload the file
	 * 
	 * @param path locator
	 * @param file file directory
	 * @throws Exception when file not found
	 */
	public void chooseFile(String path, String file) throws Exception {
		this.sendKeys(path, file);
		logger.log(Status.PASS, "uploading file");
	}

	/**
	 * Takes the locator value as input and perform click option
	 * 
	 * @param path locator
	 */
	public void click(String path) {
		this.find(path).click();
		logger.log(Status.PASS, "Clicking element '" + path + "'");
	}

	/**
	 * generates random integer which is used as candidateid
	 * 
	 * @return random number
	 */
	public int getcandidateid() {
		Random ran = new Random();
		int num = ran.nextInt(9000000) + 1000000;
		return num;
	}

	/**
	 * return name as String used for candidate name in portal
	 * 
	 * @return candidate name
	 */
	public String candidateName() {
		String name[] = { "Lindsey", "Noah", "Erica", "Cheyenne", "Ryan", "Wyatt", "Erika", "Tim", "Brooklyn", "Jill",
				"Karen", "Leslie", "Eddie", "Mariah", "Nancy", "Wayne", "Chasity", "Terri", "Aaron", "Jim", "Kendra",
				"Stephanie", "Jenna", "Antonio", "Jane", "Jan", "George", "Annette", "Janet", "Raymond", "Steven",
				"Cassandra", "Shane", "Judy", "Unborn", "Brian", "Kaitlyn", "Dusty", "Donald", "Laura", "Alisha",
				"Haley", "Marty", "Leslie", "Sam", "Eric", "Jeffrey", "Donna", "Thomas", "Myron", "Mickey", "Rod",
				"Chuck", "Ken", "Sierra", "Marsha", "Michael", "Dana", "Richard", "James", "Margaret", "Michelle",
				"Hope", "Kylie", "Helen", "Gabriel", "Myron", "Dusty", "Laura", "Paige", "Patrick", "Steven", "Kaylee",
				"Eddie", "Timmy", "Jody", "Terry", "Erin", "Donnie", "Leroy", "Rick", "Jan", "Dave", "Blake", "Carrie",
				"Randi", "Brenda", "Juanita", "Kaitlyn", "Dave", "Sandra", "Jeremiah", "Donna", "Kris", "Vernon",
				"Brianna", "Greg", "Ronald", "Brad", "Shawna" };
		List<String> answersList = Arrays.asList(name);
		Collections.shuffle(answersList);
		return answersList.get(3);
	}

	/**
	 * generate random email id and returns it
	 * 
	 * @return email id
	 */
	public String getEmail() {
		DataFactory df = new DataFactory();
		return df.getEmailAddress();

	}

	/**
	 * generates random 10 digit mobile number and return it
	 * 
	 * @return mobile number
	 */
	public String mobileno() {
		Random rand = new Random();
		long drand = (long) (rand.nextDouble() * 10000000000L);
		while (Long.toString(drand).length() < 10) {
			drand = (long) (rand.nextDouble() * 10000000000L);
		}
		return Long.toString(drand);
	}

	/**
	 * takes the table id, row number and column number as input and returns the
	 * cell value
	 * 
	 * @param id  locator of table
	 * @param row row number
	 * @param col column number
	 * @return cell value as string
	 * @throws Exception when table not found
	 */
	public String getTableCellValue(String id, int row, int col) throws Exception {
		try {
			String re = find("//table[@id='" + id + "']/tbody/tr[" + row + "]/td[" + col + "]").getText();

			if (re.equals("")) {
				System.out.println("//table[@id='" + id + "']/tbody/tr[" + row + "]/td[" + col + "]");
				throw new Exception();

			} else {
				logger.log(Status.PASS, "getting value from table  :" + re);
				return re;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());

		}
	}

	/**
	 * Takes the xpath as input and return the matching xpath count
	 * 
	 * @param path locator
	 * @return count of matching xpath
	 */
	public int getMatchingXpathCount(String path) {
		logger.log(Status.PASS, "getting xpath count for " + path);
		return driver.findElements(By.xpath(path)).size();
	}

	/**
	 * Takes the xpath as input and return the list of webelements
	 * 
	 * @param path locator
	 * @return list of webelements
	 */
	public List<WebElement> getWebelementList(String path) {
		List<WebElement> li = driver.findElements(By.xpath(path));
		logger.log(Status.PASS, "getting webelement list of :" + path);
		return li;
	}

	/**
	 * return the current page title as string
	 * 
	 * @return page title
	 */
	public String getTitle() {
		String title = driver.getTitle();
		logger.log(Status.PASS, "getting page title as  " + title);
		return title;
	}

	/**
	 * Takes locator value and style attribute as input and return the value of that
	 * attribute
	 * 
	 * @param id        locator
	 * @param attribute value
	 * @return value of give attribute as string
	 */
	public String getCssValue(String id, String attribute) {
		return this.find(id).getCssValue(attribute);
	}

	/**
	 * Takes the url as input and navigates to that URL
	 * 
	 * @param url domain name
	 */
	public void GoTo(String url) {
		driver.navigate().to(url);
		this.waitUntilLoaderisInvisible(100);
		logger.log(Status.INFO, "navigating to " + url);
		System.out.println(driver.getTitle());
		logger.log(Status.INFO, driver.getTitle());
	}

	/**
	 * returns the current date
	 * 
	 * @return current date
	 */
	public String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date currentMonth = new Date();
		return df.format(currentMonth).toString();

	}
	public String getCurrentDate(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date currentMonth = new Date();
		return df.format(currentMonth).toString();

	}
	/**
	 * Takes Dropdown locator id and inner text as input and click the inner text
	 * value
	 * 
	 * @param id    locator
	 * @param value select text
	 * @throws Exception when select dropwdown not found
	 */
	public void searchAndSelect(String id, String value) throws Exception {
		this.click(".//*[@id='" + id + "']/a");
		this.sendKeys(".//*[@id='" + id + "']//div/div/input", value);
		Thread.sleep(1000);
		this.click(".//*[@id='" + id + "']/div/ul/li");

	}

	/**
	 * returns current date and time
	 * 
	 * @return current date and time in yyyy_MM_dd_HH_mm_ss formate
	 */
	public static String getDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now); // 2016/11/16 12:08:43
		// return "mmmmm";
	}

	/**
	 * generate the random date of birth and returns it
	 * 
	 * @return date of birth generated randomly
	 */
	public String getDob() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Random random = new Random();
		int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);
		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
		return df.format(randomBirthDate).toString();
	}

	/**
	 * Takes web locator value as input and perform mouse over on it
	 * 
	 * @param id locator
	 */
	public void mouseOver(String id) {
		Actions action = new Actions(driver);
		action.moveToElement(this.find(id));
		action.build().perform();
		logger.log(Status.PASS, "performing mouse over on :" + id);
		action = null;
	}

	/**
	 * Takes the frame index id as input and switch the frame
	 * 
	 * @param index frame index
	 */
	public void SwitchFramebyIndex(int index) {
		driver.switchTo().frame(index);
		logger.log(Status.PASS, "switching frame by index " + index);

	}
	/**
	 * Takes the frame  id as input and switch the frame
	 * 
	 * @param id frame index
	 */
	public void SwitchFramebyId(String id) {
		driver.switchTo().frame(id);
		logger.log(Status.PASS, "switching frame by id " + id);

	}

	/**
	 * Takes the index of window as input and switch window based on index
	 * 
	 * @param index window index
	 */
	public void switchWindow(int index) {
		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index).toString());
	}

	/**
	 * Takes locator value as input and performs scroll to that element
	 * 
	 * @param id locator
	 */
	public void scrollTo(String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", this.find(id));
		logger.log(Status.PASS, "scrolling into view of :" + id);

	}

	/**
	 * Switching default frame
	 */
	public void SwitchDefault() {

		driver.switchTo().defaultContent();
		logger.log(Status.PASS, "switching to default frame");
	}

	/**
	 * Takes the java script as input and execute it
	 * 
	 * @param script java script
	 */
	public void executeScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}

	/**
	 * Takes locator value and file name as input and upload file using robot class
	 * 
	 * @param id       locator
	 * @param filename file directory
	 * @throws Exception when file not found
	 */
	public void FileUpload(String id, String filename) throws Exception {
		this.click(id);
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

	/**
	 * Takes the filename as input and upload it
	 * 
	 * @param filename file directory
	 * @throws Exception when file not found
	 */
	public void FileUpload(String filename) throws Exception {
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

	/**
	 * Takes the string as input and copy the input to clipboard
	 * 
	 * @param string text to copy
	 */
	public void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	/**
	 * switch to alert and accept it
	 */
	public void alertShouldbePresent() {
		Alert alert = driver.switchTo().alert();
		String te = alert.getText();
		logger.log(Status.PASS, "alert text is : " + te);
		alert.accept();
		logger.log(Status.PASS, "Alet accepted");
	}

	/**
	 * Takes the check box id as input and checks whether checkbox is selected or
	 * not
	 * 
	 * @param id locator
	 * @throws Exception when check box is not selected
	 */
	public void checkboxShouldbeSelected(String id) throws Exception {
		boolean b = this.find("id").isSelected();
		logger.log(Status.PASS, "check box selected state is : " + b);
		if (!b) {
			throw new Exception("check box not selected");
		}
	}

	/**
	 * Takes the check box id as input and checks whether checkbox is selected or
	 * not
	 * 
	 * @param id locator
	 * @throws Exception when check box was selected
	 */
	public void checkboxShouldNotbeSelected(String id) throws Exception {
		boolean b = this.find("id").isSelected();
		logger.log(Status.PASS, "check box selected state is : " + b);
		if (b) {
			throw new Exception("check box selected");
		}
	}

	/**
	 * Takes web locator as input and return true when input is selected
	 * 
	 * @param path locator
	 * @return if selected true else false
	 */
	public boolean isSelected(String path) {
		return this.find(path).isSelected();
	}

	/**
	 * Takes web locator as input and return true when input is displayed
	 * 
	 * @param path locator
	 * @return if displayed true else false
	 */
	public boolean isDisplayed(String path) {
		return this.find(path).isDisplayed();
	}

	/**
	 * Takes web locator as input and return true when input is enabled
	 * 
	 * @param path locator
	 * @return if enabled true else false
	 */
	public boolean isEnabled(String path) {
		return this.find(path).isEnabled();
	}

	/**
	 * Takes web locator value as input and clear the text from the input field
	 * 
	 * @param id locator
	 */
	public void clearElementText(String id) {
		this.find(id).clear();
		logger.log(Status.PASS, "text cleared for : " + id);
	}

	/**
	 * Takes locator of link as input and perform click function
	 * 
	 * @param link locator
	 */
	public void clickLink(String link) {
		driver.findElement(By.linkText(link)).click();
		logger.log(Status.PASS, "Clicking link : " + link);

	}

	/**
	 * Takes the element locator as input and wait 20 secs until element is
	 * clickable
	 * 
	 * @param path locator
	 */
	public void waitUntilElemenisClickable(String path) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(this.find(path)));
	}

	/**
	 * closed all browsers opened by webdriver
	 */
	public void closeAllBrowsers() {
		driver.quit();
		logger.log(Status.PASS, "all browsers are closed ");
	}

	/**
	 * closes the current window of browser opened by webdriver
	 */
	public void closeBrowser() {
		driver.close();
		logger.log(Status.PASS, "browser closed ");
	}

	/**
	 * Takes the element locator value as input and performs double click on it
	 * 
	 * @param id locator
	 */
	public void doubleClick(String id) {
		WebElement ele = this.find(id);
		Actions action = new Actions(driver);
		action.doubleClick(ele).build().perform();
		logger.log(Status.PASS, "double clicking in element : " + id);
	}

	/**
	 * Takes element locator as input and check whether element is enabled or not
	 * 
	 * @param id locator
	 * @throws Exception when element is not enabled or disabled
	 */
	public void elementShouldbeEnabled(String id) throws Exception {
		boolean b = this.find(id).isEnabled();
		logger.log(Status.PASS, "element enabled status is : " + b);
		if (!b) {
			throw new Exception(id + "  webelement not enabled");
		}
	}

	/**
	 * Takes element locator as input and check whether element is disabled or not
	 * 
	 * @param id locator
	 * @throws Exception when element is not disabled or enabled
	 */
	public void elementShouldbeDisabled(String id) throws Exception {
		boolean b = this.find(id).isDisplayed();
		logger.log(Status.PASS, "element enabled status is : " + b);

		if (!b) {
			logger.log(Status.FAIL, id + " :element not disabled");
			throw new Exception(id + "  webelement not enabled");
		}
	}

	/**
	 * Takes element locator as input and check whether element is focused or not
	 * 
	 * @param id locator
	 * @throws Exception when element is not focused
	 */
	public void elementShouldbeFocused(String id) throws Exception {
		WebElement ele = this.find(id);
		boolean b = ele == driver.switchTo().activeElement();

		if (!b) {
			logger.log(Status.FAIL, id + "  webelement is not focused instead of focusing element is  "
					+ driver.switchTo().activeElement());
			throw new Exception(id + "  webelement is not focused instead of focusing element is  "
					+ driver.switchTo().activeElement());
		}
		logger.log(Status.PASS, id + "  : is focused");

	}

	/**
	 * Takes element locator as input and check whether element is visible or not
	 * 
	 * @param id locator
	 * @throws Exception when element is not visible
	 */
	public void elementShouldbeVisible(String id) throws Exception {
		String css = this.find(id).getCssValue("display");
		if (css.equalsIgnoreCase("none")) {
			logger.log(Status.FAIL, id + "  is not visible");
			throw new Exception("element is not visible");
		}
		logger.log(Status.PASS, id + "  is visible");

	}

	/**
	 * Takes the element locator value and text as input and check whether element
	 * contains the given text
	 * 
	 * @param id   locator
	 * @param text inner text
	 * @throws Exception when element not contains given text
	 */
	public void elementShouldContains(String id, String text) throws Exception {
		String t = this.getText(id);
		if (t.contains(text)) {
			logger.log(Status.PASS, id + " contains expected text");
		} else {
			logger.log(Status.FAIL, id + "  contains text as : " + t);
			throw new Exception(id + "  contains text as : " + t);
		}
	}

	/**
	 * Takes element locator as input and check whether element is visible or not
	 * 
	 * @param id locator
	 * @throws Exception when element is visible
	 */
	public void elementShouldNotbeVisible(String id) throws Exception {
		String css = this.find(id).getCssValue("display");
		if (!(css.equalsIgnoreCase("none"))) {
			logger.log(Status.FAIL, id + "  is visible");
			throw new Exception("element is  visible");
		}
		logger.log(Status.PASS, id + "  is not visible");
	}

	/**
	 * Takes the element locator value and text as input and check whether element
	 * contains the given text
	 * 
	 * @param id   locator
	 * @param text text which contains
	 * @throws Exception when element contains given text
	 */
	public void elementShouldNotContains(String id, String text) throws Exception {
		String t = this.getText(id);
		if (!(t.contains(text))) {
			logger.log(Status.PASS, id + " contains expected text");
		} else {
			logger.log(Status.FAIL, id + "  contains text as : " + t);
			throw new Exception(id + "  contains text as : " + t);
		}
	}

	/**
	 * Takes element locator value and attribute of element as input and return the
	 * vlule of given attribute
	 * 
	 * @param id        locator
	 * @param attribute attribute name
	 * @return value for given attribute
	 */
	public String getElementAttribute(String id, String attribute) {
		String attri = this.find(id).getAttribute(attribute);
		logger.log(Status.PASS, "getting value of : " + id + attribute);
		return attri;
	}

	/**
	 * Takes dropdown id and time out unit as input and wait untill dropdown values
	 * to be loaded
	 * 
	 * @param path    locator
	 * @param TimeOut time units in secs
	 */
	public void waitUntilDropdownload(String path, int TimeOut) {
		try {
			final String id = path;
			// Thread.sleep(1500);
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeOut))
					.pollingEvery(Duration.ofMillis(200));
			// .ignoring(NoSuchElementException.class);
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					Pages pages= new Pages(driver, logger);
					WebElement ele = pages.Utill().find(id);
//					StringBuffer res = new StringBuffer(ele.getCssValue("display"));
					String res = ele.getCssValue("display");
					if (!res.toString().equals("none")) {
						System.out.println("success " + res);
						res = null;
						return ele;
					} else {
						System.out.println("failed :" + res);
						res = null;
						return null;
					}
				}
			});
		} catch (NoSuchElementException e) {
			// System.out.println("done");
		}
	}

	/**
	 * Takes time time unit as input and wait untill the loader is invisble
	 * 
	 * @param TimeOut time unit in secs
	 */
	public void waitUntilLoaderisInvisible(int TimeOut) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeOut))
					.pollingEvery(Duration.ofMillis(200)).ignoring(StaleElementReferenceException.class);
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					Pages pages= new Pages(driver, logger);
					WebElement ele = pages.Utill().find("loading-bar-spinner");
					StringBuffer res = new StringBuffer(ele.getCssValue("display"));
					// String res = ele.getCssValue("display");
					if (!res.toString().equals("block")) {
						// System.out.println("success " + res);
						// res = null;
						return ele;
					} else {
						// System.out.println("failed :" + res);
						// res = null;
						return null;
					}
				}
			});
		} catch (NoSuchElementException e) {
//			System.out.println("done");
		}
	}

	public void waitUntilLoaderisInvisible(String id, int TimeOut) {
		final String path =id; 
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(TimeOut))
					.pollingEvery(Duration.ofMillis(200)).ignoring(StaleElementReferenceException.class);
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					Pages pages= new Pages(driver, logger);
					WebElement ele = pages.Utill().find(path);
					StringBuffer res = new StringBuffer(ele.getCssValue("display"));
					// String res = ele.getCssValue("display");
					if (!res.toString().equals("block")) {
						// System.out.println("success " + res);
						// res = null;
						return ele;
					} else {
						// System.out.println("failed :" + res);
						// res = null;
						return null;
					}
				}
			});
		} catch (NoSuchElementException e) {
			System.out.println("exception for ajax loader");
			logger.log(Status.WARNING, e.getMessage());
		}
	}

	/**
	 * Takes element locator value and timeout as input and wait for element is
	 * invisible
	 * 
	 * @param path    locator
	 * @param Timeout time unit in secs
	 */
	public void waitUntilElementisVisible(String path, int Timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.visibilityOf(this.find(path)));
	}

	/**
	 * takes the URL of image as input and return true when given URL has image
	 * 
	 * @param url of image
	 * @return true when URL has Image
	 */
	public boolean isImage(String url) {
		try {
			Image image = ImageIO.read(new URL(url));
			if (image != null) {
				return true;

			} else {
				return false;
			}
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * gets the cookies of current session and return it
	 * 
	 * @return cookies as set object
	 */
	public Set<Cookie> getCookies() {
		return driver.manage().getCookies();
	}

	/**
	 * Takes Cookies as input and add it to current browser session
	 * 
	 * @param allcookie contains set of cookies
	 */
	public void importCookies(Set<Cookie> allcookie) {
		for (Cookie cookie : allcookie) {
			driver.manage().addCookie(cookie);
		}

	}

	/**
	 * Accepts the portal alerts and return the text in that alert
	 * 
	 * @return alerttext text in the alert box
	 * @throws Exception when alert not found
	 */
	public String confirmAlert() {
		By loc = By.xpath("//span[text()='OK']");
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfElementLocated(loc));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(loc)));
		String msg = this.getText("class:rwDialogText");
		driver.findElement(loc).click();
		return msg.trim();

	}

	/**
	 * return name as String used for last name in portal
	 * 
	 * @return lastName
	 */
	public String lastName() {
		String name[] = { "Cotton", "Craft", "Cannon", "Larsen", "Ruiz", "Levine", "Santiago", "Smith", "McGuire",
				"Sloan", "Byrd", "Mayer", "Bass", "Holcomb", "Stevens", "Vasquez", "Conway", "Fletcher", "McCray",
				"Olsen", "Allen", "Fry", "Burns", "Garner", "Mayo", "Patton", "Suarez", "Jarvis", "Abbott", "Sloan",
				"Marshall", "Townsend", "Heath", "Burks", "Vega", "Jefferson", "Wilder", "Chaney", "Morgan", "Dudley",
				"Dunn", "Cook", "Franco", "Reilly", "Jackson", "Mercado", "Suarez", "Fields", "Dotson", "Blanchard",
				"Cruz", "McIntyre", "Castillo", "Carr", "Schneider", "Doyle", "Gross", "Whitley", "Wheeler", "Mullen",
				"Kane", "Decker", "Compton", "Adams", "Richmond", "Hurst", "Hayes", "Rowe", "Richardson", "Stark",
				"Walker", "Patterson", "Austin", "Rosa", "Green", "Blackwell", "Roberson", "Stafford", "Nunez",
				"Schmidt", "Stein", "Chang", "Morrison", "McDowell", "Velez", "Flynn", "Brewer", "Kerr", "Wilder",
				"Love", "Bird", "Navarro", "Suarez", "Lloyd", "Powell", "Hahn", "Reed", "Mays" };
		List<String> answersList = Arrays.asList(name);
		Collections.shuffle(answersList);
		return answersList.get(3);
	}

	/**
	 * Takes list of checks as input and return only the address check in that list
	 * 
	 * @param allchecks list of check
	 * @return addresscheck as list
	 */
	public List<String> getAddressChecks(List<String> allchecks) {
		List<String> address = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			address.add(allchecks.get(i).toString());
		}
		return address;
	}

	/**
	 * Takes list of checks as input and return only the education check in that
	 * list
	 * 
	 * @param allchecks list of check
	 * @return educationcheck as list
	 */
	public List<String> getEducationChecks(List<String> allchecks) {
		List<String> education = new ArrayList<String>();
		for (int i = 22; i < 30; i++) {
			education.add(allchecks.get(i).toString());
		}
		return education;
	}

	/**
	 * Takes list of checks as input and return only the employment check in that
	 * list
	 * 
	 * @param allchecks list of check
	 * @return employmentcheck as list
	 */
	public List<String> getEmploymentChecks(List<String> allchecks) {
		List<String> emp = new ArrayList<String>();
		for (int i = 30; i < 35; i++) {
			emp.add(allchecks.get(i).toString());
		}
		return emp;
	}

	/**
	 * Takes list of checks as input and return only the reference check in that
	 * list
	 * 
	 * @param allchecks list of check
	 * @return referencecheck as list
	 */
	public List<String> getReferenceChecks(List<String> allchecks) {
		List<String> ref = new ArrayList<String>();
		for (int i = 45; i < 50; i++) {
			ref.add(allchecks.get(i).toString());
		}
		return ref;
	}

	/**
	 * Takes list of checks as input and return only the id check in that list
	 * 
	 * @param allchecks list of check
	 * @return idcheck as list
	 */
	public List<String> getIdChecks(List<String> allchecks) {
		List<String> id = new ArrayList<String>();
		for (int i = 35; i < 45; i++) {
			id.add(allchecks.get(i).toString());
		}
		return id;
	}

	/**
	 * Takes list of checks as input and return only the criminal check in that list
	 * 
	 * @param allchecks list of check
	 * @return criminalcheck as list
	 */
	public List<String> getCriminalChecks(List<String> allchecks) {
		List<String> cri = new ArrayList<String>();
		for (int i = 13; i < 19; i++) {
			cri.add(allchecks.get(i).toString());
		}
		return cri;
	}

	/**
	 * Takes list of checks as input and return only the court check in that list
	 * 
	 * @param allchecks list of check
	 * @return courtcheck as list
	 */
	public List<String> getCourtChecks(List<String> allchecks) {
		List<String> court = new ArrayList<String>();
		for (int i = 6; i < 12; i++) {
			court.add(allchecks.get(i).toString());
		}
		return court;
	}

	/**
	 * Takes list of checks as input and return only the drug check in that list
	 * 
	 * @param allchecks list of check
	 * @return drugcheck as list
	 */
	public List<String> getDrugChecks(List<String> allchecks) {
		List<String> drug = new ArrayList<String>();
		for (int i = 20; i < 22; i++) {
			drug.add(allchecks.get(i).toString());
		}
		return drug;
	}

	/**
	 * Takes the date as input and change the formate
	 * 
	 * @param date 2007-04-29
	 * @return date dd/mm/yyyy
	 */
	public String formatedob(String date) {
		// String date ="2007-04-29";
		// yyyy-mm-dd
		return date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);

	}

	/**
	 * close the current tab
	 */
	public void closeTab() {
		driver.close();
	}

	/**
	 * used for case tracker wait utill data loads to case tracker table
	 * 
	 * @param xpath   path of table
	 * @param timeout time for wait
	 */
	public void waitUntilElementHasText(String xpath, long timeout) {
		final String paths = xpath;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
//				System.out.println("waiting for data");
				logger.log(Status.INFO, "waiting for data");
				return getText(paths).length() != 0;
//				return d.findElement(By.xpath(paths)).getText().length() != 0;
			}
		});
	}

	/**
	 * takes File object as input and delete the files inside the folder
	 * 
	 * @param folder folder path to be delete
	 * @throws IOException when folder not found in given directory
	 */
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
	/**
	 * Takes driver, time, and file path as input and wait for file download in given path
	 * @param driver webdriver instance
	 * @param time timeout in seconds
	 * @param file file path
	 */
	public void waitForDownload(WebDriver driver,long time,String fileName) {
		final String path=fileName;
		final File file = new File(path);
		final File [] files=file.listFiles();
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofMillis(200));
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver t) {
				if(files.length>0) return true;
				else return false;
			}
		});
	}
	/**
	 * takes the File object as input and return the filename inside the folder
	 * 
	 * @param folder path of file
	 * @return filename inside the folder
	 * @throws Exception when file empty
	 */
	public String isFileExist(File folder) throws Exception {
		Thread.sleep(2000);
		File[] files = folder.listFiles();
		if (files.length > 0) {
			if (files[0].isFile()) {
				String name = files[0].getName();
				return name.replaceAll("[0-9]", "");
			} else {
				return null;
			}
		} else
			return null;
	}
	/**
	 * takes the File object as input and return the filename inside the folder
	 * 
	 * @param folder path of file
	 * @return filename inside the folder
	 * @throws Exception when file empty
	 */
	public String getFileName(File folder) throws Exception {
		Thread.sleep(2000);
		File[] files = folder.listFiles();
		if (files.length > 0) {
			if (files[0].isFile()) {
				String name = files[0].getName();
				return name;
			} else {
				return null;
			}
		} else
			return null;
	}

	/**
	 * Takes file path as input and return the file name in that path
	 * 
	 * @param FilePath directory
	 * @return filename name filtered from file path
	 */
	public String FilterFileName(String FilePath) {
		return FilePath.substring(FilePath.lastIndexOf("\\")).replace("\\", "");
	}

	/**
	 * Takes webelement and file out put path as input takes the screen shot and
	 * save it to given file path
	 * 
	 * @param cap            WebElement
	 * @param fileoutputpath file path
	 * @throws IOException File not found
	 */
	public void screenshot(WebElement cap, String fileoutputpath) throws IOException {
		Screenshot screenshot = new AShot().takeScreenshot(driver, cap);
		ImageIO.write(screenshot.getImage(), "PNG", new File(fileoutputpath));
	}

	/**
	 * Takes file name as input and return file property object from data entry test
	 * data
	 * 
	 * @param filename file name in data entry test data
	 * @return property file object
	 * @throws Exception file not found
	 */
	public Properties dedata(String filename) throws Exception {
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(
				new File("./src\\test\\resources\\testdata\\dataentry\\" + filename + ".properties"));
		pro.load(fis);
		return pro;
	}
	

	/**
	 * Takes file name as input and return file property object from verification
	 * test data
	 * 
	 * @param filename file name in verification test data
	 * @return property file object
	 * @throws Exception file not found
	 */
	public Properties veridata(String filename)  {
		Properties pro = new Properties();
		try {
		FileInputStream fis = new FileInputStream(
				new File("./src\\test\\resources\\testdata\\verification\\" + filename + ".properties"));
		pro.load(fis);
		}
		catch (Exception e) {
			logger.log(Status.FAIL,e.toString());
		}
		return pro;
	}
	public Properties candidatedata(String filename)  {
		Properties pro = new Properties();
		try {
		FileInputStream fis = new FileInputStream(
				new File("./src\\test\\resources\\testdata\\candidatecase\\" + filename + ".properties"));
		pro.load(fis);
		}
		catch (Exception e) {
			logger.log(Status.FAIL,e.toString());
		}
		return pro;
	}

	/**
	 * Takes time in miilli second as input and pass hte thread
	 * 
	 * @param time time in milli seconds
	 */
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public String FormateDate(String date) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("MM/yyyy");
		Date d=df.parse(date);
		return new SimpleDateFormat("MMMM yyyy").format(d).toString();
	}
	public Map<String, String> pageOne(StringTokenizer token) {
		Map<String, String> data = new LinkedHashMap<>();
		while(token.hasMoreTokens()) {
			String next=token.nextToken();
			if(next.contains(":") && !next.contains("ISO")) {
				String [] s= next.split(":");
				data.put(s[0].trim(), s[1].trim());
			}
		}
		return data;
	}
	public String getemail() throws IOException {
		String file = "./log.txt";
		List<String> lines = Files.readAllLines(Paths.get(file));
		String re =lines.get(0).toString().trim();
		return re;
	}
	
}
