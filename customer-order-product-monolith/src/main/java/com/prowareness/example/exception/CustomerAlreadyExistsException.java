package com.prowareness.example.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1860746449529240646L;

	public CustomerAlreadyExistsException(final String message) {
        super(message);
    }
}
