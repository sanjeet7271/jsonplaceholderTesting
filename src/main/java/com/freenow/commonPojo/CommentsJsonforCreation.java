package com.freenow.commonPojo;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * @Post, JSON
 * @Sanjeet.Pandit
 * @Return JSON body for comment
 */
public class CommentsJsonforCreation {
	public String postJsonData() throws JsonGenerationException, JsonMappingException, IOException {
		CommentsWithConstructor post = new CommentsWithConstructor(3, null, "Hello FreeNow", "HelloFreenow@freenow.com", "heloo");
		ObjectMapper objMap = new ObjectMapper();
		String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(post);

		return mydata;
	}
}
