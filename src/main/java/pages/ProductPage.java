package pages;

import org.openqa.selenium.By;

public class ProductPage extends BasePage{

	private By btnAddToWishList=By.xpath("//button[text()='Add to Wishlist ']");
	private By nameProduct=By.xpath("//div[@class='float-left']/h1");
	private By priceProduct=By.xpath("//span[@class='float-left'][1]");
	
	public void addToWishList() {
		click(btnAddToWishList);
		getReport("INFO", "Added to Wishlist");
	}
	
	public void recordDetails() {
		setData("productName", getText(nameProduct));
		setData("productprice", getText(priceProduct));
	}

}
