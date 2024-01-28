package com.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.AvaliablException.AccountAlreadyClosedException;
import com.AvaliablException.AccountInactiveException;
import com.AvaliablException.InsufficientFundsException;
import com.AvaliablException.InvalidPinNumberException;
import com.AvaliablException.TransferLimitExceededException;
import bank_app_u.bank_app_u_Entity.Account;
import bank_app_u.bank_app_u_Entity.TransferInfo;
import bank_app_u.bank_app_u_Entity.TransferLog;

public class AccountManager {
	Date date = new Date();
	Scanner sc = new Scanner(System.in);

	public boolean open(Account account) throws Exception {
		boolean isOpened = false;
		AccountImpl accimpl = AccountImplFactory.create(account.getClass().getSimpleName());
		isOpened = accimpl.open(account);
		generate(account);
		TransferLog.storeaccount.add(account);
		return isOpened;
	}

	public boolean close(Account account, int accNum) throws AccountAlreadyClosedException {
		boolean isClosed = false;
		if (account.isActive() == false) {
			throw new AccountAlreadyClosedException("Account is already closed.");
		} else {
			if (account.getAccNo() == accNum) {
				account.setActive(false);
				account.setClosedDate(java.time.LocalDate.now());
				isClosed = true;
			}
		}
		return isClosed;
	}

	public boolean withdraw(Account account, double amountToWithdraw, int pinNumber)
	        throws AccountAlreadyClosedException, InsufficientFundsException, InvalidPinNumberException {
	    boolean isWithdrawn = false;

	    if (account.isActive()) {
	        if (account.getPinNumber() == pinNumber) {
	            if (account.getBalance() >= amountToWithdraw && amountToWithdraw > 0) {
	                account.setBalance(account.getBalance() - amountToWithdraw);
	                isWithdrawn = true;
	                System.out.println("Withdraw done successfully");
	                System.out.println("Your balance is: " + account.getBalance());
	            } else {
	                throw new InsufficientFundsException("Insufficient Balance");
	            }
	        } else {
	            throw new InvalidPinNumberException("Invalid Pin");
	        }
	    } else {
	        throw new AccountAlreadyClosedException("Account is already closed");
	    }

	    return isWithdrawn;
	}
	
	public boolean deposit(Account account, double amountToDeposit, int pin) throws InvalidPinNumberException, AccountInactiveException {
	    boolean isDeposit = false;
	    if (account.getPinNumber() == pin) {
	    if (checkIfAccountIsActive(account)) {
	        if (checkPinNumber(account, pin)) {
	            account.setBalance(account.getBalance() + amountToDeposit);
	            isDeposit = true;
	        }
	    }else {
	    	throw new AccountInactiveException("Account is Inactive");
	    }

	    return isDeposit;
	}else {
		throw new InvalidPinNumberException("Invalid Pin");
	}
	}

	
	public boolean transferFunds(TransferInfo transferInfo, int pin, double amountToTransfer)
			throws TransferLimitExceededException, AccountInactiveException, InvalidPinNumberException,
			InsufficientFundsException {
		boolean isTransferred = false;
		if (checkIfAccountIsActive(transferInfo.getFromAccount())) {
			if (checkIfAccountIsActive(transferInfo.getToAccount())) {
				if (checkPinNumber(transferInfo.getFromAccount(), pin)) {
					if (checkIfFundsAreAvailable(transferInfo.getFromAccount(), amountToTransfer)) {
						if (checkLimitForTheDay(transferInfo.getFromAccount(), amountToTransfer)) {
							transferFundsToAccount(transferInfo, amountToTransfer);
							isTransferred = true;

						}
					}
				}
			}
		}

		return isTransferred;
	}

	public boolean checkIfAccountIsActive(Account account) throws AccountInactiveException {
		if (account.isActive() == false) {
			throw new AccountInactiveException("Your account is not active");
		}
		return true;
	}

	public boolean checkPinNumber(Account account, int pinNumber) throws InvalidPinNumberException {
		if (account.getPinNumber() != pinNumber) {
			throw new InvalidPinNumberException("Wrong Pin");
		}
		return true;
	}

	public boolean checkIfFundsAreAvailable(Account account, double ammountToTransfer)
			throws InsufficientFundsException {
		if (account.getBalance() < ammountToTransfer) {
			throw new InsufficientFundsException("Insufficient balance");
		}
		return true;
	}

	public boolean checkLimitForTheDay(Account account, double amountToTransfer) throws TransferLimitExceededException {
		double amountAlreadyTransfer = 0;

		for (TransferInfo transferinfo : TransferLog.transferlog) {
			if (transferinfo.getFromAccount().getAccNo() == account.getAccNo()) {
				amountToTransfer += transferinfo.getAmountToTransfer();
			}
		}

		double transferLimit = PrivilegeManager.getPrivilegeLimitforTransfer(account.getPrivilege());

		if ((amountAlreadyTransfer + amountToTransfer) >= transferLimit) {
			throw new TransferLimitExceededException("You reached today limit");
		}
		return true;
	}

	public boolean transferFundsToAccount(TransferInfo tranferinfo, double amount) {
		boolean isTransfered = false;

		tranferinfo.getToAccount().setBalance(tranferinfo.getToAccount().getBalance() + amount);
		tranferinfo.getFromAccount().setBalance(tranferinfo.getFromAccount().getBalance() - amount);

		TransferLog.transferlog.add(tranferinfo);
		isTransfered = true;
		return isTransfered;
	}

	public void generate(Account account) {
		int pin = (int) (Math.random() * 1000 + 999);
		int accNumber = (int) (Math.random() * 1000 + 9999);
		account.setPinNumber(pin);
		account.setAccNo(accNumber);

		System.out.println("Your account number is: " + account.getAccNo());
		System.out.println("Your pin number is :" + account.getPinNumber());

	}
}
