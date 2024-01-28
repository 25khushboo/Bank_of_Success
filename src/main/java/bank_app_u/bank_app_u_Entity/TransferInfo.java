package bank_app_u.bank_app_u_Entity;

public class TransferInfo {
	private Account fromAccount;
	private Account toAccount;
	private double amountToTransfer;
	private TransferMode transferMode;
	private int PinNumber;
	

	public int getPinNumber() {
		return PinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.PinNumber = pinNumber;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmountToTransfer() {
		return amountToTransfer;
	}

	public void setAmountToTransfer(double amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}

	public TransferMode getTransferMode() {
		return transferMode;
	}

	public void setTransferMode(TransferMode transferMode) {
		this.transferMode = transferMode;
	}
	
}
