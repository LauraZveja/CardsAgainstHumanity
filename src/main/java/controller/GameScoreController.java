package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DatabaseUtils;
import model.Score;
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
	private TableView<Score> tableView;

	@FXML
	private TableColumn<Score, Integer> placeColumn;

	@FXML
	private TableColumn<Score, String> usernameColumn;

	@FXML
	private TableColumn<Score, Integer> pointsColumn;

	@FXML
	private void initialize() {
		gameID.setText(Integer.toString(MainService.getCurrentLobby()));

		placeColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("place"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Score, String>("username"));
		pointsColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("points"));

		// load data
		tableView.setItems(getScores());
	}

	public ObservableList<Score> getScores() {
		ObservableList<Score> topPlayers = FXCollections.observableArrayList();

		for (Score temp : DatabaseUtils.getTopScore()) {
			topPlayers.add(temp);
		}
		return topPlayers;
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
