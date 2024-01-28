package com.UI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.AvaliablException.AccountInactiveException;
import com.AvaliablException.InsufficientFundsException;
import com.AvaliablException.InvalidPinNumberException;
import bank_app_u.bank_app_u_Entity.Account;
import com.Services.AccountManager;
import bank_app_u.bank_app_u_Entity.Current;
import bank_app_u.bank_app_u_Entity.Privilege;
import bank_app_u.bank_app_u_Entity.Savings;
import bank_app_u.bank_app_u_Entity.TransferInfo;
import bank_app_u.bank_app_u_Entity.TransferLog;

//Boundary class
public class AccountUi {
	Scanner sc = new Scanner(System.in);
	AccountManager manager = null;

	public void receiveCustomer() throws Exception {
		System.out.println("Hello sir,How may I help you");
		openAccount();
	}
	public void openAccount() throws Exception {
		Scanner sc = new Scanner(System.in);
		Savings saving = null;
		Current current = null;
		System.out.println("Which Account do you want to open 1.Savings - 2.Current");
		String choice = sc.nextLine();
		System.out.println("Please fill the form and hand over to us");
		// User enter valid input we will get the input from user
		try {
		if (choice.equals("1")) {
			saving = new Savings();
			System.out.println("Enter your Name");
			saving.setName(sc.nextLine());
			System.out.println("Enter your Gender");
			saving.setGender(sc.nextLine());
			System.out.println("Enter your phone number");
			saving.setPhoneNumber(sc.nextLine());
			System.out.println("Enter your D.O.B (YYYY-MM-DD)");
			LocalDate date = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
			saving.setDob(date);
			sc.nextLine();
			System.out.println("Enter the Privilege Type\nPremium\nGold\nSilver");
			
			String setPrivilege = sc.nextLine();
			
			try {
				if (setPrivilege.equals("Premium"))
					saving.setPrivilege(Privilege.PREMIUM);
				else if (setPrivilege.equals("Gold"))
					saving.setPrivilege(Privilege.GOLD);
				else if (setPrivilege.equals("Silver"))
					saving.setPrivilege(Privilege.SILVER);

				else
					throw new Exception("Enter a valid Privilege");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
			

		}
		else if (choice.equals("2")) {
			current = new Current();
			System.out.println("Enter your name");
			current.setName(sc.nextLine());
			System.out.println("Enter the Company name");
			current.setCompanyName(sc.nextLine());
			System.out.println("Enter the Registration Number");
			current.setRegistrationNo(sc.nextLine());
			System.out.println("Enter the Privilege\nPremium\nGold\nSilver");
			String setPrivilage = sc.nextLine();
			try {
				if (setPrivilage.equals("Premium"))
					current.setPrivilege(Privilege.PREMIUM);
				else if (setPrivilage.equals("Gold"))
					current.setPrivilege(Privilege.GOLD);
				else if (setPrivilage.equals("Silver"))
					current.setPrivilege(Privilege.SILVER);
				else
					throw new Exception("Something went wrong!");
			} catch (Exception e) {
				System.out.println(e.getMessage());

				System.exit(0);
			}
		}
		else
			throw new Exception("Sorry,you have entered a wrong choice");
	} catch (Exception e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
		

		boolean isOpened = false;
		try {
			manager = new AccountManager();
			if (choice.equals("1")) {
				isOpened = manager.open(saving);

				execute(isOpened, saving);
			} else if (choice.equals("2")) {
				isOpened = manager.open(current);

				execute(isOpened, current);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void execute(boolean isOpen, Account account) throws Exception {
		boolean check = true;
	    if (isOpen) {
	        do {
	            System.out.println("The operations available");
	            System.out.println("1.Withdraw\n2.Deposit\n3.Transfer\n4.Create another account\n5.Check Account Balance\n6.Close the account\n7.Display\n8.Exit");
	            int num = sc.nextInt();

	            switch (num) {
	            case 1: {
	                System.out.println("Enter the account number:");
	                int accNo = sc.nextInt();
	                Account selectedAccount = null;

	                for (Account acc : TransferLog.storeaccount) {
	                    if (acc.getAccNo() == accNo) {
	                        selectedAccount = acc;
	                        break;
	                    }
	                }

	                if (selectedAccount != null) {
	                    System.out.println("Enter the Pin");
	                    int pin = sc.nextInt();
	                    System.out.println("Enter the Amount to Withdraw");
	                    double amount = sc.nextDouble();

	                    try {
	                        if (manager.withdraw(selectedAccount, amount, pin)) {
	                            System.out.println("Withdraw done successfully");
	                        }
	                    } catch (Exception e) {
	                        throw new InvalidPinNumberException("Wrong pin");
	                    }
	                } else {
	                   throw new  AccountInactiveException("Invalid Account Number");
	                }

	                break;
	            }

	            case 2: {
	                System.out.println("Enter the account number:");
	                int accNo = sc.nextInt();
	                Account selectedAccount = null;

	                for (Account acc : TransferLog.storeaccount) {
	                    if (acc.getAccNo() == accNo) {
	                        selectedAccount = acc;
	                        break;
	                    }
	                }

	                if (selectedAccount != null) {
	                    System.out.println("Enter the Pin");
	                    int pin = sc.nextInt();
	                    System.out.println("Enter the amount to deposit");
	                    double amountToDeposit = sc.nextDouble();

	                    try {
	                        if (manager.deposit(selectedAccount, amountToDeposit, pin)) {
	                            System.out.println("Deposit done successfully");
	                        } else {
	                            System.out.println("Deposit failed");
	                        }
	                    } catch (Exception e) {
	                        throw new InvalidPinNumberException("Error: " + e.getMessage());
	                    }
	                } else {
	                    System.out.println("Invalid Account Number");
	                }

	                break;
	            }
				case 3: {
					System.out.println("Enter from-account number to transfer");
					int fromAcc = sc.nextInt();
					Account setFrom = null;
					Account setTo = null;
					TransferInfo info = new TransferInfo();
					System.out.println("Enter the to-Account number");
					int toAcc = sc.nextInt();
					System.out.println("Enter the Pin");
					int pin = sc.nextInt();
					System.out.println("Enter the amount to transfer");
					double amountToTransfer= sc.nextDouble();

					for (Account account1 : TransferLog.storeaccount) {
						if (account1.getAccNo() == fromAcc) {
							info.setFromAccount(account1);
							setFrom = account1;
						} else if (account1.getAccNo() == toAcc) {
							info.setToAccount(account1);
							setTo = account1;
						}
					}

					if (manager.transferFunds(info,pin, amountToTransfer)) {
						System.out.println("Transaction done sucessfully.");
						System.out.println("Your Balance is :" + setFrom.getBalance());
					}
					break;
				}
				case 4: {
					openAccount();
					break;
				}
				case 5: {
					System.out.println("Enter the account number:");
					int accno = sc.nextInt();
					for(Account account2: TransferLog.storeaccount) {
						if(account2.getAccNo()==accno)
						{
							account = account2;
						}
					}
					System.out.println("Enter the Pin ");
					int n = sc.nextInt();
					for (Account account1 : TransferLog.storeaccount) {
						if (account1.getPinNumber() == n) {
							System.out.println("Your balance is :" + account1.getBalance());}
						else {
							throw new InvalidPinNumberException("Invalid pin");
						}
					}
					break;
				}
				case 6: {
					check = false;
					System.out.println("Press 'y' to close the account or else press enter");
					sc.nextLine();
					if ((sc.nextLine()).equals("y")) {
						System.out.println("Enter the Account Number to be closed.");
						int accNum = sc.nextInt();
						
						if (manager.close(account,accNum))
							System.out.println("Closing Date :" + java.time.LocalDate.now());
					}
					break;
				}
				case 7: {
				    System.out.println("Enter the account number:");
				    int accNumber = sc.nextInt();
				    Account selectedAccount = null;

				    for (Account acc : TransferLog.storeaccount) {
				        if (acc.getAccNo() == accNumber) {
				            selectedAccount = acc;
				            break;
				        }
				    }

				    if (selectedAccount != null) {
				    	System.out.println("---------------------------");
				        System.out.println("Account Details:");
				        System.out.println("Account Number: " + selectedAccount.getAccNo());
				        System.out.println("Account Type: " + selectedAccount.getClass().getSimpleName());
				        System.out.println("Account Holder: " + selectedAccount.getName());
				        System.out.println("Balance: " + selectedAccount.getBalance());
				        System.out.println("Account Status: " + (selectedAccount.isActive() ? "Active" : "Inactive"));
				        System.out.println("---------------------------");
				    } else {
				        System.out.println("Account not found!");
				    }

				    break;
				}
				case 8: {
					System.out.println("Exiting... Thank you!");
	                System.exit(0);
				}
				}
			} while (check);

		} else {
			System.out.println("Please check again after some time");
		}
	}
	
}




