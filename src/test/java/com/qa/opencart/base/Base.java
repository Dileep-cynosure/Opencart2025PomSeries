package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchPage;

public class Base {

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchPage searchPage;
	protected ProductInfoPage prodInfoPage;
	protected SoftAssert softAssert;
	protected RegistrationPage regPage;
	@Parameters({"browser"} )
	@BeforeTest()
	public void setup(@Optional("firefox") String browserName) {
		df = new DriverFactory();
		prop = df.init_prop();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.init_browser(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

}
