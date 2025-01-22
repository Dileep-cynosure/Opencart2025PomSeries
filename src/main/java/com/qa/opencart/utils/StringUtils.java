package com.qa.opencart.utils;

public class StringUtils {

	public static  String getRandomEmail() {
		String email = "qaauto" +System.currentTimeMillis()+ "@gmail.com";
	return email;	
}
}
