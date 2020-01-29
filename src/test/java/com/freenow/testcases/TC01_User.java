package com.freenow.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.freenow.config.configReader;
import com.freenow.resources.resourceURLs;

public class TC01_User {
	resourceURLs resources=new resourceURLs();
	private static Logger logger = Logger.getLogger(configReader.class);
	@Test
	public void readConfigFile() {
		
		logger.info(resources.getBaseURI());
		logger.info(resources.getResourceforComments());
		logger.info(resources.getResourceforPosts());
		logger.info(resources.getResourceforPosts());
		
	}

}
