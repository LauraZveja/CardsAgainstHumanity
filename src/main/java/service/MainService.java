package service;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Category;
import model.DatabaseUtils;
import model.Deck;
import model.Player;
import model.QuestionDeck;

public class MainService extends Application {

	static Player currentPlayer;
	static int currentLobby;
	static byte roundsInCurrentGame;
	static Category currentGameCategory;

	public static void main(String[] args) {
		try {
			DatabaseUtils.createDatabase();
			currentPlayer = new Player();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);

	}

	// --- CURRENT SESSION DETAILS --------

	public static void setCurrentPlayer(String username) {
		currentPlayer.setUserName(username);
		currentGameCategory = Category.UNDER_18;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static Category getCurrentCategory() {
		return currentGameCategory;
	}

	public static int getCurrentLobby() {
		return currentLobby;
	}

	public static void setCurrentGameCategory(Category category) {
		if (category != null) {
			currentGameCategory = category;
		}
	}

	public static void setCurrentLobbyID(int id) {
		currentLobby = id;
	}

	public static int getRoundsInCurrentGame() {
		return roundsInCurrentGame;
	}

	public static void setRoundsInCurrentGame(byte rounds) {
		roundsInCurrentGame = rounds;
	}

	// -------------------- ANSWERS --------------- //

	/*
	 * updateAdult_answers - pievieno failam sarakstu ar jaunām atbildēm, pārbauda,
	 * vai atbilde jau nav iekš faila
	 */

	public static void dealHand(Player player, Deck deck) {
		if (player != null && deck != null) {
			for (int i = 0; i < 5; i++) {
				player.getAnswers().add(deck.giveMeOneCard());
			}
		} else {
			System.out.println("Error: Player or deck object is null.");
		}
	}

	// READ FILE

	// READ DECK
	public static void readAnswerDeck(Deck deck) {
		System.out.println(deck.toString());
	}

	// READ 1st CARD
	public static void readAnswerDeckFirstCard(Deck deck) {
		System.out.println(deck.getAnswerCards().get(0));
	}

	// DELTE ANSWER

	// -------------------- QUESTIONS --------------- //

	// READ QUESTION DECK
	public static void readQuestionDeck(QuestionDeck questionDeck) {
		System.out.println(questionDeck);
	}

	// READ QUESTION DECK 1ST CARD
	public static void readQuestionDeckFirstCard(QuestionDeck questionDeck) {
		System.out.println(questionDeck.getQuestionCards().get(0).getQuestion());
	}

	// READ FILE

	// CREATE QUESTION

	// UPDATE QUESTION

	// DELETE QUESTION

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene = FXMLLoader.load(getClass().getResource("/frame1_main.fxml"));
		// Scene scene =
		// FXMLLoader.load(getClass().getResource("/frame9_game_results.fxml"));

		// Stage objektam uzstādīt izveidoto scene
		primaryStage.setScene(scene);

		// Stage objekut parādīt
		primaryStage.show();
	}

}
