package com.freenow.commonPojoForPost;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class PostJsonforCreation {
	public String postJsonData() throws JsonGenerationException, JsonMappingException, IOException {
		Posts post=new Posts(3, null, "Hello FreeNow", "Hello Freenow");
		ObjectMapper objMap = new ObjectMapper();
		String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(post);
		
		return mydata;
	}
}
