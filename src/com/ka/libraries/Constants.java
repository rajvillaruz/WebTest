package com.ka.libraries;

import com.ka.libraries.Configurations;
public class Constants {
	
	public static final String OBJ_PROP_FILE = Configurations.getInstance().getProperty(Properties.OBJ_PROP_FILE);
	public static final String CHROME_DRIVER = Configurations.getInstance().getProperty(Properties.CHROME_DRIVER);
	public static final String FIREFOX_DRIVER = Configurations.getInstance().getProperty(Properties.FIREFOX_DRIVER);
	public static final String PATH_UPLOAD = Configurations.getInstance().getProperty(Properties.PATH_UPLOAD);
	public static final String BROWSER_PROPERTY_CHROME = Configurations.getInstance().getProperty(Properties.BROWSER_PROPERTY_CHROME);
	public static final String BROWSER_PROPERTY_FIREFOX= Configurations.getInstance().getProperty(Properties.BROWSER_PROPERTY_FIREFOX);
	
	public static final String PATH_CONFFILE = "config.properties";
	public static final int MYCONSTANT_ONE = 1;
}
