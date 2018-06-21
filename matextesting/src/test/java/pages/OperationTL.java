package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

import testCases.Pages;

public class OperationTL {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	WebDriverWait w;

	public OperationTL(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		w = new WebDriverWait(driver, 100);
	}

	public String getlocator(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_locators.properties")));
		return pr.getProperty(key);
	}

	public String getvalue(String key) throws FileNotFoundException, IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(new File("./src\\test\\resources\\property\\dataentry_values.properties")));
		return pr.getProperty(key);
	}

	public void Employementtl(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Prior TL");

			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_emp_outstanding"))));
			pages.Utill().click_element(getlocator("op_emp_outstanding"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_emp_normal"))));
			pages.Utill().click_element(getlocator("op_emp_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_emp_table"))));
			int b = 0;
			String ss = "";
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//*[@id='" + getlocator("op_emp_table") + "']/tbody/tr/td[5]"));
			for (int i = 0; i < tablelist.size(); i++) {
				String ref = tablelist.get(i).getText();
				if (ref.equalsIgnoreCase(no)) {
					int k = i + 2;
					pages.Utill().click_element(
							"//*[@id='" + getlocator("op_emp_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
					pages.Utill().select_by_label(getlocator("op_emp_dd"), "demotl - Chennai");
					pages.Utill().click_element(getlocator("op_emp_ass"));
					pages.Wait().wait_until_loader_is_invisible();
					b++;
					ss = pages.Utill().clickAlertbox();
					break;
				} else {
					continue;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail("Employment check not assigned");
			} else {
				logger.pass("Employment " + ss);
			}
		} catch (Exception e) {

			logger.fail(e.toString());
		}

	}

	public void Referencetl(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Reference TL");

			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_ref_outstanding"))));
			pages.Utill().click_element(getlocator("op_ref_outstanding"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_ref_normal"))));
			pages.Utill().click_element(getlocator("op_ref_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_ref_table"))));
			int b = 0;
			String ss = "";
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//*[@id='" + getlocator("op_ref_table") + "']/tbody/tr/td[5]"));
			for (int i = 0; i < tablelist.size(); i++) {
				String ref = tablelist.get(i).getText();
				if (ref.equalsIgnoreCase(no)) {
					int k = i + 2;
					pages.Utill().click_element(
							"//*[@id='" + getlocator("op_ref_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
					pages.Utill().select_by_label(getlocator("op_ref_dd"), "demotl - Chennai");
					pages.Utill().click_element(getlocator("op_ref_ass"));
					pages.Wait().wait_until_loader_is_invisible();
					b++;
					ss = pages.Utill().clickAlertbox();
					break;
				} else {
					continue;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail("Reference check not assigned");
			} else {
				logger.pass("Reference " + ss);
			}
		} catch (Exception e) {

			logger.fail(e.toString());
		}

	}

	public void Criminaltl(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Criminal TL");

			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_criminal_outstanding"))));
			pages.Utill().click_element(getlocator("op_criminal_outstanding"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_criminal_normal"))));
			pages.Utill().click_element(getlocator("op_criminal_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_criminal_table"))));
			int b = 0;
			String ss = "";
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//*[@id='" + getlocator("op_criminal_table") + "']/tbody/tr/td[6]"));
			for (int i = 0; i < tablelist.size(); i++) {
				String ref = tablelist.get(i).getText();
				if (ref.equalsIgnoreCase(no)) {
					int k = i + 2;
					pages.Utill().click_element(
							"//*[@id='" + getlocator("op_criminal_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
					pages.Utill().select_by_label(getlocator("op_criminal_dd"), "demotl - Chennai");
					pages.Utill().click_element(getlocator("op_criminal_ass"));
					pages.Wait().wait_until_loader_is_invisible();
					b++;
					ss = pages.Utill().clickAlertbox();
					break;
				} else {
					continue;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail("Criminal check not assigned");
			} else {
				logger.pass("Criminal " + ss);
			}
		} catch (Exception e) {

			logger.fail(e.toString());
		}

	}

	public void Dbtl(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "DB Check TL");

			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_db_outstanding"))));
			pages.Utill().click_element(getlocator("op_db_outstanding"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_db_normal"))));
			pages.Utill().click_element(getlocator("op_db_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_db_table"))));
			int b = 0;
			String ss = "";
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//*[@id='" + getlocator("op_db_table") + "']/tbody/tr/td[4]"));
			for (int i = 0; i < tablelist.size(); i++) {
				String ref = tablelist.get(i).getText();
				if (ref.equalsIgnoreCase(no)) {
					int k = i + 2;
					pages.Utill().click_element(
							"//*[@id='" + getlocator("op_db_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
					pages.Utill().select_by_label(getlocator("op_db_dd"), "demotl - Chennai");
					pages.Utill().click_element(getlocator("op_db_ass"));
					pages.Wait().wait_until_loader_is_invisible();
					b++;
					ss = pages.Utill().clickAlertbox();
					break;
				} else {
					continue;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail("DB check not assigned");
			} else {
				logger.pass("DB " + ss);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.fail(e.toString());
		}

	}

	public void Drugtl(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Drug Check TL");

			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_drug_outstanding"))));
			pages.Utill().click_element(getlocator("op_drug_outstanding"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_drug_normal"))));
			pages.Utill().click_element(getlocator("op_drug_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_drug_table"))));
			int b = 0;
			String ss = "";
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//*[@id='" + getlocator("op_drug_table") + "']/tbody/tr/td[4]"));
			for (int i = 0; i < tablelist.size(); i++) {
				String ref = tablelist.get(i).getText();
				if (ref.equalsIgnoreCase(no)) {
					int k = i + 2;
					pages.Utill().click_element(
							"//*[@id='" + getlocator("op_drug_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
					pages.Utill().select_by_label(getlocator("op_drug_dd"), "demotl - Chennai");
					pages.Utill().click_element(getlocator("op_drug_ass"));
					pages.Wait().wait_until_loader_is_invisible();
					b++;
					ss = pages.Utill().clickAlertbox();
					break;
				} else {
					continue;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail("Drug check not assigned");
			} else {
				logger.pass("Drug " + ss);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.fail(e.toString());
		}

	}

	public void Idtl(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "IDCheck TL");

			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_id_outstanding"))));
			pages.Utill().click_element(getlocator("op_id_outstanding"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_id_normal"))));
			pages.Utill().click_element(getlocator("op_id_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_id_table"))));
			int b = 0;
			String ss = "";
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//*[@id='" + getlocator("op_id_table") + "']/tbody/tr/td[4]"));
			for (int i = 0; i < tablelist.size(); i++) {
				String ref = tablelist.get(i).getText();
				if (ref.equalsIgnoreCase(no)) {
					int k = i + 2;
					pages.Utill().click_element(
							"//*[@id='" + getlocator("op_id_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
					pages.Utill().select_by_label(getlocator("op_id_dd"), "demotl - Chennai");
					pages.Utill().click_element(getlocator("op_id_ass"));
					pages.Wait().wait_until_loader_is_invisible();
					b++;
					ss = pages.Utill().clickAlertbox();
					break;
				} else {
					continue;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail("ID check not assigned");
			} else {
				logger.pass("ID " + ss);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.fail(e.toString());
		}

	}

	public void Courttl(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "CourtTL");

			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_court_outstanding"))));
			pages.Utill().click_element(getlocator("op_court_outstanding"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_court_normal"))));
			pages.Utill().click_element(getlocator("op_court_normal"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("op_court_table"))));
			int b = 0;
			String ss = "";
			List<WebElement> tablelist = driver
					.findElements(By.xpath("//*[@id='" + getlocator("op_court_table") + "']/tbody/tr/td[4]"));
			for (int i = 0; i < tablelist.size(); i++) {
				String ref = tablelist.get(i).getText();
				if (ref.equalsIgnoreCase(no)) {
					int k = i + 2;
					pages.Utill().click_element(
							"//*[@id='" + getlocator("op_court_table") + "']/tbody/tr[" + k + "]/td[1]/input[1]");
					pages.Utill().select_by_label(getlocator("op_court_dd"), "demotl - Chennai");
					pages.Utill().click_element(getlocator("op_court_ass"));
					pages.Wait().wait_until_loader_is_invisible();
					b++;
					ss = pages.Utill().clickAlertbox();
					break;
				} else {
					continue;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail("Court check not assigned");
			} else {
				logger.pass("Court " + ss);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.fail(e.toString());
		}

	}

	public void Facistl(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "FACIS TL");
		otherscheck("Facis", no);
	}

	public void Credittl(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "Credit TL");
		otherscheck("Credit", no);
	}

	public void BVtl(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "BV TL");
		otherscheck("BV", no);
	}

	public void PFtl(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "PF TL");
		otherscheck("PF", no);
	}

	public void ITtl(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "IT TL");
		otherscheck("IT", no);
	}

	public void otherscheck(String checkname, String no) throws Exception {
		try {
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnOutstanding");
			pages.Wait().wait_until_loader_is_invisible();
			pages.Utill().click_element("ctl00_ContentPlaceHolder1_lbtnOutstanding_OS");
			pages.Wait().wait_until_loader_is_invisible();
			int i = getindex(driver, ".//*[@id='ctl00_ContentPlaceHolder1_grdUnassigned']/tbody/tr[1]/th");
			System.out.println("index is  " + i);
			List<WebElement> list = driver.findElements(
					By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_grdUnassigned']/tbody/tr/td[" + i + "]"));
			System.out.println(list.size());
			int b = 0;
			String ss = "";
			for (int j = 0; j < list.size(); j++) {
				String m = list.get(j).getText();
				if (m.equals(no)) {
					int s = j + 2;
					driver.findElement(By.xpath(
							".//*[@id='ctl00_ContentPlaceHolder1_grdUnassigned']/tbody/tr[" + s + "]/td/input[1]"))
							.click();
					pages.Utill().select_by_label("ctl00_ContentPlaceHolder1_ddlAssignResident", "demotl - Chennai");
					pages.Utill().click_element("ctl00_ContentPlaceHolder1_imgAssign");
					pages.Wait().wait_until_loader_is_invisible();
					ss = pages.Utill().clickAlertbox();
					b++;
					break;
				}
			}
			if (b == 0 || (ss.equals(""))) {
				logger.fail(checkname + " check not assigned");
			} else {
				logger.pass(checkname + ss);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.fail(checkname+e.toString());
		}
	}

	public int getindex(WebDriver driver, String path) {
		int i = 0;
		List<WebElement> list = driver.findElements(By.xpath(path));
		for (i = 0; i < list.size(); i++) {

			String t = list.get(i).getText();
			System.out.println(t);
			if (t.equals("Matrix Ref")) {
				break;
			}
		}
		return i + 1;
	}

}
