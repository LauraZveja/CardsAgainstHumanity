package controller;

import java.io.IOException;

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
	private void initialize() {
		genderComboBox.setItems(FXCollections.observableArrayList(Gender.values()));
		genderComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedGender = newValue;
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
		
		//Create Player object. 
		
		String username = enteredUsername.getText();
		
		//Create Player object from provided data.
		Player player = new Player();
		
		
	}

}
