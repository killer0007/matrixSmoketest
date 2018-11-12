package dashboard;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import environment.Pages;

public class ReportValidation  {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public ReportValidation(WebDriver driver, ExtentTest logger) {
		this.driver=driver;
		this.logger = logger;
		pages = new Pages(driver,logger);
	}
	public void reportValidation() {
		String stage=pages.Utill().getValue("ddlAct");
		if(!stage.equals("10")) {
			pages.Utill().selectByValue("ddlAct", "10");
			pages.Utill().waitUntilLoaderisInvisible(50);
		}	
	}
	public void GetNext() {
		pages.Utill().click("btnGetNext");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().click("imgHome");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void CaseRefNo(String refno) {
		pages.Utill().sendKeys("txtCaserefNo", refno);
	}
	
	public void ClientRefNo(String refno) {
		pages.Utill().sendKeys("txtClientrefNo", refno);
	}
	public void  FirstName(String name) {
		pages.Utill().sendKeys("txtFirstName", name);
	}
	public void  LastName(String name) {
		pages.Utill().sendKeys("txtLastName", name);
	}
	public void Client(String clientName) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", clientName);
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void Project(String project) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}
	public void RegisteredBy(String registeredBy) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlWorkflowTypeModal']", registeredBy);
	}
	public void ReportStatus(String status) {
		pages.Utill().selectByLabel("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void Search() {
		pages.Utill().click("btnsearch");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void Search(String refno) {
		this.CaseRefNo(refno);
		this.Search();
	}
	public void Select(String refno) {
		pages.Utill().click("//span[text()='"+refno+"']");
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void GenerateReport() {
		pages.Utill().click("btnReportGenerate");
	}
	public void PublishReport() {
		pages.Utill().click("rwReportComponent_C_btnPublishReport_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public List<String> getReportComponents() {
		List<WebElement> name=driver.findElements(By.xpath("//*[@id='rwReportComponent_C_grdReportComponent_ctl00']/tbody/tr/td[7]"));
		List<String> component=new ArrayList<>();
		for (int i = 0; i < name.size(); i++) {
			component.add(name.get(i).getText().toString().trim());
		}
		return component;
	}
	public void GenerateReportCheckbox() {
		pages.Utill().click("rwReportComponent_C_grdReportComponent_ctl00_ctl02_ctl00_chkReportSelectSelectCheckBox");
	}
	public void ReportComments(String comments) {
		pages.Utill().SwitchFramebyId("rwReportComponent_C_txtReportComment_contentIframe");
		pages.Utill().sendKeys("/html/body", comments);
		pages.Utill().SwitchDefault();
	}
	public void ReportTemplate(String TempName) {
		String value=pages.Utill().getValue("rwReportComponent_C_ddlTemplate_Input");
		if(!value.equals(TempName)) {
		pages.Utill().click("rwReportComponent_C_ddlTemplate_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='rwReportComponent_C_ddlTemplate_DropDown']/div/ul/li[1]")));
		pages.Utill().sleep(500);
			pages.Utill().click(
					"//*[@id='rwReportComponent_C_ddlTemplate_DropDown']/div/ul//li[text()='" + TempName + "']");
		//	System.out.println("//*[@id='rwReportComponent_C_ddlTemplate_DropDown']/div/ul//li[text()='" + TempName + "']");
			
		}
	}
	public void CaseStatus(String TempName) {
		String value=pages.Utill().getValue("rwReportComponent_C_ddlCaseStatus_Input");
		if(!value.equals(TempName)) {
		pages.Utill().click("rwReportComponent_C_ddlCaseStatus_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='rwReportComponent_C_ddlCaseStatus_DropDown']/div/ul/li[1]")));
		pages.Utill().sleep(500);
			pages.Utill().click(
					"//*[@id='rwReportComponent_C_ddlCaseStatus_DropDown']/div/ul//li[text()='" + TempName + "']");
			
		}
	}
	public void previewReport() {
		pages.Utill().click("rwReportComponent_C_btnRptPreview_input");
	}
	public void submit() {
		pages.Utill().click("rwReportComponent_C_btnSubmitReport_input");
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void close() {
		pages.Utill().click("class:rwCloseButton");
	}
}
