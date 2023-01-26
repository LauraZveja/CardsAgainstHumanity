package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegistrationController {
	
	@FXML
	private Button exitToMain;
	
	
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

}
