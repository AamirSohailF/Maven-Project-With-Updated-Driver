package com.etrading.skillkart.pages;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.etrading.skillkart.generic.WebActionUtil;

public class OrderDetailPage extends BasePage
{
	//Create the WebElements
	@FindBy(xpath="//tbody/tr/td[1]/a")
	private List<WebElement> productsList;
		
	String deleteIconXpath = "//a[contains(@href,'id_product=pid')]/../..//i[@class='icon-trash']";
		
	//Constructor
	public OrderDetailPage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
	
	public String getODPUnitPrice(String productId)
	{
		String unitpriceXpath = "//a[contains(@href,'id_product=pid')]/"
				+ "../..//span[contains(@id,'product_price')]/span[@class='price']";
		unitpriceXpath=unitpriceXpath.replace("pid",productId);
		if(isProductDisplayed(productId))
		{
			return webActionUtil.getText(driver.findElement(By.xpath(unitpriceXpath)));	
		}
		return null;
	}
	
	
	//Action Methods
	public boolean verifyUnitPrice(String productId, String PDPUnitPrice)
	{
		String unitpriceXpath = "//a[contains(@href,'id_product=pid')]/"
				+ "../..//span[contains(@id,'product_price')]/span[@class='price']";
		unitpriceXpath=unitpriceXpath.replace("pid",productId);
		if(isProductDisplayed(productId))
		{
			String ODPUnitPrice=webActionUtil.getText(driver.findElement(By.xpath(unitpriceXpath)));
			return PDPUnitPrice.equalsIgnoreCase(ODPUnitPrice);
		}
		return false;
	}
	
	
	public boolean isProductDisplayed(String productId)
	{
		productId = "id_product="+productId;
		for(WebElement product:productsList)
		{
			if(product.getAttribute("href").contains(productId))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isProductNotDisplayed(String productId)
	{
		String xp = "//a[contains(@href,'id_product="+productId+"')]";
		try
		{
			return webActionUtil.waitUntilElementNotDisplayed(By.xpath(xp));
		}
		catch(NoSuchElementException e)
		{
			return true;
		}
	}
	
	public void deleteProduct(String productId)
	{
		if(isProductDisplayed(productId))
		{
			deleteIconXpath=deleteIconXpath.replace("pid", productId);
			webActionUtil.click(driver.findElement(By.xpath(deleteIconXpath)));
		}
	}
	
}
