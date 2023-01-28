package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Score {

	private SimpleIntegerProperty place;
	private SimpleStringProperty username;
	private SimpleIntegerProperty points;

	public int getPlace() {
		return place.get();
	}

	public void setPlace(SimpleIntegerProperty place) {
		this.place = place;
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(SimpleStringProperty username) {
		this.username = username;
	}

	public int getPoints() {
		return points.get();
	}

	public void setPoints(SimpleIntegerProperty points) {
		this.points = points;
	}

	public Score(int place, String username, int points) {
		this.place = new SimpleIntegerProperty(place);
		this.username = new SimpleStringProperty(username);
		this.points = new SimpleIntegerProperty(points);
	}

	@Override
	public String toString() {
		return "Score [place=" + place + ", username=" + username + ", points=" + points + "]";
	}

}
