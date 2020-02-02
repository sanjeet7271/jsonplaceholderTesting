package com.freenow.apiUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.testng.Assert;

import com.freenow.commonPojoForResponse.Comments;
import com.freenow.commonPojoForResponse.Posts;
import com.freenow.constants.HttpStatusCodes;
import com.freenow.resources.SearchForUseName;
import com.freenow.usersPojoForResponse.UsersJson;

import io.restassured.response.Response;

public class CommonUtility {
	SearchForUseName username = new SearchForUseName();
	private static Logger logger = Logger.getLogger(CommonUtility.class);
	int id;
	List<String> usernames = new ArrayList<>();
	List<Integer> ids = new ArrayList<>();
	List<Integer> postIds = new ArrayList<>();
	List<Integer> postsId = new ArrayList<>();
	List<String> emailsCommentByUsers = new ArrayList<>();
	List<String> usersEmails = new ArrayList<>();
	EmailValidator emailValidators = new EmailValidator();
	public int findUserID(Response response) {
		logger.info("Test case starts..");
		List<UsersJson> userjson = Arrays.asList(response.getBody().as(UsersJson[].class));
		logger.info("Total " + userjson.size() + " users present int the user list");
		for (int i = 0; i < userjson.size(); i++) {
			if (userjson.get(i).getUsername().equals(username.getUserName())) {
				Assert.assertEquals(userjson.get(i).getUsername(), username.getUserName(),
						"user name not found int the list");
				logger.info("Test case starts for :" + username.getUserName());
				id = userjson.get(i).getId();
				logger.info("User id for username " + userjson.get(i).getUsername() + " is " + id);
			}
		}
		logger.info("Test case finished..");
		return id;
	}
	
	public int getUserID(Response response) {
		List<UsersJson> userjson = Arrays.asList(response.getBody().as(UsersJson[].class));
		for (int i = 0; i < userjson.size(); i++) {
			if (userjson.get(i).getUsername().equals(username.getUserName())) {
				id = userjson.get(i).getId();
			}
		}
		return id;
	}
	
	public void CheckDuplicate(List<Integer> ids) {
		Object[] arr = ids.toArray();
		Set<Integer> hs = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					hs.add((Integer) arr[i]);
					break;
				}
			}
		}
		Object[] B = hs.toArray();
		if (B.length > 0) {
			logger.info("duplicate ids are :" + Arrays.toString(B));
			Assert.fail("id should be unique.");
		} else {
			logger.info("duplicate id is not found");
		}
	}

	public List<Integer> getAllPostIdsAndPostDetails(Response response) {
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
		return postIds;
	}
	
	public List<Integer> getAllPostIds(Response response) {
		List<Posts> postJson = Arrays.asList(response.getBody().as(Posts[].class));
		for (int i = 0; i < postJson.size(); i++) {
			postsId.add(postJson.get(i).getId());
			
		}
		return postsId;
	}
	
	@SuppressWarnings("static-access")
	public void validatingComments(Response commentResponse){
		int statusCode2 = commentResponse.getStatusCode();
		Assert.assertEquals(HttpStatusCodes.RESPONSE_STATUS_CODE_200, statusCode2);

		List<Comments> comments = Arrays.asList(commentResponse.getBody().as(Comments[].class));
		for (Comments comment : comments) {
			emailsCommentByUsers.add(comment.getEmail());
		}
		logger.info("Validating email Ids for commented users on particular comments, and the email Ids are "+emailsCommentByUsers);
		emailValidators.ValidEmailTest(emailsCommentByUsers);
		logger.info("Validating done for email Ids");
	}
}
