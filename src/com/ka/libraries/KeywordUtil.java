package com.ka.libraries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KeywordUtil {

	public String keyword = "";
	public String property = "";
	public String value = "";
	public String expected = "";
	public String[] browser;
	
	public String[] getBrowser() {
		return browser;
	}

	public void setBrowser(String[] browser) {
		this.browser = browser;
	}

	public KeywordUtil(String keyword) {
		this.keyword = keyword;
	}

	public KeywordUtil(String keyword, String property) {
		this.keyword = keyword;
		this.property = property;
	}
	
	public KeywordUtil(String keyword, String property, String value) {
		this.keyword = keyword;
		this.property = property;
		this.value = value;
	}
	
	public KeywordUtil(String keyword, String property, String value, String expected) {
		this.keyword = keyword;
		this.property = property;
		this.value = value;
		this.expected = expected;
	}
	
	public String callMethod() throws IOException {
		String result = "";
		switch (keyword.toUpperCase()) {
		case "OPEN BROWSER":
			result = KeywordLib.openBrowser(browser);
			break;
		case "LAUNCH APP":
			result = KeywordLib.launchApp(property);
			break;
		case "INPUT TEXT":
			result = KeywordLib.inputText(property, value);
			break;
		case "CLICK BUTTON":
			result = KeywordLib.clickButton(property);
			break;
		case "CLOSE BROWSER":
			result = KeywordLib.closeBrowser();
			break;
		default:
			break;
		}
		
		if (expected != null){
			String[] exp = expected.split(",");
			ExpectedUtil eu;
			int size = exp.length;
			
			switch (size){
			case 2:
				eu = new ExpectedUtil(exp[0], exp[1]);
				result = result + "-" + eu.callMethod();
				break;
			case 3:
				eu = new ExpectedUtil(exp[0], exp[1], exp[2]);
				result = result + "-" + eu.callMethod();
				break;
			default:
				break;
			}	
		} else{
			result = result + "- ";
		}
		return keyword + "-" + result;
	}
}
