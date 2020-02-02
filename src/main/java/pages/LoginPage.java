package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
	private By btnSignIn = By.xpath("//button/span[contains(text(),' Support ')]/../following-sibling::button/span[contains(text(),'Sign In')]");
	private By txtEmailId = By.xpath("//h2[contains(text(),'Sign in')]/following-sibling::div//input[@id='emailId']");
	private By txtPassword = By.xpath("//h2[contains(text(),'Sign in')]/following-sibling::div//input[@id='password']");
	private By btnLogin = By.xpath("//h2[contains(text(),'Sign in')]/following-sibling::div//button[@type='submit']");
	
	public void enterLoginCredentials() {

		String emailId = getData("userEmail");
		String passWord = getData("password");

		waitVisibility(btnSignIn);
		click(btnSignIn);
		getReport("info", "Clicked on Sign In Button");
		waitVisibility(txtEmailId);
		writeText(txtEmailId, emailId);
		writeText(txtPassword, passWord);
		getReport("info", "Credentials Entered");
		click(btnLogin);
		getReport("info", "User Logged In");
	}

	public void verifyLogOut() {
		try {
			waitVisibility(btnSignIn);
			getReport("info", "Logged out Successfully");
		} catch (Exception e) {
			getReport("fail", "Logging out Failed");
		}
	}

}
