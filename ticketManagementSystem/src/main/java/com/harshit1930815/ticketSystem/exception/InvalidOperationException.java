package com.harshit1930815.ticketSystem.exception;

public class InvalidOperationException extends RuntimeException {

	// extra properties that you want to mange
	public InvalidOperationException() {
		super("Resource not found on server !!");
	}

	public InvalidOperationException(String message) {
		super(message);
	}

}
