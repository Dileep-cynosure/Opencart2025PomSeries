package com.qa.opencart.testpages;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.Base;

public class ProductInfoTestPage extends Base {

	@BeforeClass
	public void ProdSetup() {

		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] headerData() {
		return new Object[][] {

				{ "macbook", "MacBook Pro" }, { "samsung", "Samsung SyncMaster 941BW" }, { "imac", "iMac" }

		};

	}

	@Test(dataProvider = "headerData")
	public void prodHeaderTest(String searchKey, String selectProd) {
		searchPage = accountsPage.AccPagedoSearch(searchKey);
		prodInfoPage = searchPage.selectProduct(selectProd);
		String header = prodInfoPage.productHeader();
		Assert.assertEquals(header, selectProd);
	}

	@DataProvider
	public Object[][] imgCountData() {
		return new Object[][] {

				{ "macbook", "MacBook Pro", 4 }, { "samsung", "Samsung SyncMaster 941BW", 1 }, { "imac", "iMac", 3 }

		};

	}

	@Test(dataProvider = "imgCountData")
	public void prodImgCount(String searchKey, String selectProd, int expImagesCount) {
		searchPage = accountsPage.AccPagedoSearch(searchKey);
		prodInfoPage = searchPage.selectProduct(selectProd);
		int imgCount = prodInfoPage.prodImageCount();
		Assert.assertEquals(imgCount, expImagesCount);
	}

	@Test()
	public void productInfoTest() {
		
		searchPage = accountsPage.AccPagedoSearch("macbook");
		prodInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> productDetails = prodInfoPage.getProductInfo();
		System.out.println(productDetails);
		softAssert.assertEquals(productDetails.get("product"), "MacBook Pro");
		Assert.assertEquals(productDetails.get("Brand"), "Apple");
		Assert.assertEquals(productDetails.get("Availability"), "In Stock");
		Assert.assertEquals(productDetails.get("exTaxprice"), "$2,000.00");
		Assert.assertEquals(productDetails.get("Product Code"), "Product 18");
		Assert.assertEquals(productDetails.get("Reward Points"), "800");
		Assert.assertEquals(productDetails.get("productprice"), "$2,000.00");
		
	}
	@Test
	public void header() {
		searchPage = accountsPage.AccPagedoSearch("macbook");
		prodInfoPage = searchPage.selectProduct("MacBook Pro");
		String prodHeader =prodInfoPage.productHeader();
		System.out.println(prodHeader);
	}
}
