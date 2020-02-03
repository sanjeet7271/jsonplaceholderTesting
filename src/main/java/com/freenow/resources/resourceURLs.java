package com.freenow.resources;

import com.freenow.config.ConfigReader;
/**
 * 
 * @author sanjeetpandit
 *
 */
public class ResourceURLs extends ConfigReader{

	private String getbaseURI;
	private String getUsers;
	private String getPosts;
	private String getComments;

	public ResourceURLs() {
		setConfig();
	}
	
	/**
	 * @utility to read configuration property
	 */
	
	public void setConfig() {

		this.getbaseURI = prop.getProperty("URL");
		this.getUsers = prop.getProperty("users");
		this.getPosts = prop.getProperty("posts");
		this.getComments = prop.getProperty("comments");

	}
	/**
	 * 
	 * @return Base URI
	 * 
	 */
	public String getBaseURI() {
		String baseURI = this.getbaseURI;
		return baseURI;
	}
	/**
	 * 
	 * @return resource URL for users
	 * 
	 */
	/* Resources for List */
	public String getResourceforUsers() {
		String users = this.getUsers;
		return users;
	}
	/**
	 * 
	 * @return resource URL for post
	 * 
	 */
	
	public String getResourceforPosts() {
		String posts = this.getPosts;
		return posts;
	}
	/**
	 * 
	 * @return resource URL for comments
	 * 
	 */
	public String getResourceforComments() {
		String comments = this.getComments;
		return comments;
	}

}
