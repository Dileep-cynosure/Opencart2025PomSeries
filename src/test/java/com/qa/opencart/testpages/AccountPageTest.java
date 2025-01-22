package com.qa.opencart.testpages;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.Base;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.SearchPage;

public class AccountPageTest extends Base {

	@BeforeClass
	public void accsetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accpage_title() {

		String title = accountsPage.getAccTitle();
		System.out.println(title);
		Assert.assertEquals(title, AppConstants.ACCOUNTPAGE_TITLE);
	}

	@Test
	public void accpage_url() {

		String title = accountsPage.getAccUrl();
		Assert.assertTrue(title.contains("route=account/account"), AppConstants.ACCOUNTPAGE_URL_FRAC_VALUE);
	}

	@Test
	public void accPageHeadersTest() {
		List<String> headersList = accountsPage.accPageHeaderslist();
		Assert.assertEquals(headersList, AppConstants.ACC_PAGE_HEADERSLIST);
	}

	@DataProvider
	public Object[][] ProductSearchData() {
		
		return new  Object[][] {
			
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2},
			{"airtel", 0}
			
		};
	}
	
	
	
	@Test(dataProvider="ProductSearchData")
	public void productSearch(String searchKey, int numofProd) {
		SearchPage searchPage = accountsPage.AccPagedoSearch(searchKey);
		Assert.assertEquals(searchPage.searchCount(), numofProd);
	}
}
