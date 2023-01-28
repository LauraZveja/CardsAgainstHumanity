package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameScoreController {

	@FXML
	private Button highscore;

	@FXML
	private Button logoutButton;

	@FXML
	public void clickHighscore() {
		try {
			Scene scene = FXMLLoader.load(getClass().getResource("/frame2_highscore.fxml"));

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void logout() {

	}

}
