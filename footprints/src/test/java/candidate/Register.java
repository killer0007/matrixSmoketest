package candidate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import environment.Utill;

public class Register extends Utill {

	public Register(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public void clickRegisterLink() {
		super.click("ctl00_ContentPlaceHolder1_lnkSingUP");
	}

	public void clickRegisterbtn() {
		super.click("ctl00_ContentPlaceHolder1_btnRegister");
	}

	public void FirstName(String firstName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtFirstName", firstName);
	}

	public void LastName(String lastName) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtLastName", lastName);
	}

	public void Fresher(boolean value) {
		if (value) {
			super.click("ctl00_ContentPlaceHolder1_rblFresher_0");
		}
	}

	public void EmailID(String email) throws Exception {
		email=emailGererator(email);
		super.sendKeys("ctl00_ContentPlaceHolder1_txtEmailID", email+"@ggmail.com");
	}

	public void Password(String password) {
		super.sendKeys("ctl00_ContentPlaceHolder1_txtPass", password);
		super.sendKeys("ctl00_ContentPlaceHolder1_txtPass2", password);
	}

	public void SignIn() {
		super.click("ctl00_ContentPlaceHolder1_lnkSignIn");
	}

	public void dob() {
		String[] date = date();
		if (date != null) {
			super.click("ctl00_ContentPlaceHolder1_ddlDate_Arrow");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlDate_DropDown']/div/ul/li[text()='" + date[0] + "']");
			super.click("ctl00_ContentPlaceHolder1_ddlMonth_Arrow");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlMonth_DropDown']/div/ul/li[text()='" + date[1] + "']");
			super.click("ctl00_ContentPlaceHolder1_ddlYear_Arrow");
			super.sleep(500);
			super.click("//div[@id='ctl00_ContentPlaceHolder1_ddlYear_DropDown']/div/ul/li[text()='" + date[2] + "']");
		}
	}

	private String[] date() {
		Date df = null;
		SimpleDateFormat sf = null;
		try {
			df = new SimpleDateFormat("dd/MM/yyyy").parse(super.getDob());
		} catch (ParseException e) {
			e.printStackTrace();
			logger.log(Status.FAIL, e.getMessage().toString());
		}
		sf = new SimpleDateFormat("dd/MMMM/yyyy");
		String[] date = sf.format(df).toString().split("/");
		return date;
	}
	private void writelog(String log) throws Exception {
		File file = new File("./log.txt");
		FileWriter writer = new FileWriter(file);
		writer.write(log);
		writer.close();
	}

	public String getemail() throws IOException {
		String file = "./log.txt";
		List<String> lines = Files.readAllLines(Paths.get(file));
		String re =lines.get(0).toString().trim();
		return re;
	}

	private String emailGererator(String email) throws Exception {
		int value = Integer.parseInt(email.replaceAll("[^0-9]", ""));
		String name = email.replaceAll("[0-9]", "");
		writelog(name+Integer.toString(value+1));
		return name+Integer.toString(value+1);
	}
	public void unCheckAll() {
		List<WebElement> box =super.getWebelementList("//input[@type='checkbox']");
		for (int i = 0; i < box.size(); i++) {
			if(box.get(i).isSelected()) {
				box.get(i).click();
			}
		}
		
	}
	public void selectCheck(String componentName) {
		super.click("//a[text()='"+componentName+"']/ancestor::td[1]/preceding-sibling::td[1]/input");
	}
	public void selectCheck(String [] list) {
		for (int i = 0; i < list.length; i++) {
			this.selectCheck(list[i]);
		}
	}
}
