package com.Services;

import com.AvaliablException.RegisterNullException;

import bank_app_u.bank_app_u_Entity.Account;
import bank_app_u.bank_app_u_Entity.Current;
import bank_app_u.bank_app_u_Entity.TransferInfo;

public class CurrentImpl implements AccountImpl{

	@Override
	public boolean open(Account account) throws Exception {
		boolean isOpen=false;
		Current current=(Current)account;
		if (current.getRegistrationNo() != null) {
			current.setActive(true);
			isOpen = true;
			TransferInfo ti = new TransferInfo();
			ti.setToAccount(account);
		} else {
			throw new RegisterNullException("Registration number is null");
		}
		return isOpen;
	}

}
