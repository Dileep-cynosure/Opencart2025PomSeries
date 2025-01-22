package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	WebDriver driver;
	ElementUtil util;
	Map<String, String> prodMap;
	By ProductName = By.xpath("//*[@id='content']//h1");
	By imagesCount = By.cssSelector("div#content .thumbnail");
	By productMetaData = By.xpath("(//div[@id = 'content']//ul[@class='list-unstyled'])[1]/li");
	By priceMetaData = By.xpath("(//div[@id = 'content']//ul[@class='list-unstyled'])[2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public boolean isProductDisplayed() {
		return util.isElementDisplayed(ProductName);
	}

	public String productHeader() {
		String headertext =util.doGetText(ProductName);
		System.out.println(headertext);
		return headertext;
	}

	public int prodImageCount() {
		return util.getElements(imagesCount).size();
	}

	public Map<String, String> getProductInfo() {
		prodMap = new HashMap<String, String>();
		prodMap.put("product", productHeader());		
		prodMap.put("prodImagesCount", String.valueOf(prodImageCount()));
		getproductMetaData();
		getProdPriceList();
		return prodMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getproductMetaData() {
		prodMap = new HashMap<String, String>();
		List<WebElement> prodMetaData = util.getElements(productMetaData);
		for (WebElement e : prodMetaData) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			prodMap.put(key, value);
		}
	}

//	$2,000.00
//	Ex Tax: $2,000.00
	private void getProdPriceList() {

		List<WebElement> prodMetaData = util.getElements(priceMetaData);
		String productPrice = prodMetaData.get(0).getText();
		prodMap.put("productprice", productPrice);
		String exTaxprice = prodMetaData.get(1).getText().split(":")[1].trim();
		prodMap.put("exTaxprice", exTaxprice);
	}

}
