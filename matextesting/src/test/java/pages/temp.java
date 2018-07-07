package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

public class temp   {

	public static void main(String[] args) {
	
		SimpleDateFormat df = new SimpleDateFormat("MMMM");
//		Date currentMonth = new Date();
	String mon=	df.format("Jan").toString();
		
	System.out.println(mon);
	
}}