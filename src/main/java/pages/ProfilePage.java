package pages;

import org.openqa.selenium.By;

public class ProfilePage  extends BasePage {
	
	private static By txtProfile=By.xpath("//h2[text()=' My Profile ']");
	
	public void getProfileId() {
		waitVisibility(txtProfile);
		String profileId=getDriver().getCurrentUrl().split("me/")[1];
		setData("profileId", profileId);
		getReport("info", "Profile Id is "+ profileId);
	}
	
	public void verifyProfileIdWithDb() {
		if(getData("profileId").equals(getData("dbOutput")))
			getReport("info", "Profile Id from application is "+getData("profileId")+" matched with Database");
		else
			getReport("fail", "Profile Id from application is "+getData("profileId")+"and data from database is "+getData("dbOutput")+" is not matched");
	}

}
