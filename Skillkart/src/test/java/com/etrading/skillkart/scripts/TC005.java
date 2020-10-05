package com.etrading.skillkart.scripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.etrading.skillkart.generic.ExcelLibrary;
import com.etrading.skillkart.generic.Utilities;
import com.etrading.skillkart.pages.CategoryPage;
import com.etrading.skillkart.pages.HomePage;
import com.etrading.skillkart.pages.OrderDetailPage;
import com.etrading.skillkart.pages.ProductDetailPage;
public class TC005 extends BaseTest
{
	@Test(description="Test With Xml file Parameter")
	public void testAddToKartUsingXML()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		String sheetName = "TC005";
		
		String menuItem = ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 0);
		String productID = Utilities.getIntText(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 1));
		
		int quantity=Utilities.returnInteger(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 2));
		
		String size=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 3);
		String color=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 4);
		
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.OpenProductInQuickView(productID);
		Assert.assertNotEquals(pdp, null);
		pdp.addItemToKart(quantity, size, color);
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed(productID), true);
	}
}
