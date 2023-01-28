package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AnswerCard;
import model.Category;
import model.DatabaseUtils;
import model.Deck;
import model.GameLobby;
import model.Player;
import model.QuestionCard;
import model.QuestionDeck;
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
	private ComboBox categoryComboBox;

	@FXML
	private TextField roundCount;

	GameLobby gameLobby = new GameLobby();

	@FXML
	private void initialize() {
		Player player = MainService.getCurrentPlayer();
		user.setText(player.getUserName());
		System.out.println("Player in initialize: " + player);
		categoryComboBox.setItems(FXCollections.observableArrayList(Category.values()));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void clickStartGame() {
		byte inputRoundCount = Byte.parseByte(roundCount.getText());

		ArrayList<Player> players = new ArrayList<>();
		players.add(MainService.getCurrentPlayer());

		QuestionDeck questionsDeck = new QuestionDeck(MainService.getCurrentCategory());
		ArrayList<QuestionCard> inputQuestions = questionsDeck.getQuestionCards();

		Deck answersDeck = new Deck(MainService.getCurrentCategory());
		ArrayList<AnswerCard> inputAnswerDeck = answersDeck.getAnswerCards();

		GameLobby gameLobby = new GameLobby(inputRoundCount, (byte) 4, players, inputQuestions, inputAnswerDeck);

		try {
			DatabaseUtils.saveGameLobbyToDB(gameLobby);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MainService.setCurrentLobbyID(gameLobby.getGameLobby_ID());
		MainService.setRoundsInCurrentGame(gameLobby.getRoundCount());
		System.out.println("Current player: " + MainService.getCurrentPlayer().getUserName() + "Lobby: "
				+ MainService.getCurrentLobby() + ", rounds: " + MainService.getRoundsInCurrentGame() + ", category: "
				+ MainService.getCurrentCategory());

		try {
			Scene scene = logout.getScene();
			Stage primaryStage = (Stage) scene.getWindow();
			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame6_select_answer.fxml"));
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO: SAKĀRTOT ALERTS
	@FXML
	public void checkCategory() {
		System.out.println("CHECK category");
		Category selected = (Category) categoryComboBox.getSelectionModel().getSelectedItem();

		if (!DatabaseUtils.isPlayerAnAdult(MainService.getCurrentPlayer().getUserName())
				&& selected.equals(Category.ADULT)) {
			// if (MainService.getCurrentCategory().equals(Category.UNDER_18) &&
			// selected.equals(Category.ADULT)) {
			Alert alertWrong = new Alert(AlertType.WARNING, "You cannot select adult category if you are under 18! ");
			alertWrong.showAndWait();
		} else {
			MainService.setCurrentGameCategory(selected);

		}

	}

	// TODO: uzlikt, lai nevar ievadīt String + sakārtot alerts
	public void checkRoundCount() {
		System.out.println("CHECK ROUND COUNT");
		String enteredRoundCount = roundCount.getText();
		System.out.println("CHECK enteredRoundCount" + enteredRoundCount);
		byte count = Byte.parseByte(enteredRoundCount);
		System.out.println("CHECK count" + count);
		if (count < 0 || count > 127) {
			startGame.setDisable(true);
			Alert alert_wrong = new Alert(AlertType.WARNING, "Number of rounds per game must be 1-127");
			alert_wrong.showAndWait();
		} else {
			startGame.setDisable(false);
			gameLobby.setRoundCount(count);
		}

	}
}
