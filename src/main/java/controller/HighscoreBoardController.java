package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DatabaseUtils;
import model.Score;

public class HighscoreBoardController implements Initializable {
	@FXML
	private TableView<Score> tableView;

	@FXML
	private TableColumn<Score, Integer> placeColumn;

	@FXML
	private TableColumn<Score, String> usernameColumn;

	@FXML
	private TableColumn<Score, Integer> pointsColumn;

	public void initialize(URL location, ResourceBundle resources) {
		// set up columns in the table
		placeColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("place"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<Score, String>("username"));
		pointsColumn.setCellValueFactory(new PropertyValueFactory<Score, Integer>("points"));

		// load data
		tableView.setItems(getScores());
	}

	public ObservableList<Score> getScores() {
		ObservableList<Score> topPlayers = FXCollections.observableArrayList();

		for (Score temp : DatabaseUtils.getTopScore()) {
			topPlayers.add(temp);
		}
		return topPlayers;
	}

}