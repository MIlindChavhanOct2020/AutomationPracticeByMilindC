package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class HomePage extends PredefinedActions{
	
	public AuthenticationPage clickOnSignIn() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("STEP : Click on sign in button");
		WebElement signInElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='header_user_info']/child::a")));
		signInElement.click();
		return new AuthenticationPage();
	}

}
