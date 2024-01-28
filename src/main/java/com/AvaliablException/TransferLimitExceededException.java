package com.AvaliablException;

public class TransferLimitExceededException extends Exception {

	public TransferLimitExceededException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println(message);
	}

}

