package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();
	public static String highlight;
	public WebDriver init_browser(Properties prop) {

		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		
		 highlight = prop.getProperty("highlight");
		optionsManager= new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		
		case "chrome":
			tl.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			tl.set(new FirefoxDriver());
			break;

		case "edge":
			tl.set(new EdgeDriver());
			break;

		default:
			throw new BrowserException(AppErrors.BROWSER_NOT_FOUND);

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);
		return getDriver();

	}

	public static WebDriver getDriver() {
		return tl.get();
	}

	// mvn clean install -Denv
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream fis = null;

		String envName = System.getProperty("env");
		System.out.println("running suite on env----->" + envName);
		if (envName == null) {
			try {
				fis = new FileInputStream(AppConstants.CONFIG_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {

			try {
				switch (envName.toLowerCase().trim()) {

				case "qa":
					fis = new FileInputStream(AppConstants.CONFIG_PATH);
					break;
				case "uat":
					fis = new FileInputStream(AppConstants.UAT_PATH);
					break;
				case "stage":
					fis = new FileInputStream(AppConstants.STAGE_PATH);
					break;
				case "dev":
					fis = new FileInputStream(AppConstants.DEV_PATH);
					break;
				case "prod":
					fis = new FileInputStream(AppConstants.PROD_PATH);
					break;
				default:
					System.out.println("please enter correct envName" + envName);
					throw new FrameWorkException("WRONGEnvPASSED");

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp location

		String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
