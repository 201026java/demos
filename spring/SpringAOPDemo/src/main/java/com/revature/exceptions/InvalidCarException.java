package com.revature.exceptions;

public class InvalidCarException extends RuntimeException {

	public InvalidCarException() {
		super();
	}

	public InvalidCarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidCarException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCarException(String message) {
		super(message);
	}

	public InvalidCarException(Throwable cause) {
		super(cause);
	}
}
