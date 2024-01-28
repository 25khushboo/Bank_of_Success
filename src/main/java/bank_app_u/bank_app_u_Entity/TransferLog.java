package bank_app_u.bank_app_u_Entity;
import java.util.ArrayList;
import java.util.List;

public class TransferLog {
	public static List<TransferInfo> transferlog = new ArrayList<TransferInfo>();
	public static List<Account> storeaccount = new ArrayList<>();

	public static List<TransferInfo> getTransferlog() {
		return transferlog;
	}

	public static void setTransferlog(List<TransferInfo> transferlog) {
		TransferLog.transferlog = transferlog;
	}

	public static List<Account> getStoreaccount() {
		return storeaccount;
	}

	public static void setStoreaccount(List<Account> storeaccount) {
		TransferLog.storeaccount = storeaccount;
	}

	public static void addAccount (Account account) {
		storeaccount.add(account);
	}
}


