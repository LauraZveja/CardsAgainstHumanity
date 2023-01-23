package model;

public class Vote {

	// variables
	private int voteID;
	private Round round;
	private QuestionCard questionCard;
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

	public QuestionCard getQuestionCard() {
		return questionCard;
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

	public void setQuestionCard(QuestionCard questionCard) {
		if (questionCard != null) {
			this.questionCard = questionCard;
		} else {
			this.questionCard = new QuestionCard();
		}
	}

	public void setAnswerCard(AnswerCard answerCard) {
		if (answerCard != null) {
			this.answerCard = answerCard;
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
	public Vote(Round round, QuestionCard questionCard, AnswerCard answerCard, Player playerWhoVoted,
			Player playerVotedAbout) {
		setVoteID();
		setRound(round);
		setQuestionCard(questionCard);
		setAnswerCard(answerCard);
		setPlayerWhoVoted(playerWhoVoted);
		setPlayerVotedAbout(playerVotedAbout);
	}

	@Override
	public String toString() {
		return "Vote [voteID=" + voteID + ", round=" + round.getRoundID() + ", questionCard="
				+ questionCard.getQuestion() + ", answerCard=" + answerCard.getAnswer() + ", playerWhoVoted="
				+ playerWhoVoted.getUserName() + ", playerVotedAbout=" + playerVotedAbout.getUserName() + "]";
	}

}
