package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class AuthenticationPage extends PredefinedActions{
	
	public void enterEmailAddress(String email) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("STEP : Enter Email");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email_create']"))).sendKeys(email);
	}
	
	public CreateAnAccountPage clickOnCreateAnAccount() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("STEP : Click to create an account button");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='SubmitCreate']"))).click();
		return new CreateAnAccountPage();
	}
	
	public MyProfilePage doLogin(String email, String password) {
		enterLoginEmailAddress(email);
		enterLoginPassword(password);
		clickOnLoginSignIn();
		return new MyProfilePage();
	}
	
	public void enterLoginEmailAddress(String emailAddress) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("STEP : Enter email Address for login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email"))).sendKeys(emailAddress);
	}
	
	public void enterLoginPassword(String password) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("STEP : Enter password for login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#passwd"))).sendKeys(password);
	}
	
	public void clickOnLoginSignIn() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("STEP : Click on sign in button for login");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='SubmitLogin']"))).click();
	}

}
