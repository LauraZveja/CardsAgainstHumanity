package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AnswerCard;
import model.Category;
import model.Colour;
import model.DatabaseUtils;
import model.Deck;
import model.QuestionCard;
import model.QuestionDeck;
import model.Round;
import service.MainService;

public class SelectAnswerController {

	@FXML
	private Label PlayerUsername;

	@FXML
	private Label answerText_1;

	@FXML
	private Label answerText_2;

	@FXML
	private Label answerText_3;

	@FXML
	private Label answerText_4;

	@FXML
	private Label answerText_5;

	@FXML
	private RadioButton radioAnswer_1;

	@FXML
	private RadioButton radioAnswer_2;

	@FXML
	private RadioButton radioAnswer_3;

	@FXML
	private RadioButton radioAnswer_4;

	@FXML
	private RadioButton radioAnswer_5;

	@FXML
	private Label questionText;

	@FXML
	private ImageView ComputerCard1;

	@FXML
	private ImageView ComputerCard2;

	@FXML
	private ImageView ComputerCard3;

	@FXML
	private ImageView ComputerCard4;

	@FXML
	private ImageView ComputerCard5;

	@FXML
	private ImageView ComputerCard6;

	@FXML
	private ImageView ComputerCard7;

	@FXML
	private ImageView ComputerCard8;

	@FXML
	private ImageView ComputerCard9;

	@FXML
	private ImageView ComputerCard10;

	@FXML
	private ImageView ComputerCard11;

	@FXML
	private ImageView ComputerCard12;

	@FXML
	private ImageView ComputerCard13;

	@FXML
	private ImageView ComputerCard14;

	@FXML
	private ImageView ComputerCard15;

	static Category category = MainService.getCurrentCategory();

	// CREATE DECK-----------------------------
	Deck answerDeck = new Deck(category);

	// CREATE QUESTION DECK----------------------
	QuestionDeck questionDeck = new QuestionDeck(category);

	// STATIC VARIABLE INITIALIZATION TO BE USED BY VOTECONTROLLER
	private static QuestionCard qc;
	public static AnswerCard radioAnswer;
	private static AnswerCard computerAnswer1;
	private static AnswerCard computerAnswer2;
	private static AnswerCard computerAnswer3;
	private static AnswerCard ac1;
	private static AnswerCard ac2;
	private static AnswerCard ac3;
	private static AnswerCard ac4;
	private static AnswerCard ac5;

	@FXML
	private Button buttonReturnToMainMenuFromSelectAnswer;

	// RETURN TO LOBBY-------------------------------
	@FXML
	public void returnToMainMenuFromSelectAnswer() {
		try {
			Scene scene_old = playTheAnswerButton.getScene();
			Stage stage_primary = (Stage) scene_old.getWindow();
			Scene scene_new = FXMLLoader.load(getClass().getResource("/frame5_lobby.fxml"));
			stage_primary.setScene(scene_new);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Label GameID;

	private int currentGameID;

	// GET GAME ID--------------------------
	public void getGameID() {
		currentGameID = DatabaseUtils.getLastLobbyID();
	}

	@FXML
	private Label RoundID;

	private int currentRoundID;

	public static Round round = new Round();

	// GET ROUND ID--------------------------
	public void getRoundId() {
		currentRoundID = round.getRoundID();
	}

	@FXML
	public void initialize() {

		// SHOW CURRENT PLAYER'S USERNAME----------
		PlayerUsername.setText(MainService.getCurrentPlayer().getUserName());

		// SHOW GAME ID----------------------------
		getGameID();
		GameID.setText(String.valueOf(currentGameID));

		// SHOW ROUND ID---------------------------
		getRoundId();
		RoundID.setText(String.valueOf(currentRoundID));

		// COMPUTER CARD BACK----------------------
		ComputerCard1.setImage(new Image("Card back.jpg"));
		ComputerCard2.setImage(new Image("Card back.jpg"));
		ComputerCard3.setImage(new Image("Card back.jpg"));
		ComputerCard4.setImage(new Image("Card back.jpg"));
		ComputerCard5.setImage(new Image("Card back.jpg"));
		ComputerCard6.setImage(new Image("Card back.jpg"));
		ComputerCard7.setImage(new Image("Card back.jpg"));
		ComputerCard8.setImage(new Image("Card back.jpg"));
		ComputerCard9.setImage(new Image("Card back.jpg"));
		ComputerCard10.setImage(new Image("Card back.jpg"));
		ComputerCard11.setImage(new Image("Card back.jpg"));
		ComputerCard12.setImage(new Image("Card back.jpg"));
		ComputerCard13.setImage(new Image("Card back.jpg"));
		ComputerCard14.setImage(new Image("Card back.jpg"));
		ComputerCard15.setImage(new Image("Card back.jpg"));

		// --------------ANSWER CARDS--------------

		// GET PLAYER'S HAND-----------------------
		ac1 = answerDeck.giveMeOneCard();
		ac2 = answerDeck.giveMeOneCard();
		ac3 = answerDeck.giveMeOneCard();
		ac4 = answerDeck.giveMeOneCard();
		ac5 = answerDeck.giveMeOneCard();

		// SHOW PLAYER'S HAND----------------------
		answerText_1.setText(ac1.getAnswer());
		answerText_2.setText(ac2.getAnswer());
		answerText_3.setText(ac3.getAnswer());
		answerText_4.setText(ac4.getAnswer());
		answerText_5.setText(ac5.getAnswer());

		// RADIO BUTTONS FOR PLAYER'S HAND---------
		ToggleGroup group = new ToggleGroup();

		radioAnswer_1.setToggleGroup(group);
		radioAnswer_1.setSelected(true);
		radioAnswer_2.setToggleGroup(group);
		radioAnswer_3.setToggleGroup(group);
		radioAnswer_4.setToggleGroup(group);
		radioAnswer_5.setToggleGroup(group);

		// --------------QUESTION CARD--------------

		// GET QUESTION CARD-------------------------
		qc = questionDeck.giveMeOneQuestion();

		// SHOW QUESTION CARD------------------------
		questionText.setText(qc.getQuestion());
	}

	// PLAYER SELECT ANSWER CARD---------------------
	@FXML
	private Button playTheAnswerButton;

	@FXML
	public void clickPlayTheAnswerButton() {

		computerAnswer1 = answerDeck.giveMeOneCard();
		computerAnswer2 = answerDeck.giveMeOneCard();
		computerAnswer3 = answerDeck.giveMeOneCard();

		if (radioAnswer_1.isSelected()) {
			answerText_1.setText(radioAnswer_1.getText());
			radioAnswer = ac1;

		} else if (radioAnswer_2.isSelected()) {
			answerText_2.setText(radioAnswer_2.getText());
			radioAnswer = ac2;

		} else if (radioAnswer_3.isSelected()) {
			answerText_3.setText(radioAnswer_3.getText());
			radioAnswer = ac3;

		} else if (radioAnswer_4.isSelected()) {
			answerText_4.setText(radioAnswer_4.getText());
			radioAnswer = ac4;

		} else {
			answerText_5.setText(radioAnswer_5.getText());
			radioAnswer = ac5;
		}

		// GO TO VOTE FRAME------------------------------------
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/frame7_vote.fxml"));
			Parent votePane = loader.load();
			Scene voteScene = new Scene(votePane);
			VoteController controller = loader.getController();
			controller.initData(qc, radioAnswer, computerAnswer1, computerAnswer2, computerAnswer3);

			Stage stage_primary = (Stage) playTheAnswerButton.getScene().getWindow();
			stage_primary.setScene(voteScene);
			stage_primary.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
