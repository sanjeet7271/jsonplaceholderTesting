package com.freenow.resources;

import com.freenow.config.ConfigReader;

public class QueryParametersForUserAndPost extends ConfigReader {
	String queryParamForUserId, queryParamForPostId;
	public QueryParametersForUserAndPost(){
		this.queryParamForUserId=prop.getProperty("userId");
		this.queryParamForPostId=prop.getProperty("postId");
		
	}
	

	public String getQueryForUserId() {
		String queryParamForUserId = this.queryParamForUserId;
		return queryParamForUserId;
	}
	public String getQueryParamForPostId() {
		String queryParamForPostId = this.queryParamForPostId;
		return queryParamForPostId;
	}
}
