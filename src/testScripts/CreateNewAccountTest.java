package testScripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.CreateAnAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDataPojo;

public class CreateNewAccountTest extends TestBase{
	
	@Test(priority=1)
	void createAccountTest() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailAddress("abc008@gmail.com");
		CreateAnAccountPage createAnAccountPage =  authenticationPage.clickOnCreateAnAccount();
		
		CreateAccountDataPojo createAccountDataPojo = new CreateAccountDataPojo();
		createAccountDataPojo.setMale(true);
		createAccountDataPojo.setFirstName("Milind");
		createAccountDataPojo.setLastName("Chavhan");
		createAccountDataPojo.setPassword("Automation123");
		createAccountDataPojo.setDays("12");
		createAccountDataPojo.setMonth("1");
		createAccountDataPojo.setYear("1990");
		createAccountDataPojo.setCompany("CloudCodes");
		createAccountDataPojo.setAddress1("6/1 Abhinav Nagar");
		createAccountDataPojo.setCity("Pune");
		createAccountDataPojo.setState("Delaware");
		createAccountDataPojo.setPostCode("27101");
		createAccountDataPojo.setOther("NA");
		createAccountDataPojo.setHomeNumber("9876543210");
		createAccountDataPojo.setMobileNumber("9876543210");
		
		createAnAccountPage.enterAccountDetails(createAccountDataPojo);
		
		MyProfilePage myProfilePage = createAnAccountPage.clickOnRegistration();
		String actualHeaderText = myProfilePage.getHeaderText();
		String expectedHeadertext = "Milind Chavhan";
		System.out.println("STEP : Verify Header"); 
		Assert.assertEquals(actualHeaderText, expectedHeadertext, "TestCase fail >> Header text verification fails");
	}
	
	@Test
	public void validationForErrorMessages() {
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailAddress("abc007@gmail.com");
		CreateAnAccountPage createAnAccountPage =  authenticationPage.clickOnCreateAnAccount();
		
		CreateAccountDataPojo createAccountDataPojo = new CreateAccountDataPojo();
		createAccountDataPojo.setMale(true);
		
		createAnAccountPage.enterAccountDetails(createAccountDataPojo);
		
		createAnAccountPage.clickOnRegistration();
		List<String> actualErrorMessageList = createAnAccountPage.getErrorMessage();
		List<String> expectedErrorMessageList = new ArrayList<String>();
		expectedErrorMessageList.add("There are 8 errors");
		expectedErrorMessageList.add("You must register at least one phone number.");
		expectedErrorMessageList.add("lastname is required.");
		expectedErrorMessageList.add("firstname is required.");
		expectedErrorMessageList.add("passwd is required.");
		expectedErrorMessageList.add("address1 is required.");
		expectedErrorMessageList.add("city is required.");
		expectedErrorMessageList.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
		expectedErrorMessageList.add("This country requires you to choose a State.");
		
		System.out.println("DEBUG - Expected Error messages");
		System.out.println(expectedErrorMessageList);
		
		System.out.println("DEBUG - Actaul Error Message");
		System.out.println(actualErrorMessageList);
		
		Assert.assertEquals(actualErrorMessageList, expectedErrorMessageList);
	}
}
