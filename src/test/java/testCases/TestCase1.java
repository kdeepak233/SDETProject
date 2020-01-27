package testCases;

import org.testng.annotations.Test;

import utils.HelperTest;

public class TestCase1 extends HelperTest {
	 @Test (groups="fb",enabled=false)
	 	public void te1() {
		 	setTestSetId("TC12345");
		 	System.out.print(getTestData("WebUrl"));
		 	driver.get(getTestData("WebUrl"));
		 	getReport("info","browserurl");
	 	}
	
	@Test (groups="fb" )
		public void te2() {
			setTestSetId("TC123");
	    	driver.get("https://www.google.com/search?sxsrf=ACYBGNTJAQPJVChUVvgabVftZ404SpRVZg%3A1580060668941&source=hp&ei=_M8tXt6RN-GC4-EPrL-bgAU&q=mind&oq=mind&gs_l=psy-ab.3..35i39l2j0i67l5j0i131j0i67j0.844.3421..4450...0.0..0.134.504.0j4......0....1..gws-wiz.AJrp0KiFmR4&ved=0ahUKEwje_5LX6KHnAhVhwTgGHazfBlAQ4dUDCAY&uact=5");
	    	getReport("info","browser");
	    }
}