package maintest2;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class temp2 {
	public static final String xmlFilePath = "D:\\c bkp\\downloads\\image\\xmlfile.xml";
static WebDriver driver;
	public static void main(String argv[]) {
//temp2 t=new temp2();
		start();
		for (int i = 0; i < 4; i++) {
			System.out.println("count is :"+i);
			System.out.println(start().toString());
//			driver.close();
		}
	}
	
	public static WebDriver start() {
		if(driver==null) {
		driver=new ChromeDriver();
		}
		return driver;
	}
	 
}
