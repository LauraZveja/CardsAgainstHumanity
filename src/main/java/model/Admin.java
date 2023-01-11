package model;

import java.util.Calendar;

public class Admin extends User {

	// variables
	private static int adminCounter = 1000;
	private int adminID;

	// getters
	public static int getAdminCounter() {
		return adminCounter;
	}

	public void getAdminID() {
		this.adminID = adminID;
	}

	// setters
	public void setAdminID() {
		this.adminID = adminCounter;
		adminCounter++;
	}

	// constructors

	public Admin() {
		super();
		setAdminID();
	}

	public Admin(String password, String userName, Calendar dateOfBirth, String email, Gender gender, Country country) {
		super(password, userName, dateOfBirth, email, gender, country);
		setAdminID();
	}

	public Admin(String password, String userName, Calendar dateOfBirth) {
		super(password, userName, dateOfBirth);
		setAdminID();
	}

	// toString
	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + super.toString() + "]";
	}

}
