package service;

import model.AnswerCard;

public class MainService {

	public static void main(String[] args) {
		
		AnswerCard ac = new AnswerCard();
	    ac.readAdultTxt(); // call the method to read Adult.txt into adult_answers

	    // test if it worked by printing out the contents of adult_answers
	    for (String line : ac.getAdult_answers()) {
	        System.out.println(line);
	    }
		
		// TODO Auto-generated method stub

	}

}
