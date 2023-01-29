package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.MainService;

public class GameScoreController {

	@FXML
	private Label gameID;

	@FXML
	private Button highscore;

	@FXML
	private Button logout;

	@FXML
	private Button newGameButton;

	@FXML
	private void initialize() {
		gameID.setText(Integer.toString(MainService.getCurrentLobby()));
	}

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
	public void clickLogout() {
		try {
			Scene scene_old = logout.getScene();
			Stage stage_primary = (Stage) scene_old.getWindow();
			Scene scene_new = FXMLLoader.load(getClass().getResource("/frame1_main.fxml"));
			stage_primary.setScene(scene_new);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void startNewGame() {
		try {
			Scene scene_old = newGameButton.getScene();
			Stage stage_primary = (Stage) scene_old.getWindow();
			Scene scene_new = FXMLLoader.load(getClass().getResource("/frame5_lobby.fxml"));
			stage_primary.setScene(scene_new);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
