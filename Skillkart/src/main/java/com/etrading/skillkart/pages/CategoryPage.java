package com.etrading.skillkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.etrading.skillkart.generic.WebActionUtil;

public class CategoryPage extends BasePage
{
	
	String quickViewXpath = "//a[contains(.,'Quick view') and contains(@href,'id_product=pid')]";
	
	//Create the WebElements
	@FindBy(xpath="//ul[@class='product_list grid row']/li//a[@class='product_img_link']")
	private List<WebElement> productsList;
	
	//Create the WebElements
	@FindBy(xpath="//a[contains(.,'Quick view')]")
	private List<WebElement> productsListQuickView;
	
	
	//Constructor
	public CategoryPage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
			
	//Action Method
	public ProductDetailPage clickOnProduct(String productId)
	{
		productId = "id_product="+productId;
		for(WebElement product:productsList)
		{
			if(product.getAttribute("href").contains(productId))
			{
				webActionUtil.jsClick(product);
				return new ProductDetailPage(driver, webActionUtil);
			}
		}
		return null;
	}
	
	public ProductDetailPage OpenProductInQuickView(String productId)
	{
		quickViewXpath = quickViewXpath.replace("pid", productId);
		String parentTagXpath = quickViewXpath+"/../..";
		productId = "id_product="+productId;
		for(WebElement pProduct:productsList)
		{
			if(pProduct.getAttribute("href").contains(productId))
			{
				webActionUtil.moveToElement(driver.findElement(By.xpath(parentTagXpath)));
				webActionUtil.click(driver.findElement(By.xpath(quickViewXpath)));
				webActionUtil.switchToFrame("0");
				return new ProductDetailPage(driver, webActionUtil);
			}			
		}
		
		return null;
	}
	
}
