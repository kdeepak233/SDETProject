package testCases;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import services.Connection;
import utils.DBConnections;
import utils.HelperTest;

public class TestCase2 extends HelperTest {
	@Test(groups = {"tc2","testcase"})
	public void testCase2() {

		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		Connection connection=new Connection();
		ProfilePage profilePage=new ProfilePage();
		DBConnections dbConnections =new DBConnections();
		
		setTestSetId("TC_ProfileTestCase");
		
		connection.postRestData(getTestData("signupAPI"));
		loginPage.enterLoginCredentials();
		homePage.verifyLogin();
		homePage.clickOnProfile();
		dbConnections.queryBuilder(getTestData("profileIdQuery"), "userEmail");
		dbConnections.getDataFromDataBase("user_id");
		profilePage.getProfileId();
		profilePage.verifyProfileIdWithDb();
		homePage.clickLogo();
		homePage.logOut();
		loginPage.verifyLogOut();
		connection.deleteData(getTestData("profileAPI"), "profileId");
		loginPage.enterLoginCredentials();
		homePage.veificationFailed();	
	}
}