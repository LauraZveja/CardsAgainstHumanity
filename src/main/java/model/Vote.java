package model;

public class Vote {

	// variables
	private int voteID;
	private Round round;
	private AnswerCard answerCard;
	private Player playerWhoVoted;
	private Player playerVotedAbout;
	private static int counter;

	// getters
	public int getVoteID() {
		return voteID;
	}

	public Round getRound() {
		return round;
	}

	public AnswerCard getAnswerCard() {
		return answerCard;
	}

	public Player playerWhoVoted() {
		return playerWhoVoted;
	}

	public Player playerVotedAbout() {
		return playerVotedAbout;
	}

	// setters

	public void setVoteID() {
		this.voteID = voteID;
		counter++;
	}

	public void setRound(Round round) {
		if (round != null) {
			this.round = round;
		} else {
			this.round = new Round();
		}
	}

	public void setAnswerCard(AnswerCard answerCard) {
		if (answerCard != null) {
			this.answerCard = answerCard;
		} else {
			this.answerCard = new AnswerCard("Empty answer", Category.UNDER_18, Colour.WHITE);
		}
	}

	public void setPlayerWhoVoted(Player playerWhoVoted) {
		if (playerWhoVoted != null) {
			this.playerWhoVoted = playerWhoVoted;
		} else {
			this.playerWhoVoted = new Player();
		}
	}

	public void setPlayerVotedAbout(Player playerVotedAbout) {
		if (playerVotedAbout != null) {
			this.playerVotedAbout = playerVotedAbout;
		} else {
			this.playerWhoVoted = new Player();
		}
	}

	// constructors
	public Vote(Round round, AnswerCard answerCard, Player playerWhoVoted, Player playerVotedAbout) {
		setVoteID();
		setRound(round);
		setAnswerCard(answerCard);
		setPlayerWhoVoted(playerWhoVoted);
		setPlayerVotedAbout(playerVotedAbout);
	}

	@Override
	public String toString() {
		return "Vote [voteID=" + voteID + ", round=" + round.getRoundID() + ", answerCard=" + answerCard.getAnswer()
				+ ", playerWhoVoted=" + playerWhoVoted.getUserName() + ", playerVotedAbout="
				+ playerVotedAbout.getUserName() + "]";
	}

}
