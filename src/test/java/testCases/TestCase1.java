package testCases;

import org.testng.annotations.Test;

import pages.FurniturePage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.WishlistPage;
import utils.HelperTest;

public class TestCase1 extends HelperTest {
	@Test(groups = "loginTestCase")
	public void te1() {

		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		FurniturePage furniturePage = new FurniturePage();
		ProductPage productPage = new ProductPage();
		WishlistPage wishlistPage=new WishlistPage();

		setTestSetId("TC12345");

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