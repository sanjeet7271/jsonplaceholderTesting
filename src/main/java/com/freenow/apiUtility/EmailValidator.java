package com.freenow.apiUtility;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.Assert;

/*
 * @Sanjeet.Pandit
 * @Utility to Validate email address with regex
 */
public class EmailValidator {

	private static Pattern pattern;
	private static Matcher matcher;
	private static Logger logger = Logger.getLogger(EmailValidator.class);
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public static boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	/**
	 * 
	 * @param email
	 * @utility to Email validation
	 */
	public static void validEmailTest(List<String> email) {

		for (String temp : email) {
			boolean valid = validate(temp);
			logger.info("Email is valid : " + temp + " , " + valid);
			Assert.assertEquals(valid, true);
		}
		email.clear();
	}
}
