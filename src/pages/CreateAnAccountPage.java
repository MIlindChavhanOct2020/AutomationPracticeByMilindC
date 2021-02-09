package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;
import pojo.CreateAccountDataPojo;

public class CreateAnAccountPage extends PredefinedActions{
	
	WebDriverWait wait;
	
	private void selectGender(boolean isMale) {
		wait = new WebDriverWait(driver,30);
		WebElement element = isMale ? wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id_gender1']")))
									: wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='id_gender2']")));
		element.click();
	}
	
	private void enterFirstName(String firstName) {
		System.out.println("STEP : Enter first name");
		if(firstName != null) {
			driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(firstName);
		}else {
			System.out.println("First name is blank");
		}
	}
	
	private void enterLastName(String lastName) {
		System.out.println("STEP : Enter Last Name");
		if(lastName != null) {
			driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(lastName);
		}else {
			System.out.println("Last name is blank");
		}
	}
	
	private void enterPassword(String password) {
		System.out.println("STEP : Enter password");
		if(password != null) {
			driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
		}else {
			System.out.println("Password is blank");
		}
	}
	
	private void selectDays(String days) {
		System.out.println("STEP : Select Date for DOB");
		if(days != null) {
			WebElement DOB_Days = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-days")));
			DOB_Days.click();
			Select s = new Select(driver.findElement(By.id("days")));
			s.selectByValue(days);
		}else {
			System.out.println("Day is blank");
		}
	}
	
	private void selectMonth(String month) {
		System.out.println("STEP : Select Month for DOB");
		if(month != null) {
			WebElement DOB_Year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-months")));
			DOB_Year.click();
			Select s = new Select(driver.findElement(By.id("months")));
			s.selectByValue(month);
		}else {
			System.out.println("Month is blank");
		}
	}
	
	private void selectYear(String year) {
		System.out.println("STEP : Select Year for DOB");
		if(year != null) {
			WebElement DOB_Year = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-years")));
			DOB_Year.click();
			Select s = new Select(driver.findElement(By.id("years")));
			s.selectByValue(year);
		}else {
			System.out.println("Year is blank");
		}
	}
	
	private void enterCompany(String company) {
		System.out.println("STEP : Enter company name");
		if(company != null) {
			driver.findElement(By.xpath("//input[@id='company']")).sendKeys(company);
		}else {
			System.out.println("Company is blank");
		}
	}
	
	private void enterAddress(String address) {
		System.out.println("STEP : Enter address");
		if(address != null) {
			driver.findElement(By.id("address1")).sendKeys(address);
		}else {
			System.out.println("Address is blank");
		}
	} 
	
	private void enterCity(String city) {
		System.out.println("STEP : Enter City name");
		if(city != null) {
			driver.findElement(By.id("city")).sendKeys(city);
		}else {
			System.out.println("City is blank");
		}
	}
	
	private void selectState(String state) {
		System.out.println("STEP : select state from dropdown");
		if(state != null) {
			WebElement stateDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-id_state")));
			stateDropDown.click();
			Select s = new Select(driver.findElement(By.id("id_state")));
			s.selectByVisibleText(state);
		}else {
			System.out.println("State is blank");
		}
	}
	
	private void enterPostCode(String postCode) {
		System.out.println("STEP : Enter post code");
		if(postCode != null) {
			driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys(postCode);
		}else {
			System.out.println("Post code is blank");
		}
	}
	
	private void enterAdditionalInfo(String additionalInformation) {
		System.out.println("STEP : Enter Additional Information");
		if(additionalInformation != null) {
			driver.findElement(By.id("other")).sendKeys(additionalInformation);
		}else {
			System.out.println("Additional information is blank");
		}
	}
	
	private void enterHomePhone(String homePhone) {
		System.out.println("STEP : Enter Home phone");
		if(homePhone != null) {
			driver.findElement(By.id("phone")).sendKeys(homePhone);
		}else {
			System.out.println("Home phone is blnak");
		}
	}
	
	private void enterMobileNumber(String mobileNumber) {
		System.out.println("STEP : Enter mobile phone");
		if(mobileNumber != null) {
			driver.findElement(By.id("phone_mobile")).sendKeys(mobileNumber);
		}else {
			System.out.println("Mobile number is blank");
		}
		
	}
	
	public void enterAccountDetails(CreateAccountDataPojo createAccountDataPojo) {
		selectGender(createAccountDataPojo.isMale());		
		enterFirstName(createAccountDataPojo.getFirstName());
		enterLastName(createAccountDataPojo.getLastName());
		enterPassword(createAccountDataPojo.getPassword());
		selectDays(createAccountDataPojo.getDays());
		selectMonth(createAccountDataPojo.getMonth());
		selectYear(createAccountDataPojo.getYear());
		enterCompany(createAccountDataPojo.getCompany());
		enterAddress(createAccountDataPojo.getAddress1());
		enterCity(createAccountDataPojo.getCity());
		selectState(createAccountDataPojo.getState());
		enterPostCode(createAccountDataPojo.getPostCode());
		enterAdditionalInfo(createAccountDataPojo.getOther());
		enterHomePhone(createAccountDataPojo.getHomeNumber());
		enterMobileNumber(createAccountDataPojo.getMobileNumber());
	}
	
	public MyProfilePage clickOnRegistration() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		System.out.println("STEP : Click on Register button");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='submitAccount']"))).click();
		return new MyProfilePage();
	}
	
	public List<String> getErrorMessage() {
		List<WebElement> errorMessageElement = driver.findElements(By.xpath("//div[@class = 'alert alert-danger']//ol/li"));
		List<String> errorMessageText = new ArrayList<String>();
		String errorMessageHeader = driver.findElement(By.xpath("//div[@class = 'alert alert-danger']/p")).getText();
		errorMessageText.add(errorMessageHeader);
		for(WebElement element : errorMessageElement) {
			errorMessageText.add(element.getText());
		}		 
		return errorMessageText;
	}
}
