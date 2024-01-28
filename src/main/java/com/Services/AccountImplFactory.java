package com.Services;

import bank_app_u.bank_app_u_Entity.*;

public class AccountImplFactory {
	public static AccountImpl create(String choice) throws Exception {
		AccountImpl accImpl = null;
		// Create object for child class
		if (choice.equals("Savings")) {
			accImpl = new SavingsImpl();
		} else if (choice.equals("Current")) {
			accImpl = new CurrentImpl();
		} else {
			throw new Exception("Enter the valid account");
		}
		return accImpl;
	}
}

