package com.etrading.skillkart.scripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.etrading.skillkart.generic.ExcelLibrary;
import com.etrading.skillkart.generic.Utilities;
import com.etrading.skillkart.pages.CategoryPage;
import com.etrading.skillkart.pages.HomePage;
import com.etrading.skillkart.pages.OrderDetailPage;
import com.etrading.skillkart.pages.ProductDetailPage;

public class TC006 extends BaseTest
{
	@Test(description="Verify Whether the deleted Product is not displayed in ODP")
	public void testDeleteAddedItemToKart()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		String sheetName = "TC006";
		
		String menuItem = ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 0);
		String productId = Utilities.getIntText(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 1));
		
		int quantity=Utilities.returnInteger(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 2));
		
		String size=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 3);
		String color=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 4);
		
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.clickOnProduct(productId);
		pdp.increaseQuantity(quantity);
		pdp.selectSize(size);
		pdp.selectColor(color);
		pdp.clickOnAddToKart();
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed(productId), true);
		odp.deleteProduct(productId);
		Assert.assertEquals(odp.isProductNotDisplayed(productId), true);
	}
}
