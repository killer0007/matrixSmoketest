package dataEntryQC;

import java.time.format.DateTimeFormatter;

public class Addresse {
public static void main(String[] args) throws Exception {
	throw new myexception("error");
}

}
class myexception extends Exception{
	String message;
	public  myexception(String msg) {
		message=msg;
	}
	public String toString() {
	return message;
	}
}
