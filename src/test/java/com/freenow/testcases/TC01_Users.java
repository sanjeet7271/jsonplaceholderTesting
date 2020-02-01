package com.freenow.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.freenow.apiUtility.EmailValidator;
import com.freenow.constants.httpMethods;
import com.freenow.constants.httpStatusCodes;
import com.freenow.resources.resourceURLs;
import com.freenow.resources.searchForUseName;
import com.freenow.restassuredmethods.restAssuredMethodsCall;
import com.freenow.usersPojoClass.UsersJson;

import io.restassured.response.Response;

public class TC01_Users {
	resourceURLs resources=new resourceURLs();
	public static Logger logger = Logger.getLogger(TC01_Users.class);
	Response response;
	restAssuredMethodsCall rest = new restAssuredMethodsCall();
	List<String> usernames = new ArrayList<>();
	List<Integer> ids = new ArrayList<>();
	List<Integer> postIds = new ArrayList<>();
	List<String> emailsCommentByUsers = new ArrayList<>();
	List<String> usersEmails = new ArrayList<>();
	int id, userId;
	searchForUseName username = new searchForUseName();
	EmailValidator emailValidators = new EmailValidator();
	
	@SuppressWarnings("static-access")
	@Test
	public void verifyToGetUserID() {
		try {
			response = rest.restAssuredCalls(httpMethods.GET, resources.getResourceforUsers(), ""," ");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(httpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);

			List<UsersJson> userjson = Arrays.asList(response.getBody().as(UsersJson[].class));
			System.out.println(userjson.size());
			for(int i=0;i<userjson.size();i++) {
				if(userjson.get(i).getUsername().equals(username.getUserName()))
				{
						logger.info("Test cases starts for :"+username.getUserName());
						id=userjson.get(i).getId();
						System.out.println(id);
						
				}
				usersEmails.add(userjson.get(i).getEmail());
			}
			System.out.println(usersEmails);
			logger.info("email validation for users");
			emailValidators.ValidEmailTest(usersEmails);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}

}
