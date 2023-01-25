package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class GameLobby {

	// VARIABLES --------------------------------------------

	private int gameLobby_ID;
	private byte roundCount;
	private byte playerCount;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<QuestionCard> questions = new ArrayList<>();
	private ArrayList<AnswerCard> answerDeck = new ArrayList<>();
	private LocalDate gameDate;

	// GET FUNCTIONS-----------------------------------------

	public int getGameLobby_ID() {
		return gameLobby_ID;
	}

	public byte getRoundCount() {
		return roundCount;
	}

	public byte getPlayerCount() {
		return playerCount;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<QuestionCard> getQuestions() {
		return questions;
	}

	public ArrayList<AnswerCard> getAnswerDeck() {
		return answerDeck;
	}

	public LocalDate getGameDate() {
		return gameDate;
	}

	// SET FUNCTIONS-----------------------------------------

	public void setGameLobby_ID() {
		this.gameLobby_ID = DatabaseUtils.getLastLobbyID()+1;
	}

	public void setRoundCount(byte inputRoundCount) {
		if (inputRoundCount > 0 && 127 > inputRoundCount) {
			this.roundCount = inputRoundCount;
		} else {
			this.roundCount = 5;
		}
	}

	public void setPlayerCount(byte inputPlayerCount) {
		if (inputPlayerCount > 0 && 11 > inputPlayerCount) {
			this.playerCount = inputPlayerCount;
		} else {
			this.playerCount = 3;
		}
	}

	public void setPlayers(ArrayList<Player> inputPlayers) {
		if (inputPlayers != null) {
			this.players = inputPlayers;
		} else {
			this.players = new ArrayList<>();
		}
	}

	public void setQuestions(ArrayList<QuestionCard> inputQuestions) {
		if (inputQuestions != null) {
			this.questions = inputQuestions;
		} else {
			this.questions = new ArrayList<>();
		}
	}

	public void setAnswerDeck(ArrayList<AnswerCard> inputAnswerDeck) {
		if (inputAnswerDeck != null) {
			this.answerDeck = inputAnswerDeck;
		} else {
			this.answerDeck = new ArrayList<>();
		}
	}

	public void setGameDate() {
		this.gameDate = LocalDate.now();
	}

	// CONSTRUCTORS------------------------------------------

	public GameLobby() {
		setGameLobby_ID();
		setRoundCount((byte) 5);
		setPlayerCount((byte) 3);
		setPlayers(new ArrayList<Player>());
		setQuestions(new ArrayList<QuestionCard>());
		setAnswerDeck(new ArrayList<AnswerCard>());
		setGameDate();
	}

	public GameLobby(byte inputRoundCount, byte inputPlayerCount, ArrayList<Player> inputPlayers,
			ArrayList<QuestionCard> inputQuestions, ArrayList<AnswerCard> inputAnswerDeck) {
		setGameLobby_ID();
		setRoundCount(inputRoundCount);
		setPlayerCount(inputPlayerCount);
		setPlayers(inputPlayers);
		setQuestions(inputQuestions);
		setAnswerDeck(inputAnswerDeck);
		setGameDate();
	}

	

	// toString----------------------------------------------

	@Override
	public String toString() {
		return "GameLobby [gameLobby_ID=" + gameLobby_ID + ", roundCount=" + roundCount + ", playerCount=" + playerCount
				+ ", players=" + players + ", questions=" + questions + ", answerDeck=" + answerDeck + ", gameDate="
				+ gameDate + "]";
	}
	}


