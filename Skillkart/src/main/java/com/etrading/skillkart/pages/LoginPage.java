package com.etrading.skillkart.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.etrading.skillkart.generic.WebActionUtil;

public class LoginPage extends BasePage
{
	//Create the WebElements
	@FindBy(partialLinkText="Sign in")
	private WebElement signInLink;
	
	@FindBy(id="email")
	private WebElement username;
	
	@FindBy(id="passwd")
	private WebElement password;
	
	@FindBy(id="SubmitLogin")
	private WebElement signInButton;
	
	@FindBy(linkText="Forgot your password?")
	private WebElement forgotPasswordLink;
	
	@FindBy(id="email_create")
	private WebElement createEmail;
	
	@FindBy(id="SubmitCreate")
	private WebElement createAccountButton;
	
	//Constructor
	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
	
	//Create the Action Methods
	public HomePage login(String un, String pwd)
	{
		webActionUtil.click(signInLink);
		webActionUtil.enterKeys(un, username);
		webActionUtil.enterKeys(pwd, password);
		webActionUtil.click(signInButton);
		return new HomePage(driver,webActionUtil);
	}
	
	public void clickForgotPassword()
	{
		webActionUtil.click(forgotPasswordLink);
	}
	
	public void createAccount(String newEmailId)
	{
		webActionUtil.enterKeys(newEmailId, createEmail);
		webActionUtil.click(createAccountButton);
	}
}









