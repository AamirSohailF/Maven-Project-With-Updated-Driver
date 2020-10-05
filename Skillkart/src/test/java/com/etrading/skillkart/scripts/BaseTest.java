package com.etrading.skillkart.scripts;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.etrading.skillkart.generic.AutoConstants;
import com.etrading.skillkart.generic.TakeScreenShotLib;
import com.etrading.skillkart.generic.WebActionUtil;
import com.etrading.skillkart.pages.HomePage;
import com.etrading.skillkart.pages.LoginPage;

public class BaseTest implements AutoConstants
{
	public WebDriver driver;
	WebActionUtil webActionUtil;
	
	@Parameters({"appUrl","browser","implicit","explicit"})
	@BeforeClass
	public void openApp(@Optional(DEFAULT_URL)String appUrl,
						@Optional(DEFAULT_BROWSER)String browser,
						@Optional(ITO)String implicit,
						@Optional(ETO)String explicit)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty(CHROME_KEY, CHROME_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);	
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty(GECKO_KEY, GECKO_PATH);
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		}
		else
		{
			throw new IllegalArgumentException("Only Chrome and Firefox is Supported");
		}
		driver.manage().window().maximize();
		long iTO=Long.parseLong(implicit);
		long eTO=Long.parseLong(explicit);
		driver.manage().timeouts().implicitlyWait(iTO,TimeUnit.SECONDS);
		driver.get(appUrl);
		webActionUtil = new WebActionUtil(driver, eTO);
	}
	
	@Parameters({"un","pwd"})
	@BeforeMethod
	public void loginToApp(@Optional(DEFAULT_USERNAME)String un,
						   @Optional(DEFAULT_PASSWORD)String pwd)
	{
		LoginPage lp = new LoginPage(driver, webActionUtil);
		lp.login(un, pwd);
	}
	
	@AfterMethod
	public void logout(ITestResult result)
	{
		try
		{
			HomePage hp = new HomePage(driver, webActionUtil);
			hp.logout();
		}
		catch(Exception e)
		{
			
		}
		String testName = result.getName();
		int status=result.getStatus();
		if(status==ITestResult.FAILURE)
		{
			Reporter.log(TakeScreenShotLib.getScreenShot(driver, IMAGE_PATH, testName), true);
		}
	}
	
	@AfterClass(alwaysRun=true)
	public void closeApp()
	{
		driver.quit();
	}
}
