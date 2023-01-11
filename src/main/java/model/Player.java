package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Player extends User{
	private static int playerCounter = 1;
	private int playerID;
	private ArrayList<AnswerCard> answers = new ArrayList<>();
	private int scorePerRound;
	private int score;
	
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID() {
		this.playerID = playerCounter;
		playerCounter++;
	}
	public ArrayList<AnswerCard> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<AnswerCard> answers) {
		if(answers != null) {
			this.answers = answers;
		} else {
			this.answers = new ArrayList<>();
		}
	}
	public int getScorePerRound() {
		return scorePerRound;
	}
	public void setScorePerRound(int scorePerRound) {
		if(scorePerRound >= 0 && scorePerRound <= 7 ) {
			this.scorePerRound = scorePerRound;
		} else {
			this.scorePerRound = 0;
		}
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		if(score >= 0 && score <= 889) { // ja 127 raundi(max) un katru reizi pa 7 punktiem(max), tad max 889
			this.score = score;
		} else {
			this.score = 0;
		}
	}
	
	public Player(String password, String userName, Calendar dateOfBirth) {
		super(password, userName, dateOfBirth);
		setPlayerID();
		setAnswers(new ArrayList<AnswerCard>());
		setScorePerRound(0);
		setScore(0);
	}
	
	public Player(String password, String userName, Calendar dateOfBirth, ArrayList<AnswerCard> answers,
			int scorePerRound, int score) {
		super(password, userName, dateOfBirth);
		setPlayerID();
		setAnswers(answers);
		setScorePerRound(scorePerRound);
		setScore(score);
	}
	
	public Player(String password, String userName, Calendar dateOfBirth, String email, Gender gender, Country country,
			int playerID, ArrayList<AnswerCard> answers, int scorePerRound, int score) {
		super(password, userName, dateOfBirth, email, gender, country);
		setPlayerID();
		setAnswers(answers);
		setScorePerRound(scorePerRound);
		setScore(score);
	}
	
	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", answers=" + answers + ", scorePerRound=" + scorePerRound + ", score="
				+ score + "]";
	}
	
	
	

}
