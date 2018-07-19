package testcase;

import org.openqa.selenium.WebDriver;
import environment.Utill;
import pagefactory.*;




public class Pages {
	WebDriver driver;
	

	public Pages(WebDriver driver) {
		this.driver = driver;
		
	}

	public LoginPage loginpage() {
		LoginPage login = new LoginPage(driver);
		return login;
	}

	public Homepage Homepage() {
		Homepage casereg = new Homepage(driver);
		return casereg;
	}
	public Utill Utill() {
		Utill u = new Utill(driver);
		return u;
	}
	
}
