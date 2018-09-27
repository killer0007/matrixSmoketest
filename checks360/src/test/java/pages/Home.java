package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public class Home {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public Home(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger=logger;
		pages = new Pages(driver, logger);
	}

	public void Logout() {
		pages.Utill().click_element("ctl00_lnkLogout");
	}

	public void clickRole() {
		pages.Utill().click_element("ctl00_ddlRole_Input");
	}

	public void selectRole(String role) {
		this.clickRole();
		System.out.println("//li[text()='" + role + "']");
		pages.Utill().wait_until_element_isvisible("//li[text()='" + role + "']", 5);
		pages.Utill().click_element("//li[text()='" + role + "']");
		pages.Utill().wait_until_loader_is_invisible(30);
	}

	public List<String> getRoles() {
		List<WebElement> ro = driver.findElements(By.xpath("//*[@id='ctl00_ddlRole_DropDown']/div/ul/li"));
		List<String> roles = new ArrayList<String>();
		this.clickRole();
		pages.Utill().wait_until_element_isvisible("//li[text()='Verification Analyst']", 5);
		for (int i = 0; i < ro.size(); i++) {
			roles.add(ro.get(i).getText());
		}
		return roles;
	}

	public void clickstages() {
		pages.Utill().click_element("ddlAct");
	}

	public void selectStage(String stage) {
		pages.Utill().select_by_label("ddlAct", stage);
		pages.Utill().wait_until_loader_is_invisible(15);

	}

	public List<String> getStages() {
		Select sel = new Select(pages.Utill().find("ddlAct"));
		List<WebElement> li = sel.getOptions();
		List<String> stage = new ArrayList<String>();
		for (int i = 0; i < li.size(); i++) {
			stage.add(li.get(i).getText());
		}
		return stage;
	}

	public String getUserName() {
		return pages.Utill().get_text("ctl00_lblUsername");
	}

	public void clickChangepassword() {
		pages.Utill().click_element("ctl00_lnkLogout");
	}

	public void Actions() {
		pages.Utill().click_element("btnActions");
		pages.Utill().wait_until_loader_is_invisible(15);

	}

	public void CaseTracker() {
		pages.Utill().click_element("CaseTracker");
//		pages.Utill().wait_until_loader_is_invisible(15);
		pages.Utill().switchWindow(1);
		pages.Utill().wait_until_loader_is_invisible(10);
	}

	public void clickRegister() {
		pages.Utill().click_element("btnNewCase_Click");
		pages.Utill().wait_until_loader_is_invisible(15);
	}
public void workStage() {
	pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnStages_input");
	pages.Utill().wait_until_loader_is_invisible(100);
}
	public void clickCaseSearch() {
		pages.Utill().click_element("ButtonDuplicateSearcha");
		pages.Utill().wait_until_loader_is_invisible(15);
	}

	public void getNext() {
		pages.Utill().click_element("btnGetNext");
	}

	public void setCaserefno(String caseno) {
		pages.Utill().input_text("txtCaserefNo", caseno);
	}

	public void setClientrefno(String clientno) {
		pages.Utill().input_text("txtClientrefNo", clientno);
	}

	public void setFirstName(String fname) {
		pages.Utill().input_text("txtFirstName", fname);
	}

	public void setLastName(String lname) {
		pages.Utill().input_text("txtLastName", lname);
	}

	public void selectClient(String client) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", client);
	}

	public void selectProject(String project) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}

	public void selectResgisteredBy(String resgisteredby) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlWorkflowTypeModal']", resgisteredby);
	}

	public void selectChecks(String checks) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlCheckTypeModal']", checks);
	}

	public void selectComponents(String component) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlComponentModal']", component);
	}

	public void selectWorkFlow(String workflow) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlAssignmentModeModal']", workflow);
	}

	public void selectVrStatus(String vrstatus) {
		pages.Utill().select_by_label("//select[@ng-model='PanelHomePageModal.ddlInitiation']", vrstatus);
	}
	public void clickSearch() {
		pages.Utill().click_element("Button3");
		pages.Utill().wait_until_loader_is_invisible(10);
	}
	public void homepage() {
		pages.Utill().click_element("ctl00_imgHome");
		pages.Utill().wait_until_loader_is_invisible(20);
	}
}
