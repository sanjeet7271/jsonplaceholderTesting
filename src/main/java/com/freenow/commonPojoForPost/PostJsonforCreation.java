package com.freenow.commonPojoForPost;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.DataProvider;

import com.freenow.excelsheetreader.ExcelSheetReader;

public class PostJsonforCreation {
		
	public String postJsonData(Integer userId, Integer id, String title, String body) throws JsonGenerationException, JsonMappingException, IOException {
		Posts post=new Posts(userId, id, title, body);
		ObjectMapper objMap = new ObjectMapper();
		String mydata = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(post);
		
		return mydata;
	}
}
