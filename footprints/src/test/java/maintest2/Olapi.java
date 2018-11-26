package maintest2;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import dashboard.DataEntrySupervision;
import dataEntry.Criminal;
import environment.BaseClass;
import environment.Pages;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import verification.VerificationInitiate;

public class Olapi extends Thread {
	public static void main(String[] args) {
		Olapi ola = new Olapi();
		String fname=ola.candidateName();
		String lname=ola.lastName();
		String dob=ola.getDob();
		RestAssured.baseURI ="http://192.168.2.17:85/APIService.svc";
		
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("APIKey", "5E9FB516-6E72-4820-AE82-EF31E0A84D17"); // Cast
		requestParams.put("CandidateID", "132");
		requestParams.put("DOB", dob);
//		requestParams.put("Email", "gopi@testmail.com");	
		requestParams.put("Email", "");	
//		requestParams.put("EmergencyContactNumber",  "9669854556");
//		requestParams.put("EmergencyContactPerson", "Demo");
		requestParams.put("EmergencyContactNumber","");
		requestParams.put("EmergencyContactPerson", "");
		requestParams.put("FatherFirstName", "fname");
		requestParams.put("FatherLastName", "lname");
		requestParams.put("FirstName", fname);	
//		requestParams.put("Fresher",  "1");
		requestParams.put("Fresher",  "");
//		requestParams.put("Gender", "Male");
		requestParams.put("Gender", "");
//		requestParams.put("LandlineNumber", "68877668-5345");
		requestParams.put("LandlineNumber", "");
//		requestParams.put("LinkedInID", "gopi@linkedin");
		requestParams.put("LinkedInID", "");
//		requestParams.put("MaritalStatus",  "Single");
		requestParams.put("MaritalStatus",  "");
//		requestParams.put("MobileNumber", "8899887788");
		requestParams.put("MobileNumber", "");
//		requestParams.put("Nationality", "indian");
		requestParams.put("Nationality", "");
		requestParams.put("WorkFlowID",  "9");
		requestParams.put("LastName",  lname);

		request.body(requestParams.toJSONString());
		request.contentType("application/json");
		Response response = request.post("/ValidateCaseRegistration");

		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
//		response.body().as(List<String>());
		System.out.println("Response body: " + response.body().asString());
		System.out.println(requestParams.toJSONString());
	}
	
	public String candidateName() {
		String name[] = { "Lindsey", "Noah", "Erica", "Cheyenne", "Ryan", "Wyatt", "Erika", "Tim", "Brooklyn", "Jill",
				"Karen", "Leslie", "Eddie", "Mariah", "Nancy", "Wayne", "Chasity", "Terri", "Aaron", "Jim", "Kendra",
				"Stephanie", "Jenna", "Antonio", "Jane", "Jan", "George", "Annette", "Janet", "Raymond", "Steven",
				"Cassandra", "Shane", "Judy", "Unborn", "Brian", "Kaitlyn", "Dusty", "Donald", "Laura", "Alisha",
				"Haley", "Marty", "Leslie", "Sam", "Eric", "Jeffrey", "Donna", "Thomas", "Myron", "Mickey", "Rod",
				"Chuck", "Ken", "Sierra", "Marsha", "Michael", "Dana", "Richard", "James", "Margaret", "Michelle",
				"Hope", "Kylie", "Helen", "Gabriel", "Myron", "Dusty", "Laura", "Paige", "Patrick", "Steven", "Kaylee",
				"Eddie", "Timmy", "Jody", "Terry", "Erin", "Donnie", "Leroy", "Rick", "Jan", "Dave", "Blake", "Carrie",
				"Randi", "Brenda", "Juanita", "Kaitlyn", "Dave", "Sandra", "Jeremiah", "Donna", "Kris", "Vernon",
				"Brianna", "Greg", "Ronald", "Brad", "Shawna" };
		List<String> answersList = Arrays.asList(name);
		Collections.shuffle(answersList);
		return answersList.get(3);
	}
	public String lastName() {
		String name[] = { "Cotton", "Craft", "Cannon", "Larsen", "Ruiz", "Levine", "Santiago", "Smith", "McGuire",
				"Sloan", "Byrd", "Mayer", "Bass", "Holcomb", "Stevens", "Vasquez", "Conway", "Fletcher", "McCray",
				"Olsen", "Allen", "Fry", "Burns", "Garner", "Mayo", "Patton", "Suarez", "Jarvis", "Abbott", "Sloan",
				"Marshall", "Townsend", "Heath", "Burks", "Vega", "Jefferson", "Wilder", "Chaney", "Morgan", "Dudley",
				"Dunn", "Cook", "Franco", "Reilly", "Jackson", "Mercado", "Suarez", "Fields", "Dotson", "Blanchard",
				"Cruz", "McIntyre", "Castillo", "Carr", "Schneider", "Doyle", "Gross", "Whitley", "Wheeler", "Mullen",
				"Kane", "Decker", "Compton", "Adams", "Richmond", "Hurst", "Hayes", "Rowe", "Richardson", "Stark",
				"Walker", "Patterson", "Austin", "Rosa", "Green", "Blackwell", "Roberson", "Stafford", "Nunez",
				"Schmidt", "Stein", "Chang", "Morrison", "McDowell", "Velez", "Flynn", "Brewer", "Kerr", "Wilder",
				"Love", "Bird", "Navarro", "Suarez", "Lloyd", "Powell", "Hahn", "Reed", "Mays" };
		List<String> answersList = Arrays.asList(name);
		Collections.shuffle(answersList);
		return answersList.get(3);
	}
	public String getDob() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Random random = new Random();
		int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);
		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
		return df.format(randomBirthDate).toString();
	}
}
