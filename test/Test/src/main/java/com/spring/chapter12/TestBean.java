package com.spring.chapter12;

import org.springframework.beans.factory.annotation.Required;

public class TestBean {
	
	public String getMessage() {
		return message;
	}

	@Required
	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	
	

}
