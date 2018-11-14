package dashboard;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import environment.Utill;

public class ReportValidation  extends Utill{

	/**
	 * This is class for Data Entry Stage
	 *
	 * @param logger logger instance
	 */
	public ReportValidation(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	public void reportValidation() {
		String stage=getValue("ddlAct");
		if(!stage.equals("10")) {
			selectByValue("ddlAct", "10");
			waitUntilLoaderisInvisible(50);
		}	
	}
	public void GetNext() {
		click("btnGetNext");
		waitUntilLoaderisInvisible(100);
		click("imgHome");
		waitUntilLoaderisInvisible(100);
	}
	public void CaseRefNo(String refno) {
		sendKeys("txtCaserefNo", refno);
	}
	
	public void ClientRefNo(String refno) {
		sendKeys("txtClientrefNo", refno);
	}
	public void  FirstName(String name) {
		sendKeys("txtFirstName", name);
	}
	public void  LastName(String name) {
		sendKeys("txtLastName", name);
	}
	public void Client(String clientName) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredClientModal']", clientName);
		waitUntilLoaderisInvisible(100);
	}
	public void Project(String project) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlFilteredprojectMadal']", project);
	}
	public void RegisteredBy(String registeredBy) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlWorkflowTypeModal']", registeredBy);
	}
	public void ReportStatus(String status) {
		selectByLabel("//select[@ng-model='PanelHomePageModal.ddlstagestatus']", status);
	}
	public void Search() {
		click("btnsearch");
		waitUntilLoaderisInvisible(100);
	}
	public void Search(String refno) {
		this.CaseRefNo(refno);
		this.Search();
	}
	public void Select(String refno) {
		click("//span[text()='"+refno+"']");
		waitUntilLoaderisInvisible(100);
	}
	public void GenerateReport() {
		click("btnReportGenerate");
	}
	public void PublishReport() {
		click("rwReportComponent_C_btnPublishReport_input");
		waitUntilLoaderisInvisible(100);
		confirmAlert();
		waitUntilLoaderisInvisible(100);
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
		click("rwReportComponent_C_grdReportComponent_ctl00_ctl02_ctl00_chkReportSelectSelectCheckBox");
	}
	public void ReportComments(String comments) {
		SwitchFramebyId("rwReportComponent_C_txtReportComment_contentIframe");
		sendKeys("/html/body", comments);
		SwitchDefault();
	}
	public void ReportTemplate(String TempName) {
		String value=getValue("rwReportComponent_C_ddlTemplate_Input");
		if(!value.equals(TempName)) {
		click("rwReportComponent_C_ddlTemplate_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='rwReportComponent_C_ddlTemplate_DropDown']/div/ul/li[1]")));
		sleep(500);
			click(
					"//*[@id='rwReportComponent_C_ddlTemplate_DropDown']/div/ul//li[text()='" + TempName + "']");
		//	System.out.println("//*[@id='rwReportComponent_C_ddlTemplate_DropDown']/div/ul//li[text()='" + TempName + "']");
			
		}
	}
	public void CaseStatus(String TempName) {
		String value=getValue("rwReportComponent_C_ddlCaseStatus_Input");
		if(!value.equals(TempName)) {
		click("rwReportComponent_C_ddlCaseStatus_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='rwReportComponent_C_ddlCaseStatus_DropDown']/div/ul/li[1]")));
		sleep(500);
			click(
					"//*[@id='rwReportComponent_C_ddlCaseStatus_DropDown']/div/ul//li[text()='" + TempName + "']");
			
		}
	}
	public void previewReport() {
		click("rwReportComponent_C_btnRptPreview_input");
	}
	public void submit() {
		click("rwReportComponent_C_btnSubmitReport_input");
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}
	public void close() {
		click("class:rwCloseButton");
	}
}
