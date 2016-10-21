package com.ka.libraries;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExpectedLib {
	
	public static WebDriver driver;
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		ExpectedLib.driver = driver;
	}

	static By by;
	
	public static String elementIsVisible(String property){
		
		String result = "";	
		String msg = "";
		
		try{
			if(driver.findElement(getPropBy(property)).isDisplayed()){
				msg = property + " is visible.";
				result = "PASSED:" + " " + msg;
			} else {
				msg = property + " is not visible.";
				result = "FAILED:" + " " + msg;
			}
		} catch (Exception e){
			msg = e.getMessage();
			result = "FAILED:" + " " + msg;
		}
		
		return result;
	}
	
	public static String elementIsNotVisible(String property){
		
		String result = "";	
		String msg = "";
		
		try{
			if(!driver.findElement(getPropBy(property)).isDisplayed()){
				msg = property + " is not visible.";
				result = "PASSED:" + " " + msg;
			} else {
				msg = property + " is visible.";
				result = "FAILED:" + " " + msg;
			}
		} catch (Exception e){
			msg = e.getMessage();
			result = "FAILED:" + " " + msg;
		}
		
		return result;
	}
	
	public static String elementIsSelected(String property){
		
		String result = "";	
		String msg = "";
		
		try{
			if(driver.findElement(getPropBy(property)).isSelected()){
				msg = property + " is selected.";
				result = "PASSED:" + " " + msg;
			} else {
				msg = property + " is not selected.";
				result = "FAILED:" + " " + msg;
			}
		} catch (Exception e){
			msg = e.getMessage();
			result = "FAILED:" + " " + msg;
		}
		
		return result;
	}
	
	public static String elementIsNotSelected(String property){
		
		String result = "";	
		String msg = "";
		
		try{
			if(!driver.findElement(getPropBy(property)).isSelected()){
				msg = property + " is not selected.";
				result = "PASSED:" + " " + msg;
			} else {
				msg = property + " is selected.";
				result = "FAILED:" + " " + msg;
			}
		} catch (Exception e){
			msg = e.getMessage();
			result = "FAILED:" + " " + msg;
		}
		
		return result;
	}
	
	public static String elementIsEnabled(String property){
		
		String result = "";	
		String msg = "";
		
		try{
			if(driver.findElement(getPropBy(property)).isEnabled()){
				msg = property + " is enabled.";
				result = "PASSED:" + " " + msg;
			} else {
				msg = property + " is disabled.";
				result = "FAILED:" + " " + msg;
			}
		} catch (Exception e){
			msg = e.getMessage();
			result = "FAILED:" + " " + msg;
		}
		
		return result;
	}
	
	public static String elementIsDisabled(String property){
		
		String result = "";	
		String msg = "";
		
		try{
			if(!driver.findElement(getPropBy(property)).isEnabled()){
				msg = property + " is disabled.";
				result = "PASSED:" + " " + msg;
			} else {
				msg = property + " is enabled.";
				result = "FAILED:" + " " + msg;
			}
		} catch (Exception e){
			msg = e.getMessage();
			result = "FAILED:" + " " + msg;
		}
		
		return result;
	}
	
	public static By getPropBy(String property) throws IOException {
		ObjectLibrary obj = new ObjectLibrary();
		if(property.endsWith("id")){
			by = By.id(obj.getProperty(property));
		} else if (property.endsWith("xpath")){
			by = By.xpath(obj.getProperty(property));
		} else if (property.endsWith("class")){
			by = By.className(obj.getProperty(property));
		} else if (property.endsWith("name")){
			by = By.name(obj.getProperty(property));
		} else if (property.endsWith("tagname")){
			by = By.tagName(obj.getProperty(property));
		} else if (property.endsWith("linkText")){
			by = By.linkText(obj.getProperty(property));
		} else if (property.endsWith("partialLinkText")){
			by = By.partialLinkText(obj.getProperty(property));
		} else if (property.endsWith("cssSelector")){
			by = By.cssSelector(obj.getProperty(property));
		} else {
			by = By.id(obj.getProperty(property));
		}
		
		return by;
	}

}
