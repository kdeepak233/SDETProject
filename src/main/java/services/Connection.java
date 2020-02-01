package services;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.BasePage;

import static io.restassured.RestAssured.given;

public class Connection extends BasePage {

	RequestSpecification requestSpecification;
	
	public static RequestSpecBuilder intializeService(String baseUri) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(baseUri);
		return builder;
	}
	
	public void postRestData(RequestSpecBuilder requestSpecBuilder,String basePath) {
		String json=getJson(basePath);
		requestSpecBuilder.setBasePath(basePath);
		requestSpecBuilder.setContentType("application/json");
		requestSpecification=requestSpecBuilder.build();
		Response responce=given(requestSpecification).body(json).when().post().then().extract().response();
		String statusCode=Integer.toString(responce.statusCode());
		
		if(statusCode.startsWith("2"))
			getReport("info", basePath.split("/")[2]+" repsonse "+responce.prettyPrint()+" Passed");
		else
			getReport("fail", basePath.split("/")[2]+" repsonse "+responce.prettyPrint()+"");
			
	}
	
	public String getJson(String basePath) {
		ServiceProvider serviceProvider=new ServiceProvider();
		String json = null;
		if(basePath.contains("signup"))
			json=serviceProvider.signUpServiceData();
		System.out.println(json);
		return json;
	}
}
