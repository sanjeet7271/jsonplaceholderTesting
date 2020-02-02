package com.freenow.testcases;

import java.util.List;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.freenow.apiUtility.CommonUtility;
import com.freenow.commonPojoForPost.PostJsonforCreation;
import com.freenow.constants.HttpMethods;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.resources.ParametersForUserAndPost;
import com.freenow.resources.ResourceURLs;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;

import io.restassured.response.Response;

public class TC01_Posts {
	ResourceURLs resources = new ResourceURLs();
	public static Logger logger = Logger.getLogger(TC01_Posts.class);
	Response response;
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();
	CommonUtility commonUtility = new CommonUtility();
	PostJsonforCreation postData=new PostJsonforCreation();
	@Test()
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
			List<Integer> ids=commonUtility.getAllPostIdsAndPostDetails(response);
			logger.error("Post IDs are :" + ids);
			logger.error("Validating duplicate Post ID");
			commonUtility.CheckDuplicate(ids);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}
	
	@Test(priority = 3, description = "to create dummy post data")
	public void createNewUser() {
		try {
			
			String dummyTestData=postData.postJsonData();
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
}
