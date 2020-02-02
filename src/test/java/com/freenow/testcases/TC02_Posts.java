package com.freenow.testcases;

import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.freenow.apiUtility.CommonUtility;
import com.freenow.commonPojoForPost.PostJsonforCreation;
import com.freenow.commonPojoForResponse.Posts;
import com.freenow.constants.HttpMethods;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.resources.ParametersForUserAndPost;
import com.freenow.resources.ResourceURLs;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;

import io.restassured.response.Response;

public class TC02_Posts {
	ResourceURLs resources = new ResourceURLs();
	public static Logger logger = Logger.getLogger(TC02_Posts.class);
	Response response;
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();
	CommonUtility commonUtility = new CommonUtility();
	PostJsonforCreation postData = new PostJsonforCreation();

	// @Test(priority = 1, description = "verify all post IDs and its duplication")
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
			logger.error("Post IDs are :" + ids);
			logger.error("Validating duplicate Post ID");
			commonUtility.CheckDuplicate(ids);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}

	//@Test(priority = 2, description = "to create dummy post data")
	public void createNewUser() {
		try {

			String dummyTestData = postData.postJsonData();
			System.out.println(dummyTestData);
			response = rest.restAssuredCalls(HttpMethods.POST, dummyTestData, resources.getResourceforPosts(), "", "");
			int statusCode = response.getStatusCode();
			System.out.println(response.asString());
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_201, statusCode);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}

	 @Test(priority = 2, description = "get response from newly created post")
	public void getResponseFromCreatedNewUser() {
		try {
			
			String dummyTestData=postData.postJsonData();
			System.out.println(dummyTestData);
			Response response = rest.restAssuredCalls(HttpMethods.POST, dummyTestData, resources.getResourceforPosts(), "", "");
			int statusCode = response.getStatusCode();
			//System.out.println(response.asString());
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_201, statusCode);
			List<Posts> postJson = Arrays.asList(response.getBody().as(Posts.class));
			for(int i = 0; i < postJson.size(); i++) {
			int newPostid=postJson.get(i).getId();
			Response getresponse = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforPosts()+newPostid,
					"", "");
			
			System.out.println(getresponse.asString());
			int statusCode1 = getresponse.getStatusCode();
			//System.out.println(response.asString());
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode1," Status code is not 200");
			}

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}
}
