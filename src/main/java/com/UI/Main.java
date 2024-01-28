package com.UI;

public class Main {

	public static void main(String[] args) throws Exception {
		AccountUi ui = null;
		try {
			ui = new AccountUi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ui.receiveCustomer();

	}
}



