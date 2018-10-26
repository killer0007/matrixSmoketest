package maintest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class temp2 {

	public static void main(String arg[]) throws Exception {
		temp2 t = new temp2();
		
System.out.println(t.filedata("12th"));
System.out.println(t.filedata("UG1"));
		
	}
	public HashMap<String, String> filedata(String component) throws Exception{
		HashMap<String , String> map=new HashMap<String, String>();
		Properties pro= this.dedata("education");
		if(component.equals("12th")) {
		map.put("Component", "12th");
		map.put("InstituteName", pro.getProperty("InstituteName"));
		map.put("InstituteAddressLine1", pro.getProperty("InstituteAddressLine1"));
		map.put("InstituteCountry", pro.getProperty("InstituteCountry"));
		map.put("InstituteState", pro.getProperty("InstituteState"));
		map.put("InstituteCity", pro.getProperty("InstituteCity"));
		map.put("BoardName", pro.getProperty("BoardName"));
		map.put("BoardAddressLine1", pro.getProperty("BoardAddressLine1"));
		map.put("BoardCountry", pro.getProperty("BoardCountry"));
		map.put("BoardState", pro.getProperty("BoardState"));
		map.put("BoardCity", pro.getProperty("BoardCity"));
		map.put("NameOfCourse", pro.getProperty("NameOfCourse"));
		map.put("MajorSubject", pro.getProperty("MajorSubject"));
		map.put("TypeOfProgram", pro.getProperty("TypeOfProgram"));
		map.put("CandidateNameinCertificate", pro.getProperty("CandidateNameinCertificate"));
		map.put("Enrollment", pro.getProperty("Enrollment"));
		map.put("CGPA", pro.getProperty("CGPA"));
		map.put("CourseCommencementYear", pro.getProperty("CourseCommencementYear"));
		map.put("CourseCompletionYear", pro.getProperty("CourseCompletionYear"));
		map.put("Comments", pro.getProperty("Comments"));
		map.put("twelveth", new File(pro.getProperty("twelveth")).getName().replaceAll(" ", ""));
		return map;
		}
		
	
	else if(component.equals("UG1")) {
		map.put("Component", "UG1");
		map.put("UG1InstituteName", pro.getProperty("UG1InstituteName"));
		map.put("UG1InstituteAddressLine1", pro.getProperty("UG1InstituteAddressLine1"));
		map.put("UG1InstituteCountry", pro.getProperty("UG1InstituteCountry"));
		map.put("UG1InstituteState", pro.getProperty("UG1InstituteState"));
		map.put("UG1InstituteCity", pro.getProperty("UG1InstituteCity"));
		map.put("UG1BoardName", pro.getProperty("UG1BoardName"));
		map.put("UG1BoardAddressLine1", pro.getProperty("UG1BoardAddressLine1"));
		map.put("UG1BoardCountry", pro.getProperty("UG1BoardCountry"));
		map.put("UG1BoardState", pro.getProperty("UG1BoardState"));
		map.put("UG1BoardCity", pro.getProperty("UG1BoardCity"));
		map.put("UG1NameOfCourse", pro.getProperty("UG1NameOfCourse"));
		map.put("UG1MajorSubject", pro.getProperty("UG1MajorSubject"));
		map.put("UG1TypeOfProgram", pro.getProperty("UG1TypeOfProgram"));
		map.put("UG1CandidateNameinCertificate", pro.getProperty("UG1CandidateNameinCertificate"));
		map.put("UG1Enrollment", pro.getProperty("UG1Enrollment"));
		map.put("UG1CGPA", pro.getProperty("UG1CGPA"));
		map.put("UG1CourseCommencementYear", pro.getProperty("UG1CourseCommencementYear"));
		map.put("UG1CourseCompletionYear", pro.getProperty("UG1CourseCompletionYear"));
		map.put("UG1Comments", pro.getProperty("UG1Comments"));
		map.put("ugone", new File(pro.getProperty("ugone")).getName().replaceAll(" ", ""));
		return map;
	}
	else
		throw new NotFoundException();
	}
	public Properties dedata(String filename) throws Exception {
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(
				new File("./src\\test\\resources\\testdata\\dataentry\\" + filename + ".properties"));
		pro.load(fis);
		return pro;
	}
}

