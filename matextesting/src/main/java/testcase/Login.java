package testcase;

import org.testng.annotations.*;

import pagefactory.Homepage;
import pagefactory.LoginPage;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	WebDriver driver;
	LoginPage login;
	Homepage home;

	@BeforeTest
	public void start() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.2.16/MatexTesting");
		login = new LoginPage(driver);
		home = new Homepage(driver);
	}

	@Test
	public void matrixLogin() throws Exception {
		login.login("demotl");
		String name = home.getLogedinUser();
		boolean result = name.equalsIgnoreCase("demotl");
		assertTrue(result);
	}

	@AfterTest
	public void teardown() {
		driver.close();
	}
}
