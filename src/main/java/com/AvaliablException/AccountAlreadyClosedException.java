package com.AvaliablException;

public class AccountAlreadyClosedException extends Exception {

	public AccountAlreadyClosedException(String message) {
		super(message);
		System.out.println(message);
	}

}

