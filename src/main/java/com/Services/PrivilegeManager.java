package com.Services;

import bank_app_u.bank_app_u_Entity.Privilege;

public class PrivilegeManager {
	public static double getPrivilegeLimitforTransfer(Privilege privilege) {
		String type1="PREMIUM";
		String type2="GOLD";
		String type3="SILVER";
		if (type1.equals(privilege.PREMIUM.toString())) {
			return 100000.0;
		}
		else if(type2.equals(privilege.GOLD.toString())) {
			return 50000.0;
		}
		else if(type3.equals(privilege.SILVER.toString())) {
			return 25000.0;
		}
		return 0;
	}
}

