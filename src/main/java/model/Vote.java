package model;

public class Vote {

	// variables
	private int voteID;
	private AnswerCard answerCard;
	private Player playerWhoVoted;
	private Player playerVotedAbout;
	private static int counter = 1;

	// getters
	public int getVoteID() {
		return voteID;
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
		this.voteID = counter;
		counter++;
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
	public Vote(AnswerCard answerCard, Player playerWhoVoted, Player playerVotedAbout) {
		setVoteID();
		setAnswerCard(answerCard);
		setPlayerWhoVoted(playerWhoVoted);
		setPlayerVotedAbout(playerVotedAbout);

	}

	@Override
	public String toString() {
		return "Vote [voteID=" + voteID + ", answerCard=" + answerCard.getAnswer() + ", playerWhoVoted="
				+ playerWhoVoted.getUserName() + ", playerVotedAbout=" + playerVotedAbout.getUserName() + "]";
	}

}
