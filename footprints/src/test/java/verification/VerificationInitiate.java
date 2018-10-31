package verification;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import environment.BaseClass;
import environment.Pages;

public class VerificationInitiate {
	WebDriver driver;
	ExtentTest logger;
	Pages pages;

	public VerificationInitiate(ExtentTest logger) {
		driver = BaseClass.getWebDriver();
		this.logger = logger;
		pages = new Pages(logger);
	}
	public void InitiationMode(String mode) {
		String value=pages.Utill().getValue("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input");
		if(!value.trim().equals(mode)) {
		pages.Utill().click("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input");
		if (verifyddvalue(mode)) {
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_DropDown']/div/ul//li[text()='"
							+ mode + "']");
			pages.Utill().waitUntilLoaderisInvisible(100);
		} else {
			throw new NotFoundException(mode);
		}
		}
	}
	public void Initiate() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnInitiate_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	public void EmpInitiate() {
		pages.Utill().click("ctl00_ContentPlaceHolder1_btnInitiate_input");
		pages.Utill().waitUntilLoaderisInvisible(100);
		this.RecipientMailID("gopinath.n@kadambatechnologies.com");
		pages.Utill().SwitchDefault();
		pages.Utill().confirmAlert();
		pages.Utill().waitUntilLoaderisInvisible(100);
	}
	private boolean verifyddvalue(String mode){
		pages.Utill().waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_DropDown']/div/ul/li[1]", 10);
		List<WebElement> list = driver
				.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_DropDown']/div/ul/li"));
		if (list.size() > 0) {
			boolean re = false;
			for (int i = 0; i < list.size(); i++) {
				String t = list.get(i).getText();
				if (t.equals(mode)) {
					re = true;
					break;
				} 
			}
			return re;
		} else {
			return false;
		}
	}
	public void idcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='ID']");
		pages.Utill().SwitchFramebyIndex(9);
	}
	public void creditcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Credit']");
		pages.Utill().SwitchFramebyIndex(6);
	}
	public void addresscheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Address']");
		pages.Utill().SwitchFramebyIndex(0);
	}
	public void courtcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Court']");
		pages.Utill().SwitchFramebyIndex(7);
	}
	public void criminalcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Criminal']");
		pages.Utill().SwitchFramebyIndex(5);
	}
	public void databasecheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='DataBase']");
		pages.Utill().SwitchFramebyIndex(4);
	}
	public void educationcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Education']");
		pages.Utill().SwitchFramebyIndex(1);
	}
	public void employmentcheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Employment']");
		pages.Utill().SwitchFramebyIndex(2);;
	}
	public void referencecheck() {
		pages.Utill().SwitchDefault();
		pages.Utill().click("//*[@id='tabStrip']/div/ul/li//span[text()='Reference']");
		pages.Utill().SwitchFramebyIndex(3);
	}
	public void RecipientMailID(String emailid) {
		pages.Utill().sleep(500);
		pages.Utill().clearElementText("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_txt_vr_recipentMailid");
		pages.Utill().sendKeys("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_txt_vr_recipentMailid", emailid);
		pages.Utill().clearElementText("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_txt_cc_mailid");
		pages.Utill().sleep(800);
		pages.Utill().click("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_btnSendmailid_input");
	}
public void Initiate(String refno, String component, String mode) {
		System.out.println("Verification Initiation : "+component);
		switch (component) {
		case "Current Address":
			pages.Verification().Search(refno, "Address", "Current Address");
			pages.Verification().Select(refno);
			this.addresscheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Permanent":
			pages.Verification().Search(refno, "Address", "Permanent");
			pages.Verification().Select(refno);
			this.addresscheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "12th":
			pages.Verification().Search(refno, "Education", "12th");
			pages.Verification().Select(refno);
			this.educationcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "UG1":
			pages.Verification().Search(refno, "Education", "UG1");
			pages.Verification().Select(refno);
			this.educationcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Current/Latest Employment":
			pages.Verification().Search(refno, "Employment", "Current/Latest Employment");
			pages.Verification().Select(refno);
			this.employmentcheck();
			this.InitiationMode(mode);
			this.EmpInitiate();
			break;
		case "Previous Employment":
			pages.Verification().Search(refno, "Employment", "Previous Employment");
			pages.Verification().Select(refno);
			this.employmentcheck();
			this.InitiationMode(mode);
			this.EmpInitiate();
			break;
		case "Reference 1":
			pages.Verification().Search(refno, "Reference", "Reference 1");
			pages.Verification().Select(refno);
			this.referencecheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Current Address Criminal Check":
			pages.Verification().Search(refno, "Criminal", "Current Address Criminal Check");
			pages.Verification().Select(refno);
			this.criminalcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Permanent Criminal Check":
			pages.Verification().Search(refno, "Criminal", "Permanent Criminal Check");
			pages.Verification().Select(refno);
			this.criminalcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Current Address Court Check":
			pages.Verification().Search(refno, "Court", "Current Address Court Check");
			pages.Verification().Select(refno);
			this.courtcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Permanent Court Check":
			pages.Verification().Search(refno, "Court", "Permanent Court Check");
			pages.Verification().Select(refno);
			this.courtcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Credit Check 1":
			pages.Verification().Search(refno, "Credit", "Credit Check 1");
			pages.Verification().Select(refno);
			this.creditcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Passport":
			pages.Verification().Search(refno, "ID", "Passport");
			pages.Verification().Select(refno);
			this.idcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		case "Aadhaar Card":
			pages.Verification().Search(refno, "ID", "Aadhaar Card");
			pages.Verification().Select(refno);
			this.idcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		default:
			break;
		}
	}
}
