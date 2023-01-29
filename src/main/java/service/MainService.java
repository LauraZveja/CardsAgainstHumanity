package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Category;
import model.DatabaseUtils;
import model.Deck;
import model.Player;
import model.QuestionDeck;
import model.Score;

public class MainService extends Application {

	static Player currentPlayer;
	static int currentLobby;
	static byte roundsInCurrentGame;
	static Category currentGameCategory;
	static ArrayList<Score> currentGameScores;

	public static void main(String[] args) {
		try {
			DatabaseUtils.createDatabase();
			currentPlayer = new Player();
			currentGameScores = new ArrayList<>();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * currentPlayer.setUserName("User1"); currentLobby = 100;
		 * currentGameScores.add(new Score(1, "User1", 20)); currentGameScores.add(new
		 * Score(2, "User2", 14)); currentGameScores.add(new Score(3, "User3", 13));
		 * currentGameScores.add(new Score(4, "User4", 10));
		 */
		launch(args);

		// insertAnswer(Category.ADULT, "Skat, skat tur laukā, zvaigznīte!");
		// insertAnswer(Category.ADULT, "Skat, skat tur laukā, zvaigznīte")

		/*
		 * ArrayList<String> new_adult_answers = new ArrayList<>();
		 * new_adult_answers.add("Testing, testing, testing!");
		 * new_adult_answers.add("A moment of silence.");
		 * new_adult_answers.add("11111111111111."); new_adult_answers.add("22222222.");
		 * new_adult_answers.add("A moment of silence.");
		 * 
		 * 
		 * updateAdult_answers(Category.ADULT, new_adult_answers);
		 * 
		 * try { // Use BufferedReader to read the file BufferedReader reader = new
		 * BufferedReader(new FileReader("src/main/resources/ADULT.txt")); String line;
		 * // Read the file line by line while ((line = reader.readLine()) != null) { //
		 * Print the contents of the file System.out.println(line); } // Close the
		 * reader reader.close(); } catch (IOException e) { e.printStackTrace(); }
		 */

		// Pārbaude, vai katram spēlētājam piešķir 5 dažādas atbilžu kārtis.
		/*
		 * Player speletajs = new Player(); Deck kava = new Deck(Category.ADULT);
		 * dealHand(speletajs, kava);
		 * 
		 * Player speletajs2 = new Player(); dealHand(speletajs2, kava);
		 * 
		 * System.out.println(speletajs.getAnswers());
		 * System.out.println(speletajs2.getAnswers()); //
		 * updateAnswerDeckByCategory(Category.ADULT, new_adult_answers);
		 * //deleteAnswerFromAnswerDeckByCategory(Category.ADULT, "11111111111111");
		 * //readAnswerDeckFileByCategory(Category.ADULT);
		 */
		// Deck deck1 = new Deck(Category.ADULT);
		// readAnswerDeck(deck1);

		// System.out.println(deck1);
		// readAnswerDeckFirstCard(deck1);

		// -------------- QUESTIONS ---------------------- //
		// QuestionDeck questionDeck = new QuestionDeck(Category.UNDER_18);

		// QuestionDeck questionDeck2 = new QuestionDeck(Category.ADULT);
		// readQuestionDeck(questionDeck2);

		/*
		 * ArrayList<String> newQuestions = new ArrayList<>(); newQuestions.
		 * add("I swear to God I am gonna murder my husband if he does not shut up about _____"
		 * ); newQuestions.add("Yo, is _____ racist?");
		 * newQuestions.add("I have solved politics. My solution is ______");
		 * 
		 * updateQuestions(Category.ADULT, newQuestions);
		 * readQuestionDeckFileByCategory(Category.ADULT);
		 * deleteQuestionFromQuestionDeckByCategory(Category.ADULT,
		 * "Instead of the Jews, Hitler should have worried more about ____.");
		 * readQuestionDeckFileByCategory(Category.ADULT);
		 */

	}

	// --- CURRENT SESSION DETAILS --------

	public static void setCurrentPlayer(String username) {
		currentPlayer.setUserName(username);
		currentGameCategory = Category.UNDER_18;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static ArrayList<Score> getCurrentGameScores() {
		return currentGameScores;
	}

	public static void setCurrentGameScores(ArrayList<Score> scores) {
		currentGameScores = scores;
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
