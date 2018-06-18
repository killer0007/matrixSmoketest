package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import userLogin.*;

public class temp {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		System.out.println(getnextMonth());
	}
	public static String getnextMonth() {
			Calendar cal = GregorianCalendar.getInstance();
	    	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	    	Date currentMonth = new Date();
	    	cal.setTime(currentMonth);

	    	// current month
	    	String currentMonthAsSting = df.format(cal.getTime());

	    	// Add next month
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
	   	String nextMonthAsString = df.format(cal.getTime());
	   	//System.out.println(nextMonthAsString);
	   	return nextMonthAsString;
		
	}
	
}
