package com.ka.libraries;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class KeywordLib {

	WebElement element;
	static DesiredCapabilities capabilities;
	public static WebDriver driver;
	static By by;
	
	public static String openBrowser(String[] browser, String ipAddress) throws MalformedURLException {
		String result;	
		String msg = "";
		
		try {
			System.out.println(ipAddress);
			if(browser[2].equals(Constants.BROWSER_PROPERTY_CHROME.toString())){
				capabilities = DesiredCapabilities.chrome();
				driver = new RemoteWebDriver(new URL("http://" + ipAddress + "/wd/hub"), capabilities);
				ExpectedLib.driver = driver;
			} else if (browser[2].equals(Constants.BROWSER_PROPERTY_FIREFOX)){
				capabilities = DesiredCapabilities.firefox();
				driver = new RemoteWebDriver(new URL("http://" + ipAddress + "/wd/hub"), capabilities);
				ExpectedLib.driver = driver;
			}	
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String launchApp(String url){
		String result;
		String msg = "";
		try {
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String inputText(String property, String value) throws IOException {
		String result;
		String msg = "";
		try {
			By byInputProp = getPropBy(property);
			driver.findElement(byInputProp).sendKeys(value);
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
		
	}
	
	public static String clickButton(String property) throws IOException{
		String result ;
		String msg = "";
		try {
			By byButtonProp = getPropBy(property);
			
			driver.findElement(byButtonProp).click();
			msg = " ";
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static String closeBrowser() {
		String result;	
		String msg = "";
		
		try {
			driver.quit();
			result = "PASSED";
		} catch (Exception e) {
			msg = e.getMessage();
			result = "FAILED" + " " + msg;
		}
		
		return result;
	}
	
	public static By getPropBy(String property) throws IOException {
		ObjectLibrary obj = new ObjectLibrary();
		if(property.endsWith("id")){
			by = By.id(obj.getProperty(property));
		} else if (property.contains("xpath")){
			by = By.xpath(obj.getProperty(property));
		} else if (property.contains("class")){
			by = By.className(obj.getProperty(property));
		} else if (property.endsWith("name")){
			by = By.name(obj.getProperty(property));
		} else if (property.contains("tagname")){
			by = By.tagName(obj.getProperty(property));
		} else if (property.contains("linkText")){
			by = By.linkText(obj.getProperty(property));
		} else if (property.contains("partialLinkText")){
			by = By.partialLinkText(obj.getProperty(property));
		} else if (property.contains("cssSelector")){
			by = By.cssSelector(obj.getProperty(property));
		} else {
			by = By.id(obj.getProperty(property));
		}
		
		return by;
	}

	
}
