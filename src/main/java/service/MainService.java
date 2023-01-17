package service;

import model.AnswerCard;

public class MainService {

	public static void main(String[] args) {

		AnswerCard ac = new AnswerCard();
		ac.readCategoryAdultTxt(); // call the method to read Adult.txt into adult_answers

		// test if it worked by printing out the contents of adult_answers
		for (String line : ac.getAdult_answers()) {
			System.out.println(line);
		}

		// Edgars
		AnswerCard ab = new AnswerCard();
		ab.readCategoryUnder_18Txt(); // call the method to read Adult.txt into adult_answers

		// test if it worked by printing out the contents of adult_answers
		for (String line : ab.getUnderage_answers()) {
			System.out.println(line);
		}

	}

}
