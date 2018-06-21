package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import environment.Utill;
import testCases.Pages;

public class OperationTM {
	protected final WebDriver driver;
	protected final Pages pages;
	ExtentTest logger;
	WebDriverWait w;

	public OperationTM(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		pages = new Pages(driver, logger);
		w = new WebDriverWait(driver, 20);
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

	public void Address(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "Residence TM");
		driver.findElement(By.linkText(no)).click();
		pages.Wait().wait_until_loader_is_invisible();

	}

	public void Education(String no) throws Exception {
		try {
			pages.CaseRegistration().navigateTo("Dashboard", "Academic TM");
			String x = pages.Utill().get_text(getlocator("v_all_out"));
			if (!(x.equals("0"))) {
				pages.Utill().click_element(getlocator("v_all_out"));
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_normal"))));
				String y = pages.Utill().get_text(getlocator("v_normal"));
				if (!(y.equals("0"))) {
					pages.Utill().click_element(getlocator("v_normal"));
					pages.Wait().wait_until_loader_is_invisible();
					w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_table"))));
					int index = 0;
					String ss = "";
					List<WebElement> table_list = driver
							.findElements(By.xpath("//*[@id='" + getlocator("v_table") + "']/tbody/tr/td[8]/a"));
					for (int i = 0; i < table_list.size(); i++) {
						String n = table_list.get(i).getText();
						if (n.equals(no)) {
							index = i + 2;
							pages.Utill().click_element(
									"//*[@id='" + getlocator("v_table") + "']/tbody/tr[" + index + "]/td[5]/input");
							pages.Wait().wait_until_loader_is_invisible();
							w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_table"))));
							pages.Utill().select_by_label(getlocator("v_doctype"), "Email ID Proof");
							String file = System.getProperty("user.dir") + "\\Documents\\edu_one.pdf";
							pages.Utill().choose_file(getlocator("v_upload"), file);

							ss = pages.Utill().clickAlertbox();
							System.out.println(ss);
							pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnclose2");
							pages.Utill().click_element(
									"//*[@id='" + getlocator("v_table") + "']/tbody/tr[" + index + "]/td[1]/input[8]");
							pages.Utill().click_element(getlocator("v_edu_vr"));
							pages.Wait().wait_until_loader_is_invisible();

							w.until(ExpectedConditions
									.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_btnInitiateVR")));
							pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnInitiateVR");
							pages.Wait().wait_until_loader_is_invisible();
							ss = pages.Utill().clickAlertbox();
							System.out.println(ss);

							break;
						}
					}
					if ((index == 0)) {
						System.out.println("education vr initiation failed");
						logger.fail("education vr initiation failed");
					} else {
						logger.pass("education vr initiation completed");
						x = "";
						x = pages.Utill().get_text(getlocator("v_updation_out"));
						if (!(x.equals("0"))) {
							pages.Utill().click_element(getlocator("v_updation_out"));
							pages.Wait().wait_until_loader_is_invisible();
							y = "";
							y = pages.Utill().get_text(getlocator("vedu_upda_normal"));
							if (!(y.equals("0"))) {
								pages.Utill().click_element(getlocator("vedu_upda_normal"));
								pages.Wait().wait_until_loader_is_invisible();
								// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[8]
								// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[2]/input
								w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vedu_upda_table"))));
								index = 0;
								ss = "";
								table_list = driver.findElements(
										By.xpath("//*[@id='" + getlocator("vedu_upda_table") + "']/tbody/tr/td[8]"));
								for (int i = 0; i < table_list.size(); i++) {
									String n = table_list.get(i).getText();
									if (n.equals(no)) {
										index = i + 2;
										pages.Utill().click_element("//*[@id='" + getlocator("vedu_upda_table")
												+ "']/tbody/tr[" + index + "]/td[2]/input");
										WebDriverWait wait = new WebDriverWait(driver, 100);
										wait.until(ExpectedConditions
												.presenceOfElementLocated(By.id(getlocator("vEdu_EducationType"))));

										pages.Utill().input_text((getlocator("vEdu_Name")), (getvalue("vEdu_Name")));
										w.until(ExpectedConditions.presenceOfElementLocated(
												By.xpath("//*[text()='" + getvalue("vEdu_Name") + "']")));
										pages.Utill().click_element("//*[text()='" + getvalue("vEdu_Name") + "']");
										pages.Wait().wait_until_loader_is_invisible();
										pages.Utill().input_text(getlocator("vEdu_Address"), getvalue("vEdu_Address"));
										pages.Utill().input_text(getlocator("vEdu_StudentID"), getvalue("vEdu_StudentID"));
										pages.Utill().input_text(getlocator("vEdu_Gap"), getvalue("vEdu_Gap"));
										pages.Utill().input_text(getlocator("vEdu_StartDt"), getvalue("vEdu_StartDt"));
										pages.Utill().input_text(getlocator("vEdu_EndDt"), getvalue("vEdu_EndDt"));
										pages.Utill().input_text(getlocator("vEdu_PassYear"), getvalue("vEdu_PassYear"));
										pages.Utill().select_by_label(getlocator("vEdu_DegreeType"),
												getvalue("vEdu_DegreeType"));
										pages.Utill().input_text(getlocator("vEdu_NameofDegree"),
												getvalue("vEdu_NameofDegree"));
										pages.Utill().input_text(getlocator("vEdu_MajorSubject"),
												getvalue("vEdu_MajorSubject"));
										pages.Utill().select_by_label(getlocator("vEdu_Graduated"),
												getvalue("vEdu_Graduated"));
										pages.Utill().select_by_label(getlocator("vEdu_ProgramType"),
												getvalue("vEdu_ProgramType"));
										pages.Utill().input_text(getlocator("vEdu_CGPA"), getvalue("vEdu_CGPA"));
										pages.Utill().input_text(getlocator("vEdu_ClassObtained"),
												getvalue("vEdu_ClassObtained"));
										pages.Utill().input_text(getlocator("vEdu_VerifierName"),
												getvalue("vEdu_VerifierName"));
										pages.Utill().input_text(getlocator("vEdu_VerifierDesignation"),
												getvalue("vEdu_VerifierDesignation"));
										pages.Utill().input_text(getlocator("vEdu_VerifierContact"),
												getvalue("vEdu_VerifierContact"));
										pages.Utill().input_text(getlocator("vEdu_VerifierEmail"),
												getvalue("vEdu_VerifierEmail"));
										pages.Utill().select_by_label(getlocator("vEdu_Confirmfrom"),
												getvalue("vEdu_Confirmfrom"));
										pages.Utill().select_by_label(getlocator("vEdu_Typeofrevert"),
												getvalue("vEdu_Typeofrevert"));
										pages.Utill().select_by_label(getlocator("vEdu_Mode"), getvalue("vEdu_Mode"));
										pages.Utill().input_text(getlocator("vEdu_VerComments"),
												getvalue("vEdu_VerComments"));
										pages.Utill().click_element(getlocator("vedu_date"));
										w.until(ExpectedConditions
												.presenceOfElementLocated(By.id(getlocator("vedu_today"))));
										pages.Utill().click_element(getlocator("vedu_today"));
										pages.Utill().click_element(getlocator("vedu_green"));

										pages.Utill().handle_Alert();
										driver.switchTo().defaultContent();
										break;
									}
								}
							}
						} else {
							logger.fail("no value in education updation dashboard");
							System.out.println("no value in updation allocation dashboard");
						}
					}
				} else {
					logger.fail("no value in education allocation dashboard");
					System.out.println("no value in education allocation dashboard");
				}
			} else {
				logger.fail("no value in education allocation dashboard");
				System.out.println("no value in education allocation dashboard");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.navigate().to(getlocator("home_page"));
			logger.fail(e.toString());
			String temp = Utill.getScreenshot(driver);
			logger.fail(e.toString(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

	}

	public void Employment(String no) throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "Prior TM");
		String x = pages.Utill().get_text(getlocator("vemp_all_out"));
		if (!(x.equals("0"))) {
			pages.Utill().click_element(getlocator("vemp_all_out"));
			pages.Wait().wait_until_loader_is_invisible();
			w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("v_normal"))));
			String y = pages.Utill().get_text(getlocator("v_normal"));
			if (!(y.equals("0"))) {
				pages.Utill().click_element(getlocator("v_normal"));
				pages.Wait().wait_until_loader_is_invisible();
				w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_table"))));
				int index = 0;
				String ss = "";
				List<WebElement> table_list = driver
						.findElements(By.xpath("//*[@id='" + getlocator("vemp_table") + "']/tbody/tr/td[8]/a"));
				for (int i = 0; i < table_list.size(); i++) {
					String n = table_list.get(i).getText();
					if (n.equals(no)) {
						index = i + 2;

						pages.Utill().click_element(
								"//*[@id='" + getlocator("vemp_table") + "']/tbody/tr[" + index + "]/td[1]/input[9]");
						pages.Utill().click_element(getlocator("v_edu_vr"));
						pages.Wait().wait_until_loader_is_invisible();

						w.until(ExpectedConditions
								.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_btnInitiateVR")));
						pages.Utill().click_element("ctl00_ContentPlaceHolder1_btnInitiateVR");
						pages.Wait().wait_until_loader_is_invisible();
						ss = pages.Utill().clickAlertbox();
						System.out.println(ss);

						break;
					}
				}
				if ((index == 0)) {
					System.out.println("Employment vr initiation failed");
					logger.fail("Employment vr initiation failed");
				} else {
					logger.pass("Employment vr initiation completed");
					x = "";
					x = pages.Utill().get_text(getlocator("v_updation_out"));
					if (!(x.equals("0"))) {
						pages.Utill().click_element(getlocator("v_updation_out"));
						pages.Wait().wait_until_loader_is_invisible();
						y = "";
						y = pages.Utill().get_text(getlocator("vemp_upda_normal"));
						if (!(y.equals("0"))) {
							pages.Utill().click_element(getlocator("vemp_upda_normal"));
							pages.Wait().wait_until_loader_is_invisible();
							// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[8]
							// .//*[@id='ctl00_ContentPlaceHolder1_grdAllocFW']/tbody/tr[2]/td[2]/input
							w.until(ExpectedConditions.presenceOfElementLocated(By.id(getlocator("vemp_upda_table"))));
							index = 0;
							ss = "";
							table_list = driver.findElements(
									By.xpath("//*[@id='" + getlocator("vemp_upda_table") + "']/tbody/tr/td[8]"));
							for (int i = 0; i < table_list.size(); i++) {
								String n = table_list.get(i).getText();
								if (n.equals(no)) {
									index = i + 2;
									pages.Utill().click_element("//*[@id='" + getlocator("vemp_upda_table")
											+ "']/tbody/tr[" + index + "]/td[2]/input");

									
									Thread.sleep(1000);
									String currentWindow=driver.getWindowHandle();
									Set<String> handles=driver.getWindowHandles();
									System.out.println(handles);
									for(String e:handles) {
										if(!(e.equals(currentWindow))) {
											driver.switchTo().window(e);
											break;
										}
									}
									System.out.println(driver.getTitle());
									// w.until(ExpectedConditions
									// .presenceOfElementLocated(By.id(getlocator("vEdu_EducationType"))));
									//
									// pages.Utill().input_text((getlocator("vEdu_Name")), (getvalue("vEdu_Name")));
									// w.until(ExpectedConditions
									// .presenceOfElementLocated(By.xpath("//*[text()='" + getvalue("vEdu_Name") +
									// "']")));
									// pages.Utill().click_element("//*[text()='" + getvalue("vEdu_Name") + "']");
									// pages.Wait().wait_until_loader_is_invisible();
									// pages.Utill().input_text(getlocator("vEdu_Address"),
									// getvalue("vEdu_Address"));
									// pages.Utill().input_text(getlocator("vEdu_StudentID"),
									// getvalue("vEdu_StudentID"));
									// pages.Utill().input_text(getlocator("vEdu_Gap"), getvalue("vEdu_Gap"));
									// pages.Utill().input_text(getlocator("vEdu_StartDt"),
									// getvalue("vEdu_StartDt"));
									// pages.Utill().input_text(getlocator("vEdu_EndDt"), getvalue("vEdu_EndDt"));
									// pages.Utill().input_text(getlocator("vEdu_PassYear"),
									// getvalue("vEdu_PassYear"));
									// pages.Utill().select_by_label(getlocator("vEdu_DegreeType"),
									// getvalue("vEdu_DegreeType"));
									// pages.Utill().input_text(getlocator("vEdu_NameofDegree"),
									// getvalue("vEdu_NameofDegree"));
									// pages.Utill().input_text(getlocator("vEdu_MajorSubject"),
									// getvalue("vEdu_MajorSubject"));
									// pages.Utill().select_by_label(getlocator("vEdu_Graduated"),
									// getvalue("vEdu_Graduated"));
									// pages.Utill().select_by_label(getlocator("vEdu_ProgramType"),
									// getvalue("vEdu_ProgramType"));
									// pages.Utill().input_text(getlocator("vEdu_CGPA"), getvalue("vEdu_CGPA"));
									// pages.Utill().input_text(getlocator("vEdu_ClassObtained"),
									// getvalue("vEdu_ClassObtained"));
									// pages.Utill().input_text(getlocator("vEdu_VerifierName"),
									// getvalue("vEdu_VerifierName"));
									// pages.Utill().input_text(getlocator("vEdu_VerifierDesignation"),
									// getvalue("vEdu_VerifierDesignation"));
									// pages.Utill().input_text(getlocator("vEdu_VerifierContact"),
									// getvalue("vEdu_VerifierContact"));
									// pages.Utill().input_text(getlocator("vEdu_VerifierEmail"),
									// getvalue("vEdu_VerifierEmail"));
									// pages.Utill().select_by_label(getlocator("vEdu_Confirmfrom"),
									// getvalue("vEdu_Confirmfrom"));
									// pages.Utill().select_by_label(getlocator("vEdu_Typeofrevert"),
									// getvalue("vEdu_Typeofrevert"));
									// pages.Utill().select_by_label(getlocator("vEdu_Mode"),
									// getvalue("vEdu_Mode"));
									// pages.Utill().input_text(getlocator("vEdu_VerComments"),
									// getvalue("vEdu_VerComments"));
									// pages.Utill().click_element(getlocator("vedu_date"));
									// w.until(ExpectedConditions
									// .presenceOfElementLocated(By.id(getlocator("vedu_today"))));
									// pages.Utill().click_element(getlocator("vedu_today"));
									// pages.Utill().click_element(getlocator("vedu_green"));
									//
									// pages.Utill().handle_Alert();
								}
							}
						}
					} else {
						logger.fail("no value in education updation dashboard");
						System.out.println("no value in updation allocation dashboard");
					}
				}
			} else {
				logger.fail("no value in education allocation dashboard");
				System.out.println("no value in education allocation dashboard");
			}
		} else {
			logger.fail("no value in education allocation dashboard");
			System.out.println("no value in education allocation dashboard");
		}
	}

	public void temp() throws Exception {
		pages.CaseRegistration().navigateTo("Dashboard", "Prior TM");
		pages.Utill().click_element(getlocator("v_updation_out"));
		pages.Wait().wait_until_loader_is_invisible();
		pages.Utill().click_element(getlocator("vemp_upda_normal"));
		pages.Wait().wait_until_loader_is_invisible();
		Thread.sleep(2000);
		pages.Utill().click_element("ctl00_ContentPlaceHolder1_grdUpdation_ctl05_btnemp");
		Thread.sleep(1000);
		String currentWindow=driver.getWindowHandle();
		Set<String> handles=driver.getWindowHandles();
		System.out.println(handles);
		for(String e:handles) {
			if(!(e.equals(currentWindow))) {
				driver.switchTo().window(e);
				break;
			}
		}
		System.out.println(driver.getTitle());
		
	}

}
