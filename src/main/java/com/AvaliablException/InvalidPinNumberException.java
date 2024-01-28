package com.AvaliablException;

public class InvalidPinNumberException extends Exception {
	public InvalidPinNumberException(String message) {
		System.out.println(message);
	}
}
