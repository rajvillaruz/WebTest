package com.ka.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ObjectLibrary {
	Properties prop;
	
	public ObjectLibrary() throws IOException {
		prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.OBJ_PROP_FILE);
//		FileInputStream inputStream = new FileInputStream(Constants.OBJ_PROP_FILE);
		prop.load(inputStream);
		inputStream.close();
	}
	
	public String getProperty(String propName) {
		return prop.getProperty(propName);
	}
}
