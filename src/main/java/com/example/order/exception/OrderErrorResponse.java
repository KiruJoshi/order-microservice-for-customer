package com.example.order.exception;

import com.example.order.response.OrderResponse;

public class OrderErrorResponse {

	private int statusCode;
	private String message;
	private OrderResponse response;


	public OrderErrorResponse(int statusCode, String message, OrderResponse response) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.response = response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderResponse getResponse() {
		return response;
	}

	public void setResponse(OrderResponse response) {
		this.response = response;
	}

}
