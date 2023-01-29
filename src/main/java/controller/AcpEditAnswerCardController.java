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

public class AcpEditAnswerCardController {
	@FXML
	private Button returnToACP;
	@FXML
	private Button refresh;
	@FXML
	private ListView<String> lvAnswerCards;
	@FXML
	private ComboBox<Category> categoryCB;
	@FXML
	private Button create;
	@FXML
	private TextField newanswercard;
	@FXML
	private void initialize() {
		lvAnswerCards.setCellFactory(lv -> new CustomListCell());
		categoryCB.getItems().addAll(Category.values());
		categoryCB.getSelectionModel().selectFirst();
		categoryCB.valueProperty().addListener((observable, oldValue, newValue) -> {
			readAnswerDeckFileByCategory(Category.valueOf(newValue.toString()));
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
	                updateAnswer(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()), itemText, textField.getText());
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
	            deleteAnswerFromAnswerDeckByCategory(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()), item);
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
		readAnswerDeckFileByCategory(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()));
	}
	
	public void clickCreate() {
		String answerCard = newanswercard.getText();
		if(!answerCard.isEmpty()) {
			insertAnswer(Category.valueOf(categoryCB.getSelectionModel().getSelectedItem().toString()), answerCard);
			clickRefresh();
			newanswercard.setText("");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Answer added");
			alert.setContentText("The new answer \""+answerCard+"\" was successfully added to the list.");
			alert.showAndWait();
		}
	}
	
	public void readAnswerDeckFileByCategory(Category category) {
		try {
			lvAnswerCards.getItems().clear();
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + category + ".txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				lvAnswerCards.getItems().add(line.trim());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertAnswer(Category category, String newAnswer) {
		try (BufferedReader br = new BufferedReader(
				new FileReader("src/main/resources/" + category + ".txt"))) {
			String line;
			boolean alreadyExist = false;
			while ((line = br.readLine()) != null) {
				if (line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(newAnswer.replaceAll("[\\.,'!]", ""))) {
					alreadyExist = true;
					break;
				}
			}
			if (!alreadyExist) {
				try (BufferedWriter bw = new BufferedWriter(
						new FileWriter("src/main/resources/" + category + ".txt", true))) {
					bw.write(newAnswer);
					bw.newLine();
				}
			} else {
				System.out.println("Answer already exists in the file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAnswer(Category category, String oldAnswer, String newAnswer) {
		if (oldAnswer != null && newAnswer != null) {
			try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + category + ".txt"))) {
				String line;
				StringBuilder sb = new StringBuilder();
				boolean updated = false;
				while ((line = br.readLine()) != null) {
					if (line.replaceAll("[\\.,']", "").equalsIgnoreCase(oldAnswer.replaceAll("[\\.,']", ""))) {
						sb.append(newAnswer).append("\n");
						updated = true;
					} else {
						sb.append(line).append("\n");
					}
				}
				if (updated) {
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + category + ".txt"))) {
						bw.write(sb.toString());
					}
				} else {
					System.out.println("neatrada atbildi: "+oldAnswer);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteAnswerFromAnswerDeckByCategory(Category category, String unwanted_Answer) {
		System.out.println(unwanted_Answer);
		File file = new File("src/main/resources/" + category + ".txt");
		StringBuilder temp_answers = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.replaceAll("[\\.,'!]", "").equalsIgnoreCase(unwanted_Answer.replaceAll("[\\.,'!]", ""))) {
					temp_answers.append(line + "\n");
				}
			}
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(temp_answers.toString());
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
