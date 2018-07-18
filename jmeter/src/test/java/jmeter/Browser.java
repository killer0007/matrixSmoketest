package jmeter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
	WebDriver driver;

	public static void main(String[] args) {
		Browser b = new Browser();
		b.matex("yes", "demotl", "pass@123");
	}

	public String matex(String headless, String name, String pass) {
		try {
			if (headless.equalsIgnoreCase("Yes")) {
				ChromeOptions op = new ChromeOptions();
				op.setHeadless(true);
				driver = new ChromeDriver(op);
				Dimension d = new Dimension(1382, 744);
				driver.manage().window().setSize(d);
				driver.get("http://192.168.2.16/MatexTesting");

			} else {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("http://192.168.2.16/MatexTesting");

			}

			String re = login(name, pass);
			driver.close();
			return re;
		} catch (Exception e) {
			return e.getMessage().toString();

		}
	}

	public String login(String name, String pass) {
		try {
			driver.findElement(By.id("txtUsername")).sendKeys(name);
			driver.findElement(By.id("txtPassword")).sendKeys(pass);
			driver.findElement(By.id("btnLogin")).click();
			return driver.findElement(By.id("ctl00_lblWelcome")).getText();
		} catch (UnhandledAlertException e) {
			Alert alert = driver.switchTo().alert();
			String op = alert.getText();
			alert.accept();
			return op;
		}
	}
}
