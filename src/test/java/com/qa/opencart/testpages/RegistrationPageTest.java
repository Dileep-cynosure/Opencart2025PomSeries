package com.qa.opencart.testpages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.Base;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends Base {

	@BeforeClass

	public void regSetup() {
		regPage = loginPage.navigatetoRegister();

	}

	@DataProvider
	public Object[][] userRegTestData() {
		return new Object[][] { { "Arti", "automation", "9876787656", "arti@123", "yes" },
				{ "Praful", "automation", "9876787690", "praful@123", "no" },
				{ "Madhu", "automation", "9876787876", "madhu@123", "yes" } };
	}

	@DataProvider
	public Object[][] userRegData() {
		return ExcelUtil.getTestData(AppConstants.REG_ACC_DATA_SHEET);
	}

	@DataProvider
	public Object[][] useCsvRegData() {
		return CsvUtil.RegisterData(AppConstants.REG_ACC_CSV_DATA_SHEET);
	}

	
	@Test(dataProvider = "userRegData")
	public void userRegisterationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(
				regPage.userRegister(firstName, lastName, StringUtils.getRandomEmail(), telephone, password, subscribe),
				AppErrors.USER_REG_NOT_DONE);
	}
	
	@Test(dataProvider = "useCsvRegData")
	public void userRegCsvTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(
				regPage.userRegister(firstName, lastName, StringUtils.getRandomEmail(), telephone, password, subscribe),
				AppErrors.USER_REG_NOT_DONE);
	}

}
