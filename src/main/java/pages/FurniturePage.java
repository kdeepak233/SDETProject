package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FurniturePage extends BasePage{

	private By listFurnitures=By.xpath("//div[@class='furniture-list']//button");
	
	public void selectProduct() {
		waitVisibility(listFurnitures);
		List <WebElement> list=getDriver().findElements(listFurnitures);
		int size=getRandomInteger(list.size()-1);
		click(list.get(size));
		getReport("INFO","clicked on a furniture");
	}

}
