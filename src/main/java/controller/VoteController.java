package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import model.AnswerCard;
import model.DatabaseUtils;
import model.GameLobby;
import model.Player;
import model.QuestionCard;
import model.User;
import model.Vote;
import service.MainService;

public class VoteController {

	@FXML
	private RadioButton radioAnswer1;

	@FXML
	private RadioButton radioAnswer2;

	@FXML
	private RadioButton radioAnswer3;

	@FXML
	private Button exitToLobby;

	@FXML
	private Label questionText;

	@FXML
	private Label ComputerAnswer1;

	@FXML
	private Label ComputerAnswer2;

	@FXML
	private Label ComputerAnswer3;

	@FXML
	private Label user;

	@FXML
	private Button voteForBestAnswer;

	@FXML
	private Label GameID;

	static Calendar computerDoB = new GregorianCalendar(1980, Calendar.DECEMBER, 31);

	// STATIC VARIABLE INITIALIZATION TO BE USED BY FRAME 8 AS WELL-------
	public static Vote vote;
	public static Player computerPlayer1;
	public static Player computerPlayer2;
	public static Player computerPlayer3;

	// LOCAL VARIABLES
	private int currentGameID;
	private Player player;
	private AnswerCard radioAnswer;
	private AnswerCard computerAnswer1;
	private AnswerCard computerAnswer2;
	private AnswerCard computerAnswer3;

	// RANDOM CLASS OBJECT INITIALIZATION TO BE USED FOR COMPUTER VOTES----
	Random random = new Random();

	@FXML
	// FUNCTION TO RETURN TO GAMELOBBY--------------------------------------
	public void backToLobby() {

		try {

			Scene voteScene = exitToLobby.getScene();
			Stage primaryStage = (Stage) voteScene.getWindow();

			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame5_lobby.fxml"));

			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// DATA SETUP FOR FRAME 7-------------------------------------------------
	public void initData(QuestionCard qc, AnswerCard radioAnswer, AnswerCard computerAnswer1,
			AnswerCard computerAnswer2, AnswerCard computerAnswer3) {

		// GET QUESTION FROM FRAME 6-----------------------------------------
		questionText.setText(qc.getQuestion());

		// GET COMPUTER ANSWERS FROM FRAME 6---------------------------------
		ComputerAnswer1.setText(computerAnswer1.getAnswer());
		ComputerAnswer2.setText(computerAnswer2.getAnswer());
		ComputerAnswer3.setText(computerAnswer3.getAnswer());

		// GET CURRENT PLAYER------------------------------------------------
		player = MainService.getCurrentPlayer();

		// SET CURRENT PLAYER'S USERNAME IN THE UI---------------------------
		user.setText(player.getUserName());

		// SET RECEIVED ARGUMENTS AS INSTANCE VARIABLES FOR THIS CLASS-------
		this.radioAnswer = radioAnswer;
		this.computerAnswer1 = computerAnswer1;
		this.computerAnswer2 = computerAnswer2;
		this.computerAnswer3 = computerAnswer3;

		// RADIO BUTTONS FOR PLAYER'S OPTIONS TO VOTE------------------------
		ToggleGroup group = new ToggleGroup();

		radioAnswer1.setToggleGroup(group);
		radioAnswer2.setToggleGroup(group);
		radioAnswer3.setToggleGroup(group);

	}

	// FUNCTIONS RESPONSIBLE FOR VOTE SELECTION FOR COMPUTER PLAYERS---------
	public void chosenByComputer1() {

		int chosenCard = random.nextInt(3) + 1;

		if (chosenCard == 1) {
			vote = new Vote(radioAnswer, computerPlayer1, player);
		} else if (chosenCard == 2) {
			vote = new Vote(computerAnswer2, computerPlayer1, computerPlayer2);
		} else {
			vote = new Vote(computerAnswer3, computerPlayer1, computerPlayer3);
		}

	}

	public void chosenByComputer2() {

		int chosenCard = random.nextInt(3) + 1;

		if (chosenCard == 1) {
			vote = new Vote(radioAnswer, computerPlayer2, player);
		} else if (chosenCard == 2) {
			vote = new Vote(computerAnswer2, computerPlayer2, computerPlayer1);
		} else {
			vote = new Vote(computerAnswer3, computerPlayer2, computerPlayer3);
		}

	}

	public void chosenByComputer3() {

		int chosenCard = random.nextInt(3) + 1;

		if (chosenCard == 1) {
			vote = new Vote(radioAnswer, computerPlayer3, player);
		} else if (chosenCard == 2) {
			vote = new Vote(computerAnswer2, computerPlayer3, computerPlayer1);
		} else {
			vote = new Vote(computerAnswer3, computerPlayer3, computerPlayer2);
		}

	}

	// GET CURRENT GAME'S ID--------------------------------------------------
	public void getGameID() {

		currentGameID = DatabaseUtils.getLastLobbyID();

	}

	@FXML
	// SET CURRENT GAME AND ROUND ID'S IN THE UI------------------------------
	public void initialize() {
		getGameID();
		GameID.setText(String.valueOf(currentGameID));
	}

	@FXML
	public void vote() {

		// CREATE PLAYER OBJECTS FOR COMPUTER AND SAVE THEM TO DB------------
		computerPlayer1 = new Player("Parole123!", "SarcasmIsMyLoveLang", computerDoB);
		try {
			DatabaseUtils.savePlayerToDB(computerPlayer1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		computerPlayer2 = new Player("Parole123!", "CynicalGenius", computerDoB);
		try {
			DatabaseUtils.savePlayerToDB(computerPlayer2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		computerPlayer3 = new Player("Parole123!", "DarkHumorEnthusiast", computerDoB);
		try {
			DatabaseUtils.savePlayerToDB(computerPlayer3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// CREATE VOTE OBJECTS BASED ON CHOSEN VOTES--------------------------
		if (radioAnswer1.isSelected()) {
			vote = new Vote(computerAnswer1, player, computerPlayer1);

		} else if (radioAnswer2.isSelected()) {
			vote = new Vote(computerAnswer2, player, computerPlayer2);
		} else {
			vote = new Vote(computerAnswer3, player, computerPlayer3);
		}
		
		chosenByComputer1();
		chosenByComputer2();
		chosenByComputer3();

	}

}
