package com.etrading.skillkart.generic;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class WebActionUtil
{
	WebDriver driver;
	JavascriptExecutor js;
	Actions actions;
	WebDriverWait wait;
	public WebActionUtil(WebDriver driver, long eTo) 
	{
		this.driver=driver;
		js = (JavascriptExecutor)driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, eTo);
	}
	
	public void enterKeys(String keyToEnter, WebElement target)
	{
		target.clear();
		target.sendKeys(keyToEnter);
	}
	
	public void click(WebElement target)
	{
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(target));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		scrollToView(target);
		boolean isClicked = false;
		Exception exp = null;
		int i;
		for(i=1;i<=3;i++)
		{
			try
			{
				target.click();
				isClicked=true;
				break;
			}
			catch(Exception e)
			{
				exp=e;
			}
		}
		if(exp!=null && i>3)
		{
			
		}
		if(!isClicked)
		{
			jsClick(target);
		}
			
	}
	
	public boolean waitUntilElementNotDisplayed(By target)
	{
		try
		{
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(target));
		}
		catch(TimeoutException e)
		{
			return false;
		}
	}
	
	public void jsClick(WebElement target)
	{
		js.executeScript("arguments[0].click();", target);
	}
	
	public void scrollToView(WebElement target)
	{
		js.executeScript("arguments[0].scrollIntoView();", target);
	}
	
	public void jsEnterKeys(String keysToEnter, WebElement target)
	{
		js.executeScript("arguments[0].value="+keysToEnter+";", target);
	}
	
	public void scrollDown(int pixels)
	{
		js.executeScript("window.scrollBy(0,"+pixels+");");
	}
	
	public void scrollUp(int pixels)
	{
		js.executeScript("window.scrollBy(0,-"+pixels+");");
	}
	
	public void selectByVisibleText(WebElement listBox, String text)
	{
		Select s = new Select(listBox);
		s.selectByVisibleText(text);
	}
	
	public void moveToElement(WebElement target)
	{
		scrollToView(target);
		actions.moveToElement(target).perform();
	}
	
	public void moveToElementAndClick(WebElement target)
	{
		scrollToView(target);
		actions.moveToElement(target).perform();
		click(target);
	}
	
	public void dragAndDrop(WebElement source ,WebElement target)
	{
		actions.dragAndDrop(source, target).perform();
	}
	
	public void rightClick(WebElement target)
	{
		actions.contextClick(target).perform();
	}
	
	public void switchToFrame(String idNameOrIndex)
	{
		Utilities.sleepInSeconds(10);
		try
		{
			int index = Integer.parseInt(idNameOrIndex);
			driver.switchTo().frame(index);
		}
		catch(NumberFormatException e) 
		{
			driver.switchTo().frame(idNameOrIndex);
		}
		catch(Exception e)
		{
			
		}
	}

	public String getText(WebElement unitPrice)
	{
		wait.until(ExpectedConditions.visibilityOf(unitPrice));
		return unitPrice.getText();
	}
}







