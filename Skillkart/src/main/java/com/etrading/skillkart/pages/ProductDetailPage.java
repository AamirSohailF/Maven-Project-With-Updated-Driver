package com.etrading.skillkart.pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.etrading.skillkart.generic.Utilities;
import com.etrading.skillkart.generic.WebActionUtil;

public class ProductDetailPage extends BasePage
{
	//Create the WebElements
	@FindBy(id="our_price_display")
	private WebElement unitPrice;
	
	@FindBy(className="icon-minus")
	private WebElement minusIcon;
	
	@FindBy(className="icon-plus")
	private WebElement plusIcon;
	
	@FindBy(id="group_1")
	private WebElement sizeListBox;
	
	@FindBy(xpath="//ul[@id='color_to_pick_list']/li/a")
	private List<WebElement> colorPicker;
	
	@FindBy(name="Submit")
	private WebElement addToKartButton;
	
	@FindBy(xpath="//a[contains(.,'Proceed to checkout')]")
	private WebElement proceedToCheckout;
	
	@FindBy(xpath="//a[@title='Continue shopping']")
	private WebElement continueShopping;
	
	@FindBy(className="cross")
	private WebElement closePopup;
		
	//Constructor
	public ProductDetailPage(WebDriver driver, WebActionUtil webActionUtil) 
	{
		super(driver, webActionUtil);
	}
	
	//Action Methods
	
	public String getProductUnitPrice()
	{
		return webActionUtil.getText(unitPrice);
	}
	
	public void decreaseQuantity(int quantity)
	{
		for(int i=1;i<=quantity;i++)
		{
			webActionUtil.click(minusIcon);
		}		
	}
	
	public void increaseQuantity(int quantity)
	{
		for(int i=1;i<=quantity-1;i++)
		{
			webActionUtil.click(plusIcon);
		}		
	}
	
	public void selectSize(String sizeText)
	{
		webActionUtil.selectByVisibleText(sizeListBox, sizeText);
	}
	
	public void selectColor(String colorText)
	{
		for(WebElement color:colorPicker)
		{
			if(color.getAttribute("name").equalsIgnoreCase(colorText))
			{
				webActionUtil.click(color);
				break;
			}
		}
	}
	
	public void clickOnAddToKart()
	{
		webActionUtil.click(addToKartButton);
	}
	
	public OrderDetailPage clickOnProccedToCheckout()
	{
		driver.switchTo().defaultContent();
		webActionUtil.click(proceedToCheckout);
		return new OrderDetailPage(driver,webActionUtil);
	}
	
	public void clickOnContinueShopping()
	{
		webActionUtil.click(continueShopping);
	}
	
	public void closePopup()
	{
		webActionUtil.click(closePopup);
	}
	
	public void addItemToKart(int quantity, String sizeText, String colorText)
	{
		increaseQuantity(quantity);
		selectSize(sizeText);
		selectColor(colorText);
		clickOnAddToKart();
		Utilities.sleepInSeconds(5);
	}
	
	
	
}
