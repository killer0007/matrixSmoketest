package maintest2;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CaseStatus {

	public static void main(String[] args) throws MalformedURLException {
		URL server = new URL("http://127.0.0.1:4444/wd/hub");
		 
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setBrowserName("chrome");
	 
	    System.out.println("Connecting to " + server);
	 
	    WebDriver driver = new RemoteWebDriver(server, capabilities);
	 
	    driver.get("http://www.google.com");
	 
	    driver.quit();
	}
	public void  casestatus() {
		RestAssured.baseURI ="http://192.168.2.17:84/API_CaseStatusService.svc";
		
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("RefNo", "API-000034"); // Cast
		request.body(requestParams.toJSONString());
		request.contentType("application/json");
		Response response = request.post("/ValidateCaseStatus");
		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
//		response.body().as(List<String>());
		System.out.println("Response body: " + response.body().asString());
		System.out.println(requestParams.toJSONString());
	}

	}


