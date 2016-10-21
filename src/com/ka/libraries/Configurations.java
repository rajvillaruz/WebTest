package com.ka.libraries;

import java.util.Properties;

public class Configurations {

	private Properties properties = null;
	private static Configurations instance = null;
	//final static String OBJ_PROP = ;
	
	private Configurations(){
		this.properties = new Properties();
		try{
			//FileInputStream confFile = new FileInputStream(Constants.PATH_CONFFILE);
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.PATH_CONFFILE));
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	private synchronized static void createInstance(){
		if (instance == null){
			instance = new Configurations();
		}
	}
	
	public static Configurations getInstance(){
		if(instance == null){
			createInstance();
		}
		return instance;
	}
	
	public String getProperty(String key){
		String result = null;
		if(key != null && !key.trim().isEmpty()){
			result = this.properties.getProperty(key);
		}
		return result;
	}
	
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
}
