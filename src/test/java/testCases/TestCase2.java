package testCases;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import services.Connection;
import utils.HelperTest;

public class TestCase2 extends HelperTest {
	@Test(groups = "tc2")
	public void testCase2() {

		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		Connection connection=new Connection();
		ProfilePage profilePage=new ProfilePage();
		
		setTestSetId("TC_ProfileTestCase");
		
		connection.postRestData(getTestData("signupAPI"));
		loginPage.enterLoginCredentials();
		homePage.verifyLogin();
		homePage.clickOnProfile();
		profilePage.getProfileId();
		homePage.clickLogo();
		homePage.logOut();
		loginPage.verifyLogOut();
		connection.deleteData(getTestData("profileAPI"), "profileId");
		loginPage.enterLoginCredentials();
		homePage.veificationFailed();
		
	}
	
}