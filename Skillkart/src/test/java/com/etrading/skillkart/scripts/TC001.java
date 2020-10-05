package com.etrading.skillkart.scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.etrading.skillkart.pages.CategoryPage;
import com.etrading.skillkart.pages.HomePage;
import com.etrading.skillkart.pages.OrderDetailPage;
import com.etrading.skillkart.pages.ProductDetailPage;
//No QuickView Used
public class TC001 extends BaseTest
{
	@Test(description="Verify Whether the Added Product is displayed in ODP")
	public void testItemAddedToKart()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		CategoryPage category = hp.clickOnMenu("Dresses");
		ProductDetailPage pdp = category.clickOnProduct("5");
		pdp.increaseQuantity(4);
		pdp.decreaseQuantity(1);
		pdp.selectSize("L");
		pdp.selectColor("Blue");
		pdp.clickOnAddToKart();
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed("5"), true);
	}
}
