package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Player;
import service.MainService;

public class AcpMainController {
	@FXML
	private Button editquestioncards;
	@FXML
	private Button editanswercards;
	@FXML
	private Button logout;
	@FXML
	private Label user;
	
	@FXML
	private void initialize() {
		Player player = MainService.getCurrentPlayer();
		user.setText(player.getUserName());
	}
	
	@FXML
	public void clickEditQuestionCards() {
		try {
			Scene scene = editquestioncards.getScene();
			Stage primaryStage = (Stage) scene.getWindow();
			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame11_acp_edit_question_cards.fxml"));
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void clickEditAnswerCards() {
		try {
			Scene scene = editquestioncards.getScene();
			Stage primaryStage = (Stage) scene.getWindow();
			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame12_acp_edit_answer_cards.fxml"));
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void clickEditPlayers() {
		
	}
	@FXML
	public void clickLogout() {
		try {
			Scene scene = logout.getScene();
			Stage primaryStage = (Stage) scene.getWindow();
			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame3_login.fxml"));
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
