package com.prowareness.example.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1860746449529240646L;

	public ProductNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductNotFoundException(final String message) {
        super(message);
    }
}
