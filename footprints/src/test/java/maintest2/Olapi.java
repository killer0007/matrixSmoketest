package maintest2;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
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
		RestAssured.baseURI ="http://192.168.2.17:85/APIService.svc";
		
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("APIKey", "5E9FB516-6E72-4820-AE82-EF31E0A84D17"); // Cast
		requestParams.put("CandidateID", "132");
		requestParams.put("DOB", "27/03/1991");
		requestParams.put("Email", "gopi@testmail.com");	
		requestParams.put("EmergencyContactNumber",  "9669854556");
		requestParams.put("EmergencyContactPerson", "Demo"); // Cast
		requestParams.put("FatherFirstName", "fname");
		requestParams.put("FatherLastName", "lname");
		requestParams.put("FirstName", "gopi");	
		requestParams.put("Fresher",  "1");
		requestParams.put("Gender", "Male"); // Cast
		requestParams.put("CandidateID", "4567");
		requestParams.put("LandlineNumber", "68877668-5345");
		requestParams.put("LinkedInID", "gopi@linkedin");	
		requestParams.put("MaritalStatus",  "Single");
		requestParams.put("MobileNumber", "8899887788");
		requestParams.put("Nationality", "indian");	
		requestParams.put("WorkFlowID",  "9");
		requestParams.put("LastName",  "vijay");

		request.body(requestParams.toJSONString());
		request.contentType("application/json");
		Response response = request.post("/ValidateCaseRegistration");

		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
//		response.body().as(List<String>());
		System.out.println("Response body: " + response.body().asString());
		System.out.println(requestParams.toJSONString());
	}
	
}
