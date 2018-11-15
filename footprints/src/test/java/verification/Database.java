package verification;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Database extends dataEntryQC.Database implements Verification {
	/**
	 * This is class for Database page in data entry
	 * 
	 * @param logger logger instance
	 */
	public Database(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
	}

	
	/**
	 * click submit button on database data entry
	 * 
	 * @throws Exception WebDriverException
	 */
	public void submit() throws Exception {
		click("ctl00_ContentPlaceHolder1_btnDataBaseSaveSubmit_input");
		waitUntilLoaderisInvisible(100);
		SwitchDefault();
		confirmAlert();
		waitUntilLoaderisInvisible(100);
	}

	
public void group(String groupName) {
	String group=getValue("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_Input");
	if(!group.equals(groupName)) {
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseGroup_DropDown']/div/ul//li[text()='"
				+ groupName + "']");
		waitUntilLoaderisInvisible(100);
	}
}
public void tag(String tagName) {
	String tag=getValue("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_Input");
	if(!tag.equals(tagName)) {
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_ddlDataBaseTag_DropDown']/div/ul//li[text()='"
				+ tagName + "']");
		waitUntilLoaderisInvisible(100);
	}
}
public void addDBCheck() {
	String tagName="Record Found";
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_Input");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_DropDown']/div/ul/li[1]")));
		click("//*[@id='ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_ddlDataBaseResult_DropDown']/div/ul//li[text()='"
				+ tagName + "']");
		waitUntilLoaderisInvisible(100);
		sendKeys("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_txtDataBaseComments", "found");
		click("ctl00_ContentPlaceHolder1_dockDataBaseDetails_C_gviewDataBaseElements_ctl00_ctl04_imgBtnDataBaseOk");
		waitUntilLoaderisInvisible(100);
}
	public void VerifierName(String name) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierName", name);
	}

	public String VerifierName() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierName");
	}

	public void VerifierDesignation(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierDesignation", relationship);
	}

	public String VerifierDesignation() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierDesignation");
	}

	public void VerifierContactNo(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierContactNo", relationship);
	}

	public String VerifierContactNo() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierContactNo");
	}

	public void VerifierEmail(String relationship) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierEmailID", relationship);
	}

	public String VerifierEmail() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierEmailID");
	}

	public void Ver_Comments(String comments) {
		sendKeys("ctl00_ContentPlaceHolder1_txtDataBaseVerifierComments", comments);
	}

	public String Ver_Comments() {
		return getValue("ctl00_ContentPlaceHolder1_txtDataBaseVerifierComments");
	}

	public void ComponentStatus(String status) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
		if (!value.equals(status.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@id='ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_DropDown']/div/ul/li[1]")));
			click("//*[@id='ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_DropDown']/div/ul//li[text()='"
					+ status + "']");
		}
	}

	public String ComponentStatus() {
		return getValue("ctl00_ContentPlaceHolder1_ddlDataBaseVerifierMark_Input");
	}

	public void ServiceProvider(String name) {
		String value = getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
		if (!value.equals(name.trim())) {
			click("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul/li[1]")));
			click(
					"//*[@id='ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_DropDown']/div/ul//li[text()='"
							+ name + "']");
		}
	}

	public String ServiceProvider() {
		return getValue("ctl00_ContentPlaceHolder1_ddlServiceProviderForVerification_Input");
	}
	public void Verification() throws Exception {
		Properties pro = veridata("database");
		this.databasecheck();
		this.group("Felony Check");
		this.tag("FELONY TAG");
		this.addDBCheck();
		this.document();
		this.UploadDocument("Verification Report", pro.getProperty("dbdoc"));
		this.docclose();
		this.VerifierName(pro.getProperty("VerifierName"));
		this.VerifierDesignation(pro.getProperty("VerifierDesignation"));
		this.VerifierContactNo(pro.getProperty("VerifierContactNo"));
		this.VerifierEmail(pro.getProperty("VerifierEmail"));
		this.Ver_Comments(pro.getProperty("verComments"));
		this.ComponentStatus(pro.getProperty("ComponentStatus"));
		this.submit();
	}
	public Map<String, String> databasedata() throws Exception{	
		this.databasecheck();
		Map<String , String> map=new LinkedHashMap<String, String>();
		map.put("VerifierName", this.VerifierName());
		map.put("VerifierDesignation", this.VerifierDesignation());
		map.put("VerifierContactNo", this.VerifierContactNo());
		map.put("VerifierEmail", this.VerifierEmail());
		map.put("Ver_Comments", this.Ver_Comments());
		map.put("ComponentStatus", this.ComponentStatus());
		map.put("ServiceProvider", this.ServiceProvider());
		logger.log(Status.INFO, map.toString());
		return map;
	}
	public Map<String, String> filedata() throws Exception{
		Map<String , String> map=new LinkedHashMap<String, String>();
		Properties pro= veridata("database");
		map.put("VerifierName", pro.getProperty("VerifierName"));
		map.put("VerifierDesignation", pro.getProperty("VerifierDesignation"));
		map.put("VerifierContactNo", pro.getProperty("VerifierContactNo"));
		map.put("VerifierEmail", pro.getProperty("VerifierEmail"));
		map.put("Ver_Comments", pro.getProperty("verComments"));
		map.put("ComponentStatus", pro.getProperty("ComponentStatus"));
		map.put("ServiceProvider", pro.getProperty("ServiceProvider"));
		logger.log(Status.INFO, map.toString());
		return map;
		
	}
	

	@Override
	public void UpdateReportComments() {
		int count = driver.findElements(By.xpath("//*[@id='accordion']/div")).size();

		for (int i = 1; i < count; i++) {
			if (i > 1) {
				click("//*[@id='accordion']/div[" + Integer.toString(i) + "]//b");
			}
			String info = getText("//*[@id='accordion']/div[" + Integer.toString(i) + "]//td[2]/span");
			sendKeys("//*[@id='accordion']/div[" + Integer.toString(i) + "]//div[3]/div/p/..", info);
		}

	}
}
