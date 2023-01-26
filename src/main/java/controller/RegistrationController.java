package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Gender;
import model.Player;
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

	public void register() {

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
		System.out.println("User name:" + username + ", date of birth: " + dateFormat.format(dateOfBirth.getTime()) +  ", email: " + email + ", gender: " + gender + ", country: " + country);

	}

}
