package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegistrationPage {

	WebDriver driver;
	ElementUtil util;

	By regHeader = By.xpath("//div[@id='content']//h1");
	By firstName = By.xpath("//input[@name='firstname']");
	By lastName = By.xpath("//input[@name='lastname']");
	By email = By.xpath("//input[@name='email']");
	By telephone = By.xpath("//input[@name='telephone']");
	By password = By.xpath("//input[@name='password']");
	By confirmPassword = By.xpath("//input[@name='confirm']");
	By subScribeYes = By.xpath("//div[@class='form-group']//label[@class='radio-inline'][1]/input[@type='radio']");
	By subScribeNo = By.xpath("//div[@class='form-group']//label[@class='radio-inline'][2]/input[@type='radio']");
	By privacyPolicy = By.xpath("//div[@class='pull-right']/input[@name='agree']");
	By continueBtn = By.xpath("//div[@class='pull-right']/input[@value='Continue']");
	By successMsg = By.xpath("//div[@id='content']/h1");
	By logOut = By.linkText("Logout");
	By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String regHeader() {

		String text = util.getElement(regHeader).getText();
		return text;
	}

	public boolean userRegister(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		
		util.doSendKeys(this.firstName,firstName, TimeUtil.DEFAULT_TIMEOUT_TIME);
		util.doSendKeys(this.lastName, lastName);
		util.doSendKeys(this.email, email);
		util.doSendKeys(this.telephone, telephone);
		util.doSendKeys(this.password, password);
		util.doSendKeys(this.confirmPassword, password);
		if(subscribe.equalsIgnoreCase("Yes")) {
			util.doClick(subScribeYes, TimeUtil.DEFAULT_MDEIUM_TIME);
		}else {
			util.doClick(subScribeNo);
		}
		
		util.doClick(privacyPolicy);
		util.doClick(continueBtn);
		
		String successText=util.doGetText(successMsg);
		if(successText.contains(AppConstants.USER_REGISTER_SUCCESS)) {
    	util.doClick(logOut);
		util.doClick(registerLink);
		return true;
	    }else {
		return false;
				}
	}

}
