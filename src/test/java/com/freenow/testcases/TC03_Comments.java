package com.freenow.testcases;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.freenow.apiUtility.CommonUtility;
import com.freenow.apiUtility.EmailValidator;
import com.freenow.commonpojo.CommentsJsonforCreation;
import com.freenow.constants.HttpMethods;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.resources.ParametersForUserAndPost;
import com.freenow.resources.ResourceURLs;
import com.freenow.resources.SearchForUseName;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;
import io.restassured.response.Response;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class TC03_Comments {
	ResourceURLs resources = new ResourceURLs();
	private static Logger logger = Logger.getLogger(TC03_Comments.class);
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	List<Integer> postIds = new ArrayList<>();
	List<String> emailsCommentByUsers = new ArrayList<>();
	SearchForUseName username = new SearchForUseName();
	EmailValidator emailValidators = new EmailValidator();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();
	CommonUtility commonUtility = new CommonUtility();
	CommentsJsonforCreation CommentsJson = new CommentsJsonforCreation();

	/**
	 * 
	 * @validate all email's commented by users to particular user ID
	 * 
	 */

	@Test(description = "validating all emails are proper or not, comments made by people for each post")
	public void verifyToGetCommentsOnUserEachPost() {
		try {
			logger.info("getting user id for the corresponding username");
			Response userResponse = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "",
					" ");
			int statusCode = userResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);
			int id = commonUtility.getUserID(userResponse);
			logger.info("Recieved user id for the corresponding username " + id);
			logger.info("getting post ids for the corresponding user Id");
			Response postResponse = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "",
					resources.getResourceforPosts(), Integer.toString(id), queryParam.getQueryForUserId());
			int statusCode1 = postResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode1);
			List<Integer> postIds = commonUtility.getAllPostIds(postResponse);
			logger.info("Recieved post ids for the corresponding user Id " + postIds);
			for (int postId : postIds) {
				Response commentResponse = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "",
						resources.getResourceforComments(), Integer.toString(postId),
						queryParam.getQueryParamForPostId());
				logger.info("comments validation starts for the post id " + postId);
				commonUtility.validatingComments(commentResponse);
			}

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}

	/**
	 * 
	 * @Made comment on each post, Which is created by the particular user
	 * 
	 */

	@Test(priority = 2, description = "make comment on each post of the user with dummy test data")
	public void createNewComment() {
		try {
			logger.info("getting user id for the corresponding username");
			Response userResponse = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "",
					" ");
			int statusCode = userResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);
			int id = commonUtility.getUserID(userResponse);
			logger.info("Recieved user id for the corresponding username " + id);
			logger.info("getting post ids for the corresponding user Id");
			Response postResponse = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "",
					resources.getResourceforPosts(), Integer.toString(id), queryParam.getQueryForUserId());
			int statusCode1 = postResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode1);
			List<Integer> postIds = commonUtility.getAllPostIds(postResponse);
			logger.info("Recieved post ids for the corresponding user Id " + postIds);

			for (int postId : postIds) {
				logger.info("Creating comment for the post " + postId);
				String dummyTestData = CommentsJson.postJsonData();
				Response commentresponse = rest.restAssuredCalls(HttpMethods.POST_WITH_QUERYPARAM, dummyTestData,
						resources.getResourceforComments(), Integer.toString(postId),
						queryParam.getQueryParamForPostId());
				int statusCode2 = commentresponse.getStatusCode();
				System.out.println(commentresponse.asString());
				Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_201, statusCode2);
				logger.info("made comment for the post " + postId + " is successful");
			}

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Response not found.");
			Assert.fail("Status code is not correct.");
		}
	}
}
