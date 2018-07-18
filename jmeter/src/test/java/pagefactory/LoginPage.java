package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import environment.DbConnection;

public class LoginPage {
	WebDriver driver;
	@FindBy(id = "txtUsername")
	WebElement username;
	@FindBy(id = "txtPassword")
	WebElement password;
	@FindBy(id = "btnLogin")
	WebElement loginbtn;
	String pass;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setUserName(String name) throws Exception {
		username.sendKeys(name);
		pass = DbConnection.GetlocalPassword(name);

	}

	public void setpassword() {
		password.sendKeys(pass);
	}

	public void ClickLogin() {
		loginbtn.click();
	}

	public void login(String name) throws Exception {
		this.setUserName(name);
		this.setpassword();
		this.ClickLogin();
	}

}
