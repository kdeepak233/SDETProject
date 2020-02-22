package services;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.BasePage;
import utils.HelperTest;

public class Connection extends BasePage {

	private static RequestSpecification requestSpecification;
	private static RequestSpecBuilder requestSpecBuilder;
	
	public static RequestSpecBuilder intializeService(String baseUri) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(baseUri);
		return builder;
	}
	
	public void postRestData(String basePath) {
		String json=getJson(basePath);
		buildRequest(basePath);
		Response response=given(requestSpecification).body(json).when().post().then().extract().response();
		String statusCode=Integer.toString(response.statusCode());
		if(statusCode.startsWith("2"))
			getReport("info", basePath.split("/")[3]+" repsonse "+response.prettyPrint() +" Passed");
		else
			getReport("fail", basePath.split("/")[3]+" repsonse "+response.prettyPrint()+" Failed");		
	}
	
	public void buildRequest(String basePath) {
		getRequestBuilder();
		requestSpecBuilder.setBasePath(basePath);
		requestSpecBuilder.setContentType("application/json");
		requestSpecification=requestSpecBuilder.build();
	}
	
	public void getRestData(String basePath,String value) {
		buildRequest(basePath);
		Response response=given(requestSpecification).when().get("/"+value);
		String statusCode=Integer.toString(response.statusCode());
		if(statusCode.startsWith("2"))
			getReport("info", basePath.split("/")[2]+" repsonse "+response.prettyPrint()+" Passed");
		else
			getReport("fail", basePath.split("/")[2]+" repsonse "+response.prettyPrint()+"Failed");		
	}
	
	public String getJson(String basePath) {
		ServiceProvider serviceProvider=new ServiceProvider();
		String json = null;
		if(basePath.contains("signup"))
			json=serviceProvider.signUpServiceData();
		System.out.println(json);
		return json;
	}
	
	public void deleteData(String basePath,String value) {
		value=getData(value);
		buildRequest(basePath);
		Response response=given(requestSpecification).when().delete("/"+value).then().extract().response();
		String statusCode=Integer.toString(response.statusCode());
		if(statusCode.startsWith("2"))
			getReport("info", basePath.split("/")[3]+" repsonse "+response.prettyPrint()+" Passed");
		else
			getReport("fail", basePath.split("/")[3]+" repsonse "+response.prettyPrint()+" Failed");		
	}
	
	public void getRequestBuilder() {
		requestSpecBuilder=HelperTest.requestSpecBuilder;
	}
}
