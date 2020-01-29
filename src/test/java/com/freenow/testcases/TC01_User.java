package com.freenow.testcases;

import org.testng.annotations.Test;

import com.freenow.resources.resourceURLs;

public class TC01_User {
	resourceURLs resources=new resourceURLs();
	@Test
	public void readConfigFile() {
		
		System.out.println(resources.getBaseURI());
		System.out.println(resources.getResourceforComments());
		System.out.println(resources.getResourceforPosts());
		System.out.println(resources.getResourceforPosts());
		
	}

}
