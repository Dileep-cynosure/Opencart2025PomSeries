package com.qa.opencart.testpages;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.Base;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

//@Listeners({ExtentReportListener.class, TestAllureListener.class, AnnotationTransformer.class})
public class LoginPageTest extends Base {

	@Description("--Login Page Test")
	@Severity(SeverityLevel.MINOR)
	@Owner("DIEEP")
	@Test(priority = 0)
	public void loginpageTitleTest() {
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, "Account Login", AppErrors.TITLE_NOT_FOUND);

	}

	@Test(priority = 1)
	public void loginPageUrlTest() {
		String url = loginPage.getPageUrl();
		Assert.assertTrue(url.contains("opencart"), AppErrors.URL_NOT_FOUND);

	}

	@Test(priority = 2)
	public void forgotPwLinkTest() {

		Assert.assertTrue(loginPage.checkforgotLinkisExist(), AppErrors.ELEMENT_NOT_FOUND);

	}

	@Test(priority = 3)
	public void loginPageTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountsPage.accSearchExists(), "search key not exists");
	}
}
