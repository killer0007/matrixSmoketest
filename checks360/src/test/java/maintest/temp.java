package maintest;

import java.util.Set;
import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class temp {
public static void main(String[] args) throws Exception{
//WebDriver driver=new ChromeDriver();
//driver.get("http://192.168.2.17:96/Login.aspx");
//driver.manage().window().maximize();
//driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtUserName")).sendKeys("demoemp");
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
	DataFactory df = new DataFactory();
	for (int i = 0; i < 100; i++) {
		String fname=df.getFirstName();
//		String lname = df.getLastName();
		System.out.println(fname);
//		System.out.println(fname+" : "+lname);
	}
	
}
}
