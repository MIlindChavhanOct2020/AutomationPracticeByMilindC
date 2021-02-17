package testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyProfilePage;
import util.ExcelOperation;

public class LoginAccountTest extends TestBase{
	
	@Test(dataProvider = "LoginData")
	public void validLogin(String emailAddress, String password) {
		
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		MyProfilePage myProfilePage = authenticationPage.doLogin(emailAddress, password);
		
		System.out.println("STEP : Verify user full name on My profile page");
		
		String actualUserFullName = myProfilePage.getHeaderText();
		String expectedUserFullName = "harsh patel";
		
		Assert.assertEquals(expectedUserFullName, actualUserFullName);
	}
	
	@DataProvider(name = "LoginData")
	public String[][] dataForLogin() throws IOException {
		return ExcelOperation.getExcelData("Demo.xlsx", "LoginData");
	}

}
