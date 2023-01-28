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
import model.Deck;
import model.QuestionCard;
import model.QuestionDeck;

public class SelectAnswerController {

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

	Category category = Category.ADULT;

	// CREATE DECK-----------------------------
	Deck answerDeck = new Deck(category);

	// CREATE QUESTION DECK----------------------
	QuestionDeck questionDeck = new QuestionDeck(category);

	public void initialize() {

		// COMPUTER CARD BACK
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
		AnswerCard ac1 = answerDeck.giveMeOneCard();
		AnswerCard ac2 = answerDeck.giveMeOneCard();
		AnswerCard ac3 = answerDeck.giveMeOneCard();
		AnswerCard ac4 = answerDeck.giveMeOneCard();
		AnswerCard ac5 = answerDeck.giveMeOneCard();

		// SHOW PLAYER'S HAND----------------------
		answerText_1.setText(ac1.getAnswer());
		answerText_2.setText(ac2.getAnswer());
		answerText_3.setText(ac3.getAnswer());
		answerText_4.setText(ac4.getAnswer());
		answerText_5.setText(ac5.getAnswer());

		// RADIO BUTTONS FOR PLAYER'S HAND---------
		ToggleGroup group = new ToggleGroup();

		radioAnswer_1.setToggleGroup(group);
		radioAnswer_2.setToggleGroup(group);
		radioAnswer_3.setToggleGroup(group);
		radioAnswer_4.setToggleGroup(group);
		radioAnswer_5.setToggleGroup(group);

		// --------------QUESTION CARD--------------

		// GET QUESTION CARD-------------------------
		QuestionCard qc = questionDeck.giveMeOneQuestion();

		// SHOW QUESTION CARD------------------------
		questionText.setText(qc.getQuestion());
	}

	@FXML
	private Button playTheAnswerButton;

	@FXML
	public void clickPlayTheAnswerButton() {
		AnswerCard radioAnswer;
		QuestionCard qc = questionDeck.giveMeOneQuestion();
		questionText.setText(qc.getQuestion());

		if (radioAnswer_1.isSelected()) {
			AnswerCard ac1 = answerDeck.giveMeOneCard();
			answerText_1.setText(ac1.getAnswer());
			radioAnswer = ac1;


		} else {
			if (radioAnswer_2.isSelected()) {
				AnswerCard ac2 = answerDeck.giveMeOneCard();
				answerText_2.setText(ac2.getAnswer());
				radioAnswer = ac2;


			} else {
				if (radioAnswer_3.isSelected()) {
					AnswerCard ac3 = answerDeck.giveMeOneCard();
					answerText_3.setText(ac3.getAnswer());
					radioAnswer = ac3;


				} else {
					if (radioAnswer_4.isSelected()) {
						AnswerCard ac4 = answerDeck.giveMeOneCard();
						answerText_4.setText(ac4.getAnswer());
						radioAnswer = ac4;


					} else {
						AnswerCard ac5 = answerDeck.giveMeOneCard();
						answerText_5.setText(ac5.getAnswer());
						radioAnswer = ac5;

					}
				}
			}
		}
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frame7_vote.fxml"));
	        Parent votePane = loader.load();
	        VoteController controller = loader.getController();
	        controller.initData(qc, radioAnswer);

	        Scene scene_old = playTheAnswerButton.getScene();
	        Stage stage_primary = (Stage) scene_old.getWindow();
	        Scene voteScene = new Scene(votePane);

	        stage_primary.setScene(voteScene);
	        stage_primary.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    }
	}
