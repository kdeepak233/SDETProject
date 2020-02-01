package pages;

import org.openqa.selenium.By;

public class ProductPage extends BasePage{

	private By btnAddToWishList=By.xpath("//button[text()='Add to Wishlist ']");
	private By nameProduct=By.xpath("//div[@class='float-left']/h1");
	private By priceProduct=By.xpath("//span[@class='float-left'][1]");
	private By successMessage=By.xpath("//div[@aria-label='Successfully added product to your wishlist']");
	public void addToWishList() {
		click(btnAddToWishList);
		getReport("INFO", "Added to Wishlist");
		waitTillElementNotVisible(successMessage);
	}
	
	public void recordDetails() {
		setData("productName", getText(nameProduct));
		setData("productprice", getText(priceProduct));
	}

}
