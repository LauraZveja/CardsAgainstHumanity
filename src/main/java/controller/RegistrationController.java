package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Gender;
import model.Player;
import model.User;
import model.Country;
import model.DatabaseUtils;

import java.util.Date;

public class RegistrationController {

	@FXML
	private Button exitToMain;

	@FXML
	private Label username;

	@FXML
	private TextField enteredUsername;

	@FXML
	private Button register;

	@FXML
	private Label password;

	@FXML
	private PasswordField enteredPassword;

	@FXML
	private Label email;

	@FXML
	private TextField enteredEmail;

	@FXML
	private Label DoB;

	@FXML
	private DatePicker enteredDoB;

	@FXML
	private Label gender;

	@FXML
	private ComboBox<Gender> genderComboBox;

	private Gender selectedGender;

	@FXML
	private Label country;

	@FXML
	private ComboBox<Country> countryComboBox;

	private Country selectedCountry;

	User user = new User();

	@FXML
	private void initialize() {
		genderComboBox.setItems(FXCollections.observableArrayList(Gender.values()));
		genderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			selectedGender = newValue;
		});

		countryComboBox.setItems(FXCollections.observableArrayList(Country.values()));
		countryComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			selectedCountry = newValue;
		});
	}

	public void backToMainScreen() {

		try {

			Scene registrationScene = exitToMain.getScene();
			Stage primaryStage = (Stage) registrationScene.getWindow();

			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame1_main.fxml"));

			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registerPlayer() {

		String password = enteredPassword.getText();
		String username = enteredUsername.getText();
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(Date.from(enteredDoB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		String email = enteredEmail.getText();
		Gender gender = selectedGender;
		Country country = selectedCountry;

		// Create Player object from provided data.
		Player player = new Player(password, username, dateOfBirth, email, gender, country);
		try {
			DatabaseUtils.savePlayerToDB(player);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("User name:" + username + ", date of birth: " + dateFormat.format(dateOfBirth.getTime())
				+ ", email: " + email + ", gender: " + gender + ", country: " + country);
		Alert alert = new Alert(AlertType.INFORMATION, "Welcome to the CAH family. You will be redirected to the Login page.");
		alert.showAndWait();
		
		try {

			Scene registrationScene = exitToMain.getScene();
			Stage primaryStage = (Stage) registrationScene.getWindow();

			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame3_login.fxml"));

			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void checkUsername() {
		String username = enteredUsername.getText();

		if (DatabaseUtils.isUsernameTaken(username)) {
			register.setDisable(true);
			Alert alert_wrong = new Alert(AlertType.WARNING, "Username is already taken.");
			alert_wrong.showAndWait();
		} else {
			user.setUserName(username);
			if (user.getUserName().equals("Unknown")) {
				register.setDisable(true);
				user.setUserName("");
				Alert alertWrong = new Alert(AlertType.WARNING, "Username must meet complexity requirements..");
				alertWrong.showAndWait();
			} else {
				register.setDisable(false);
			}
		}

	}

	public void checkPassword() {
		String password = enteredPassword.getText();
		user.setPassword(password);
		if (user.getPassword().equals("password123!")) {
			register.setDisable(true);
			user.setPassword("");
			Alert alertWrong = new Alert(AlertType.WARNING, "Password must meet complexity requirements.");
			alertWrong.showAndWait();
		} else {
			register.setDisable(false);
		}

	}

	public void checkEmail() {
		String email = enteredEmail.getText();
		user.setEmail(email);
		if (user.getEmail().equals("Unknown")) {
			register.setDisable(true);
			user.setEmail("");
			Alert alertWrong = new Alert(AlertType.WARNING, "Email is not valid.");
			alertWrong.showAndWait();
		} else {
			register.setDisable(false);
		}

	}

	public void checkDoB() {
		LocalDate currentDate = LocalDate.now();
		LocalDate dateOfBirth = enteredDoB.getValue();
		Period period = Period.between(dateOfBirth, currentDate);
		if (period.getYears() < 13) {
			register.setDisable(true);
			Alert alertWrong = new Alert(AlertType.WARNING, "I'm so sorry, kid. Way too young to play this game.");
			alertWrong.showAndWait();
		} else {
			register.setDisable(false);
		}
	}
}
