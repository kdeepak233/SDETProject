package testCases;

import org.testng.annotations.Test;

import pages.FurniturePage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.WishlistPage;
import services.Connection;
import utils.HelperTest;

public class TestCase1 extends HelperTest {
	@Test(groups = {"loginTestCase","testcase"})
	public void te1() {

		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		FurniturePage furniturePage = new FurniturePage();
		ProductPage productPage = new ProductPage();
		WishlistPage wishlistPage=new WishlistPage();
		Connection connection=new Connection();
	
		setTestSetId("TC_LoginTestCase");
		
		connection.postRestData(getTestData("signupAPI"));
		loginPage.enterLoginCredentials();
		homePage.verifyLogin();
		homePage.clickFurniture();
		furniturePage.selectProduct();
		productPage.recordDetails();
		productPage.addToWishList();
		homePage.clickLogo();
		homePage.clickWishList();
		wishlistPage.verifyProduct();
		homePage.clickLogo();
		homePage.logOut();
		loginPage.verifyLogOut();
	}
}