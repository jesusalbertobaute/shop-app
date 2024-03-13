package com.shop.app.product.usecase.exception;

public class ProductInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2419958311348806903L;

	public ProductInvalidException() {
		super("Product is invalid");
	}
	
	public ProductInvalidException(String message) {
		super(message);
	}
	
	public ProductInvalidException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	 }
}
