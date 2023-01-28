package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.AnswerCard;
import model.QuestionCard;

public class VoteController {
	

	@FXML
	private Button exitToLobby;
	
	
	
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



	public void initData(QuestionCard qc, AnswerCard radioAnswer) {
		// TODO Auto-generated method stub
		
	}

}
