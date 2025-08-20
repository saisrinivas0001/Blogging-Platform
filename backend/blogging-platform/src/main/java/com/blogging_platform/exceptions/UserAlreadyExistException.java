package com.blogging_platform.exceptions;

public class UserAlreadyExistException extends RuntimeException{

	public UserAlreadyExistException(String message) {
		super(message);
	}

	
}
