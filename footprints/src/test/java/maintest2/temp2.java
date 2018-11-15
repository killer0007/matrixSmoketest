package maintest2;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import dashboard.DataEntrySupervision;
import dataEntry.Criminal;
import environment.BaseClass;
import environment.Pages;
import verification.VerificationInitiate;

public class temp2{
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentTest logger;
	ExtentReports extent;
	Pages pages;
	Properties config;
	protected String contractName = null;
	protected String clientName = null;
	protected String projectName = null;
	protected String candidateName = null;
	protected String candidateId = null;
	protected String lastName = null;
	protected String refno = null;
	protected String uname = null;
	public static void main(String[] args) throws Exception {
		temp2 t = new temp2();
//		t.criminalDocument();
		System.out.println(t.formatedob("1996-09-24"));
	}

	public void criminalDocument() throws Exception {
		reporter = new ExtentHtmlReporter("./Reports/matrixflow.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		driver = new BaseClass().getDriver();
		config = BaseClass.getlocator();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(config.getProperty("url"));
		contractName = config.getProperty("contractname");
		clientName = config.getProperty("clientname");
		projectName = config.getProperty("projectname");
		logger = extent.createTest("criminal");
		logger.assignAuthor("Gopinath");
		pages = new Pages(driver,logger);
		uname = config.getProperty("uname");
		pages.Login().userLogin(config.getProperty("uname"), config.getProperty("pass"));
		//case registration
		pages.Home().clickRegister();
		candidateName = pages.Utill().candidateName();
		candidateId = Integer.toString(pages.Utill().getcandidateid());
		lastName = pages.Utill().lastName();
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("CandidateName", candidateName);
		datas.put("CandidateId", candidateId);
		datas.put("ClientName", clientName);
		datas.put("ProjectName", projectName);
		datas.put("lastname", lastName);
		pages.CaseRegistration().registercase(datas, false);
		String[] components = {"Permanent Criminal Check","Current Address Criminal Check"};
		for (int i = 0; i < components.length; i++) {
			pages.CaseRegistration().selectcheck(components[i].toString());
		}
		pages.CaseRegistration().submit();
		pages.Utill().confirmAlert();
		pages.Home().homepage();
		//data entry assign
		refno = pages.DbConnection().getLastrefno(projectName);
		System.out.println(refno);
		DataEntrySupervision des = pages.DataEntrySupervision();
		des.datanentrysupervision();
		//des.assigngetnext(refno);
		des.assign(refno, "demoempl");
		
//		data entry
		pages.DataEntry().datanentry();
		pages.Utill().click("//*[text()='" + refno + "']");
		pages.Utill().waitUntilLoaderisInvisible(100);
		HashMap<String, String> casedetails = pages.DbConnection().getLastCase(projectName);
		logger.log(Status.INFO, casedetails.toString());
		assertEquals(casedetails.get("firstname"), pages.CaseInformation().FirstName());
		assertEquals(casedetails.get("lastname"), pages.CaseInformation().LastName());
		this.currentAddress();
		this.PermanentAddress();
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.DataEntryQCSupervision().datanentryqcsupervision();
		pages.DataEntryQCSupervision().assign(refno, "demoempl");
		pages.DataEntryQC().datanentryqc();
		pages.DataEntryQC().selectcase(refno);
		dataEntryQC.Criminal cri= new dataEntryQC.Criminal(driver, logger);
		cri.criminalcheck();
		cri.Component("Current Address Criminal Check");
		cri.submit();
		cri.criminalcheck();
		cri.Component("Permanent Criminal Check");
		cri.submit();
		pages.Utill().waitUntilLoaderisInvisible(100);
		pages.VerificationSupervisor().verificationsupervisor();
		pages.VerificationSupervisor().assign(refno, "demoempl");
		VerificationInitiate ver= new VerificationInitiate(driver,logger);
		pages.Verification().verification();
		ver.Initiate(refno, "Current Address Criminal Check", "In Person");
		ver.Initiate(refno, "Permanent Criminal Check", "In Person");
		verification.Criminal criminal = new verification.Criminal(driver,logger);
		pages.Verification().verification();
		pages.Verification().CurrentAddressCriminalCheck(refno);
		criminal.Verification();
		pages.Verification().PermanentCriminalCheck(refno);
		criminal.Verification();
		
		
	}
	public void currentAddress() throws Exception{
		Criminal criminal = new Criminal(driver, logger);
		criminal.criminalcheck();
		criminal.Component("Current Address Criminal Check");
		criminal.AddressLine1("current address criminal");
		criminal.State();
		criminal.City();
		criminal.submit();
	}
public void PermanentAddress() throws Exception{
	Criminal criminal = new Criminal(driver, logger);
	criminal.criminalcheck();
	criminal.Component("Permanent Criminal Check");
	criminal.AddressLine1("Permannet address criminal");
	criminal.State();
	criminal.City();
	criminal.submit();
	}
public String formatedob(String date) {
	// String date ="2007-04-29";
	// yyyy-mm-dd
	return date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);

}
}
