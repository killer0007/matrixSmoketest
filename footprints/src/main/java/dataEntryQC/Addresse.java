package dataEntryQC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Addresse {
public static void main(String[] args) throws ParseException {
	String date ="08/2016";
System.out.println(new Addresse().FormateDate("MMMM yyyy", date));

}
public String FormateDate(String format, String date) throws ParseException{
	SimpleDateFormat df = new SimpleDateFormat("MM/yyyy");
	Date d=df.parse(date);
	return new SimpleDateFormat(format).format(d).toString();
}
}
