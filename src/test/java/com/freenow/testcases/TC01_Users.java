package com.freenow.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.freenow.apiUtility.CommonUtility;
import com.freenow.apiUtility.EmailValidator;
import com.freenow.constants.HttpMethods;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.resources.ParametersForUserAndPost;
import com.freenow.resources.ResourceURLs;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;
import com.freenow.usersPojoForPost.UserJsonforCreation;
import com.freenow.usersPojoForResponse.UsersJson;

import io.restassured.response.Response;

public class TC01_Users {
	ResourceURLs resources = new ResourceURLs();
	private static Logger logger = Logger.getLogger(TC01_Users.class);
	Response response;
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	List<String> usersEmails = new ArrayList<>();
	List<Integer> userIds = new ArrayList<>();
	EmailValidator emailValidators = new EmailValidator();
	CommonUtility commonUtility = new CommonUtility();
	UserJsonforCreation userTestData=new UserJsonforCreation();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();
	@Test(priority = 1, description = "finding User ID id when username known")
	public void verifyToGetUserID() {
		try {
			response = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "", " ");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode, "response is not 200");
			commonUtility.findUserID(response);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}

	@SuppressWarnings("static-access")
	@Test(priority = 2, description = "to verify email address is proper and Unique user Id")
	public void verifyToUserEmailAddress() {
		try {
			response = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "","");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);
			List<UsersJson> userjson = Arrays.asList(response.getBody().as(UsersJson[].class));
			logger.info("Storing email address to list");
			for (int i = 0; i < userjson.size(); i++) {

				usersEmails.add(userjson.get(i).getEmail());
				userIds.add(userjson.get(i).getId());
			}
			logger.info("checking user id is duplicate or not.");
			commonUtility.CheckDuplicate(userIds);
			
			logger.info("email validation for users is started.");
			emailValidators.ValidEmailTest(usersEmails);
			logger.info("email validation for users is successfully done");

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}
	
	@Test(priority = 3, description = "to verify email address is proper and Unique user Id")
	public void createNewUser() {
		try {
			
			String dummyTestData=userTestData.userJsonData();
			System.out.println(dummyTestData);
			response = rest.restAssuredCalls(HttpMethods.POST, dummyTestData, resources.getResourceforUsers(), "", "");
			int statusCode = response.getStatusCode();
			System.out.println(response.asString());
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_201, statusCode);
			

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}
}
