package bank_app_u.bank_app_u;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.AvaliablException.*;
import com.Services.AccountManager;
import bank_app_u.bank_app_u_Entity.Account;
import bank_app_u.bank_app_u_Entity.Savings;
import bank_app_u.bank_app_u_Entity.TransferInfo;
import bank_app_u.bank_app_u_Entity.TransferLog;
/**
 * Unit test for simple App.
 */
public class AppTest {

	AccountManager manager = new AccountManager();
	@Test 
	void depositMethodTest() throws InvalidPinNumberException, AccountInactiveException
	{
		Account account2 = new Savings();
		account2.setPinNumber(1234);
		account2.setActive(true);
		TransferLog.storeaccount.add(account2);
		boolean depositToTest = manager.deposit(account2,1000000,1234);
		assertTrue(depositToTest);
	}
	@Test
	void transferMethodTest() throws Exception
	{
		Account account3 = new Savings();
		Account account4 = new Savings();
		
		account3.setActive(true);
		account4.setActive(true);
		
		account3.setPinNumber(1234);
		account4.setPinNumber(5678);
		
		account3.setBalance(10000);
		account4.setBalance(10000);
		
		TransferInfo t1 = new TransferInfo();
		t1.setFromAccount(account3);
		t1.setToAccount(account4);
		
		boolean transferMethodTeststatus=manager.transferFunds(t1,1234,200);
		assertTrue(transferMethodTeststatus);
		
	}
	
	@Test 
	void openToTest() throws Exception {
		Account account5 = new Savings();
		Savings savings = (Savings)account5;
		
		LocalDate localdate = LocalDate.of(2000, 10, 10);
		account5.setActive(true);
		savings.setDob(localdate);
		savings.setPinNumber(1234);
		
		boolean openToTestStatus = manager.open(account5);
		assertTrue(openToTestStatus);
		
	}
	@Test
	void closeToTest() throws AccountAlreadyClosedException
	{
		Account account6 = new Savings();
		account6.setActive(true);
		account6.setPinNumber(1234);
		account6.setAccNo(1001);
		
		boolean closeToTestStatus = manager.close(account6,1001);
		assertTrue(closeToTestStatus);
	}
	@Test
	void chechIfAccountIsActiveTest() throws AccountInactiveException{
		Account account7= new Savings();
		account7.setActive(true);
		boolean chechIfAccountIsActiveStatus= manager.checkIfAccountIsActive(account7);
		assertTrue(chechIfAccountIsActiveStatus);
	}
	
	@Test
	void checkPinNumberTest() throws InvalidPinNumberException{
		Account account8= new Savings();
		account8.setActive(true);
		account8.setPinNumber(1234);
		boolean checkPinNumberStatus= manager.checkPinNumber(account8, 1234);
		assertTrue(checkPinNumberStatus);
	}
	
	@Test
	void checkIfFundsAreAvailableTest1() throws InsufficientFundsException{
		Account account9= new Savings();
		account9.setActive(true);
		account9.setPinNumber(1234);
		account9.setBalance(10000);
		boolean checkIfFundsAreAvailableStatus= manager.checkIfFundsAreAvailable(account9, 5000);
		assertTrue(checkIfFundsAreAvailableStatus);
	}
	
	@Test
	void checkLimitForTheDayTest1() throws TransferLimitExceededException{
		Account account10= new Savings();
		account10.setActive(true);
		account10.setPinNumber(1234);
		account10.setBalance(10000);
		boolean checkLimitForTheDayTestStatus= manager.checkLimitForTheDay(account10, 9000);
		assertTrue(checkLimitForTheDayTestStatus);
	}
	
	@Test
	void  transferFundsToAccountTest() {
		Account account11 = new Savings();
		account11.setActive(true);
		account11.setPinNumber(1234);
		account11.setBalance(10000);
		
		Account account12 = new Savings();
		account12.setActive(true);
		account12.setPinNumber(1234);
		account12.setBalance(10000);

		TransferInfo t2 = new TransferInfo();
		t2.setFromAccount(account11);
		t2.setToAccount(account12);
		
		boolean transferFundsToAccountStatus = manager.transferFundsToAccount(t2, 100);
		assertTrue(transferFundsToAccountStatus);
	}
	
	//Adding new test cases here
	@Test
	void withdrawMethodAccountException()
			throws AccountAlreadyClosedException {
 
		Account account7 = new Savings();
		account7.setPinNumber(1234);
		account7.setBalance(10000);
		account7.setActive(false);
 
		TransferLog.storeaccount.add(account7);
		assertThrows(AccountAlreadyClosedException.class, ()-> manager.withdraw(account7, 1000, 1234));
	}

	
	
	@Test
	void withdrawMethodInvalidPinException()
			throws InvalidPinNumberException {
		List<Account> accounts = new ArrayList<>();
		Account account9 = new Savings();
		account9.setPinNumber(1234);
		account9.setBalance(10000);
		account9.setActive(true);
 
		TransferLog.storeaccount = accounts;
		assertThrows(InvalidPinNumberException.class, ()-> manager.withdraw(account9, 1000, 1238));
	}
	
	@Test
	void closeToTestException() throws AccountAlreadyClosedException
	{
		Account account6 = new Savings();
		account6.setActive(false);
		account6.setPinNumber(1234);
		account6.setAccNo(1001);
		assertThrows(AccountAlreadyClosedException.class, ()-> manager.close(account6, 1001));
	}
	
	@Test
	void withdrawMethodTest()
			throws AccountAlreadyClosedException, InsufficientFundsException, InvalidPinNumberException {
		List<Account> accounts = new ArrayList<>();
		Account account1 = new Savings();
		account1.setPinNumber(1234);
		account1.setBalance(100000);
		account1.setActive(true);
		accounts.add(account1);
		TransferLog.storeaccount = accounts;
		
		boolean withdrawMethodTestStatus = manager.withdraw(account1, 2000, 1234);

		assertTrue(withdrawMethodTestStatus);
	}
	@Test
	void withdrawMethodInsufficientException() throws InsufficientFundsException {
 
		Account account8 = new Savings();
		account8.setPinNumber(1234);
		account8.setBalance(2000);
		account8.setActive(true);
 
		TransferLog.storeaccount.add(account8);
		assertThrows(InsufficientFundsException.class, ()-> manager.withdraw(account8, 200090, 1234));
	}
	
	@Test
	void depositMethodAccountInactive() throws AccountInactiveException{
		Account account9 = new Savings();
		account9.setPinNumber(1234);
		account9.setActive(false);
		
		TransferLog.storeaccount.add(account9);
		assertThrows(AccountInactiveException.class, () -> manager.deposit(account9, 10000, 1234));
		}
	@Test
	void depositMethodInvalidPin() throws InvalidPinNumberException{
		Account account9 = new Savings();
		account9.setPinNumber(1234);
		account9.setActive(true);
		
		TransferLog.storeaccount.add(account9);
		assertThrows(InvalidPinNumberException.class, () -> manager.deposit(account9, 10000, 1235));
		}
	
	
}