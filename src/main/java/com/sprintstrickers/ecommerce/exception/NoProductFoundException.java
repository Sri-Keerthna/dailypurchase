package com.sprintstrickers.ecommerce.exception;

/**
 * @author Sri Keerthna
 * @since 2019-12-23
 */
public class NoProductFoundException extends Exception {

	/**
	 * If there is no product found it will throw an error
	 */
	private static final long serialVersionUID = 1L;

	public NoProductFoundException(String exception) {
		super(exception);
	}
}
