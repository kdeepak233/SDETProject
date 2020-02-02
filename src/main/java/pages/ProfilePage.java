package pages;

import org.openqa.selenium.By;

public class ProfilePage  extends BasePage {
	
	private static By txtProfile=By.xpath("//h2[text()=' My Profile ']");
	
	public void getProfileId() {
		waitVisibility(txtProfile);
		String profileId=getDriver().getCurrentUrl().split("me/")[1];
		System.out.println(profileId);
		setData("profileId", profileId);
		getReport("info", "Profile Id is "+ profileId);
	}

}
