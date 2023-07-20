package com.example.order.exception;

public class BadRequestException  extends Exception{

	public BadRequestException(String message) {
		super(message);
		System.out.println("Bad Request test "+message);
	}

}
