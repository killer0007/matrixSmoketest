package verification;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import environment.Pages;
import environment.Utill;

public class VerificationInitiate extends Utill {
	Pages pages;
	public VerificationInitiate(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
		pages=new Pages(driver, logger);
	}
	public void InitiationMode(String mode) {
		String value=getValue("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input");
		
		if(!value.trim().equals(mode) && super.isEnabled("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input")) {
		click("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input");
		if (verifyddvalue(mode)) {
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_DropDown']/div/ul//li[text()='"
							+ mode + "']");
			waitUntilLoaderisInvisible(100);
		} else {
			throw new NotFoundException(mode);
		}
		}
	}
	public void InitiationMode() {
		String mode="Email (Preferred)";
		String value=getValue("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input");
		
		if(!value.trim().equals(mode) && super.isEnabled("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input")) {
		click("ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_Input");
		super.sleep(300);
		if (verifyddvalue(mode)) {
			pages.Utill()
					.click("//div[@id='ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_DropDown']/div/ul//li[text()='"
							+ mode + "']");
			waitUntilLoaderisInvisible(100);
		} else {
			throw new NotFoundException(mode);
		}
		}
	}
	public void Initiate() {
		click("ctl00_ContentPlaceHolder1_btnInitiate_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}
	public void EmpInitiate() {
		click("ctl00_ContentPlaceHolder1_btnInitiate_input");
		waitUntilLoaderisInvisible(100);
		super.waitUntilElementHasText("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_lbl_vr_recipentMailid", 10);
		this.RecipientMailID("gopinath.n@kadambatechnologies.com");
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}
	private boolean verifyddvalue(String mode){
		waitUntilElementHasText("//*[@id='ctl00_ContentPlaceHolder1_ddlBeforeVerificationInitiationMode_DropDown']/div/ul/li[1]", 10);
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
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='ID']");
		SwitchFramebyIndex(9);
	}
	public void creditcheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Credit']");
		SwitchFramebyIndex(6);
	}
	public void addresscheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Address']");
		SwitchFramebyIndex(0);
	}
	public void courtcheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Court']");
		SwitchFramebyIndex(7);
	}
	public void criminalcheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Criminal']");
		SwitchFramebyIndex(5);
	}
	public void databasecheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='DataBase']");
		SwitchFramebyIndex(4);
	}
	public void educationcheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Education']");
		SwitchFramebyIndex(1);
	}
	public void employmentcheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Employment']");
		SwitchFramebyIndex(2);;
	}
	public void referencecheck() {
		SwitchDefault();
		click("//*[@id='tabStrip']/div/ul/li//span[text()='Reference']");
		SwitchFramebyIndex(3);
	}
	public void RecipientMailID(String emailid) {
		sleep(500);
		clearElementText("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_txt_vr_recipentMailid");
		sendKeys("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_txt_vr_recipentMailid", emailid);
		clearElementText("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_txt_cc_mailid");
		sleep(800);
		click("ctl00_ContentPlaceHolder1_rw_VrInititiate_Preview_C_btnSendmailid_input");
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
			this.InitiationMode();
			this.EmpInitiate();
			break;
		case "Previous Employment":
			pages.Verification().Search(refno, "Employment", "Previous Employment");
			pages.Verification().Select(refno);
			this.employmentcheck();
			this.InitiationMode();
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
		case "Voter ID":
			pages.Verification().Search(refno, "ID", "Voter ID");
			pages.Verification().Select(refno);
			this.idcheck();
			this.InitiationMode(mode);
			this.Initiate();
			break;
		default:
			throw new NotFoundException(component);
		}
	}
}
