package model;

public class Vote {

	// variables
	private int voteID;
	private Round round;
	private QuestionCard questionCard;
	private AnswerCard answerCard;
	private Player player;
	private byte voteCount;
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

	public Player getPlayer() {
		return player;
	}

	public byte getVoteCount() {
		return voteCount;
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

	public void setPlayer(Player player) {
		if (player != null) {
			this.player = player;
		} else {
			this.player = new Player();
		}
	}

	public void setVoteCount(byte voteCount) {
		if (voteCount > 0 && voteCount < 11) {
			this.voteCount = voteCount;
		} else {
			this.voteCount = 0;
		}

	}

	// constructors
	public Vote(Round round, QuestionCard questionCard, AnswerCard answerCard, Player player, byte voteCount) {
		setVoteID();
		setRound(round);
		setQuestionCard(questionCard);
		setAnswerCard(answerCard);
		setPlayer(player);
		setVoteCount(voteCount);
	}

	public Vote() {
		setVoteID();
		setRound(new Round());
		setQuestionCard(new QuestionCard());
		//setAnswerCard(new AnswerCard());
		setPlayer(new Player());
		setVoteCount((byte) 0);
	}

	// TODO: Kad pabeigtas Q&A kārtis, precizēt
	@Override
	public String toString() {
		return "Vote [voteID=" + voteID + ", round=" + round.getRoundID() + ", questionCard=" + questionCard
				+ ", answerCard=" + answerCard + ", player=" + player.getUserName() + ", voteCount=" + voteCount + "]";
	}

}
