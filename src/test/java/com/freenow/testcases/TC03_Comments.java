package com.freenow.testcases;

import java.util.ArrayList;
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
import com.freenow.resources.SearchForUseName;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;
import io.restassured.response.Response;

public class TC03_Comments {
	ResourceURLs resources = new ResourceURLs();
	private static Logger logger = Logger.getLogger(TC03_Comments.class);
	// Response response;
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	List<Integer> postIds = new ArrayList<>();
	List<String> emailsCommentByUsers = new ArrayList<>();
	SearchForUseName username = new SearchForUseName();
	EmailValidator emailValidators = new EmailValidator();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();
	CommonUtility commonUtility = new CommonUtility();

	@SuppressWarnings("static-access")
	@Test()
	public void verifyToGetCommentsOnUserEachPost() {
		try {
			Response userResponse = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "",
					" ");
			int statusCode = userResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);
			int id = commonUtility.getUserID(userResponse);

			Response postResponse = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "",
					resources.getResourceforPosts(), Integer.toString(id), queryParam.getQueryForUserId());
			int statusCode1 = postResponse.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode1);
			List<Integer> postIds = commonUtility.getAllPostIds(postResponse);
			for (int postId : postIds) {
				Response commentResponse = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "",
						resources.getResourceforComments(), Integer.toString(postId),
						queryParam.getQueryParamForPostId());
				commonUtility.validatingComments(commentResponse);
			}

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}
}
