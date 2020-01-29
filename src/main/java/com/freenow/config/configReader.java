package com.freenow.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class configReader {
	
	public Properties prop;
	public String baseURL;
	String getbaseURI;
	FileInputStream input = null;
	
	public static Logger logger = Logger.getLogger(configReader.class);
	public configReader(){
		configurationReader();
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void configurationReader(){
		try {
			input = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config/config.properties");
			prop = new Properties();
			prop.load(input);
			input.close();
		} catch (IOException e) {
			logger.error("Exception " + e);
			logger.error("Properties file not found.");
			Assert.fail("Properties file not found.");
		}
	}
	

}
