package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {

	private String password;
	private String userName;
	private Calendar dateOfBirth;
	private String email;
	private Calendar registrationDate;
	private Gender gender;
	private Country country;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// parole satur vismaz vienu ciparu, vismaz vienu mazo un lielo burtu, vismaz
		// vienu special character un ir 8-20 zimes gara.
		if (password != null
				&& password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#%^&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
			this.password = password;
		} else {
			this.password = "password123!";
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		/*
		 * starts with alphanumeric character, length must between 5-20,
		 * dot/hyphen/underscore only after min 3 alphanumeric characters and cannot
		 * appear consecutively
		 */
		if (userName != null && userName.matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$")) {
			this.userName = userName;
		} else {
			this.userName = "Unknown";
		}
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		Calendar checkDateBefore = new GregorianCalendar(2010, Calendar.DECEMBER, 31);
		Calendar checkDateAfter = new GregorianCalendar(1900, Calendar.DECEMBER, 31);

		if (dateOfBirth.after(checkDateAfter) && dateOfBirth.before(checkDateBefore)) {
			this.dateOfBirth = dateOfBirth;
		} else {
			// varbūt var padomāt, ko mēs daram ar spēlētāju, kurš norādījis neatbilstošu
			// vecumu. Pie reģistrācijas pārbaudām?
			// iegūstot šodienas datumu un tad skatās, ja nostrādājis defaultais vai jaunāks
			// par 18, tad neļauj reģistrēt spēlētāju?
			this.dateOfBirth = new GregorianCalendar(2006, Calendar.JANUARY, 1);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null && email.matches("^(.+)@(.+)$")) {
			this.email = email;
		} else {
			this.email = "Unknown";
		}
		;
	}

	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate() {

		Calendar today = Calendar.getInstance();

		this.registrationDate = today;

	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		if (gender != null) {
			this.gender = gender;
		} else {
			this.gender = Gender.OTHER;

		}
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		if (country != null) {
			this.country = country;
		} else {
			this.country = Country.OTHER;

		}
	}

	public User() {
		setPassword("password123!");
		setUserName("Unknown");
		setDateOfBirth(new GregorianCalendar(2010, Calendar.JANUARY, 1));
		setEmail("Unknown");
		setRegistrationDate();
		setGender(Gender.OTHER);
		setCountry(Country.OTHER);
	}

	public User(String password, String userName, Calendar dateOfBirth) {
		setPassword(password);
		setUserName(userName);
		setDateOfBirth(dateOfBirth);
		setEmail("Unknown");
		setRegistrationDate();
		setGender(Gender.OTHER);
		setCountry(Country.OTHER);
	}

	public User(String password, String userName, Calendar dateOfBirth, String email, Gender gender, Country country) {
		setPassword(password);
		setUserName(userName);
		setDateOfBirth(dateOfBirth);
		setEmail(email);
		setRegistrationDate();
		setGender(gender);
		setCountry(country);
	}

	public String toString() {
		return "User name:" + userName + ", date of birth: " + dateOfBirth.get(Calendar.DAY_OF_MONTH) + "/"
				+ dateOfBirth.get((Calendar.MONTH) + 1) + "/" + dateOfBirth.get(Calendar.YEAR) + ", email: " + email
				+ ", registration date: " + registrationDate.get(Calendar.DAY_OF_MONTH) + "/"
				+ registrationDate.get((Calendar.MONTH) + 1) + "/" + registrationDate.get(Calendar.YEAR) + ", gender: "
				+ gender + ", country: " + country;
	}

}
