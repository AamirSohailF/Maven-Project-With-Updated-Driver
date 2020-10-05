package com.etrading.skillkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.etrading.skillkart.generic.WebActionUtil;

public class HomePage extends BasePage
{
	//Create the WebElements
	@FindBy(linkText="Women")
	private WebElement womenLink;
		
	@FindBy(xpath="(//a[.='Dresses'])[2]")
	private WebElement dressesLink;
	
	@FindBy(xpath="(//a[.='T-shirts'])[2]")
	private WebElement tShirtsLink;
	
	@FindBy(partialLinkText="Sign out")
	private WebElement signOutLink;
	
	//Constructor
	public HomePage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
	
	//Action Methods
	public CategoryPage clickOnMenu(String menuName)
	{
		menuName=menuName.toLowerCase();
		switch(menuName)
		{
			case "dresses":webActionUtil.click(dressesLink);
						   break;
			case "women":webActionUtil.click(womenLink);
						 break;
			case "t-shirts":webActionUtil.click(tShirtsLink);
							break;
		}
		return new CategoryPage(driver,webActionUtil);
	}
	
	public void logout()
	{
		webActionUtil.click(signOutLink);
	}
}
