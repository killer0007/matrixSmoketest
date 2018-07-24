package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class temp {

	public static void main(String[] args) throws Exception {
	WebDriver driver= new ChromeDriver();
	driver.get("http://192.168.2.16/reportserver/Pages/ReportViewer.aspx?%2fReportForTesting%2fCaseHistory&rs:Command=Render&MatrixRefNo=DTCSSAA145");
//	WebDriverWait wait = new WebDriverWait(driver, 10);
//	wait.until(ExpectedConditions.alertIsPresent());
	System.out.println("starting");
	driver.switchTo().alert().sendKeys("administrator" + Keys.TAB + "kadamba@123$");
	driver.switchTo().alert().accept();	
		System.out.println("ending");
	}
}
