package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AnswerCard;
import model.Category;
import model.DatabaseUtils;
import model.GameLobby;
import model.Player;
import model.QuestionCard;
import service.MainService;

public class GameLobbyController {

	@FXML
	private Label user;

	@FXML
	private Button highscore;

	@FXML
	private Button logout;

	@FXML
	private Button startGame;

	@FXML
	private ComboBox<Category> categoryComboBox;

	@FXML
	private TextField roundCount;

	private byte rounds;

	@FXML
	private Label errorMessage;

	// GameLobby gameLobby = new GameLobby();

	@FXML
	private void initialize() {
		Player player = MainService.getCurrentPlayer();
		user.setText(player.getUserName());

		if (DatabaseUtils.isPlayerAnAdult(MainService.getCurrentPlayer().getUserName())) {
			categoryComboBox.getItems().addAll(Category.values());
		} else {
			categoryComboBox.getItems().add(Category.UNDER_18);
		}

		categoryComboBox.getSelectionModel().selectFirst();

		roundCount.textProperty().addListener((observable, oldValue, newValue) -> {
			checkRoundCount(newValue);
		});

		errorMessage.textProperty().addListener((observable, oldValue, newValue) -> {
			startGame.setDisable(!newValue.isEmpty());
		});

		startGame.setDisable(true);
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
			Scene scene = logout.getScene();
			Stage primaryStage = (Stage) scene.getWindow();
			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame1_main.fxml"));
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void clickStartGame() {
		if (errorMessage.getText().isEmpty()) {

			byte inputRoundCount = Byte.parseByte(roundCount.getText());
			ArrayList<Player> players = new ArrayList<>();
			players.add(MainService.getCurrentPlayer());
			GameLobby gameLobby = new GameLobby(inputRoundCount, (byte) 4, players, new ArrayList<QuestionCard>(),
					new ArrayList<AnswerCard>());

			try {
				DatabaseUtils.saveGameLobbyToDB(gameLobby);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			MainService.setCurrentLobbyID(gameLobby.getGameLobby_ID());
			MainService.setRoundsInCurrentGame(gameLobby.getRoundCount());
			System.out.println("Current player: " + MainService.getCurrentPlayer().getUserName() + ", Lobby: "
					+ MainService.getCurrentLobby() + ", rounds: " + MainService.getRoundsInCurrentGame()
					+ ", category: " + MainService.getCurrentCategory());

			try {
				Scene scene = logout.getScene();
				Stage primaryStage = (Stage) scene.getWindow();
				Scene mainScene = FXMLLoader.load(getClass().getResource("/frame6_select_answer.fxml"));
				primaryStage.setScene(mainScene);
				primaryStage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void checkCategory() {
		Category selected = categoryComboBox.getSelectionModel().getSelectedItem();
		MainService.setCurrentGameCategory(selected);

	}

	public void checkRoundCount(String newValue) {
		try {
			rounds = Byte.parseByte(newValue);
			if (rounds < 1 || rounds > 127) {
				throw new NumberFormatException();
			}
			errorMessage.setText("");
			startGame.setDisable(false);
		} catch (NumberFormatException e) {
			errorMessage.setText("Round count must be a number between 1 and 127.");
			startGame.setDisable(true);
		}

	}
}
