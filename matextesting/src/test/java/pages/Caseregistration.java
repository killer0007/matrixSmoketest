package pages;

import java.util.concurrent.TimeoutException;

import javax.activity.InvalidActivityException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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
	public String caseRegistration(String clientName, int cadID, String cadName) throws InterruptedException, NoSuchElementException, TimeoutException {
		
		String xp = "ctl00_ContentPlaceHolder1_CaseRegistrationUC";
		pages.Utill().find("//*[text()='Dashboard']").click();
		pages.Utill().find(".//*[text()='Case Registration']").click();
		pages.Utill().find(xp + "_txtClientName").sendKeys(clientName);
		pages.Wait().waitForVisibilityOfData(xp + "_ace1_completionListElem",5);
		pages.Utill().find(".//*[text()='"+clientName+"']").click();
		Select sel = new Select(pages.Utill().find(xp + "_TypeofMedium"));
		sel.selectByIndex(1);
		sel = new Select(pages.Utill().find(xp + "_ddlSubGroup"));
		sel.selectByIndex(1);
		pages.Utill().find(xp + "_Case_CandidateName").sendKeys(cadName);
		pages.Utill().find(xp + "_Case_CandidateId").sendKeys(Integer.toString(cadID));
		pages.Utill().find(xp + "_Case_EmployeeId").sendKeys("43232");
		pages.Utill().find("ctl00_ContentPlaceHolder1_CaseRegistrationUC_btnRegister").click();
		WebDriverWait w =new WebDriverWait(driver, 10);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id("ok")));
		String result=pages.Utill().find("//*[@class='m_content']").getText();
		pages.Utill().find("ok").click();
		return result;
	}
	public void navigateTo(String header, String menu) throws NoSuchElementException{
		//pages = new Pages(driver);
		pages.Utill().find("//*[text()='"+header+"']").click();
		pages.Utill().find("//*[text()='"+menu+"']").click();
	}
	public String assignToDETM(String cadName,int cadId ) throws InterruptedException, NoSuchElementException, InvalidActivityException {
		//pages = new Pages(driver);
		pages.Utill().find("ctl00_ContentPlaceHolder1_txtCandidateName").sendKeys(cadName);
		pages.Utill().find("ctl00_ContentPlaceHolder1_txtCandidateid").sendKeys(Integer.toString(cadId));
		pages.Utill().find("ctl00_ContentPlaceHolder1_butnSearch").click();
		pages.Wait().wait_until_loader_is_invisible();
		String no =pages.Utill().GetTableCellValue("ctl00_ContentPlaceHolder1_grdCRT", 2, 4);
		pages.Utill().find("ctl00_ContentPlaceHolder1_grdCRT_ctl02_chkCRTSelect").click();
		Select sel = new Select(pages.Utill().find("ctl00_ContentPlaceHolder1_ddlTM"));
		sel.selectByVisibleText("demotl");
		pages.Utill().find("ctl00_ContentPlaceHolder1_btnAssign").click();
		pages.Wait().wait_until_loader_is_invisible();
		String temp = pages.Utill().clickAlertbox();
		System.out.println(temp);
		
		return no;
		
	}
}
