package com.AvaliablException;

public class AccountInactiveException extends Exception {

	public AccountInactiveException(String message) {
		super(message);
		System.out.println(message);
		// TODO Auto-generated constructor stub
	}
}
