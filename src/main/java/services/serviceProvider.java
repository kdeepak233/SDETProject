package services;

import org.json.JSONObject;

import pages.BasePage;

public class serviceProvider extends BasePage{
	
	public JSONObject signUpServiceData() {
		JSONObject json=new JSONObject();
		JSONObject jsonChlid=new JSONObject();		
		
		String name=getRandomString(7);
		String email=name+"@demo.com";
		String phoneNumber="9"+getRandomIntegerLength(9);
		
		setData("name", name);
		setData("userEmail", email);
		setData("phoneNumber",phoneNumber);
		setData("password","Demo@1234");
	
		jsonChlid.put("description", "description");
		jsonChlid.put("name", "DemoUser");
		jsonChlid.put("roleId", 196);
		json.put("emailId", email);
		json.put("gender", "MALE");
		json.put("name", name);
		json.put("mobileNo", phoneNumber);
		json.put("password", "Demo@1234");
		json.put("role", jsonChlid);
		json.put("userStatus", "ACTIVE");		

		getReport("info", "Data created for signup "+json.toString());
		return json;
	}
}