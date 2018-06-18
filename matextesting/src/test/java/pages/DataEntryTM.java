package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import userLogin.Pages;

public class DataEntryTM {
	WebDriver driver;
	protected final Pages pages;

	public DataEntryTM(WebDriver driver) {
		this.driver = driver;
		pages = new Pages(driver);
	}

	public String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
		// src\test\resources\property
		return pr.getProperty(key);
	}

	public String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		// src\test\resources\property
		return pr.getProperty(key);
	}

	private void Scroll(String id) {
		// pages=new Pages(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", pages.Utill().find(id));
	}

	private String getnextMonth() {
		Calendar cal = GregorianCalendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

		Date currentMonth = new Date();
		cal.setTime(currentMonth);

		// current month
		// String currentMonthAsSting = df.format(cal.getTime());

		// Add next month
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		String nextMonthAsString = df.format(cal.getTime());
		// System.out.println(nextMonthAsString);
		return nextMonthAsString;

	}

	public String Personal() throws IOException, InterruptedException {
		// pages=new Pages(driver);
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("gender"))));
		Select sel = new Select(pages.Utill().find(getlocator("gender")));
		sel.selectByVisibleText(getvalue("gender"));
		pages.Utill().find(getlocator("no_gap")).sendKeys("1");
		pages.Utill().find(getlocator("emp_id")).sendKeys(getvalue("emp_id"));
		Scroll(getlocator("Aadhar_card"));
		sel = new Select(pages.Utill().find(getlocator("Aadhar_card")));
		sel.selectByVisibleText(getvalue("Aadhar_card"));
		sel = new Select(pages.Utill().find(getlocator("pass_port")));
		sel.selectByVisibleText(getvalue("pass_port"));
		sel = new Select(pages.Utill().find(getlocator("pan_card")));
		sel.selectByVisibleText(getvalue("pan_card"));
		sel = new Select(pages.Utill().find(getlocator("drug")));
		sel.selectByVisibleText(getvalue("drug"));
		if (getvalue("drug").equals("Yes")) {
			sel = new Select(pages.Utill().find(getlocator("drug_panel")));
			sel.selectByVisibleText(getvalue("drug_panel"));
		}
		sel = new Select(pages.Utill().find(getlocator("db")));
		sel.selectByVisibleText(getvalue("db"));
		sel = new Select(pages.Utill().find(getlocator("driving_license")));
		sel.selectByVisibleText(getvalue("driving_license"));
		sel = new Select(pages.Utill().find(getlocator("voter_id")));
		sel.selectByVisibleText(getvalue("voter_id"));
		sel = new Select(pages.Utill().find(getlocator("medical")));
		sel.selectByVisibleText(getvalue("medical"));
		sel = new Select(pages.Utill().find(getlocator("righttocheck")));
		sel.selectByVisibleText(getvalue("righttocheck"));
		if (getvalue("righttocheck").equals("Yes")) {
			sel = new Select(pages.Utill().find(getlocator("right_check_type")));
			sel.selectByVisibleText(getvalue("right_check_type"));
		}
		pages.Utill().find(getlocator("per_save")).click();
		pages.Wait().wait_until_loader_is_invisible();
		String ss = pages.Utill().clickAlertbox();
		System.out.println("personal  " + ss);
		return ss;
	}

	public void AddressCheck() throws IOException, InterruptedException {
		if (getvalue("address").equalsIgnoreCase("yes")) {
			// pages=new Pages(driver);
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("address"))));
			pages.Utill().find(getlocator("address")).click();
			Select sel = new Select(pages.Utill().find(getlocator("add_type")));
			sel.selectByVisibleText(getvalue("add_type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().find(getlocator("add_des")).sendKeys(getvalue("add_des"));
			pages.Utill().find(getlocator("pincode")).sendKeys(getvalue("pincode"));
			w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("pincode") + "']")));
			// pages.Wait().waitForVisibilityOfData("Aut4_completionListElem",10);
			pages.Utill().find("//*[text()='" + getvalue("pincode") + "']").click();
			pages.Wait().wait_until_loader_is_invisible();
			sel = new Select(pages.Utill().find(getlocator("Residence_type")));
			sel.selectByVisibleText(getvalue("Residence_type"));
			pages.Utill().find(getlocator("landmark")).sendKeys(getvalue("landmark"));
			pages.Utill().find(getlocator("add_fdate")).sendKeys(getvalue("add_fdate"));
			if (getvalue("add_tilldate").equalsIgnoreCase("yes")) {
				pages.Utill().find(getlocator("add_tdate")).click();
				pages.Utill().find(getlocator("add_tilldate")).click();
			} else {
				pages.Utill().find(getlocator("add_tdate")).sendKeys(getvalue("add_tdate"));
			}

			pages.Utill().find(getlocator("period_of_stay")).sendKeys(getvalue("period_of_stay"));
			pages.Utill().find(getlocator("police_station")).sendKeys(getvalue("police_station"));
			pages.Utill().find(getlocator("add_mobile")).sendKeys(getvalue("add_mobile"));
			sel = new Select(pages.Utill().find(getlocator("location_type")));
			sel.selectByVisibleText(getvalue("location_type"));
			pages.Wait().wait_until_loader_is_invisible();
			sel = new Select(pages.Utill().find(getlocator("add_applicable")));
			sel.selectByVisibleText(getvalue("add_applicable"));
			pages.Wait().wait_until_loader_is_invisible();
			sel = new Select(pages.Utill().find(getlocator("add_court")));
			sel.selectByVisibleText(getvalue("add_court"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().find(getlocator("add_save")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("Address  " + ss);
		} else {
			System.out.println("address skipped");
		}

	}

	public void EducationCheck() throws IOException, InterruptedException {

		if (getvalue("education").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("education"))));
			pages.Utill().find(getlocator("education")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("edu_type"))));
			Select sel = new Select(pages.Utill().find(getlocator("edu_type")));
			sel.selectByVisibleText(getvalue("edu_type"));
			pages.Utill().find(getlocator("edu_name")).sendKeys(getvalue("edu_name"));
			w.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("edu_name") + "']")));
			// pages.Wait().waitForVisibilityOfData("Aut4_completionListElem",10);
			pages.Utill().find("//*[text()='" + getvalue("edu_name") + "']").click();
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().find(getlocator("Edu_StudentID")).sendKeys(getvalue("Edu_StudentID"));
			pages.Utill().find(getlocator("Edu_StartDt")).sendKeys(getvalue("Edu_StartDt"));
			pages.Utill().find(getlocator("Edu_EndDt")).sendKeys(getvalue("Edu_EndDt"));
			pages.Utill().find(getlocator("Edu_PassYear")).sendKeys(getvalue("Edu_PassYear"));
			sel = new Select(pages.Utill().find(getlocator("Edu_DegreeType")));
			sel.selectByVisibleText(getvalue("Edu_DegreeType"));
			pages.Utill().find(getlocator("Edu_NameofDegree")).sendKeys(getvalue("Edu_NameofDegree"));
			pages.Utill().find(getlocator("Edu_MajorSubject")).sendKeys(getvalue("Edu_MajorSubject"));
			sel = new Select(pages.Utill().find(getlocator("Edu_Graduated")));
			sel.selectByVisibleText(getvalue("Edu_Graduated"));
			sel = new Select(pages.Utill().find(getlocator("Edu_ProgramType")));
			sel.selectByVisibleText(getvalue("Edu_ProgramType"));
			pages.Utill().find(getlocator("Edu_CGPA")).sendKeys(getvalue("Edu_CGPA"));
			pages.Utill().find(getlocator("Edu_ClassObtained")).sendKeys(getvalue("Edu_ClassObtained"));
			pages.Utill().find(getlocator("Edu_NotApplicable")).click();
			pages.Utill().find(getlocator("Education1_save")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("education " + ss);

		} else {
			System.out.println("education skipped");
		}
	}

	public void EmploymentCheck() throws IOException, InterruptedException {
		if (getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("employment"))));
			pages.Utill().find(getlocator("employment")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("emp_Type"))));
			Select sel = new Select(pages.Utill().find(getlocator("emp_Type")));
			sel.selectByVisibleText(getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			if (getvalue("emp_SameasClient").equalsIgnoreCase("yes")) {
				pages.Utill().find(getlocator("emp_SameasClient")).click();
				pages.Wait().wait_until_loader_is_invisible();
				pages.Utill().find(getlocator("Emp_save")).click();
				pages.Wait().wait_until_loader_is_invisible();
				String ss = pages.Utill().clickAlertbox();
				System.out.println("education " + ss);

			} else {
				pages.Utill().find(getlocator("Emp_CmpName")).sendKeys(getvalue("Emp_CmpName"));

				w.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("Emp_CmpName") + "']")));
				pages.Utill().find("//*[text()='" + getvalue("Emp_CmpName") + "']").click();
				pages.Wait().wait_until_loader_is_invisible();

				pages.Utill().find(getlocator("Emp_CmpAddr")).sendKeys(getvalue("Emp_CmpAddr"));
				pages.Utill().find(getlocator("Emp_Position")).sendKeys(getvalue("Emp_Position"));
				pages.Utill().find(getlocator("Emp_Department")).sendKeys(getvalue("Emp_Department"));
				pages.Utill().find(getlocator("Emp_HOAddr")).sendKeys(getvalue("Emp_HOAddr"));
				pages.Utill().find(getlocator("Emp_FromDt")).sendKeys(getvalue("Emp_FromDt"));
				if (getvalue("Emp_Tilldate").equalsIgnoreCase("YES")) {
					pages.Utill().find(getlocator("Emp_ToDt")).click();
					pages.Utill().find(getlocator("Emp_Tilldate")).click();
					pages.Utill().find(getlocator("Emp_ReliveDt")).sendKeys(getnextMonth());
				} else {
					pages.Utill().find(getlocator("Emp_ToDt")).sendKeys(getvalue("Emp_ToDt"));
				}
				pages.Utill().find(getlocator("Emp_EmpCode")).sendKeys(getvalue("Emp_EmpCode"));
				sel = new Select(pages.Utill().find(getlocator("Emp_EmplType")));
				sel.selectByVisibleText(getvalue("Emp_EmplType"));
				if (getvalue("Emp_EmplType").equals("Contract")) {
					pages.Utill().find(getlocator("Emp_AgencyDetails")).sendKeys(getvalue("Emp_AgencyDetails"));
				}

				sel = new Select(pages.Utill().find(getlocator("Emp_CurrencyType")));
				sel.selectByVisibleText(getvalue("Emp_CurrencyType"));

				pages.Utill().find(getlocator("Emp_LastSalary")).sendKeys(getvalue("Emp_LastSalary"));
				sel = new Select(pages.Utill().find(getlocator("Emp_SalType")));
				sel.selectByVisibleText(getvalue("Emp_SalType"));

				pages.Utill().find(getlocator("Emp_RepAuthName")).sendKeys(getvalue("Emp_RepAuthName"));
				pages.Utill().find(getlocator("Emp_RepAuthDesig")).sendKeys(getvalue("Emp_RepAuthDesig"));

				pages.Utill().find(getlocator("Emp_RepAuthMobile1")).sendKeys(getvalue("Emp_RepAuthMobile1"));
				pages.Utill().find(getlocator("Emp_RepAuthEmail")).sendKeys(getvalue("Emp_RepAuthEmail"));
				pages.Utill().find(getlocator("Emp_HRName")).sendKeys(getvalue("Emp_HRName"));
				pages.Utill().find(getlocator("Emp_ReasonLeave")).sendKeys(getvalue("Emp_ReasonLeave"));
				sel = new Select(pages.Utill().find(getlocator("Emp_YTR")));
				sel.selectByVisibleText(getvalue("Emp_YTR"));
				pages.Utill().find(getlocator("Emp_Applicable")).click();
				pages.Utill().find(getlocator("Emp_save")).click();
				pages.Wait().wait_until_loader_is_invisible();
				String ss = pages.Utill().clickAlertbox();
				System.out.println("employment " + ss);
			}
		} else {
			System.out.println("employment skipped");
		}
	}

	public void ReeferenceCheck() throws IOException, InterruptedException {
		if (getvalue("ref").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Referene"))));
			pages.Utill().find(getlocator("Referene")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Ref_Type"))));
			Select sel = new Select(pages.Utill().find(getlocator("Ref_Type")));
			sel.selectByVisibleText(getvalue("Ref_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			try {
				sel = new Select(pages.Utill().find(getlocator("Ref_CheckType")));
				sel.selectByVisibleText(getvalue("Ref_CheckType"));
			} catch (Exception e) {

			}
			pages.Utill().find(getlocator("Ref_RepName")).sendKeys(getvalue("Ref_RepName"));
			pages.Utill().find(getlocator("Ref_RepDesig")).sendKeys(getvalue("Ref_RepDesig"));
			pages.Utill().find(getlocator("Ref_RepMobile1")).sendKeys(getvalue("Ref_RepMobile1"));
			pages.Utill().find(getlocator("Ref_RepEmail")).sendKeys(getvalue("Ref_RepEmail"));
			pages.Utill().find(getlocator("Ref_OrgName")).sendKeys(getvalue("Ref_OrgName"));
			pages.Utill().find(getlocator("Ref_OrgAddr")).sendKeys(getvalue("Ref_OrgAddr"));
			pages.Utill().find(getlocator("Ref_HRName")).sendKeys(getvalue("Ref_HRName"));
			pages.Utill().find(getlocator("Ref_HRContact")).sendKeys(getvalue("Ref_HRContact"));
			pages.Utill().find(getlocator("Ref_HRMobile")).sendKeys(getvalue("Ref_HRMobile"));
			pages.Utill().find(getlocator("Ref_HREmail")).sendKeys(getvalue("Ref_HREmail"));
			pages.Utill().find(getlocator("Ref_HRDesig")).sendKeys(getvalue("Ref_HRDesig"));
			sel = new Select(pages.Utill().find(getlocator("Ref_YTR")));
			sel.selectByVisibleText(getvalue("Ref_YTR"));
			pages.Utill().find(getlocator("Ref_Applicable")).click();
			pages.Utill().find(getlocator("Ref_save")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("reference " + ss);
		} else {
			System.out.println("reference skipped");
		}
	}

	public void GapCheck() throws IOException, InterruptedException {
		if (getvalue("Gap").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Gap"))));
			pages.Utill().find(getlocator("Gap")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Gap_CheckType"))));
			Select sel = new Select(pages.Utill().find(getlocator("Gap_CheckType")));
			sel.selectByVisibleText(getvalue("Gap_CheckType"));
			pages.Wait().wait_until_loader_is_invisible();
			sel = new Select(pages.Utill().find(getlocator("Gap_Type")));
			sel.selectByVisibleText(getvalue("Gap_Type"));
			pages.Utill().find(getlocator("Gap_FDate")).sendKeys(getvalue("Gap_FDate"));
			pages.Utill().find(getlocator("Gap_TDate")).sendKeys(getvalue("Gap_TDate"));
			pages.Utill().find(getlocator("Gap_Reason")).sendKeys(getvalue("Gap_Reason"));
			sel = new Select(pages.Utill().find(getlocator("Gap_Applicable")));
			sel.selectByVisibleText(getvalue("Gap_Applicable"));
			sel = new Select(pages.Utill().find(getlocator("Gap_AddType")));
			sel.selectByVisibleText(getvalue("Gap_AddType"));
			pages.Wait().wait_until_loader_is_invisible();

			pages.Utill().find(getlocator("Gap_Save")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("Gap " + ss);
		} else {
			System.out.println("gap check skipped");
		}
	}

	public void FacisCheck() throws IOException, InterruptedException {
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("facis").equalsIgnoreCase("yes")
				&& getvalue("address").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().find(getlocator("Others")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("facis"))));
			Select sel = new Select(pages.Utill().find(getlocator("facis")));
			sel.selectByVisibleText("Yes");
			// pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("facis"))));
			sel = new Select(pages.Utill().find(getlocator("FACIS_Type")));
			sel.selectByVisibleText(getvalue("FACIS_Type"));
			sel = new Select(pages.Utill().find(getlocator("FACIS_Level")));
			sel.selectByVisibleText(getvalue("FACIS_Level"));

			sel = new Select(pages.Utill().find(getlocator("FACIS_AddressType")));
			sel.selectByVisibleText(getvalue("add_type"));
			pages.Wait().wait_until_loader_is_invisible();

			pages.Utill().find(getlocator("FACIS_save")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("facis " + ss);
		} else {
			System.out.println("facis checks skipped check status");
		}
	}

	public void CreditCheck() throws IOException, InterruptedException {
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("credit").equalsIgnoreCase("yes")
				&& getvalue("address").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().find(getlocator("Others")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("credit"))));
			Select sel = new Select(pages.Utill().find(getlocator("credit")));
			sel.selectByVisibleText("Yes");
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("credit_AddressType"))));
			sel = new Select(pages.Utill().find(getlocator("credit_AddressType")));
			sel.selectByVisibleText(getvalue("add_type"));
			pages.Wait().wait_until_loader_is_invisible();
			sel = new Select(pages.Utill().find(getlocator("credit_Category")));
			sel.selectByVisibleText(getvalue("credit_Category"));
			sel = new Select(pages.Utill().find(getlocator("credit_ResidenceType")));
			sel.selectByVisibleText(getvalue("credit_ResidenceType"));
			sel = new Select(pages.Utill().find(getlocator("credit_SalaryType")));
			sel.selectByVisibleText(getvalue("credit_SalaryType"));
			sel = new Select(pages.Utill().find(getlocator("credit_IncomeType")));
			sel.selectByVisibleText(getvalue("credit_IncomeType"));
			pages.Utill().find(getlocator("credit_Salary")).sendKeys(getvalue("credit_Salary"));

			pages.Utill().find(getlocator("credit_save")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("credit " + ss);
		} else {
			System.out.println("credit checks skipped check status");
		}
	}

	public void BvCheck() throws IOException, InterruptedException {
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("bank").equalsIgnoreCase("yes")
				&& getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().find(getlocator("Others")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("bank"))));
			Select sel = new Select(pages.Utill().find(getlocator("bank")));
			sel.selectByVisibleText("Yes");
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("BVType"))));
			sel = new Select(pages.Utill().find(getlocator("BVType")));
			sel.selectByVisibleText(getvalue("BVType"));
			sel = new Select(pages.Utill().find(getlocator("BVEmpType")));
			sel.selectByVisibleText(getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().find(getlocator("BVCountry")).sendKeys(getvalue("BVCountry"));
			System.out.println("//*[text()='" + getvalue("BVCountry") + "']");
			w.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					".//*[@id='ctl00_ContentPlaceHolder1_TabContainer1_TabPanel6_CandidateOtherChecks1_aceBVCountry_completionListElem']/li[2]")));
			pages.Utill().find(
					".//*[@id='ctl00_ContentPlaceHolder1_TabContainer1_TabPanel6_CandidateOtherChecks1_aceBVCountry_completionListElem']/li[2]")
					.click();
			pages.Utill().find(getlocator("BVState")).sendKeys(getvalue("BVState"));
			w.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(".//*[@id='BVautoState_completionListElem']/li")));
			pages.Utill().find(".//*[@id='BVautoState_completionListElem']/li").click();

			pages.Utill().find(getlocator("BVCity")).sendKeys(getvalue("BVCity"));
			w.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(".//*[@id='BVautoCity_completionListElem']/li[2]")));
			pages.Utill().find(".//*[@id='BVautoCity_completionListElem']/li[2]").click();
			pages.Utill().find(getlocator("BVSave")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("Bank Verification " + ss);
		} else {
			System.out.println("bank checks skipped check status");
		}
	}

	public void ItCheck() throws IOException, InterruptedException {
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("it").equalsIgnoreCase("yes")
				&& getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().find(getlocator("Others")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("it"))));
			Select sel = new Select(pages.Utill().find(getlocator("it")));
			sel.selectByVisibleText("Yes");
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("ITType"))));
			sel = new Select(pages.Utill().find(getlocator("ITType")));
			sel.selectByVisibleText(getvalue("ITType"));

			sel = new Select(pages.Utill().find(getlocator("ITEmpType")));
			sel.selectByVisibleText(getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().find(getlocator("ITSave")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("IT Verification " + ss);
		} else {
			System.out.println("IT checks skipped check status");
		}
	}

	public void PfCheck() throws IOException, InterruptedException {
		if (getvalue("others").equalsIgnoreCase("yes") && getvalue("it").equalsIgnoreCase("yes")
				&& getvalue("emp").equalsIgnoreCase("yes")) {
			WebDriverWait w = new WebDriverWait(driver, 10);
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("Others"))));
			pages.Utill().find(getlocator("Others")).click();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("pf"))));
			Select sel = new Select(pages.Utill().find(getlocator("pf")));
			sel.selectByVisibleText("Yes");
			sel = new Select(pages.Utill().find(getlocator("PFType")));
			sel.selectByVisibleText(getvalue("PFType"));
			sel = new Select(pages.Utill().find(getlocator("PFEmpType")));
			sel.selectByVisibleText(getvalue("emp_Type"));
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().find(getlocator("PFNumber")).sendKeys(getvalue("PFNumber"));
			pages.Utill().find(getlocator("PFSave")).click();
			pages.Wait().wait_until_loader_is_invisible();
			String ss = pages.Utill().clickAlertbox();
			System.out.println("PF Verification " + ss);
		} else {
			System.out.println("PF checks skipped check status");
		}
	}
}
