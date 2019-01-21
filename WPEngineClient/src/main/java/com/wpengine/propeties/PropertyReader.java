package com.wpengine.propeties;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.wpengine.impls.WpengineRestClient;



public final class PropertyReader {

	 static final Logger logger = Logger.getLogger(WpengineRestClient.class);
	final static String WPENGINE_PROPERTYFILE = "src/main/resources/wpengine-config.properties";
	final static Properties properties = new Properties();
	private String accouturl;
	
	public PropertyReader(){
		try {
			properties.load(new FileInputStream(WPENGINE_PROPERTYFILE));
			setAccouturl(properties.getProperty("accouturl"));
		} catch (FileNotFoundException e) {
		  logger.debug(e.getMessage());
		} catch (IOException e) {
			logger.debug(e.getMessage());
			
		}
	}
	
	
	/**
	 * @return the accouturl
	 */
	public String getAccouturl() {
		return accouturl;
	}

	/**
	 * @param accouturl the accouturl to set
	 */
	public void setAccouturl(String accouturl) {
		this.accouturl = accouturl;
	}
	
	static {
		PropertyReader pr = new PropertyReader();
	}

}
