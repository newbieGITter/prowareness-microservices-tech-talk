package com.prowareness.example.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1860746449529240646L;

	public ProductAlreadyExistsException(final String message) {
        super(message);
    }
}
