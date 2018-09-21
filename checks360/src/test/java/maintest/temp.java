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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.graphbuilder.struc.LinkedList;

import io.restassured.internal.support.FileReader;

public class temp {
public static void main(String[] args) throws Exception{

WebDriver driver= new ChromeDriver();
driver.get("file:///C:/Users/admin/Downloads/Qp.html");
List<String> actual=new ArrayList<String>();
List<WebElement> list = driver.findElements(By.xpath("html/body/div[1]/div[3]/div/div"));
for (int i = 0; i < list.size(); i++) {
	List<WebElement> tr =list.get(i).findElements(By.xpath("table/tbody/tr/td/table/tbody/tr/td/img[1]"));
	for (int j = 1; j < tr.size(); j++) {
		String scr=tr.get(j).getAttribute("src");
		if(scr.contains("tick")) {
//			System.out.println((i+1)+" = "+j+" pass");
			actual.add(Integer.toString(j));
			break;
		}
		else if(scr.contains("cross")) {
//			System.out.println(i+" failed");
			continue;
		}
		else {
			throw new Exception(scr);
			
		}
	}
	
}

List<WebElement> aclist=driver.findElements(By.xpath("html/body/div[1]/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td[2]"));
List<String> expected=new ArrayList<String>();
for (int i = 0; i < aclist.size(); i++) {
	
	expected.add(aclist.get(i).getText());
}

//System.out.println("actual "+actual.size());
//System.out.println("expected "+expected.size());	
int pass=0;
int fail=0;
for (int i = 0; i < 75; i++) {
	if(actual.get(i).equals(expected.get(i))) {
		pass++;
	}
	else if(!expected.get(i).contains("-")) {
		fail++;
	}
}
System.out.println("pass ="+pass);
System.out.println("fail ="+fail);
int result=pass-(fail/3);
System.out.println("result is "+ result);
}

}
