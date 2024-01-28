package com.Services;

import java.time.Year;
import java.util.Date;

import com.AvaliablException.AgeValidityException;

import bank_app_u.bank_app_u_Entity.Account;
import bank_app_u.bank_app_u_Entity.Savings;

public class SavingsImpl implements AccountImpl {
	int year = Year.now().getValue();
	
		@Override
		public boolean open(Account account) throws AgeValidityException {
			boolean isActive= false;
			Savings saving = (Savings) account;
			int age = year - saving.getDob().getYear();
					
			if (age >= 18) {
				saving.setActive(true);
				isActive = true;
	           
			}
			else {
				throw new AgeValidityException("Your age is less then 18!");
			}
			return isActive;
		}
}


