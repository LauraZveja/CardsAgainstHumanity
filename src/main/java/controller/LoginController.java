package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DatabaseUtils;
import service.MainService;

public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button buttonLoginUser;

	@FXML
	public void loginButtonClick() {
		String playerUsername = usernameField.getText();
		String playerPassword = passwordField.getText();

		if (DatabaseUtils.isPlayerPasswordCorrect(playerUsername, playerPassword)) {
			Alert alert_correct = new Alert(AlertType.INFORMATION, "Login succesful!");
			alert_correct.showAndWait();

			MainService.setCurrentPlayer(playerUsername);
			System.out.println(
					"LOGIN: Playerun:" + playerUsername + " current: " + MainService.getCurrentPlayer().getUserName());
			try {
				Scene scene_old = buttonLoginUser.getScene();
				Stage stage_primary = (Stage) scene_old.getWindow();
				Scene scene_new = FXMLLoader.load(getClass().getResource("/frame5_lobby.fxml"));
				stage_primary.setScene(scene_new);
				stage_primary.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			Alert alert_wrong = new Alert(AlertType.WARNING, "Wrong username and/or password");
			alert_wrong.showAndWait();
		}

	}

	@FXML
	private Button buttonReturnToMainMenuFromLogin;

	@FXML
	public void returnToMainMenuFromLogin() {
		try {
			Scene scene_old = buttonLoginUser.getScene();
			Stage stage_primary = (Stage) scene_old.getWindow();
			Scene scene_new = FXMLLoader.load(getClass().getResource("/frame1_main.fxml"));
			stage_primary.setScene(scene_new);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}