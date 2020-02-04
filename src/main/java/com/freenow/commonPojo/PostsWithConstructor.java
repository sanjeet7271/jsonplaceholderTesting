package com.freenow.commonpojo;

/*
 * @Sanjeet.Pandit
 * @Pojo class for Post section,
 * @user constructor to initialize value to post data 
 */

public class PostsWithConstructor {

	private Integer userId;
	private Integer id;
	private String title;
	private String body;

	public PostsWithConstructor(Integer userId, Integer id, String title, String body) {
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}