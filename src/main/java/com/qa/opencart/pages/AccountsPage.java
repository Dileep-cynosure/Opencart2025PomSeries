package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil util;
	By headersList = By.xpath("//div[@id='content']/h2");

	By searchBar = By.xpath("//input[@name='search']");
	By searchBtn = By.xpath("//*[@id=\"search\"]/span/button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String getAccTitle() {
		String title = util.waitForTitleToBe(AppConstants.ACCOUNTPAGE_TITLE, TimeUtil.DEFAULT_TIMEOUT_TIME);
		return title;
	}

	public String getAccUrl() {
		String url = util.waitForURLContains(AppConstants.ACCOUNTPAGE_URL_FRAC_VALUE, 5);
		return url;
	}

	public boolean accSearchExists() {
		return util.doIsDisplayed(searchBtn);
	}

	public List<String> accPageHeaderslist() {
		List<WebElement> headers = util.waitForVisibilityOfElemenetsLocated(headersList, TimeUtil.DEFAULT_TIMEOUT_TIME);
		List<String> headersList = new ArrayList<String>();
		for (WebElement e : headers) {
			String text = e.getText();
			headersList.add(text);
		}

		return headersList;
	}

	
	
	
	
	public SearchPage AccPagedoSearch(String searchKey) {

		if (accSearchExists()) {
			util.doSendKeys(searchBar, searchKey);
			util.doClick(searchBtn);
			return new SearchPage(driver);
		} else {
			System.out.println("seach icon not present on the page");
			return null;
		}
	}

}
