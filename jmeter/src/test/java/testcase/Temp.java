package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Temp {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Properties pro = new Properties();
		String current= System.getProperty("user.dir");
		System.out.println(current);
		pro.load(new FileInputStream(new File(current+"/config.properties")));
	String data =	pro.getProperty("downloadFilepath");
	System.out.println(data);
	}}

