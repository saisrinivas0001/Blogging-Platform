package com.blogging_platform.exceptions;

public class BlogNotFoundException extends RuntimeException{

	public BlogNotFoundException(String message) {
		super(message);
	}
	
}
