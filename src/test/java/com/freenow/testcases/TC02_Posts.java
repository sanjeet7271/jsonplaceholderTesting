package com.freenow.testcases;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.freenow.apiUtility.CommonUtility;
import com.freenow.commonPojo.PostJsonforCreation;
import com.freenow.commonPojo.Posts;
import com.freenow.constants.HttpMethods;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.excelsheetreader.ExcelSheetReader;
import com.freenow.resources.ParametersForUserAndPost;
import com.freenow.resources.ResourceURLs;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;

import io.restassured.response.Response;

/**
 * 
 * @author sanjeetpandit
 *
 */

public class TC02_Posts {
	String xlFilePath = "./src/main/resources/testdata/TestData.xlsx";
	String sheetName = "Posts";
	ExcelSheetReader provideData = new ExcelSheetReader();
	Object[][] data = null;
	ResourceURLs resources = new ResourceURLs();
	public static Logger logger = Logger.getLogger(TC02_Posts.class);
	Response response;
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();
	CommonUtility commonUtility = new CommonUtility();
	PostJsonforCreation postData = new PostJsonforCreation();

	/**
	 * @get all post ID
	 * 
	 */

	@Test(priority = 1, description = "verify all post IDs and its duplication")
	public void verifyToGetPostIds() {
		try {
			response = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "", " ");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);
			int id = commonUtility.findUserID(response);

			response = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "", resources.getResourceforPosts(),
					Integer.toString(id), queryParam.getQueryForUserId());
			int statusCode1 = response.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode1);
			List<Integer> ids = commonUtility.getAllPostIdsAndPostDetails(response);
			logger.info("Post IDs are :" + ids);
			logger.info("Validating duplicate Post ID");
			commonUtility.CheckDuplicate(ids);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}

	@DataProvider(name = "post")
	public Object[][] testData() throws IOException {
		data = provideData.testData(xlFilePath, sheetName);
		return data;
	}

	/**
	 * 
	 * @param userId
	 * @param id
	 * @param title
	 * @param body
	 * @Create post to particular users
	 */
	@Test(priority = 2, dataProvider = "post", description = "to create dummy post data")
	public void createNewUser(String userId, String id, String title, String body) {
		try {

			String dummyTestData = postData.postJsonData(Integer.parseInt(userId), Integer.parseInt(id), title, body);
			response = rest.restAssuredCalls(HttpMethods.POST, dummyTestData, resources.getResourceforPosts(), "", "");
			int statusCode = response.getStatusCode();
			logger.info(response.asString());
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_201, statusCode);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}

	/**
	 * 
	 * @param userId
	 * @param id
	 * @param title
	 * @param body
	 * @To retrieve post data created by users, Failed because it is not able to
	 *     retrieve created data
	 */
	@Test(priority = 3, dataProvider = "post", description = "get response from newly created post")
	public void getResponseFromCreatedNewUser(String userId, String id, String title, String body) {
		try {

			String dummyTestData = postData.postJsonData(Integer.parseInt(userId), Integer.parseInt(id), title, body);

			Response response = rest.restAssuredCalls(HttpMethods.POST, dummyTestData, resources.getResourceforPosts(),
					"", "");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_201, statusCode);
			List<Posts> postJson = Arrays.asList(response.getBody().as(Posts.class));
			for (int i = 0; i < postJson.size(); i++) {
				int newPostid = postJson.get(i).getId();
				Response getresponse = rest.restAssuredCalls(HttpMethods.GET, "",
						resources.getResourceforPosts() + newPostid, "", "");

				int statusCode1 = getresponse.getStatusCode();
				Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode1, " Status code is not 200");
			}

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}
}
