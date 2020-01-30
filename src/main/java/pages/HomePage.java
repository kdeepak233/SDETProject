package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

	private By txtLogin = By.xpath("//button/span[contains(text(),'Hi, ')]");
	private By btnLogOut = By.xpath("//div[@role='menu']//button[contains(text(),'Log out')]");
	private By linkLogo = By.xpath("//h3[@routerlink='/home']");
	private By linkFurniture= By.xpath("//button[text()=' All Furnitures ']");
	private By linkWishlist= By.xpath("//form/button/span[contains(text(),'Wishlist')]");
	public void verifyLogin() {
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
}
