package maintest;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.internal.support.FileReader;

public class temp {
public static void main(String[] args) throws Exception{
//	s//driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtUserName")).sendKeys("demoemp");
//driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtPassword")).sendKeys("Pass@123");
//driver.findElement(By.id("ctl00_ContentPlaceHolder1_btnLogin")).click();
//Set<Cookie> allcookie = driver.manage().getCookies();
////JavascriptExecutor js = (JavascriptExecutor) driver;
////js.executeScript("document.getElementById('ctl00_lnkLogout').click()");
////driver.findElement(By.id("ctl00_lnkLogout")).click();
//driver.close();
//for(Cookie cookie : allcookie) {
//    System.out.println(cookie.getValue());
//}
////Thread.sleep(1260000);
//WebDriver driver2=new ChromeDriver();
//driver2.get("http://192.168.2.17:96/Login.aspx");
//for(Cookie cookie : allcookie) {
//    driver2.manage().addCookie(cookie);
//}
//driver2.get("http://192.168.2.17:97/Web/dashboard.aspx");
//	DataFactory df = new DataFactory();
//	for (int i = 0; i < 100; i++) {
////		String fname=df.getFirstName();
//		String lname = df.getLastName();
//		System.out.println(lname);
////		System.out.println(fname+" : "+lname);
//	}
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	
////	Date date = new Date();
//	LocalDateTime now = LocalDateTime.now();
//	String ds=df.format(now);
////	String ds=date.toString();
//	System.out.println(ds);
//	int year=Integer.parseInt(ds.substring(6, 10))-20;
//	System.out.println(year);

	Random random = new Random();
	int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
	int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
	long randomDay = minDay + random.nextInt(maxDay - minDay);

	LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

	System.out.println(df.format(randomBirthDate).toString());
}
}
