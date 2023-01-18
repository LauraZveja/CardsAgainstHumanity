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

}
