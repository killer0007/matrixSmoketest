package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class Home extends Utill{
	ExtentTest logger;
	/**
	 * This is class for Home Page
	 * 
	 * @param logger logger instance
	 */
	public Home(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
/**
 * Performs click action on Logout button
 */
	public void Logout() {
		click("ctl00_lnkLogout");
	}
/**
 * Performs click action on Role Dropdown
 */
	public void clickRole() {
		click("ctl00_ddlRole_Input");
	}
/**
 * Takes role name as input and select from role dropdown
 * @param role Role name
 */
	public void selectRole(String role) {
		this.clickRole();
		System.out.println("//li[text()='" + role + "']");
		waitUntilElementisVisible("//li[text()='" + role + "']", 5);
		click("//li[text()='" + role + "']");
		waitUntilLoaderisInvisible(30);
	}
/**
 * Returns the all role as list
 * @return Roles as list
 */
	public List<String> getRoles() {
		List<WebElement> ro = driver.findElements(By.xpath("//*[@id='ctl00_ddlRole_DropDown']/div/ul/li"));
		List<String> roles = new ArrayList<String>();
		this.clickRole();
		waitUntilElementisVisible("//li[text()='Verification Analyst']", 5);
		for (int i = 0; i < ro.size(); i++) {
			roles.add(ro.get(i).getText());
		}
		return roles;
	}
	/**
	 * Performs click action on Stages dropdown
	 */
	public void clickstages() {
		click("ddlAct");
	}
/**
 * Takes stage as input and select the given stage from drodown
 * @param stage Stage name
 */
	public void selectStage(String stage) {
		selectByLabel("ddlAct", stage);
		waitUntilLoaderisInvisible(15);

	}
/**
 * Returns all the stage as list
 * @return stage list
 */
	public List<String> getStages() {
		Select sel = new Select(find("ddlAct"));
		List<WebElement> li = sel.getOptions();
		List<String> stage = new ArrayList<String>();
		for (int i = 0; i < li.size(); i++) {
			stage.add(li.get(i).getText());
		}
		return stage;
	}
/**
 * returns logged in user name
 * @return name user name
 */
	public String getUserName() {
		return getText("ctl00_lblUsername");
	}
/**
 * Performs click action on change password link
 */
	public void clickChangepassword() {
		click("ctl00_lnkLogout");
	}
	/**
	 * Performs click action on Actions button
	 */
	public void Actions() {
		click("btnActions");
		waitUntilLoaderisInvisible(15);

	}
	/**
	 * Performs click action on Case Tracker button and switched to new window
	 */
	public void CaseTracker() {
		click("CaseTracker");
//		waitUntilLoaderisInvisible(15);
		switchWindow(1);
		waitUntilLoaderisInvisible(10);
	}
/**
 * Performs click action on Register New Case button
 * @throws Exception ElementNotVisibleException
 */
	public void clickRegister()  {
		try {
		click("btnNewCase_Click");
		}
		catch(ElementNotVisibleException e) {
			super.sleep(3000);
			click("btnNewCase_Click");	
		}
		
		waitUntilLoaderisInvisible(15);
	}
	/**
	 * Performs click action on work stage button
	 */
public void workStage() {
	click("ctl00_ContentPlaceHolder1_btnStages_input");
	waitUntilLoaderisInvisible(100);
}

/**
 * Performs click action on Case Search button
 */
public void clickCaseSearch() {
		click("ButtonDuplicateSearcha");
		waitUntilLoaderisInvisible(15);
	}
/**
 * Performs click action on get Next button
 */
	public void getNext() {
		click("btnGetNext");
	}
/**
 * Takes case reference number as input and pass the value to caserefno field
 * @param caseno Case Reference Number
 */
	public void setCaserefno(String caseno) {
		sendKeys("txtCaserefNo", caseno);
	}
	/**
	 * Takes client reference number as input and pass the value to ClientrefNo field
	 * @param clientno Case Reference Number
	 */
	public void setClientrefno(String clientno) {
		sendKeys("txtClientrefNo", clientno);
	}
	/**
	 * Takes FirstName as input and pass the value to FirstName field
	 * @param fname FirstName
	 */
	public void setFirstName(String fname) {
		sendKeys("txtFirstName", fname);
	}
	/**
	 * Takes LastName as input and pass the value to LastName field
	 * @param lname LastName
	 */
	public void setLastName(String lname) {
		sendKeys("txtLastName", lname);
	}
	/**
	 * Takes client name as input and pass it to client name field
	 * @param client client name
	 */
	public void selectClient(String client) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", client);
	}
	/**
	 * Takes project name as input and pass it to project name field
	 * @param project project name
	 */
	public void selectProject(String project) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}

	/**
	 * Performs click action on home icon
	 */
	public void homepage() {
		click("ctl00_imgHome");
		waitUntilLoaderisInvisible(20);
	}
}
