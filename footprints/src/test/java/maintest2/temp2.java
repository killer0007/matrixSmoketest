package maintest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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

	public static void main(String arg[]) throws Exception {
		String file = "C:\\Users\\admin\\Documents\\serail.ser";
		WebDriver driver=new ChromeDriver();
		FileOutputStream fout = new FileOutputStream(new File(file));
		ObjectOutputStream out= new ObjectOutputStream(fout);
		out.writeObject(driver);
		out.close();
		fout.close();
		WebDriver driver2=null;
		FileInputStream fis = new FileInputStream(new File(file));
		ObjectInputStream in = new ObjectInputStream(fis);
		driver2=(WebDriver) in.readObject();
		in.close();
		fis.close();
		driver2.manage().window().maximize();
		driver.get("http://192.168.2.17:97");
		
	}
}

class serial implements Serializable {
	
	
	public int a;
	public String b;

	public serial(int a, String b) {
		this.a = a;
		this.b = b;
	}

	public void set(int c) {
		a = c;
	}
}
