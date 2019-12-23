package com.sprintstrickers.ecommerce.exception;

public class InvalidProduct extends Exception {
	private static final long serialVersionUID = 1L;
	InvalidProduct(){
		
	}
	public InvalidProduct(String message) {
		super(message);
	}
}
