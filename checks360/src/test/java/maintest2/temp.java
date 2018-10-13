package maintest2;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class temp {
	public static WebDriver driver;

	public static void main(String[]  args) throws MalformedURLException, InterruptedException{
//WebDriver driver= new ChromeDriver();
//driver.get("http://www.google.com");
//System.out.println(driver.manage().window().getSize());
//driver.manage().window().maximize();
//System.out.println(driver.manage().window().getSize());
//driver.close();
		temp t = new temp();
		t.test();
	}
	public void test() {
		casetracker cs = new casetracker();
		cs.setComponentname("address");
		cs.setComponentoutcome("out");
		cs.setCurrenstage("stage");
		cs.setPerson("gopi"); 
		cs.setRecievedon("recieve");
		cs.setStatus("status");
//		casetracker cs2 = new casetracker();
		cs.setComponentname("addresssssssssssss");
		cs.setComponentoutcome("out");
		cs.setCurrenstage("stage");
		cs.setPerson("gopi"); 
		cs.setRecievedon("recieve");
		cs.setStatus("status");
		List<casetracker> re = new ArrayList<casetracker>();
		re.add(cs);
		re.add(cs);
		System.out.println(re.get(0).getComponentname());
		System.out.println(re.get(1).getComponentname());
	}
}
class casetracker {
	private String componentname;
	private String recievedon;
	private String status;
	private String componentoutcome;
	private String currenstage;
	private String person;
	public String getComponentname() {
		return componentname;
	}
	public void setComponentname(String componentname) {
		this.componentname = componentname;
	}
	public String getRecievedon() {
		return recievedon;
	}
	public void setRecievedon(String recievedon) {
		this.recievedon = recievedon;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComponentoutcome() {
		return componentoutcome;
	}
	public void setComponentoutcome(String componentoutcome) {
		this.componentoutcome = componentoutcome;
	}
	public String getCurrenstage() {
		return currenstage;
	}
	public void setCurrenstage(String currenstage) {
		this.currenstage = currenstage;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
}