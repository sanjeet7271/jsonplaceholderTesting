package com.freenow.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.freenow.apiUtility.CommonUtility;
import com.freenow.apiUtility.EmailValidator;
import com.freenow.constants.HttpMethods;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.excelsheetreader.ExcelSheetReader;
import com.freenow.resources.ParametersForUserAndPost;
import com.freenow.resources.ResourceURLs;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;
import com.freenow.userspojoforpost.UserJsonforCreation;
import com.freenow.userspojoforresponse.UsersJson;

import io.restassured.response.Response;

/**
 * 
 * @author sanjeetpandit
 * @Test cases for users
 *
 */
public class TC01_Users {
	ResourceURLs resources = new ResourceURLs();
	private static Logger logger = Logger.getLogger(TC01_Users.class);
	Response response;
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	List<String> usersEmails = new ArrayList<>();
	List<Integer> userIds = new ArrayList<>();
	EmailValidator emailValidators = new EmailValidator();
	CommonUtility commonUtility = new CommonUtility();
	UserJsonforCreation userTestData = new UserJsonforCreation();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();

	String xlFilePath = "./src/main/resources/testdata/TestData.xlsx";
	String sheetName = "Users";
	ExcelSheetReader provideData = new ExcelSheetReader();
	Object[][] data = null;
	Integer createdID;

	/**
	 * 1. @find user ID if user name is known
	 */
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

	/**
	 * 1. @Test case for validate email address 2. @verify received user id is
	 * unique
	 */
	@SuppressWarnings("static-access")
	@Test(priority = 2, description = "to verify email address is proper and Unique user Id")
	public void verifyToUserEmailAddress() {
		try {
			response = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "", "");
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
			emailValidators.validEmailTest(usersEmails);
			logger.info("email validation for users is successfully done");

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}

	@DataProvider(name = "user")
	public Object[][] testData() throws IOException {
		data = provideData.testData(xlFilePath, sheetName);
		return data;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param username
	 * @param email
	 * @param street
	 * @param suite
	 * @param city
	 * @param zipcode
	 * @param lat
	 * @param lng
	 * @param phone
	 * @param website
	 * @param Cname
	 * @param catchPhrase
	 * @param bs
	 * @verify user is created with test data
	 */

	@Test(priority = 3, dataProvider = "user", description = "to verify email address is proper and Unique user Id")
	public void createNewUser(String id, String name, String username, String email, String street, String suite,
			String city, String zipcode, String lat, String lng, String phone, String website, String Cname,
			String catchPhrase, String bs) {
		try {

			String dummyTestData = userTestData.userJsonData(Integer.parseInt(id), name, username, email, street, suite,
					city, zipcode, lat, lng, phone, website, Cname, catchPhrase, bs);
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

	@Test(priority = 4, dataProvider = "user", description = "to verify email address is proper and Unique user Id")
	public void getCreateNewUserDetails(String id, String name, String username, String email, String street,
			String suite, String city, String zipcode, String lat, String lng, String phone, String website,
			String Cname, String catchPhrase, String bs) {
		try {

			String dummyTestData = userTestData.userJsonData(Integer.parseInt(id), name, username, email, street, suite,
					city, zipcode, lat, lng, phone, website, Cname, catchPhrase, bs);
			System.out.println(dummyTestData);
			Response postResponse = rest.restAssuredCalls(HttpMethods.POST, dummyTestData,
					resources.getResourceforUsers(), "", "");
			int statusCode = postResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_201, statusCode);
			createdID = postResponse.jsonPath().get("id");
			Response getResponse = rest.restAssuredCalls(HttpMethods.GET, "",
					resources.getResourceforUsers() + "/" + createdID, "", " ");
			int statusCode1 = getResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode1, "response is not 200");

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}
}
