package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {
	protected static WebDriver driver;
	
	public static void start() {
		System.out.println("STEP : Launch browser");
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("STEP : Redirect to Automation practice");
		driver.get("http://automationpractice.com/");
		driver.manage().window().maximize();
	}
	
	public static void close() {
		System.out.println("STEP : Close browser");
		driver.close();
	}
}
