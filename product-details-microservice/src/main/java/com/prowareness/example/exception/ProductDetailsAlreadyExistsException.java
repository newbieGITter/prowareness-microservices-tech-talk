package com.prowareness.example.exception;

public class ProductDetailsAlreadyExistsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1860746449529240646L;

	public ProductDetailsAlreadyExistsException(final String message) {
        super(message);
    }
}
