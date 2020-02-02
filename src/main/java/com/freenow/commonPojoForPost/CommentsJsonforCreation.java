package com.freenow.commonPojoForPost;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class CommentsJsonforCreation {
	public String postJsonData() throws JsonGenerationException, JsonMappingException, IOException {
		Comments post=new Comments(3, null, "Hello FreeNow", "HelloFreenow@freenow.com", "heloo");
		ObjectMapper objMap = new ObjectMapper();
		String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(post);
		
		return mydata;
	}
}
