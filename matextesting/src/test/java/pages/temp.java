package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class temp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//
	}

	public static void geturl(WebDriver driver) throws Exception {
		String path = ".//*[@id='ctl00_pnlHide']/div[2]/ul/li[1]/ul/li/a";
		driver.findElement(By.xpath("//*[text()='Dashboard']")).click();
		Map<String, String> map = new HashMap<String, String>();
		List<String> url = new ArrayList<String>();
		List<WebElement> ele = driver.findElements(By.xpath(path));
		for (int i = 0; i < ele.size(); i++) {
			String te = ele.get(i).getText();
			url.add(te);

		}
		System.out.println(url);
		for (int i = 0; i < url.size(); i++) {
			//System.out.println(url.get(i));
			try {
				driver.findElement(By.xpath("//*[text()='Dashboard']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[text()='" + url.get(i) + "']")).click();
				Thread.sleep(1000);
				//System.out.println(driver.getTitle());
				map.put(url.get(i), driver.getTitle());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				map.put(url.get(i), "error");
				driver.navigate().to("http://192.168.2.16/MatexTesting/Matrix/UserHome.aspx");
			}

		}
		printMap(map);
	}

	public static void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}
}
