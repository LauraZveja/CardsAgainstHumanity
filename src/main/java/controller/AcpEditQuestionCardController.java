package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Category;

public class AcpEditQuestionCardController {
	@FXML
	private Button returnToACP;
	@FXML
	private Button refresh;
	@FXML
	private ListView<String> lvQuestionCards;
	@FXML
	private ComboBox<Category> categoryCB;
	@FXML
	private Button create;
	@FXML
	private TextField newquestioncard;
	@FXML
	private void initialize() {
		lvQuestionCards.setCellFactory(lv -> new CustomListCell());
		categoryCB.getItems().addAll(Category.values());
		categoryCB.getSelectionModel().selectFirst();
		categoryCB.valueProperty().addListener((observable, oldValue, newValue) -> {
			readQuestionDeckFileByCategory(Category.valueOf(newValue.toString()));
		});
		clickRefresh();
	}
	
	private class CustomListCell extends ListCell<String> {
	    private final Label label = new Label();
	    private final HBox hBox = new HBox();
	    private final Button updateButton = new Button("Update");
	    private final Button deleteButton = new Button("Delete");

	    public CustomListCell() {
	        hBox.getChildren().addAll(label, updateButton, deleteButton);
	        hBox.setSpacing(10);
	        updateButton.setOnAction(event -> {
	            int index = getIndex();
	            String itemText = getListView().getItems().get(index);

	            // jauns stage popup windowam
	            Stage stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);

	        	// izveidojam jaunu update pogu un textfield
	            TextField textField = new TextField(itemText);
	            Button updateButton2 = new Button("Update");
	            updateButton2.setOnAction(event2 -> {
	                updateQuestion(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()), itemText, textField.getText());
	                clickRefresh();
	                stage.close();
	            });

	            // izveidojam layoutu, pievienojam textfield un update pogu
	            VBox layout = new VBox(10);
	            layout.getChildren().addAll(textField, updateButton2);
	            layout.setAlignment(Pos.CENTER);

	            // setojam scene un paradam stage
	            Scene scene = new Scene(layout, 300, 150);
	            stage.setScene(scene);
	            stage.show();
	        });
	        deleteButton.setOnAction(event -> {
	            String item = getItem();
	            deleteQuestionFromQuestionDeckByCategory(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()), item);
	            clickRefresh();
	        });
	    }

	    @Override
	    protected void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        if (empty) {
	            setText(null);
	            setGraphic(null);
	        } else {
	            label.setText(item);
	            setGraphic(hBox);
	        }
	    }
	}

	@FXML
	public void clickRefresh() {
		readQuestionDeckFileByCategory(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()));
	}
	
	public void clickCreate() {
		String questionCard = newquestioncard.getText();
		if(!questionCard.isEmpty()) {
			insertQuestion(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()), questionCard);
			clickRefresh();
			newquestioncard.setText("");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Question added");
			alert.setContentText("The new question \""+questionCard+"\" was successfully added to the list.");
			alert.showAndWait();
		}
	}
	
	public void readQuestionDeckFileByCategory(Category category) {
		try {
			lvQuestionCards.getItems().clear();
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/QUESTIONS_" + category + ".txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				lvQuestionCards.getItems().add(line.trim());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertQuestion(Category category, String newQuestion) {
		try (BufferedReader br = new BufferedReader(
				new FileReader("src/main/resources/QUESTIONS_" + category + ".txt"))) {
			String line;
			boolean alreadyExist = false;
			while ((line = br.readLine()) != null) {
				if (line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(newQuestion.replaceAll("[\\.,'!]", ""))) {
					alreadyExist = true;
					break;
				}
			}
			if (!alreadyExist) {
				try (BufferedWriter bw = new BufferedWriter(
						new FileWriter("src/main/resources/QUESTIONS_" + category + ".txt", true))) {
					bw.write(newQuestion);
					bw.newLine();
				}
			} else {
				System.out.println("Question already exists in the file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuestion(Category category, String oldQuestion, String newQuestion) {
		if (oldQuestion != null && newQuestion != null) {
			try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/QUESTIONS_" + category + ".txt"))) {
				String line;
				StringBuilder sb = new StringBuilder();
				boolean updated = false;
				while ((line = br.readLine()) != null) {
					if (line.replaceAll("[\\.,']", "").equalsIgnoreCase(oldQuestion.replaceAll("[\\.,']", ""))) {
						sb.append(newQuestion).append("\n");
						updated = true;
					} else {
						sb.append(line).append("\n");
					}
				}
				if (updated) {
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/QUESTIONS_" + category + ".txt"))) {
						bw.write(sb.toString());
					}
				} else {
					System.out.println("neatrada jautajumu: "+oldQuestion);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteQuestionFromQuestionDeckByCategory(Category category, String unwanted_Question) {
		System.out.println(unwanted_Question);
		File file = new File("src/main/resources/QUESTIONS_" + category + ".txt");
		StringBuilder temp_questions = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(unwanted_Question.replaceAll("[\\.,'!]", ""))) {
					temp_questions.append(line + "\n");
				}
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(temp_questions.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void clickReturnToACP() {
		try {
			Scene scene = returnToACP.getScene();
			Stage primaryStage = (Stage) scene.getWindow();
			Scene mainScene = FXMLLoader.load(getClass().getResource("/frame10_acp_main.fxml"));
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
