package testScripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.CreateAnAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDataPojo;
import util.ExcelOperation;

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
	
	//Create Account data from Excel with 17 Parameter as method parameter
	
	@Test(dataProvider = "createAccountDataFromExcel")
	public void createAccountWithExcelDataTest(String gender, String firstName, String lastName,
			String password, String days, String months, String year, String company,
			String address1, String city, String state, String postCode, String other, 
			String homePhone, String mobilePhone, String alliasAddress, String email) {
		
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailAddress(email);
		CreateAnAccountPage createAnAccountPage =  authenticationPage.clickOnCreateAnAccount();
		
		CreateAccountDataPojo createAccountDataPojo = new CreateAccountDataPojo();
		createAccountDataPojo.setMale(true);
		createAccountDataPojo.setFirstName(firstName);
		createAccountDataPojo.setLastName(lastName);
		createAccountDataPojo.setPassword(password);
		createAccountDataPojo.setDays(days);
		createAccountDataPojo.setMonth(months);
		createAccountDataPojo.setYear(year);
		createAccountDataPojo.setCompany(company);
		createAccountDataPojo.setAddress1(address1);
		createAccountDataPojo.setCity(city);
		createAccountDataPojo.setState(state);
		createAccountDataPojo.setPostCode(postCode);
		createAccountDataPojo.setOther(other);
		createAccountDataPojo.setHomeNumber(homePhone);
		createAccountDataPojo.setMobileNumber(mobilePhone);
		
		createAnAccountPage.enterAccountDetails(createAccountDataPojo);
		
		MyProfilePage myProfilePage = createAnAccountPage.clickOnRegistration();
		String actualHeaderText = myProfilePage.getHeaderText();
		String expectedHeadertext = firstName +" "+lastName;
		System.out.println("STEP : Verify Header"); 
		Assert.assertEquals(actualHeaderText, expectedHeadertext, "TestCase fail >> Header text verification fails");
	}
	
	@DataProvider(name = "createAccountDataFromExcel")
	public String[][] createAccountData() throws IOException {
		return ExcelOperation.getExcelData("Demo.xlsx", "Data");
	}
	
	//Create Account data from Pojo
	@Test(dataProvider = "createAccountDataFromPojo")
	public void createAccountWithExcelDataPojoTest(CreateAccountDataPojo createAccountDataPojo) {
		
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		authenticationPage.enterEmailAddress(createAccountDataPojo.getEmail());
		
		CreateAnAccountPage createAnAccountPage =  authenticationPage.clickOnCreateAnAccount();
		
		createAnAccountPage.enterAccountDetails(createAccountDataPojo);
		
		MyProfilePage myProfilePage = createAnAccountPage.clickOnRegistration();
		String actualHeaderText = myProfilePage.getHeaderText();
		String expectedHeadertext = createAccountDataPojo.getFirstName() +" "+createAccountDataPojo.getLastName();
		System.out.println("STEP : Verify Header"); 
		Assert.assertEquals(actualHeaderText, expectedHeadertext, "TestCase fail >> Header text verification fails");
	}
	
	@DataProvider(name = "createAccountDataFromPojo")
	public Object[][] createAccountDataWithPojo() throws IOException {
		
		String[][] data = ExcelOperation.getExcelData("Demo.xlsx", "Data");
		Object[][] dataOutput = new Object[data.length][1];
		CreateAccountDataPojo createAccountDataPojo = new CreateAccountDataPojo();
		/*boolean gender;
		if(data[0][0].equalsIgnoreCase("Male")) {
			gender = true;
		}else {
			gender = false;
		}*/
		
		boolean gender = data[0][0].equalsIgnoreCase("Male") ? true : false;  	//Alternative code for ForLoop
		createAccountDataPojo.setMale(gender);
		createAccountDataPojo.setFirstName(data[0][1]);
		createAccountDataPojo.setLastName(data[0][2]);
		createAccountDataPojo.setPassword(data[0][3]);
		createAccountDataPojo.setDays(data[0][4]);
		createAccountDataPojo.setMonth(data[0][5]);
		createAccountDataPojo.setYear(data[0][6]);
		createAccountDataPojo.setCompany(data[0][7]);
		createAccountDataPojo.setAddress1(data[0][8]);
		createAccountDataPojo.setCity(data[0][9]);
		createAccountDataPojo.setState(data[0][10]);
		createAccountDataPojo.setPostCode(data[0][11]);
		createAccountDataPojo.setOther(data[0][12]);
		createAccountDataPojo.setHomeNumber(data[0][13]);
		createAccountDataPojo.setMobileNumber(data[0][14]);
		createAccountDataPojo.setEmail(data[0][16]);
		dataOutput[0][0] = createAccountDataPojo;
		return dataOutput;
	}
}
