package com.kdf.keyword;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Keywords {
	
	WebDriver driver;
	Properties prop;
	String name;
	String browser;
	
	public Keywords(WebDriver driver, String browser) throws IOException {
		prop = new Properties();
		FileInputStream stream = new FileInputStream("objects.properties");
		prop.load(stream);
		stream.close();
		this.driver = driver;
		this.browser = browser;
	}
	
	public void callMethod(String keyword, String object, String value) {
		if("INPUT TEXT".equals(keyword.toUpperCase())) {
			inputText(object, value);
		}
	}
	
	public void callMethod(String keyword, String object) {
		if("LAUNCH APP".equals(keyword.toUpperCase())) {
			launchApp(object);
		}
		if("CLICK BUTTON".equals(keyword.toUpperCase())) {
			clickButton(object);
		}
	}
	public void callMethod(String keyword) {
		if("OPEN BROWSER".equals(keyword.toUpperCase())) {
			openBrowser();
		}
	}

	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rochelle\\Documents\\Automated Testing References\\Selenium WebDriver\\Installers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("https://www.google.com");
	}
	
	public void closeBrowser() {
		driver.close();
	}
	
	public void launchApp(String url) {
		driver.get(url);
	}
	
	public void inputText(String obj, String value) {
		name = prop.getProperty(obj);
		driver.findElement(By.id(name)).sendKeys(value);;
	}
	
	public void clickButton(String obj) {
		name = prop.getProperty(obj);
		driver.findElement(By.id(name)).click();
	}
	
	public void chooseSelect(String obj, String value) {
		name = prop.getProperty(obj);
		Select drpdown = new Select(driver.findElement(By.id(name)));
		drpdown.selectByValue(value);
	}
	
	public void chooseCheckbox(String obj) {
		name = prop.getProperty(obj);
	}
	
	public void chooseRadioButton(String obj) {
		name = prop.getProperty(obj);
	}
}
