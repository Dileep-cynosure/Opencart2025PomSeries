package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	public static final String ACCOUNTPAGE_TITLE = "My Account";
	public static final String LOGINPAGE_TITLE = "Account Login";
	public static final String LOGINPAGE_URL_FRAC_VALUE = "route=account/login";
	public static final String ACCOUNTPAGE_URL_FRAC_VALUE = "route=account/account";
	public static final String REGISTERPAGE_HEADER = "Register Account";
	public static final String USER_REGISTER_SUCCESS = "Your Account Has Been Created!";
	public static final List<String> ACC_PAGE_HEADERSLIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");
	
	public static final String CONFIG_PATH =  "./src/test/resources/config/config.properties";
	public static final String UAT_PATH = "./src/test/resources/config/uat.properties";
	public static final String DEV_PATH = "./src/test/resources/config/dev.properties";
	public static final String STAGE_PATH = "./src/test/resources/config/stage.properties";
	public static final String PROD_PATH = "./src/test/resources/config/prod.properties";
	public static final String REGISTER_SHEET_NAME ="register";
	public static final CharSequence USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REG_ACC_DATA_SHEET ="register";
	public static final String REG_ACC_CSV_DATA_SHEET ="register";
	
}
