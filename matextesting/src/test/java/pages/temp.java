package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class temp

{
	 public static void main(String args[]) {
	        int n=40;
	        for (int i=1;i<=n;i++ ){
	            for (int j=1;j<=n-i ;j++ ){
	                System.out.print(" ");
	            } 
	            for(int k=1; k<=i;k++){
	                System.out.print(k+" ");
	            }
	        System.out.println();    
	        } 
	        
	for (int i=n-1;i>=1;i-- ){
	            for (int j=n-i;j>=1 ;j-- ){
	                System.out.print(" ");
	            } 
	            for(int k=1; k<=i;k++){
	                System.out.print(k+" ");
	            }
	        System.out.println();    
	        } 
	        
	    
		
	}
	public static void post() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification headerreq = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); // Cast
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");
		requestParams.put("Email",  "sample2ee26d9@gmail.com");
		headerreq.header("Content-Type", "application/json");
		headerreq.body(requestParams.toJSONString());
		
//		Response response = headerreq.request(Method.POST, "/register");
		Response response = headerreq.post("/register");
		System.out.println(response.asString());
		int statusCode = response.getStatusCode();
		
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println(statusCode);
		System.out.println(successCode);
	}
public void get() {
	RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	RequestSpecification headerreq = RestAssured.given();
	Response response = headerreq.request(Method.GET, "/Hyderabad");
//	String responsebody = response.getBody().asString();
//	System.out.println(responsebody);
//	String rescode = response.getStatusLine();
//	System.out.println(rescode);
//	String contentType = response.header("Content-Type");
//	System.out.println("Content-Type value: " + contentType);
// 
//	
//	String serverType =  response.header("Server");
//	System.out.println("Server value: " + serverType);
// 
//	
//	String acceptLanguage = response.header("Content-Encoding");
//	System.out.println("Content-Encoding: " + acceptLanguage);
//	System.out.println(response.getHeaders());
//	List<Header> hd =response.getHeaders().asList();
	Headers hd= response.headers();
	for(Header h:hd) {
		System.out.println(h.getName()+" : "+h.getValue());
	}
}
}