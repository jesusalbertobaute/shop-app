package com.shop.app.product.usecase.exception;

public class InvalidProductCodeException extends RuntimeException {
	
	private static final long serialVersionUID = 2948357799181010568L;

	public InvalidProductCodeException() {
		super("Product Code is invalid");
	}
	
	public InvalidProductCodeException(String message) {
		super(message);
	}
	
	public InvalidProductCodeException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	 }

}
