package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

	private By txtLogin = By.xpath("//button/span[contains(text(),'Hi, ')]");
	private By btnLogOut = By.xpath("//div[@role='menu']//button[contains(text(),'Log out')]");
	private By linkLogo = By.xpath("//h3[@routerlink='/home']");
	private By linkFurniture = By.xpath("//button[text()=' All Furnitures ']");
	private By linkWishlist = By.xpath("//form/button/span[contains(text(),'Wishlist')]");
	private By btnProfile = By.xpath("//div[@role='menu']//button[contains(text(),' Profile')]");
	private By txtVerificationFailedMsg = By.xpath("//div[@role='alert' and contains(text(),'Invalid Email or Password')]");
	private By successloggedOutMessage = By.xpath("//div[contains(@aria-label,'Successfully logged out')]");
	private By successMessage = By.xpath("//div[contains(@aria-label,'Successfully logged in')]");
	
	public void verifyLogin() {
		waitTillElementNotVisible(successMessage);
		try {
			waitVisibility(txtLogin);
			getReport("info", "Logged in Successfully");
		} catch (Exception e) {
			getReport("fail", "Login Failed");
		}
	}

	public void logOut() {
		waitVisibility(txtLogin);
		click(txtLogin);
		waitVisibility(btnLogOut);
		click(btnLogOut);
		waitTillElementNotVisible(successloggedOutMessage);
	}

	public void clickLogo() {
		click(linkLogo);
		getReport("info", "clicked on Logo");
	}

	public void clickFurniture() {
		click(linkFurniture);
		getReport("info", "clicked on Furniture");
	}

	public void clickWishList() {
		click(linkWishlist);
		getReport("info", "clicked on wishList");
	}

	public void clickOnProfile() {
		waitVisibility(txtLogin);
		click(txtLogin);
		waitVisibility(btnProfile);
		click(btnProfile);
	}

	public void veificationFailed() {
		try {
			waitVisibility(txtVerificationFailedMsg);
			getReport("info", "Login Failed Successfully Passed");
		} catch (Exception e) {
			getReport("fail", "Login success");
		}
	}

}
