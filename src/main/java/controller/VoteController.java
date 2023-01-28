package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.AnswerCard;
import model.QuestionCard;

public class VoteController {
	

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



	public void initData(QuestionCard qc, AnswerCard radioAnswer, AnswerCard computerAnswer1, AnswerCard computerAnswer2, AnswerCard computerAnswer3) {
		
		questionText.setText(qc.getQuestion());
		ComputerAnswer1.setText(computerAnswer1.getAnswer());
		ComputerAnswer2.setText(computerAnswer2.getAnswer());
		ComputerAnswer3.setText(computerAnswer3.getAnswer());
		
		
	}
	
	public void chosenByComputer1() {
		
	}





}
