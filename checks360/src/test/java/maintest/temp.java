package maintest;



import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import environment.BaseClass;

@Listeners(environment.Listener.class)

public class temp {
	public static void main(String[] args) throws Exception {
		WebDriver driver=BaseClass.getDriver();
		driver.get("http://192.168.2.17:97/Login.aspx");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtUserName")).sendKeys("gopi");
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtUserName")).sendKeys(Keys.CONTROL, "a");
		WebElement name = driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtUserName"));
		name.clear();
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtPassword")).sendKeys(Keys.CONTROL,"v");
		name.sendKeys(Keys.CONTROL,"v");
//		Actions action = new Actions(driver);
//		action.moveToElement(name).doubleClick().sendKeys(Keys.CONTROL+"c").
//		
//		sendKeys(name,Keys.TAB).sendKeys(name,Keys.CONTROL+"v")
//		.build().perform();
//		action.release();
//		Actions action1 = new Actions(driver);
//		action1.moveToElement(name).click().sendKeys(Keys.TAB).sendKeys(Keys.CONTROL,"v").build().perform();
//		action.release();
	}
}
