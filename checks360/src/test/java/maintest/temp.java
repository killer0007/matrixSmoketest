package maintest;



import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners(environment.Listener.class)

public class temp {
	@Test
	public void one() {
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(false, "msg");
		sf.assertAll();
	}
	@AfterMethod
	public void teardown(ITestResult result) {
		System.out.println("-----------------------------------");
		System.out.println(result.getThrowable().getMessage());
		System.out.println("-----------------------------------");
//		if(result.getThrowable().getMessage().toString().contains("AssertionError")) {
//			System.out.println("assert error");
//		}
//		else {
//			System.out.println("others");
//		}
	}
}
