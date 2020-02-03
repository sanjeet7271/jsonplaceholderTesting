package com.freenow.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
/*
 * @Sanjeet.Pandit
 * @configuration Reader
 * 
 */
public class ConfigReader {
	
	public Properties prop;
	public String baseURL;
	String getbaseURI;
	FileInputStream input = null;
	
	public static Logger logger = Logger.getLogger(ConfigReader.class);
	public ConfigReader(){
		configurationReader();
	}
	
	/**
	 * @author sanjeetpandit
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
