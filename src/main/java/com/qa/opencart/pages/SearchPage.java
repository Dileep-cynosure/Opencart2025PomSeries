package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchPage {
	WebDriver driver;
	ElementUtil util;

	By searchResults = By.xpath("//*[@class='product-thumb']");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public int searchCount() {

		List<WebElement> productList = util.waitForVisibilityOfElemenetsLocated(searchResults,
				TimeUtil.DEFAULT_TIMEOUT_TIME);
		return productList.size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		
		util.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

	
}
