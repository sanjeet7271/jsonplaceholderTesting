package com.freenow.resources;

import com.freenow.config.ConfigReader;

public class ParametersForUserAndPost extends ConfigReader {
	String queryParamForUserId, queryParamForPostId,pathParam;
	
	public ParametersForUserAndPost(){
		this.queryParamForUserId=prop.getProperty("userId");
		this.queryParamForPostId=prop.getProperty("postId");
		//this.pathParam=prop.getProperty("");
		
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
