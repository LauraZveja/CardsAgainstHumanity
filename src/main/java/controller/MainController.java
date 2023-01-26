package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private Button register;

	public void clickRegister() {

		// ielādējas jaunais skats.
		try {

			Scene mainScene = register.getScene();
			Stage primaryStage = (Stage) mainScene.getWindow();

			Scene registerScene = FXMLLoader.load(getClass().getResource("/frame4_register.fxml"));

			primaryStage.setScene(registerScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private Button login;

	@FXML
	public void clickLogin() {
		try {
			Scene scene_old = login.getScene();
			Stage stage_primary = (Stage) scene_old.getWindow();
			Scene scene_new = FXMLLoader.load(getClass().getResource("/frame3_login.fxml"));
			stage_primary.setScene(scene_new);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
