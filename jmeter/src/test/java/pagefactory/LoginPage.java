package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import environment.DbConnection;
import testcase.Pages;

public class LoginPage {
	Pages pages;
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
		Pages pages = new Pages(driver);
	}

	public void setUserName(String name) throws Exception {
		pages.Utill().input_text(pages.Utill().getlocator("uname"), name);
		
		pass = DbConnection.GetlocalPassword(name);

	}

	public void setpassword() throws Exception{
		pages.Utill().input_text(pages.Utill().getlocator("pass"), pass);
//		password.sendKeys(pass);
	}

	public void ClickLogin() throws Exception {
		pages.Utill().click_element(pages.Utill().getlocator("loginbtn"));
//		loginbtn.click();
	}

	public void login(String name) throws Exception {
		this.setUserName(name);
		this.setpassword();
		this.ClickLogin();
	}

}
