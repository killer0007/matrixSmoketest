package maintest;



import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners(environment.Listener.class)

public class temp {
	public static void main(String[] args) {
		String s = "45t";
		System.out.println(s.substring(0,1));
	}
}
