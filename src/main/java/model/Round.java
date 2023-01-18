package model;

import java.util.ArrayList;

public class Round {

	// variables
	private int roundID;
	private static byte idCounter = 0;
	private QuestionCard questionCard;
	private ArrayList<Vote> votes;
	private ArrayList<QuestionCard> questionPerRound;

	// getters

	public int getRoundID() {
		return roundID;
	}

	public QuestionCard getQuestionCard() {
		return questionCard;
	}

	public ArrayList<Vote> getVotes() {
		return votes;
	}

	public ArrayList<QuestionCard> getQuestionPerRound() {
		return questionPerRound;
	}

	// setters

	public void setRoundID() {
		this.roundID = idCounter;
		idCounter++;
	}

	public void setQuestionCard(QuestionCard questionCard) {
		if (questionCard != null) {
			this.questionCard = questionCard;
		} else {
			this.questionCard = new QuestionCard();
		}
	}

	public void setVotes(ArrayList<Vote> votes) {
		if (votes != null) {
			this.votes = votes;
		} else {
			this.votes = new ArrayList<Vote>();
		}
	}

	public void setQuestionPerRound(ArrayList<QuestionCard> questionPerRound) {
		if (questionPerRound != null) {
			this.questionPerRound = questionPerRound;
		} else {
			this.questionPerRound = new ArrayList<QuestionCard>();
		}
	}

	// constructors

	public Round() {
		setRoundID();
		setQuestionCard(questionCard);
		setVotes(votes);
		setQuestionPerRound(questionPerRound);
	}

	public Round(QuestionCard questionCard, ArrayList<Vote> votes, ArrayList<QuestionCard> questionPerRound) {
		setRoundID();
		setQuestionCard(new QuestionCard());
		setVotes(new ArrayList<Vote>());
		setQuestionPerRound(new ArrayList<QuestionCard>());
	}

	// toString

	// TODO: Precizēt, kad Question klase ir gatava
	@Override
	public String toString() {
		return "Round [roundID=" + roundID + ", questionCard=" + questionCard + ", votes=" + votes
				+ ", questionPerRound=" + questionPerRound + "]";
	}

}
