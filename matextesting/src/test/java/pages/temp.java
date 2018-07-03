package pages;
 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import testCases.Pages;
 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class temp {
 public static void main(String[] args) {
	 String t ="rgba(0, 0, 0, 0) url(\"http://192.168.2.16/MatexTesting/Extn/img/logoin.gif\") no-repeat scroll 5px 60% / auto padding-box border-box";
	 System.out.println(t.matches(".*\\bhttp://192.168.2.16/MatexTesting/Extn/img/logoin.gif\\b.*"));
}
	public static boolean getlogo(WebDriver driver) throws Exception{
		
		try {
			//String scr=pages.Utill().find("//*[@class='logoin']").getCssValue("background");
			String scr=driver.findElement(By.xpath("//*[@class='logoin']")).getCssValue("background");
			System.out.println("try "+scr);
			if(scr.matches(".*\\bhttp://192.168.2.16/MatexTesting/Extn/img/logoin.gif\\b.*")) {
				return true;
			}
			else {
				return false;
			}
		} catch (NoSuchElementException e) {
			//Thread.sleep(1500);
			try {
				String scr=driver.findElement(By.xpath("//*[@class='logo']")).getAttribute("src");
				System.out.println("catch "+scr);
				if(scr.matches(".*\\bhttp://192.168.2.16/MatexTesting/Styles/images/logoin.gif\\b.*")) {
					
					return true;
				}
				else {
					return false;
				}
			} catch (NoSuchElementException e1) {
				// TODO Auto-generated catch block
				System.out.println("catch final");
				return false;
			}
		}
		
		
	}
}