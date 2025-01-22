package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {

	WebDriver driver;
	ElementUtil util;
	private By emailId = By.xpath("//input[@name='email']");
	private By password = By.xpath("//input[@name='password']");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
	private By registerationLink = By.linkText("Register");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	// Action methods

	public String getPageTitle() {
		String title = util.waitForTitleToBe(AppConstants.LOGINPAGE_TITLE, TimeUtil.DEFAULT_TIMEOUT_TIME);
		return title;
	}

	public String getPageUrl() {
		String url = util.waitForURLContains(AppConstants.LOGINPAGE_URL_FRAC_VALUE, 5);
		return url;
	}

	public boolean checkforgotLinkisExist() {
		return util.isElementDisplayed(forgotPwLink);
	}

	public AccountsPage doLogin(String username, String pwd) {
System.out.println("userName: " +username + "password: "+ pwd);
		util.doSendKeys(emailId, username, TimeUtil.DEFAULT_TIMEOUT_TIME);
		util.doSendKeys(password, pwd, TimeUtil.DEFAULT_TIMEOUT_TIME);
		util.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	public RegistrationPage navigatetoRegister() {
		util.doClick(registerationLink);
		return new RegistrationPage(driver) ;
	}

}
