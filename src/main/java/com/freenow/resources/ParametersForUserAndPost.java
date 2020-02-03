package com.freenow.resources;

import com.freenow.config.ConfigReader;

/**
 * 
 * @author sanjeetpandit
 * @Return Query parameter for user id, post id
 *
 */
public class ParametersForUserAndPost extends ConfigReader {
	String queryParamForUserId, queryParamForPostId, pathParam;

	public ParametersForUserAndPost() {
		this.queryParamForUserId = prop.getProperty("userId");
		this.queryParamForPostId = prop.getProperty("postId");

	}

	/**
	 * 
	 * @return Query Parameter for User ID
	 * 
	 */

	public String getQueryForUserId() {
		String queryParamForUserId = this.queryParamForUserId;
		return queryParamForUserId;
	}

	/**
	 * 
	 * @return Query Parameter for post ID
	 * 
	 */

	public String getQueryParamForPostId() {
		String queryParamForPostId = this.queryParamForPostId;
		return queryParamForPostId;
	}
}
