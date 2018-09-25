package maintest;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.fluttercode.datafactory.impl.DataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.graphbuilder.struc.LinkedList;

import io.restassured.internal.support.FileReader;

public class temp {
String name;
public temp(String name) {
	this.name=name;
}
public static void main(String[] args) {
	temp t = new temp("hi");
	one o = t.new one();
	two to = t.new two();
}
class one {
	public one() {
		System.out.println("from one :"+name);
	}
}
class two{
	public two() {
		System.out.println("from two :"+name);
	}
}
}


