package com.ka.libraries;

import java.io.IOException;

public class ExpectedUtil {
	
	public String keyword = "";
	public String property = "";
	public String value = "";
	
	public ExpectedUtil(String keyword) {
		this.keyword = keyword;
	}

	public ExpectedUtil(String keyword, String property) {
		this.keyword = keyword;
		this.property = property;
	}
	
	public ExpectedUtil(String keyword, String property, String value) {
		this.keyword = keyword;
		this.property = property;
		this.value = value;
	}
	
	public String callMethod() throws IOException {
		String result = "";
		switch (keyword.toUpperCase()) {
		case "ELEMENT SHOULD BE VISIBLE":
			result = ExpectedLib.elementIsVisible(property);
			break;
		case "ELEMENT SHOULD NOT BE VISIBLE":
			result = ExpectedLib.elementIsNotVisible(property);
			break;
		case "ELEMENT SHOULD BE SELECTED":
			result = ExpectedLib.elementIsSelected(property);
			break;
		case "ELEMENT SHOULD NOT BE SELECTED":
			result = ExpectedLib.elementIsNotSelected(property);
			break;
		case "ELEMENT SHOULD BE ENABLED":
			result = ExpectedLib.elementIsEnabled(property);
			break;
		case "ELEMENT SHOULD BE DISABLED":
			result = ExpectedLib.elementIsDisabled(property);
			break;
		default:
			break;
		}
		
		return keyword + " " + result;
	}
	
}
