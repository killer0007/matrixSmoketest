package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testcase.Pages;

public class Homepage {
	WebDriver driver;
	
	protected final Pages pages;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pages = new Pages(driver);
	}

	public String getLogedinUser() throws Exception {
		
		return pages.Utill().get_text(pages.Utill().getlocator("loginedname"));
	}
	public String moveto(String title,String name) throws Exception{
		pages.Utill().click_element(title);
		pages.Utill().click_element(name);
		return pages.Utill().getTitle();
	}
	
}
