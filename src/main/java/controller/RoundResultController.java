package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AnswerCard;
import model.DatabaseUtils;
import model.Player;
import model.QuestionCard;
import model.Round;
import model.Vote;
import service.MainService;

public class RoundResultController {

	@FXML
	private Label ResultsUsername1;

	@FXML
	private Label ResultsUsername2;

	@FXML
	private Label ResultsUsername3;

	@FXML
	private Label ResultsUsername1_1;

	@FXML
	private Label ResultsUsername1_2;

	@FXML
	private Label ResultsUsername1_3;

	@FXML
	private ImageView ResultsComputerCard1;

	@FXML
	private ImageView ResultsComputerCard2;

	@FXML
	private ImageView ResultsComputerCard3;

	@FXML
	private ImageView ResultsComputerCard4;

	@FXML
	private ImageView ResultsComputerCard5;

	@FXML
	private ImageView ResultsComputerCard6;

	@FXML
	private ImageView ResultsComputerCard7;

	@FXML
	private ImageView ResultsComputerCard8;

	@FXML
	private ImageView ResultsComputerCard9;

	@FXML
	private ImageView ResultsComputerCard10;

	@FXML
	private ImageView ResultsComputerCard11;

	@FXML
	private ImageView ResultsComputerCard12;

	@FXML
	private ImageView ResultsComputerCard13;

	@FXML
	private ImageView ResultsComputerCard14;

	@FXML
	private ImageView ResultsComputerCard15;

	@FXML
	private Label ResultsPlusVotes1;

	@FXML
	private Label ResultsPlusVotes2;

	@FXML
	private Label ResultsPlusVotes3;

	@FXML
	private Label ResultsPlusVotesPlayer;

	@FXML
	private Label ResultsUsernamePlayer;

	@FXML
	private Label ResultsGameID;

	private int currentResultsGameID;

	// GET GAME ID--------------------------
	public void getResultsGameID() {
		currentResultsGameID = DatabaseUtils.getLastLobbyID();
	}

	@FXML
	private Label ResultsRoundID;

	private int currentResultsRoundID;

	public static Round round = new Round();

	// GET ROUND ID--------------------------
	public void getReslutsRoundId() {
		currentResultsRoundID = SelectAnswerController.round.getRoundID();
	}

	@FXML
	private Button ReturnToLobbyFromResults;

	// RETURN TO LOBBY-------------------------------
	@FXML
	public void clickReturnToLobbyFromResults() {
		try {
			Scene scene_old = ReturnToLobbyFromResults.getScene();
			Stage stage_primary = (Stage) scene_old.getWindow();
			Scene scene_new = FXMLLoader.load(getClass().getResource("/frame5_lobby.fxml"));
			stage_primary.setScene(scene_new);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {

		ResultsAnswerCardPlayer.setText(SelectAnswerController.radioAnswer.getAnswer());

		// SHOW VOTES
		byte VoteCount1 = 0;
		byte VoteCount2 = 0;
		byte VoteCount3 = 0;
		byte VoteCountPlayer = 0;

		for (Vote temp : SelectAnswerController.round.getVotes()) {
			if (temp.playerVotedAbout() == VoteController.computerPlayer1) {
				VoteCount1++;
			} else if (temp.playerVotedAbout() == VoteController.computerPlayer2) {
				VoteCount2++;
			} else if (temp.playerVotedAbout() == VoteController.computerPlayer3) {
				VoteCount3++;
			} else {
				VoteCountPlayer++;
			}
		}

		ResultsPlusVotes1.setText(String.valueOf(VoteCount1) + " Votes");
		ResultsPlusVotes2.setText(String.valueOf(VoteCount2) + " Votes");
		ResultsPlusVotes3.setText(String.valueOf(VoteCount3) + " Votes");
		ResultsPlusVotesPlayer.setText(String.valueOf(VoteCountPlayer) + " Votes");

		// SHOW CURRENT PLAYER'S USERNAME----------
		ResultsUsernamePlayer.setText(MainService.getCurrentPlayer().getUserName());

		// SHOW GAME ID----------------------------
		getResultsGameID();
		ResultsGameID.setText(String.valueOf(currentResultsGameID));

		// SHOW ROUND ID---------------------------
		getReslutsRoundId();
		ResultsRoundID.setText(String.valueOf(currentResultsRoundID));

		// COMPUTER CARD BACK----------------------
		ResultsComputerCard1.setImage(new Image("Card back.jpg"));
		ResultsComputerCard2.setImage(new Image("Card back.jpg"));
		ResultsComputerCard3.setImage(new Image("Card back.jpg"));
		ResultsComputerCard4.setImage(new Image("Card back.jpg"));
		ResultsComputerCard5.setImage(new Image("Card back.jpg"));
		ResultsComputerCard6.setImage(new Image("Card back.jpg"));
		ResultsComputerCard7.setImage(new Image("Card back.jpg"));
		ResultsComputerCard8.setImage(new Image("Card back.jpg"));
		ResultsComputerCard9.setImage(new Image("Card back.jpg"));
		ResultsComputerCard10.setImage(new Image("Card back.jpg"));
		ResultsComputerCard11.setImage(new Image("Card back.jpg"));
		ResultsComputerCard12.setImage(new Image("Card back.jpg"));
		ResultsComputerCard13.setImage(new Image("Card back.jpg"));
		ResultsComputerCard14.setImage(new Image("Card back.jpg"));
		ResultsComputerCard15.setImage(new Image("Card back.jpg"));
	}

	@FXML
	private Button ContinueToNextRoundFromResults;

	@FXML
	public void clickContinueToNextRoundFromResults() {

		try {
			Scene scene_old = ReturnToLobbyFromResults.getScene();
			Stage stage_primary = (Stage) scene_old.getWindow();
			Scene scene_new = FXMLLoader.load(getClass().getResource("/frame6_Select_answer.fxml"));
			stage_primary.setScene(scene_new);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Label ResultsQuestionCard;

	@FXML
	private Label ResultsAnswerCard1;

	@FXML
	private Label ResultsAnswerCard2;

	@FXML
	private Label ResultsAnswerCard3;

	@FXML
	private Label ResultsAnswerCardPlayer;

	public void initData(Label questionText, AnswerCard radioAnswer, AnswerCard computerAnswer1,
			AnswerCard computerAnswer2, AnswerCard computerAnswer3) {

		// GET COMPUTER ANSWERS FROM FRAME 7---------------------------------
		ResultsAnswerCard1.setText(computerAnswer1.getAnswer());
		ResultsAnswerCard2.setText(computerAnswer2.getAnswer());
		ResultsAnswerCard3.setText(computerAnswer3.getAnswer());
		ResultsAnswerCardPlayer.setText(radioAnswer.getAnswer());

		// GET QUESTION CARD TEXT FROM FRAME 7-------------------------------
		ResultsQuestionCard.setText(questionText.getText());
	}

}
