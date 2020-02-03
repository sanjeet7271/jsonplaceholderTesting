package com.freenow.resources;

import com.freenow.config.ConfigReader;
/**
 * 
 * @author sanjeetpandit
 *
 */
public class SearchForUseName extends ConfigReader {

	String userName, queryParamForUserId, queryParamForPostId;

	public SearchForUseName() {
		setConfig();
	}
	/**
	 * Configuration reader
	 * 
	 */
	public void setConfig() {

		this.userName = prop.getProperty("username");

	}
	/**
	 * 
	 * @return username test data
	 * 
	 */
	public String getUserName() {
		String username = this.userName;
		return username;
	}

}
