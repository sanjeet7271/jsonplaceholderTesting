package com.freenow.commonpojo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * @Post, JSON
 * @Sanjeet.Pandit
 * @Return JSON body for Post
 * @daaProvider
 */

public class PostJsonforCreation {

	public String postJsonData(Integer userId, Integer id, String title, String body)
			throws JsonGenerationException, JsonMappingException, IOException {
		PostsWithConstructor post = new PostsWithConstructor(userId, id, title, body);
		ObjectMapper objMap = new ObjectMapper();
		String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(post);

		return mydata;
	}
}
