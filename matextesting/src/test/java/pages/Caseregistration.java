package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import userLogin.Pages;
import environment.Wait;

public class Caseregistration {
	WebDriver driver;
	protected final Pages pages;
	Wait wait;
	ExtentTest logger;
	public Caseregistration(WebDriver driver,ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
		pages = new Pages(driver,logger);
	}
	public String caseRegistration(String clientName, int cadID, String cadName) throws Exception {
		
		String xp = "ctl00_ContentPlaceHolder1_CaseRegistrationUC";
		pages.Utill().click_element("//*[text()='Dashboard']");
		pages.Utill().click_element(".//*[text()='Case Registration']");
		pages.Utill().input_text(xp + "_txtClientName", clientName);
		pages.Wait().waitForVisibilityOfData(xp + "_ace1_completionListElem",5);
		pages.Utill().click_element(".//*[text()='"+clientName+"']");
		Select sel = new Select(pages.Utill().find(xp + "_TypeofMedium"));
		sel.selectByIndex(1);
		sel = new Select(pages.Utill().find(xp + "_ddlSubGroup"));
		sel.selectByIndex(1);
		pages.Utill().input_text(xp + "_Case_CandidateName", cadName);
		pages.Utill().input_text(xp + "_Case_CandidateId", Integer.toString(cadID));
		pages.Utill().input_text(xp + "_Case_EmployeeId", "43232");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_CaseRegistrationUC_btnRegister");
		WebDriverWait w =new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
		String result=pages.Utill().get_text("//*[@class='m_content']");
		pages.Utill().click_element("ok");
		return result;
	}
	public void navigateTo(String header, String menu) throws Exception{
		//pages = new Pages(driver);
		try {
			WebDriverWait w=new WebDriverWait(driver, 50);
			w.until(ExpectedConditions.elementToBeClickable(pages.Utill().find("//*[text()='"+header+"']")));
			pages.Utill().click_element("//*[text()='"+header+"']");
			pages.Utill().click_element("//*[text()='"+menu+"']");
		} catch (WebDriverException e) {
			
			driver.navigate().to("http://192.168.2.16/MatexTesting/Matrix/AssignerHome.aspx");
		}
	}
	public String assignToDETM(String cadName,int cadId ) throws Exception {
		//pages = new Pages(driver);
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCandidateName", cadName);
		pages.Utill().input_text("ctl00_ContentPlaceHolder1_txtCandidateid", Integer.toString(cadId));
		pages.Wait().wait_until_loader_is_invisible();
		String no =pages.Utill().GetTableCellValue("ctl00_ContentPlaceHolder1_grdCRT", 2, 4);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdCRT_ctl02_chkCRTSelect");
		pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_ddlTM", "demotl");
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnAssign");
		pages.Wait().wait_until_loader_is_invisible();
		String temp = pages.Utill().clickAlertbox();
		System.out.println(temp);
		
		return no;
		
	}
}
