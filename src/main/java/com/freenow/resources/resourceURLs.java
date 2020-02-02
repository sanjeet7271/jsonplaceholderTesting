package com.freenow.resources;

import com.freenow.config.ConfigReader;

public class ResourceURLs extends ConfigReader{

	private String getbaseURI;
	private String getUsers;
	private String getPosts;
	private String getComments;

	public ResourceURLs() {
		setConfig();
	}

	public void setConfig() {

		this.getbaseURI = prop.getProperty("URL");
		this.getUsers = prop.getProperty("users");
		this.getPosts = prop.getProperty("posts");
		this.getComments = prop.getProperty("comments");

	}

	public String getBaseURI() {
		String baseURI = this.getbaseURI;
		//System.out.println(baseURI);
		return baseURI;
	}

	/* Resources for List */
	public String getResourceforUsers() {
		String users = this.getUsers;
		return users;
	}

	public String getResourceforPosts() {
		String posts = this.getPosts;
		return posts;
	}

	public String getResourceforComments() {
		String comments = this.getComments;
		return comments;
	}

}
