package com.freenow.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.freenow.apiUtility.EmailValidator;
import com.freenow.commonPojoClass.Comments;
import com.freenow.commonPojoClass.Posts;
import com.freenow.constants.HttpMethods;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.resources.ParametersForUserAndPost;
import com.freenow.resources.ResourceURLs;
import com.freenow.resources.SearchForUseName;
import com.freenow.restassuredmethods.RestAssuredMethodsCall;
import com.freenow.usersPojoForResponse.UsersJson;

import io.restassured.response.Response;

public class TC01_Comments {
	ResourceURLs resources = new ResourceURLs();
	public static Logger logger = Logger.getLogger(TC01_Comments.class);
	Response response;
	RestAssuredMethodsCall rest = new RestAssuredMethodsCall();
	List<String> usernames = new ArrayList<>();
	List<Integer> ids = new ArrayList<>();
	List<Integer> postIds = new ArrayList<>();
	List<String> emailsCommentByUsers = new ArrayList<>();
	List<String> usersEmails = new ArrayList<>();
	int id, userId;
	SearchForUseName username = new SearchForUseName();
	EmailValidator emailValidators = new EmailValidator();
	ParametersForUserAndPost queryParam = new ParametersForUserAndPost();

	@SuppressWarnings("static-access")
	@Test
	public void verifyToGetUserID() {
		try {
			response = rest.restAssuredCalls(HttpMethods.GET, "", resources.getResourceforUsers(), "", " ");
			int statusCode = response.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);

			List<UsersJson> userjson = Arrays.asList(response.getBody().as(UsersJson[].class));
			System.out.println(userjson.size());
			for (int i = 0; i < userjson.size(); i++) {
				if (userjson.get(i).getUsername().equals(username.getUserName())) {
					logger.info("Test cases starts for :" + username.getUserName());
					id = userjson.get(i).getId();
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

	@Test(dependsOnMethods = "verifyToGetUserID")
	public void verifyToGetPostIds() {
		try {
			response = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "", resources.getResourceforPosts(),
					Integer.toString(id), queryParam.getQueryForUserId());
			int statusCode = response.getStatusCode();
			Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);

			List<Posts> postJson = Arrays.asList(response.getBody().as(Posts[].class));

			for (int i = 0; i < postJson.size(); i++) {
				postIds.add(postJson.get(i).getId());

				String title = postJson.get(i).getTitle();
				int titleLength = title.length();
				logger.info("length of the title is :" + titleLength);
				logger.info("Title of the post is :" + title + " and post Id id  :" + postJson.get(i).getId());

				String body = postJson.get(i).getBody();
				int bodyLength = title.length();

				logger.info("length of the body is :" + bodyLength);
				logger.info("Body of the post is :" + body + " and post Id id :" + postJson.get(i).getId());

			}
			System.out.println(postIds);

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}

	@SuppressWarnings("static-access")
	@Test(dependsOnMethods = "verifyToGetPostIds")
	public void verifyToGetCommentsOnUserEachPost() {
		try {
			for (int postId : postIds) {
				response = rest.restAssuredCalls(HttpMethods.GET_WITH_QUERYPARAM, "",
						resources.getResourceforComments(), Integer.toString(postId),
						queryParam.getQueryParamForPostId());
				int statusCode = response.getStatusCode();
				Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode);

				List<Comments> comments = Arrays.asList(response.getBody().as(Comments[].class));
				for (Comments comment : comments) {
					emailsCommentByUsers.add(comment.getEmail());
				}
				emailsCommentByUsers = response.jsonPath().get("email");
				System.out.println(emailsCommentByUsers);
				emailValidators.ValidEmailTest(emailsCommentByUsers);

			}

		} catch (Exception e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}
}
