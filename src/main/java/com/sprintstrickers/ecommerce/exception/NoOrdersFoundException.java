package com.sprintstrickers.ecommerce.exception;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
public class NoOrdersFoundException extends Exception {

	/**
	 * If there is no orders found for that user id it will throw an error
	 */
	private static final long serialVersionUID = 1L;
	
	public NoOrdersFoundException(String exception) {
		super(exception);
	}

}
