package com.freenow.resources;

import com.freenow.config.configReader;

public class searchForUseName extends configReader{
String userName, queryParamForUserId, queryParamForPostId;
	
	public searchForUseName() {
		setConfig();
	}

	public void setConfig() {

		this.userName = prop.getProperty("username");
		

	}
	public String getUserName() {
		String username = this.userName;
		return username;
	}
	

}
