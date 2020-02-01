package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WishlistPage extends BasePage {

	private By nameProduct = By.xpath("//div[@class='name']/b");

	public void verifyProduct() {
		List<WebElement> list = getDriver().findElements(nameProduct);
		for (int i = 0; i < list.size(); i++) {
			if (getText(list.get(0)).equals(getData("productName")))
				getReport("info", "Product matched Successfully");
			else
				getReport("Fail", "Product did not matched");
		}
	}
}
