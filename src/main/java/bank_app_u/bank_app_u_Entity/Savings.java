package bank_app_u.bank_app_u_Entity;

import java.time.LocalDate;
import java.util.Date;

public class Savings extends Account {
	
	private LocalDate dob;
	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	private String gender;
	private String phoneNumber;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
