package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;

import environment.BaseClass;
import environment.Pages;

public class Home {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;
	/**
	 * This is class for Home Page
	 * 
	 * @param logger logger instance
	 */
	public Home(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}
/**
 * Performs click action on Logout button
 */
	public void Logout() {
		pages.Utill().click("ctl00_lnkLogout");
	}
/**
 * Performs click action on Role Dropdown
 */
	public void clickRole() {
		pages.Utill().click("ctl00_ddlRole_Input");
	}
/**
 * Takes role name as input and select from role dropdown
 * @param role Role name
 */
	public void selectRole(String role) {
		this.clickRole();
		System.out.println("//li[text()='" + role + "']");
		pages.Utill().waitUntilElementisVisible("//li[text()='" + role + "']", 5);
		pages.Utill().click("//li[text()='" + role + "']");
		pages.Utill().waitUntilLoaderisInvisible(30);
	}
/**
 * Returns the all role as list
 * @return Roles as list
 */
	public List<String> getRoles() {
		List<WebElement> ro = driver.findElements(By.xpath("//*[@id='ctl00_ddlRole_DropDown']/div/ul/li"));
		List<String> roles = new ArrayList<String>();
		this.clickRole();
		pages.Utill().waitUntilElementisVisible("//li[text()='Verification Analyst']", 5);
		for (int i = 0; i < ro.size(); i++) {
			roles.add(ro.get(i).getText());
		}
		return roles;
	}
	/**
	 * Performs click action on Stages dropdown
	 */
	public void clickstages() {
		pages.Utill().click("ddlAct");
	}
/**
 * Takes stage as input and select the given stage from drodown
 * @param stage Stage name
 */
	public void selectStage(String stage) {
		pages.Utill().selectByLabel("ddlAct", stage);
		pages.Utill().waitUntilLoaderisInvisible(15);

	}
/**
 * Returns all the stage as list
 * @return stage list
 */
	public List<String> getStages() {
		Select sel = new Select(pages.Utill().find("ddlAct"));
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
		return pages.Utill().getText("ctl00_lblUsername");
	}
/**
 * Performs click action on change password link
 */
	public void clickChangepassword() {
		pages.Utill().click("ctl00_lnkLogout");
	}
	/**
	 * Performs click action on Actions button
	 */
	public void Actions() {
		pages.Utill().click("btnActions");
		pages.Utill().waitUntilLoaderisInvisible(15);

	}
	/**
	 * Performs click action on Case Tracker button and switched to new window
	 */
	public void CaseTracker() {
		pages.Utill().click("CaseTracker");
//		pages.Utill().waitUntilLoaderisInvisible(15);
		pages.Utill().switchWindow(1);
		pages.Utill().waitUntilLoaderisInvisible(10);
	}
/**
 * Performs click action on Register New Case button
 * @throws Exception ElementNotVisibleException
 */
	public void clickRegister() throws Exception {
		try {
		pages.Utill().click("btnNewCase_Click");
		}
		catch(ElementNotVisibleException e) {
			Thread.sleep(3000);
			pages.Utill().click("btnNewCase_Click");	
		}
		
		pages.Utill().waitUntilLoaderisInvisible(15);
	}
	/**
	 * Performs click action on work stage button
	 */
public void workStage() {
	pages.Utill().click("ctl00_ContentPlaceHolder1_btnStages_input");
	pages.Utill().waitUntilLoaderisInvisible(100);
}

/**
 * Performs click action on Case Search button
 */
public void clickCaseSearch() {
		pages.Utill().click("ButtonDuplicateSearcha");
		pages.Utill().waitUntilLoaderisInvisible(15);
	}
/**
 * Performs click action on get Next button
 */
	public void getNext() {
		pages.Utill().click("btnGetNext");
	}
/**
 * Takes case reference number as input and pass the value to caserefno field
 * @param caseno Case Reference Number
 */
	public void setCaserefno(String caseno) {
		pages.Utill().sendKeys("txtCaserefNo", caseno);
	}
	/**
	 * Takes client reference number as input and pass the value to ClientrefNo field
	 * @param clientno Case Reference Number
	 */
	public void setClientrefno(String clientno) {
		pages.Utill().sendKeys("txtClientrefNo", clientno);
	}
	/**
	 * Takes FirstName as input and pass the value to FirstName field
	 * @param fname FirstName
	 */
	public void setFirstName(String fname) {
		pages.Utill().sendKeys("txtFirstName", fname);
	}
	/**
	 * Takes LastName as input and pass the value to LastName field
	 * @param lname LastName
	 */
	public void setLastName(String lname) {
		pages.Utill().sendKeys("txtLastName", lname);
	}
	/**
	 * Takes client name as input and pass it to client name field
	 * @param client client name
	 */
	public void selectClient(String client) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", client);
	}
	/**
	 * Takes project name as input and pass it to project name field
	 * @param project project name
	 */
	public void selectProject(String project) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}

//	public void selectResgisteredBy(String resgisteredby) {
//		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlWorkflowTypeModal']", resgisteredby);
//	}

//	public void selectChecks(String checks) {
//		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']", checks);
//	}
//
//	public void selectComponents(String component) {
//		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlComponentModal']", component);
//	}
//
//	public void selectWorkFlow(String workflow) {
//		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlAssignmentModeModal']", workflow);
//	}
//
//	public void selectVrStatus(String vrstatus) {
//		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlInitiation']", vrstatus);
//	}
//	public void clickSearch() {
//		pages.Utill().click("Button3");
//		pages.Utill().waitUntilLoaderisInvisible(10);
//	}
	/**
	 * Performs click action on home icon
	 */
	public void homepage() {
		pages.Utill().click("ctl00_imgHome");
		pages.Utill().waitUntilLoaderisInvisible(20);
	}
}
