package com.freenow.usersPojoForPost;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class UserJsonforCreation {
	public String userJsonData() throws JsonGenerationException, JsonMappingException, IOException {
		Geo geo = new Geo("12.34", "234.234");
		Address addr = new Address("kala lumpur", "apt 312", "george villa", "11000939", geo);
		Company company = new Company("cronear", "multi clien feature", "real time market");
		UsersJson p = new UsersJson(12, "namdal", "fgyfg", "adam@gmail.com", addr, "1323413", "werwer.com", company);
		ObjectMapper objMap = new ObjectMapper();
		String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(p);
		
		return mydata;
	}
}
