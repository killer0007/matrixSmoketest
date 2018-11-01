package maintest2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class temp2 {
	WebDriver driver;
public static void main(String[] args) {
	temp2 t = new temp2();
	t.getWebDriver();
}
	public WebDriver getWebDriver() {
		if (driver == null)
			driver = new ChromeDriver();
		return driver;
	}

}
