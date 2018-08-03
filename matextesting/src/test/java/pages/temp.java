package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class temp

{
	public static void main(String[] args) throws Exception {
		Properties pro = new Properties();
		String current= System.getProperty("user.dir");
		System.out.println(current);
		pro.load(new FileInputStream(new File(current+"/config.properties")));
	String data =	pro.getProperty("downloadFilepath");
	System.out.println(data);
	}

	public void deleteFiles(File folder) throws IOException {
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				// file.delete();
				System.out.println(file.getName());
			} else if (file.isDirectory()) {
				deleteFiles(file);
				System.out.println(file.getName());
			}
		}
	}

	public Boolean isfileexist(File folder, String filename) throws Exception {
//		boolean re=this.waitforfile(folder);
//		if(true) {
		File[] files = folder.listFiles();
		if (files[0].isFile()) {
			String name = files[0].getName();
			System.out.println(name);
			if (name.equals(filename)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
//		}
//		else {
//			return false;
//		}
		
	}
	private Boolean waitforfile(File folder) throws Exception{
		File[] files = folder.listFiles();
		int i=0;
		while(files.length==0 && i<10) {
			Thread.sleep(200);
			files = folder.listFiles();
			System.out.println("looping :"+i);
			i++;
		}
		if(files.length==0) {
			System.out.println("file empty");
			return false;
		}
		else if (files.length>0) {
			return true;
		}
		else {
			System.out.println("file empty2");
			return false;
		}
	}
}