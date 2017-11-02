package com.manulife.top5website.exception;

public class InvalidFileFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public InvalidFileFormatException() {
		super();

	}

	public InvalidFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFileFormatException(String message) {
		super(message);
	}

	public InvalidFileFormatException(Throwable cause) {
		super(cause);
	}
}
