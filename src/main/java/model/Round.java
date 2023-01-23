package model;

import java.util.ArrayList;

public class Round {

	// variables
	private int roundID;
	private static byte idCounter = 0;
	private QuestionCard questionCard;
	private ArrayList<AnswerCard> playedAnswerCards;
	private ArrayList<Vote> votes;
	private ArrayList<QuestionCard> questionPerRound;

	// getters

	public int getRoundID() {
		return roundID;
	}

	public QuestionCard getQuestionCard() {
		return questionCard;
	}

	public QuestionCard playedAnswerCards() {
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

	public void setPlayedAnswerCards(ArrayList<AnswerCard> playedAnswerCards) {
		if (playedAnswerCards != null) {
			this.playedAnswerCards = playedAnswerCards;
		} else {
			this.playedAnswerCards = new ArrayList<AnswerCard>();
		}
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
		setQuestionCard(new QuestionCard());
		setPlayedAnswerCards(new ArrayList<AnswerCard>());
		setVotes(new ArrayList<Vote>());
		setQuestionPerRound(new ArrayList<QuestionCard>());
	}

	public Round(QuestionCard questionCard, ArrayList<AnswerCard> playedAnswerCards, ArrayList<Vote> votes,
			ArrayList<QuestionCard> questionPerRound) {
		setRoundID();
		setQuestionCard(questionCard);
		setPlayedAnswerCards(playedAnswerCards);
		setVotes(votes);
		setQuestionPerRound(questionPerRound);
	}

	// toString

	@Override
	public String toString() {
		return "Round [roundID=" + roundID + ", questionCard=" + questionCard.getQuestion() + ", playedAnswerCards="
				+ playedAnswerCards + ", votes=" + votes + ", questionPerRound=" + questionPerRound.toString() + "]";
	}

}
