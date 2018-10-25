package maintest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class temp2 {

	public static void main(String arg[]) throws Exception {
//		String file = "C:\\Users\\admin\\Documents\\serail.ser";
//		WebDriver driver=new ChromeDriver();
//		FileOutputStream fout = new FileOutputStream(new File(file));
//		ObjectOutputStream out= new ObjectOutputStream(fout);
//		out.writeObject(driver);
//		out.close();
//		fout.close();
//		WebDriver driver2=null;
//		FileInputStream fis = new FileInputStream(new File(file));
//		ObjectInputStream in = new ObjectInputStream(fis);
//		driver2=(WebDriver) in.readObject();
//		in.close();
//		fis.close();
//		driver2.manage().window().maximize();
//		driver.get("http://192.168.2.17:97");
		String path="D:\\gopi\\checks360\\pdf\\Current Address.pdf";
		System.out.println(new File(path).getName());
		
	}
}

class serial implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8200304652726450859L;
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
