package com.freenow.resources;

import com.freenow.config.ConfigReader;

public class SearchForUseName extends ConfigReader{
String userName, queryParamForUserId, queryParamForPostId;
	
	public SearchForUseName() {
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
